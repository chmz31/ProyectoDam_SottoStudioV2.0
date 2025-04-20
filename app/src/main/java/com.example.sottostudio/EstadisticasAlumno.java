package com.example.sottostudio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EstadisticasAlumno extends AppCompatActivity {

    private EditText editHoy, editSemana, editMes, editTotal;
    private Button botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estadisticas_alumno);

        // Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets sb = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sb.left, sb.top, sb.right, sb.bottom);
            return insets;
        });

        // 1) Referencias UI
        editHoy     = findViewById(R.id.edit1_estadalumn);
        editSemana  = findViewById(R.id.edit2_estadalumn);
        editMes     = findViewById(R.id.edit3_estadalumn);
        editTotal   = findViewById(R.id.edit4_estadalumn);
        botonVolver = findViewById(R.id.boton1_estadalumn);

        // 2) Obtener ID_USUARIO de la sesión
        SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
        int idUsuario = prefs.getInt("ID_USUARIO", -1);
        if (idUsuario == -1) {
            Toast.makeText(this, "Usuario no logueado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 3) Consultar la base de datos
        DBHelper db = new DBHelper(this);
        int minutosHoy    = db.horasHoy(idUsuario);
        int minutosSemana = db.horasSemana(idUsuario);
        int minutosMes    = db.horasMes(idUsuario);
        int minutosTotal  = db.horasTotal(idUsuario);

        // 4) Pintar en pantalla
        editHoy.   setText(minutosHoy    + " min");
        editSemana.setText(minutosSemana + " min");
        editMes.   setText(minutosMes    + " min");
        editTotal. setText(minutosTotal  + " min");

        // 5) Botón volver a PerfilAlumno
        botonVolver.setOnClickListener(v -> {
            startActivity(new Intent(EstadisticasAlumno.this, PerfilAlumno.class));
            finish();
        });
    }
}