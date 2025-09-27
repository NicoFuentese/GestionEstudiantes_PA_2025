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
        System.out.println("Working Dir = " + new java.io.File("").getAbsolutePath());
        
        Colegio cole = DataStore.cargarTodo();
        
        Colegio finalCole = cole;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataStore.guardarTodo(finalCole);
            System.out.println("[DataStore] Datos guardados en carpeta 'data/'.");
        }));

        GestionEstudiante gestor = new GestionEstudiante(cole);
        
        while (true) {
            String[] opciones = {"Agregar alumno", "Mostrar lista alumno", "Alumnos con riesgo academico", "Modificar alumno", "agregar nota alumno", "Eliminar alumno", "Cerrar asignatura (promediar y guardar)","Salir"};
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
                    try
                    {
                        gestor.agregarAlumno();
                    }
                    catch(AlumnoDuplicadoException e){
                        JOptionPane.showMessageDialog(
                        null, 
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    catch(TelefonoInvalidoException e){
                        JOptionPane.showMessageDialog(
                        null, 
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    catch(EmailInvalidoException e){
                        JOptionPane.showMessageDialog(
                        null, 
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "Mostrar lista alumno":
                    gestor.mostrarAlumnos();
                    break;
                case "Alumnos con riesgo academico":
                    gestor.mostrarAlumnosPeligro();
                    break;
                case "Modificar alumno":
                    gestor.modificarAlumno();
                    break;
                case "agregar nota alumno":
                    gestor.agregarNotaAlumno();
                    break;
                case "Eliminar alumno":
                    gestor.eliminarAlumno();
                    break;
                case "Cerrar asignatura (promediar y guardar)":
                    try {
                        gestor.cerrarAsignatura();
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                                "Cerrar asignatura", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

