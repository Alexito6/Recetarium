package com.example.pruebalogmeal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pruebalogmeal.API.Connector;
import com.example.pruebalogmeal.base.BaseActivity;
import com.example.pruebalogmeal.base.CallInterface;
import com.example.pruebalogmeal.model.Usuario;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private static final String TAG = "LOGMEAL";
    private Context context = this;

    // Vistas de navegación y Login
    private TextView enlaceRegistro, tvForgotPassword;
    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnLogin;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- 1. INICIALIZACIÓN DE VISTAS ---
        enlaceRegistro = findViewById(R.id.tvSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // --- 2. CONFIGURACIÓN DE RESULT LAUNCHER ---
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
//                Intent data=result.getData();
//                if (data!=null){
//                    Fruta fruta=(Fruta) data.getSerializableExtra("fruta");
//                    adaptadorRV.addFruta(fruta);
//                    adaptadorRV.notifyItemInserted(adaptadorRV.getItemCount()-1);
//                }
            }
        });

        // --- 3. LISTENERS DE NAVEGACIÓN ---
        enlaceRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Registro.class);
                activityResultLauncher.launch(intent);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CambiarPassword.class);
                activityResultLauncher.launch(intent);
            }
        });

        // --- 4. LISTENERS DE ACCIÓN (LOGIN) ---
        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realizarLogin();
                }
            });
        }

        // --- 5. LÓGICA DE PRUEBA COMENTADA ---
//        try {
//            File imageFile = copyImageFromRaw();
//            testLogMealApi(imageFile);
//        } catch (Exception e) {
//            Log.e(TAG, "Error preparando la imagen", e);
//        }
    }

    // --- MÉTODOS DE LÓGICA DE NEGOCIO ---

    private void realizarLogin() {
        String identifier = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (identifier.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        executeCall(new CallInterface<Usuario>() {
            @Override
            public Usuario doInBackground() throws Exception {
                Map<String, String> params = new HashMap<>();
                params.put("identifier", identifier);
                params.put("password", password);

                return Connector.getConector().postWithParams(Usuario.class, params, "api/usuarios/login");
            }

            @Override
            public void doInUI(Usuario data) {
                if (data != null) {
                    Toast.makeText(MainActivity.this, "¡Bienvenido " + data.getNombre() + "!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Login correcto: " + data.getEmail());
                }
            }

            @Override
            public void doInError(Context context, Exception e) {
                Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Copia res/raw/prueba.jpg a un archivo real accesible por Retrofit
     */
    private File copyImageFromRaw() throws Exception {
        InputStream is = getResources().openRawResource(R.raw.macarron);
        File outFile = new File(getExternalFilesDir(null), "macarron.jpg");

        FileOutputStream fos = new FileOutputStream(outFile);
        byte[] buffer = new byte[4096];
        int read;
        while ((read = is.read(buffer)) != -1) {
            fos.write(buffer, 0, read);
        }

        fos.close();
        is.close();

        Log.d(TAG, "Imagen copiada en: " + outFile.getAbsolutePath());
        return outFile;
    }

    private void testLogMealApi(File file) {
        if (!file.exists()) {
            Log.e(TAG, "Archivo no encontrado");
            return;
        }

        RequestBody requestFile = RequestBody.create(file, MediaType.parse("image/jpeg"));
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        LogMealApi api = ApiClient.getClient().create(LogMealApi.class);
        String token = "Bearer " + BuildConfig.LOGMEAL_APIUSER_TOKEN;

        api.recognizeFood(token, body).enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("LOGMEAL", "Error HTTP " + response.code());
                    return;
                }

                FoodResponse data = response.body();
                if (data.recognition_results == null || data.recognition_results.isEmpty()) {
                    Log.e("LOGMEAL", "No se reconoció ningún plato");
                    return;
                }

                FoodItem best = data.recognition_results.get(0);
                String nombre = best.name;
                float prob = best.prob;

                Log.d("LOGMEAL", "PLATO DETECTADO: " + nombre + " (" + prob + ")");
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Log.e(TAG, "Fallo en la llamada", t);
            }
        });
    }
}