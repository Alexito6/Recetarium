package com.example.pruebalogmeal.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

    private Long id;
    private String nombre;
    private String email;

    // Usamos SerializedName por si en el JSON del servidor
    // la propiedad se llama distinto (ej. password_hash)
    @SerializedName("passwordHash")
    private String password;

    private String fechaRegistro;

    private List<Alergia> alergias;

    // Constructor vacío necesario para GSON
    public Usuario() {
    }

    // Constructor para registro/creación
    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public List<Alergia> getAlergias() { return alergias; }
    public void setAlergias(List<Alergia> alergias) { this.alergias = alergias; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
