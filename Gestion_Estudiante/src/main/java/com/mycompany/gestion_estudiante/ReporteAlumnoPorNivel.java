/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

/**
 *
 * @author Nicolas
 */

public class ReporteAlumnoPorNivel extends Reporte {
    
    public ReporteAlumnoPorNivel() {super();}
    public ReporteAlumnoPorNivel(String nombreNivel) {
        super(null, nombreNivel);
    }
    
    public String resumen() {
        return "Alumnos por nivel (conteo y ocupacion)";
    }
    
    public String nombreArchivoBase() {
        return "Alumnos_por_nivel";
    }
    
    public String generar(Colegio c) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(resumen()).append("\n");
        if (nombreNivel != null && !nombreNivel.isBlank())
            sb.append("# Filtro nivel: ").append(nombreNivel).append("\n");
        sb.append("nivel;anio;jornada;paralelo;capacidad;alumnos;ocupacion_%\n");
        
        int totalNiveles = 0, totalAlumnos = 0, totalCapacidad = 0;
        for (Nivel n : c.getNiveles()) {
            if (nombreNivel != null && !nombreNivel.isBlank()
                && (n.getNombre()==null || !n.getNombre().equalsIgnoreCase(nombreNivel))) continue;
            
            int alumnos = n.getAlumnos().size();
            int cap = n.getCantidadMaximaAlumnos();
            double ocup = cap > 0 ? (100.0 * alumnos / cap) : 0.0;
            
            sb.append(nn(n.getNombre())).append(";")
              .append(n.getAnio()).append(";")
              .append(nn(n.getJornada())).append(";")
              .append(nn(n.getParalelo())).append(";")
              .append(cap).append(";")
              .append(alumnos).append(";")
              .append(String.format(java.util.Locale.US,"%.2f", ocup))
              .append("\n");

            totalNiveles++; totalAlumnos += alumnos; totalCapacidad += cap;
        }
        sb.append("# tot_niveles=").append(totalNiveles)
          .append(" tot_alumnos=").append(totalAlumnos)
          .append(" tot_capacidad=").append(totalCapacidad).append("\n");
        return sb.toString();
    }
}
