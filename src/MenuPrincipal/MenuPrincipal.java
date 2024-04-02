package MenuPrincipal;
import java.sql.*;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.text.*;

public class MenuPrincipal extends javax.swing.JFrame {
    
    public static String[][] PRODUCTOS = new String[50][10];
    public static String[][] PRODUCTOSenviar = new String[50][10];
    public static String[][] PRODUCTOSmodificar = new String[1][10];
    
    int contadorProd;
    public static final String URL = "jdbc:mysql://localhost:3306/sebd", USERNAME = "root", PASSWORD = "";
    
    public MenuPrincipal() {
        initComponents();
        this.setExtendedState(this.MAXIMIZED_BOTH);
        CargarTabla();
        EstablecerFecha();
    }
    
    public String[][] EnviarPRODUCTOS(){
        return PRODUCTOSenviar;
    }
    
    public String[][] EnviarPRODUCTOSaPartidas(){
        return PRODUCTOS;
    }
    
    public javax.swing.JButton jButton_Actualizar2;
    
    public javax.swing.JTextField Enviar(){
        return jTextField_Total;
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
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        String date = DateFormat.format(c.getTime());
        jLabel_FechaHora.setText(date);
    }
    
    private void CargarTabla(){
        float sum = 0;
        tablaPRODUCTOS.setModel(new javax.swing.table.DefaultTableModel(
        PRODUCTOS,
        new String [] {
            "COD DE BARRAS", "SERIE", "DESCRIPCION", "CATEGORIA", "U.M", "$ GENERAL", "$ TECNICO", "CANTIDAD"
        }
        ));
        EstablecerTamañoColumnas();
        for(int x=0; PRODUCTOS[x][8]!=null; x++){
            sum+= Float.parseFloat(PRODUCTOS[x][8]);
        }
        
        System.out.println("\n");
        
        for(int x = 0; PRODUCTOS[x][0]!= null;x++){
            System.out.print("PRODUCTO: "+(x+1)+"\n");
            for(int y = 0; y<10; y++){
                System.out.print(""+PRODUCTOS[x][y]+", ");
            }
            System.out.println("\n");
        }
        jTextField_Total.setText(""+sum);
        ConsultaFolio();
        ValidarSubT_IVA();
    }
    
    public void Limpiar(){
        PRODUCTOS = new String[50][10];
        PRODUCTOSenviar = new String[50][10];
    }
    
    public void CargarTabla2(javax.swing.JTable A){
        float sum = 0;
        A.setModel(new javax.swing.table.DefaultTableModel(
        PRODUCTOS,
        new String [] {
            "COD DE BARRAS", "SERIE", "DESCRIPCION", "CATEGORIA", "U.M", "$ GENERAL", "$ TECNICO", "CANTIDAD"
        }
        ));
        //EstablecerTamañoColumnas();
        for(int x=0; PRODUCTOS[x][8]!=null; x++){
            sum+= Float.parseFloat(PRODUCTOS[x][8]);
        }
        jTextField_Total.setText(""+sum);
        ConsultaFolio();
        ValidarSubT_IVA();
    }
    
    public javax.swing.JTable Get_EnviarTabla(){
        return tablaPRODUCTOS;
    }
    
    public void ConsultaFolio(){
        try{
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("SELECT MAX(ID_REP) AS ID_REP FROM REPORTES");
            res = ps.executeQuery(); 
            int ow = 0;
            if(res.next()){
                ow = res.getInt("ID_REP")+1;
            }
            String s = String.format("%04d", ow);
            jLabel_Folio.setText(s);
            
        } catch(Exception ex){JOptionPane.showMessageDialog(null, "Error en la consulta del folio: "+ex.toString());}
    }
    
