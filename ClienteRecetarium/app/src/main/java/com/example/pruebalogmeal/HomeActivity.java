package com.example.pruebalogmeal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebalogmeal.API.Connector;
import com.example.pruebalogmeal.base.AdaptadorRecetas;
import com.example.pruebalogmeal.base.BaseActivity;
import com.example.pruebalogmeal.base.CallInterface;
import com.example.pruebalogmeal.model.Alergia;
import com.example.pruebalogmeal.model.Ingrediente;
import com.example.pruebalogmeal.model.Receta;
import com.example.pruebalogmeal.model.Usuario;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    private static final String TAG = "LOGMEAL_HOME";

    private ChipGroup cgAlergiasHome;
    private TextInputEditText etSearch;
    private TextView tvWelcome;
    private final Context context = this;
    private RecyclerView recyclerView;
    private AdaptadorRecetas adaptadorRV;
    private List<Receta> listaRecetas;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private Usuario usuarioLogueado;

    private ExtendedFloatingActionButton fabFavoritos;
    private MaterialCardView cardScan;
    private NestedScrollView nestedScrollView;

    private ActivityResultLauncher<String> galleryLauncher;
    private ActivityResultLauncher<Uri> cameraLauncher;
    private Uri cameraImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Picasso.get().setLoggingEnabled(true);
        tvWelcome = findViewById(R.id.tvWelcome);

        usuarioLogueado = (Usuario) getIntent().getSerializableExtra("usuarioLogueado");
        if (usuarioLogueado != null) {
            tvWelcome.setText("¡Hola, " + usuarioLogueado.getNombre() + "!");
        }

        setupActivityLauncher();
        setupLaunchersIA();
        initViews();
        setupRecyclerView();
        cargarRecetasDesdeServidor();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rvRecetas);
        etSearch = findViewById(R.id.etSearch);
        fabFavoritos = findViewById(R.id.fabFavoritos);
        cardScan = findViewById(R.id.cardScan);
        cgAlergiasHome = findViewById(R.id.cgAlergiasHome);

        nestedScrollView = findViewById(R.id.nestedScrollViewHome);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adaptadorRV != null) {
                    adaptadorRV.filtrar(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        configurarEscudoSalud();

        fabFavoritos.setOnClickListener(v -> {
            if (listaRecetas != null && usuarioLogueado != null) {
                FavoritosSheet sheet = new FavoritosSheet(listaRecetas, usuarioLogueado.getId(), receta -> {
                    if (getSupportFragmentManager().findFragmentByTag("FavoritosSheet") != null) {
                        ((com.google.android.material.bottomsheet.BottomSheetDialogFragment)
                                getSupportFragmentManager().findFragmentByTag("FavoritosSheet")).dismiss();
                    }
                    validarYSaltarADetalle(receta);
                });
                sheet.show(getSupportFragmentManager(), "FavoritosSheet");
            } else {
                Toast.makeText(context, "Cargando datos...", Toast.LENGTH_SHORT).show();
            }
        });

        if (cardScan != null) {
            cardScan.setOnClickListener(v -> mostrarDialogoOrigenImagen());
        }

        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY > oldScrollY) {
                    fabFavoritos.shrink();
                } else {
                    fabFavoritos.extend();
                }
            });
        }
    }

    private void mostrarDialogoOrigenImagen() {
        CharSequence[] opciones = {"Hacer foto con la Cámara", "Seleccionar de la Galería"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Identificar ingrediente con IA");
        builder.setItems(opciones, (dialog, item) -> {
            if (item == 0) {
                abrirCamara();
            } else if (item == 1) {
                abrirGaleria();
            }
        });
        builder.show();
    }

    private void abrirGaleria() {
        galleryLauncher.launch("image/*");
    }

    private void abrirCamara() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Escaneo_IA");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Imagen para LogMeal");

        cameraImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        if (cameraImageUri != null) {
            cameraLauncher.launch(cameraImageUri);
        } else {
            Toast.makeText(this, "Error al preparar la cámara", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupLaunchersIA() {
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                this::procesarUriSeleccionada
        );

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                isSuccess -> {
                    if (isSuccess && cameraImageUri != null) {
                        procesarUriSeleccionada(cameraImageUri);
                    } else {
                        Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void procesarUriSeleccionada(Uri uri) {
        if (uri != null) {
            try {
                File file = convertUriToFile(uri);
                procesarReconocimientoLogMeal(file);
            } catch (Exception e) {
                Log.e(TAG, "Error procesando el archivo de imagen", e);
                Toast.makeText(this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void procesarReconocimientoLogMeal(File file) {
        if (!file.exists()) return;

        showProgress();

        RequestBody requestFile = RequestBody.create(file, MediaType.parse("image/jpeg"));
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        LogMealApi api = ApiClient.getClient().create(LogMealApi.class);
        String token = "Bearer " + BuildConfig.LOGMEAL_APIUSER_TOKEN;

        api.recognizeFood(token, body).enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                hideProgress();
                if (!response.isSuccessful() || response.body() == null) {
                    if (response.code() == 413) {
                        Toast.makeText(HomeActivity.this, "La imagen es demasiado grande para la IA", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(HomeActivity.this, "Error en el servicio de LogMeal (Código " + response.code() + ")", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                FoodResponse data = response.body();
                if (data.recognition_results == null || data.recognition_results.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "La IA no identificó ningún alimento", Toast.LENGTH_SHORT).show();
                    return;
                }

                FoodItem bestMatch = data.recognition_results.get(0);
                String rawIngredient = bestMatch.name.toLowerCase();

                Toast.makeText(HomeActivity.this, "IA detectó (Inglés): " + rawIngredient, Toast.LENGTH_LONG).show();
                buscarRecetasPorIngredienteEnBackend(rawIngredient);
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                hideProgress();
                Log.e(TAG, "Error de red con LogMeal", t);
                Toast.makeText(HomeActivity.this, "Fallo de conexión con la IA", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buscarRecetasPorIngredienteEnBackend(String ingrediente) {
        if (usuarioLogueado == null) return;

        executeCall(new CallInterface<List<Receta>>() {
            @Override
            public List<Receta> doInBackground() throws Exception {
                String urlConParams = "api/recetas/buscar-por-ingrediente?ingrediente=" + ingrediente + "&usuarioId=" + usuarioLogueado.getId();
                return Connector.getConector().getAsList(Receta.class, urlConParams);
            }

            @Override
            public void doInUI(List<Receta> data) {
                if (data != null && !data.isEmpty()) {
                    listaRecetas = data;
                    adaptadorRV.setRecetas(data);
                    Toast.makeText(context, "Se encontraron " + data.size() + " recetas con " + ingrediente, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "No hay recetas cargadas con: " + ingrediente, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void doInError(Context context, Exception e) {
                Log.e(TAG, "Error de parseo en backend", e);
                Toast.makeText(context, "Respuesta inesperada del servidor", Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void configurarEscudoSalud() {
        if (usuarioLogueado != null && cgAlergiasHome != null) {
            cgAlergiasHome.removeAllViews();
            List<Alergia> listaAlergias = usuarioLogueado.getAlergias();

            if (listaAlergias != null && !listaAlergias.isEmpty()) {
                for (Alergia alergia : listaAlergias) {
                    Chip chip = new Chip(context);
                    chip.setText(alergia.getNombre());
                    chip.setTextColor(android.graphics.Color.parseColor("#A0522D"));
                    chip.setChipBackgroundColor(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#F4E7DA")));
                    chip.setChipStrokeWidth(0f);
                    chip.setOnClickListener(v -> irAConfigurarAlergias());
                    cgAlergiasHome.addView(chip);
                }
            } else {
                Chip chipConfig = new Chip(context);
                chipConfig.setText("Configurar Alergias 🛡️");
                chipConfig.setTextColor(android.graphics.Color.parseColor("#A0522D"));
                chipConfig.setChipBackgroundColor(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#F4E7DA")));
                chipConfig.setChipIcon(androidx.core.content.ContextCompat.getDrawable(context, android.R.drawable.ic_notification_overlay));
                chipConfig.setChipIconTint(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#A0522D")));
                chipConfig.setChipStrokeWidth(0f);
                chipConfig.setOnClickListener(v -> irAConfigurarAlergias());
                cgAlergiasHome.addView(chipConfig);
            }
        }
    }

    private void irAConfigurarAlergias() {
        if (usuarioLogueado == null) return;
        Intent intent = new Intent(context, ConfigurarAlergiasActivity.class);
        intent.putExtra("usuarioLogueado", usuarioLogueado);
        activityResultLauncher.launch(intent);
    }

    private void setupRecyclerView() {
        listaRecetas = new ArrayList<>();
        Long idUsuario = (usuarioLogueado != null) ? usuarioLogueado.getId() : 0L;

        adaptadorRV = new AdaptadorRecetas(listaRecetas, this, null, idUsuario);
        adaptadorRV.setOnItemClickListener(this::validarYSaltarADetalle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorRV);
    }

    public void validarYSaltarADetalle(Receta receta) {
        if (usuarioLogueado == null) {
            abrirDetalle(receta);
            return;
        }

        List<Alergia> alergiasUsuario = usuarioLogueado.getAlergias();
        List<Ingrediente> ingredientesReceta = new ArrayList<>(receta.getIngredientes());
        StringBuilder detectadas = new StringBuilder();
        boolean peligro = false;

        if (alergiasUsuario != null && !ingredientesReceta.isEmpty()) {
            for (Alergia a : alergiasUsuario) {
                long idAlergiaUsuario = a.getId();

                for (Ingrediente i : ingredientesReceta) {
                    if (i.getAlergiasIds() != null && i.getAlergiasIds().contains(idAlergiaUsuario)) {
                        peligro = true;
                        if (detectadas.indexOf(a.getNombre()) == -1) {
                            if (detectadas.length() > 0) detectadas.append(", ");
                            detectadas.append(a.getNombre());
                        }
                        break;
                    }
                }
            }
        }

        if (peligro) {
            new MaterialAlertDialogBuilder(context)
                    .setTitle("⚠️ Alerta de Alergia")
                    .setMessage("Esta receta contiene ingredientes vinculados a tus alergias: " + detectadas + ". ¿Deseas continuar bajo tu responsabilidad?")
                    .setPositiveButton("Ver receta", (dialog, which) -> abrirDetalle(receta))
                    .setNegativeButton("Cancelar", null)
                    .show();
        } else {
            abrirDetalle(receta);
        }
    }

    private void abrirDetalle(Receta receta) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("receta", receta);
        intent.putExtra("usuarioId", usuarioLogueado != null ? usuarioLogueado.getId() : 0L);
        activityResultLauncher.launch(intent);
    }

    private void setupActivityLauncher() {
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Usuario usuarioActualizado = (Usuario) result.getData().getSerializableExtra("usuarioLogueado");
                        if (usuarioActualizado != null) {
                            this.usuarioLogueado = usuarioActualizado;
                            configurarEscudoSalud();
                        }
                    } else if (result.getResultCode() == RESULT_OK) {
                        cargarRecetasDesdeServidor();
                    }
                }
        );
    }

    private void cargarRecetasDesdeServidor() {
        executeCall(new CallInterface<List<Receta>>() {
            @Override
            public List<Receta> doInBackground() throws Exception {
                if (usuarioLogueado != null) {
                    return Connector.getConector().getAsList(Receta.class, "api/recetas/usuario/" + usuarioLogueado.getId());
                } else {
                    return Connector.getConector().getAsList(Receta.class, "api/recetas");
                }
            }

            @Override
            public void doInUI(List<Receta> data) {
                if (data != null) {
                    listaRecetas = data;
                    adaptadorRV.setRecetas(data);
                } else {
                    Toast.makeText(context, "No se han podido cargar las recetas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void doInError(Context context, Exception e) {
                Toast.makeText(context, "Error de conexión: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private File convertUriToFile(Uri uri) throws Exception {
        InputStream is = getContentResolver().openInputStream(uri);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmapOriginal = BitmapFactory.decodeStream(is, null, options);
        if (is != null) is.close();

        File tempFile = new File(getExternalFilesDir(null), "upload_image.jpg");
        FileOutputStream fos = new FileOutputStream(tempFile);

        if (bitmapOriginal != null) {
            bitmapOriginal.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            bitmapOriginal.recycle();
        }

        fos.flush();
        fos.close();
        return tempFile;
    }
}