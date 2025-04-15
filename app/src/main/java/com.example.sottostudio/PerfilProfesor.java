package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PerfilProfesor extends AppCompatActivity {

    protected Button boton1, boton2, boton3, boton4;

    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_profesor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boton1 = findViewById(R.id.boton1_profesor);
        boton2 = findViewById(R.id.boton2_profesor);
        boton3 = findViewById(R.id.boton3_profesor);
        boton4 = findViewById(R.id.boton4_profesor);

        boton1.setOnClickListener(v -> {
            pasarPantalla = new Intent(PerfilProfesor.this, VerAlumno.class);
            startActivity(pasarPantalla);
        });

        boton2.setOnClickListener(v -> {
            pasarPantalla = new Intent(PerfilProfesor.this, VerRanking.class);
            startActivity(pasarPantalla);
        });

        boton3.setOnClickListener(v -> {
            pasarPantalla = new Intent(PerfilProfesor.this, GestionModulos.class);
            startActivity(pasarPantalla);
        });

        boton4.setOnClickListener(v -> {
            pasarPantalla = new Intent(PerfilProfesor.this, Herramientas.class);
            startActivity(pasarPantalla);
        });


    }
}