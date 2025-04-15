package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InicioSesion extends AppCompatActivity {

    protected TextView texto1, texto2;
    protected EditText usuario, contrasena;
    protected Button boton1, boton2;

    protected Intent pasarpantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1 = findViewById(R.id.text2_inicio);
        texto2 = findViewById(R.id.text3_inicio);

        usuario = findViewById(R.id.edit1_inicio);
        contrasena = findViewById(R.id.edit2_inicio);

        boton1 = findViewById(R.id.boton1_inicio);
        boton2 = findViewById(R.id.boton2_inicio);

        boton1.setOnClickListener(v -> {
            String usuarioTexto = usuario.getText().toString();
            String contrasenaTexto = contrasena.getText().toString();

            if (!esEmailValido(usuarioTexto)) {
                Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!esContrasenaValida(contrasenaTexto)) {
                Toast.makeText(this, "Contraseña no válida", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí puedes agregar la lógica para verificar las credenciales en la base de datos
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
        });

        boton2.setOnClickListener(v -> {
            pasarpantalla = new Intent(InicioSesion.this, Registro.class);
            startActivity(pasarpantalla);
        });
    }

    private boolean esEmailValido(String usuario) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(usuario).matches();
    }

    private boolean esContrasenaValida(String contrasena) {
        return contrasena.length() >= 8 &&
                contrasena.matches(".*[A-Z].*") &&
                contrasena.matches(".*[a-z].*") &&
                contrasena.matches(".*\\d.*") &&
                contrasena.matches(".*[@#$%^&+=!].*");
    }
}