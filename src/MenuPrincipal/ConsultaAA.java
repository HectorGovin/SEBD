package MenuPrincipal;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ConsultaAA extends javax.swing.JFrame {
    
    
    public String[][] PRODUCTOS;
    String[][] data = new String[10][7];
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public ConsultaAA() {
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
        jButton_CB = new javax.swing.JButton();
        jPanel_ID = new javax.swing.JPanel();
        jTextField_ID = new javax.swing.JTextField();
        jPanel_Serie = new javax.swing.JPanel();
        jTextField_Serie = new javax.swing.JTextField();
        jPanel_Categoria = new javax.swing.JPanel();
        jComboBox_Categoria = new javax.swing.JComboBox<>();
        jPanel_UM = new javax.swing.JPanel();
        jTextField_UM = new javax.swing.JTextField();
        jPanel_PG = new javax.swing.JPanel();
        jTextField_PG = new javax.swing.JTextField();
        jPanel_PT = new javax.swing.JPanel();
        jTextField_PT = new javax.swing.JTextField();
        jPanel_DIM = new javax.swing.JPanel();
        jTextField_DIM = new javax.swing.JTextField();
        jPanel_STOCK = new javax.swing.JPanel();
        jTextField_STOCK = new javax.swing.JTextField();
        jLabel_RegistroProd = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel_CB = new javax.swing.JPanel();
        jTextField_CB = new javax.swing.JTextField();
        jPanel_DES = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Opciones.setBackground(new java.awt.Color(153, 188, 133));
        jPanel_Opciones.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel_Buscar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Buscar.setText("Buscar producto:");

        jTextField_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_BuscarKeyPressed(evt);
            }
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
        jButton_Productos.setEnabled(false);

        jButton_Servicios.setText("Servicios");
        jButton_Servicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ServiciosMouseClicked(evt);
            }
        });

        jButton_Agregar.setText("Agregar");
        jButton_Agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AgregarMouseClicked(evt);
            }
        });
        jButton_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AgregarActionPerformed(evt);
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

        jButton_CB.setText("Generar Código de barras");
        jButton_CB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CBMouseClicked(evt);
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
                        .addComponent(jButton_Actualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_CB))
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addComponent(jLabel_Buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Productos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Servicios)))
                .addContainerGap(466, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_CB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

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
                .addComponent(jTextField_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_IDLayout.setVerticalGroup(
            jPanel_IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Serie.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_Serie.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "SERIE"));

        javax.swing.GroupLayout jPanel_SerieLayout = new javax.swing.GroupLayout(jPanel_Serie);
        jPanel_Serie.setLayout(jPanel_SerieLayout);
        jPanel_SerieLayout.setHorizontalGroup(
            jPanel_SerieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SerieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_Serie, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_SerieLayout.setVerticalGroup(
            jPanel_SerieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SerieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Categoria.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_Categoria.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "CATEGORÍA"));

        jComboBox_Categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AA", "FERRETERIA" }));

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

        jPanel_UM.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_UM.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "UNIDAD DE MEDIDA"));

        javax.swing.GroupLayout jPanel_UMLayout = new javax.swing.GroupLayout(jPanel_UM);
        jPanel_UM.setLayout(jPanel_UMLayout);
        jPanel_UMLayout.setHorizontalGroup(
            jPanel_UMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_UM, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_UMLayout.setVerticalGroup(
            jPanel_UMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_PG.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_PG.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "PRECIO GENERAL"));

        javax.swing.GroupLayout jPanel_PGLayout = new javax.swing.GroupLayout(jPanel_PG);
        jPanel_PG.setLayout(jPanel_PGLayout);
        jPanel_PGLayout.setHorizontalGroup(
            jPanel_PGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_PG, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_PGLayout.setVerticalGroup(
            jPanel_PGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_PG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_PT.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_PT.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "PRECIO TÉCNICO"));

        javax.swing.GroupLayout jPanel_PTLayout = new javax.swing.GroupLayout(jPanel_PT);
        jPanel_PT.setLayout(jPanel_PTLayout);
        jPanel_PTLayout.setHorizontalGroup(
            jPanel_PTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_PT, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_PTLayout.setVerticalGroup(
            jPanel_PTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_PTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_PT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_DIM.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_DIM.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "DIMENSIONES"));

        javax.swing.GroupLayout jPanel_DIMLayout = new javax.swing.GroupLayout(jPanel_DIM);
        jPanel_DIM.setLayout(jPanel_DIMLayout);
        jPanel_DIMLayout.setHorizontalGroup(
            jPanel_DIMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DIMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_DIM, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_DIMLayout.setVerticalGroup(
            jPanel_DIMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DIMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_DIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_STOCK.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_STOCK.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "STOCK"));

        javax.swing.GroupLayout jPanel_STOCKLayout = new javax.swing.GroupLayout(jPanel_STOCK);
        jPanel_STOCK.setLayout(jPanel_STOCKLayout);
        jPanel_STOCKLayout.setHorizontalGroup(
            jPanel_STOCKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_STOCKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_STOCK, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_STOCKLayout.setVerticalGroup(
            jPanel_STOCKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_STOCKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_STOCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel_RegistroProd.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_RegistroProd.setText("Modificar producto");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel_CB.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_CB.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "CÓDIGO DE BARRAS"));

        javax.swing.GroupLayout jPanel_CBLayout = new javax.swing.GroupLayout(jPanel_CB);
        jPanel_CB.setLayout(jPanel_CBLayout);
        jPanel_CBLayout.setHorizontalGroup(
            jPanel_CBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CB, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_CBLayout.setVerticalGroup(
            jPanel_CBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_DESLayout.setVerticalGroup(
            jPanel_DESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DESLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_DES, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel_PT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_PG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_DIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_STOCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_UM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel_RegistroProd, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel_RegistroProd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel_PG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel_PT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_DIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                                .addComponent(jPanel_STOCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel_DES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void jButton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarActionPerformed
        eliminar();
        actualizar();
    }//GEN-LAST:event_jButton_EliminarActionPerformed

    private void jButton_AgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AgregarMouseClicked
        new AgregarProductos().setVisible(true);
    }//GEN-LAST:event_jButton_AgregarMouseClicked

    private void jButton_ServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ServiciosMouseClicked
        new ConsultaServicios().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_ServiciosMouseClicked

    private void jButton_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_AgregarActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
    actualizar();
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void tablaPRODUCTOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPRODUCTOSMouseClicked
    seleccionar();
    }//GEN-LAST:event_tablaPRODUCTOSMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    modificar();
    actualizar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyReleased
    BuscarPRODUCTOS(jTextField_Buscar.getText());
    }//GEN-LAST:event_jTextField_BuscarKeyReleased

    private void jTextField_BuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyPressed

    }//GEN-LAST:event_jTextField_BuscarKeyPressed

    private void jButton_CBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CBMouseClicked
    cb();
    }//GEN-LAST:event_jButton_CBMouseClicked

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
            java.util.logging.Logger.getLogger(ConsultaAA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaAA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaAA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaAA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ConsultaAA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_CB;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JButton jButton_Productos;
    private javax.swing.JButton jButton_Servicios;
    public javax.swing.JComboBox<String> jComboBox_Categoria;
    private javax.swing.JLabel jLabel_Buscar;
    private javax.swing.JLabel jLabel_RegistroProd;
    private javax.swing.JPanel jPanel_CB;
    public javax.swing.JPanel jPanel_Categoria;
    private javax.swing.JPanel jPanel_DES;
    private javax.swing.JPanel jPanel_DIM;
    private javax.swing.JPanel jPanel_Fondo;
    public javax.swing.JPanel jPanel_ID;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JPanel jPanel_PG;
    private javax.swing.JPanel jPanel_PT;
    private javax.swing.JPanel jPanel_STOCK;
    private javax.swing.JPanel jPanel_Serie;
    private javax.swing.JPanel jPanel_UM;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextField_Buscar;
    public javax.swing.JTextField jTextField_CB;
    public javax.swing.JTextField jTextField_DIM;
    public javax.swing.JTextField jTextField_ID;
    public javax.swing.JTextField jTextField_PG;
    public javax.swing.JTextField jTextField_PT;
    public javax.swing.JTextField jTextField_STOCK;
    public javax.swing.JTextField jTextField_Serie;
    public javax.swing.JTextField jTextField_UM;
    public javax.swing.JTable tablaPRODUCTOS;
    // End of variables declaration//GEN-END:variables
}
