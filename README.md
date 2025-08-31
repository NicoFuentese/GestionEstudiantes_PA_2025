Gestión de Estudiantes

Es una aplicación en Java para gestionar estudiantes en un colegio, permitiendo registrar alumnos en niveles, listar alumnos por nivel y listar niveles. Utiliza colecciones (ArrayList y HashMap) para administrar datos y BufferedReader para leer entradas del usuario desde la consola. Incluye un método demo para inicializar datos de ejemplo.
Requisitos

- Java: JDK 11 o superior
- IDE: NetBeans 12 o superior (compatible con IntelliJ IDEA o Eclipse)
- Git: Instalado para clonar el repositorio (descargar Git)
- GitHub Desktop: Para gestionar el repositorio (descargar GitHub Desktop)

Cómo obtener y configurar el proyecto
1. Copiar el enlace del repositorio

- Ve al repositorio en GitHub: https://github.com/NicoFuentese/GestionEstudiantes_PA_2025.git
- Haz clic en el botón verde Code en la página del repositorio.
- Copia la URL bajo HTTPS .

2. Configurar en GitHub Desktop

- Abre GitHub Desktop.
- Ve a File > Clone Repository.
- En la pestaña URL, pega el enlace copiado del repositorio.
- Selecciona un directorio local para guardar el proyecto (por ejemplo, C:\Proyectos\GestionEstudiantes).
- Haz clic en Clone para descargar el repositorio.
- Una vez clonado, el proyecto aparecerá en GitHub Desktop. Puedes abrir los archivos desde el directorio local.

3. Configurar y ejecutar en NetBeans

- Abre NetBeans.
- Ve a File > Open Project.
- Navega al directorio donde clonaste el repositorio (por ejemplo, C:\Proyectos\GestionEstudiantes) y selecciona la carpeta del proyecto.
- NetBeans detectará automáticamente el proyecto Java si tiene la estructura correcta (src/com/mycompany/gestion_estudiante). Si no, crea un nuevo proyecto:
- File > New Project > Java > Java Application.
- Selecciona el directorio del repositorio como ubicación del proyecto.
- Asegúrate de que los archivos .java estén en src/com/mycompany/gestion_estudiante.


Haz clic derecho en Gestion_Estudiante.java en el explorador de proyectos y selecciona Run File para ejecutar la aplicación.
Sigue el menú en consola para interactuar con la aplicación (insertar alumnos en niveles, listar alumnos por nivel, listar niveles).

Estructura del Proyecto

- src/com/mycompany/gestion_estudiante/Alumno.java: Modela un alumno con RUT, nombres, apellidos, teléfono, email, estado académico, y una lista de niveles aprobados.
- src/com/mycompany/gestion_estudiante/Asignatura.java: Modela una asignatura con nombre, descripción, créditos, y profesor (opcional).
- src/com/mycompany/gestion_estudiante/Nivel.java: Modela un nivel académico con nombre, año, jornada, paralelo, capacidad máxima, estado, y listas de alumnos y asignaturas (malla).
- src/com/mycompany/gestion_estudiante/Colegio.java: Modela un colegio con nombre, dirección, teléfono, estado privado, una lista de niveles, y un mapa de alumnos por RUT.
- src/com/mycompany/gestion_estudiante/Gestion_Estudiante.java: Clase principal con la interfaz de consola y el método main.
- .gitignore: Ignora archivos generados como .class, bin/, .idea/, *.iml, out/.

Descripción del Programa

La aplicación gestiona estudiantes en un colegio, organizados por niveles académicos (por ejemplo, "primer año"). Sus principales características son:

- Carga de datos: Inicializa un colegio con datos de ejemplo usando el método demo de Colegio, que crea dos niveles, dos asignaturas, y dos alumnos.
- Gestión de alumnos: Almacena alumnos en un HashMap<String, Alumno> (por RUT) en Colegio y permite registrarlos manualmente a través de la consola.
- Gestión de niveles: Almacena niveles en una ArrayList<Nivel> en Colegio y asignaturas en una ArrayList<Asignatura> en Nivel. Los alumnos se asignan a niveles mediante una ArrayList<Alumno> en Nivel.
- Interfaz de consola: Ofrece un menú para:
- Insertar un alumno en un nivel, solicitando RUT, nombres, apellidos, teléfono, email, y estado académico.
- Listar alumnos inscritos en un nivel seleccionado.
- Listar todos los niveles del colegio.


Uso de colecciones:

- ArrayList en Alumno (aprobados), Nivel (alumnos, malla), y Colegio (niveles).
- HashMap en Colegio (indiceAlumnos) para mapear RUTs a alumnos.


Uso de BufferedReader: Lee entradas del usuario desde la consola para el menú y los datos de alumnos, con validación para entradas numéricas.

Robustez: Incluye validaciones para entradas vacías (en Colegio.setNombre y setDireccion), capacidad máxima de alumnos en un nivel, y manejo de excepciones para entradas numéricas inválidas.

Ejemplo de Uso

Al ejecutar Gestion_Estudiante.java, se carga el colegio con datos de ejemplo (método demo de Colegio).
En el menú, selecciona una opción:
1: Inserta un alumno en un nivel. Por ejemplo:Seleccione indice de nivel: 0

- Rut: 33.333.333-3
- Nombre 1: Carla
- Nombre 2: Sofia
- Apellido 1: Lopez
- Apellido 2: Gomez
- Telefono: 912345678
- Email: carla.lopez@correo.cl
- Estado Academico: true
- Alumno agregado al nivel


2: Lista alumnos en un nivel. Por ejemplo:Seleccion indice de nivel: 0

Alumnos en Nivel{nombre ='primer año', anio = 2025, jornada = 'diurna', paralelo = 'A', cantidadMaximaAlumnos = 60, activo = Sí}:

- Alumno{rut = 11.111.111-1, nombre = Ana Paula Perez Roncaglia, telefono = 997205530, email = ana@correo.cl, Estado = Activo}
- Alumno{rut = 22.222.222-2, nombre = Luis Emilio Ramirez Roco, telefono = 922334455, email = luis@correo.cl, Estado = Activo}


3: Lista niveles. Por ejemplo: Niveles del colegio:

- 0) Nivel{nombre = 'primer año', anio = 2025, jornada = 'diurna', paralelo = 'A', cantidadMaximaAlumnos = 60, activo = Sí}
- 1) Nivel{nombre ='segundo año', anio = 2025, jornada ='diurna', paralelo ='B', cantidadMaximaAlumnos = 50, activo = Sí}


- 0: Sale del programa.


Contribuciones

Este proyecto fue desarrollado como parte de una actividad de Programación Avanzada, por parte de los Alumnos Nicolas Fuentes, Victor Guilarte y Eduardo Sandoval.
Si deseas contribuir, crea un fork del repositorio, realiza tus cambios y envía un pull request.
