/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

/**
 *
 * @author Nicolas
 */
public class Asignatura{
    
    
    private String nombre;    
    private String descripcion;
    private String profesor = null;
    private int creditos;
    
//contructores
public Asignatura(){}
public Asignatura(String nombre, String descripcion,int creditos, String profesor){
    
    this.nombre = nombre.toLowerCase();
    this.descripcion = descripcion;
    this.creditos = creditos;
    this.profesor = profesor;
}

public Asignatura(String nombre, String descripcion, int creditos){
    
    this.nombre = nombre.toLowerCase();
    this.descripcion = descripcion;
    this.creditos = creditos;
}

public Asignatura(String nombre, String descripcion,String profesor , int creditos){
    
    this.nombre = nombre.toLowerCase();
    this.descripcion = descripcion;
    this.profesor = profesor;
    this.creditos = creditos;
}

public Asignatura(String nombre){
    this.nombre = nombre.toLowerCase();
}

public String getNombre(){return nombre; }
public void setNombre(String nombre){ this.nombre =  nombre.toLowerCase(); }

public String getDescripcion(){ return descripcion;}
public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

public String getProfesor(){return profesor;} 
public void setProfesor(String profesor){ this.profesor = profesor;}

public int getCreditos(){ return creditos;}
public void setCreditos(int creditos){ if (creditos >= 0) this.creditos = creditos;}


@Override

public String toString(){
    return "Asignatura = " + nombre + "\n" + "Descripción = " + descripcion + "\n" + "Profesor = " + profesor + "\n" + "Creditos = " + creditos + "\n";
}
}