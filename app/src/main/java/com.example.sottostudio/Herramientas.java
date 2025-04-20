package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Herramientas extends AppCompatActivity {

    protected Button boton1, boton2, boton3;

    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_herramientas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boton1 = findViewById(R.id.boton1_herramientas);
        boton2 = findViewById(R.id.boton2_herramientas);
        boton3 = findViewById(R.id.boton3_herramientas);


        /* Pendiente de implementar
        boton1.setOnClickListener(v -> {
            pasarPantalla = new Intent(Herramientas.this, Afinador.class);
            startActivity(pasarPantalla);
        });


        boton2.setOnClickListener(v -> {
            pasarPantalla = new Intent(Herramientas.this, Metronomo.class);
            startActivity(pasarPantalla);
        });

         */
        boton3.setOnClickListener(v -> {
            // Recuperar el rol del usuario (puedes pasarlo como extra desde la actividad anterior)
            String rolUsuario = obtenerRolUsuario();

            if ("profesor".equals(rolUsuario)) {
                pasarPantalla = new Intent(Herramientas.this, PerfilProfesor.class);
            } else if ("alumno".equals(rolUsuario)) {
                pasarPantalla = new Intent(Herramientas.this, PerfilAlumno.class);
            } else {
                // Manejar el caso de rol desconocido
                return;
            }

            startActivity(pasarPantalla);
        });
    }

    private String obtenerRolUsuario() {
        // Simulación: Recuperar el rol del usuario (reemplazar con lógica de base de datos o intent extra)
        // Por ejemplo, puedes usar getIntent().getStringExtra("rolUsuario");
        return "alumno"; // Cambiar según la lógica real
    }
}