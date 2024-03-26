package MenuPrincipal;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class MenuPrincipalCobroRapido extends javax.swing.JFrame {
    
    public JTable getTablaPRODUCTOS(){
        return tablaPRODUCTOS;
    }
    
    private AgregarProductos agregarProductos;
    
    Clientes cl = new Clientes();
    MenuPrincipal mp = new MenuPrincipal();
    Reportes r = new Reportes();
    Partidas p = new Partidas();
    
    /*public void actualizar(){
        DefaultTableModel model = (DefaultTableModel)tablaPRODUCTOS.getModel();
        model.setRowCount(0);
    }*/
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    

    public MenuPrincipalCobroRapido() {
        initComponents();
        this.setLocationRelativeTo(null);
        CargarTabla();
    }
    
    /*public static Connection getConection(){
        
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }*/
    
    private void CargarTabla(){
        mp.EnviarPRODUCTOS();
        tablaPRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
        mp.PRODUCTOStab,
        new String [] {
            "DESCRIPCION", "CANTIDAD", "COBRO"
        }
        ));
        /*TableColumn c;
        c = tablaPRODUCTOS.getColumnModel().getColumn(0);
        c.setMaxWidth(120); c.setMinWidth(120); c.setResizable(false); 
        c = tablaPRODUCTOS.getColumnModel().getColumn(1);
        c.setMaxWidth(70); c.setMinWidth(70); c.setResizable(false); 
        c = tablaPRODUCTOS.getColumnModel().getColumn(2);
        c.setPreferredWidth(400);
        c = tablaPRODUCTOS.getColumnModel().getColumn(3);
        c.setMaxWidth(80); c.setMinWidth(80); c.setResizable(false); 
        for(int cx = 4; cx<8;cx++){
            c = tablaPRODUCTOS.getColumnModel().getColumn(cx);
            c.setMaxWidth(80); c.setMinWidth(80); c.setResizable(false); 
        }*/
        jLabel_Folio.setText(mp.jLabel_Folio.getText());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_Barra = new javax.swing.JPanel();
        jLabel_RegistroProd = new javax.swing.JLabel();
        jLabel_Folio_S = new javax.swing.JLabel();
        jLabel_Folio = new javax.swing.JLabel();
        jPanel_Opciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPRODUCTOS = new javax.swing.JTable();
        jButton_Cancelar = new javax.swing.JButton();
        jButton_Confirmar = new javax.swing.JButton();
        jComboBox_FP = new javax.swing.JComboBox<>();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Barra.setBackground(new java.awt.Color(153, 188, 133));

        jLabel_RegistroProd.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_RegistroProd.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setText("Cobro Rápido");

        jLabel_Folio_S.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Folio_S.setText("FOLIO:");

        jLabel_Folio.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Folio.setText("0001");

        javax.swing.GroupLayout jPanel_BarraLayout = new javax.swing.GroupLayout(jPanel_Barra);
        jPanel_Barra.setLayout(jPanel_BarraLayout);
        jPanel_BarraLayout.setHorizontalGroup(
            jPanel_BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BarraLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel_RegistroProd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_Folio_S, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Folio)
                .addGap(16, 16, 16))
        );
        jPanel_BarraLayout.setVerticalGroup(
            jPanel_BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BarraLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Folio_S, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_Folio))
                    .addComponent(jLabel_RegistroProd))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel_Opciones.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_Opciones.setPreferredSize(new java.awt.Dimension(1280, 720));

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

        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CancelarMouseClicked(evt);
            }
        });

        jButton_Confirmar.setText("Confirmar");
        jButton_Confirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ConfirmarMouseClicked(evt);
            }
        });

        jComboBox_FP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TARJETA", "TRANSFERENCIA" }));
        jComboBox_FP.setSelectedIndex(-1);

        javax.swing.GroupLayout jPanel_OpcionesLayout = new javax.swing.GroupLayout(jPanel_Opciones);
        jPanel_Opciones.setLayout(jPanel_OpcionesLayout);
        jPanel_OpcionesLayout.setHorizontalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_FP, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel_OpcionesLayout.setVerticalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_FP, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addComponent(jPanel_Barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
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

    private void jButton_CancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CancelarMouseClicked
    this.dispose();
    }//GEN-LAST:event_jButton_CancelarMouseClicked

    private void tablaPRODUCTOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPRODUCTOSMouseClicked
     
    }//GEN-LAST:event_tablaPRODUCTOSMouseClicked

    private void jButton_ConfirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ConfirmarMouseClicked
        r.SetDatos(1, 1, mp.jLabel_Folio.getText(), ""+jComboBox_FP.getSelectedItem());
        mp.EnviarPRODUCTOSaPartidas();
        System.out.println("\n\n");
        System.out.println("\nSí pasó por aqui");
        for(int i = 0; mp.PRODUCTOS[i][9] != null; i++){
            p.SetDatos(""+mp.jLabel_Folio.getText(), mp.PRODUCTOS[i][9], mp.PRODUCTOS[i][5], mp.PRODUCTOS[i][7]);
            //p.SetDatos(""+r.getNOTA_REP(), mp.PRODUCTOS[i][9], mp.PRODUCTOS[i][7], Double.parseDouble("5"), Double.parseDouble("5"), Double.parseDouble(mp.PRODUCTOS[i][8]));
            System.out.println("\nRegistro no. "+i+":\n\nID de REPORTE: "+r.getNOTA_REP()+"   ID de PRODUCTO: "+mp.PRODUCTOS[i][9]+"   Cantidad: "+p.getCAN_PAR()+"   SubTotal: "+p.getSUBT_PAR()+"   IVA: "+p.getIVA_PAR()+"   Total: "+p.getTOT_PAR());
            p.SubirDatosPartidas();}
        JOptionPane.showMessageDialog(null, "Registro añadido con éxito");
        dispose();
    }//GEN-LAST:event_jButton_ConfirmarMouseClicked

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
            java.util.logging.Logger.getLogger(MenuPrincipalCobroRapido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalCobroRapido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalCobroRapido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalCobroRapido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalCobroRapido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Confirmar;
    private javax.swing.JComboBox<String> jComboBox_FP;
    private javax.swing.JLabel jLabel_Folio;
    private javax.swing.JLabel jLabel_Folio_S;
    private javax.swing.JLabel jLabel_RegistroProd;
    private javax.swing.JPanel jPanel_Barra;
    private javax.swing.JPanel jPanel_Fondo;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaPRODUCTOS;
    // End of variables declaration//GEN-END:variables
}
