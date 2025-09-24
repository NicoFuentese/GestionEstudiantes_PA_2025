/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

/**
 *
 * @author sando
 */
import java.time.LocalDate;
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
        
        String nombre1 = JOptionPane.showInputDialog("Ingrese Primer Nombre:");
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
        
        Alumno n = new Alumno(rut, nombre1, nombre2, apellido1, apellido2, telefono, email, true);
        
        colegioGestor.registrarAlumno(n);
    }
    
    public void mostrarAlumnos() {
    if ((colegioGestor.getIndiceAlumnos()).isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
        return;
    }

    String[] columnas = {"RUT", "Nombres", "Apellidos", "email", "telefono"};
    DefaultTableModel model = new DefaultTableModel(columnas, 0);
    
    for (Alumno a : (colegioGestor.getIndiceAlumnos()).values()) {
        model.addRow(new Object[]{
                a.getRut(),
                a.getNombre1() + " " + a.getNombre2(),
                a.getApellido1() + " " + a.getApellido2(),
                a.getEmail(),
                a.getTelefono()
        });
    }

    JTable table = new JTable(model);
    
    JScrollPane scroll = new JScrollPane(table);
    
    JOptionPane.showMessageDialog(null, scroll, "Empleados Registrados", JOptionPane.INFORMATION_MESSAGE);
}

    public void modificarAlumno() {
    if ((colegioGestor.getIndiceAlumnos()).isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
        return;
    }

    String rut = JOptionPane.showInputDialog("Ingrese el RUT del empleado a modificar:");
    if (rut == null || rut.isEmpty()) return;

    Alumno e = (colegioGestor.getIndiceAlumnos()).get(rut);
    if (e == null) return;
    
    String nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre:", e.getNombre1());
    if (nombre != null && !nombre.isEmpty()) e.setNombre1(nombre);
    
}
    public void eliminarAlumno() {
        if ((colegioGestor.getIndiceAlumnos()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
            return;
        }

        String rut = JOptionPane.showInputDialog("Ingrese el RUT del empleado a eliminar:");
        if (rut == null || rut.isEmpty()) return;

        Alumno a = (colegioGestor.getIndiceAlumnos()).get(rut);
        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Está seguro que desea eliminar a " + a.getNombre1() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            (colegioGestor.getIndiceAlumnos()).remove(rut);
            JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente");
       }
    }
}
