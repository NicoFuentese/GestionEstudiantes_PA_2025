/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

/**
 *
 * @author sando
 */
public class TelefonoInvalidoException extends Exception{
    
    public TelefonoInvalidoException(){
        super("El telefono solo acepta caracteres numericos");
    }
}
