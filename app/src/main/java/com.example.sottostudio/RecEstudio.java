package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecEstudio extends AppCompatActivity {

    protected EditText edit1;
    protected Button boton1, boton2, boton3;
    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rec_estudio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit1 = findViewById(R.id.edit1_rec);
        boton1 = findViewById(R.id.boton1_rec);
        boton2 = findViewById(R.id.boton2_rec);
        boton3 = findViewById(R.id.boton3_rec);

        /*Función por implementar
        boton1.setOnClickListener(v -> {

            pasarPantalla = new Intent(RecEstudio.this, EstadisticasAlumno.class);
            startActivity(pasarPantalla);
        });
        */

        boton2.setOnClickListener(v -> {
            pasarPantalla = new Intent(RecEstudio.this, PerfilAlumno.class);
            startActivity(pasarPantalla);
        });

        /* Función por implementar
        boton3.setOnClickListener(v -> {
            pasarPantalla = new Intent(RecEstudio.this, EjerciciosAlumno.class);
            startActivity(pasarPantalla);
        });
         */
    }
}