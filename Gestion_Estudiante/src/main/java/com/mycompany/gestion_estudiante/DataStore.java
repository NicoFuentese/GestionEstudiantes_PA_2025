/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
/**
 *
 * @author Nicolas
 *  Persistencia batch muy simple en CSV (carpeta "data" en la raíz del proyecto).
 * Archivos:
 *   - data/alumnos.csv        → rut;nombre
 *   - data/niveles.csv        → nivelKey;nombre;anio;jornada;paralelo;capacidad;activo
 *   - data/asignaturas.csv    → asigKey;nombre;descripcion;profesor;creditos;nivelKey
 *   - data/inscripciones.csv  → nivelKey;rut;asigKey;estado;nota;periodo
 */
public class DataStore {
    private DataStore() {}
    
    private static final Path BASE = Paths.get("data");
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String SEP = ";";
    
    private static final Map<String, Nivel> IDX_NIVEL = new HashMap<>();
    private static final Map<String, Asignatura> IDX_ASIG = new HashMap<>();
    private static final Map<String, Alumno> IDX_ALUMNO = new HashMap<>();
    
    public static Colegio cargarTodo() {
        try { Files.createDirectories(BASE); } catch (IOException ignored) {}
        IDX_NIVEL.clear(); IDX_ASIG.clear(); IDX_ALUMNO.clear();
        
        Colegio c = new Colegio("Colegio Sin Nombre", "-", "-", true);
        
        cargarNiveles(c);
        cargarAlumnos(c);
        cargarAsignaturas(c);
        cargarInscripciones(c);

        System.out.println("[DataStore] Cargado desde: " + BASE.toAbsolutePath());
        return c;
    }
    
    public static void guardarTodo(Colegio c) {
        try { Files.createDirectories(BASE); } catch (IOException ignored) {}
        
        guardarNiveles(c);
        guardarAlumnos(c);
        Map<Asignatura,String> kAsig = guardarAsignaturas(c);
        guardarInscripciones(c, kAsig);
        
        System.out.println("[DataStore] Guardado en: " + BASE.toAbsolutePath());
    }
    
