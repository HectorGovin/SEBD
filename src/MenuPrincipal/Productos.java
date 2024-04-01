/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuPrincipal;

import Practica01.Conexion;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.UIManager.getString;
import static javax.swing.UIManager.getInt;
import static javax.swing.UIManager.getString;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Productos {
    
    int ID_PROD;
    String CODIGO_PROD;
    String SERIE_PROD;
    String CAT_PROD;
    String UM_PROD;
    String DES_PROD;
    double PRG_PROD;
    double PRT_PROD;
    String DIM_PROD;
    int STOCK_PROD;

    public int getID_PROD() {
        return ID_PROD;
    }

    public void setID_PROD(int ID_PROD) {
        this.ID_PROD = ID_PROD;
    }

    public String getCODIGO_PROD() {
        return CODIGO_PROD;
    }

    public void setCODIGO_PROD(String CODIGO_PROD) {
        this.CODIGO_PROD = CODIGO_PROD;
    }

    public String getSERIE_PROD() {
        return SERIE_PROD;
    }

    public void setSERIE_PROD(String SERIE_PROD) {
        this.SERIE_PROD = SERIE_PROD;
    }

    public String getCAT_PROD() {
        return CAT_PROD;
    }

    public void setCAT_PROD(String CAT_PROD) {
        this.CAT_PROD = CAT_PROD;
    }

    public String getUM_PROD() {
        return UM_PROD;
    }

    public void setUM_PROD(String UM_PROD) {
        this.UM_PROD = UM_PROD;
    }

    public String getDES_PROD() {
        return DES_PROD;
    }

    public void setDES_PROD(String DES_PROD) {
        this.DES_PROD = DES_PROD;
    }

    public double getPRG_PROD() {
        return PRG_PROD;
    }

    public void setPRG_PROD(double PRG_PROD) {
        this.PRG_PROD = PRG_PROD;
    }

    public double getPRT_PROD() {
        return PRT_PROD;
    }

    public void setPRT_PROD(double PRT_PROD) {
        this.PRT_PROD = PRT_PROD;
    }

    public String getDIM_PROD() {
        return DIM_PROD;
    }

    public void setDIM_PROD(String DIM_PROD) {
        this.DIM_PROD = DIM_PROD;
    }

    public int getSTOCK_PROD() {
        return STOCK_PROD;
    }

    public void setSTOCK_PROD(int STOCK_PROD) {
        this.STOCK_PROD = STOCK_PROD;
    }
    
    public void InsertarProducto(JTextField paramCB, JTextField paramSerie, JComboBox paramCat, JTextField paramUM, JTextArea paramDes, JTextField paramPRG, JTextField paramPRT, JTextField paramDIM, JTextField paramStock){
       
        setCODIGO_PROD(paramCB.getText());
        setSERIE_PROD(paramSerie.getText());
        setCAT_PROD(""+paramCat.getSelectedItem());
        setUM_PROD(paramUM.getText());
        setDES_PROD(paramDes.getText());
        double PRGDouble = Double.parseDouble(paramPRG.getText());
        setPRG_PROD(PRGDouble);
        double PRTDouble = Double.parseDouble(paramPRT.getText());
        setPRT_PROD(PRTDouble);
        setDIM_PROD(paramDIM.getText());
        int STOCKInt = Integer.parseInt(paramStock.getText());
        setSTOCK_PROD(STOCKInt);
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "insert into productos (CODIGO_PROD, SERIE_PROD, CAT_PROD, UM_PROD, DES_PROD, PRG_PROD, PRT_PROD, DIM_PROD, STOCK_PROD) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            
            cs.setString(1, getCODIGO_PROD());
            cs.setString(2, getSERIE_PROD());
            cs.setString(3, getCAT_PROD());
            cs.setString(4, getUM_PROD());
            cs.setString(5, getDES_PROD());
            cs.setDouble(6, getPRG_PROD());
            cs.setDouble(7, getPRT_PROD());
            cs.setString(8, getDIM_PROD());
            cs.setInt(9, getSTOCK_PROD());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se agregó correctamen el producto");
                    
        } catch (Exception ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo agregar el producto, error: "+ex.toString());
            
        }
    }
    
    public void InsertarServicio(JComboBox paramCat, JTextArea paramDes, JTextField paramPRG){
       
        setCAT_PROD(""+paramCat.getSelectedItem());
        setDES_PROD(paramDes.getText());
        double PRGDouble = Double.parseDouble(paramPRG.getText());
        setPRG_PROD(PRGDouble);

        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "insert into productos (CAT_PROD, DES_PROD, PRG_PROD) values (?, ?, ?)";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            
            cs.setString(1, getCAT_PROD());
            cs.setString(2, getDES_PROD());
            cs.setDouble(3, getPRG_PROD());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se agregó correctamen el servicio");
                    
        } catch (Exception ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo agregar el servicio, error: "+ex.toString());
            
        }
    }
    
    public void MostrarProductos(JTable paramTablaTotalProductos){
        
        Conexion objetoConexion = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalProductos.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        modelo.addColumn("ID");
        modelo.addColumn("CODIGO DE BARRAS");
        modelo.addColumn("SERIE");
        modelo.addColumn("CATEGORIA");
        modelo.addColumn("UNIDAD DE MEDIDA");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("PRECIO GENERAL");
        modelo.addColumn("PRECIO TECNICO");
        modelo.addColumn("DIMENSIONES");
        modelo.addColumn("CANTIDAD");
        
        paramTablaTotalProductos.setModel(modelo);
        
        sql="select * from productos";
        
        String[] datos = new String [10];
        Statement st;
        
        try{
            st= objetoConexion.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                datos[8]=rs.getString(9);
                datos[9]=rs.getString(10);
                
                modelo.addRow(datos);
                
                paramTablaTotalProductos.setModel(modelo);
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla, error: "+ex.toString());
        }
        
    }
    
    public void SeleccionarProductos(JTable paramTablaProductos, JTextField paramID, JTextField paramCB, JTextField paramSerie, JComboBox paramCat, JTextField paramUM, JTextArea paramDes, JTextField paramPRG, JTextField paramPRT, JTextField paramDIM, JTextField paramStock){
        
        try{
            int fila = paramTablaProductos.getSelectedRow();
            if(fila >=0){
                paramID.setText((paramTablaProductos.getValueAt(fila, 0).toString()));
                paramCB.setText((String) (paramTablaProductos.getValueAt(fila, 1).toString()));
                paramSerie.setText((String) (paramTablaProductos.getValueAt(fila, 2).toString()));
                paramCat.setSelectedItem((String) (paramTablaProductos.getValueAt(fila, 3).toString()));
                paramDes.setText((String) (paramTablaProductos.getValueAt(fila, 4).toString()));
                paramPRG.setText((String) (paramTablaProductos.getValueAt(fila, 5).toString()));
                paramPRT.setText((String) (paramTablaProductos.getValueAt(fila,6).toString()));
                paramUM.setText((String) (paramTablaProductos.getValueAt(fila, 7).toString()));
                paramDIM.setText((String) (paramTablaProductos.getValueAt(fila, 8).toString()));
                paramStock.setText((String) (paramTablaProductos.getValueAt(fila, 9).toString()));             
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no encontrada");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+ex.toString());
        }
    }
    
    public void ModificarProducto(String paramID, JTextField paramCB, JTextField paramSerie, JComboBox paramCat, JTextField paramUM, JTextArea paramDes, JTextField paramPRG, JTextField paramPRT, JTextField paramDIM, JTextField paramStock){
        setID_PROD(Integer.parseInt(paramID));
        setCODIGO_PROD(paramCB.getText());
        setSERIE_PROD(paramSerie.getText());
        setCAT_PROD(""+paramCat.getSelectedItem());
        setUM_PROD(paramUM.getText());
        setDES_PROD(paramDes.getText());
        double PRGDouble = Double.parseDouble(paramPRG.getText());
        setPRG_PROD(PRGDouble);
        double PRTDouble = Double.parseDouble(paramPRT.getText());
        setPRT_PROD(PRTDouble);
        setDIM_PROD(paramDIM.getText());
        int STOCKInt = Integer.parseInt(paramStock.getText());
        setSTOCK_PROD(STOCKInt);
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "UPDATE productos SET CODIGO_PROD=?, SERIE_PROD=?, CAT_PROD=?, UM_PROD=?, DES_PROD=?, PRG_PROD=?, PRT_PROD=?, DIM_PROD=?, STOCK_PROD=? WHERE ID_PROD=?;";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            
            cs.setString(1, getCODIGO_PROD());
            cs.setString(2, getSERIE_PROD());
            cs.setString(3, getCAT_PROD());
            cs.setString(4, getUM_PROD());
            cs.setString(5, getDES_PROD());
            cs.setDouble(6, getPRG_PROD());
            cs.setDouble(7, getPRT_PROD());
            cs.setString(8, getDIM_PROD());
            cs.setInt(9, getSTOCK_PROD());
            cs.setInt(10, getID_PROD());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modificó correctamen el producto");
                    
        } catch (SQLException ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo modificar el producto, error: "+ex.toString());
            
        }
    }
    
    public void SeleccionarServicios(JTable paramTablaProductos, JTextField paramID, JTextArea paramDes, JComboBox paramCat, JTextField paramPRG){
        
        try{
            int fila = paramTablaProductos.getSelectedRow();
            if(fila >=0){
                paramID.setText((paramTablaProductos.getValueAt(fila, 0).toString()));
                paramDes.setText((String) (paramTablaProductos.getValueAt(fila, 1).toString())); 
                paramCat.setSelectedItem((String) (paramTablaProductos.getValueAt(fila, 2).toString()));
                paramPRG.setText((String) (paramTablaProductos.getValueAt(fila, 3).toString()));
                
                               
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no encontrada");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+ex.toString());
        }
    }
    
    public void ModificarServicio(JTextField paramID, JComboBox paramCat, JTextArea paramDes, JTextField paramPRG){
        
        setID_PROD(Integer.parseInt(paramID.getText()));
        setCAT_PROD(""+paramCat.getSelectedItem());
        setDES_PROD(paramDes.getText());
        double PRGDouble = Double.parseDouble(paramPRG.getText());
        setPRG_PROD(PRGDouble);
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "UPDATE productos SET CAT_PROD=?, DES_PROD=?, PRG_PROD=? WHERE ID_PROD=?;";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            
            cs.setString(1, getCAT_PROD());
            cs.setString(2, getDES_PROD());
            cs.setDouble(3, getPRG_PROD());
            cs.setInt(4, getID_PROD());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modificó correctamen el servicio");
                    
        } catch (SQLException ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo modificar el servicio, error: "+ex.toString());
            
        }
    }
    
    
        public void EliminarProducto(String paramID){

        setID_PROD(Integer.parseInt(paramID));
    
        Conexion objetoConexion = new Conexion();
        
        String consulta = "DELETE FROM productos WHERE ID_PROD=?";
        try{
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            cs.setInt(1, ID_PROD);
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se eliminó correctamente el producto");
            
        } catch(Exception ex) {
                        
            JOptionPane.showMessageDialog(null,"No se pudo eliminar el producto, error: "+ex.toString());
            
        }        
    }
        
    public void ModificarProducto(JTextField paramID, JTextField paramCB, JTextField paramSerie, JComboBox paramCat, JTextField paramUM, JTextArea paramDes, JTextField paramPRG, JTextField paramPRT, JTextField paramDIM, JTextField paramStock){
        
        setID_PROD(Integer.parseInt(paramID.getText()));
        setCODIGO_PROD(paramCB.getText());
        setSERIE_PROD(paramSerie.getText());
        setCAT_PROD(""+paramCat.getSelectedItem());
        setUM_PROD(paramUM.getText());
        setDES_PROD(paramDes.getText());
        double PRGDouble = Double.parseDouble(paramPRG.getText());
        setPRG_PROD(PRGDouble);
        double PRTDouble = Double.parseDouble(paramPRT.getText());
        setPRT_PROD(PRTDouble);
        setDIM_PROD(paramDIM.getText());
        int STOCKInt = Integer.parseInt(paramStock.getText());
        setSTOCK_PROD(STOCKInt);
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "UPDATE productos SET CODIGO_PROD=?, SERIE_PROD=?, CAT_PROD=?, UM_PROD=?, DES_PROD=?, PRG_PROD=?, PRT_PROD=?, DIM_PROD=?, STOCK_PROD=? WHERE ID_PROD=?;";
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
                        
            cs.setString(1, getCODIGO_PROD());
            cs.setString(2, getSERIE_PROD());
            cs.setString(3, getCAT_PROD());
            cs.setString(4, getUM_PROD());
            cs.setString(5, getDES_PROD());
            cs.setDouble(6, getPRG_PROD());
            cs.setDouble(7, getPRT_PROD());
            cs.setString(8, getDIM_PROD());
            cs.setInt(9, getSTOCK_PROD());
            cs.setInt(10, getID_PROD());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modificó correctamen el producto");
                    
        } catch (SQLException ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo modificar el producto, error: "+ex.toString());
            
        }
    }
    
    public void CB(String paramCB){
        
        Image img;
        
        setCODIGO_PROD(paramCB);
    
        Conexion objetoConexion = new Conexion();
        
        String consulta = "select * from productos";
        Statement st;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(paramCB+".pdf"));
        
        int userSelection = fileChooser.showSaveDialog(null);
        
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
        
        try {
            
            st= objetoConexion.conectar().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            getString(paramCB);
            
            Document doc = new Document();
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            doc.open();

            ColumnText column1 = new ColumnText(pdf.getDirectContent());
            ColumnText column2 = new ColumnText(pdf.getDirectContent());
            ColumnText column3 = new ColumnText(pdf.getDirectContent());
            
            column1.setSimpleColumn(36, 50, 150, 800);
            column2.setSimpleColumn(350, 50, 236, 800);
            column3.setSimpleColumn(550, 50, 436, 800);

            for(int i = 0; i < 12; i++){
            Barcode128 code = new Barcode128();
            code.setCode(paramCB);
            img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
            
            Paragraph paragraph = new Paragraph(" ");
            
            column1.addElement(img);
            column1.addElement(paragraph);
            
            column2.addElement(img);
            column2.addElement(paragraph);
            
            column3.addElement(img);
            column3.addElement(paragraph);
                        
            }
            column1.go();
            column2.go();
            column3.go();
            
            doc.close();
            
            JOptionPane.showMessageDialog(null,"Se guardó correctamente el código de barras");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   }
}
