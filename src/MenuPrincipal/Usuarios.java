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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPasswordField;
import static javax.swing.UIManager.getString;
import static javax.swing.UIManager.getInt;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Usuarios {
    
    int ID_USU;
    String NOMBRE_USU;
    String TELEF_USU;
    String CARGO_USU;
    String PSSWD_USU;

    public int getID_USU() {
        return ID_USU;
    }

    public void setID_USU(int ID_USU) {
        this.ID_USU = ID_USU;
    }

    public String getNOMBRE_USU() {
        return NOMBRE_USU;
    }

    public void setNOMBRE_USU(String NOMBRE_USU) {
        this.NOMBRE_USU = NOMBRE_USU;
    }

    public String getTELEF_USU() {
        return TELEF_USU;
    }

    public void setTELEF_USU(String TELEF_USU) {
        this.TELEF_USU = TELEF_USU;
    }

    public String getCARGO_USU() {
        return CARGO_USU;
    }

    public void setCARGO_USU(String CARGO_USU) {
        this.CARGO_USU = CARGO_USU;
    }

    public String getPSSWD_USU() {
        return PSSWD_USU;
    }

    public void setPSSWD_USU(String PSSWD_USU) {
        this.PSSWD_USU = PSSWD_USU;
    }

    
    public void InsertarUsuario(JTextArea paramNOM, JTextField paramTEL, JTextField paramCARGO, JTextField paramCONTRA){
       
        setNOMBRE_USU(paramNOM.getText());
        setTELEF_USU(paramTEL.getText());
        setCARGO_USU(paramCARGO.getText());
        setPSSWD_USU(paramCONTRA.getText());

        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "insert into usuarios (NOM_USU, TELF_USU, CARGO_USU, PSSWD_USU) values (?, ?, ?, ?)";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            
            cs.setString(1, getNOMBRE_USU());
            cs.setString(2, getTELEF_USU());
            cs.setString(3, getCARGO_USU());
            cs.setString(4, getPSSWD_USU());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se agregó correctamen el usuario");
                    
        } catch (Exception ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo agregar el usuario, error: "+ex.toString());
            
        }
    }
    
        public void SeleccionarUsuarios(JTable paramTablaUsuarios, JTextField paramID, JTextArea paramNOM, JTextField paramTEL, JTextField paramCARGO, JTextField paramCONTRA){
        
        try{
            int fila = paramTablaUsuarios.getSelectedRow();
            if(fila >=0){
                paramID.setText((paramTablaUsuarios.getValueAt(fila, 0).toString()));
                paramNOM.setText((String) (paramTablaUsuarios.getValueAt(fila, 1).toString()));
                paramTEL.setText((String) (paramTablaUsuarios.getValueAt(fila, 2).toString()));
                paramCARGO.setText((String) (paramTablaUsuarios.getValueAt(fila, 3).toString()));
                paramCONTRA.setText((String) (paramTablaUsuarios.getValueAt(fila, 4).toString()));          
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no encontrada");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+ex.toString());
        }
    }
    
    public void ModificarUsuario(JTextField paramID, JTextArea paramNOM, JTextField paramTEL, JTextField paramCARGO, JTextField paramCONTRA){
        
        setID_USU(Integer.parseInt(paramID.getText()));
        setNOMBRE_USU(paramNOM.getText());
        setTELEF_USU(paramTEL.getText());
        setCARGO_USU(paramCARGO.getText());
        setPSSWD_USU(paramCONTRA.getText());
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "UPDATE usuarios SET NOM_USU=?, TELF_USU=?, CARGO_USU=?, PSSWD_USU=? WHERE ID_USU=?;";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            
            cs.setString(1, getNOMBRE_USU());
            cs.setString(2, getTELEF_USU());
            cs.setString(3, getCARGO_USU());
            cs.setString(4, getPSSWD_USU());
            cs.setInt(5, getID_USU());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modificó correctamen el usuario");
                    
        } catch (SQLException ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo modificar el usuario, error: "+ex.toString());
            
        }
    }
    
        public void EliminarUsuario(String paramID){

        setID_USU(Integer.parseInt(paramID));
    
        Conexion objetoConexion = new Conexion();
        
        String consulta = "DELETE FROM usuarios WHERE ID_USU=?";
        try{
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            cs.setInt(1, ID_USU);
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se eliminó correctamente el usuario");
            
        } catch(Exception ex) {
                        
            JOptionPane.showMessageDialog(null,"No se pudo eliminar el usuario, error: "+ex.toString());
            
        }
    }
        
        public void RellenarComboBox(JComboBox combo){
            String sql = "select * from usuarios";
            Statement st;
            Conexion objetoConexion = new Conexion();
            Connection conexion = objetoConexion.conectar();
            try{
                
                st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    combo.addItem(rs.getString("NOM_USU"));
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error: "+ex.toString());
            }
        }
}
