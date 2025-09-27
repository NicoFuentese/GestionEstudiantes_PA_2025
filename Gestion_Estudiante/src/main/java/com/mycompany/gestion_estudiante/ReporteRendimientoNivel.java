/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;
import java.util.*;

/**
 *
 * @author Nicolas
 */
public class ReporteRendimientoNivel extends Reporte {
    
    public ReporteRendimientoNivel(){super();}
    public ReporteRendimientoNivel(String periodo, String nombreNivel){
        super(periodo, nombreNivel);
    }
    
    public String resumen(){
        return "Rendimiento por nivel: promedio, mediana y tasa aprobacion";
    }
    
    protected String nombreArchivoBase(){
        return "rendimiento_nivel";
    }
    
    public String generar(Colegio c) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(resumen()).append("\n");
        if (periodo != null && !periodo.isBlank()) sb.append("# Periodo: ").append(periodo).append("\n");
        if (nombreNivel != null && !nombreNivel.isBlank()) sb.append("# Nivel: ").append(nombreNivel).append("\n");
        
        sb.append("nivel;periodo;inscripciones;promedio;mediana;tasa_aprobacion_%\n");
        int totIns = 0; List<Double> todas = new ArrayList<>();
        int aprob = 0, nivelesContados = 0;
        
        for (Nivel n : c.getNiveles()) {
            if (nombreNivel != null && !nombreNivel.isBlank()
                    && (n.getNombre()==null || !n.getNombre().equalsIgnoreCase(nombreNivel))) continue;

            List<Double> notas = new ArrayList<>();
            int insN = 0, aprobN = 0;

            for (Inscripcion i : n.getInscripciones()) {
                if (i == null) continue;
                if (periodo != null && !periodo.isBlank()) {
                    if (i.getPeriodo()==null || !i.getPeriodo().equalsIgnoreCase(periodo)) continue;
                }
                double v = i.getNotaFinal();
                if (v >= 1.0 && v <= 7.0) {
                    notas.add(v); todas.add(v); insN++;
                    if ("APROBADO".equalsIgnoreCase(nn(i.getEstado()))) aprobN++;
                }
            }

            if (insN == 0) continue;

            double prom = promedio(notas);
            double med = mediana(notas);
            double tasa = 100.0 * aprobN / insN;

            sb.append(nn(n.getNombre())).append(";")
              .append(nn(periodo)).append(";")
              .append(insN).append(";")
              .append(String.format(java.util.Locale.US,"%.2f", prom)).append(";")
              .append(String.format(java.util.Locale.US,"%.2f", med)).append(";")
              .append(String.format(java.util.Locale.US,"%.2f", tasa))
              .append("\n");

            nivelesContados++; totIns += insN; aprob += aprobN;
        }
        
        if (totIns > 0) {
            double promG = promedio(todas);
            double medG  = mediana(todas);
            double tasaG = 100.0 * aprob / totIns;
            sb.append("# resumen_global: niveles=").append(nivelesContados)
              .append(" inscripciones=").append(totIns)
              .append(" promedio=").append(String.format(java.util.Locale.US,"%.2f", promG))
              .append(" mediana=").append(String.format(java.util.Locale.US,"%.2f", medG))
              .append(" tasa_aprobacion_%=").append(String.format(java.util.Locale.US,"%.2f", tasaG))
              .append("\n");
        } else {
            sb.append("# sin datos para los filtros indicados\n");
        }

        return sb.toString();
    }
    
    private static double promedio(List<Double> xs){
        double s = 0.0;
        for (double v: xs){
            s += v;
        }
        return xs.isEmpty() ? 0.0 : s / xs.size();
    }
    
    private static double mediana(List<Double> xs) {
        if (xs.isEmpty()) return 0.0;
        List<Double> c = new ArrayList<>(xs);
        Collections.sort(c);
        int n = c.size();
        return (n % 2 == 1) ? c.get(n/2) : (c.get(n/2-1) + c.get(n/2))/2.0;
    }
    
}
