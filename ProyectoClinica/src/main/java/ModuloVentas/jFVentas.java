package ModuloVentas;

import ModelosTablas.ModeloTablaDetalles;
import ModelosTablas.ModeloTablaVentas;
import Controladores.DetalleventaJpaController;
import Controladores.VentasJpaController;
import Entidades.Detalleventa;
import Entidades.Servicio;
import Entidades.Ventas;
import Entidades.LibroCompraVenta;
import ModelosTablas.ModeloTablaContexto;
import Singleton.EntityM;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class jFVentas extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;
    private EntityManager em = EntityM.getEm();
    private ModeloTablaContexto modTC = null;
    private Calendar c = new GregorianCalendar();
    
    public jFVentas() {
        initComponents();
        llenarCombo();
        setModeloTabla();
        cargarVentas(c.getTime());
        jDCFecha.setCalendar(c);
        jBCrear.setEnabled(false);
        agregarEvento();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNuevos = new javax.swing.JTable();
        jCBServicios = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jBAgregar = new javax.swing.JButton();
        jBCrear = new javax.swing.JButton();
        jTFCantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFFactura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Existencias = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTVentas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTDetalles = new javax.swing.JTable();
        jDCFecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Venta"));

        jTNuevos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTNuevos);

        jLabel1.setText("Servicio");

        jBAgregar.setText("Agregar Servicio");
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });

        jBCrear.setText("Crear Venta");
        jBCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCrearActionPerformed(evt);
            }
        });

        jTFCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCantidadKeyTyped(evt);
            }
        });

        jLabel2.setText("Cantidad");

        jTFFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFFacturaKeyTyped(evt);
            }
        });

        jLabel3.setText("No. Factura: ");

        Existencias.setText("Existencias");
        Existencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExistenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Existencias)
                        .addGap(312, 312, 312)
                        .addComponent(jBCrear)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel1))
                            .addComponent(jCBServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2)
                                .addGap(157, 157, 157))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBAgregar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCBServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCrear)
                    .addComponent(Existencias))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas realizadas"));

        jTVentas = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.
            }
        };
        jTVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTVentas);

        jTDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTDetalles);

        jDCFecha.setToolTipText("");

        jLabel4.setText("Seleccionar fecha:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDCFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //FUNCION PARA AGREGAR EVENTO
    public void agregarEvento(){
        jDCFecha.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if("date".equals(e.getPropertyName())){
                    cargarVentas((Date)e.getNewValue());
                }
            }
        });
    }
    
    //Funciones para limpiar y setear ""
    public void limpiarRegistros()
    {
        try
        {
            DefaultTableModel modelo=(DefaultTableModel) jTNuevos.getModel();
            int fil = jTNuevos.getRowCount()-1;
            for (int i = fil; i >= 0; i--)
            {
                modelo.removeRow(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void limpiarDetalles()
    {
        try
        {
            DefaultTableModel modelo=(DefaultTableModel) jTDetalles.getModel();
            int fil = jTDetalles.getRowCount()-1;
            for (int i = fil; i >= 0; i--)
            {
                modelo.removeRow(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void limpiarVentas()
    {
        try
        {
            DefaultTableModel modelo=(DefaultTableModel) jTVentas.getModel();
            int fil = jTVentas.getRowCount()-1;
            for (int i = fil; i >= 0; i--)
            {
                modelo.removeRow(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void verificar()
    {
        int fil = jTNuevos.getRowCount();
        if(fil > 0)
        {
            limpiarRegistros();
            jBCrear.setEnabled(false);
        }
    }
    
    
    
    //Método que se utilizará para llenar el ComboBox con todos los servicios
    private void llenarCombo(){
        TypedQuery<Servicio> query = em.createNamedQuery("Servicio.findAll", Servicio.class);
        List<Servicio> listaDatos = query.getResultList();
        for(Servicio s : listaDatos){
            jCBServicios.addItem(s.getNombre());
        }
    }
    
    //Método utilizado para crear el modelo de la tabla donde se muestran las ventas
    private void setModeloTabla(){
        try {
            modTC = new ModeloTablaContexto(new ModeloTablaVentas());
            modeloTabla = modTC.ejecutarModel();
            jTVentas.setModel(modeloTabla);
            jTVentas.getColumnModel().getColumn(0).setMaxWidth(0);
            jTVentas.getColumnModel().getColumn(0).setMinWidth(0);
            jTVentas.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTVentas.getColumnModel().getColumn(0).setResizable(false);
            modTC = new ModeloTablaContexto(new ModeloTablaDetalles());
            modeloTabla = modTC.ejecutarModel();
            jTDetalles.setModel(modeloTabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    
    //Método utilizado para cargar los datos en la tabla
    private void cargarVentas(Date fecha){
        limpiarVentas();
        Object o[] = null;
        int posicion = 0;
        TypedQuery<Ventas> query = em.createNamedQuery("Ventas.findAllFecha", Ventas.class);
        query.setParameter("inicio", fecha, TemporalType.DATE);
        query.setParameter("fin", fecha, TemporalType.DATE);
        List<Ventas> listaDatos = query.getResultList();
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "E MMM dd HH:mm:ss z uuuu" ).withLocale( Locale.US );
        ZonedDateTime zdt = null;
        LocalDate ld = null;
        modeloTabla = (DefaultTableModel)jTVentas.getModel();
        for(Ventas v : listaDatos){
            modeloTabla.addRow(o);
            modeloTabla.setValueAt(v.getId(), posicion, 0);
            modeloTabla.setValueAt(v.getTotal(), posicion, 1);
            zdt = ZonedDateTime.parse(v.getFecha().toString(), f);
            ld = zdt.toLocalDate();
            modeloTabla.setValueAt(ld, posicion, 2);
            modeloTabla.setValueAt(v.getNumero(), posicion, 3);
            posicion++;
        }
        posicion=0;
    }
    private void cargarDetalles(int id){
        Object o[] = null;
        int posicion = 0;
        Query v = em.createNativeQuery("SELECT servicio.Nombre, detalleventa.Cantidad, detalleventa.Subtotal FROM Servicio INNER JOIN detalleventa ON Servicio.id = detalleventa.Servicio_id WHERE detalleventa.Ventas_id = ?;");
        v.setParameter(1, id);
        List<Object[]> listaDatos = v.getResultList();
        modeloTabla = (DefaultTableModel)jTDetalles.getModel();
        for(Object[] lista : listaDatos){
            modeloTabla.addRow(o);
            modeloTabla.setValueAt(lista[0], posicion, 0);
            modeloTabla.setValueAt(lista[1], posicion, 1);
            modeloTabla.setValueAt(lista[2], posicion, 2);
            posicion++;
        }       
        posicion=0;
    }
    
    //Método para crear DetalleVenta
    private void crearDetalle(int linea){
        //Obtención de Querys necesarias 
        Detalleventa detalle = new Detalleventa();
        DetalleventaJpaController cDetalle = new DetalleventaJpaController(EntityM.getEmf());
        Servicio servicio = new Servicio();
        
        TypedQuery<Servicio> queryServicio = em.createNamedQuery("Servicio.findByNombre", Servicio.class);
        queryServicio.setParameter("nombre", (String)jTNuevos.getValueAt(linea, 0));
        servicio = queryServicio.getSingleResult();
        
        Query queryVentasId = em.createNamedQuery("Ventas.idMax", Ventas.class);
        // Declaración de variables extra
        
        float precio = servicio.getPrecio();
        
        int idVenta = (Integer)queryVentasId.getSingleResult();
        TypedQuery<Ventas> queryVentas = em.createNamedQuery("Ventas.findById", Ventas.class);
        queryVentas.setParameter("id", idVenta);
        Ventas v = queryVentas.getSingleResult();
        Servicio s = queryServicio.getSingleResult();
        try{
            detalle.setSubtotal(precio*Integer.parseInt((String)jTNuevos.getValueAt(linea, 1)));
            detalle.setCantidad(Integer.parseInt((String)jTNuevos.getValueAt(linea, 1)));
            detalle.setServicioid(s);
            detalle.setVentasid(v);
            cDetalle.create(detalle);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //Método para crear Factura
    private void crearFactura(){
        //Creación de Querys
        Ventas venta = new Ventas();
        VentasJpaController cVentas = new VentasJpaController(EntityM.getEmf());
        LibroCompraVenta lbCV = new LibroCompraVenta();
        TypedQuery<LibroCompraVenta> queryLibro = em.createNamedQuery("LibroCompraVenta.findById", LibroCompraVenta.class);
        
        Query queryLibroId = em.createNamedQuery("LibroCompraVenta.idMax", LibroCompraVenta.class);
        
        //Declaración de variables
        int idLibro = 0;
        float total = 0;
        Date fecha = jDCFecha.getDate();
        
        idLibro = (Integer)queryLibroId.getSingleResult();
        queryLibro.setParameter("id", idLibro);
        lbCV = queryLibro.getSingleResult();
        
        try{
            venta.setTotal(total);
            venta.setFecha(fecha);
            venta.setNumero(jTFFactura.getText());
            venta.setLibrocompraventaid(lbCV);
            cVentas.create(venta);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //Método para actualizar el total de la venta
    private void totalVenta(){
        Ventas venta = new Ventas();
        VentasJpaController cVentas = new VentasJpaController(EntityM.getEmf());
        
        Query queryVentaId = em.createNamedQuery("Ventas.idMax", Ventas.class);
        int idVenta = (Integer)queryVentaId.getSingleResult();
        
        Query queryVenta = em.createNamedQuery("Ventas.findById", Ventas.class);
        queryVenta.setParameter("id", idVenta);
        venta = (Ventas)queryVenta.getSingleResult();
        
        Query detallev = em.createNativeQuery("SELECT SUM(Subtotal) FROM detalleventa WHERE detalleventa.Ventas_id = ?");
        detallev.setParameter(1, idVenta);
        double tot = (Double) detallev.getSingleResult();
        
        float total = (float) tot;
        try{
            venta.setTotal(total);
            cVentas.updateTotal(venta);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //Métodos/Acciones de botones
    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
        int a = 0;
        
        if(!"".equals(jTFCantidad.getText()))
        {
            a = Integer.parseInt(jTFCantidad.getText());
        }

        if(((!"".equals(jTFCantidad.getText())) && (!"".equals(jTFFactura.getText()))) || (a >= 1))
        {
            String  fila[] = new String [2];
            DefaultTableModel modelo = (DefaultTableModel) jTNuevos.getModel();
            fila[0] = (String)jCBServicios.getSelectedItem();
            fila[1] = jTFCantidad.getText();
            modelo.addRow(fila);
        }    
        else
        {
            JOptionPane.showMessageDialog(this, "Debe llenar el espacio cantidad y/o factura para proceder");
        }
        jTFCantidad.setText("");
        jBCrear.setEnabled(true);
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jBCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCrearActionPerformed
        //ESTE BOTÓN SERVIRÁ PARA CREAR LA FACTURA Y LUEGO CREAR CADA UNO DE LOS DETALLES DE LA MISMA 
        //Y COMO FINAL ACUALIZAR EL TOTAL EN LA VENTA
        crearFactura();
        for(int i = 0; i < jTNuevos.getRowCount(); i++)
        {
            crearDetalle(i);
        }
        totalVenta();
        verificar();
        cargarVentas(jDCFecha.getDate());
        jTFFactura.setText("");
    }//GEN-LAST:event_jBCrearActionPerformed

    private void ExistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExistenciasActionPerformed
        /*
        this.setEnabled(false);
        this.setVisible(false);
        new Reduc(conexion,this).setVisible(true);*/
    }//GEN-LAST:event_ExistenciasActionPerformed

    private void jTVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTVentasMouseClicked
        //AL DARLE DOBLE CLICK A UNA FILA, BUSCARÁ LOS DETALLES DE LA VENTA Y LOS SETEARÁ A LA TABLA
        if(evt.getButton() == 1)
        {
            int fila = jTVentas.getSelectedRow();
            if(fila >= 0){
              limpiarDetalles();
              cargarDetalles((Integer)jTVentas.getValueAt(fila, 0));
            }    
        }
    }//GEN-LAST:event_jTVentasMouseClicked

    private void jTFCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCantidadKeyTyped
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
    }//GEN-LAST:event_jTFCantidadKeyTyped

    private void jTFFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFFacturaKeyTyped
        char letra = evt.getKeyChar();
        if((letra<'0'||letra>'9')) evt.consume();
    }//GEN-LAST:event_jTFFacturaKeyTyped

    public static void main(String args[]) {
        
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
            java.util.logging.Logger.getLogger(jFVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Existencias;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JButton jBCrear;
    private javax.swing.JComboBox jCBServicios;
    private com.toedter.calendar.JDateChooser jDCFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTDetalles;
    private javax.swing.JTextField jTFCantidad;
    private javax.swing.JTextField jTFFactura;
    private javax.swing.JTable jTNuevos;
    private javax.swing.JTable jTVentas;
    // End of variables declaration//GEN-END:variables
}
