package com.example.sottostudio;

import android.Manifest;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RecEstudio extends AppCompatActivity {

    private Chronometer chronometer;
    private Button botonGrabar, botonVolver, botonRegistrar;

    private boolean isRecording = false;
    private MediaRecorder recorder;
    private long pauseOffset = 0;
    private String outputFilePath;

    private int idInstrumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rec_estudio);

        // Insets para barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets sb = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sb.left, sb.top, sb.right, sb.bottom);
            return insets;
        });

        // 1) Recuperar ID_INSTRUMENTO del Intent
        idInstrumento = getIntent().getIntExtra("ID_INSTRUMENTO", -1);
        if (idInstrumento == -1) {
            Toast.makeText(this, "Instrumento no seleccionado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 2) Referencias UI
        chronometer    = findViewById(R.id.chronometer_rec);
        botonGrabar    = findViewById(R.id.boton1_rec);
        botonVolver    = findViewById(R.id.boton2_rec);
        botonRegistrar = findViewById(R.id.boton3_rec);

        // 3) Solicitar solo permiso de micrófono
        ActivityResultLauncher<String[]> requestPermissionLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.RequestMultiplePermissions(),
                        result -> {
                            boolean micOk = result.getOrDefault(Manifest.permission.RECORD_AUDIO, false);
                            if (!micOk) {
                                Toast.makeText(this, "Permiso de micrófono denegado", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
        // Lanzamos la petición únicamente del permiso RECORD_AUDIO
        requestPermissionLauncher.launch(new String[]{
                Manifest.permission.RECORD_AUDIO
        });

        // 4) Lógica de grabación / cronómetro
        botonGrabar.setOnClickListener(v -> {
            if (!isRecording) startRecording();
            else               stopRecording();
        });

        // 5) Volver al perfil alumno
        botonVolver.setOnClickListener(v -> finish());

        // 6) Registrar sesión en BD
        botonRegistrar.setOnClickListener(v -> {
            if (isRecording) {
                Toast.makeText(this, "Detén la grabación antes de registrar", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cálculo de duración en minutos
            long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            int durMin = (int)(elapsedMillis / 1000 / 60);

            // Recuperar ID_USUARIO de SharedPreferences
            SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
            int idUsuario = prefs.getInt("ID_USUARIO", -1);
            if (idUsuario == -1) {
                Toast.makeText(this, "Usuario no logueado", Toast.LENGTH_SHORT).show();
                return;
            }

            // Formatear fecha actual
            String fechaHoy = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    .format(new Date());

            // Insertar en BD
            DBHelper db = new DBHelper(this);
            boolean ok = db.insertarSesionEstudio(idUsuario, idInstrumento, durMin, fechaHoy);

            if (ok) {
                Toast.makeText(this, "Sesión guardada: " + durMin + " min", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error guardando sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startRecording() {
        try {
            outputFilePath = getExternalCacheDir().getAbsolutePath()
                    + "/rec_" + System.currentTimeMillis() + ".3gp";

            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.setOutputFile(outputFilePath);
            recorder.prepare();
            recorder.start();

            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();

            botonGrabar.setText("Parar");
            isRecording = true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "No se pudo iniciar grabación", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;

        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.stop();

        botonGrabar.setText("Grabar");
        isRecording = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
    }
}