package com.example.sottostudio;
import com.example.sottostudio.DBHelper;

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
            String nombreCompleto = nombre.getText().toString().trim();
            String emailTexto = email.getText().toString().trim();
            String fechaNacTexto = fechaNac.getText().toString().trim();
            String password = contrasena.getText().toString();
            String confirmPassword = confContr.getText().toString();

            if (nombreCompleto.isEmpty() || emailTexto.isEmpty() || fechaNacTexto.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!esContrasenaValida(password)) {
                Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres, incluir mayúsculas, minúsculas, un número y un símbolo", Toast.LENGTH_LONG).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            DBHelper dbHelper = new DBHelper(this);

            if (dbHelper.emailExiste(emailTexto)) {
                Toast.makeText(this, "Este correo ya está registrado", Toast.LENGTH_SHORT).show();
                return;
            }

            // Separar nombre y apellidos (simplificado)
            String[] partesNombre = nombreCompleto.split(" ", 2);
            String nombreSolo = partesNombre[0];
            String apellidos = partesNombre.length > 1 ? partesNombre[1] : "";

            boolean exito = dbHelper.insertarUsuario(nombreSolo, apellidos, emailTexto, password, fechaNacTexto, "alumno");

            if (exito) {
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Registro.this, InicioSesion.class));
                finish();
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
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

//    private String obtenerRolUsuario(String email) {
//         Simulación: Asignar rol según el email (reemplazar con lógica de base de datos)
//        if (email.equals("profesor@example.com")) {
//            return "profesor";
//        } else if (email.equals("alumno@example.com")) {
//            return "alumno";
//        }
//       return "desconocido";
//   }
}