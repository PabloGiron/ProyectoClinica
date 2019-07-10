/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloInventario;

import Controladores.ProductoJpaController;
import Entidades.Producto;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oem
 */
public class InventarioPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form InventarioPrincipal
     */
    public InventarioPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        llenarTabla();
        cargarDatos();
    }
    ProductoJpaController controladorInventario = new ProductoJpaController(); 
    DefaultTableModel rellenarTabla;
    private void cargarDatos(){
        try{
            llenarTabla();
            Object o[] = null;
            List<Producto> listaPedido = controladorInventario.findProductoEntities();
            for(int i = 0; i < listaPedido.size(); i++){
                rellenarTabla.addRow(o);
                rellenarTabla.setValueAt(listaPedido.get(i).getId(), i, 0);
                rellenarTabla.setValueAt(listaPedido.get(i).getNombre(), i, 1);
                rellenarTabla.setValueAt(listaPedido.get(i).getCantidad(), i, 2);
                rellenarTabla.setValueAt(listaPedido.get(i).getPrecio(), i, 3);
                if (listaPedido.get(i).getTipo1().equals("1")){
                    rellenarTabla.setValueAt("Equipo", i, 4);
                }else if (listaPedido.get(i).getTipo1().equals("2")){
                    rellenarTabla.setValueAt("Instrumental operatorio", i, 4);
                }else if (listaPedido.get(i).getTipo1().equals("3")){
                    rellenarTabla.setValueAt("Material", i, 4);
                } else {rellenarTabla.setValueAt("Error", i, 4);}
                // Condicion para ver a que tipo_2 pertenece el objeto
                if (listaPedido.get(i).getTipo2().equals("1")){
                    rellenarTabla.setValueAt("Instrumental básico", i, 5);
                }else if (listaPedido.get(i).getTipo2().equals("2")){
                    rellenarTabla.setValueAt("Exodoncia", i, 5);
                }else if (listaPedido.get(i).getTipo2().equals("3")){
                    rellenarTabla.setValueAt("Operatoria", i, 5);
                }else if (listaPedido.get(i).getTipo2().equals("4")){
                    rellenarTabla.setValueAt("Periodoncia", i, 5);
                }else if (listaPedido.get(i).getTipo2().equals("5")){
                    rellenarTabla.setValueAt("Prótesis fija", i, 5);
                }else if (listaPedido.get(i).getTipo2().equals("6")){
                    rellenarTabla.setValueAt("Prótesis removibles parcial", i, 5);
                }else {rellenarTabla.setValueAt("---------", i, 5);}
                //rellenarTabla.setValueAt(listaPedido.get(i).getTipo1(), i, 4);
                //rellenarTabla.setValueAt(listaPedido.get(i).getTipo2(), i, 5);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void cargarMateriales(){
        try{
            llenarTabla();
            Object o[] = null;
            int cont = 0;
            List<Producto> listaPedido = controladorInventario.findProductoEntities();
            for(int i = 0; i < listaPedido.size(); i++){
                if(listaPedido.get(i).getTipo1().equals("3")){
                    rellenarTabla.addRow(o);
                    rellenarTabla.setValueAt(listaPedido.get(i).getId(), cont, 0);
                    rellenarTabla.setValueAt(listaPedido.get(i).getNombre(), cont, 1);
                    rellenarTabla.setValueAt(listaPedido.get(i).getCantidad(), cont, 2);
                    rellenarTabla.setValueAt(listaPedido.get(i).getPrecio(), cont, 3);
                    rellenarTabla.setValueAt("Material", cont, 4);
                    cont++;
                }else{System.out.println("Entro aqui: " + i);}
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void cargarEquipo(){
        try{
            llenarTabla();
            Object o[] = null;
            int cont = 0;
            List<Producto> listaPedido = controladorInventario.findProductoEntities();
            for(int i = 0; i < listaPedido.size(); i++){
                if(listaPedido.get(i).getTipo1().equals("1")){
                    rellenarTabla.addRow(o);
                    rellenarTabla.setValueAt(listaPedido.get(i).getId(), cont, 0);
                    rellenarTabla.setValueAt(listaPedido.get(i).getNombre(), cont, 1);
                    rellenarTabla.setValueAt(listaPedido.get(i).getCantidad(), cont, 2);
                    rellenarTabla.setValueAt(listaPedido.get(i).getPrecio(), cont, 3);
                    rellenarTabla.setValueAt("Equipo", cont, 4);
                    cont++;
                }else{System.out.println("Entro aqui: " + i);}
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        private void cargarInstrumentalOperatorio(){
        try{
            llenarTabla();
            Object o[] = null;
            int cont = 0;
            List<Producto> listaPedido = controladorInventario.findProductoEntities();
            for(int i = 0; i < listaPedido.size(); i++){
                if(listaPedido.get(i).getTipo1().equals("2")){
                    rellenarTabla.addRow(o);
                    rellenarTabla.setValueAt(listaPedido.get(i).getId(), cont, 0);
                    rellenarTabla.setValueAt(listaPedido.get(i).getNombre(), cont, 1);
                    rellenarTabla.setValueAt(listaPedido.get(i).getCantidad(), cont, 2);
                    rellenarTabla.setValueAt(listaPedido.get(i).getPrecio(), cont, 3);
                    rellenarTabla.setValueAt("Instrumental operatorio", cont, 4);
                    if (listaPedido.get(i).getTipo2().equals("1")) {
                        rellenarTabla.setValueAt("Instrumental básico", cont, 5);
                    } else if (listaPedido.get(i).getTipo2().equals("2")) {
                        rellenarTabla.setValueAt("Exodoncia", cont, 5);
                    } else if (listaPedido.get(i).getTipo2().equals("3")) {
                        rellenarTabla.setValueAt("Operatoria", cont, 5);
                    } else if (listaPedido.get(i).getTipo2().equals("4")) {
                        rellenarTabla.setValueAt("Periodoncia", cont, 5);
                    } else if (listaPedido.get(i).getTipo2().equals("5")) {
                        rellenarTabla.setValueAt("Prótesis fija", cont, 5);
                    } else if (listaPedido.get(i).getTipo2().equals("6")) {
                        rellenarTabla.setValueAt("Prótesis removibles parcial", cont, 5);
                    } else {
                        rellenarTabla.setValueAt("---------", cont, 5);
                    }
                    cont++;
                }else{System.out.println("Entro aqui: " + i);}
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        private void llenarTabla() {
        try {
            rellenarTabla = (new DefaultTableModel(
                    null, new String[]{
                        "Id", "Nombre",
                        "Cantidad","Precio",
                        "Tipo 1", "Tipo 2"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false,false, false, false
                };
                
                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return canEdit[colIndex];
                }
            });
            tablaInventario.setModel(rellenarTabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaInventarioMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        jButton1.setText("Mostrar todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Materiales");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Equipo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Instrumental operatorio");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenu1.setText("Crear");

        jMenuItem1.setText("Crear producto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Modificar");

        jMenuItem2.setText("Modificar producto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaInventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaInventarioMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here

        cargarDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cargarEquipo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cargarMateriales();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        cargarInstrumentalOperatorio();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ClienteInventario inventario = new ClienteInventario();
        inventario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        ClienteInventario inventario = new ClienteInventario();
        inventario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InventarioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventarioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventarioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventarioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventarioPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaInventario;
    // End of variables declaration//GEN-END:variables
}
