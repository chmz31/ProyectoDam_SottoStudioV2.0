package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SeleccionInstrumento extends AppCompatActivity {

    protected Button boton1, boton2, boton3;

    protected Intent pasarPantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccion_instrumento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        boton1 = findViewById(R.id.boton1_instr);
        boton2 = findViewById(R.id.boton2_instr);
        boton3 = findViewById(R.id.boton3_instr);

        boton1.setOnClickListener(v -> {
            pasarPantalla = new Intent(SeleccionInstrumento.this, RecEstudio.class);
            startActivity(pasarPantalla);
        });
        boton2.setOnClickListener(v -> {
            pasarPantalla = new Intent(SeleccionInstrumento.this, RecEstudio.class);
            startActivity(pasarPantalla);
        });
        boton3.setOnClickListener(v -> {
            pasarPantalla = new Intent(SeleccionInstrumento.this, PerfilAlumno.class);
            startActivity(pasarPantalla);
        });

    }
}