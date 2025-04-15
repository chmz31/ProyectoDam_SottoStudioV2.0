package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registro extends AppCompatActivity {

    protected EditText nombre, fechaNac, contrasena, confContr, email;
    protected Button boton1, boton2;

    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.edit1_registro);
        email = findViewById(R.id.edit2_registro);
        fechaNac = findViewById(R.id.edit3_registro);
        contrasena = findViewById(R.id.edit4_registro);
        confContr = findViewById(R.id.edit5_registro);

        boton1 = findViewById(R.id.boton1_registro);
        boton2 = findViewById(R.id.boton2_registro);

        boton1.setOnClickListener(v -> {
            pasarPantalla = new Intent(Registro.this, InicioSesion.class);
            startActivity(pasarPantalla);
        });

        boton2.setOnClickListener(v -> {
            String nombreTexto = nombre.getText().toString().trim();
            String emailTexto = email.getText().toString().trim();
            String fechaNacTexto = fechaNac.getText().toString().trim();
            String password = contrasena.getText().toString();
            String confirmPassword = confContr.getText().toString();

            // Validar que todos los campos estén llenos
            if (nombreTexto.isEmpty() || emailTexto.isEmpty() || fechaNacTexto.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar contraseña
            if (!esContrasenaValida(password)) {
                Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres, incluir mayúsculas, minúsculas, un número y un símbolo", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simular la asignación de rol (puedes reemplazar esto con una consulta a la base de datos)
            String rol = obtenerRolUsuario(emailTexto);

            // Redirigir a la actividad correspondiente
            if (rol.equals("profesor")) {
                Intent intent = new Intent(Registro.this, PerfilProfesor.class);
                startActivity(intent);
            } else if (rol.equals("alumno")) {
                Intent intent = new Intent(Registro.this, PerfilAlumno.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error: Rol no asignado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean esContrasenaValida(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[@#$%^&+=!].*");
    }

    private String obtenerRolUsuario(String email) {
        // Simulación: Asignar rol según el email (reemplazar con lógica de base de datos)
        if (email.equals("profesor@example.com")) {
            return "profesor";
        } else if (email.equals("alumno@example.com")) {
            return "alumno";
        }
        return "desconocido";
    }
}