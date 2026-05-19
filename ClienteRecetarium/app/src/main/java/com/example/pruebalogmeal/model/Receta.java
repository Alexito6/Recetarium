package com.example.pruebalogmeal.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Receta implements Serializable {

    private Long id;
    private String titulo;
    private String descripcion;
    private String instrucciones;
    private String imagen_url;
    private List<Ingrediente> ingredientes;
    private boolean favorito;
    private int dificultad;
    private int duracion;

    public Receta() {
    }

    public Receta(Long id, String titulo, String descripcion, String instrucciones, String imagen_url, List<Ingrediente> ingredientes, boolean favorito, int dificultad, int duracion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.instrucciones = instrucciones;
        this.imagen_url = imagen_url;
        this.ingredientes = ingredientes;
        this.favorito = favorito;
        this.dificultad = dificultad;
        this.duracion = duracion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getImagenUrl() {
        return imagen_url;
    }

    public void setImagenUrl(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public boolean isFavorita() {
        return favorito;
    }

    public void setFavorita(boolean favorito) {
        this.favorito = favorito;
    }

    // 🌟 GETTERS Y SETTERS PARA LOS NUEVOS CAMPOS
    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receta receta = (Receta) o;
        return Objects.equals(id, receta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return titulo;
    }
}