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

    public void agregarAlumno() {
        
        String nombre1 = JOptionPane.showInputDialog("Ingrese primer Nombre:");
        if (nombre1 == null || nombre1.isEmpty()) return;
        String nombre2 = JOptionPane.showInputDialog("Ingrese Segundo Nombre:");
        if (nombre2 == null || nombre2.isEmpty()) return;
        String apellido1 = JOptionPane.showInputDialog("Ingrese Primer Apellido:");
        if (apellido1 == null || apellido1.isEmpty()) return;
        String apellido2 = JOptionPane.showInputDialog("Ingrese Segundo Apellido:");
        if (apellido2 == null || apellido2.isEmpty()) return;
        String telefono = JOptionPane.showInputDialog("Ingrese telefono:");
        if (telefono == null || telefono.isEmpty()) return;
        String email = JOptionPane.showInputDialog("Ingrese email:");
        if (email == null || email.isEmpty()) return;
        
        String rut = JOptionPane.showInputDialog("Ingrese rut:");
        if (rut == null || rut.isEmpty()) return;
        
        
        
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

        if (opcion == null || opcion.equals("Salir")) return;
        Alumno n = new Alumno(rut, nombre1, nombre2, apellido1, apellido2, telefono, email, opcion, true);
 
        colegioGestor.registrarAlumno(n, opcion); 
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
        if ((colegioGestor.getIndiceAlumnos()).isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
        return;
        }
        
        String rut = JOptionPane.showInputDialog("Ingrese el RUT del alumno:");
        if (rut == null || rut.isEmpty()) return;
        
        String a = JOptionPane.showInputDialog("Ingrese el nombre de la asignatura:");
        if (a == null || a.isEmpty()) return;
        
        String nota = JOptionPane.showInputDialog("Ingrese la nota:");
        if (a == null || a.isEmpty()) return;
        
        double nNota = Double.parseDouble(nota);
        if(nNota < 1.0 || nNota > 7.0) return;
        
        String nN = (colegioGestor.getIndiceAlumnos()).get(rut).getNombreNivel();
        colegioGestor.buscarNivel(nN).agregarNotaAlumno(rut, nN, nNota);
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
}
