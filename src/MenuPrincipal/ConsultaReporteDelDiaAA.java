package MenuPrincipal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ConsultaReporteDelDiaAA extends javax.swing.JFrame {
    
    String date;
    
    public String[][] PRODUCTOS;
    String[][] data = new String[10][7];
    
    public String[][] REPORTES;
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public ConsultaReporteDelDiaAA() {
        initComponents();
        this.setLocationRelativeTo(null);
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        EstablecerFecha();
        LeerReportesDelDia();
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
    
    private void EstablecerFecha(){
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Calendar c = Calendar.getInstance();
        date = DateFormat.format(c.getTime());
        jLabel_Fecha.setText(date);
    }
    
    private void LeerReportesDelDia(){
        try{
            Connection con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(DISTINCT REPORTES.ID_REP) FROM REPORTES LEFT JOIN PARTIDAS ON REPORTES.ID_REP = PARTIDAS.ID_REP LEFT JOIN PRODUCTOS ON PRODUCTOS.ID_PROD = PARTIDAS.ID_PROD WHERE REPORTES.DATE_REP = '04-02-2024' && PRODUCTOS.CAT_PROD = 'AA';");
            
            res = ps.executeQuery();
            
            System.out.println("\n"+date);
            
            if(res.next())
                REPORTES = new String[res.getInt("COUNT(DISTINCT REPORTES.ID_REP)")][10];
            
            ps = con.prepareStatement("SELECT DISTINCT rep.NOTA_REP, usu.NOM_USU, cli.NOM_CLIE, rep.FP_REP, rep.TOTAL_REP FROM `reportes` rep LEFT JOIN `usuarios` usu on rep.ID_USU = usu.ID_USU LEFT JOIN `clientes` cli on rep.ID_CLIE = cli.ID_CLIE LEFT JOIN `partidas` par on rep.ID_REP = par.ID_REP LEFT JOIN `productos` prod on prod.ID_PROD = par.ID_PROD WHERE rep.DATE_REP = '"+date+"' && prod.CAT_PROD = 'AA'");
            res = ps.executeQuery();
            int i = 0;
            while(res.next())
            {
                REPORTES[i][0] = ("" + res.getString("rep.NOTA_REP"));
                REPORTES[i][1] = ("" + res.getString("usu.NOM_USU"));
                REPORTES[i][2] = ("" + res.getString("cli.NOM_CLIE"));
                REPORTES[i][3] = ("" + res.getString("rep.FP_REP"));
                REPORTES[i][4] = ("" + res.getString("rep.TOTAL_REP"));
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
                "FOLIO", "EMPLEADO", "CLIENTE", "FORMA DE PAGO", "TOTAL"
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

        jLabel1 = new javax.swing.JLabel();
        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_Opciones = new javax.swing.JPanel();
        jLabel_Buscar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPRODUCTOS = new javax.swing.JTable();
        jButton_PDF = new javax.swing.JButton();
        jButton_Salir = new javax.swing.JButton();
        jLabel_Ferreteria = new javax.swing.JLabel();
        jLabel_Fecha = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setLocation(new java.awt.Point(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Opciones.setBackground(new java.awt.Color(153, 188, 133));
        jPanel_Opciones.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel_Buscar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Buscar.setText("Reportes con fecha de:");

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

        jButton_PDF.setText("Generar PDF");

        jButton_Salir.setText("Salir");

        jLabel_Ferreteria.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Ferreteria.setForeground(new java.awt.Color(153, 0, 0));
        jLabel_Ferreteria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Ferreteria.setText("AA y SERVICIOS");

        jLabel_Fecha.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Fecha.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Fecha.setText("fecha");

        javax.swing.GroupLayout jPanel_OpcionesLayout = new javax.swing.GroupLayout(jPanel_Opciones);
        jPanel_Opciones.setLayout(jPanel_OpcionesLayout);
        jPanel_OpcionesLayout.setHorizontalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_OpcionesLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jButton_Salir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel_Buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel_Ferreteria, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel_OpcionesLayout.setVerticalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Ferreteria, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_PDF, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Opciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 344));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPRODUCTOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPRODUCTOSMouseClicked

    }//GEN-LAST:event_tablaPRODUCTOSMouseClicked

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
            java.util.logging.Logger.getLogger(ConsultaReporteDelDiaAA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaReporteDelDiaAA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaReporteDelDiaAA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaReporteDelDiaAA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ConsultaReporteDelDiaAA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_PDF;
    private javax.swing.JButton jButton_Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Buscar;
    private javax.swing.JLabel jLabel_Fecha;
    private javax.swing.JLabel jLabel_Ferreteria;
    private javax.swing.JPanel jPanel_Fondo;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaPRODUCTOS;
    // End of variables declaration//GEN-END:variables
}
