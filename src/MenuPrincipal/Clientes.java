/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuPrincipal;

import Practica01.Conexion;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javax.swing.UIManager.getString;
import static javax.swing.UIManager.getInt;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Clientes {

    int ID_CLIE;
    String NOM_CLIE;
    String RFC_CLIE;
    String DIR_CLIE;
    int CP_CLIE;
    String TELF_CLIE;
    String MAIL_CLIE;
    String REGI_CLIE;
    String DIRFIS_CLIE;

    public int getID_CLIE() {
        return ID_CLIE;
    }

    public void setID_CLIE(int ID_CLIE) {
        this.ID_CLIE = ID_CLIE;
    }

    public String getNOM_CLIE() {
        return NOM_CLIE;
    }

    public void setNOM_CLIE(String NOM_CLIE) {
        this.NOM_CLIE = NOM_CLIE;
    }

    public String getRFC_CLIE() {
        return RFC_CLIE;
    }

    public void setRFC_CLIE(String RFC_CLIE) {
        this.RFC_CLIE = RFC_CLIE;
    }

    public String getDIR_CLIE() {
        return DIR_CLIE;
    }

    public void setDIR_CLIE(String DIR_CLIE) {
        this.DIR_CLIE = DIR_CLIE;
    }

    public int getCP_CLIE() {
        return CP_CLIE;
    }

    public void setCP_CLIE(int CP_CLIE) {
        this.CP_CLIE = CP_CLIE;
    }

    public String getTELF_CLIE() {
        return TELF_CLIE;
    }

    public void setTELF_CLIE(String TELF_CLIE) {
        this.TELF_CLIE = TELF_CLIE;
    }

    public String getMAIL_CLIE() {
        return MAIL_CLIE;
    }

    public void setMAIL_CLIE(String MAIL_CLIE) {
        this.MAIL_CLIE = MAIL_CLIE;
    }

    public String getREGI_CLIE() {
        return REGI_CLIE;
    }

    public void setREGI_CLIE(String REGI_CLIE) {
        this.REGI_CLIE = REGI_CLIE;
    }

    public String getDIRFIS_CLIE() {
        return DIRFIS_CLIE;
    }

    public void setDIRFIS_CLIE(String DIRFIS_CLIE) {
        this.DIRFIS_CLIE = DIRFIS_CLIE;
    }
    
    public void InsertarCliente(JTextField paramNOM, JTextField paramRFC, JTextField paramDIR, JTextField paramCP, JTextField paramTELF, JTextField paramMAIL, JTextField paramREGI, JTextField paramDIRFIS){
       
        setNOM_CLIE(paramNOM.getText());
        setRFC_CLIE(paramRFC.getText());
        setDIR_CLIE(paramDIR.getText());
        int CPInt = Integer.parseInt(paramCP.getText());
        setCP_CLIE(CPInt);
        setTELF_CLIE(paramTELF.getText());
        setMAIL_CLIE(paramMAIL.getText());
        setREGI_CLIE(paramREGI.getText());
        setDIRFIS_CLIE(paramDIRFIS.getText());
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "insert into clientes (NOM_CLIE, RFC_CLIE, DIR_CLIE, CP_CLIE, TELF_CLIE, MAIL_CLIE, REGI_CLIE, DIRFIS_CLIE) values (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            
            cs.setString(1, getNOM_CLIE());
            cs.setString(2, getRFC_CLIE());
            cs.setString(3, getDIR_CLIE());
            cs.setInt(4, getCP_CLIE());
            cs.setString(5, getTELF_CLIE());
            cs.setString(6, getMAIL_CLIE());
            cs.setString(7, getREGI_CLIE());
            cs.setString(8, getDIRFIS_CLIE());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se agregó correctamen el cliente");
                    
        } catch (Exception ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo agregar el cliente, error: "+ex.toString());
            
        }
    }
    
    public void SeleccionarClientes(JTable paramTablaClientes, JTextField paramID, JTextField paramNOM, JTextField paramRFC, JTextField paramDIR, JTextField paramCP, JTextField paramTELF, JTextField paramMAIL, JTextField paramREGI, JTextField paramDIRFIS){
        
        try{
            int fila = paramTablaClientes.getSelectedRow();
            if(fila >=0){
                paramID.setText((paramTablaClientes.getValueAt(fila, 0).toString()));
                paramNOM.setText((String) (paramTablaClientes.getValueAt(fila, 1).toString()));
                paramRFC.setText((String) (paramTablaClientes.getValueAt(fila, 2).toString()));
                paramDIR.setText((String) (paramTablaClientes.getValueAt(fila, 3).toString()));
                paramCP.setText((String) (paramTablaClientes.getValueAt(fila, 4).toString()));
                paramTELF.setText((String) (paramTablaClientes.getValueAt(fila, 5).toString()));
                paramMAIL.setText((String) (paramTablaClientes.getValueAt(fila,6).toString()));
                paramREGI.setText((String) (paramTablaClientes.getValueAt(fila, 7).toString()));
                paramDIRFIS.setText((String) (paramTablaClientes.getValueAt(fila, 8).toString()));             
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no encontrada");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+ex.toString());
        }
    }
    
    public void ModificarCliente(JTextField paramID, JTextField paramNOM, JTextField paramRFC, JTextField paramDIR, JTextField paramCP, JTextField paramTELF, JTextField paramMAIL, JTextField paramREGI, JTextField paramDIRFIS){
        
        setID_CLIE(Integer.parseInt(paramID.getText()));
        setNOM_CLIE(paramNOM.getText());
        setRFC_CLIE(paramRFC.getText());
        setDIR_CLIE(paramDIR.getText());
        int CPInt = Integer.parseInt(paramCP.getText());
        setCP_CLIE(CPInt);
        setTELF_CLIE(paramTELF.getText());
        setMAIL_CLIE(paramMAIL.getText());
        setREGI_CLIE(paramREGI.getText());
        setDIRFIS_CLIE(paramDIRFIS.getText());
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "UPDATE clientes SET NOM_CLIE=?, RFC_CLIE=?, DIR_CLIE=?, CP_CLIE=?, TELF_CLIE=?, MAIL_CLIE=?, REGI_CLIE=?, DIRFIS_CLIE=? WHERE ID_CLIE=?;";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
                        
            cs.setString(1, getNOM_CLIE());
            cs.setString(2, getRFC_CLIE());
            cs.setString(3, getDIR_CLIE());
            cs.setInt(4, getCP_CLIE());
            cs.setString(5, getTELF_CLIE());
            cs.setString(6, getMAIL_CLIE());
            cs.setString(7, getREGI_CLIE());
            cs.setString(8, getDIRFIS_CLIE());
            cs.setInt(9, getID_CLIE());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modificó correctamen el cliente");
                    
        } catch (SQLException ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo modificar el cliente, error: "+ex.toString());
            
        }
    }
    
        public void EliminarCliente(String paramID){

        setID_CLIE(Integer.parseInt(paramID));
    
        Conexion objetoConexion = new Conexion();
        
        String consulta = "DELETE FROM clientes WHERE ID_CLIE=?";
        try{
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            cs.setInt(1, ID_CLIE);
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se eliminó correctamente el cliente");
            
        } catch(Exception ex) {
                        
            JOptionPane.showMessageDialog(null,"No se pudo eliminar el cliente, error: "+ex.toString());
            
        }
}
}
