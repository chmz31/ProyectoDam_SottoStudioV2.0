package com.example.sottostudio;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SottoStudio.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla de instrumentos
        String createInstrumentosTable = "CREATE TABLE instrumentos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL" +
                ");";

        // Tabla de usuarios (sin id_instrumento directo)
        String createUsuariosTable = "CREATE TABLE usuarios (" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL," +
                "fecha_nacimiento TEXT NOT NULL," +
                "rol TEXT NOT NULL CHECK (rol IN ('alumno', 'profesor'))" +
                ");";

        // Tabla de módulos
        String createModulosTable = "CREATE TABLE modulos (" +
                "id_modulo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL" +
                ");";

        // Tabla de ejercicios
        String createEjerciciosTable = "CREATE TABLE ejercicios (" +
                "id_ejercicio INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT," +
                "id_modulo INTEGER NOT NULL," +
                "FOREIGN KEY(id_modulo) REFERENCES modulos(id_modulo)" +
                ");";

        // Tabla de ejercicios realizados por alumnos
        String createEjerciciosAlumnoTable = "CREATE TABLE ejercicios_alumno (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario INTEGER NOT NULL," +
                "id_ejercicio INTEGER NOT NULL," +
                "estado TEXT," +
                "calificacion INTEGER," +
                "url_entrega TEXT," +
                "FOREIGN KEY(id_usuario) REFERENCES usuarios(id_usuario)," +
                "FOREIGN KEY(id_ejercicio) REFERENCES ejercicios(id_ejercicio)" +
                ");";

        // Tabla intermedia para usuarios e instrumentos (relación muchos a muchos)
        String createUsuariosInstrumentosTable = "CREATE TABLE usuarios_instrumentos (" +
                "id_usuario INTEGER NOT NULL," +
                "id_instrumento INTEGER NOT NULL," +
                "PRIMARY KEY (id_usuario, id_instrumento)," +
                "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)," +
                "FOREIGN KEY (id_instrumento) REFERENCES instrumentos(id)" +
                ");";

        // Tabla de registro de estudio
        String createRegistroEstudioTable = "CREATE TABLE registro_estudio (" +
                "id_estudio INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_usuario INTEGER NOT NULL," +
                "id_instrumento INTEGER NOT NULL," +
                "duracion_minutos INTEGER NOT NULL," +
                "fecha TEXT NOT NULL," +
                "FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)," +
                "FOREIGN KEY (id_instrumento) REFERENCES instrumentos(id)" +
                ");";

        // Crear todas las tablas
        db.execSQL(createInstrumentosTable);
        db.execSQL(createUsuariosTable);
        db.execSQL(createModulosTable);
        db.execSQL(createEjerciciosTable);
        db.execSQL(createEjerciciosAlumnoTable);
        db.execSQL(createUsuariosInstrumentosTable);
        db.execSQL(createRegistroEstudioTable);

        // Logs
        Log.i("DBHelper", "Tabla 'instrumentos' creada correctamente.");
        Log.i("DBHelper", "Tabla 'usuarios' creada correctamente.");
        Log.i("DBHelper", "Tabla 'modulos' creada correctamente.");
        Log.i("DBHelper", "Tabla 'ejercicios' creada correctamente.");
        Log.i("DBHelper", "Tabla 'ejercicios_alumno' creada correctamente.");
        Log.i("DBHelper", "Tabla 'usuarios_instrumentos' creada correctamente.");
        Log.i("DBHelper", "Tabla 'registro_estudio' creada correctamente.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertarUsuario(String nombre, String apellidos, String email, String password, String fechaNacimiento, String rol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellidos", apellidos);
        values.put("email", email);
        values.put("password", password);
        values.put("fecha_nacimiento", fechaNacimiento);
        values.put("rol", rol);

        long resultado = db.insert("usuarios", null, values);
        return resultado != -1;
    }

    public boolean emailExiste(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id_usuario FROM usuarios WHERE email = ?", new String[]{email});
        boolean existe = cursor.moveToFirst();
        cursor.close();
        return existe;
    }
    public boolean validarUsuario(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ? AND password = ?", new String[]{email, password});
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    public String obtenerRol(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT rol FROM usuarios WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            String rol = cursor.getString(0);
            cursor.close();
            return rol;
        }
        cursor.close();
        return "desconocido";
    }
}