    private void EstablecerTamañoColumnas(){
        TableColumn c;
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
        }
    }
    
    private void ValidarSubT_IVA(){
        double SUBT = Double.parseDouble(jTextField_Total.getText()) / 1.16;
        jTextField_Subtotal.setText(String.format("%.2f", SUBT));
        double IVA = Double.parseDouble(jTextField_Subtotal.getText()) * .16;
        jTextField_Impuesto.setText(String.format("%.2f", IVA));
    }
    
    private void ValidarCantidades(){
        String cod = jTextField_Producto.getText();
        if(PRODUCTOS[0][0] != null){
                for(int x = 0; PRODUCTOS [x][0] != null; x++){
                    if(PRODUCTOS[x][0].equals(cod)){
                        PRODUCTOS[x][7] = "" + (Integer.parseInt(PRODUCTOS[x][7]) + 1);
                        ConsultaProductos(x); break;
                    }else if(PRODUCTOS[x + 1][0] == null){
                        contadorProd++;
                        PRODUCTOS[contadorProd][7] = ("" + 1);
                        ConsultaProductos(contadorProd); break;
                    }
                }
            }else{
                //PRIMER PRODUCTO
                contadorProd=0; 
                PRODUCTOS[contadorProd][7] = ("" + 1);
                ConsultaProductos(contadorProd);
            }
    }
    
    private void ConsultaProductos(int m){
        String cod = jTextField_Producto.getText();
        try{
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("SELECT * FROM PRODUCTOS WHERE CODIGO_PROD = '" + cod + "'");
            res = ps.executeQuery();
            while(res.next()){
                PRODUCTOS[m][0] = ("" + res.getString("CODIGO_PROD")); 
                PRODUCTOS[m][1] = ("" + res.getString("SERIE_PROD"));
                PRODUCTOS[m][2] = ("" + res.getString("DES_PROD")); 
                PRODUCTOS[m][3] = ("" + res.getString("CAT_PROD")); 
                PRODUCTOS[m][4] = ("" + res.getString("UM_PROD"));
                PRODUCTOS[m][5] = ("" + res.getString("PRG_PROD")); 
                PRODUCTOS[m][6] = ("" + res.getString("PRT_PROD"));
                PRODUCTOS[m][9] = ("" + res.getInt("ID_PROD"));
                PRODUCTOSenviar[m][0]=PRODUCTOS[m][2];
            }
            PRODUCTOSenviar[m][1]=PRODUCTOS[m][7]; //CANTIDAD
            PRODUCTOS[m][8] = ""+(Float.parseFloat(PRODUCTOS[m][5]) * Float.parseFloat(PRODUCTOS[m][7])); 
            PRODUCTOSenviar[m][2]=PRODUCTOS[m][8];
            CargarTabla();
        }catch(SQLException e){
            System.out.println(e);
        }
    }       
    
    private void EliminarPartida(){
        System.out.println("\n"+contadorProd);
        int row = tablaPRODUCTOS.getSelectedRow();
        System.out.print("\nEl valor de SelectedRow es: "+row);
        for(int x = 0; x<10; x++){
            if(PRODUCTOS[row+1][x] != null){
                for(int y=row; PRODUCTOS[y+1][x]!= null; y++){
                    PRODUCTOS[y][x] = PRODUCTOS[y+1][x];
                    PRODUCTOS[y+1][x] = null;
                    PRODUCTOSenviar[y][x] = PRODUCTOSenviar[y+1][x];
                    PRODUCTOSenviar[y+1][x] = null;
                }
            }else{
                PRODUCTOS[row][x] = null;
                PRODUCTOSenviar[row][x] = null;
            }
        }
        contadorProd--;
        CargarTabla();
    }
    
    private void ModificarPartida(){
        ROWXD = tablaPRODUCTOS.getSelectedRow();
        PRODUCTOSmodificar[0][0] = ""+ROWXD;
        PRODUCTOSmodificar[0][1] = PRODUCTOS[ROWXD][2]; //DESCRIPCION
        PRODUCTOSmodificar[0][2] = PRODUCTOS[ROWXD][1]; //SERIE
        PRODUCTOSmodificar[0][3] = PRODUCTOS[ROWXD][7]; //CANTIDAD
    }
    
    public static int ROWXD;
    
    public int EnviarRow(){
        return ROWXD;
    }
    
    public String[][] EnviarPRODUCTOSaModificar(){
        return PRODUCTOSmodificar;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_FHG = new javax.swing.JPanel();
        jLabel_FH_S = new javax.swing.JLabel();
        jLabel_FechaHora = new javax.swing.JLabel();
        jLabel_G_S = new javax.swing.JLabel();
        jLabel_Ganancias = new javax.swing.JLabel();
        jPanel_Productos = new javax.swing.JPanel();
        jLabel_Producto = new javax.swing.JLabel();
        jTextField_Producto = new javax.swing.JTextField();
        jPanel_FAS_AND_BOTTOM = new javax.swing.JPanel();
        jButton_Ferreteria = new javax.swing.JButton();
        jButton_AireAcondicionado = new javax.swing.JButton();
        jButton_Servicios = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPRODUCTOS = new javax.swing.JTable();
        jButton_VentasHoyFerreteria = new javax.swing.JButton();
        jButton_CobroClienteU = new javax.swing.JButton();
        jButton_CobroRapido = new javax.swing.JButton();
        jLabel_Subtotal = new javax.swing.JLabel();
        jTextField_Subtotal = new javax.swing.JTextField();
        jLabel_Impuesto = new javax.swing.JLabel();
        jTextField_Impuesto = new javax.swing.JTextField();
        jTextField_Total = new javax.swing.JTextField();
        jButton_Modificar = new javax.swing.JButton();
        jButton_ELIMINAR = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_VentasHoyAA = new javax.swing.JButton();
        jLabel_Folio_S = new javax.swing.JLabel();
        jLabel_Folio = new javax.swing.JLabel();
        jButton_AñadirProducto = new javax.swing.JButton();
        jMenuBar_Principal = new javax.swing.JMenuBar();
        jMenu_Productos = new javax.swing.JMenu();
        jMenu_Reportes = new javax.swing.JMenu();
        jMenu_Clientes = new javax.swing.JMenu();
        jMenu_Usuarios = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_Fondo.setToolTipText("");

        jPanel_FHG.setBackground(new java.awt.Color(153, 188, 133));
        jPanel_FHG.setToolTipText("");

        jLabel_FH_S.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_FH_S.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_FH_S.setText("Fecha:");
        jLabel_FH_S.setToolTipText("");

        jLabel_FechaHora.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_FechaHora.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_FechaHora.setText("22/02/2024 12:12 p.m.");

        jLabel_G_S.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_G_S.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_G_S.setText("Ganancias:");

        jLabel_Ganancias.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Ganancias.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Ganancias.setText("$300.00 mxn");

        jPanel_Productos.setBackground(new java.awt.Color(225, 240, 218));

        jLabel_Producto.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Producto.setText("Producto:");

        jTextField_Producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_ProductoKeyReleased(evt);
            }
        });

        jPanel_FAS_AND_BOTTOM.setBackground(new java.awt.Color(153, 188, 133));

        jButton_Ferreteria.setText("FERRETERÍA");
        jButton_Ferreteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_FerreteriaMouseClicked(evt);
            }
        });

        jButton_AireAcondicionado.setText("AIRE ACONDICIONADO");
        jButton_AireAcondicionado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AireAcondicionadoMouseClicked(evt);
            }
        });

        jButton_Servicios.setText("SERVICIOS");
        jButton_Servicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ServiciosMouseClicked(evt);
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
        jScrollPane1.setViewportView(tablaPRODUCTOS);

        jButton_VentasHoyFerreteria.setForeground(new java.awt.Color(102, 0, 0));
        jButton_VentasHoyFerreteria.setText("VENTAS DE HOY");
        jButton_VentasHoyFerreteria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FERRETERIA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(153, 0, 0))); // NOI18N
        jButton_VentasHoyFerreteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_VentasHoyFerreteriaMouseClicked(evt);
            }
        });

        jButton_CobroClienteU.setText("COBRO CLIENTE USUAL");
        jButton_CobroClienteU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CobroClienteUMouseClicked(evt);
            }
        });

        jButton_CobroRapido.setText("COBRO RÁPIDO");
        jButton_CobroRapido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CobroRapidoMouseClicked(evt);
            }
        });

        jLabel_Subtotal.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Subtotal.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Subtotal.setText("SUBTOTAL:");

        jTextField_Subtotal.setEditable(false);
        jTextField_Subtotal.setEnabled(false);

        jLabel_Impuesto.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Impuesto.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Impuesto.setText("IMPUESTO:");

        jTextField_Impuesto.setEditable(false);
        jTextField_Impuesto.setEnabled(false);

        jTextField_Total.setEditable(false);
        jTextField_Total.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jTextField_Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton_Modificar.setBackground(new java.awt.Color(255, 153, 51));
        jButton_Modificar.setText("MODIFICAR");
        jButton_Modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ModificarMouseClicked(evt);
            }
        });

        jButton_ELIMINAR.setBackground(new java.awt.Color(204, 0, 0));
        jButton_ELIMINAR.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ELIMINAR.setText("ELIMINAR");
        jButton_ELIMINAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ELIMINARMouseClicked(evt);
            }
        });

        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ActualizarMouseClicked(evt);
            }
        });

        jButton_VentasHoyAA.setForeground(new java.awt.Color(0, 51, 102));
        jButton_VentasHoyAA.setText("VENTAS DE HOY");
        jButton_VentasHoyAA.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AIRES ACONDICIONADOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 51, 102))); // NOI18N
        jButton_VentasHoyAA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_VentasHoyAAMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_FAS_AND_BOTTOMLayout = new javax.swing.GroupLayout(jPanel_FAS_AND_BOTTOM);
        jPanel_FAS_AND_BOTTOM.setLayout(jPanel_FAS_AND_BOTTOMLayout);
        jPanel_FAS_AND_BOTTOMLayout.setHorizontalGroup(
            jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel_FAS_AND_BOTTOMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_FAS_AND_BOTTOMLayout.createSequentialGroup()
                        .addComponent(jButton_AireAcondicionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Ferreteria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Servicios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ELIMINAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Actualizar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_FAS_AND_BOTTOMLayout.createSequentialGroup()
                        .addComponent(jButton_VentasHoyFerreteria, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_VentasHoyAA, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(jButton_CobroClienteU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_CobroRapido, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_FAS_AND_BOTTOMLayout.createSequentialGroup()
                                .addComponent(jLabel_Subtotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_FAS_AND_BOTTOMLayout.createSequentialGroup()
                                .addComponent(jLabel_Impuesto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Impuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(jTextField_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel_FAS_AND_BOTTOMLayout.setVerticalGroup(
            jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FAS_AND_BOTTOMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Ferreteria)
                    .addComponent(jButton_AireAcondicionado)
                    .addComponent(jButton_Servicios)
                    .addComponent(jButton_Modificar)
                    .addComponent(jButton_ELIMINAR)
                    .addComponent(jButton_Actualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_VentasHoyFerreteria, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_VentasHoyAA, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_FAS_AND_BOTTOMLayout.createSequentialGroup()
                            .addGroup(jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel_Subtotal)
                                .addComponent(jTextField_Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel_Impuesto)
                                .addComponent(jTextField_Impuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel_FAS_AND_BOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_CobroRapido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(jButton_CobroClienteU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jTextField_Total, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jLabel_Folio_S.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Folio_S.setText("FOLIO:");

        jLabel_Folio.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Folio.setText("0001");

        jButton_AñadirProducto.setText("AÑADIR");

        javax.swing.GroupLayout jPanel_ProductosLayout = new javax.swing.GroupLayout(jPanel_Productos);
        jPanel_Productos.setLayout(jPanel_ProductosLayout);
        jPanel_ProductosLayout.setHorizontalGroup(
            jPanel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Producto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_AñadirProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_Folio_S, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Folio)
                .addGap(15, 15, 15))
            .addComponent(jPanel_FAS_AND_BOTTOM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_ProductosLayout.setVerticalGroup(
            jPanel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Folio_S, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_Folio))
                    .addGroup(jPanel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Producto)
                        .addComponent(jTextField_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_AñadirProducto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_FAS_AND_BOTTOM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_FHGLayout = new javax.swing.GroupLayout(jPanel_FHG);
        jPanel_FHG.setLayout(jPanel_FHGLayout);
        jPanel_FHGLayout.setHorizontalGroup(
            jPanel_FHGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Productos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_FHGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_FH_S)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_FechaHora)
                .addGap(48, 48, 48)
                .addComponent(jLabel_G_S)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Ganancias)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_FHGLayout.setVerticalGroup(
            jPanel_FHGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FHGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_FHGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_FechaHora)
                    .addComponent(jLabel_G_S)
                    .addComponent(jLabel_Ganancias)
                    .addComponent(jLabel_FH_S))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Productos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_FHG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_FHG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu_Productos.setText("Productos");
        jMenu_Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu_ProductosMouseClicked(evt);
            }
        });
        jMenuBar_Principal.add(jMenu_Productos);

        jMenu_Reportes.setText("Reportes");
        jMenu_Reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu_ReportesMouseClicked(evt);
            }
        });
        jMenuBar_Principal.add(jMenu_Reportes);

        jMenu_Clientes.setText("Clientes");
        jMenu_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu_ClientesMouseClicked(evt);
            }
        });
        jMenuBar_Principal.add(jMenu_Clientes);

        jMenu_Usuarios.setText("Usuarios");
        jMenu_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu_UsuariosMouseClicked(evt);
            }
        });
        jMenuBar_Principal.add(jMenu_Usuarios);

        setJMenuBar(jMenuBar_Principal);

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

    private void jMenu_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu_ProductosMouseClicked
        new ConsultaAA().setVisible(true);
    }//GEN-LAST:event_jMenu_ProductosMouseClicked

    private void jMenu_UsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu_UsuariosMouseClicked
        new ConsultaUsuarios().setVisible(true);
    }//GEN-LAST:event_jMenu_UsuariosMouseClicked

    private void jTextField_ProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ProductoKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            ValidarCantidades();
            jTextField_Producto.setText("");}
    }//GEN-LAST:event_jTextField_ProductoKeyReleased

    private void jButton_FerreteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_FerreteriaMouseClicked
        new MenuPrincipalConsultaFerreteria().setVisible(true);
    }//GEN-LAST:event_jButton_FerreteriaMouseClicked

    private void jButton_AireAcondicionadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AireAcondicionadoMouseClicked
        new MenuPrincipalConsultaAA().setVisible(true);
    }//GEN-LAST:event_jButton_AireAcondicionadoMouseClicked

    private void jButton_ServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ServiciosMouseClicked
        new MenuPrincipalConsultaServicios().setVisible(true);
    }//GEN-LAST:event_jButton_ServiciosMouseClicked

    private void jButton_CobroRapidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CobroRapidoMouseClicked
        new MenuPrincipalCobroRapido().setVisible(true);
    }//GEN-LAST:event_jButton_CobroRapidoMouseClicked

    private void jButton_CobroClienteUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CobroClienteUMouseClicked
        new MenuPrincipalCobroClienteFrecuente().setVisible(true);
    }//GEN-LAST:event_jButton_CobroClienteUMouseClicked

    private void jButton_ActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ActualizarMouseClicked
        CargarTabla();
    }//GEN-LAST:event_jButton_ActualizarMouseClicked

    private void jMenu_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu_ClientesMouseClicked
        new ConsultaClientes().setVisible(true);
    }//GEN-LAST:event_jMenu_ClientesMouseClicked

    private void jMenu_ReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu_ReportesMouseClicked
        new ConsultaReportes().setVisible(true);
    }//GEN-LAST:event_jMenu_ReportesMouseClicked

    private void jButton_ELIMINARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ELIMINARMouseClicked
        EliminarPartida();
    }//GEN-LAST:event_jButton_ELIMINARMouseClicked

    private void jButton_ModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ModificarMouseClicked
        ModificarPartida();
        new MenuPrincipalModificarPartida().setVisible(true);
    }//GEN-LAST:event_jButton_ModificarMouseClicked

    private void jButton_VentasHoyAAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_VentasHoyAAMouseClicked
        new ConsultaReporteDelDiaAA().setVisible(true);
    }//GEN-LAST:event_jButton_VentasHoyAAMouseClicked

    private void jButton_VentasHoyFerreteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_VentasHoyFerreteriaMouseClicked
        new ConsultaReporteDelDiaFerreteria().setVisible(true);
    }//GEN-LAST:event_jButton_VentasHoyFerreteriaMouseClicked

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_AireAcondicionado;
    private javax.swing.JButton jButton_AñadirProducto;
    private javax.swing.JButton jButton_CobroClienteU;
    private javax.swing.JButton jButton_CobroRapido;
    private javax.swing.JButton jButton_ELIMINAR;
    private javax.swing.JButton jButton_Ferreteria;
    private javax.swing.JButton jButton_Modificar;
    private javax.swing.JButton jButton_Servicios;
    private javax.swing.JButton jButton_VentasHoyAA;
    private javax.swing.JButton jButton_VentasHoyFerreteria;
    private javax.swing.JLabel jLabel_FH_S;
    private javax.swing.JLabel jLabel_FechaHora;
    public javax.swing.JLabel jLabel_Folio;
    private javax.swing.JLabel jLabel_Folio_S;
    private javax.swing.JLabel jLabel_G_S;
    private javax.swing.JLabel jLabel_Ganancias;
    private javax.swing.JLabel jLabel_Impuesto;
    private javax.swing.JLabel jLabel_Producto;
    private javax.swing.JLabel jLabel_Subtotal;
    private javax.swing.JMenuBar jMenuBar_Principal;
    private javax.swing.JMenu jMenu_Clientes;
    private javax.swing.JMenu jMenu_Productos;
    private javax.swing.JMenu jMenu_Reportes;
    private javax.swing.JMenu jMenu_Usuarios;
    private javax.swing.JPanel jPanel_FAS_AND_BOTTOM;
    private javax.swing.JPanel jPanel_FHG;
    private javax.swing.JPanel jPanel_Fondo;
    private javax.swing.JPanel jPanel_Productos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_Impuesto;
    private javax.swing.JTextField jTextField_Producto;
    private javax.swing.JTextField jTextField_Subtotal;
    public javax.swing.JTextField jTextField_Total;
    private javax.swing.JTable tablaPRODUCTOS;
    // End of variables declaration//GEN-END:variables
}
