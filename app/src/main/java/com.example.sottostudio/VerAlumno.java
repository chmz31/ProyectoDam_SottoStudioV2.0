package com.example.sottostudio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerAlumno extends AppCompatActivity {

    protected ListView list1;
    protected Button boton1, boton2;

    protected Intent pasarPantalla;

    private String alumnoSeleccionado = null; // Variable para almacenar el alumno seleccionado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_alumno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list1 = findViewById(R.id.list1_veralum);
        boton1 = findViewById(R.id.boton1_veralum);
        boton2 = findViewById(R.id.boton2_veralum);

        // Simulación de datos para la lista
        String[] alumnos = {"Alumno 1", "Alumno 2", "Alumno 3", "Alumno 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, alumnos);
        list1.setAdapter(adapter);
        list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Detectar selección de un elemento de la lista
        list1.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            alumnoSeleccionado = (String) parent.getItemAtPosition(position); // Guardar el alumno seleccionado
        });

        // Configurar el botón para redirigir solo si hay un alumno seleccionado
        boton1.setOnClickListener(v -> {
            if (alumnoSeleccionado != null) {
                pasarPantalla = new Intent(VerAlumno.this, EstadisticasAlumno.class);
                pasarPantalla.putExtra("nombreAlumno", alumnoSeleccionado); // Pasar el nombre del alumno
                startActivity(pasarPantalla);
            } else {
                Toast.makeText(this, "Por favor, selecciona un alumno de la lista", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón 2 para redirigir a PerfilProfesor
        boton2.setOnClickListener(v -> {
            pasarPantalla = new Intent(VerAlumno.this, PerfilProfesor.class);
            startActivity(pasarPantalla);
        });
    }
}