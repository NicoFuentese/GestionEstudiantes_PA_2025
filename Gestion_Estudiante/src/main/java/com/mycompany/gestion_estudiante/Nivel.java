/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

/**
 *
 * @author Nicolas
 */
import java.util.*;
public class Nivel {
    private List<Alumno> alumnos = new ArrayList(); //posible mapa
    private List<Asignatura> malla = new ArrayList<>();
    private String nombre;
    private int anio;
    private String jornada;
    private String paralelo;
    private int cantidadMaximaAlumnos;
    private boolean activo;

    // Constructores
    public Nivel() {}

    public Nivel(String nombre, int anio, String jornada, String paralelo,
                 int cantidadMaximaAlumnos, boolean activo) {
        this.nombre = nombre;
        this.anio = anio;
        this.jornada = jornada;
        this.paralelo = paralelo;
        this.cantidadMaximaAlumnos = cantidadMaximaAlumnos;
        this.activo = activo;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getJornada() { return jornada; }
    public void setJornada(String jornada) { this.jornada = jornada; }

    public String getParalelo() { return paralelo; }
    public void setParalelo(String paralelo) { this.paralelo = paralelo; }

    public int getCantidadMaximaAlumnos() { return cantidadMaximaAlumnos; }
    public void setCantidadMaximaAlumnos(int cantidadMaximaAlumnos) { this.cantidadMaximaAlumnos = cantidadMaximaAlumnos; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    public List<Alumno> getAlumnos() {return alumnos;}
    public List<Asignatura> getMalla() {return malla;}

    @Override
    public String toString() {
        return "Nivel{" +
                "nombre='" + nombre + '\'' +
                ", anio=" + anio +
                ", jornada='" + jornada + '\'' +
                ", paralelo='" + paralelo + '\'' +
                ", cantidadMaximaAlumnos=" + cantidadMaximaAlumnos +
                ", activo=" + (activo ? "Sí" : "No") +
                '}';
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Ejemplo de uso
        Nivel n1 = new Nivel();

        System.out.println(n1);
    }
}
