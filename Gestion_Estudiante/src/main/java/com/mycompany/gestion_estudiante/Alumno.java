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

public class Alumno {
    private List<Nivel> aprobados = new ArrayList<>();
    private String rut;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private int telefono;
    private String email;
    private boolean estadoAcademico; //true o false si esta activo
    
    //contructores
    
    public Alumno () {}
    
    public Alumno(String rut,
            String nombre1,
            String nombre2,
            String apellido1,
            String apellido2,
            int telefono,
            String email,
            boolean estadoAcademico){
        this.rut = rut;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.email = email;
        this.estadoAcademico = estadoAcademico;
    }
    
    //get y setter rut
    public String getRut (){
        return rut;
    }
    
    public void setRut(String rut) {
        this.rut = rut;
    }
    
    //get y setter nombre1
    public String getNombre1() {
        return nombre1;
    }
    
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }
    
    //get y setter nombre2
    public String getNombre2() {
        return nombre2;
    }
    
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    
    //get y setter apellido1
    public String getApellido1() {
        return apellido1;
    }
    
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    
    //get y setter apellido2
    public String getApellido2() {
        return apellido2;
    }
    
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    
    //get y setter telefono
    public int getTelefono() {
        return telefono;
    }
    
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    //get y setter email
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    //get y setter estadoAcademico
    public boolean getEstadoAcademico() {
        return estadoAcademico;
    }
    
    public void setEstadoAcademico(boolean estadoAcademico) {
        this.estadoAcademico = estadoAcademico;
    }
    
    @Override
    public String toString() {
        return "Alumno{" + 
                "rut = " + rut +
                ", nombre = " + nombre1+ " " + nombre2 + " " + apellido1+ " " + apellido2 +
                ", telefono = " + telefono +
                ", email = " + email +
                ", Estado = " + (estadoAcademico ? "Activo":"Inactivo");
    }
    
    //metodo main
    public static void main (String[] args) {
        Alumno a1 = new Alumno();
        System.out.println(a1);
    }
}
