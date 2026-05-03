package com.example.pruebalogmeal;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pruebalogmeal.API.Connector;
import com.example.pruebalogmeal.base.BaseActivity;
import com.example.pruebalogmeal.base.CallInterface;
import com.example.pruebalogmeal.model.Usuario;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class CambiarPassword extends BaseActivity {

    private ImageButton btnBack;
    private MaterialButton btnUpdate;
    private TextInputEditText etUserIdentifier, etNewPassword, etConfirmNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_password);

        // Inicialización de vistas
        btnBack = findViewById(R.id.btnBackChange);
        btnUpdate = findViewById(R.id.btnUpdatePassword);
        etUserIdentifier = findViewById(R.id.etUserIdentifier);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        if (btnUpdate != null) {
            btnUpdate.setOnClickListener(v -> validarYCambiar());
        }
    }

    private void validarYCambiar() {
        String identifier = etUserIdentifier.getText().toString().trim();
        String newPass = etNewPassword.getText().toString();
        String confirm = etConfirmNewPassword.getText().toString();

        // Validaciones previas
        if (identifier.isEmpty() || newPass.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPass.equals(confirm)) {
            etConfirmNewPassword.setError("Las contraseñas no coinciden");
            return;
        }

        if (newPass.length() < 6) {
            etNewPassword.setError("Mínimo 6 caracteres");
            return;
        }

        realizarPeticionCambio(identifier, newPass);
    }

    // Eliminamos oldPassword de los argumentos
    public void realizarPeticionCambio(String identifier, String newPassword) {
        executeCall(new CallInterface<Usuario>() {
            @Override
            public Usuario doInBackground() throws Exception {
                Map<String, String> params = new HashMap<>();
                params.put("identifier", identifier);
                params.put("newPassword", newPassword);

                return Connector.getConector().putWithParams(Usuario.class, params, "api/usuarios/password");
            }

            @Override
            public void doInUI(Usuario data) {
                if (data != null) {
                    Toast.makeText(CambiarPassword.this, "Nueva contraseña establecida", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void doInError(Context context, Exception e) {
                Toast.makeText(context, "Error: El usuario o email no existe.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}