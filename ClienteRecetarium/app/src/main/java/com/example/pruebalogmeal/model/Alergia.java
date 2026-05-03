package com.example.pruebalogmeal.model;

import java.io.Serializable;
import java.util.Objects;

public class Alergia implements Serializable {

    private Long id;
    private String nombre;

    public Alergia() {
    }

    public Alergia(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alergia alergia = (Alergia) o;
        return Objects.equals(id, alergia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}