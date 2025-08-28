/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestion_estudiante;

/**
 *
 * @author Nicolas
 */
import java.io.*;
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
}