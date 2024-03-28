package MenuPrincipal;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ConsultaClientes extends javax.swing.JFrame {
    
    public String[][] CLIENTES;
    String[][] data = new String[10][10];
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public ConsultaClientes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        LeerCLIENTES();
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
        
        tablaCLIENTES.setModel(new javax.swing.table.DefaultTableModel(
            CLIENTES,
            new String [] {
                "ID", "NOMBRE", "RFC", "DIRECCIÓN", "C.P.", "TELÉFONO", "CORREO", "REGIMEN FISCAL", "DIRECCIÓN FISCAL"
            }
            ));
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(new javax.swing.table.DefaultTableModel(
            CLIENTES,
            new String [] {
                "ID", "NOMBRE", "RFC", "DIRECCIÓN", "C.P.", "TELÉFONO", "CORREO", "REGIMEN FISCAL", "DIRECCIÓN FISCAL"
            }
            ));
        
        tablaCLIENTES.setRowSorter(OrdenarTabla);
        
        TableColumn c;
        c = tablaCLIENTES.getColumnModel().getColumn(0);
        c.setMaxWidth(50); c.setMinWidth(50); c.setResizable(false); 
        c = tablaCLIENTES.getColumnModel().getColumn(1);
        c.setMaxWidth(200); c.setMinWidth(200); c.setResizable(false); 
        c = tablaCLIENTES.getColumnModel().getColumn(2);
        c.setMaxWidth(110); c.setMinWidth(110); c.setResizable(false); 
        c = tablaCLIENTES.getColumnModel().getColumn(3);
        c.setPreferredWidth(300);
        c = tablaCLIENTES.getColumnModel().getColumn(4);
        c.setMaxWidth(50); c.setMinWidth(50); c.setResizable(false);
        c = tablaCLIENTES.getColumnModel().getColumn(5);
        c.setMaxWidth(90); c.setMinWidth(90); c.setResizable(false);
        c = tablaCLIENTES.getColumnModel().getColumn(6);
        c.setMaxWidth(210); c.setMinWidth(210); c.setResizable(false);
        c = tablaCLIENTES.getColumnModel().getColumn(7);
        c.setPreferredWidth(300);
        c = tablaCLIENTES.getColumnModel().getColumn(8);
        c.setPreferredWidth(300);
    }
    
    private void LeerCLIENTES(){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_CLIE) FROM CLIENTES");
            res = ps.executeQuery();
            
            if(res.next())
                CLIENTES = new String[res.getInt("COUNT(ID_CLIE)")][9];
            
            ps = con.prepareStatement("SELECT * FROM CLIENTES");
            res = ps.executeQuery();
            int cero;
            String zero;
            while(res.next())
            {
                cero = res.getInt("ID_CLIE");
                CLIENTES[i][0] = String.format("%04d", cero);
                CLIENTES[i][1] = ("" + res.getString("NOM_CLIE"));
                CLIENTES[i][2] = ("" + res.getString("RFC_CLIE"));
                CLIENTES[i][3] = ("" + res.getString("DIR_CLIE"));                
                CLIENTES[i][4] = ("" + res.getString("CP_CLIE"));
                CLIENTES[i][5] = ("" + res.getString("TELF_CLIE"));
                CLIENTES[i][6] = ("" + res.getString("MAIL_CLIE"));
                CLIENTES[i][7] = ("" + res.getString("REGI_CLIE"));
                CLIENTES[i][8] = ("" + res.getString("DIRFIS_CLIE"));
                i++;
            }
            CargarTabla();
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    
        private void BuscarCLIENTES(String buscar){
        int i = 0;
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("SELECT COUNT(ID_CLIE) FROM CLIENTES");
            res = ps.executeQuery();
            
            if(res.next())
                CLIENTES = new String[res.getInt("COUNT(ID_CLIE)")][9];
            
            ps = con.prepareStatement("SELECT * FROM CLIENTES WHERE NOM_CLIE LIKE '%"+buscar+"%' OR RFC_CLIE LIKE '%"+buscar+"%'");
            res = ps.executeQuery();
            int cero;
            String zero;
            while(res.next())
            {
                cero = res.getInt("ID_CLIE");
                CLIENTES[i][0] = String.format("%04d", cero);
                CLIENTES[i][1] = ("" + res.getString("NOM_CLIE"));
                CLIENTES[i][2] = ("" + res.getString("RFC_CLIE"));
                CLIENTES[i][3] = ("" + res.getString("DIR_CLIE"));                
                CLIENTES[i][4] = ("" + res.getString("CP_CLIE"));
                CLIENTES[i][5] = ("" + res.getString("TELF_CLIE"));
                CLIENTES[i][6] = ("" + res.getString("MAIL_CLIE"));
                CLIENTES[i][7] = ("" + res.getString("REGI_CLIE"));
                CLIENTES[i][8] = ("" + res.getString("DIRFIS_CLIE"));
                i++;
            }
            CargarTabla();
            
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void eliminar(){
        int row = tablaCLIENTES.getSelectedRow();
        String value = tablaCLIENTES.getModel().getValueAt(row, 0).toString();
        
        Clientes objetoClientes = new Clientes();
        objetoClientes.EliminarCliente(value);
    }
            
    public void actualizar(){
        LeerCLIENTES();
    }      
    
    public void seleccionar(){
        Clientes objetoClientes = new Clientes();
        objetoClientes.SeleccionarClientes(tablaCLIENTES, jTextField_ID, jTextField_NOM, jTextField_RFC, jTextField_DIR, jTextField_CP, jTextField_TELF, jTextField_MAIL, jTextField_REGI, jTextField_DIRFIS);
    }
    
    public void modificar(){
        Clientes objetoClientes = new Clientes();
        objetoClientes.ModificarCliente(jTextField_ID, jTextField_NOM, jTextField_RFC, jTextField_DIR, jTextField_CP, jTextField_TELF, jTextField_MAIL, jTextField_REGI, jTextField_DIRFIS);
    }   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_Opciones = new javax.swing.JPanel();
        jLabel_Buscar = new javax.swing.JLabel();
        jTextField_Buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCLIENTES = new javax.swing.JTable();
        jButton_Agregar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jPanel_ID = new javax.swing.JPanel();
        jTextField_ID = new javax.swing.JTextField();
        jPanel_NOMBRE = new javax.swing.JPanel();
        jTextField_NOM = new javax.swing.JTextField();
        jPanel_RFC = new javax.swing.JPanel();
        jTextField_RFC = new javax.swing.JTextField();
        jPanel_DIR = new javax.swing.JPanel();
        jTextField_DIR = new javax.swing.JTextField();
        jPanel_CP = new javax.swing.JPanel();
        jTextField_CP = new javax.swing.JTextField();
        jPanel_TELF = new javax.swing.JPanel();
        jTextField_TELF = new javax.swing.JTextField();
        jPanel_MAIL = new javax.swing.JPanel();
        jTextField_MAIL = new javax.swing.JTextField();
        jPanel_REGI = new javax.swing.JPanel();
        jTextField_REGI = new javax.swing.JTextField();
        jPanel_DIRFIS = new javax.swing.JPanel();
        jTextField_DIRFIS = new javax.swing.JTextField();
        jLabel_RegistroProd = new javax.swing.JLabel();
        jButton_Guardar = new javax.swing.JButton();

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

        tablaCLIENTES.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaCLIENTES.getTableHeader().setReorderingAllowed(false);
        tablaCLIENTES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCLIENTESMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCLIENTES);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE)
        );
        jPanel_OpcionesLayout.setVerticalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Buscar)
                    .addComponent(jTextField_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
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

        jPanel_NOMBRE.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_NOMBRE.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "NOMBRE"));

        javax.swing.GroupLayout jPanel_NOMBRELayout = new javax.swing.GroupLayout(jPanel_NOMBRE);
        jPanel_NOMBRE.setLayout(jPanel_NOMBRELayout);
        jPanel_NOMBRELayout.setHorizontalGroup(
            jPanel_NOMBRELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NOMBRELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_NOM)
                .addContainerGap())
        );
        jPanel_NOMBRELayout.setVerticalGroup(
            jPanel_NOMBRELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NOMBRELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_NOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_RFC.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_RFC.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "RFC"));

        javax.swing.GroupLayout jPanel_RFCLayout = new javax.swing.GroupLayout(jPanel_RFC);
        jPanel_RFC.setLayout(jPanel_RFCLayout);
        jPanel_RFCLayout.setHorizontalGroup(
            jPanel_RFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_RFCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_RFC)
                .addContainerGap())
        );
        jPanel_RFCLayout.setVerticalGroup(
            jPanel_RFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_RFCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_RFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_DIR.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_DIR.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "DIRECCIÓN"));

        javax.swing.GroupLayout jPanel_DIRLayout = new javax.swing.GroupLayout(jPanel_DIR);
        jPanel_DIR.setLayout(jPanel_DIRLayout);
        jPanel_DIRLayout.setHorizontalGroup(
            jPanel_DIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DIRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_DIR)
                .addContainerGap())
        );
        jPanel_DIRLayout.setVerticalGroup(
            jPanel_DIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DIRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_DIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_CP.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_CP.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "CÓDIGO POSTAL"));

        javax.swing.GroupLayout jPanel_CPLayout = new javax.swing.GroupLayout(jPanel_CP);
        jPanel_CP.setLayout(jPanel_CPLayout);
        jPanel_CPLayout.setHorizontalGroup(
            jPanel_CPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CP)
                .addContainerGap())
        );
        jPanel_CPLayout.setVerticalGroup(
            jPanel_CPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_TELF.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_TELF.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "TELÉFONO"));

        javax.swing.GroupLayout jPanel_TELFLayout = new javax.swing.GroupLayout(jPanel_TELF);
        jPanel_TELF.setLayout(jPanel_TELFLayout);
        jPanel_TELFLayout.setHorizontalGroup(
            jPanel_TELFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TELFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_TELF)
                .addContainerGap())
        );
        jPanel_TELFLayout.setVerticalGroup(
            jPanel_TELFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TELFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_TELF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_MAIL.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_MAIL.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "CORREO"));

        javax.swing.GroupLayout jPanel_MAILLayout = new javax.swing.GroupLayout(jPanel_MAIL);
        jPanel_MAIL.setLayout(jPanel_MAILLayout);
        jPanel_MAILLayout.setHorizontalGroup(
            jPanel_MAILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MAILLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_MAIL)
                .addContainerGap())
        );
        jPanel_MAILLayout.setVerticalGroup(
            jPanel_MAILLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MAILLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_MAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_REGI.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_REGI.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "REGIMEN FISCAL"));

        javax.swing.GroupLayout jPanel_REGILayout = new javax.swing.GroupLayout(jPanel_REGI);
        jPanel_REGI.setLayout(jPanel_REGILayout);
        jPanel_REGILayout.setHorizontalGroup(
            jPanel_REGILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_REGILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_REGI)
                .addContainerGap())
        );
        jPanel_REGILayout.setVerticalGroup(
            jPanel_REGILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_REGILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_REGI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_DIRFIS.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_DIRFIS.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "DIRECCIÓN FISCAL"));

        javax.swing.GroupLayout jPanel_DIRFISLayout = new javax.swing.GroupLayout(jPanel_DIRFIS);
        jPanel_DIRFIS.setLayout(jPanel_DIRFISLayout);
        jPanel_DIRFISLayout.setHorizontalGroup(
            jPanel_DIRFISLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DIRFISLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_DIRFIS)
                .addContainerGap())
        );
        jPanel_DIRFISLayout.setVerticalGroup(
            jPanel_DIRFISLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_DIRFISLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_DIRFIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel_RegistroProd.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_RegistroProd.setText("Modificar cliente");

        jButton_Guardar.setText("Guardar");
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FondoLayout.createSequentialGroup()
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel_DIRFIS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_REGI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_MAIL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_TELF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_CP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_DIR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_RFC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_NOMBRE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FondoLayout.createSequentialGroup()
                        .addComponent(jLabel_RegistroProd, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel_RegistroProd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_NOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_RFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_DIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_TELF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_MAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_REGI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_DIRFIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliente seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION);
        if(respuesta == 0){
            eliminar();
            actualizar();
        }
    }//GEN-LAST:event_jButton_EliminarActionPerformed

    private void jButton_AgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AgregarMouseClicked
        new AgregarClientes().setVisible(true);
    }//GEN-LAST:event_jButton_AgregarMouseClicked

    private void jButton_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_AgregarActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
    actualizar();
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void tablaCLIENTESMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCLIENTESMouseClicked
    seleccionar();
    }//GEN-LAST:event_tablaCLIENTESMouseClicked

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
    int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas realizar la modificación?", "Modificar", JOptionPane.YES_NO_OPTION);
        if(respuesta == 0){
            modificar();
            actualizar();
        }
    }//GEN-LAST:event_jButton_GuardarActionPerformed

    private void jTextField_BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarKeyReleased
    BuscarCLIENTES(jTextField_Buscar.getText());
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
            java.util.logging.Logger.getLogger(ConsultaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ConsultaClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Agregar;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JLabel jLabel_Buscar;
    private javax.swing.JLabel jLabel_RegistroProd;
    private javax.swing.JPanel jPanel_CP;
    private javax.swing.JPanel jPanel_DIR;
    private javax.swing.JPanel jPanel_DIRFIS;
    private javax.swing.JPanel jPanel_Fondo;
    public javax.swing.JPanel jPanel_ID;
    private javax.swing.JPanel jPanel_MAIL;
    private javax.swing.JPanel jPanel_NOMBRE;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JPanel jPanel_REGI;
    private javax.swing.JPanel jPanel_RFC;
    private javax.swing.JPanel jPanel_TELF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField_Buscar;
    public javax.swing.JTextField jTextField_CP;
    public javax.swing.JTextField jTextField_DIR;
    public javax.swing.JTextField jTextField_DIRFIS;
    public javax.swing.JTextField jTextField_ID;
    public javax.swing.JTextField jTextField_MAIL;
    public javax.swing.JTextField jTextField_NOM;
    public javax.swing.JTextField jTextField_REGI;
    public javax.swing.JTextField jTextField_RFC;
    public javax.swing.JTextField jTextField_TELF;
    public javax.swing.JTable tablaCLIENTES;
    // End of variables declaration//GEN-END:variables
}
