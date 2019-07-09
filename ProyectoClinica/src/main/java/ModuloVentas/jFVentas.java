package ModuloVentas;

import Controladores.DetalleventaJpaController;
import Controladores.VentasJpaController;
import Entidades.*;
import Singleton.EntityM;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class jFVentas extends javax.swing.JFrame {

    DefaultTableModel modeloTabla;
    private EntityManager em = EntityM.getEm();
    
    public jFVentas() {
        initComponents();
        llenarCombo();
        setModeloTabla();
        cargarVentas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Nuevos = new javax.swing.JTable();
        jCBServicios = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        Agregar = new javax.swing.JButton();
        Crear = new javax.swing.JButton();
        jTFCantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFFactura = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Existencias = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jFVentas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Detalle = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Venta"));

        Nuevos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Nuevos);

        jLabel1.setText("Servicio");

        Agregar.setText("Agregar Servicio");
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });

        Crear.setText("Crear Venta");
        Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearActionPerformed(evt);
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
                        .addComponent(Crear)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(Agregar))))
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
                    .addComponent(Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Crear)
                    .addComponent(Existencias))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas realizadas"));

        jFVentas = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.
            }
        };
        jFVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jFVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jFVentas);

        Detalle.setEditable(false);
        Detalle.setColumns(20);
        Detalle.setRows(5);
        Detalle.setText("Detalles de venta...");
        jScrollPane3.setViewportView(Detalle);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Método que se utilizará para llenar el ComboBox con todos los servicios
    private void llenarCombo(){
        TypedQuery<Servicio> query = em.createNamedQuery("Servicio.findAll", Servicio.class);
        List<Servicio> listaDatos = query.getResultList();
        for(Servicio s : listaDatos){
            jCBServicios.addItem(s.getNombre());
        }
        em.close();
    }
    
    //Método utilizado para crear el modelo de la tabla donde se muestran las ventas
    private void setModeloTabla(){
        try {
            modeloTabla = (new DefaultTableModel(
                    null, new String[]{
                        "", "Total",
                        "Fecha","No. Factura"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false
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
            jFVentas.setModel(modeloTabla);
            jFVentas.getColumnModel().getColumn(0).setMaxWidth(0);
            jFVentas.getColumnModel().getColumn(0).setMinWidth(0);
            jFVentas.getColumnModel().getColumn(0).setPreferredWidth(0);
            jFVentas.getColumnModel().getColumn(0).setResizable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    
    //Método utilizado para cargar los datos en la tabla
    private void cargarVentas(){
        Object o[] = null;
        int posicion = 0;
        TypedQuery<Ventas> query = em.createNamedQuery("Ventas.findAll", Ventas.class);
        List<Ventas> listaDatos = query.getResultList();
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "E MMM dd HH:mm:ss z uuuu" ).withLocale( Locale.US );
        ZonedDateTime zdt = null;
        LocalDate ld = null;
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
        em.close();
    }
    
    //Método para crear DetalleVenta
    private void crearDetalle(){
        //Obtención de Querys necesarias 
        Detalleventa detalle = new Detalleventa();
        DetalleventaJpaController cDetalle = new DetalleventaJpaController(EntityM.getEmf());
        Query queryDetalle = em.createNativeQuery("INSERT INTO Detalleventa (Subtotal, Cantidad, Servicio_id, Ventas_id) VALUES (:subtotal, :cantidad, :servid, :vid)");
        
        TypedQuery<Servicio> queryServicio = em.createNamedQuery("Servicio.findByNombre", Servicio.class);
        queryServicio.setParameter("nombre", jCBServicios.getSelectedItem());
        
        Query queryVentasId = em.createNamedQuery("Ventas.idMax", Ventas.class);
        // Declaración de variables extra
        float subTotal = 0, precio = 0;
        int idVenta = 0;
        
        idVenta = (Integer)queryVentasId.getSingleResult();
        TypedQuery<Ventas> queryVentas = em.createNamedQuery("Ventas.findById", Ventas.class);
        queryServicio.setParameter("id", idVenta);
        Ventas v = queryVentas.getSingleResult();
        Servicio s = queryServicio.getSingleResult();
        try{
            detalle.setSubtotal(precio*Integer.parseInt(jTFCantidad.getText()));
            detalle.setCantidad(Integer.parseInt(jTFCantidad.getText()));
            detalle.setServicioid(s);
            detalle.setVentasid(v);
            cDetalle.create(detalle);
            JOptionPane.showMessageDialog(null, "¡Detalle cargado correctamente!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        em.close();
    }
    
    //Método para crear Factura
    private void crearFactura(){
        //Creación de Querys
        Ventas venta = new Ventas();
        VentasJpaController cVentas = new VentasJpaController(EntityM.getEmf());
        Query queryLibro = em.createQuery("LibroCompraVenta.idMax", LibroCompraVenta.class);
        Query queryVenta = em.createNativeQuery("INSERT INTO Ventas (Total, Fecha, Numero, Libro_compra_venta_id) VALUES (:total, :fecha, :numero, :idLibro)");
        
        //Declaración de variables
        int idLibro = 0;
        float total = 0;
        Date fecha = null;
       
        idLibro = (Integer)queryLibro.getSingleResult();
        
        venta.setTotal(total);
        venta.setFecha(fecha);
        
    }
    
    //Métodos/Acciones de botones
    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
        
        
        
        /*PreparedStatement Com;
        int a = 0;
        int id_libro;
        Bandera = 1;
        try {
            try(FileWriter fw = new FileWriter("\\\\OEM-VAIO\\Users\\oem\\Documents\\GitHub\\BD_Clinica\\Bitacora.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                if(!"".equals(Cantidad.getText()))
                {
                    a = Integer.parseInt(Cantidad.getText());
                }

                if(((!"".equals(Cantidad.getText())) && (!"".equals(factura.getText()))) || (a >= 1))
                {
                    if(Nuevos.getRowCount() == 0)
                    {
                        try {
                            //----------------------------------------------------------
                            PreparedStatement InicioTransaccion = conexion.prepareStatement("START TRANSACTION");
                            InicioTransaccion.executeUpdate();
                            AumentarNumTransaccion();
                            out.println("<T"+NumTransaccion+",START TRANSACTION>");

                            //-------------------------------------------
                            String sql2 = "SELECT MAX(Libro_compra_venta.ID) FROM Libro_compra_venta;";
                            sent = conexion.createStatement();
                            ResultSet rs = sent.executeQuery(sql2);
                            rs.next();
                            id_libro = Integer.parseInt(rs.getString(1));
                            String sql_venta = "INSERT INTO Ventas (Total, Fecha, Numero, Libro_compra_venta_id) VALUES(0, NOW(), '"+factura.getText()+"', "+ id_libro +")";
                            PreparedStatement ps;
                            Date date = new Date();
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            out.println("<T"+NumTransaccion+",Escritura tabla ventas,0,"+dateFormat.format(date)+","+factura.getText()+","+id_libro+">");
                            ps = conexion.prepareCall(sql_venta);
                            int n = ps.executeUpdate();

                            if (n > 0)
                            {

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Venta_na.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    String  fila[] = new String [2];
                    DefaultTableModel modelo = (DefaultTableModel) Nuevos.getModel();
                    fila[0] = (String)Servicios.getSelectedItem();
                    fila[1] = Cantidad.getText();
                    modelo.addRow(fila);
                    try {
                        String id_s, sub, p, m;
                        float precio = 0, subtotal = 0, cant = 0;
                        int id_v = Venta_id();
                        String s, v;
                        v = Float.toString(id_v);
                        String sql2 = "SELECT Servicio.ID, Servicio.Precio FROM Servicio WHERE Servicio.Nombre = '" + Nuevos.getValueAt(Nuevos.getRowCount()-1, 0) + "';";
                        sent = conexion.createStatement();
                        ResultSet rs = sent.executeQuery(sql2);
                        rs.next();
                        id_s = (rs.getString(1));
                        p = rs.getString("Servicio.precio");
                        precio = Float.parseFloat(p);
                        m = (String)Nuevos.getValueAt(Nuevos.getRowCount()-1, 1);
                        cant = Integer.parseInt(m);
                        subtotal = precio * cant;
                        sub = Float.toString(subtotal);
                        out.println("<T"+NumTransaccion+",Escritura tabla detalleventa,"+sub+","+(String)Nuevos.getValueAt(Nuevos.getRowCount()-1, 1)+","+v+","+id_s+">");
                    }catch (SQLException ex) {
                        Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } Cantidad.setText("");
                    habilitar();
                }
                else
                {

                    

                    JOptionPane.showMessageDialog(this, "Debe llenar el espacio cantidad y/o factura para proceder");
                }}}catch (IOException e) {}*/
    }//GEN-LAST:event_AgregarActionPerformed

    private void CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearActionPerformed
       /* try {
            try(FileWriter fw = new FileWriter("\\\\OEM-VAIO\\Users\\oem\\Documents\\GitHub\\BD_Clinica\\Bitacora.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                //String sql = "INSERT INTO DetalleVenta (Subtotal, Cantidad, Ventas_ID, Servicio_ID)" + "VALUES(?,?,?,?)";

                String id_s, sub, p, m;
                float precio = 0, subtotal = 0, cant = 0;
                int id_v = Venta_id();
                String s, v;
                v = Float.toString(id_v);
                float total = 0;
                for(int i = 0; i < Nuevos.getRowCount(); i++)
                {

                    String sql2 = "SELECT Servicio.ID, Servicio.Precio FROM Servicio WHERE Servicio.Nombre = '" + Nuevos.getValueAt(i, 0) + "';";
                    sent = conexion.createStatement();
                    ResultSet rs = sent.executeQuery(sql2);
                    rs.next();
                    id_s = (rs.getString(1));
                    p = rs.getString("Servicio.precio");
                    precio = Float.parseFloat(p);
                    m = (String)Nuevos.getValueAt(i, 1);
                    cant = Float.parseFloat(m);
                    subtotal = precio * cant;
                    System.out.println(m);
                    sub = Float.toString(subtotal);
                    System.out.println(subtotal);

                    String sql = "INSERT INTO DetalleVenta (Subtotal, Cantidad, Ventas_ID, Servicio_ID)" + "VALUES(?,?,?,?)";
                    PreparedStatement ps = conexion.prepareCall(sql);
                    ps.setString(1, sub);
                    ps.setString(2, (String)Nuevos.getValueAt(i, 1));
                    ps.setString(3, v);
                    ps.setString(4, id_s);
                    int n = ps.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "Datos Guardados Correctamente");
                        out.println("<T"+NumTransaccion+",Escritura tabla detalleventa,"+sub+","+(String)Nuevos.getValueAt(i, 1)+","+v+","+id_s+">");

                    }

                    total = total + subtotal;

                    /*------------------------------------------------------------------------*/
                    /*ResultSet rs2 = null;

                    String sql45 = "SELECT MAX(id) FROM ventas";
                    Statement stmt2 = conexion.createStatement();
                    rs2 = stmt2.executeQuery(sql45);
                    int id2 =0;
                    while(rs2.next()){
                        //System.out.println(rs.getInt(1));
                        id2 = rs2.getInt(1);
                    }
                    String sql23 = ("SELECT * FROM ventas WHERE id = '"+id2+"'");
                    stmt2 = conexion.createStatement();
                    rs2 = stmt2.executeQuery(sql23);
                    while(rs2.next()){
                        //System.out.println(rs.getInt(1));
                        total = rs2.getInt(2);
                    }
                    PreparedStatement pst = conexion.prepareStatement("INSERT INTO finanzas(Ingresos,Egresos,Fecha) VALUES('"+total+"',0,NOW())");
                    pst.executeUpdate();

                    /*-------------------------------------------------------------------------

                }
                totalventa(id_v, Float.toString(total));
                PreparedStatement Com = conexion.prepareStatement("COMMIT");
                Com.executeUpdate();
                out.println("<T"+NumTransaccion+",Commit>");
                Bandera = 0;
            }catch (IOException e) {}  }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        verificar();
        //cargar();
        carga();
        factura.setText("");*/

    }//GEN-LAST:event_CrearActionPerformed

    private void ExistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExistenciasActionPerformed
        /*
        this.setEnabled(false);
        this.setVisible(false);
        new Reduc(conexion,this).setVisible(true);*/
    }//GEN-LAST:event_ExistenciasActionPerformed

    private void jFVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFVentasMouseClicked
        /*
        if(evt.getButton() == 1)
        {
            Detalle.setText("");
            int fila = Ventas.getSelectedRow();
            String nombre,cantidad,subtotal;
            try {
                String sql = "SELECT servicio.Nombre, detalleventa.Cantidad, detalleventa.Subtotal FROM Servicio INNER JOIN detalleventa ON Servicio.id = detalleventa.Servicio_id WHERE detalleventa.Ventas_id = " + Ventas.getValueAt(fila, 0) + ";";
                sent = conexion.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                while (rs.next()) {
                    nombre = rs.getString("servicio.Nombre");
                    cantidad = rs.getString("detalleventa.Cantidad");
                    subtotal = rs.getString("detalleventa.Subtotal");
                    Detalle.setText(Detalle.getText() +"\n" + "Nombre: " + nombre + "\n" + "Cantidad: " + cantidad + "\n" + "Subtotal: " + subtotal + "\n");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Venta_na.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }//GEN-LAST:event_jFVentasMouseClicked

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
    private javax.swing.JButton Agregar;
    private javax.swing.JButton Crear;
    private javax.swing.JTextArea Detalle;
    private javax.swing.JButton Existencias;
    private javax.swing.JTable Nuevos;
    private javax.swing.JComboBox jCBServicios;
    private javax.swing.JTable jFVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFCantidad;
    private javax.swing.JTextField jTFFactura;
    // End of variables declaration//GEN-END:variables
}
