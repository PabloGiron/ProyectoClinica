/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloInventario;

/**
 *
 * @author oem
 */
public class AgregarProducto extends Inventario {
    private Inventario inventario = new Inventario();
    public void agregarProducto(){
        
        System.out.println(inventario.getPrecio());
    }
}
