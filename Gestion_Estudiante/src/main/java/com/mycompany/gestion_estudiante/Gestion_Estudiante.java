/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestion_estudiante;

/**
 *
 * @author Nicolas
 */
import java.io.*;
import java.util.*;

public class Gestion_Estudiante {
    
    private static BufferedReader lector = new BufferedReader( new InputStreamReader( System.in));
    
    public static void main(String[] args) throws IOException{
        
        Colegio colegio = Colegio.demo();
        int opcion;
        
        do{
            System.out.println("\n=== SISTEMA GESTIÓN ESTUDIANTES ===");
            System.out.println("1) Insertar Alumno en un Nivel (manual)");
            System.out.println("2) Listar Alumnos por Nivel");
            System.out.println("3) Listar Niveles"); 
            System.out.println("0) Salir"); 
            System.out.println("Opcion: "); 
            opcion = leerEntero();
            
            switch(opcion) {
                case 1:
                    insertarAlumnoEnNivel(colegio);
                    break;
                case 2: 
                    listarAlumnosPorNivel(colegio);
                    break;
                case 3: 
                    listarNiveles(colegio);
                    break;
                case 0: 
                    System.out.println("Hasta luego!");
                    return;
                default: 
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while(opcion != 0);
    }
    
    public static int leerEntero()throws IOException {
        while(true) {
            String entrada = lector.readLine();
            if (entrada.trim().matches("[0-9]+")) {
                return Integer.parseInt(entrada);
            } else {
                System.out.println("No es un numero, ingreselo nuevamente");
            }
        }
    }
    
    public static void insertarAlumnoEnNivel(Colegio col) throws IOException{
        listarNiveles(col);
        System.out.println("Seleccione indice de nivel: ");
        int idx = leerEntero();
        Nivel nivel = col.buscarNivel(idx);
        if (nivel == null) {
            System.out.println("Nivel no encontrado.");
            return;
        }
        
        System.out.println("Rut: ");
        String rut = lector.readLine().trim();
        System.out.println("Nombre 1: ");
        String nombre1 = lector.readLine().trim();
        System.out.println("Nombre 2: ");
        String nombre2 = lector.readLine().trim();
        System.out.println("apellido 1: ");
        String apellido1 = lector.readLine().trim();
        System.out.println("apellido 2: ");
        String apellido2 = lector.readLine().trim();
        System.out.println("Telefono: ");
        int telefono = Integer.parseInt(lector.readLine().trim());
        System.out.println("Email: ");
        String email = lector.readLine().trim();
        System.out.println("Estado Academico: ");
        boolean estadoAcademico = Boolean.parseBoolean(lector.readLine().trim());
        
        col.registrarAlumno(rut, nombre1, nombre2, apellido1, apellido2, telefono, email, estadoAcademico);
        boolean ok = nivel.agregarAlumno(col.getIndiceAlumnos().get(rut));
        
        if (ok) System.out.println("Alumno agregado al nivel");
        else System.out.println("No se pudo agregar alumno al nivel");
    }
    
    public static void listarNiveles(Colegio col) {
        System.out.println("Niveles del colegio:");
        for (int i = 0; i < col.getNiveles().size(); i++) {
            System.out.println(i + ")" + col.getNiveles().get(i));
        }
    }
    
    public static void listarAlumnosPorNivel (Colegio col) throws IOException{
        listarNiveles(col);
        System.out.println("Seleccion indice de nivel :");
        int idx = leerEntero();
        Nivel nivel = col.buscarNivel(idx);
        if (nivel == null) {
            System.out.println("Nivel no encontrado");
            return;
        }
        
        List<Alumno> lista = nivel.getAlumnos();
        if(lista.isEmpty()) System.out.println("Sin alumnos en este nivel");
        
        System.out.println("\nAlumnos en " + nivel + ":");
        for(Alumno a : lista) System.out.println(" - " + a);
    }
}