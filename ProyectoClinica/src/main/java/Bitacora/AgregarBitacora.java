/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bitacora;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

/**
 *
 * @author oem
 */
public class AgregarBitacora {
    
    static int numeroTransaccion;
    
    public static void iniciarTransaccion(){
        numeroTransaccion = ObtenerNumTransaccion();
        try(FileWriter fw = new FileWriter("Bitacora.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
                {
                    AumentarNumTransaccion();
                    out.println("<T"+numeroTransaccion+",START TRANSACTION>");
                }catch (IOException e) {}
        System.out.println("Entro a iniciarTransaccion");
    }
    
    public static void agregarTransaccion(String cadena){
        numeroTransaccion = ObtenerNumTransaccion();
        try(FileWriter fw = new FileWriter("Bitacora.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
                {
                    out.println("<T"+numeroTransaccion+", "+ cadena+">");
                }catch (IOException e) {}
        System.out.println("Entro a iniciarTransaccion");
    }
    
    public static void finalizarTransaccion(int respuesta){
        numeroTransaccion = ObtenerNumTransaccion();
        try(FileWriter fw = new FileWriter("Bitacora.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
                if(respuesta==1){
                out.println("<T"+numeroTransaccion+",Commit>");}
                else if(respuesta==2){
                out.println("<T"+numeroTransaccion+",Rollback>");}
        }catch (IOException e) {}
    }
    
    public static int ObtenerNumTransaccion(){
     File f = new File("NumTransaccion.txt"); 
     BufferedReader entrada = null;
     String Num;
     int NumTransaccion = 0;
     try { 
             entrada = new BufferedReader( new FileReader( f ) ); 
             String linea; 
             while(entrada.ready()){ 
             linea = entrada.readLine(); 
             //System.out.println(linea);
             Num = linea;
             NumTransaccion = Integer.parseInt(Num);
             //NumTransaccion = n;
             System.out.println("El numero es: "+ NumTransaccion);
             } 
     }catch (IOException e) { 
             e.printStackTrace(); 
     } 
     finally 
     { 
     try{ 
            entrada.close(); 
     }catch(IOException e){} 
     }
     return NumTransaccion;
     //System.out.println(NumTransaccion);
 } 
 /**
  * Aumenta en 1 el numero de transaccion
  */

    public static void AumentarNumTransaccion(){
     numeroTransaccion = ObtenerNumTransaccion();
     numeroTransaccion = numeroTransaccion + 1;
     borrar("NumTransaccion.txt");
     try(FileWriter fw = new FileWriter("NumTransaccion.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw))
             {
                 out.println(numeroTransaccion);

              } catch (IOException e) {}
 }
 /**
  * Borra la direccion que reciba
  * @param Direccion String, que contiende la direccion del archivo
  */
     public static void borrar (String Direccion){
     File Txt = new File(Direccion);
     if(Txt.exists()){
         Txt.delete();
     }
 }
 
     public static void crearTransaccion(String cadena,int respuesta){
         iniciarTransaccion();
         agregarTransaccion(cadena);
         finalizarTransaccion(respuesta);
    }
}

