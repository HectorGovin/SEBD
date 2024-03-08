package MenuPrincipal;
import java.sql.*;

public class ConsultasUsuarios extends javax.swing.JFrame {

    public String[][] USUARIOS;
    String[][] data = new String[6][6];
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public ConsultasUsuarios() {
        initComponents();
        this.setLocationRelativeTo(null);
        LeerUSUARIO();
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
    
    private void LeerUSUARIO(){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_USU) FROM USUARIOS");
            res = ps.executeQuery();
            
            if(res.next())
                USUARIOS = new String[res.getInt("COUNT(ID_USU)")][6];
            
            ps = con.prepareStatement("SELECT * FROM USUARIOS");
            res = ps.executeQuery();
            
            while(res.next())
            {
                USUARIOS[i][0] = ("" + res.getInt("ID_USU"));
                USUARIOS[i][1] = ("" + res.getString("NOM_USU"));
                USUARIOS[i][2] = ("" + res.getString("TELF_USU"));
                USUARIOS[i][3] = ("" + res.getString("CARGO_USU"));
                i++;
            }
            
            tablaUSUARIOS.setModel(new javax.swing.table.DefaultTableModel(
            USUARIOS,
            new String [] {
                "ID", "NOMBRE", "TELEFONO", "CARGO"
            }
            ));
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_Buscar = new javax.swing.JPanel();
        jLabel_Buscar = new javax.swing.JLabel();
        jTextField_Buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUSUARIOS = new javax.swing.JTable();
        jButton_Agregar = new javax.swing.JButton();
        jButton_Editar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Buscar.setBackground(new java.awt.Color(153, 188, 133));
        jPanel_Buscar.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel_Buscar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Buscar.setText("Buscar usuario:");

        tablaUSUARIOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "TELEFONO", "CARGO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaUSUARIOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaUSUARIOSKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUSUARIOS);

        jButton_Agregar.setText("Agregar");

        jButton_Editar.setText("Editar");

        jButton_Eliminar.setText("Eliminar");

        javax.swing.GroupLayout jPanel_BuscarLayout = new javax.swing.GroupLayout(jPanel_Buscar);
        jPanel_Buscar.setLayout(jPanel_BuscarLayout);
        jPanel_BuscarLayout.setHorizontalGroup(
            jPanel_BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel_BuscarLayout.createSequentialGroup()
                .addGroup(jPanel_BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_BuscarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_Buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_BuscarLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Agregar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Editar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Eliminar)))
                .addContainerGap(679, Short.MAX_VALUE))
        );
        jPanel_BuscarLayout.setVerticalGroup(
            jPanel_BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Buscar)
                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addGroup(jPanel_BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
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

    private void tablaUSUARIOSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaUSUARIOSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaUSUARIOSKeyPressed

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
            java.util.logging.Logger.getLogger(ConsultasUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultasUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultasUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultasUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultasUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Editar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JLabel jLabel_Buscar;
    private javax.swing.JPanel jPanel_Buscar;
    private javax.swing.JPanel jPanel_Fondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_Buscar;
    private javax.swing.JTable tablaUSUARIOS;
    // End of variables declaration//GEN-END:variables
}
