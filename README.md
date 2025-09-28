# Gestión de Estudiantes

Es una aplicación en Java para gestionar estudiantes en un colegio, permitiendo registrar alumnos en niveles, listar alumnos por nivel, listar niveles, gestionar asignaturas, inscripciones, y generar reportes. Utiliza colecciones (`ArrayList` y `HashMap`) para administrar datos, `BufferedReader` para entradas por consola, y persistencia en archivos CSV. Incluye un método `demo` para inicializar datos de ejemplo.

## Requisitos

- **Java**: JDK 11 o superior
- **IDE**: NetBeans 12 o superior (compatible con IntelliJ IDEA o Eclipse)
- **Git**: Instalado para clonar el repositorio ([descargar Git](https://git-scm.com/downloads))
- **GitHub Desktop**: Para gestionar el repositorio ([descargar GitHub Desktop](https://desktop.github.com/))

## Cómo obtener y configurar el proyecto

### Copiar el enlace del repositorio

1. Ve al repositorio en GitHub: [https://github.com/NicoFuentese/GestionEstudiantes_PA_2025.git](https://github.com/NicoFuentese/GestionEstudiantes_PA_2025.git)
2. Haz clic en el botón verde **Code**.
3. Copia la URL bajo **HTTPS**.

### Configurar en GitHub Desktop

1. Abre GitHub Desktop.
2. Ve a **File > Clone Repository**.
3. En la pestaña **URL**, pega el enlace copiado del repositorio.
4. Selecciona un directorio local para guardar el proyecto (por ejemplo, `C:\Proyectos\GestionEstudiantes`).
5. Haz clic en **Clone** para descargar el repositorio.
6. Una vez clonado, el proyecto aparecerá en GitHub Desktop. Puedes abrir los archivos desde el directorio local.

### Configurar y ejecutar en NetBeans

1. Abre NetBeans.
2. Ve a **File > Open Project**.
3. Navega al directorio donde clonaste el repositorio (por ejemplo, `C:\Proyectos\GestionEstudiantes`) y selecciona la carpeta del proyecto.
4. NetBeans detectará automáticamente el proyecto Java si tiene la estructura correcta (`src/com/mycompany/gestion_estudiante`). Si no, crea un nuevo proyecto:
   - **File > New Project > Java > Java Application**.
   - Selecciona el directorio del repositorio como ubicación del proyecto.
   - Asegúrate de que los archivos `.java` estén en `src/com/mycompany/gestion_estudiante`.
5. Haz clic derecho en `Gestion_Estudiante.java` en el explorador de proyectos y selecciona **Run File** para ejecutar la aplicación.
6. Sigue el menú en consola para interactuar con la aplicación (insertar alumnos, listar alumnos por nivel, listar niveles, etc.).

## Estructura del Proyecto

- `Alumno.java`: Modela un alumno con RUT, nombres, apellidos, teléfono, email, estado académico, nivel asignado, y mapa de notas por asignatura.
- `Asignatura.java`: Modela una asignatura con nombre (único), descripción, profesor, y créditos.
- `Nivel.java`: Modela un nivel académico con nombre, año, jornada, paralelo, capacidad máxima, estado activo, listas de alumnos, asignaturas, e inscripciones.
- `Colegio.java`: Modela un colegio con nombre, dirección, teléfono, estado privado, lista de niveles, y mapa de alumnos por RUT.
- `Inscripcion.java`: Modela la relación alumno-asignatura con estado (inscrito, aprobado, reprobado), nota final, y periodo.
- `DataStore.java`: Maneja persistencia en archivos CSV (`data/alumnos.csv`, `niveles.csv`, `asignaturas.csv`, `inscripciones.csv`).
- `ExportadorTxt.java`: Exporta reportes a archivos de texto.
- `Reporte.java`: Clase abstracta para reportes, con métodos `resumen` y `generar`.
- `ReporteAlumnoPorNivel.java`: Genera reportes de ocupación por nivel.
- `ReporteRendimientoNivel.java`: Genera reportes de rendimiento (promedio, mediana, tasa de aprobación).
- `GestionEstudiante.java`: Gestiona operaciones como agregar, modificar, eliminar alumnos y niveles, y buscar alumnos.
- `Gestion_Estudiante.java`: Clase principal con la interfaz de consola y el método `main`.
- `AlumnoDuplicadoException.java`: Excepción para alumnos duplicados.
- `EmailInvalidoException.java`: Excepción para emails inválidos.
- `InscripcionInvalidaException.java`: Excepción para inscripciones inválidas.
- `src/com/mycompany/gestion_estudiante/InscripcionDuplicadaException.java`: Excepción para inscripciones duplicadas.
- `src/com/mycompany/gestion_estudiante/TelefonoInvalidoException.java`: Excepción para teléfonos inválidos.
- `.gitignore`: Ignora archivos generados como `.class`, `bin/`, `.idea/`, `*.iml`, `out/`.

## Descripción del Programa

La aplicación gestiona estudiantes en un colegio, organizados por niveles académicos (por ejemplo, "primer año"). Sus principales características son:

- **Carga de datos**: Inicializa un colegio con datos de ejemplo usando el método `demo` de `Colegio`, que crea niveles, asignaturas, alumnos, e inscripciones.
- **Gestión de alumnos**: Almacena alumnos en un `HashMap<String, Alumno>` (por RUT) en `Colegio` y permite registrarlos manualmente a través de la consola o interfaz gráfica (Swing con `JOptionPane` y `JTable`).
- **Gestión de niveles**: Almacena niveles en un `ArrayList` en `Colegio`, asignaturas e inscripciones en `ArrayList`s en `Nivel`. Los alumnos se asignan a niveles mediante un `ArrayList` en `Nivel`.
- **Gestión de inscripciones**: Vincula alumnos con asignaturas, con estado (inscrito, aprobado, reprobado) y nota final.
- **Reportes**: Genera reportes de ocupación (`ReporteAlumnoPorNivel`) y rendimiento (`ReporteRendimientoNivel`), exportados a archivos de texto con `ExportadorTxt`.
- **Interfaz de usuario**:
  - **Consola**: Menú para insertar alumnos, listar alumnos por nivel, listar niveles, y salir.
  - **Gráfica**: Menú con `JOptionPane` y visualización de datos en `JTable` para listar alumnos y niveles.
- **Uso de colecciones**:
  - `ArrayList` en `Alumno` (niveles aprobados), `Nivel` (alumnos, asignaturas, inscripciones), y `Colegio` (niveles).
  - `HashMap` en `Colegio` (`indiceAlumnos`) para mapear RUTs a alumnos y en `Alumno` (notas por asignatura).
- **Persistencia**: Carga y guarda datos en archivos CSV usando `DataStore`.
- **Robustez**: Incluye validaciones para entradas vacías (en `Colegio.setNombre`, `setDireccion`), capacidad máxima de alumnos en un nivel, y manejo de excepciones (`AlumnoDuplicadoException`, `EmailInvalidoException`, etc.) para entradas inválidas.
- **Funcionalidad adicional**: Identifica alumnos en riesgo académico (promedio < 4.0) con `GestionEstudiante.mostrarAlumnosPeligro`.

### Diagrama UML

El diagrama UML del sistema, que detalla clases, atributos, métodos, y relaciones (1-* para `Colegio-Nivel`, `Nivel-Alumno/Asignatura`, `Nivel-Inscripcion`; 1-1 para `Inscripcion-Alumno/Asignatura`), está disponible en el repositorio como `diagrama_uml.mmd`. Puedes renderizarlo usando [Mermaid Live Editor](https://mermaid.live/) o un plugin de Mermaid en VSCode.

## Ejemplo de Uso

Al ejecutar `Gestion_Estudiante.java`, se carga el colegio con datos de ejemplo (método `demo` de `Colegio`). En el menú, selecciona una opción:

1. **Insertar un alumno en un nivel**:

   Seleccione indice de nivel: 0   Rut: 33.333.333-3   Nombre 1: Carla   Nombre 2: Sofia   Apellido 1: Lopez   Apellido 2: Gomez   Telefono: 912345678   Email: carla.lopez@correo.cl   Estado Academico: true   Alumno agregado al nivel

2. **Listar alumnos en un nivel**:

   Seleccion indice de nivel: 0   Alumnos en Nivel{nombre='primer año', anio=2025, jornada='diurna', paralelo='A', cantidadMaximaAlumnos=60, activo=Sí}:   Alumno{rut=11.111.111-1, nombre=Ana Paula Perez Roncaglia, telefono=997205530, email=ana@correo.cl, Estado=Activo}   Alumno{rut=22.222.222-2, nombre=Luis Emilio Ramirez Roco, telefono=922334455, email=luis@correo.cl, Estado=Activo}

3. **Listar niveles**:

   Niveles del colegio:   Nivel{nombre='primer año', anio=2025, jornada='diurna', paralelo='A', cantidadMaximaAlumnos=60, activo=Sí}   Nivel{nombre='segundo año', anio=2025, jornada='diurna', paralelo='B', cantidadMaximaAlumnos=50, activo=Sí}

4. **Otras funcionalidades** (vía interfaz gráfica o consola):
- Agregar, modificar, o eliminar alumnos y niveles.
- Buscar alumnos por RUT.
- Mostrar alumnos en riesgo académico (promedio < 4.0).
- Generar reportes en archivos de texto.

0. **Salir del programa**.

## Contribuciones

Este proyecto fue desarrollado como parte de una actividad de Programación Avanzada por Nicolás Fuentes, Victor Guilarte, y Eduardo Sandoval. Si deseas contribuir:

1. Crea un fork del repositorio.
2. Realiza tus cambios en una rama nueva.
3. Envía un pull request con una descripción clara de los cambios.

## Licencia

Este proyecto es de uso académico y no tiene una licencia específica definida. Contacta a los autores para más información.
