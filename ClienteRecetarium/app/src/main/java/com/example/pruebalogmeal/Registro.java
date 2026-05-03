package com.example.pruebalogmeal;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebalogmeal.API.Connector;
import com.example.pruebalogmeal.base.BaseActivity;
import com.example.pruebalogmeal.base.CallInterface;
import com.example.pruebalogmeal.model.Usuario;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Registro extends BaseActivity {

    private ImageButton btnBack;
    private TextView tvBackToLogin;
    private MaterialButton btnSignUp;
    private TextInputEditText etFullName, etRegisterEmail, etRegisterPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        btnBack = findViewById(R.id.btnBack);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        etFullName = findViewById(R.id.etFullName);
        etRegisterEmail = findViewById(R.id.etRegisterEmail);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnBack.setOnClickListener(v -> finish());
        tvBackToLogin.setOnClickListener(v -> finish());

        btnSignUp.setOnClickListener(v -> realizarRegistro());
    }

    private void realizarRegistro() {
        String nombre = etFullName.getText().toString().trim();
        String email = etRegisterEmail.getText().toString().trim();
        String pass = etRegisterPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();

        // Validaciones previas a la llamada API
        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(confirm)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario user = new Usuario(nombre, email, pass);
        addUser(user);
    }

    public void addUser(Usuario usuario) {
        executeCall(new CallInterface<Usuario>() {
            @Override
            public Usuario doInBackground() throws Exception {
                return Connector.getConector().post(Usuario.class, usuario, "api/usuarios");
            }

            @Override
            public void doInUI(Usuario data) {
                if (data != null) {
                    Toast.makeText(Registro.this, "Usuario " + data.getNombre() + " registrado!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void doInError(Context context, Exception e) {
                Toast.makeText(context, "Error: El usuario o email ya están registrados.", Toast.LENGTH_LONG).show();
            }
        });
    }
}