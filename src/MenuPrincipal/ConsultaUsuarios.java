package MenuPrincipal;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ConsultaUsuarios extends javax.swing.JFrame {
    
    
    public String[][] USUARIOS;
    String[][] data = new String[9][7];
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public ConsultaUsuarios() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        LeerUSUARIOS();
    }
    
    public static Connection getConection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    private void CargarTabla(){
        tablaUSUARIOS.setModel(new javax.swing.table.DefaultTableModel(
            USUARIOS,
            new String [] {
                "ID", "NOMBRE", "TELÉFONO", "CARGO", "CONTRASEÑA"
            }
            ));
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(new javax.swing.table.DefaultTableModel(
            USUARIOS,
            new String [] {
                "ID", "NOMBRE", "TELÉFONO", "CARGO", "CONTRASEÑA"
            }
            ));
        
        tablaUSUARIOS.setRowSorter(OrdenarTabla);
        
        TableColumn c;
        c = tablaUSUARIOS.getColumnModel().getColumn(0);
        c.setMaxWidth(50); c.setMinWidth(50); c.setResizable(false); 
        c = tablaUSUARIOS.getColumnModel().getColumn(1);
        c.setPreferredWidth(400);
        c = tablaUSUARIOS.getColumnModel().getColumn(2);
        c.setMaxWidth(120); c.setMinWidth(120); c.setResizable(false);
        c = tablaUSUARIOS.getColumnModel().getColumn(3);
        c.setMaxWidth(120); c.setMinWidth(120); c.setResizable(false);
        c = tablaUSUARIOS.getColumnModel().getColumn(4);
        c.setMaxWidth(120); c.setMinWidth(120); c.setResizable(false);
    }
    
    private void LeerUSUARIOS(){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_USU) FROM USUARIOS");
            res = ps.executeQuery();
            
            if(res.next())
                USUARIOS = new String[res.getInt("COUNT(ID_USU)")][5];
            
            ps = con.prepareStatement("SELECT * FROM USUARIOS");
            res = ps.executeQuery();
            int cero;
            while(res.next())
            {
                cero = res.getInt("ID_USU");
                USUARIOS[i][0] = String.format("%02d", cero);
                USUARIOS[i][1] = ("" + res.getString("NOM_USU"));
                USUARIOS[i][2] = ("" + res.getString("TELF_USU"));
                USUARIOS[i][3] = ("" + res.getString("CARGO_USU"));
                USUARIOS[i][4] = ("" + res.getString("PSSWD_USU"));
                i++;
            }
            
            CargarTabla();
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }

        private void BuscarUSUARIOS(String buscar){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_USU) FROM USUARIOS");
            res = ps.executeQuery();
            
            if(res.next())
                USUARIOS = new String[res.getInt("COUNT(ID_USU)")][5];
            
            ps = con.prepareStatement("SELECT * FROM USUARIOS WHERE NOM_USU LIKE '%"+buscar+"%'");
            res = ps.executeQuery();
            int cero;
            while(res.next())
            {
                cero = res.getInt("ID_USU");
                USUARIOS[i][0] = String.format("%02d", cero);
                USUARIOS[i][1] = ("" + res.getString("NOM_USU"));
                USUARIOS[i][2] = ("" + res.getString("TELF_USU"));
                USUARIOS[i][3] = ("" + res.getString("CARGO_USU"));
                USUARIOS[i][4] = ("" + res.getString("PSSWD_USU"));
                i++;
            }
            
            CargarTabla();
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void eliminar(){
        int row = tablaUSUARIOS.getSelectedRow();
        String value = tablaUSUARIOS.getModel().getValueAt(row, 0).toString();
        
        Usuarios ObjetoUsuarios = new Usuarios();
        ObjetoUsuarios.EliminarUsuario(value);
    }
            
    public void actualizar(){
        LeerUSUARIOS();
    }        
    
    public void seleccionar(){
        Usuarios ObjetoUsuarios = new Usuarios();
        ObjetoUsuarios.SeleccionarUsuarios(tablaUSUARIOS, jTextField_ID, jTextArea_NOM, jTextField_TEL, jTextField_CARGO, jTextField_CONTRA);
    }
    
    public void modificar(){
        Usuarios ObjetoUsuarios = new Usuarios();
        ObjetoUsuarios.ModificarUsuario(jTextField_ID, jTextArea_NOM, jTextField_TEL, jTextField_CARGO, jTextField_CONTRA);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_Opciones = new javax.swing.JPanel();
        jLabel_Buscar = new javax.swing.JLabel();
        jTextField_Buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUSUARIOS = new javax.swing.JTable();
        jButton_Agregar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel_ID = new javax.swing.JPanel();
        jTextField_ID = new javax.swing.JTextField();
        jPanel_NOM = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTextArea_NOM = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel_TEL = new javax.swing.JPanel();
        jTextField_TEL = new javax.swing.JTextField();
        jPanel_CARGO = new javax.swing.JPanel();
        jTextField_CARGO = new javax.swing.JTextField();
        jPanel_CONTRA = new javax.swing.JPanel();
        jTextField_CONTRA = new javax.swing.JTextField();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Opciones.setBackground(new java.awt.Color(153, 188, 133));
        jPanel_Opciones.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel_Buscar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Buscar.setText("Buscar usuario:");

        jTextField_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_BuscarKeyReleased(evt);
            }
        });

        tablaUSUARIOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaUSUARIOS.getTableHeader().setReorderingAllowed(false);
        tablaUSUARIOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUSUARIOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUSUARIOS);

        jButton_Agregar.setText("Agregar");
        jButton_Agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AgregarMouseClicked(evt);
            }
        });

        jButton_Eliminar.setText("Eliminar");
        jButton_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EliminarActionPerformed(evt);
            }
        });

        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_OpcionesLayout = new javax.swing.GroupLayout(jPanel_Opciones);
        jPanel_Opciones.setLayout(jPanel_OpcionesLayout);
        jPanel_OpcionesLayout.setHorizontalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addComponent(jButton_Agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Eliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Actualizar))
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addComponent(jLabel_Buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1385, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel_OpcionesLayout.setVerticalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Buscar)
                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Modificar usuario");

        jPanel_ID.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "ID"));

        jTextField_ID.setEditable(false);
        jTextField_ID.setEnabled(false);

        javax.swing.GroupLayout jPanel_IDLayout = new javax.swing.GroupLayout(jPanel_ID);
        jPanel_ID.setLayout(jPanel_IDLayout);
        jPanel_IDLayout.setHorizontalGroup(
            jPanel_IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_ID)
                .addContainerGap())
        );
        jPanel_IDLayout.setVerticalGroup(
            jPanel_IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_NOM.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_NOM.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "NOMBRE"));

        jTextArea_NOM.setColumns(20);
        jTextArea_NOM.setLineWrap(true);
        jTextArea_NOM.setRows(5);
        jScrollPane.setViewportView(jTextArea_NOM);

        javax.swing.GroupLayout jPanel_NOMLayout = new javax.swing.GroupLayout(jPanel_NOM);
        jPanel_NOM.setLayout(jPanel_NOMLayout);
        jPanel_NOMLayout.setHorizontalGroup(
            jPanel_NOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NOMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_NOMLayout.setVerticalGroup(
            jPanel_NOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NOMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel_TEL.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_TEL.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "TELÉFONO"));

        javax.swing.GroupLayout jPanel_TELLayout = new javax.swing.GroupLayout(jPanel_TEL);
        jPanel_TEL.setLayout(jPanel_TELLayout);
        jPanel_TELLayout.setHorizontalGroup(
            jPanel_TELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TELLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_TEL)
                .addContainerGap())
        );
        jPanel_TELLayout.setVerticalGroup(
            jPanel_TELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TELLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_TEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_CARGO.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_CARGO.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "CARGO"));

        javax.swing.GroupLayout jPanel_CARGOLayout = new javax.swing.GroupLayout(jPanel_CARGO);
        jPanel_CARGO.setLayout(jPanel_CARGOLayout);
        jPanel_CARGOLayout.setHorizontalGroup(
            jPanel_CARGOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CARGOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CARGO)
                .addContainerGap())
        );
        jPanel_CARGOLayout.setVerticalGroup(
            jPanel_CARGOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CARGOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CARGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_CONTRA.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_CONTRA.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "CONTRASEÑA"));

        javax.swing.GroupLayout jPanel_CONTRALayout = new javax.swing.GroupLayout(jPanel_CONTRA);
        jPanel_CONTRA.setLayout(jPanel_CONTRALayout);
        jPanel_CONTRALayout.setHorizontalGroup(
            jPanel_CONTRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CONTRALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CONTRA)
                .addContainerGap())
        );
        jPanel_CONTRALayout.setVerticalGroup(
            jPanel_CONTRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CONTRALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CONTRA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 1709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_CONTRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_TEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_CARGO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1))
                            .addComponent(jPanel_NOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FondoLayout.createSequentialGroup()
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_NOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_TEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_CARGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_CONTRA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void jButton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el usuario seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION);
        if(respuesta == 0){
            eliminar();
            actualizar();
        }
    }//GEN-LAST:event_jButton_EliminarActionPerformed

    private void jButton_AgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AgregarMouseClicked
        new AgregarUsuarios().setVisible(true);
    }//GEN-LAST:event_jButton_AgregarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas realizar la modificación?", "Modificar", JOptionPane.YES_NO_OPTION);
        if(respuesta == 0){
            modificar();
            actualizar();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaUSUARIOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUSUARIOSMouseClicked
    seleccionar();
    }//GEN-LAST:event_tablaUSUARIOSMouseClicked

    private void jTextField_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyReleased
    BuscarUSUARIOS(jTextField_Buscar.getText());
    }//GEN-LAST:event_jTextField_BuscarKeyReleased

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
            java.util.logging.Logger.getLogger(ConsultaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Buscar;
    private javax.swing.JPanel jPanel_CARGO;
    private javax.swing.JPanel jPanel_CONTRA;
    private javax.swing.JPanel jPanel_Fondo;
    public javax.swing.JPanel jPanel_ID;
    private javax.swing.JPanel jPanel_NOM;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JPanel jPanel_TEL;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea_NOM;
    private javax.swing.JTextField jTextField_Buscar;
    public javax.swing.JTextField jTextField_CARGO;
    public javax.swing.JTextField jTextField_CONTRA;
    public javax.swing.JTextField jTextField_ID;
    public javax.swing.JTextField jTextField_TEL;
    public javax.swing.JTable tablaUSUARIOS;
    // End of variables declaration//GEN-END:variables
}