    //Carga
    private static void cargarNiveles(Colegio c) {
        Path p = BASE.resolve("niveles.csv");
        if (!Files.exists(p)) return;
        
        c.getNiveles().clear();

        try (BufferedReader br = Files.newBufferedReader(p, UTF_8)) {
            String line; boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                if (first) { first = false; if (esHeader(line)) continue; }
                String[] t = line.split(SEP, -1);

                String key      = safe(t,0);
                String nombre   = safe(t,1);
                int anio        = parseInt(safe(t,2), 0);
                String jornada  = safe(t,3);
                String paralelo = safe(t,4);
                int capacidad   = parseInt(safe(t,5), 9999);
                boolean activo  = parseBool(safe(t,6), true);

                Nivel n = new Nivel(nombre, anio, jornada, paralelo, capacidad, activo);
                c.getNiveles().add(n);
                IDX_NIVEL.put(!key.isBlank() ? key : nivelKey(n), n);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void cargarAlumnos(Colegio c) {
        Path p = BASE.resolve("alumnos.csv");
        if (!Files.exists(p)) return;

        try (BufferedReader br = Files.newBufferedReader(p, UTF_8)) {
            String line; boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                if (first) { first = false; if (esHeader(line)) continue; }
                String[] t = line.split(SEP, -1);

                String rut  = safe(t,0);
                if (rut.isBlank()) continue;

                String nombre1   = t.length > 1 ? safe(t, 1) : "";
                String nombre2   = t.length > 2 ? safe(t, 2) : "";
                String apellido1 = t.length > 3 ? safe(t, 3) : "";
                String apellido2 = t.length > 4 ? safe(t, 4) : "";
                String telefono  = t.length > 5 ? safe(t, 5) : "";
                String email     = t.length > 6 ? safe(t, 6) : "";

                Alumno a = new Alumno();
                a.setRut(rut);
                a.setNombre1(nombre1);
                a.setNombre2(nombre2);
                a.setApellido1(apellido1);
                a.setApellido2(apellido2);
                a.setTelefono(telefono);
                a.setEmail(email);
                
                if (!c.getIndiceAlumnos().containsKey(rut)) {
                    c.getIndiceAlumnos().put(rut, a);
                } else {
                    System.err.println("[WARN] Alumno duplicado en CSV: " + rut);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void cargarAsignaturas(Colegio c) {
        Path p = BASE.resolve("asignaturas.csv");
        if (!Files.exists(p)) return;

        try (BufferedReader br = Files.newBufferedReader(p, UTF_8)) {
            String line; boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                if (first) { first = false; if (esHeader(line)) continue; }
                String[] t = line.split(SEP, -1);

                String ak        = safe(t,0);
                String nombre    = safe(t,1);
                String desc      = safe(t,2);
                String profesor  = safe(t,3);
                int creditos     = parseInt(safe(t,4), 0);
                String nivelKey  = safe(t,5);

                Asignatura as = new Asignatura();
                as.setNombre(nombre);
                as.setDescripcion(desc);
                as.setProfesor(profesor);
                as.setCreditos(creditos);

                IDX_ASIG.put(!ak.isBlank() ? ak : asigKey(as), as);

                Nivel n = IDX_NIVEL.get(nivelKey);
                if (n != null) {
                    n.getMalla().add(as);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void cargarInscripciones(Colegio c) {
        Path p = BASE.resolve("inscripciones.csv");
        if (!Files.exists(p)) return;

        try (BufferedReader br = Files.newBufferedReader(p, UTF_8)) {
            String line; boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                if (first) { first = false; if (esHeader(line)) continue; }
                String[] t = line.split(SEP, -1);

                String nivelKey = safe(t,0);
                String rut      = safe(t,1);
                String asigKey  = safe(t,2);
                String estado   = safe(t,3);
                double nota     = parseDouble(safe(t,4), 0.0);
                String periodo  = safe(t,5);

                Nivel n = IDX_NIVEL.get(nivelKey);
                if (n == null) continue;

                Alumno a = IDX_ALUMNO.get(rut);
                if (a == null) {
                    a = new Alumno();
                    a.setRut(rut);
                    IDX_ALUMNO.put(rut, a);
                }

                //asegura que el alumno esta en el nivel
                if (!n.getAlumnos().contains(a)) {
                    n.getAlumnos().add(a);
                }
                
                Asignatura as = IDX_ASIG.get(asigKey);
                if (as == null) continue;

                Inscripcion i = new Inscripcion();
                i.setAlumno(a);
                i.setAsignatura(as);
                i.setEstado(estado);
                i.setNotaFinal(nota);
                i.setPeriodo(periodo);
                
                try {
                    n.agregarInscripcion(i);
                } catch (Exception ex) {
                    System.err.println("[WARN] Inscripción omitida (" + rut + "," + asigKey + "," + periodo + "): " + ex.getMessage());
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    //guardado
    private static void guardarNiveles(Colegio c) {
        Path p = BASE.resolve("niveles.csv");
        try (BufferedWriter bw = Files.newBufferedWriter(p, UTF_8)) {
            bw.write("nivelKey;nombre;anio;jornada;paralelo;capacidad;activo\n");
            for (Nivel n : c.getNiveles()) {
                String key = nivelKey(n);
                bw.write(String.join(SEP,
                    key,
                    nn(n.getNombre()),
                    String.valueOf(n.getAnio()),
                    nn(n.getJornada()),
                    nn(n.getParalelo()),
                    String.valueOf(n.getCantidadMaximaAlumnos()),
                    String.valueOf(n.getActivo())
                ));
                bw.write("\n");
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void guardarAlumnos(Colegio c) {
        Path p = BASE.resolve("alumnos.csv");
        try (BufferedWriter bw = Files.newBufferedWriter(p, UTF_8)) {
            bw.write("rut;nombre1;nombre2;apellido1;apellido2;telefono;email\n");
            Set<String> vistos = new HashSet<>();
            
            for (Alumno a : c.getIndiceAlumnos().values()) {
                if (vistos.add(a.getRut())) {
                    bw.write(String.join(SEP,
                        nn(a.getRut()),
                        nn(a.getNombre1()),
                        nn(a.getNombre2()),
                        nn(a.getApellido1()),
                        nn(a.getApellido2()),
                        nn(a.getTelefono()),
                        nn(a.getEmail())
                    ));
                    bw.write("\n");
                }
            }
            
            //respaldo solo en Niveles
            for (Nivel n : c.getNiveles()) {
                for (Alumno a : n.getAlumnos()) {
                    if (vistos.add(a.getRut())) {
                        bw.write(String.join(SEP,
                            nn(a.getRut()),
                            nn(a.getNombre1()),
                            nn(a.getNombre2()),
                            nn(a.getApellido1()),
                            nn(a.getApellido2()),
                            nn(a.getTelefono()),
                            nn(a.getEmail())
                        ));
                        bw.write("\n");
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static Map<Asignatura,String> guardarAsignaturas(Colegio c) {
        Path p = BASE.resolve("asignaturas.csv");
        Map<Asignatura,String> map = new HashMap<>();

        try (BufferedWriter bw = Files.newBufferedWriter(p, UTF_8)) {
            bw.write("asigKey;nombre;descripcion;profesor;creditos;nivelKey\n");
            for (Nivel n : c.getNiveles()) {
                String nk = nivelKey(n);
                for (Asignatura as : n.getMalla()) {
                    String ak = asigKey(as);
                    map.put(as, ak);
                    bw.write(String.join(SEP,
                        ak,
                        nn(as.getNombre()),
                        nn(as.getDescripcion()),
                        nn(as.getProfesor()),
                        String.valueOf(as.getCreditos()),
                        nk
                    ));
                      bw.write("\n");
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return map;
    }

    private static void guardarInscripciones(Colegio c, Map<Asignatura,String> asigKeys) {
        Path p = BASE.resolve("inscripciones.csv");
        try (BufferedWriter bw = Files.newBufferedWriter(p, UTF_8)) {
            bw.write("nivelKey;rut;asigKey;estado;nota;periodo\n");
            for (Nivel n : c.getNiveles()) {
                String nk = nivelKey(n);
                for (Inscripcion i : n.getInscripciones()) {
                    String ak = asigKeys.getOrDefault(i.getAsignatura(), asigKey(i.getAsignatura()));
                    bw.write(String.join(SEP,
                        nk,
                        nn(i.getAlumno().getRut()),
                        ak,
                        nn(i.getEstado()),
                        String.valueOf(i.getNotaFinal()),
                        nn(i.getPeriodo())
                    ));
                    bw.write("\n");
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    //Ayudas
    private static boolean esHeader(String line) {
        String l = line.toLowerCase(Locale.ROOT);
        return l.contains("rut") || l.contains("nivelkey") || l.contains("asigkey");
    }

    private static String nn(String s) { return s == null ? "" : s; }
    private static String safe(String[] arr, int i) { return i < arr.length ? arr[i] : ""; }
    private static int parseInt(String s, int def) { try { return Integer.parseInt(s.trim()); } catch (Exception e) { return def; } }
    private static double parseDouble(String s, double def) { try { return Double.parseDouble(s.trim()); } catch (Exception e) { return def; } }
    private static boolean parseBool(String s, boolean def) {
        if (s == null) return def;
        String x = s.trim().toLowerCase(Locale.ROOT);
        if (x.equals("true") || x.equals("1") || x.equals("si") || x.equals("sí")) return true;
        if (x.equals("false") || x.equals("0") || x.equals("no")) return false;
        return def;
    }

    private static String slug(String s) {
        String x = s.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "-");
        return x.replaceAll("(^-|-$)", "");
    }

    private static String nivelKey(Nivel n) {
        return String.join("_",
            slug(nn(n.getNombre())),
            String.valueOf(n.getAnio()),
            slug(nn(n.getJornada())),
            slug(nn(n.getParalelo()))
        );
    }

    private static String asigKey(Asignatura a) {
        return slug(nn(a.getNombre()));
    }
}
