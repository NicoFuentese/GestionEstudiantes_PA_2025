/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_estudiante;

/**
 *
 * @author sando
 */
public class EmailInvalidoException extends Exception{
    public EmailInvalidoException(){
        super("Ingrese un email valido por favor (puede ser que se haya olvidado el @ o .)");
    }
}
