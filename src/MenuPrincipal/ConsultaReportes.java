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
    
    private void CargarFechas(){
        try{
            Connection con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            /*ps = con.prepareStatement("SELECT COUNT(ID_PROD) FROM PRODUCTOS WHERE CAT_PROD='AA' OR CAT_PROD='FERRETERIA'");
            res = ps.executeQuery();
            
            if(res.next())
                PRODUCTOS = new String[res.getInt("COUNT(ID_PROD)")][10];*/
            
            ps = con.prepareStatement("SELECT DISTINCT DATE_REP FROM `reportes` WHERE 1;");
            res = ps.executeQuery();
            
            while(res.next())
                {jComboBox_Fechas.addItem("" + res.getString("DATE_REP"));}
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    private void LeerFechaSeleccionada(){
        try{
            Connection con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            String FechaSeleccionada = ""+jComboBox_Fechas.getSelectedItem();
            System.out.println(FechaSeleccionada);
            
            ps = con.prepareStatement("SELECT COUNT(ID_REP) FROM REPORTES WHERE DATE_REP='"+FechaSeleccionada+"'");
            res = ps.executeQuery();
            
            if(res.next())
                REPORTES = new String[res.getInt("COUNT(ID_REP)")][10];
            
            ps = con.prepareStatement("SELECT r.ID_REP, u.NOM_USU, c.NOM_CLIE, r.NOTA_REP, r.FP_REP, r.TOTAL_REP FROM `reportes` r LEFT JOIN `usuarios` u on r.ID_USU = u.ID_USU LEFT JOIN `Clientes` c ON r.ID_CLIE = c.ID_CLIE WHERE DATE_REP='"+FechaSeleccionada+"'");
            res = ps.executeQuery();
            int i = 0;
            while(res.next())
            {
                REPORTES[i][0] = ("" + res.getString("r.ID_REP"));
                REPORTES[i][1] = ("" + res.getString("NOM_USU"));
                REPORTES[i][2] = ("" + res.getString("NOM_CLIE"));
                REPORTES[i][3] = ("" + res.getString("NOTA_REP"));
                REPORTES[i][4] = ("" + res.getString("FP_REP"));
                REPORTES[i][5] = ("" + res.getString("TOTAL_REP"));
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
                "ID", "EMPLEADO", "CLIENTE", "NOTA", "FORMA DE PAGO", "TOTAL"
            }
            ));
    }
    
    /*
    private void CargarTabla(){
        
        tablaPRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
            PRODUCTOS,
            new String [] {
                "ID", "COD DE BARRAS", "SERIE", "CATEGORIA", "DESCRIPCION", "$ GENERAL", "$ TECNICO", "U.M", "DIM", "STOCK"
            }
            ));
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(new javax.swing.table.DefaultTableModel(
            PRODUCTOS,
            new String [] {
                "ID", "COD DE BARRAS", "SERIE", "CATEGORIA", "DESCRIPCION", "$ GENERAL", "$ TECNICO", "U.M" ,"DIM", "STOCK"
            }
            ));
        
        tablaPRODUCTOS.setRowSorter(OrdenarTabla);
        
        TableColumn c;
               c = tablaPRODUCTOS.getColumnModel().getColumn(0);
        c.setMaxWidth(50); c.setMinWidth(50); c.setResizable(false); 
        c = tablaPRODUCTOS.getColumnModel().getColumn(1);
        c.setMaxWidth(120); c.setMinWidth(120); c.setResizable(false); 
        c = tablaPRODUCTOS.getColumnModel().getColumn(2);
        c.setMaxWidth(70); c.setMinWidth(70); c.setResizable(false); 
        c = tablaPRODUCTOS.getColumnModel().getColumn(3);
        c.setMaxWidth(110); c.setMinWidth(110); c.setResizable(false); 
        c = tablaPRODUCTOS.getColumnModel().getColumn(4);
        c.setPreferredWidth(500);
        for(int cx = 5; cx<10;cx++){
            c = tablaPRODUCTOS.getColumnModel().getColumn(cx);
            c.setMaxWidth(80); c.setMinWidth(80); c.setResizable(false); 
        }
    }
    
    private void LeerPRODUCTOS(){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_PROD) FROM PRODUCTOS WHERE CAT_PROD='AA' OR CAT_PROD='FERRETERIA'");
            res = ps.executeQuery();
            
            if(res.next())
                PRODUCTOS = new String[res.getInt("COUNT(ID_PROD)")][10];
            
            ps = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE CAT_PROD='AA' OR CAT_PROD='FERRETERIA'");
            res = ps.executeQuery();
            int cero;
            String zero;
            while(res.next())
            {
                cero = res.getInt("ID_PROD");
                PRODUCTOS[i][0] = String.format("%04d", cero);
                PRODUCTOS[i][1] = ("" + res.getString("CODIGO_PROD"));
                PRODUCTOS[i][2] = ("" + res.getString("SERIE_PROD"));
                PRODUCTOS[i][3] = ("" + res.getString("CAT_PROD"));                
                PRODUCTOS[i][4] = ("" + res.getString("DES_PROD"));
                PRODUCTOS[i][5] = ("" + res.getString("PRG_PROD"));
                PRODUCTOS[i][6] = ("" + res.getString("PRT_PROD"));
                PRODUCTOS[i][7] = ("" + res.getString("UM_PROD"));
                PRODUCTOS[i][8] = ("" + res.getString("DIM_PROD"));
                cero = res.getInt("STOCK_PROD");
                PRODUCTOS[i][9] = String.format("%04d", cero);
                i++;
            }
            CargarTabla();
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    
        private void BuscarPRODUCTOS(String buscar){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_PROD) FROM PRODUCTOS WHERE CAT_PROD='AA' OR CAT_PROD='FERRETERIA'");
            res = ps.executeQuery();
            
            if(res.next())
                PRODUCTOS = new String[res.getInt("COUNT(ID_PROD)")][10];
            
            ps = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE CODIGO_PROD LIKE '%"+buscar+"%' OR DES_PROD LIKE '%"+buscar+"%' HAVING CAT_PROD='AA' OR CAT_PROD='FERRETERIA'");
            res = ps.executeQuery();
            int cero;
            String zero;
            while(res.next())
            {
                cero = res.getInt("ID_PROD");
                PRODUCTOS[i][0] = String.format("%04d", cero);
                PRODUCTOS[i][1] = ("" + res.getString("CODIGO_PROD"));
                PRODUCTOS[i][2] = ("" + res.getString("SERIE_PROD"));
                PRODUCTOS[i][3] = ("" + res.getString("CAT_PROD"));                
                PRODUCTOS[i][4] = ("" + res.getString("DES_PROD"));
                PRODUCTOS[i][5] = ("" + res.getString("PRG_PROD"));
                PRODUCTOS[i][6] = ("" + res.getString("PRT_PROD"));
                PRODUCTOS[i][7] = ("" + res.getString("UM_PROD"));
                PRODUCTOS[i][8] = ("" + res.getString("DIM_PROD"));
                cero = res.getInt("STOCK_PROD");
                PRODUCTOS[i][9] = String.format("%04d", cero);
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
        objetoProductos.SeleccionarProductos(tablaPRODUCTOS, jTextField_ID, jTextField_CB, jTextField_Serie, jComboBox_Categoria, jTextField_UM, jTextArea, jTextField_PG, jTextField_PT, jTextField_DIM, jTextField_STOCK);
    }
    
    public void modificar(){
        Productos objetoProductos = new Productos();
        objetoProductos.ModificarProducto(jTextField_ID, jTextField_CB, jTextField_Serie, jComboBox_Categoria, jTextField_UM, jTextArea, jTextField_PG, jTextField_PT, jTextField_DIM, jTextField_STOCK);
    }
    
    public void cb(){
        int row = tablaPRODUCTOS.getSelectedRow();
        String value = tablaPRODUCTOS.getModel().getValueAt(row, 1).toString();
        
        Productos ObjetoProductos = new Productos();
        ObjetoProductos.CB(value);
    }
    */
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
        tablaPRODUCTOS1 = new javax.swing.JTable();
        jComboBox_Fechas = new javax.swing.JComboBox<>();

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

        tablaPRODUCTOS1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPRODUCTOS1.getTableHeader().setReorderingAllowed(false);
        tablaPRODUCTOS1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPRODUCTOS1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPRODUCTOS1);

        javax.swing.GroupLayout jPanel_OpcionesLayout = new javax.swing.GroupLayout(jPanel_Opciones);
        jPanel_Opciones.setLayout(jPanel_OpcionesLayout);
        jPanel_OpcionesLayout.setHorizontalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_Fechas, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Busqueda)
                .addContainerGap(122, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
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

    }//GEN-LAST:event_tablaPRODUCTOSMouseClicked

    private void tablaPRODUCTOS1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPRODUCTOS1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaPRODUCTOS1MouseClicked

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
    private javax.swing.JPanel jPanel_Fondo;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaPRODUCTOS;
    public javax.swing.JTable tablaPRODUCTOS1;
    // End of variables declaration//GEN-END:variables
}
