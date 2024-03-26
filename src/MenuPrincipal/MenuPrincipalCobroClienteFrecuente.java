package MenuPrincipal;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;

public class MenuPrincipalCobroClienteFrecuente extends javax.swing.JFrame {
    
    public void actualizar(){
        DefaultTableModel model = (DefaultTableModel)tablaPRODUCTOS.getModel();
        model.setRowCount(0);
    }
    
    String[] NOMBRES = new String[9];
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    Clientes cl = new Clientes();
    MenuPrincipal mp = new MenuPrincipal();
    Reportes r = new Reportes();
    Partidas p = new Partidas();

    public MenuPrincipalCobroClienteFrecuente() {
        initComponents();
        this.setLocationRelativeTo(null);
        CargarNombres();
        CargarTabla();
    }
    
    private void CargarNombres(){
        cl.CargarNombres(jComboBox_Cliente);
        jComboBox_Cliente.setSelectedItem(null);
    }
    
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
    
    private void ImprimirDatos(){
        jTextField_RFC.setText(cl.getRFC_CLIE());
        jTextField_CP.setText(""+cl.getCP_CLIE());
        jTextField_Email.setText(cl.getMAIL_CLIE());
        jTextField_Telefono.setText(cl.getTELF_CLIE());
        jTextArea_RegimenFiscal.setText(cl.getREGI_CLIE());
        jTextArea_Direccion.setText(cl.getDIR_CLIE());
        jTextArea_DireccionFiscal.setText(cl.getDIRFIS_CLIE());
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
        jPanel_ID = new javax.swing.JPanel();
        jComboBox_Cliente = new javax.swing.JComboBox<>();
        jButton_CargarDatosCliente = new javax.swing.JButton();
        jPanel_ID1 = new javax.swing.JPanel();
        jTextField_RFC = new javax.swing.JTextField();
        jPanel_ID2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_Direccion = new javax.swing.JTextArea();
        jPanel_ID3 = new javax.swing.JPanel();
        jTextField_Email = new javax.swing.JTextField();
        jPanel_ID4 = new javax.swing.JPanel();
        jTextField_CP = new javax.swing.JTextField();
        jPanel_ID5 = new javax.swing.JPanel();
        jTextField_Telefono = new javax.swing.JTextField();
        jPanel_ID8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_DireccionFiscal = new javax.swing.JTextArea();
        jPanel_ID9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_RegimenFiscal = new javax.swing.JTextArea();
        jButton_Confirmar = new javax.swing.JButton();
        jPanel_ID6 = new javax.swing.JPanel();
        jComboBox_FP = new javax.swing.JComboBox<>();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Barra.setBackground(new java.awt.Color(153, 188, 133));

        jLabel_RegistroProd.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_RegistroProd.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setText("Cobro de Cliente Frecuente");

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
                .addGap(15, 15, 15))
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

