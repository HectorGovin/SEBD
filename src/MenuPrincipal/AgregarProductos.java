/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MenuPrincipal;
import Practica01.Conexion;
import java.awt.Container;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Wilbert
 */
public class AgregarProductos extends javax.swing.JFrame {
    
    private javax.swing.JTable tablaPRODUCTOS;
    
    public AgregarProductos(ConsultasProductos solicitarTabla){
        this.tablaPRODUCTOS = solicitarTabla.tablaPRODUCTOS;
    }
    
    
    public void guardar(){
        Productos ObjetoProductos = new Productos();
        ObjetoProductos.InsertarProducto(jTextField_CB, jTextField_Serie, jComboBox_Categoria, jTextField_UM, jTextArea1, jTextField_PG, jTextField_PT, jTextField_DIM, jTextField_STOCK);
    }
    
    public void actualizar(){
        Productos objetoProductos = new Productos();
        objetoProductos.MostrarProductos(tablaPRODUCTOS);
    }
    
    public static Connection getConection(){
        String URL = "jdbc:mysql://localhost:3306/sebd";
        String USERNAME = "root";
        String PASSWORD = "";
        
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    private void CargarID(){
        
        try{
            Connection con = null;
            con = getConection();
            
            PreparedStatement ps;
            ResultSet res;            
            
            ps = con.prepareStatement("SELECT * FROM PRODUCTOS ORDER by ID_PROD DESC LIMIT 1;");
            res = ps.executeQuery();
            
            if(res.next()){
                jTextField_ID.setText(""+(res.getInt("ID_PROD")+1));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    private void LimpiarCampos(){
        jTextField_CB.setText("");
        jTextField_Serie.setText("");
        jTextField_UM.setText("");
        jTextField_PG.setText("");
        jTextField_PT.setText("");
        jTextField_DIM.setText("");
        jTextField_STOCK.setText("");
        jTextArea1.setText("");
    }
    
    public AgregarProductos() {
        initComponents();
        this.setLocationRelativeTo(null);
        CargarID();
    }
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Fondo = new javax.swing.JPanel();
        jPanel_Barra = new javax.swing.JPanel();
        jLabel_RegistroProd = new javax.swing.JLabel();
        jPanel_ID = new javax.swing.JPanel();
        jTextField_ID = new javax.swing.JTextField();
        jPanel_CB = new javax.swing.JPanel();
        jTextField_CB = new javax.swing.JTextField();
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
        jPanel_ID2 = new javax.swing.JPanel();
        jTextField_STOCK = new javax.swing.JTextField();
        jPanel_UM1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton_Guardar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();

        setResizable(false);

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));

        jPanel_Barra.setBackground(new java.awt.Color(153, 188, 133));

        jLabel_RegistroProd.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_RegistroProd.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setText("Registro de producto");

        javax.swing.GroupLayout jPanel_BarraLayout = new javax.swing.GroupLayout(jPanel_Barra);
        jPanel_Barra.setLayout(jPanel_BarraLayout);
        jPanel_BarraLayout.setHorizontalGroup(
            jPanel_BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BarraLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel_RegistroProd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_BarraLayout.setVerticalGroup(
            jPanel_BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BarraLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel_RegistroProd)
                .addContainerGap(17, Short.MAX_VALUE))
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

        jComboBox_Categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AA", "SERVICIOS", "FERRETERIA" }));

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

        jPanel_ID2.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_ID2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "STOCK"));

        javax.swing.GroupLayout jPanel_ID2Layout = new javax.swing.GroupLayout(jPanel_ID2);
        jPanel_ID2.setLayout(jPanel_ID2Layout);
        jPanel_ID2Layout.setHorizontalGroup(
            jPanel_ID2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_STOCK, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_ID2Layout.setVerticalGroup(
            jPanel_ID2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ID2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField_STOCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_UM1.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_UM1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(171, 171, 171), 1, true), "DESCRIPCIÓN"));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel_UM1Layout = new javax.swing.GroupLayout(jPanel_UM1);
        jPanel_UM1.setLayout(jPanel_UM1Layout);
        jPanel_UM1Layout.setHorizontalGroup(
            jPanel_UM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UM1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_UM1Layout.setVerticalGroup(
            jPanel_UM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UM1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jButton_Guardar.setText("Guardar");
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });

        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_FondoLayout = new javax.swing.GroupLayout(jPanel_Fondo);
        jPanel_Fondo.setLayout(jPanel_FondoLayout);
        jPanel_FondoLayout.setHorizontalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel_PT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_PG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_DIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_UM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_ID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButton_Guardar)
                        .addGap(31, 31, 31)
                        .addComponent(jButton_Cancelar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel_FondoLayout.setVerticalGroup(
            jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                .addComponent(jPanel_Barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addComponent(jPanel_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_Serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_FondoLayout.createSequentialGroup()
                        .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                                .addComponent(jPanel_UM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel_PG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel_PT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 13, Short.MAX_VALUE))
                            .addGroup(jPanel_FondoLayout.createSequentialGroup()
                                .addComponent(jPanel_ID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel_UM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_DIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
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

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
        guardar();
        LimpiarCampos();
        actualizar();
    }//GEN-LAST:event_jButton_GuardarActionPerformed

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
            java.util.logging.Logger.getLogger(AgregarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    public javax.swing.JComboBox<String> jComboBox_Categoria;
    private javax.swing.JLabel jLabel_RegistroProd;
    private javax.swing.JPanel jPanel_Barra;
    private javax.swing.JPanel jPanel_CB;
    public javax.swing.JPanel jPanel_Categoria;
    private javax.swing.JPanel jPanel_DIM;
    private javax.swing.JPanel jPanel_Fondo;
    public javax.swing.JPanel jPanel_ID;
    private javax.swing.JPanel jPanel_ID2;
    private javax.swing.JPanel jPanel_PG;
    private javax.swing.JPanel jPanel_PT;
    private javax.swing.JPanel jPanel_Serie;
    private javax.swing.JPanel jPanel_UM;
    private javax.swing.JPanel jPanel_UM1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField_CB;
    public javax.swing.JTextField jTextField_DIM;
    public javax.swing.JTextField jTextField_ID;
    public javax.swing.JTextField jTextField_PG;
    public javax.swing.JTextField jTextField_PT;
    public javax.swing.JTextField jTextField_STOCK;
    public javax.swing.JTextField jTextField_Serie;
    public javax.swing.JTextField jTextField_UM;
    // End of variables declaration//GEN-END:variables

}
