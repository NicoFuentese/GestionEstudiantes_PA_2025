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
    //colecciones
    private List<Alumno> alumnos = new ArrayList<>(); //posible mapa
    private ArrayList<Asignatura> malla = new ArrayList<>();
    
    private String nombre;
    private int anio;
    private String jornada;
    private String paralelo;
    private int cantidadMaximaAlumnos;
    private boolean activo;
    private List<Inscripcion> inscripciones = new ArrayList<>();

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

    public boolean getActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    public List<Alumno> getAlumnos() {return alumnos;}
    public List<Asignatura> getMalla() {return malla;}
    
    public List<Inscripcion> getInscripciones(){return inscripciones;}
    
    //Agregar alumnos
    public boolean agregarAlumno(Alumno a){
        if (alumnos.size() >= cantidadMaximaAlumnos) return false; // exepcion
        a.agregarAsignaturas(malla);
        return alumnos.add(a);
    }
    
    public boolean agregarNotaAlumno(String rut, String nA, double n){
       
        for(Asignatura i : malla) {
            if(nA.equals(i.getNombre())){
                if(buscarAlumno(rut) != null && (buscarAlumno(rut)).agregarNota(i, n)) return true;
                else return false;
            }
        }
        return false;
    }
    
    public boolean agregarAlumno(String rut,
            String nombre1,
            String nombre2,
            String apellido1,
            String apellido2,
            String telefono,
            String email,
            String nombreNivel,
            boolean estadoAcademico) {
        return agregarAlumno(new Alumno(rut, nombre1, nombre2, apellido1, apellido2, telefono, email, nombreNivel, estadoAcademico));
    }
    
    //Buscar alumno
    public Alumno buscarAlumno(String rut){
        for (Alumno a: alumnos) {
            if (a.getRut().equals(rut)) return a;
        }
        return null;
    }
    
    public List<Alumno> buscarAlumnoPorNombre (String fragmento) {
        List<Alumno> res = new ArrayList<>();
        
        for (Alumno a: alumnos) {
            String nombreCompleto = (a.getNombre1() + " " + a.getApellido1()).toLowerCase();
            if (nombreCompleto.contains(fragmento.toLowerCase())) res.add(a);
        }
        return res;
    }
    
    //agregar inscripciones
    public void agregarInscripcion (Inscripcion i) 
            throws InscripcionInvalidaException, InscripcionDuplicadaException{
        if (i == null) throw new InscripcionInvalidaException("Inscripcion null");
        if (i.getAlumno() == null) throw new InscripcionInvalidaException("Alumno null");
        if(i.getAsignatura() == null) throw new InscripcionInvalidaException("Asignatura null");
        if(i.getPeriodo() == null) throw new InscripcionInvalidaException("Periodo null");
        if (i.getNotaFinal() < 1.0 || i.getNotaFinal() > 7.0)
            throw new InscripcionInvalidaException("Nota fuera de rango (1 - 7");
        
        //duplicados
        String rut = i.getAlumno().getRut();
        String asigId = i.getAsignatura().getNombre();
        String periodo = i.getPeriodo();
        
        boolean dup = inscripciones.stream().anyMatch(x ->
                x.getAlumno().getRut().equals(rut) &&
                x.getAsignatura().getNombre().equals(asigId) &&
                x.getPeriodo().equals(periodo)
        );
        if (dup) throw new InscripcionDuplicadaException(rut, asigId, periodo);
        inscripciones.add(i);
    }
    
    public void agregarInscripcion (Alumno alumno, Asignatura asig, String periodo,
            String estado, double nota) 
            throws InscripcionInvalidaException, InscripcionDuplicadaException {
        Inscripcion i = new Inscripcion();
        i.setAlumno(alumno);
        i.setAsignatura(asig);
        i.setPeriodo(periodo);
        i.setEstado(estado);
        i.setNotaFinal(nota);
        agregarInscripcion(i);
    }
    
    public void agregarInscripcion (Alumno alumno, Asignatura asig, String periodo)
            throws InscripcionInvalidaException, InscripcionDuplicadaException {
        agregarInscripcion(alumno, asig, periodo, "INSCRITO", 0.0);
    }
    
    
    
    public boolean eliminarInscripcion(Inscripcion i){return inscripciones.remove(i);} 

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
