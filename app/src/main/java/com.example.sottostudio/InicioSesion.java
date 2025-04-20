package com.example.sottostudio;

import android.content.Intent;
import android.content.SharedPreferences;
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

    private TextView texto1, texto2;
    private EditText usuario, contrasena;
    private Button botonLogin, botonRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);

        // Ajuste de insets para barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets sb = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sb.left, sb.top, sb.right, sb.bottom);
            return insets;
        });

        // Referencias UI
        texto1        = findViewById(R.id.text2_inicio);
        texto2        = findViewById(R.id.text3_inicio);
        usuario       = findViewById(R.id.edit1_inicio);
        contrasena    = findViewById(R.id.edit2_inicio);
        botonLogin    = findViewById(R.id.boton1_inicio);
        botonRegistro = findViewById(R.id.boton2_inicio);

        botonLogin.setOnClickListener(v -> {
            String emailTexto = usuario.getText().toString().trim();
            String passTexto  = contrasena.getText().toString().trim();

            // Validaciones de formato
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailTexto).matches()) {
                Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show();
                return;
            }
            if (passTexto.length() < 8
                    || !passTexto.matches(".*[A-Z].*")
                    || !passTexto.matches(".*[a-z].*")
                    || !passTexto.matches(".*\\d.*")
                    || !passTexto.matches(".*[@#$%^&+=!].*")) {
                Toast.makeText(this, "Contraseña no válida", Toast.LENGTH_SHORT).show();
                return;
            }

            DBHelper dbHelper = new DBHelper(this);
            // 1) valida credenciales
            boolean valido = dbHelper.validarUsuario(emailTexto, passTexto);
            if (!valido) {
                Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                return;
            }

            // 2) obtener datos del usuario
            int idUsuario = dbHelper.obtenerIdUsuario(emailTexto);
            String rol    = dbHelper.obtenerRol(emailTexto);

            // 3) guardar sesión en SharedPreferences
            SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
            prefs.edit()
                    .putInt("ID_USUARIO", idUsuario)
                    .putString("ROL_USUARIO", rol)
                    .apply();

            // 4) navegar al perfil correspondiente
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            Intent intent;
            if ("profesor".equals(rol)) {
                intent = new Intent(this, PerfilProfesor.class);
            } else {
                intent = new Intent(this, PerfilAlumno.class);
            }
            startActivity(intent);
            finish();
        });

        botonRegistro.setOnClickListener(v -> {
            startActivity(new Intent(this, Registro.class));
        });
    }
}