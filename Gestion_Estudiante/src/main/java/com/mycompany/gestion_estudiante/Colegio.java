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
        this.niveles = inicializarNiveles();
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
    public ArrayList<Nivel> inicializarNiveles() {
        
        ArrayList<Nivel> niveles = new ArrayList<>();
        
        Nivel n1 = new Nivel("primero basico",2025,"diurna","A",60,true);
        n1.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n1);
        Nivel n2 = new Nivel("segundo basico",2025,"diurna","A",50,true);
        n2.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n2);
        Nivel n3 = new Nivel("tercero basico",2025,"diurna","A",50,true);
        n3.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n3);
        Nivel n4 = new Nivel("cuarto basico",2025,"diurna","A",50,true);
        n4.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n4);
        Nivel n5 = new Nivel("quinto basico",2025,"diurna","A",50,true);
        n5.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n5);
        Nivel n6 = new Nivel("sexto basico",2025,"diurna","A",50,true);
        n6.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n6);
        Nivel n7 = new Nivel("septimo basico",2025,"diurna","A",50,true);
        n7.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n7);
        Nivel n8 = new Nivel("octavo basico",2025,"diurna","A",50,true);
        n8.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n8);
        Nivel n9 = new Nivel("primero medio",2025,"diurna","A",50,true);
        n9.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n9);
        Nivel n10 = new Nivel("segundo medio",2025,"diurna","A",50,true);
        n10.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n10);
        Nivel n11 = new Nivel("tercero medio",2025,"diurna","A",50,true);
        n11.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n11);
        Nivel n12 = new Nivel("cuarto medio",2025,"diurna","A",50,true);
        n12.getMalla().add(new Asignatura("matematicas"));
        niveles.add(n12);
        
        
        //alumnos
        //col.registrarAlumno("11.111.111-1", "Ana", "Paula", "Perez","Roncaglia", 997205530, "ana@correo.cl", true);
        //col.registrarAlumno("22.222.222-2", "Luis", "Emilio", "Ramirez","Roco", 922334455, "luis@correo.cl", true);
        
        //asignar nivel
        //n1.agregarAlumno(col.getIndiceAlumnos().get("11.111.111-1"));
        //n1.agregarAlumno(col.getIndiceAlumnos().get("22.222.222-2"));
        
        return niveles;
    }
    
}
