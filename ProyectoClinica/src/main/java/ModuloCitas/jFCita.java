package ModuloCitas;

import Controladores.CitanormalJpaController;
import Controladores.CitaortodonciaJpaController;
import Entidades.Citanormal;
import Entidades.Citaortodoncia;
import Entidades.Historialpaciente;
import Entidades.Paciente;
import ModelosTablas.ModeloTablaContexto;
import ModelosTablas.ModeloTablaPacientes;
import ModuloPacientes.ClientePacientes;
import Singleton.EntityM;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nasc_
 */
public class jFCita  extends javax.swing.JFrame {
    
    //DECLARACION DE VARIABLES GLOBALES
    private DefaultTableModel modeloTabla;
    private EntityManager em = EntityM.getEm();
    private ModeloTablaContexto modTC = null;
    private Paciente pacienteC = null;
    
    public jFCita() {
        initComponents();
        this.setLocationRelativeTo(null);
        setModeloTabla();
        cargarPacientes("n");
    }
    //METODO PARA CREAR LA CITA
    private void crearCita(){
        Query queryHistorial = em.createQuery("SELECT h FROM Paciente p INNER JOIN p.historialpacienteList h WHERE p.id = " + pacienteC.getId() + " ");
        Historialpaciente historial = (Historialpaciente)queryHistorial.getSingleResult();
        //CREA UNA CITA DE ORTODONCIA
        if(String.valueOf(jCBCita.getSelectedItem()).equals("Cita Ortodoncia")){
            Citaortodoncia cita = new Citaortodoncia();
            CitaortodonciaJpaController cCita = new CitaortodonciaJpaController(EntityM.getEmf());
            if(jTFNombre.getText().equals("") || jTFPrecio.getText().equals("") ){
            JOptionPane.showMessageDialog(null,"Error: Uno de los campos se encuentran vacíos.");
            }
            else if(Integer.parseInt(jTFPrecio.getText()) < 1){
                JOptionPane.showMessageDialog(null,"Error: El precio debe ser mayor a 0.");
            }
            else{
                short pagado;
                if(jCheckPagado.isSelected()){
                    pagado = 1;
                }else{
                    pagado = 0;
                }
                
                cita.setPrecio(Float.parseFloat(jTFPrecio.getText()));
                cita.setDescripcion(jTADescripcion.getText());
                cita.setFecha(new Date());
                cita.setPagado(pagado);
                cita.setHistorialPacienteidHistorialPaciente(historial);
                cCita.create(cita);
                
            }
        //CREAR CITA NORMAL
        }else if(String.valueOf(jCBCita.getSelectedItem()).equals("Cita Normal")){
            Citanormal cita = new Citanormal();
            CitanormalJpaController cCita = new CitanormalJpaController(EntityM.getEmf());
            cita.setDescripcion(jTADescripcion.getText());
            cita.setFecha(new Date());
            cita.setHistorialPacienteidHistorialPaciente(historial);
            cCita.create(cita);
        }
        this.jTFPrecio.setText("");
        this.jTFNombre.setText("");
        this.jTADescripcion.setText("");
        this.jTFNit.setText("");
        this.jCheckPagado.setSelected(false);
                
    }
    //CARGAR PACIENTES A TABLA CON FILTRO PERSONALIZADO
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
    //METODO DE CREACION DE MODELO DE TABLA
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
    //METODO PARA LIMPIAR TABLA PACIENTE
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
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jCBCita = new javax.swing.JComboBox<>();
        jTFPrecio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckPagado = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADescripcion = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jBAgregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTPacientes = new javax.swing.JTable();
        jTFNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTFNit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFFiltro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jDCFecha = new org.netbeans.modules.form.InvalidComponent();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cita"));

        jCBCita.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cita Ortodoncia", "Cita Normal" }));
        jCBCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBCitaActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de Cita:");

        jLabel2.setText("Precio:");

        jCheckPagado.setText("Pagado");

        jTADescripcion.setColumns(20);
        jTADescripcion.setRows(5);
        jTADescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTADescripcionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTADescripcion);

        jLabel3.setText("Descripcion:");

        jBAgregar.setText("Agregar");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

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

        jTFNombre.setEditable(false);

        jLabel4.setText("Nombre:");

        jLabel5.setText("NIT:");

        jTFNit.setEditable(false);

        jLabel6.setText("Filtrar Paciente:");

        jTFFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFFiltroKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCBCita, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jCheckPagado))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFNit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jCheckPagado))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jTFNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBAgregar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Seleccionar fecha:");

        jDCFecha.setToolTipText("");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Reporte2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jCBCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBCitaActionPerformed
        //SI EL CHECK BUTTON ESTA EN 1 SE DESHABILITAN EL PRECIO Y EL CHECK DE PAGADO
        if(jCBCita.getSelectedIndex() == 1){
            jTFPrecio.setEditable(false);
            jCheckPagado.setEnabled(false);
        }
        //SI EL CHECK BUTTON ESTA EN 0 SE HABILITAN EL PRECIO Y EL CHECK DE PAGADO
        else if (jCBCita.getSelectedIndex() == 0){
            jTFPrecio.setEditable(true);
            jCheckPagado.setEnabled(true);
        }
    }//GEN-LAST:event_jCBCitaActionPerformed

    private void jTADescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTADescripcionKeyTyped
        if(jTADescripcion.getText().length() == 100)
        {
           evt.consume();
        }
    }//GEN-LAST:event_jTADescripcionKeyTyped

    private void jTFFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFFiltroKeyReleased
        cargarPacientes(jTFFiltro.getText());
    }//GEN-LAST:event_jTFFiltroKeyReleased
    //SI SE SELECCIONA UN PACIENTE DE LA TABLA SUS DATOS SE INGRESAN EN LOS TEXTFIELD
    private void jTPacientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPacientesMousePressed
        if(evt.getClickCount() > 1){
            int fila = jTPacientes.getSelectedRow();
            if(fila >= 0){
                try {
                    pacienteC = new Paciente(Integer.parseInt(jTPacientes.getValueAt(fila, 0).toString()));
                    jTFNombre.setText(jTPacientes.getValueAt(fila, 1).toString());
                    jTFNit.setText(jTPacientes.getValueAt(fila, 2).toString());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna fila.");
            }
        }
    }//GEN-LAST:event_jTPacientesMousePressed

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        crearCita();
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ClientePacientes cliente = new ClientePacientes();
        cliente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jFReporteCitas cita = new jFReporteCitas();
        cita.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(jFCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new jFCita().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAgregar;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBCita;
    private javax.swing.JCheckBox jCheckPagado;
    private org.netbeans.modules.form.InvalidComponent jDCFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTADescripcion;
    private javax.swing.JTextField jTFFiltro;
    private javax.swing.JTextField jTFNit;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFPrecio;
    private javax.swing.JTable jTPacientes;
    // End of variables declaration//GEN-END:variables
}
