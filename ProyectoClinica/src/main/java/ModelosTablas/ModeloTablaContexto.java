/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosTablas;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nasc_
 */
public class ModeloTablaContexto {
    private ACModeloTabla mod;
    
    public ModeloTablaContexto(ACModeloTabla mod){
        this.mod = mod;
    }
    
    public DefaultTableModel ejecutarModel(){
        return mod.getModelo();
    }
}
