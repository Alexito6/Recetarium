package com.example.pruebalogmeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.pruebalogmeal.base.AdaptadorRecetas;
import com.example.pruebalogmeal.model.Receta;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavoritosSheet extends BottomSheetDialogFragment {

    public interface OnFavoritoClickListener {
        void onFavoritoClick(Receta receta);
    }

    private AdaptadorRecetas adaptadorFavoritos;
    private List<Receta> recetasTotales;
    private Long usuarioId;
    private OnFavoritoClickListener clickListener;
    private RecyclerView rvFavoritos;

    public FavoritosSheet(List<Receta> recetasTotales, Long usuarioId, OnFavoritoClickListener clickListener) {
        this.recetasTotales = recetasTotales;
        this.usuarioId = usuarioId;
        this.clickListener = clickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_favoritos_sheet, container, false);

        rvFavoritos = view.findViewById(R.id.rvFavoritosSheet);
        setupRecyclerView();

        return view;
    }

    private void setupRecyclerView() {
        // Filtramos las recetas que son favoritas del usuario
        List<Receta> favoritas = new ArrayList<>();
        if (recetasTotales != null) {
            for (Receta r : recetasTotales) {
                if (r.isFavorita()) {
                    favoritas.add(r);
                }
            }
        }

        adaptadorFavoritos = new AdaptadorRecetas(favoritas, getContext(), null, usuarioId);


        adaptadorFavoritos.setOnItemClickListener(receta -> {
            if (clickListener != null) {
                clickListener.onFavoritoClick(receta);
            }
        });

        rvFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoritos.setAdapter(adaptadorFavoritos);
    }
}