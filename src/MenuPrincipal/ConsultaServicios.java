package MenuPrincipal;
import static MenuPrincipal.MenuPrincipalConsultaServicios.getConection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ConsultaServicios extends javax.swing.JFrame {
    
    
    public String[][] PRODUCTOS;
    String[][] data = new String[9][7];
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public ConsultaServicios() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        LeerPRODUCTOS();
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
        tablaPRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
            PRODUCTOS,
            new String [] {
                "ID", "DESCRIPCION", "CATEGORIA", "$ GENERAL"
            }
            ));
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(new javax.swing.table.DefaultTableModel(
            PRODUCTOS,
            new String [] {
                "ID", "DESCRIPCION", "CATEGORIA", "$ GENERAL"
            }
            ));
        
        tablaPRODUCTOS.setRowSorter(OrdenarTabla);
        
        TableColumn c;
        c = tablaPRODUCTOS.getColumnModel().getColumn(0);
        c.setMaxWidth(50); c.setMinWidth(50); c.setResizable(false); 
        c = tablaPRODUCTOS.getColumnModel().getColumn(1);
        c.setPreferredWidth(400);
        c = tablaPRODUCTOS.getColumnModel().getColumn(2);
        c.setMaxWidth(90); c.setMinWidth(90); c.setResizable(false);
        c = tablaPRODUCTOS.getColumnModel().getColumn(3);
        c.setMaxWidth(90); c.setMinWidth(90); c.setResizable(false);
    }
    
    private void LeerPRODUCTOS(){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_PROD) FROM PRODUCTOS WHERE CAT_PROD='SERVICIOS'");
            res = ps.executeQuery();
            
            if(res.next())
                PRODUCTOS = new String[res.getInt("COUNT(ID_PROD)")][4];
            
            ps = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE CAT_PROD='SERVICIOS'");
            res = ps.executeQuery();
            int cero;
            while(res.next())
            {
                cero = res.getInt("ID_PROD");
                PRODUCTOS[i][0] = String.format("%04d", cero);
                PRODUCTOS[i][1] = ("" + res.getString("DES_PROD"));
                PRODUCTOS[i][2] = ("" + res.getString("CAT_PROD"));
                PRODUCTOS[i][3] = ("" + res.getString("PRG_PROD"));
                i++;
            }
            
            CargarTabla();
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    
        private void BuscarSERVICIOS(String buscar){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_PROD) FROM PRODUCTOS WHERE CAT_PROD='SERVICIOS'");
            res = ps.executeQuery();
            
            if(res.next())
                PRODUCTOS = new String[res.getInt("COUNT(ID_PROD)")][4];
            
            ps = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE DES_PROD LIKE '%"+buscar+"%' HAVING CAT_PROD='SERVICIOS'");
            res = ps.executeQuery();
            int cero;
            while(res.next())
            {
                cero = res.getInt("ID_PROD");
                PRODUCTOS[i][0] = String.format("%04d", cero);
                PRODUCTOS[i][1] = ("" + res.getString("DES_PROD"));
                PRODUCTOS[i][2] = ("" + res.getString("CAT_PROD"));
                PRODUCTOS[i][3] = ("" + res.getString("PRG_PROD"));
                i++;
            }
            
            CargarTabla();
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void eliminar(){
        int row = tablaPRODUCTOS.getSelectedRow();
        String value = tablaPRODUCTOS.getModel().getValueAt(row, 0).toString();
        
        Productos ObjetoProductos = new Productos();
        ObjetoProductos.EliminarProducto(value);
    }
            
    public void actualizar(){
        LeerPRODUCTOS();
    }        
    
    public void seleccionar(){
        Productos objetoProductos = new Productos();
        objetoProductos.SeleccionarServicios(tablaPRODUCTOS, jTextField_ID3, jTextArea, jComboBox_Categoria, jTextField_PG2);
    }
    
    public void modificar(){
        Productos objetoProductos = new Productos();
        objetoProductos.ModificarServicio(jTextField_ID3, jComboBox_Categoria, jTextArea, jTextField_PG2);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_Opciones = new javax.swing.JPanel();
        jLabel_Buscar = new javax.swing.JLabel();
        jTextField_Buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPRODUCTOS = new javax.swing.JTable();
        jButton_Productos = new javax.swing.JButton();
        jButton_Servicios = new javax.swing.JButton();
        jButton_Agregar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel_ID3 = new javax.swing.JPanel();
        jTextField_ID3 = new javax.swing.JTextField();
        jPanel_Categoria = new javax.swing.JPanel();
        jComboBox_Categoria = new javax.swing.JComboBox<>();
        jPanel_DES = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel_PG2 = new javax.swing.JPanel();
        jTextField_PG2 = new javax.swing.JTextField();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Opciones.setBackground(new java.awt.Color(153, 188, 133));
        jPanel_Opciones.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel_Buscar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Buscar.setText("Buscar servicio:");

        jTextField_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_BuscarKeyReleased(evt);
            }
        });

        tablaPRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPRODUCTOS.getTableHeader().setReorderingAllowed(false);
        tablaPRODUCTOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPRODUCTOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPRODUCTOS);

        jButton_Productos.setText("Productos");
        jButton_Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ProductosMouseClicked(evt);
            }
        });

        jButton_Servicios.setText("Servicios");
        jButton_Servicios.setEnabled(false);

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
                        .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Productos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Servicios)))
                .addContainerGap(1172, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel_OpcionesLayout.setVerticalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Buscar)
                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Productos)
                    .addComponent(jButton_Servicios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Modificar servicio");

        jPanel_ID3.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "ID"));

        jTextField_ID3.setEditable(false);
        jTextField_ID3.setEnabled(false);

        javax.swing.GroupLayout jPanel_ID3Layout = new javax.swing.GroupLayout(jPanel_ID3);
        jPanel_ID3.setLayout(jPanel_ID3Layout);
        jPanel_ID3Layout.setHorizontalGroup(
            jPanel_ID3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_ID3)
                .addContainerGap())
        );
        jPanel_ID3Layout.setVerticalGroup(
            jPanel_ID3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_ID3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Categoria.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_Categoria.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "CATEGORÍA"));

        jComboBox_Categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SERVICIOS" }));
        jComboBox_Categoria.setEnabled(false);

        javax.swing.GroupLayout jPanel_CategoriaLayout = new javax.swing.GroupLayout(jPanel_Categoria);
        jPanel_Categoria.setLayout(jPanel_CategoriaLayout);
        jPanel_CategoriaLayout.setHorizontalGroup(
            jPanel_CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_CategoriaLayout.setVerticalGroup(
            jPanel_CategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_DES.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_DES.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "DESCRIPCIÓN"));

        jTextArea.setColumns(20);
        jTextArea.setLineWrap(true);
        jTextArea.setRows(5);
        jScrollPane.setViewportView(jTextArea);

        javax.swing.GroupLayout jPanel_DESLayout = new javax.swing.GroupLayout(jPanel_DES);
        jPanel_DES.setLayout(jPanel_DESLayout);
        jPanel_DESLayout.setHorizontalGroup(
            jPanel_DESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_DESLayout.setVerticalGroup(
            jPanel_DESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DESLayout.createSequentialGroup()
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

        jPanel_PG2.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_PG2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "PRECIO GENERAL"));

        javax.swing.GroupLayout jPanel_PG2Layout = new javax.swing.GroupLayout(jPanel_PG2);
        jPanel_PG2.setLayout(jPanel_PG2Layout);
        jPanel_PG2Layout.setHorizontalGroup(
            jPanel_PG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PG2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_PG2)
                .addContainerGap())
        );
        jPanel_PG2Layout.setVerticalGroup(
            jPanel_PG2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PG2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_PG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_ID3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_DES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addComponent(jPanel_PG2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_ID3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_DES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_PG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el servicio seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION);
        if(respuesta == 0){
            eliminar();
            actualizar();
        }
    }//GEN-LAST:event_jButton_EliminarActionPerformed

    private void jButton_AgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AgregarMouseClicked
        new AgregarServicios().setVisible(true);
    }//GEN-LAST:event_jButton_AgregarMouseClicked

    private void jButton_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ProductosMouseClicked
        new ConsultaAA().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_ProductosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas realizar la modificación?", "Modificar", JOptionPane.YES_NO_OPTION);
        if(respuesta == 0){
            modificar();
            actualizar();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaPRODUCTOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPRODUCTOSMouseClicked
    seleccionar();
    }//GEN-LAST:event_tablaPRODUCTOSMouseClicked

    private void jTextField_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyReleased
    BuscarSERVICIOS(jTextField_Buscar.getText());
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
            java.util.logging.Logger.getLogger(ConsultaServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ConsultaServicios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JButton jButton_Productos;
    private javax.swing.JButton jButton_Servicios;
    public javax.swing.JComboBox<String> jComboBox_Categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Buscar;
    public javax.swing.JPanel jPanel_Categoria;
    private javax.swing.JPanel jPanel_DES;
    private javax.swing.JPanel jPanel_Fondo;
    public javax.swing.JPanel jPanel_ID3;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JPanel jPanel_PG2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextField_Buscar;
    public javax.swing.JTextField jTextField_ID3;
    public javax.swing.JTextField jTextField_PG2;
    public javax.swing.JTable tablaPRODUCTOS;
    // End of variables declaration//GEN-END:variables
}
