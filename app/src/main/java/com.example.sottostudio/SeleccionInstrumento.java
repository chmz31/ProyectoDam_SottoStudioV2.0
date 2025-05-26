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

    private Button boton1;  // Guitarra
    private Button boton2;  // Batería
    private Button boton3;  // Volver al perfil

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccion_instrumento);

        // Ajuste de insets (barras de sistema)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Enlaza los botones del layout
        boton1 = findViewById(R.id.boton1_instr);  // Instrumento 1 (e.g. Guitarra)
        boton2 = findViewById(R.id.boton2_instr);  // Instrumento 2 (e.g. Batería)
        boton3 = findViewById(R.id.boton3_instr);  // Volver

        // Al pulsar Instrumento 1 → RecEstudio con idInstrumento = 1
        boton1.setOnClickListener(v -> {
            Intent intent = new Intent(SeleccionInstrumento.this, RecEstudio.class);
            intent.putExtra("ID_INSTRUMENTO", 1);
            startActivity(intent);
        });

        // Al pulsar Instrumento 2 → RecEstudio con idInstrumento = 2
        boton2.setOnClickListener(v -> {
            Intent intent = new Intent(SeleccionInstrumento.this, RecEstudio.class);
            intent.putExtra("ID_INSTRUMENTO", 2);
            startActivity(intent);
        });

        // Al pulsar Volver → regresa a PerfilAlumno
        boton3.setOnClickListener(v -> {
            startActivity(new Intent(SeleccionInstrumento.this, PerfilAlumno.class));
            finish();
        });
    }
}