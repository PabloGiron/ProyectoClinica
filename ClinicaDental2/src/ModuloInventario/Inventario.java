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
public class Inventario {
    private IModificarBD strategy;
    public Inventario(IModificarBD strategy){
        this.strategy = strategy;
    }
    public void procesar(Producto producto){
        strategy.Operar(producto);
    }
}
