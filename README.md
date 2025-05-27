SottoStudio V2.0

Aplicación Android desarrollada como proyecto final de ciclo DAM (Desarrollo de Aplicaciones Multiplataforma). SottoStudio está pensada para ayudar a estudiantes de música a registrar sus progresos, gestionar sus estudios y realizar ejercicios, tanto desde el rol de alumno como desde el rol de profesor.

🎓 Características principales:

🔑 Autenticación de usuario

Registro de usuarios con validación de contraseña segura.

Inicio de sesión con validación de credenciales.

Almacenamiento de sesión mediante SharedPreferences.

👨‍🏫 Roles diferenciados

Alumno: Accede a su perfil, puede grabar sesiones de estudio, visualizar estadísticas, cambiar de instrumento y hacer ejercicios.

Profesor: (En desarrollo) Gestor de ejercicios, calificaciones, y seguimiento de alumnos.

🎧 Grabación de estudio

Cronómetro con control de grabación de audio.

Registro de sesiones por fecha, duración e instrumento asociado.

Base de datos SQLite local.

📊 Estadísticas de progreso

Horas de estudio hoy, semana, mes y total acumulado.

Filtros automáticos por fecha desde la base de datos.

📘 Estructura del proyecto

Arquitectura basada en actividades.

Navegación mediante Intent entre pantallas.

Layouts adaptados a temática oscura.

📂 Base de datos (SQLite)

El fichero de creación de base de datos está definido mediante el archivo DBHelper.java. Entre sus tablas destacan:

usuarios

instrumentos

registro_estudio

modulos, ejercicios, ejercicios_alumno

usuarios_instrumentos


📦 Instalación y ejecución

Clona el repositorio:

git clone https://github.com/chmz31/ProyectoDam_SottoStudioV2.0.git

Abre el proyecto en Android Studio.

Ejecuta el emulador o conecta un dispositivo físico.

Compila y lanza la aplicación.

✨ Licencia

Este proyecto es de código abierto con fines educativos. Puedes usarlo y modificarlo libremente.
