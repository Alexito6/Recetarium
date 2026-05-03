package com.example.pruebalogmeal.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebalogmeal.R;
import com.example.pruebalogmeal.model.Receta;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorRecetas extends RecyclerView.Adapter<AdaptadorRecetas.ViewHolder> {

    private List<Receta> recetas;
    private Context context;
    private LayoutInflater layoutInflater;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    public AdaptadorRecetas(Context context, List<Receta> recetas, ActivityResultLauncher<Intent> activityResultLauncher) {
        this.context = context;
        this.recetas = recetas;
        this.activityResultLauncher = activityResultLauncher;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        int numIngredientes = (receta.getIngredientes() != null) ? receta.getIngredientes().size() : 0;
        holder.tvCantIngredientes.setText(numIngredientes + " ingredientes");

        Picasso.get()
                .load(Parameters.URL_IMAGE_BASE + receta.getImagenUrl())
//                .placeholder(R.drawable.loading_placeholder) mientras carga
//                .error(R.drawable.error_image) si falla
                .into(holder.imgReceta);

//        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(context, DetailActivity.class);
//            intent.putExtra("receta", receta);
//            intent.putExtra("posicion", holder.getAdapterPosition());
//            activityResultLauncher.launch(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return (recetas != null) ? recetas.size() : 0;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgReceta;
        private TextView tvTitulo, tvDescripcion, tvCantIngredientes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgReceta = itemView.findViewById(R.id.imgReceta);
            tvTitulo = itemView.findViewById(R.id.tvTituloReceta);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionReceta);
            tvCantIngredientes = itemView.findViewById(R.id.tvCantIngredientes);
        }
    }
}
