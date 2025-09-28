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
    private ArrayList<Nivel> niveles;
    private HashMap<String, Alumno> indiceAlumnos;
    private boolean privada;
    
    //constructor
    public Colegio(String nombre, String direccion, String telefono, boolean privada)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.privada = privada;
        this.niveles = new ArrayList<>();
        this.indiceAlumnos = new HashMap<>();
        
    }
    
    //get y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()) return;
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    
    }
    public void setDireccion(String direccion) {
        if(direccion == null || direccion.trim().isEmpty()) return;
        this.direccion = direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getPrivada() {
        return privada;
    }
    
    public void setPrivada(boolean privada) {
        this.privada = privada;
    }
    
    public List<Nivel> getNiveles() {
        return niveles;
    }
    
    public HashMap<String, Alumno> getIndiceAlumnos() {
        return indiceAlumnos;
    }
    
    //metodos alumnos
    public boolean registrarAlumno(Alumno aa, String nivel) throws AlumnoDuplicadoException {
        if(aa == null)return false;
        if(indiceAlumnos.containsKey(aa.getRut())) {throw new AlumnoDuplicadoException();}
        indiceAlumnos.put(aa.getRut(), aa); 
        
        for(Nivel e : niveles){
            if(e.getNombre().equals(nivel)){
                e.agregarAlumno(aa);
                aa.agregarAsignaturas(new ArrayList<>(e.getMalla()));
            }
        }
        return true;
    }
     
    public boolean registrarAlumno (String rut, String nombre1, 
            String nombre2, String apellido1, String apellido2, 
            String telefono, String email, boolean estadoAcademico,
            String nivel)  throws AlumnoDuplicadoException {
        return registrarAlumno(new Alumno(rut, nombre1, nombre2, apellido1, apellido2, telefono, email, nivel, estadoAcademico), nivel);
    }
    
    public Alumno buscarAlumno(String rutAlumno)
    {
        if(!(indiceAlumnos.containsKey(rutAlumno))) return null;
        return indiceAlumnos.get(rutAlumno);
    }
    
    //metodos niveles
    public boolean agregarNivel (Nivel n) {
        return niveles.add(n);
    }
    
    public Nivel buscarNivel(String a) {
        for(Nivel n : niveles) if(n.getNombre().equals(a)) return n;
        return null;
    }
    //Datos iniciales
    
    
}
