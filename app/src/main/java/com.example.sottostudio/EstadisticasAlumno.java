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

public class EstadisticasAlumno extends AppCompatActivity {

    protected EditText edit1, edit2, edit3, edit4;

    protected Button boton1;

    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estadisticas_alumno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edit1 = findViewById(R.id.edit1_estadalumn);
        edit2 = findViewById(R.id.edit2_estadalumn);
        edit3= findViewById(R.id.edit3_estadalumn);
        edit4 = findViewById(R.id.edit4_estadalumn);
        boton1 = findViewById(R.id.boton1_estadalumn);


        // Establecer el texto predeterminado
        edit1.setText("Pendiente de conectar con la BdD");
        edit2.setText("Pendiente de conectar con la BdD");
        edit3.setText("Pendiente de conectar con la BdD");
        edit4.setText("Pendiente de conectar con la BdD");

        // Configurar el botón para regresar a PerfilAlumno
        boton1 = findViewById(R.id.boton1_estadalumn);
        boton1.setOnClickListener(v -> {
            Intent intent = new Intent(EstadisticasAlumno.this, PerfilAlumno.class);
            startActivity(intent);
        });
    }
}