        jPanel_ID.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "Nombre de cliente"));

        javax.swing.GroupLayout jPanel_IDLayout = new javax.swing.GroupLayout(jPanel_ID);
        jPanel_ID.setLayout(jPanel_IDLayout);
        jPanel_IDLayout.setHorizontalGroup(
            jPanel_IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_Cliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_IDLayout.setVerticalGroup(
            jPanel_IDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_IDLayout.createSequentialGroup()
                .addComponent(jComboBox_Cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton_CargarDatosCliente.setText("Cargar datos");
        jButton_CargarDatosCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CargarDatosClienteMouseClicked(evt);
            }
        });

        jPanel_ID1.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "RFC del cliente"));

        jTextField_RFC.setEditable(false);
        jTextField_RFC.setEnabled(false);

        javax.swing.GroupLayout jPanel_ID1Layout = new javax.swing.GroupLayout(jPanel_ID1);
        jPanel_ID1.setLayout(jPanel_ID1Layout);
        jPanel_ID1Layout.setHorizontalGroup(
            jPanel_ID1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_RFC, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_ID1Layout.setVerticalGroup(
            jPanel_ID1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID1Layout.createSequentialGroup()
                .addComponent(jTextField_RFC, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_ID2.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "Direccion del cliente"));

        jTextArea_Direccion.setColumns(20);
        jTextArea_Direccion.setRows(5);
        jScrollPane2.setViewportView(jTextArea_Direccion);

        javax.swing.GroupLayout jPanel_ID2Layout = new javax.swing.GroupLayout(jPanel_ID2);
        jPanel_ID2.setLayout(jPanel_ID2Layout);
        jPanel_ID2Layout.setHorizontalGroup(
            jPanel_ID2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_ID2Layout.setVerticalGroup(
            jPanel_ID2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_ID3.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "Email"));

        javax.swing.GroupLayout jPanel_ID3Layout = new javax.swing.GroupLayout(jPanel_ID3);
        jPanel_ID3.setLayout(jPanel_ID3Layout);
        jPanel_ID3Layout.setHorizontalGroup(
            jPanel_ID3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_Email)
                .addContainerGap())
        );
        jPanel_ID3Layout.setVerticalGroup(
            jPanel_ID3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ID3Layout.createSequentialGroup()
                .addComponent(jTextField_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_ID4.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "Código postal"));

        javax.swing.GroupLayout jPanel_ID4Layout = new javax.swing.GroupLayout(jPanel_ID4);
        jPanel_ID4.setLayout(jPanel_ID4Layout);
        jPanel_ID4Layout.setHorizontalGroup(
            jPanel_ID4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_CP)
                .addContainerGap())
        );
        jPanel_ID4Layout.setVerticalGroup(
            jPanel_ID4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID4Layout.createSequentialGroup()
                .addComponent(jTextField_CP, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_ID5.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "Teléfono"));

        javax.swing.GroupLayout jPanel_ID5Layout = new javax.swing.GroupLayout(jPanel_ID5);
        jPanel_ID5.setLayout(jPanel_ID5Layout);
        jPanel_ID5Layout.setHorizontalGroup(
            jPanel_ID5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_Telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_ID5Layout.setVerticalGroup(
            jPanel_ID5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ID5Layout.createSequentialGroup()
                .addComponent(jTextField_Telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_ID8.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "Direccion fiscal"));

        jTextArea_DireccionFiscal.setColumns(20);
        jTextArea_DireccionFiscal.setRows(5);
        jScrollPane5.setViewportView(jTextArea_DireccionFiscal);

        javax.swing.GroupLayout jPanel_ID8Layout = new javax.swing.GroupLayout(jPanel_ID8);
        jPanel_ID8.setLayout(jPanel_ID8Layout);
        jPanel_ID8Layout.setHorizontalGroup(
            jPanel_ID8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_ID8Layout.setVerticalGroup(
            jPanel_ID8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_ID9.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "Regimen fiscal"));

        jTextArea_RegimenFiscal.setColumns(20);
        jTextArea_RegimenFiscal.setRows(5);
        jScrollPane6.setViewportView(jTextArea_RegimenFiscal);

        javax.swing.GroupLayout jPanel_ID9Layout = new javax.swing.GroupLayout(jPanel_ID9);
        jPanel_ID9.setLayout(jPanel_ID9Layout);
        jPanel_ID9Layout.setHorizontalGroup(
            jPanel_ID9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel_ID9Layout.setVerticalGroup(
            jPanel_ID9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID9Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton_Confirmar.setText("Confirmar");
        jButton_Confirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ConfirmarMouseClicked(evt);
            }
        });

        jPanel_ID6.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "Forma de pago"));

        jComboBox_FP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TARJETA", "TRANSFERENCIA" }));
        jComboBox_FP.setSelectedIndex(-1);
        jComboBox_FP.setToolTipText("");

        javax.swing.GroupLayout jPanel_ID6Layout = new javax.swing.GroupLayout(jPanel_ID6);
        jPanel_ID6.setLayout(jPanel_ID6Layout);
        jPanel_ID6Layout.setHorizontalGroup(
            jPanel_ID6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_FP, 0, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_ID6Layout.setVerticalGroup(
            jPanel_ID6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID6Layout.createSequentialGroup()
                .addComponent(jComboBox_FP)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_OpcionesLayout = new javax.swing.GroupLayout(jPanel_Opciones);
        jPanel_Opciones.setLayout(jPanel_OpcionesLayout);
        jPanel_OpcionesLayout.setHorizontalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addComponent(jPanel_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_CargarDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_ID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_ID3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel_ID5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_ID4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3))
                    .addComponent(jPanel_ID9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addComponent(jPanel_ID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_ID8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_OpcionesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addComponent(jPanel_ID6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        jPanel_OpcionesLayout.setVerticalGroup(
            jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton_CargarDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_ID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_ID4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_ID5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_ID3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_ID9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_OpcionesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_ID6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_OpcionesLayout.createSequentialGroup()
                                .addGap(0, 11, Short.MAX_VALUE)
                                .addGroup(jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel_ID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel_ID8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel_ID.getAccessibleContext().setAccessibleName("Cliente");
        jPanel_ID.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel_Barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addComponent(jPanel_Barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
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
        r.SetDatos(1, cl.getID_CLIE(), mp.jLabel_Folio.getText(), ""+jComboBox_FP.getSelectedItem());
        mp.EnviarPRODUCTOSaPartidas();
        System.out.println("\n\n");
        System.out.println("\nSí pasó por aqui");
        for(int i = 0; mp.PRODUCTOS[i][9] != null; i++){
            p.SetDatos(""+mp.jLabel_Folio.getText(), mp.PRODUCTOS[i][9], mp.PRODUCTOS[i][5], mp.PRODUCTOS[i][7]);
            //p.SetDatos(""+mp.jLabel_Folio.getText(), mp.PRODUCTOS[i][9], mp.PRODUCTOS[i][7], Double.parseDouble("5"), Double.parseDouble("5"), Double.parseDouble(mp.PRODUCTOS[i][8]));
            //System.out.println("\nRegistro no. "+i+":\n\nID de REPORTE: "+r.getNOTA_REP()+"   ID de PRODUCTO: "+mp.PRODUCTOS[i][9]+"   Cantidad: "+mp.PRODUCTOS[i][7]+"   SubTotal: "+Double.parseDouble("5")+"   IVA: "+Double.parseDouble("5")+"   Total: "+Double.parseDouble(mp.PRODUCTOS[i][8]));
            System.out.println("\nRegistro no. "+i+":\n\nID de REPORTE: "+r.getNOTA_REP()+"   ID de PRODUCTO: "+mp.PRODUCTOS[i][9]+"   Cantidad: "+p.getCAN_PAR()+"   SubTotal: "+p.getSUBT_PAR()+"   IVA: "+p.getIVA_PAR()+"   Total: "+p.getTOT_PAR());
            p.SubirDatosPartidas();}
        JOptionPane.showMessageDialog(null, "Registro añadido con éxito");
        dispose();
    }//GEN-LAST:event_jButton_ConfirmarMouseClicked

    private void jButton_CargarDatosClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CargarDatosClienteMouseClicked
        cl.BuscarDatos(""+jComboBox_Cliente.getSelectedItem());
        ImprimirDatos();
    }//GEN-LAST:event_jButton_CargarDatosClienteMouseClicked

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
            java.util.logging.Logger.getLogger(MenuPrincipalCobroClienteFrecuente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalCobroClienteFrecuente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalCobroClienteFrecuente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalCobroClienteFrecuente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalCobroClienteFrecuente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_CargarDatosCliente;
    private javax.swing.JButton jButton_Confirmar;
    private javax.swing.JComboBox<String> jComboBox_Cliente;
    private javax.swing.JComboBox<String> jComboBox_FP;
    private javax.swing.JLabel jLabel_Folio;
    private javax.swing.JLabel jLabel_Folio_S;
    private javax.swing.JLabel jLabel_RegistroProd;
    private javax.swing.JPanel jPanel_Barra;
    private javax.swing.JPanel jPanel_Fondo;
    public javax.swing.JPanel jPanel_ID;
    public javax.swing.JPanel jPanel_ID1;
    public javax.swing.JPanel jPanel_ID2;
    public javax.swing.JPanel jPanel_ID3;
    public javax.swing.JPanel jPanel_ID4;
    public javax.swing.JPanel jPanel_ID5;
    public javax.swing.JPanel jPanel_ID6;
    public javax.swing.JPanel jPanel_ID8;
    public javax.swing.JPanel jPanel_ID9;
    private javax.swing.JPanel jPanel_Opciones;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea_Direccion;
    private javax.swing.JTextArea jTextArea_DireccionFiscal;
    private javax.swing.JTextArea jTextArea_RegimenFiscal;
    private javax.swing.JTextField jTextField_CP;
    private javax.swing.JTextField jTextField_Email;
    public javax.swing.JTextField jTextField_RFC;
    private javax.swing.JTextField jTextField_Telefono;
    public javax.swing.JTable tablaPRODUCTOS;
    // End of variables declaration//GEN-END:variables
}
