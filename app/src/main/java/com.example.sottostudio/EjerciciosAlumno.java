package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EjerciciosAlumno extends AppCompatActivity {

    protected Button boton1, boton2, boton3, boton4;
    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicios_alumno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boton1 = findViewById(R.id.boton1_ejalum);
        boton2 = findViewById(R.id.boton2_ejalum);
        boton3 = findViewById(R.id.boton3_ejalum);
        boton4 = findViewById(R.id.boton4_ejalum);

        /*Pendiente de implementar
        boton1.setOnClickListener(v -> {

            pasarPantalla = new Intent(EjerciciosAlumno.this, DictadoRit.class);
            startActivity(pasarPantalla);
        });
        boton2.setOnClickListener(v -> {
            pasarPantalla = new Intent(EjerciciosAlumno.this, DictadoArm.class);
            startActivity(pasarPantalla);
        });
        boton3.setOnClickListener(v -> {
            pasarPantalla = new Intent(EjerciciosAlumno.this, Intervalos.class);
            startActivity(pasarPantalla);
        });
        */
        boton4.setOnClickListener(v -> {
            pasarPantalla = new Intent(EjerciciosAlumno.this, PerfilAlumno.class);
            startActivity(pasarPantalla);
        });

    }
}