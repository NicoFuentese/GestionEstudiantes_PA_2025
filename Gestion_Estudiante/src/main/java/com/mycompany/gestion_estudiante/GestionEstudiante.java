/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

/**
 *
 * @author sando
 */
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionEstudiante {
    
    //private HashMap<String, Inscripcion> inscripciones;
    private Colegio colegioGestor;

    public GestionEstudiante(Colegio c) {
        //inscripciones = new HashMap<>();
        colegioGestor = c;
    }

    public void agregarAlumno() throws AlumnoDuplicadoException, TelefonoInvalidoException, EmailInvalidoException{
        
        String[] opciones = {"primero basico","segundo basico", 
            "tercero basico", "cuarto basico", 
            "quinto basico","sexto basico", 
            "septimo basico", "octavo basico", 
            "primero medio","segundo medio", 
            "tercero medio", "cuarto medio"};
        
        String opcion = (String) javax.swing.JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú Alumnos",
                    javax.swing.JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
        );

        if (opcion == null) return;
        
        
        String rut = JOptionPane.showInputDialog("Ingrese rut:");
        if (rut == null || rut.isEmpty()) return;
        
        String nombre1 = JOptionPane.showInputDialog("Ingrese primer Nombre:");
        if (nombre1 == null || nombre1.isEmpty()) return;
        String nombre2 = JOptionPane.showInputDialog("Ingrese Segundo Nombre:");
        if (nombre2 == null || nombre2.isEmpty()) return;
        String apellido1 = JOptionPane.showInputDialog("Ingrese Primer Apellido:");
        if (apellido1 == null || apellido1.isEmpty()) return;
        String apellido2 = JOptionPane.showInputDialog("Ingrese Segundo Apellido:");
        if (apellido2 == null || apellido2.isEmpty()) return;
        
        
        String telefono = JOptionPane.showInputDialog("Ingrese telefono (no agrege sufijo):");
        try{
            int numVacio = Integer.parseInt(telefono);
        }
        catch(NumberFormatException e)
        {
            throw new TelefonoInvalidoException();
        }
        if (telefono == null || telefono.isEmpty()) return;
        
        
        String email = JOptionPane.showInputDialog("Ingrese email:");
        if(!email.contains("@") && !email.contains(".")) throw new EmailInvalidoException();
        if (email == null || email.isEmpty()) return;
        
        Alumno n = new Alumno(rut, nombre1, nombre2, apellido1, apellido2, telefono, email, opcion, true);
 
        colegioGestor.registrarAlumno(n, opcion); 
        
        //inicializamos map notas del alumno
        Nivel nv = colegioGestor.buscarNivel(opcion);
        if (nv != null) {
            n.agregarAsignaturas(new ArrayList<>(nv.getMalla()));
        }
    }
    
    public void mostrarAlumnos() {
    if ((colegioGestor.getIndiceAlumnos()).isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
        return;
    }

    String[] columnas = {"RUT", "1er Nombre", "2do Nombre", 
        "1er Apellido", "2do Apellido", "email", "telefono", "promedio"};
    DefaultTableModel model = new DefaultTableModel(columnas, 0);
    
    for (Alumno a : (colegioGestor.getIndiceAlumnos()).values()) {
        model.addRow(new Object[]{
                a.getRut(),
                a.getNombre1(), a.getNombre2(),
                a.getApellido1(), a.getApellido2(),
                a.getEmail(),
                a.getTelefono(), a.getPromedioGeneral()
        });
    }

    JTable table = new JTable(model);
    
    JScrollPane scroll = new JScrollPane(table);
    
    JOptionPane.showMessageDialog(null, scroll, "Alumnos Registrados", JOptionPane.INFORMATION_MESSAGE);
}
    public void agregarNotaAlumno(){
        
        if (colegioGestor.getIndiceAlumnos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
            return;
        }

        // RUT
        String rut = JOptionPane.showInputDialog("Ingrese el RUT del alumno:");
        if (rut == null || (rut = rut.trim()).isEmpty()) return;

        Alumno alumno = colegioGestor.getIndiceAlumnos().get(rut);
        if (alumno == null) {
            JOptionPane.showMessageDialog(null, "No existe un alumno con RUT: " + rut);
            return;
        }

        // Asignatura (nombre)
        String nomAsig = JOptionPane.showInputDialog("Ingrese el nombre de la asignatura:");
        if (nomAsig == null || (nomAsig = nomAsig.trim()).isEmpty()) return;

        // Nota (soportando coma)
        String notaStr = JOptionPane.showInputDialog("Ingrese la nota (1.0–7.0):");
        if (notaStr == null || (notaStr = notaStr.trim()).isEmpty()) return;
        notaStr = notaStr.replace(',', '.');

        double nNota;
        try {
            nNota = Double.parseDouble(notaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato de nota inválido: " + notaStr);
            return;
        }
        if (nNota < 1.0 || nNota > 7.0) {
            JOptionPane.showMessageDialog(null, "La nota debe estar entre 1.0 y 7.0");
            return;
        }

        // Nivel
        String nombreNivel = alumno.getNombreNivel();
        Nivel nivel = (nombreNivel == null) ? null : colegioGestor.buscarNivel(nombreNivel);
        if (nivel == null) {
            nivel = buscarNivelQueContieneAsignatura(nomAsig);
            if (nivel == null) {
                JOptionPane.showMessageDialog(null, "No se encontró un nivel para la asignatura: " + nomAsig);
                return;
            }
        }
        
        //garantizar que el alumno pertenezca a la lista del nivel
        if (!nivel.getAlumnos().contains(alumno)) {
            nivel.agregarAlumno(alumno);
        }

        // Verifica que la asignatura exista en la malla del nivel
        boolean existeAsig = false;
        for (Asignatura as : nivel.getMalla()) {
            if (as.getNombre() != null && as.getNombre().equalsIgnoreCase(nomAsig)) {
                existeAsig = true;
                break;
            }
        }
        if (!existeAsig) {
            JOptionPane.showMessageDialog(null, "La asignatura '" + nomAsig + "' no está en la malla del nivel '" + nivel.getNombre() + "'.");
            return;
        }

        // Registrar la nota
        try {
            alumno.agregarAsignaturas(new java.util.ArrayList<>(nivel.getMalla()));
            boolean ok = nivel.agregarNotaAlumno(rut, nomAsig, nNota);
            if (!ok) {
                JOptionPane.showMessageDialog(null,
                    "No se pudo registrar la nota.\n" +
                    "Verifica RUT y que la asignatura exista en la malla del nivel.");
                return;
            }
            JOptionPane.showMessageDialog(null, "Nota registrada correctamente.");
        } catch (Throwable t) {
            t.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la nota: " + t.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }        
        }
        public void mostrarAlumnosPeligro() {
            if ((colegioGestor.getIndiceAlumnos()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
                return;
            }

            ArrayList<Alumno> listaPeligrantes = new ArrayList<>();
            for (Alumno a : (colegioGestor.getIndiceAlumnos()).values()){
                if(a.getPromedioGeneral() < 4.0 && a.calcularPromedioGeneral()) listaPeligrantes.add(a);
            }

            String[] columnas = {"RUT", "Primer Nombre", "Segundo Nombre", 
                "Primer Apellido", "Segundo Apellido", "email", "telefono"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);

            for (Alumno a : listaPeligrantes) {
                model.addRow(new Object[]{
                        a.getRut(),
                        a.getNombre1(), a.getNombre2(),
                        a.getApellido1(), a.getApellido2(),
                        a.getEmail(),
                        a.getTelefono()
                });
            }

            JTable table = new JTable(model);

            JScrollPane scroll = new JScrollPane(table);

            JOptionPane.showMessageDialog(null, scroll, "Alumnos Con Peligro Academico", JOptionPane.INFORMATION_MESSAGE);
    }
        
    private Nivel buscarNivelQueContieneAsignatura(String nombreAsig) {
        for (Nivel n : colegioGestor.getNiveles()) {
            for (Asignatura as : n.getMalla()) {
                if (as.getNombre() != null && as.getNombre().equalsIgnoreCase(nombreAsig)) {
                    return n;
                }
            }
        }
        return null;
    }

    public void modificarAlumno() {
    if ((colegioGestor.getIndiceAlumnos()).isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
        return;
    }

    String rut = JOptionPane.showInputDialog("Ingrese el RUT del alumno a modificar:");
    if (rut == null || rut.isEmpty()) return;

    Alumno e = (colegioGestor.getIndiceAlumnos()).get(rut);
    if (e == null) return;
    
    String nombre1 = JOptionPane.showInputDialog("Ingrese nuevo primer nombre:", e.getNombre1());
    if (nombre1 != null && !nombre1.isEmpty()) e.setNombre1(nombre1);
    String nombre2 = JOptionPane.showInputDialog("Ingrese nuevo segundo nombre:", e.getNombre2());
    if (nombre2 != null && !nombre2.isEmpty()) e.setNombre2(nombre2);
    String apellido1 = JOptionPane.showInputDialog("Ingrese nuevo primer apellido:", e.getApellido1());
    if (apellido1 != null && !apellido1.isEmpty()) e.setApellido1(apellido1);
    String apellido2 = JOptionPane.showInputDialog("Ingrese nuevo primer apellido:", e.getApellido2());
    if (apellido2 != null && !apellido2.isEmpty()) e.setApellido1(apellido2);
    String email = JOptionPane.showInputDialog("Ingrese nuevo primer apellido:", e.getEmail());
    if (email != null && !email.isEmpty()) e.setEmail(email);
    String telefono = JOptionPane.showInputDialog("Ingrese nuevo primer apellido:", e.getTelefono());
    if (telefono != null && !telefono.isEmpty()) e.setTelefono(telefono);
    
}
    public void eliminarAlumno() {
        if ((colegioGestor.getIndiceAlumnos()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
            return;
        }

        String rut = JOptionPane.showInputDialog("Ingrese el RUT del alumno a eliminar:");
        if (rut == null || rut.isEmpty()) return;

        if((colegioGestor.getIndiceAlumnos()).containsKey(rut)) return;
        Alumno a = (colegioGestor.getIndiceAlumnos()).get(rut);
        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Está seguro que desea eliminar a " + a.getNombre1() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            (colegioGestor.getIndiceAlumnos()).remove(rut);
            JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente");
       }
    }
    /*
    public void cerrarAsignatura() {
        if (colegioGestor.getIndiceAlumnos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
            return;
        }

        String rut = JOptionPane.showInputDialog("RUT del alumno:");
        if (rut == null || (rut = rut.trim()).isEmpty()) return;

        Alumno a = colegioGestor.getIndiceAlumnos().get(rut);
        if (a == null) { JOptionPane.showMessageDialog(null, "RUT no encontrado."); return; }

        String asig = JOptionPane.showInputDialog("Nombre de la asignatura (como en la malla):");
        if (asig == null || (asig = asig.trim()).isEmpty()) return;

        String periodo = JOptionPane.showInputDialog(null, "Periodo (ej: 2025-2):", "2025-2");
        if (periodo == null || (periodo = periodo.trim()).isEmpty()) return;

        // Encontrar el nivel del alumno (o cualquiera que contenga la asignatura)
        Nivel nivel = (a.getNombreNivel() == null) ? null : colegioGestor.buscarNivel(a.getNombreNivel());
        if (nivel == null) {
            // fallback: busca un nivel que tenga la asignatura
            for (Nivel n : colegioGestor.getNiveles()) {
                for (Asignatura x : n.getMalla()) {
                    if (x.getNombre() != null && x.getNombre().equalsIgnoreCase(asig)) {
                        nivel = n; break;
                    }
                }
                if (nivel != null) break;
            }
        }
        if (nivel == null) { JOptionPane.showMessageDialog(null, "No se encontró un nivel con esa asignatura."); return; }

        try {
            boolean ok = nivel.cerrarNotaFinal(rut, asig, periodo);
            JOptionPane.showMessageDialog(null, ok ? "Nota final calculada y guardada." : "No fue posible cerrar la asignatura.");
        } catch (InscripcionDuplicadaException | InscripcionInvalidaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Inscripción", JOptionPane.WARNING_MESSAGE);
        }
    }
    */
    public void cerrarAsignatura() {
        if (colegioGestor.getIndiceAlumnos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
            return;
        }

        String rut = JOptionPane.showInputDialog("RUT del alumno:");
        if (rut == null || (rut = rut.trim()).isEmpty()) return;

        Alumno a = colegioGestor.getIndiceAlumnos().get(rut);
        if (a == null) { JOptionPane.showMessageDialog(null, "RUT no encontrado."); return; }

        String asig = JOptionPane.showInputDialog("Nombre de la asignatura (como en la malla):");
        if (asig == null || (asig = asig.trim()).isEmpty()) return;

        String periodo = JOptionPane.showInputDialog(null, "Periodo (ej: 2025-2):", "2025-2");
        if (periodo == null || (periodo = periodo.trim()).isEmpty()) return;

        //Encontrar el nivel x nombre que guarda alumno
        Nivel nivel = (a.getNombreNivel() == null) ? null : colegioGestor.buscarNivel(a.getNombreNivel());

        //cualquier nivel que contenga la asignatura
        if (nivel == null) {
            for (Nivel n : colegioGestor.getNiveles()) {
                for (Asignatura x : n.getMalla()) {
                    if (x.getNombre() != null && x.getNombre().equalsIgnoreCase(asig)) { nivel = n; break; }
                }
                if (nivel != null) break;
            }
        }
        if (nivel == null) { JOptionPane.showMessageDialog(null, "No se encontró un nivel con esa asignatura."); return; }

        //Asegura que el alumno este en la lista del nivel
        if (!nivel.getAlumnos().contains(a)) {
            nivel.agregarAlumno(a);
        }

        //Verifica que existan parciales para la asignatura antes de cerrar
        Asignatura asRef = null;
        for (Asignatura x : nivel.getMalla()) {
            if (x.getNombre() != null && x.getNombre().equalsIgnoreCase(asig)) { asRef = x; break; }
        }
        if (asRef == null) { JOptionPane.showMessageDialog(null, "La asignatura no está en la malla del nivel."); return; }

        java.util.List<Double> parciales = a.getNotasDeAsignatura(asRef);
        if (parciales == null || parciales.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay notas parciales para esa asignatura. Agrega notas primero.");
            return;
        }

        try {
            boolean ok = nivel.cerrarNotaFinal(rut, asig, periodo);
            JOptionPane.showMessageDialog(null, ok ? "Nota final calculada y guardada." : "No fue posible cerrar la asignatura.");
        } catch (InscripcionDuplicadaException | InscripcionInvalidaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Inscripción", JOptionPane.WARNING_MESSAGE);
        }
    }
        
    public void generarReporteTxt() {
        String[] tipos = {"Alumnos por nivel", "Rendimiento por nivel"};
        String tipo = (String) javax.swing.JOptionPane.showInputDialog(
                null, "Elige reporte:", "Reportes",
                javax.swing.JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        if (tipo == null) return;

        try {
            Reporte rep;

            if ("Alumnos por nivel".equals(tipo)) {
                String niv = javax.swing.JOptionPane.showInputDialog("Filtrar por nivel (opcional):");
                rep = (niv == null || niv.isBlank())
                        ? new ReporteAlumnoPorNivel()
                        : new ReporteAlumnoPorNivel(niv.trim());
            } else { // "Rendimiento por nivel"
                String per = javax.swing.JOptionPane.showInputDialog("Periodo (opcional, ej: 2025-2):");
                String niv = javax.swing.JOptionPane.showInputDialog("Nivel (opcional):");
                rep = new ReporteRendimientoNivel(
                        (per == null || per.isBlank()) ? null : per.trim(),
                        (niv == null || niv.isBlank()) ? null : niv.trim()
                );
            }

            java.nio.file.Path out = ExportadorTxt.export(rep, colegioGestor);

            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    rep.resumen() + "\n\nGuardado en:\n" + out.toAbsolutePath(),
                    "Reporte TXT",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                    null, "Error al generar reporte: " + ex.getMessage(),
                    "Reportes", javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void agregarNivel() {
        
        String[] opciones = {"primero basico","segundo basico", 
            "tercero basico", "cuarto basico", 
            "quinto basico","sexto basico", 
            "septimo basico", "octavo basico", 
            "primero medio","segundo medio", 
            "tercero medio", "cuarto medio"};
        
        String nombre = (String) javax.swing.JOptionPane.showInputDialog(
                    null,
                    "Seleccione un nivel agregable:",
                    "Menú Alumnos",
                    javax.swing.JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
        );
        
        String letra = JOptionPane.showInputDialog("Ingrese letra paralelo nivel");
        if (letra == null || (letra = letra.trim()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Jornada no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        nombre = nombre + " " + letra;
        
        if (nombre == null || (nombre = nombre.trim()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre de nivel no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si el nivel ya existe
        if (colegioGestor.buscarNivel(nombre) != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un nivel con el nombre: " + nombre, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int anio = java.time.LocalDate.now().getYear();
        
        String[] opciones2 = {"diurna", "nocturna"};
        
        String jornada = (String) javax.swing.JOptionPane.showInputDialog(
                    null,
                    "Seleccione una jornada:",
                    "Menú Alumnos",
                    javax.swing.JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones2,
                    opciones2[0]
        );

        String capacidadStr = JOptionPane.showInputDialog("Ingrese la capacidad del nivel (ej: 50):");
        if (capacidadStr == null || (capacidadStr = capacidadStr.trim()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Capacidad no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int capacidad;
        try {
            capacidad = Integer.parseInt(capacidadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La capacidad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean activo = true;
        // Crear y registrar el nuevo nivel
        Nivel nuevoNivel = new Nivel(nombre, anio, jornada, letra, capacidad, activo);
        colegioGestor.agregarNivel(nuevoNivel);
        JOptionPane.showMessageDialog(null, "Nivel " + nombre + " agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para modificar un nivel
    public void modificarNivel() {
        if (colegioGestor.getNiveles().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay niveles registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nivel a modificar:");
        if (nombre == null || (nombre = nombre.trim()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre de nivel no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Nivel nivel = colegioGestor.buscarNivel(nombre);
        if (nivel == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un nivel con el nombre: " + nombre, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        String[] opciones2 = {"diurna", "nocturna"};
        
        String jornada = (String) javax.swing.JOptionPane.showInputDialog(
                    null,
                    "Seleccione una jornada:",
                    "Menú Alumnos",
                    javax.swing.JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones2,
                    opciones2[0]
        );

        String capacidadStr = JOptionPane.showInputDialog("Ingrese la nueva capacidad (ej: 50):", String.valueOf(nivel.getCantidadMaximaAlumnos()));
        if (capacidadStr != null && !(capacidadStr = capacidadStr.trim()).isEmpty()) {
            try {
                nivel.setCantidadMaximaAlumnos(Integer.parseInt(capacidadStr));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La capacidad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        int activoOption = JOptionPane.showConfirmDialog(null, "¿El nivel está activo?", "Estado del nivel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if (activoOption >= 0) {
            nivel.setActivo(activoOption == JOptionPane.YES_OPTION);
        }

        JOptionPane.showMessageDialog(null, "Nivel modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para eliminar un nivel
    public void eliminarNivel() {
        if (colegioGestor.getNiveles().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay niveles registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nivel a eliminar:");
        if (nombre == null || (nombre = nombre.trim()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre de nivel no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Nivel nivel = colegioGestor.buscarNivel(nombre);
        if (nivel == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un nivel con el nombre: " + nombre, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!nivel.getAlumnos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar el nivel porque tiene " + nivel.getAlumnos().size() + " alumnos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Está seguro que desea eliminar el nivel " + nivel.getNombre() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            colegioGestor.getNiveles().remove(nivel);
            JOptionPane.showMessageDialog(null, "Nivel eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void buscarAlumno() {
        if (colegioGestor.getIndiceAlumnos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rut = JOptionPane.showInputDialog("Ingrese el RUT del alumno a buscar:");
        if (rut == null || (rut = rut.trim()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "RUT no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Alumno alumno = colegioGestor.buscarAlumno(rut);
        if (alumno == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un alumno con el RUT: " + rut, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mostrar la información del alumno en una tabla
        String[] columnas = {"RUT", "1er Nombre", "2do Nombre", "1er Apellido", "2do Apellido", "Email", "Teléfono", "Nivel", "Promedio", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        model.addRow(new Object[]{
                alumno.getRut(),
                alumno.getNombre1(),
                alumno.getNombre2(),
                alumno.getApellido1(),
                alumno.getApellido2(),
                alumno.getEmail(),
                alumno.getTelefono(),
                alumno.getNombreNivel(),
                String.format("%.2f", alumno.getPromedioGeneral()),
                alumno.getEstadoAcademico() ? "Activo" : "Inactivo"
        });

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scroll, "Información del Alumno", JOptionPane.INFORMATION_MESSAGE);
    }
} 
