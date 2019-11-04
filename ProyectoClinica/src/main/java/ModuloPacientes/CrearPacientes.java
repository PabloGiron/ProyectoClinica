package ModuloPacientes;

import static Bitacora.AgregarBitacora.crearTransaccion;
import Controladores.PacienteJpaController;
import Controladores.TelefonoJpaController;
import Controladores.TutorpacienteJpaController;
import Entidades.Paciente;
import Entidades.Servicio;
import Entidades.Telefono;
import Entidades.Tutorpaciente;
import Singleton.EntityM;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oem
 */
public class CrearPacientes extends javax.swing.JFrame {

    /** Creates new form CrearPacientes */
    public CrearPacientes() {
        initComponents();
        this.setLocationRelativeTo(null);
        llenarTablaPacientes();
        llenarTablaTutor();
        cargarDatos();
        cargarDatosTutor();
    }
    //DECLARACION DE VARIABLES GLOBALES
    PacienteJpaController controladorPaciente = new PacienteJpaController(EntityM.getEmf());
    TelefonoJpaController controladorTelefono = new TelefonoJpaController(EntityM.getEmf());
    TutorpacienteJpaController controladorTutor = new TutorpacienteJpaController(EntityM.getEmf());
    DefaultTableModel rellenarTablaPaciente;
    DefaultTableModel rellenarTablaTutor;
    Paciente pacienteEditar;
    Telefono telefonoEditar;
    Tutorpaciente tutorEditar;
    private int idTutor;
    private EntityManager em = EntityM.getEm();
    //METODO PARA CARGAR TODOS LOS DATOS DE TABLA PACIENTES
    private void llenarTablaPacientes() {
        try {
            rellenarTablaPaciente = (new DefaultTableModel(
                    null, new String[]{
                        "Id", "Nombre",
                        "Edad","Direccion",
                        "Tutor", "Telefono"}) {
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
            tablaPacientes.setModel(rellenarTablaPaciente);
            tablaPacientes.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaPacientes.getColumnModel().getColumn(0).setMinWidth(0);
            tablaPacientes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaPacientes.getColumnModel().getColumn(0).setResizable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    //METODO PARA CARGAR TODOS LOS DATOS DE TABLA TUTORES
    private void llenarTablaTutor() {
        try {
            rellenarTablaTutor = (new DefaultTableModel(
                    null, new String[]{
                        "Id", "Nombre",
                        "Direccion","Telefono",
                        }) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false,false
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
            tablaTutor2.setModel(rellenarTablaTutor);
            tablaTutor2.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaTutor2.getColumnModel().getColumn(0).setMinWidth(0);
            tablaTutor2.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaTutor2.getColumnModel().getColumn(0).setResizable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    //METODO PARA CARGAR TODOS DATOS EN LA TABLA DE PACIENTES
    private void cargarDatos(){
        Object o[] = null;
        int posicion = 0;
        Query query = em.createNativeQuery("SELECT paciente.id, paciente.Nombre, paciente.Edad, paciente.Direccion, paciente.TutorPaciente_id, telefono.Numero FROM paciente LEFT JOIN telefono ON paciente.id = telefono.Paciente_id;");
        List<Object[]> listaDatos = query.getResultList();
        rellenarTablaPaciente = (DefaultTableModel)tablaPacientes.getModel();
        for(Object[] lista : listaDatos){
            rellenarTablaPaciente.addRow(o);
            rellenarTablaPaciente.setValueAt(lista[0], posicion, 0);
            rellenarTablaPaciente.setValueAt(lista[1], posicion, 1);
            rellenarTablaPaciente.setValueAt(lista[2], posicion, 2);
            rellenarTablaPaciente.setValueAt(lista[3], posicion, 3);
            rellenarTablaPaciente.setValueAt(lista[4], posicion, 4);
            rellenarTablaPaciente.setValueAt(lista[5], posicion, 5);
            posicion++;
        }       
    }
    //METODO PARA CARGAR DATOS  EN LA TABLA TUTORES
    private void cargarDatosTutor(){
        Object o[] = null;
        int posicion = 0;
        Query query = em.createNativeQuery("SELECT tutorpaciente.*, telefono.Numero FROM tutorpaciente LEFT JOIN telefono ON tutorpaciente.id = telefono.TutorPaciente_id;");
        List<Object[]> listaDatos = query.getResultList();
        rellenarTablaTutor = (DefaultTableModel)tablaTutor2.getModel();
        for(Object[] lista : listaDatos){
            rellenarTablaTutor.addRow(o);
            rellenarTablaTutor.setValueAt(lista[0], posicion, 0);
            rellenarTablaTutor.setValueAt(lista[1], posicion, 1);
            rellenarTablaTutor.setValueAt(lista[2], posicion, 2);
            rellenarTablaTutor.setValueAt(lista[3], posicion, 3);
            posicion++;
        }       
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtNit = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPacientes = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnBuscarTutor = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaTutor2 = new javax.swing.JTable();
        txtBusqueda3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTutor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel2.setText("Edad:");

        jLabel3.setText("NIT:");

        jLabel4.setText("Direccion:");

        jLabel5.setText("Telefono:");

        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });

        btnCrear.setText("Ingresar");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Nuevo paciente");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de búsqueda Paciente"));

        tablaPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaPacientesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPacientes);

        btnBuscarPaciente.setText("Buscar");
        btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPacienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarPaciente)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarPaciente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de búsqueda Tutor"));

        btnBuscarTutor.setText("Buscar");
        btnBuscarTutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTutorActionPerformed(evt);
            }
        });

        tablaTutor2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaTutor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaTutor2MousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tablaTutor2);

        txtBusqueda3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusqueda3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtBusqueda3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(btnBuscarTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarTutor))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setText("Tutor:");

        txtTutor.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTutor)
                            .addComponent(txtTelefono)
                            .addComponent(txtDireccion)
                            .addComponent(txtNit)
                            .addComponent(txtEdad)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed
    //BOTON PARA LA CREACION DE UN PACIENTE
    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        if(txtNombre.getText().equals("") || txtDireccion.getText().equals("") || txtTelefono.getText().equals("") ){
            JOptionPane.showMessageDialog(null,"Error: Uno de los campos se encuentran vacíos.");
            crearTransaccion("Error creación de un nuevo paciente, uno o más campos se encontraban vacíos",2);
        }else{
            try{
                Paciente paciente = new Paciente ();
                Telefono telefono = new Telefono();
                paciente.setNombre(txtNombre.getText());
                paciente.setEdad(Integer.parseInt(txtEdad.getText()));
                paciente.setDireccion((txtDireccion.getText()));
                paciente.setNit(txtNit.getText());
                if(!txtTutor.getText().equals(""))
                {
                    TypedQuery<Tutorpaciente> queryTutorPaciente = em.createNamedQuery("Tutorpaciente.findById", Tutorpaciente.class);
                    queryTutorPaciente.setParameter("id", idTutor);
                    Tutorpaciente tutorpaciente = queryTutorPaciente.getSingleResult();
                    paciente.setTutorPacienteid(tutorpaciente);
                }
                telefono.setNumero(txtTelefono.getText());
                telefono.setPacienteid(paciente);
                controladorPaciente.create(paciente);
                controladorTelefono.create(telefono);
                llenarTablaPacientes();
                cargarDatos();
                JOptionPane.showMessageDialog(null,"Se ha creado un nuevo registro.");
                crearTransaccion("Escritura tabla paciente ,"+txtNombre.getText()+","+txtEdad.getText()+","+txtNit.getText()+","+txtDireccion.getText()+","+txtTelefono.getText(), 1);
            }catch(Exception e){ JOptionPane.showMessageDialog(null, e.getMessage());}
            //-------------------------

            this.txtDireccion.setText("");
            this.txtNombre.setText("");
            this.txtEdad.setText("");
            this.txtTelefono.setText("");
            this.txtNit.setText("");
            this.txtTutor.setText("");

        }
    }//GEN-LAST:event_btnCrearActionPerformed
    //BOTON QUE REALIZA BUSQUEDA PERSONALIZADA
    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        // TODO add your handling code here:
        llenarTablaPacientes();
        Object o[] = null;
        int posicion = 0;
        Query query = em.createNativeQuery("SELECT paciente.id, paciente.Nombre, paciente.Edad, paciente.Direccion, paciente.TutorPaciente_id, telefono.Numero FROM paciente LEFT JOIN telefono ON paciente.id = telefono.Paciente_id WHERE paciente.Nombre LIKE '%"+txtBusqueda.getText()+"%';");
        List<Object[]> listaDatos = query.getResultList();
        rellenarTablaPaciente = (DefaultTableModel)tablaPacientes.getModel();
        for(Object[] lista : listaDatos){
            rellenarTablaPaciente.addRow(o);
            rellenarTablaPaciente.setValueAt(lista[0], posicion, 0);
            rellenarTablaPaciente.setValueAt(lista[1], posicion, 1);
            rellenarTablaPaciente.setValueAt(lista[2], posicion, 2);
            rellenarTablaPaciente.setValueAt(lista[3], posicion, 3);
            rellenarTablaPaciente.setValueAt(lista[4], posicion, 4);
            rellenarTablaPaciente.setValueAt(lista[5], posicion, 5);
            posicion++;
        }       
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed
    //BOTON QUE REALIZA LA MODIFICACION DE UN REGISTRO
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        controladorPaciente.actualizarPaciente(txtNombre.getText(), txtEdad.getText(), txtDireccion.getText(), txtNit.getText(), txtTelefono.getText(), pacienteEditar.getId());
        llenarTablaPacientes();
        cargarDatos();
        crearTransaccion("Modificación tabla pacientes, Nombre antiguo: "+nombre+" Nombre nuevo:"+txtNombre.getText()+",Edad antigua: "+edad+" edad nueva: "+txtEdad.getText()+", direccion antigua: "+direccion+" direccion nueva: "+txtDireccion.getText()+", telefono antiguo: "+telefono+"telefono nuevo: "+txtTelefono.getText(), 1);
        JOptionPane.showMessageDialog(null,"Se ha actulizado un registro exitosamente.");

        this.txtDireccion.setText("");
        this.txtNombre.setText("");
        this.txtTelefono.setText("");
        this.txtNit.setText("");
        this.txtEdad.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed
    public String nombre,edad,direccion,telefono;
    //CUANDO SE PRESIONES SOBRE UN PACIENTE EN LA TABLA PODRA SER MODIFICADO
    private void tablaPacientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPacientesMousePressed
        // TODO add your handling code here:
        if(evt.getClickCount() > 1){
            int fila = tablaPacientes.getSelectedRow();
            if(fila >= 0){
                try {
                    pacienteEditar = new Paciente(Integer.parseInt(tablaPacientes.getValueAt(fila, 0).toString()));
                    //telefonoEditar = new Telefono(tutorEditar.getId());
                    System.out.println(Integer.parseInt(tablaPacientes.getValueAt(fila, 0).toString()));
                    txtNombre.setText(tablaPacientes.getValueAt(fila, 1).toString());
                    nombre = txtNombre.getText();
                    txtEdad.setText(tablaPacientes.getValueAt(fila, 2).toString());
                    edad = txtEdad.getText();
                    txtDireccion.setText(tablaPacientes.getValueAt(fila, 3).toString());
                    direccion = txtDireccion.getText();
                    txtTelefono.setText(tablaPacientes.getValueAt(fila, 5).toString());
                    telefono = txtTelefono.getText();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna fila.");
            }
        }
    }//GEN-LAST:event_tablaPacientesMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ClientePacientes clienteP = new ClientePacientes();
        clienteP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing
    //BOTON QUE REALIZA LA BUSQUEDA PERSONALIZADA
    private void btnBuscarTutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTutorActionPerformed
        // TODO add your handling code here:
        llenarTablaTutor();
        int posicion = 0;
        Object o[] = null;
        Query query = em.createNativeQuery("SELECT tutorpaciente.*, telefono.Numero FROM tutorpaciente LEFT JOIN telefono ON tutorpaciente.id = telefono.TutorPaciente_id WHERE tutorpaciente.Nombre LIKE '%"+txtBusqueda.getText()+"%';");
        List<Object[]> listaDatos = query.getResultList();
        rellenarTablaTutor = (DefaultTableModel)tablaTutor2.getModel();
        for(Object[] lista : listaDatos){
            rellenarTablaTutor.addRow(o);
            rellenarTablaTutor.setValueAt(lista[0], posicion, 0);
            rellenarTablaTutor.setValueAt(lista[1], posicion, 1);
            rellenarTablaTutor.setValueAt(lista[2], posicion, 2);
            rellenarTablaTutor.setValueAt(lista[3], posicion, 3);
            posicion++;
        }
    }//GEN-LAST:event_btnBuscarTutorActionPerformed
    //AL PRESIONAR UN TUTOR DE LA TABLA PODRA SER MODIFICADO
    private void tablaTutor2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTutor2MousePressed
        // TODO add your handling code here:
        if(evt.getClickCount() > 1){
            int fila = tablaTutor2.getSelectedRow();
            if(fila >= 0){
                try {
                    txtTutor.setText(tablaTutor2.getValueAt(fila, 1).toString());
                    idTutor = Integer.parseInt(tablaTutor2.getValueAt(fila, 0).toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null,"No ha seleccionado ninguna fila.");
            }
        }
    }//GEN-LAST:event_tablaTutor2MousePressed

    private void txtBusqueda3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusqueda3ActionPerformed

    }//GEN-LAST:event_txtBusqueda3ActionPerformed

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
            java.util.logging.Logger.getLogger(CrearPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearPacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarTutor;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tablaPacientes;
    private javax.swing.JTable tablaTutor2;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtBusqueda3;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTutor;
    // End of variables declaration//GEN-END:variables

}
