/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestion_estudiante;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolas
 */
public class Gestion_Estudiante {
    public static void main(String [] args)
    {
        Colegio cole = new Colegio(" ", " ", " ", false);
        GestionEstudiante gestor = new GestionEstudiante(cole);
        
        while (true) {
            String[] opciones = {"Agregar alumno", "Mostrar lista alumno", "Alumnos con riesgo academico", "Modificar alumno", "agregar nota alumno", "Eliminar alumno", "Salir"};
            String opcion = (String) javax.swing.JOptionPane.showInputDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú Alumnos",
                    javax.swing.JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (opcion == null || opcion.equals("Salir")) break;

            switch (opcion) {
                case "Agregar alumno":
                    gestor.agregarAlumno();
                    break;
                case "Mostrar lista alumno":
                    gestor.mostrarAlumnos();
                    break;
                case "Alumnos con riesgo academico":
                    //gestor.mostrarEmpleados();
                    break;
                case "Modificar alumno":
                    //gestor.modificarEmpleado();
                    break;
                case "agregar nota alumno":
                    //gestor.eliminarEmpleado();
                    break;
                case "Eliminar alumno":
                    //gestor.eliminarEmpleado();
                    break;
                default:
                    break;
            }
        }
    }
}

