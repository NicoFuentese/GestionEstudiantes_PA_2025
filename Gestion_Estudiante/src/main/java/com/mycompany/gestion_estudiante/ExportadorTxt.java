/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Nicolas
 */
public final class ExportadorTxt {
    private static final String DIR = "reportes";

    //exporta un Reporte al directorio por defecto "reportes/"
    public static Path export(Reporte r, Colegio c) throws IOException {
        return export(r, c, Paths.get(DIR));
    }

    //exporta un Reporte a un directorio
    public static Path export(Reporte r, Colegio c, Path dir) throws IOException {
        Files.createDirectories(dir);

        String base = r.nombreArchivoBase() + r.sufijoArchivo();
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        Path out = dir.resolve(base + "_" + stamp + ".txt");

        try (BufferedWriter bw = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {
            bw.write(r.generar(c));
            bw.newLine();
        }
        return out;
    }

    //Exporta contenido
    public static Path exportContenido(String base, String contenido) throws IOException {
        Files.createDirectories(Paths.get(DIR));
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        Path out = Paths.get(DIR, base + "_" + stamp + ".txt");
        try (BufferedWriter bw = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {
            bw.write(contenido == null ? "" : contenido);
            bw.newLine();
        }
        return out;
    }

    private ExportadorTxt() {}
}
