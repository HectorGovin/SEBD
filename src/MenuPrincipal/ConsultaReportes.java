package MenuPrincipal;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ConsultaReportes extends javax.swing.JFrame {
    
    
    public String[][] PRODUCTOS;
    String[][] data = new String[10][7];
    
    public String[][] REPORTES;
    public String[][] PARTIDAS;
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public ConsultaReportes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        CargarDatos();
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
    
    private void CargarDatos(){
        CargarTabla();
        CargarFechas();
    }
    
    private void CargarPartidas(){
        try{
            Connection con = getConection();
            
            int fila = tablaPRODUCTOS.getSelectedRow();
            
            int nota = Integer.parseInt(REPORTES[fila][0]);
            
            String Note = REPORTES[fila][0];
            System.out.println(Note);
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(REPORTES.ID_REP) FROM PRODUCTOS JOIN PARTIDAS ON PRODUCTOS.ID_PROD = PARTIDAS.ID_PROD JOIN REPORTES ON PARTIDAS.ID_REP = REPORTES.ID_REP WHERE REPORTES.NOTA_REP='"+Note+"'");
            res = ps.executeQuery();
            
            if(res.next())
                PARTIDAS = new String[res.getInt("COUNT(REPORTES.ID_REP)")][10];
            
            ps = con.prepareStatement("SELECT REPORTES.NOTA_REP, PRODUCTOS.DES_PROD, PARTIDAS.CAN_PAR, PARTIDAS.SUBT_PAR, PARTIDAS.IVA_PAR, PARTIDAS.TOT_PAR FROM PRODUCTOS JOIN PARTIDAS ON PRODUCTOS.ID_PROD = PARTIDAS.ID_PROD JOIN REPORTES ON PARTIDAS.ID_REP = REPORTES.ID_REP WHERE REPORTES.NOTA_REP='"+Note+"'");
            res = ps.executeQuery();
            int i = 0;
            while(res.next())
            {
                PARTIDAS[i][0] = (""+(i+1));
                PARTIDAS[i][1] = ("" + res.getString("DES_PROD"));
                PARTIDAS[i][2] = ("" + res.getString("CAN_PAR"));
                PARTIDAS[i][3] = ("" + res.getString("SUBT_PAR"));
                PARTIDAS[i][4] = ("" + res.getString("IVA_PAR"));
                PARTIDAS[i][5] = ("" + res.getString("TOT_PAR"));
                i++;
            }
            jLabel_Folio.setText(Note);
            CtablaREPORTES();
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    private void CtablaREPORTES(){
        tablaREPORTES.setModel(new javax.swing.table.DefaultTableModel(
            PARTIDAS,
            new String [] {
                "NO. DE PRODUCTO", "PRODUCTO", "CANTIDAD", "SUB TOTAL", "IVA", "TOTAL"
            }
            ));
    }
    
    private void CargarFechas(){
        try{
            Connection con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT DISTINCT DATE_REP FROM `reportes` WHERE 1;");
            res = ps.executeQuery();
            
            while(res.next())
                {jComboBox_Fechas.addItem("" + res.getString("DATE_REP"));}
            
            jComboBox_Fechas.setSelectedIndex(-1);
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    private void LeerFechaSeleccionada(){
        try{
            Connection con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            String Date = ""+jComboBox_Fechas.getSelectedItem();
            System.out.println(Date);
            
            ps = con.prepareStatement("SELECT COUNT(DISTINCT ID_REP) FROM REPORTES WHERE DATE_REP='"+Date+"'");
            res = ps.executeQuery();
            
            if(res.next())
                REPORTES = new String[res.getInt("COUNT(DISTINCT ID_REP)")][10];
            
            ps = con.prepareStatement("SELECT DISTINCT REPORTES.NOTA_REP, USUARIOS.NOM_USU, CLIENTES.NOM_CLIE, REPORTES.FP_REP, REPORTES.TOTAL_REP FROM REPORTES JOIN USUARIOS ON REPORTES.ID_USU = USUARIOS.ID_USU JOIN CLIENTES ON REPORTES.ID_CLIE = CLIENTES.ID_CLIE WHERE REPORTES.DATE_REP='"+Date+"'");
            res = ps.executeQuery();
            int i = 0;
            while(res.next())
            {
                REPORTES[i][0] = ("" + res.getString("NOTA_REP"));
                REPORTES[i][1] = ("" + res.getString("NOM_USU"));
                REPORTES[i][2] = ("" + res.getString("NOM_CLIE"));
                REPORTES[i][3] = ("" + res.getString("FP_REP"));
                REPORTES[i][4] = ("" + res.getString("TOTAL_REP"));
                i++;
            }
        CargarTabla();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    private void CargarTabla(){
        tablaPRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
            REPORTES,
            new String [] {
                "NOTA", "EMPLEADO", "CLIENTE", "FORMA DE PAGO", "TOTAL"
            }
            ));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_Opciones = new javax.swing.JPanel();
        jLabel_Buscar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPRODUCTOS = new javax.swing.JTable();
        jButton_Busqueda = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaREPORTES = new javax.swing.JTable();
        jComboBox_Fechas = new javax.swing.JComboBox<>();
        jLabel_Buscar1 = new javax.swing.JLabel();
        jLabel_Folio = new javax.swing.JLabel();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Opciones.setBackground(new java.awt.Color(153, 188, 133));
        jPanel_Opciones.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel_Buscar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Buscar.setText("Seleccionar Fecha:");

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

        jButton_Busqueda.setText("Busqueda");
        jButton_Busqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_BusquedaMouseClicked(evt);
            }
        });

        tablaREPORTES.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaREPORTES.getTableHeader().setReorderingAllowed(false);
        tablaREPORTES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaREPORTESMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaREPORTES);

        jLabel_Buscar1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_Buscar1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Buscar1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Buscar1.setText("Detalles de FOLIO");
        jLabel_Buscar1.setToolTipText("");

        jLabel_Folio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_Folio.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Folio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Folio.setText("0000");
        jLabel_Folio.setToolTipText("");

        javax.swing.GroupLayout jPanel_OpcionesLayout = new javax.swing.GroupLayout(jPanel_Opciones);
        jPanel_Opciones.setLayout(jPanel_OpcionesLayout);
        jPanel_OpcionesLayout.setHorizontalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_Buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Fechas, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Busqueda))
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel_Buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Folio, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_OpcionesLayout.setVerticalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Buscar)
                    .addComponent(jButton_Busqueda)
                    .addComponent(jComboBox_Fechas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Buscar1)
                    .addComponent(jLabel_Folio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
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

    private void tablaPRODUCTOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPRODUCTOSMouseClicked
        CargarPartidas();
    }//GEN-LAST:event_tablaPRODUCTOSMouseClicked

    private void tablaREPORTESMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaREPORTESMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaREPORTESMouseClicked

    private void jButton_BusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_BusquedaMouseClicked
        LeerFechaSeleccionada();
    }//GEN-LAST:event_jButton_BusquedaMouseClicked

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
            java.util.logging.Logger.getLogger(ConsultaReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ConsultaReportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Busqueda;
    private javax.swing.JComboBox<String> jComboBox_Fechas;
    private javax.swing.JLabel jLabel_Buscar;
    private javax.swing.JLabel jLabel_Buscar1;
    private javax.swing.JLabel jLabel_Folio;
    private javax.swing.JPanel jPanel_Fondo;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tablaPRODUCTOS;
    public javax.swing.JTable tablaREPORTES;
    // End of variables declaration//GEN-END:variables
}
