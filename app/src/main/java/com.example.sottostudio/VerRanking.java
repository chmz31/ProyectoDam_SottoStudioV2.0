package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerRanking extends AppCompatActivity {

    protected ListView list1;

    protected Button boton1;
    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_ranking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list1 = findViewById(R.id.list1_rank);
        boton1 = findViewById(R.id.boton1_rank);

        boton1.setOnClickListener(v -> {
            pasarPantalla = new Intent(VerRanking.this, PerfilProfesor.class);
            startActivity(pasarPantalla);
        });

        //Falta por implementar que en el listview se muestren los alumnos y sus notas obteniendo datos de la BdD
    }
}