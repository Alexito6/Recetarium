package com.example.pruebalogmeal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebalogmeal.API.Connector;
import com.example.pruebalogmeal.base.BaseActivity;
import com.example.pruebalogmeal.base.CallInterface;
import com.example.pruebalogmeal.model.Alergia;
import com.example.pruebalogmeal.model.Usuario;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ConfigurarAlergiasActivity extends BaseActivity {

    private TextInputEditText etBuscar;
    private ChipGroup cgSeleccionadas;
    private RecyclerView rvDisponibles;
    private MaterialButton btnGuardar;
    private Context context = this;

    private Usuario usuarioLogueado;
    private List<Alergia> todasLasAlergiasServer = new ArrayList<>();
    private List<Alergia> listaFiltradaBusqueda = new ArrayList<>();
    private List<Alergia> alergiasSeleccionadasUsuario = new ArrayList<>();

    private AlergiasAdapter adaptadorRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_alergias);

        usuarioLogueado = (Usuario) getIntent().getSerializableExtra("usuarioLogueado");
        if (usuarioLogueado == null) {
            Toast.makeText(context, "Error al cargar usuario", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Clonamos las alergias actuales del usuario para poder editarlas de forma segura
        if (usuarioLogueado.getAlergias() != null) {
            alergiasSeleccionadasUsuario = new ArrayList<>(usuarioLogueado.getAlergias());
        }

        initViews();
        setupRecyclerView();
        redibujaChips();
        cargarTodasLasAlergiasDelServidor();
    }

    private void initViews() {
        etBuscar = findViewById(R.id.etBuscarAlergia);
        cgSeleccionadas = findViewById(R.id.cgAlergiasSeleccionadas);
        rvDisponibles = findViewById(R.id.rvAlergiasDisponibles);
        btnGuardar = findViewById(R.id.btnGuardarAlergias);

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarAlergias(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnGuardar.setOnClickListener(v -> guardarAlergiasEnServidor());
    }

    private void setupRecyclerView() {
        adaptadorRecyclerView = new AlergiasAdapter();
        rvDisponibles.setLayoutManager(new LinearLayoutManager(context));
        rvDisponibles.setAdapter(adaptadorRecyclerView);
    }

    private void cargarTodasLasAlergiasDelServidor() {
        executeCall(new CallInterface<List<Alergia>>() {
            @Override
            public List<Alergia> doInBackground() throws Exception {
                // Llama al endpoint de tu API que devuelve el catálogo completo de alergias
                return Connector.getConector().getAsList(Alergia.class, "api/alergias");
            }

            @Override
            public void doInUI(List<Alergia> result) {
                if (result != null) {
                    todasLasAlergiasServer = result;
                    filtrarAlergias("");
                }
            }

            @Override
            public void doInError(Context context, Exception e) {
                Toast.makeText(context, "Error al cargar catálogo de alergias", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filtrarAlergias(String texto) {
        listaFiltradaBusqueda.clear();
        String query = texto.toLowerCase().trim();

        for (Alergia a : todasLasAlergiasServer) {
            // Solo se muestra en los resultados si coincide con la búsqueda
            // Y si el usuario NO la tiene seleccionada ya arriba en los chips
            boolean coincideTexto = a.getNombre().toLowerCase().contains(query);
            boolean yaSeleccionada = alergiasSeleccionadasUsuario.contains(a);

            if (coincideTexto && !yaSeleccionada) {
                listaFiltradaBusqueda.add(a);
            }
        }
        adaptadorRecyclerView.notifyDataSetChanged();
    }

    private void redibujaChips() {
        cgSeleccionadas.removeAllViews();
        for (Alergia alergia : alergiasSeleccionadasUsuario) {
            Chip chip = new Chip(context);
            chip.setText(alergia.getNombre());
            chip.setCloseIconVisible(true); // Muestra la "X" para eliminar

            // Si el usuario pulsa en la "X", se elimina de su lista y se vuelve a habilitar abajo
            chip.setOnCloseIconClickListener(v -> {
                alergiasSeleccionadasUsuario.remove(alergia);
                redibujaChips();
                filtrarAlergias(etBuscar.getText().toString());
            });

            cgSeleccionadas.addView(chip);
        }
    }

    private void guardarAlergiasEnServidor() {
        executeCall(new CallInterface<Usuario>() {
            @Override
            public Usuario doInBackground() throws Exception {

                usuarioLogueado.setAlergias(alergiasSeleccionadasUsuario);

                return Connector.getConector().put(Usuario.class, usuarioLogueado, "api/usuarios/" + usuarioLogueado.getId());
            }

            @Override
            public void doInUI(Usuario usuarioActualizado) {
                if (usuarioActualizado != null) {
                    Toast.makeText(context, "Alergias guardadas con éxito", Toast.LENGTH_SHORT).show();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("usuarioLogueado", usuarioActualizado);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }

            @Override
            public void doInError(Context context, Exception e) {
                Toast.makeText(context, "Error al guardar los cambios en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class AlergiasAdapter extends RecyclerView.Adapter<AlergiasAdapter.AlergiaViewHolder> {

        @NonNull
        @Override
        public AlergiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alergia_busqueda, parent, false);
            return new AlergiaViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull AlergiaViewHolder holder, int position) {
            Alergia alergia = listaFiltradaBusqueda.get(position);
            holder.tvNombre.setText(alergia.getNombre());

            holder.itemView.setOnClickListener(v -> {
                alergiasSeleccionadasUsuario.add(alergia);
                etBuscar.setText("");
                redibujaChips();
                filtrarAlergias("");
            });
        }

        @Override
        public int getItemCount() {
            return listaFiltradaBusqueda.size();
        }

        class AlergiaViewHolder extends RecyclerView.ViewHolder {
            TextView tvNombre;
            ImageView ivAgregar;

            public AlergiaViewHolder(@NonNull View itemView) {
                super(itemView);
                tvNombre = itemView.findViewById(R.id.tvNombreAlergiaItem);
                ivAgregar = itemView.findViewById(R.id.ivAgregarAlergia);
            }
        }
    }
}