package com.example.pruebalogmeal.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Receta implements Serializable {

    private int id;
    private String titulo;
    private String descripcion;
    private String instrucciones;
    private String imagen_url;
    private List<Ingrediente> ingredientes;

    public Receta() {
    }

    public Receta(int id, String titulo, String descripcion, String instrucciones, String imagen_url, List<Ingrediente> ingredientes) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.instrucciones = instrucciones;
        this.imagen_url = imagen_url;
        this.ingredientes = ingredientes;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receta receta = (Receta) o;
        return id == receta.id;
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
