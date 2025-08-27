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

public class Colegio {
    private String nombre;
    private String direccion;
    private String telefono;
    private LinkedList<Nivel> grados;
    private HashMap<String, Alumno> indiceAlumnos;
    private boolean privada;
    //pruebaaaa commit
    
    public Colegio(String nombre, String direccion, String telefono, boolean privada)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.privada = privada;
        this.grados = new LinkedList<Nivel>();
    }
    public Colegio(String nombre, String direccion, String telefono){this(nombre, direccion, telefono, false);}
    public Colegio(String nombre, String direccion){this(nombre, direccion, " ");}
    public Colegio(String nombre){this(nombre, " ", " ");}
    public Colegio(){this(" ", " ", " ");}
    
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {
        if(nombre == " "){this.nombre = nombre;}
        else System.out.println("Tu no puedes cambiar el nombre del colegio"); //You cannot chargue school's name
    }

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {
        if(direccion == " "){this.direccion = direccion;}
        else System.out.println("Tu no puedes cambiar la dirección del colegio");
    }
    
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public boolean isPrivada() {return privada;}
    public void setPrivada(boolean privada) {this.privada = privada;}
    
    public boolean agregarAlumno(Alumno aa){
        if(aa == null)return false;
        indiceAlumnos.put(aa.getRut(), aa);
        
        return true;
    }
    
}
