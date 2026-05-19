package com.example.pruebalogmeal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ingrediente implements Serializable {

    private int id;
    private String nombre;
    private List<Long> alergiasIds;

    public Ingrediente() {
        this.alergiasIds = new ArrayList<>();
    }

    public Ingrediente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.alergiasIds = new ArrayList<>();
    }

    // --- Getters y Setters ---

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

    public List<Long> getAlergiasIds() {
        return alergiasIds;
    }

    public void setAlergiasIds(List<Long> alergiasIds) {
        this.alergiasIds = alergiasIds;
    }

    // --- Métodos de utilidad ---

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

    @Override
    public String toString() {
        return nombre;
    }
}