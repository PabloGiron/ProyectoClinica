package ModuloCitas;

import Entidades.Paciente;
import ModelosTablas.ModeloTablaContexto;
import ModelosTablas.ModeloTablaPacientes;
import Singleton.EntityM;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jFHistorial extends javax.swing.JFrame {

    //DECLARACION DE VARIABLES GLOBALES
    private DefaultTableModel modeloTabla;
    private EntityManager em = EntityM.getEm();
    private ModeloTablaContexto modTC = null;
    private Paciente pacienteC = null;
    
    public jFHistorial() {
        initComponents();
        jDCInicio.setDate(new Date());
        jDCFin.setDate(new Date());
        jDCInicio.setEnabled(false);
        jDCFin.setEnabled(false);
    }
    //METODO PARA LA CREACION DE MODELO DE TABLA PACIENTE
    private void setModeloTabla(){
        try {
            modTC = new ModeloTablaContexto(new ModeloTablaPacientes());
            modeloTabla = modTC.ejecutarModel();
            jTPacientes.setModel(modeloTabla);
            jTPacientes.getColumnModel().getColumn(0).setMaxWidth(0);
            jTPacientes.getColumnModel().getColumn(0).setMinWidth(0);
            jTPacientes.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTPacientes.getColumnModel().getColumn(0).setResizable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    
    //METODO PARA CARGAR PACIENTES MEDIANTE FILTRO
    private void cargarPacientes(String filtro){
        limpiarPacientes();
        Object o[] = null;
        int posicion = 0;
        Query queryPacientes = em.createNativeQuery("SELECT Paciente.id, Paciente.nombre, Paciente.nit FROM Paciente WHERE Paciente.nombre LIKE '" + filtro + "%';");
        List<Object[]> listaDatos = queryPacientes.getResultList();
        if(listaDatos.isEmpty())
        modeloTabla = (DefaultTableModel)jTPacientes.getModel();
        for(Object[] lista : listaDatos){
            modeloTabla.addRow(o);
            modeloTabla.setValueAt(lista[0], posicion, 0);
            modeloTabla.setValueAt(lista[1], posicion, 1);
            modeloTabla.setValueAt(lista[2], posicion, 2);
            posicion++;
        }       
        posicion=0;
    }
    
    private void cargarHistorial(){
        limpiarHistorial();
    }
    
    //METODO PARA LIMPIAR LA TABLA PACIENTES
    private void limpiarPacientes()
    {
        try
        {
            DefaultTableModel modelo=(DefaultTableModel) jTPacientes.getModel();
            int fil = jTPacientes.getRowCount()-1;
            for (int i = fil; i >= 0; i--)
            {
                modelo.removeRow(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //METODO PARA LIMPIAR LA TABLA HISTORIAL DEL PACIENTE
    private void limpiarHistorial(){
        try
        {
            DefaultTableModel modelo=(DefaultTableModel) jTOrtodoncia.getModel();
            int fil = jTOrtodoncia.getRowCount()-1;
            for (int i = fil; i >= 0; i--)
            {
                modelo.removeRow(i);
            }
            modelo=(DefaultTableModel) jTNormal.getModel();
            fil = jTNormal.getRowCount()-1;
            for (int i = fil; i >= 0; i--)
            {
                modelo.removeRow(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTPacientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTFFiltro = new javax.swing.JTextField();
        jDCInicio = new com.toedter.calendar.JDateChooser();
        jDCFin = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTNormal = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTOrtodoncia = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hisorial del paciente"));

        jTPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTPacientesMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTPacientes);

        jLabel6.setText("Filtrar Paciente:");

        jTFFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFFiltroKeyReleased(evt);
            }
        });

        jTNormal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTNormal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTNormalMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTNormal);

        jTOrtodoncia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTOrtodoncia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTOrtodonciaMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTOrtodoncia);

        jLabel1.setText("Fecha Inicio:");

        jLabel2.setText("Fecha Fin:");

        jLabel3.setText("Historial Ortodoncia");

        jLabel4.setText("Historial Normal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDCFin, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDCFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jDCInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CUANDO SE INGRESA UNA LETRA EN EL JTEXTFIELD SE CARGAN LOS DATOS CON UN FILTRO
    private void jTFFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFFiltroKeyReleased
    cargarPacientes(jTFFiltro.getText());
    }//GEN-LAST:event_jTFFiltroKeyReleased
    //CUANDO SE PRESIONA UN PACIENTE SE OBTIENEN LOS DATOS DEL PACIENTE
    private void jTPacientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPacientesMousePressed
        if(evt.getClickCount() > 1){
            int fila = jTPacientes.getSelectedRow();
            if(fila >= 0){
                try {
                    pacienteC = new Paciente(Integer.parseInt(jTPacientes.getValueAt(fila, 0).toString()));
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna fila.");
            }
        }
    }//GEN-LAST:event_jTPacientesMousePressed

    private void jTNormalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTNormalMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTNormalMousePressed

    private void jTOrtodonciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTOrtodonciaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTOrtodonciaMousePressed

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
            java.util.logging.Logger.getLogger(jFHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFHistorial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDCFin;
    private com.toedter.calendar.JDateChooser jDCInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTable jTNormal;
    private javax.swing.JTable jTOrtodoncia;
    private javax.swing.JTable jTPacientes;
    // End of variables declaration//GEN-END:variables
}
