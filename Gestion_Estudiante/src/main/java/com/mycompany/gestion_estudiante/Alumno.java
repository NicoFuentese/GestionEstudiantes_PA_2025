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
    private HashMap<Asignatura, ArrayList<Double>> notas = new HashMap<>();
    private String rut;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String email;
    private String nombreNivel;
    private double promedioGeneral;
    private boolean estadoAcademico; //true o false si esta activo
    
    //contructores
    
    public Alumno () {}
    
    public Alumno(String rut,
            String nombre1,
            String nombre2,
            String apellido1,
            String apellido2,
            String telefono,
            String email,
            String nombreNivel,
            boolean estadoAcademico){
        this.rut = rut;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.email = email;
        this.estadoAcademico = estadoAcademico;
        this.nombreNivel = nombreNivel;
        this.promedioGeneral = 0;
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
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
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
    
    public String getNombreNivel()
    {
        return nombreNivel;
    }
    
    public void setNombreNivel(String nn)
    {
        nombreNivel = nn;
    }
    
    public boolean agregarAsignaturas(ArrayList<Asignatura> aamm){
        if(aamm.isEmpty() || aamm.isEmpty()) return false;
        
        for(Asignatura a : aamm)
        {
            /*
            ArrayList <Double> nn = new ArrayList<>();
            notas.put(a, nn);
            */
            notas.computeIfAbsent(a, k -> new ArrayList<>());
        }
        return true;
    }
    
    public void setPromedioGeneral(double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }
    
    public HashMap<Asignatura, ArrayList<Double>> getNotas() {
        return notas;
    }
    public void setNotas(HashMap<Asignatura, ArrayList<Double>> notas) {
        this.notas = (notas == null) ? new HashMap<>() : notas;
    }
    
    public boolean agregarNota(Asignatura a, double n){
        //if(!notas.containsKey(a)) {return false;}
        if(a == null) {return false;}
        notas.computeIfAbsent(a, k -> new ArrayList<>());
        (notas.get(a)).add(n);
        this.calcularPromedioGeneral(); 
        return true;
    }
    
    public List<Double> getNotasDeAsignatura(Asignatura a) {
        ArrayList<Double> xs = notas.get(a);
        return (xs == null) ? java.util.Collections.emptyList() : xs;
    }

    //necesita set¿ No crack, si funciona
    public boolean calcularPromedioGeneral(){
        if(notas.isEmpty()) return false;
        int countA = 0;
        double sumA = 0.0;
        for(ArrayList <Double> n : notas.values())
        {
            if(n.isEmpty()) continue;
            int count = 0;
            Double sum = 0.0;
            for(Double num : n) {
                sum += num;
                count++;
            }
            sumA += (sum / count);
            countA++;
        }
        
        promedioGeneral = (sumA / countA);
        return true;
    }
    
    public double getPromedioGeneral()
    {
        calcularPromedioGeneral();
        if(promedioGeneral < 1.0) return 1.0;
        return promedioGeneral;
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
    
    //metodo main de prueba
    public static void main (String[] args) {
        Alumno a1 = new Alumno();
        System.out.println(a1);
    }
}
