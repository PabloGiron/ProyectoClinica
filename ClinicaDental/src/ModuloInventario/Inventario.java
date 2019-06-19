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
    private float precio;
    private String nombreProducto;
    private int cantidad;

    public float getPrecio() {
        return precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void consultarProducto(){
        
    }
}
