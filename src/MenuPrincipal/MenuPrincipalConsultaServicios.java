package MenuPrincipal;
import static MenuPrincipal.ConsultaServicios.getConection;
import java.sql.*;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MenuPrincipalConsultaServicios extends javax.swing.JFrame {
    
    
    public String[][] PRODUCTOS;
    String[][] data = new String[9][9];
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public MenuPrincipalConsultaServicios() {
        initComponents();
        this.setLocationRelativeTo(null);
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
                "ID", "DESCRIPCION", "$ GENERAL"
            }
            ));
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(new javax.swing.table.DefaultTableModel(
            PRODUCTOS,
            new String [] {
                "ID", "DESCRIPCION", "$ GENERAL"
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
                PRODUCTOS = new String[res.getInt("COUNT(ID_PROD)")][3];
            
            ps = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE CAT_PROD='SERVICIOS'");
            res = ps.executeQuery();
            int cero;
            while(res.next())
            {
                cero = res.getInt("ID_PROD");
                PRODUCTOS[i][0] = String.format("%04d", cero);
                PRODUCTOS[i][1] = ("" + res.getString("DES_PROD"));
                PRODUCTOS[i][2] = ("" + res.getString("PRG_PROD"));
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
                PRODUCTOS = new String[res.getInt("COUNT(ID_PROD)")][3];
            
            ps = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE DES_PROD LIKE '%"+buscar+"%' HAVING CAT_PROD='SERVICIOS'");
            res = ps.executeQuery();
            int cero;
            while(res.next())
            {
                cero = res.getInt("ID_PROD");
                PRODUCTOS[i][0] = String.format("%04d", cero);
                PRODUCTOS[i][1] = ("" + res.getString("DES_PROD"));
                PRODUCTOS[i][2] = ("" + res.getString("PRG_PROD"));
                i++;
            }
            
            CargarTabla();
            
        } catch(SQLException e){
            System.out.println(e);
        }
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
        jButton_Aires = new javax.swing.JButton();
        jButton_Ferreteria = new javax.swing.JButton();
        jButton_Servicios = new javax.swing.JButton();
        jButton_Agregar = new javax.swing.JButton();
        jButton_Salir = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Opciones.setBackground(new java.awt.Color(153, 188, 133));
        jPanel_Opciones.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel_Buscar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Buscar.setText("Buscar producto:");

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
        jScrollPane1.setViewportView(tablaPRODUCTOS);

        jButton_Aires.setText("Aires Acondicionados");
        jButton_Aires.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AiresMouseClicked(evt);
            }
        });

        jButton_Ferreteria.setText("Ferretería");
        jButton_Ferreteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_FerreteriaMouseClicked(evt);
            }
        });

        jButton_Servicios.setText("Servicios");
        jButton_Servicios.setEnabled(false);

        jButton_Agregar.setText("Agregar a nota");
        jButton_Agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AgregarMouseClicked(evt);
            }
        });

        jButton_Salir.setText("Salir");
        jButton_Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_SalirMouseClicked(evt);
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
            .addComponent(jScrollPane1)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Aires)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Ferreteria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Servicios)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(jButton_Actualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 435, Short.MAX_VALUE)
                .addComponent(jButton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel_OpcionesLayout.setVerticalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Buscar)
                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Aires)
                    .addComponent(jButton_Ferreteria)
                    .addComponent(jButton_Servicios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Actualizar))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AgregarMouseClicked
        new AgregarProductos().setVisible(true);
    }//GEN-LAST:event_jButton_AgregarMouseClicked

    private void jButton_SalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_SalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_jButton_SalirMouseClicked

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        DefaultTableModel model = (DefaultTableModel)tablaPRODUCTOS.getModel();
        model.setRowCount(0);
        LeerPRODUCTOS();
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void jButton_AiresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AiresMouseClicked
        new MenuPrincipalConsultaAA().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_AiresMouseClicked

    private void jButton_FerreteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_FerreteriaMouseClicked
        new MenuPrincipalConsultaFerreteria().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_FerreteriaMouseClicked

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
            java.util.logging.Logger.getLogger(MenuPrincipalConsultaServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalConsultaServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalConsultaServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalConsultaServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new MenuPrincipalConsultaServicios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Aires;
    private javax.swing.JButton jButton_Ferreteria;
    private javax.swing.JButton jButton_Salir;
    private javax.swing.JButton jButton_Servicios;
    private javax.swing.JLabel jLabel_Buscar;
    private javax.swing.JPanel jPanel_Fondo;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_Buscar;
    public javax.swing.JTable tablaPRODUCTOS;
    // End of variables declaration//GEN-END:variables
}
