/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

/**
 *
 * @author Nicolas
 */
public abstract class Reporte {
    protected String periodo;
    protected String nombreNivel;
    
    public Reporte(){};
    public Reporte(String periodo, String nombreNivel){
        this.periodo = periodo;
        this.nombreNivel = nombreNivel;
    }
    
    public abstract String resumen();
    protected abstract String nombreArchivoBase();
    public abstract String generar(Colegio c);
    
    //ayudas
    protected static String nn(String s){
        return s == null ? "":s;
    }
    
    protected String sufijoArchivo(){
        String s = "";
        if (periodo != null && !periodo.isBlank())
            s += "_"+periodo.replaceAll("[^0-9A-Za-z_-]", "");
        if (nombreNivel != null && !nombreNivel.isBlank()){
            s += "_nivel-"+nombreNivel.replaceAll("\\s+","-");
        }
        return s.isEmpty()? "" : s;
    }
    
}
