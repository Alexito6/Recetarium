package com.example.pruebalogmeal.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebalogmeal.API.Connector;
import com.example.pruebalogmeal.DetailActivity;
import com.example.pruebalogmeal.R;
import com.example.pruebalogmeal.model.Ingrediente;
import com.example.pruebalogmeal.model.Receta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptadorRecetas extends RecyclerView.Adapter<AdaptadorRecetas.ViewHolder> {

    private List<Receta> recetas;
    private List<Receta> recetasFull;
    private Context context;
    private LayoutInflater layoutInflater;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private Long usuarioId;

    // --- Interfaz para delegar el clic a la HomeActivity ---
    public interface OnItemClickListener {
        void onItemClick(Receta receta);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    // -------------------------------------------------------------

    // Constructor corregido para alinearse perfectamente con la llamada desde HomeActivity
    public AdaptadorRecetas(List<Receta> recetas, Context context, ActivityResultLauncher<Intent> activityResultLauncher, Long usuarioId) {
        this.context = context;
        this.recetas = recetas != null ? recetas : new ArrayList<>();
        this.recetasFull = new ArrayList<>(this.recetas);
        this.activityResultLauncher = activityResultLauncher;
        this.usuarioId = usuarioId;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setRecetas(List<Receta> nuevasRecetas) {
        this.recetas = nuevasRecetas != null ? nuevasRecetas : new ArrayList<>();
        this.recetasFull = new ArrayList<>(this.recetas);
        notifyDataSetChanged();
    }

    public void filtrar(String texto) {
        if (texto == null || texto.isEmpty()) {
            recetas = new ArrayList<>(recetasFull);
        } else {
            String query = texto.toLowerCase().trim();
            List<Receta> filtrados = new ArrayList<>();

            for (Receta r : recetasFull) {
                String titulo = r.getTitulo() != null ? r.getTitulo() : "";
                boolean coincideTitulo = titulo.toLowerCase().contains(query);
                boolean coincideIngredientes = false;

                if (r.getIngredientes() != null) {
                    for (Ingrediente ingrediente : r.getIngredientes()) {
                        if (ingrediente.getNombre() != null && ingrediente.getNombre().toLowerCase().contains(query)) {
                            coincideIngredientes = true;
                            break;
                        }
                    }
                }
                if (coincideTitulo || coincideIngredientes) {
                    filtrados.add(r);
                }
            }
            recetas = filtrados;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_receta, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Receta receta = recetas.get(position);

        holder.tvTitulo.setText(receta.getTitulo());
        holder.tvDescripcion.setText(receta.getDescripcion());

        // Manejo del estado visual del favorito
        if (receta.isFavorita()) {
            holder.btnFavorito.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.btnFavorito.setImageResource(android.R.drawable.btn_star_big_off);
        }

        int numIngredientes = (receta.getIngredientes() != null) ? receta.getIngredientes().size() : 0;
        holder.tvCantIngredientes.setText(numIngredientes + " ingredientes");

        // LÓGICA DE PICASSO: Carga directa de la URL absoluta de ImgBB
        String urlImagenDirecta = receta.getImagenUrl();

        if (urlImagenDirecta != null && !urlImagenDirecta.isEmpty()) {
            Picasso.get()
                    .load(urlImagenDirecta)
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_delete)
                    .into(holder.imgReceta);
        } else {
            holder.imgReceta.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        // Evento de clic en el botón de favoritos
        holder.btnFavorito.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                Receta recetaActual = recetas.get(currentPos);
                boolean nuevoEstado = !recetaActual.isFavorita();
                recetaActual.setFavorita(nuevoEstado);
                holder.btnFavorito.setImageResource(
                        nuevoEstado ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off
                );
                gestionarFavoritoEnServidor(recetaActual, nuevoEstado ? "POST" : "DELETE", currentPos);
            }
        });

        // Evento de clic en la tarjeta de la receta
        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onItemClick(receta);
            } else if (activityResultLauncher != null) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("receta", receta);
                intent.putExtra("usuarioId", usuarioId);
                activityResultLauncher.launch(intent);
            } else {
                Toast.makeText(context, "Error al abrir el detalle: Lanzador no listo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gestionarFavoritoEnServidor(Receta receta, String metodo, int position) {
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            activity.executeCall(new CallInterface<Map>() {
                @Override
                public Map doInBackground() throws Exception {
                    if (metodo.equals("POST")) {
                        Map<String, Long> favoritoData = new HashMap<>();
                        favoritoData.put("receta_id", receta.getId());
                        favoritoData.put("usuario_id", usuarioId);
                        return Connector.getConector().post(Map.class, favoritoData, "api/favoritos");
                    } else {
                        String path = "api/favoritos/usuario/" + usuarioId + "/receta/" + receta.getId();
                        return Connector.getConector().delete(Map.class, path);
                    }
                }

                @Override
                public void doInUI(Map result) {
                    receta.setFavorita(metodo.equals("POST"));
                    notifyItemChanged(position);
                    String msg = metodo.equals("POST") ? "Añadido a favoritos" : "Eliminado de favoritos";
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void doInError(Context context, Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error al gestionar favorito", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (recetas != null) ? recetas.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgReceta;
        private TextView tvTitulo, tvDescripcion, tvCantIngredientes;
        private ImageButton btnFavorito;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgReceta = itemView.findViewById(R.id.imgReceta);
            tvTitulo = itemView.findViewById(R.id.tvTituloReceta);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionReceta);
            tvCantIngredientes = itemView.findViewById(R.id.tvCantIngredientes);
            btnFavorito = itemView.findViewById(R.id.btnFavorito);
        }
    }
}