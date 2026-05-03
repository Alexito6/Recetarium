package com.example.pruebalogmeal.model;
import java.io.Serializable;
import java.util.Objects;

public class Ingrediente implements Serializable {

    private int id;
    private String nombre;

    public Ingrediente() {
    }

    public Ingrediente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // El toString suele ser útil para mostrar el nombre directamente en Spinners o diálogos
    @Override
    public String toString() {
        return nombre;
    }
}
