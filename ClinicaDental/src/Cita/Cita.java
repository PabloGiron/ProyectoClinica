/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cita;

/**
 *
 * @author nasc_
 */
public class Cita {
    
    public ICita getCita(String tipo){
        return (ICita) new CitaNormal();
    }
}