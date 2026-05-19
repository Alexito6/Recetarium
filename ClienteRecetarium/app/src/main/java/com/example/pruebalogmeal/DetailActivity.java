package com.example.pruebalogmeal;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebalogmeal.API.Connector;
import com.example.pruebalogmeal.base.BaseActivity;
import com.example.pruebalogmeal.base.CallInterface;
import com.example.pruebalogmeal.base.Parameters;
import com.example.pruebalogmeal.model.Ingrediente;
import com.example.pruebalogmeal.model.Receta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends BaseActivity {

    private ImageView imgDetalle;
    private TextView tvTitulo, tvDescripcion, tvIngredientes, tvInstrucciones;
    private FloatingActionButton fabFavorito;
    private RatingBar rbDificultadDetalle;
    private TextView tvTiempoDetalle;

    private Receta receta;
    private Long usuarioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        receta = (Receta) getIntent().getSerializableExtra("receta");
        usuarioId = getIntent().getLongExtra("usuarioId", 0L);

        if (receta == null) {
            finish();
            return;
        }

        initViews();
        rellenarDatos();
    }

    private void initViews() {
        imgDetalle = findViewById(R.id.imgDetalle);
        tvTitulo = findViewById(R.id.tvTituloDetalle);
        tvDescripcion = findViewById(R.id.tvDescripcionDetalle);
        tvIngredientes = findViewById(R.id.tvIngredientesDetalle);
        tvInstrucciones = findViewById(R.id.tvInstruccionesDetalle);
        fabFavorito = findViewById(R.id.fabFavoritoDetalle);
        rbDificultadDetalle = findViewById(R.id.rbDificultadDetalle);
        tvTiempoDetalle = findViewById(R.id.tvTiempoDetalle);

        fabFavorito.setOnClickListener(v -> {
            if (receta.isFavorita()) {
                gestionarFavorito("DELETE");
            } else {
                gestionarFavorito("POST");
            }
        });
    }

    private void rellenarDatos() {
        tvTitulo.setText(receta.getTitulo());
        tvDescripcion.setText(receta.getDescripcion());
        tvInstrucciones.setText(receta.getInstrucciones());

        rbDificultadDetalle.setRating(receta.getDificultad());
        tvTiempoDetalle.setText(receta.getDuracion() + " min");

        // Formatear lista de ingredientes
        if (receta.getIngredientes() != null) {
            StringBuilder sb = new StringBuilder();
            for (Ingrediente i : receta.getIngredientes()) {
                sb.append("• ").append(i.getNombre()).append("\n");
            }
            tvIngredientes.setText(sb.toString());
        }

        // Picasso carga directamente el enlace web absoluto de ImgBB
        String urlImagenDirecta = receta.getImagenUrl();
        if (urlImagenDirecta != null && !urlImagenDirecta.isEmpty()) {
            Picasso.get()
                    .load(urlImagenDirecta)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_delete)
                    .into(imgDetalle);
        } else {
            imgDetalle.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        actualizarIconoFavorito();
    }

    private void actualizarIconoFavorito() {
        if (receta.isFavorita()) {
            fabFavorito.setImageResource(R.drawable.star_bitmap);
        } else {
            fabFavorito.setImageResource(R.drawable.star_border_bitmap);
        }

        fabFavorito.setImageTintList(
                android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor("#FFC107")
                )
        );
    }

    private void gestionarFavorito(String metodo) {
        executeCall(new CallInterface<Map>() {
            @Override
            public Map doInBackground() throws Exception {
                if (metodo.equals("POST")) {
                    Map<String, Long> data = new HashMap<>();
                    data.put("receta_id", receta.getId());
                    data.put("usuario_id", usuarioId);
                    return Connector.getConector().post(Map.class, data, "api/favoritos");
                } else {
                    String path = "api/favoritos/usuario/" + usuarioId + "/receta/" + receta.getId();
                    return Connector.getConector().delete(Map.class, path);
                }
            }

            @Override
            public void doInUI(Map result) {

                receta.setFavorita(metodo.equals("POST"));
                actualizarIconoFavorito();

                setResult(RESULT_OK);

                String msg = metodo.equals("POST") ? "Añadida a favoritos" : "Eliminada de favoritos";
                Toast.makeText(DetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doInError(Context context, Exception e) {
                Toast.makeText(context, "Error al actualizar favorito", Toast.LENGTH_SHORT).show();
            }
        });
    }
}