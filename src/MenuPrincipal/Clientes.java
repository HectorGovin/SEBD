
package MenuPrincipal;

import javax.swing.*;
import java.sql.*;

import Practica01.Conexion;


public class Clientes {
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd", USERNAME = "root", PASSWORD = "";
    
    int ID_CLIE, CP_CLIE;
    String NOM_CLIE, RFC_CLIE, DIR_CLIE, TELF_CLIE, MAIL_CLIE, REGI_CLIE, DIRFIS_CLIE;
    String[][] CLIENTES = new String[30][9];
    String[] NOMBRES = new String[9];
    
    public int getID_CLIE() {return ID_CLIE;}

    public void setID_CLIE(int ID_CLIE) {this.ID_CLIE = ID_CLIE;}
    
    public int getCP_CLIE() {return CP_CLIE;}

    public void setCP_CLIE(int CP_CLIE) {this.CP_CLIE = CP_CLIE;}
    
    public String getNOM_CLIE() {return NOM_CLIE;}
    
    public void setNOM_CLIE(String NOM_CLIE) {this.NOM_CLIE = NOM_CLIE;}
    
    public String getRFC_CLIE() {return RFC_CLIE;}
    
    public void setRFC_CLIE(String RFC_CLIE) {this.RFC_CLIE = RFC_CLIE;}
    
    public String getDIR_CLIE() {return DIR_CLIE;}
    
    public void setDIR_CLIE(String DIR_CLIE) {this.DIR_CLIE = DIR_CLIE;}
    
    public String getTELF_CLIE() {return TELF_CLIE;}
    
    public void setTELF_CLIE(String TELF_CLIE) {this.TELF_CLIE = TELF_CLIE;}

    public String getMAIL_CLIE() {return MAIL_CLIE;}
    
    public void setMAIL_CLIE(String MAIL_CLIE) {this.MAIL_CLIE = MAIL_CLIE;}
    
    public String getREGI_CLIE() {return REGI_CLIE;}
    
    public void setREGI_CLIE(String REGI_CLIE) {this.REGI_CLIE = REGI_CLIE;}
    
    public String getDIRFIS_CLIE() {return DIRFIS_CLIE;}
    
    public void setDIRFIS_CLIE(String DIRFIS_CLIE) {this.DIRFIS_CLIE = DIRFIS_CLIE;}
    
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
    
    public void CargarNombres(JComboBox JCB){
        try{
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            
            /* ACOMPLETAR ESTO DESPUÉS!!!!!
            
            ps = con.prepareStatement("SELECT ID COUNT(*) FROM CLIENTES");
            res = ps.executeQuery();
            
            if(res.next()){
                CLIENTES = new String[res.getInt(CP_CLIE)]
            }*/
            
            ps = con.prepareStatement("SELECT * FROM CLIENTES WHERE ID_CLIE != 1");
            res = ps.executeQuery(); int m = 0;
            while(res.next()){
                NOMBRES[m] = ("" + res.getString("NOM_CLIE"));
                JCB.addItem(NOMBRES[m]);
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void BuscarDatos(String N){
        try{
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("SELECT * FROM CLIENTES WHERE NOM_CLIE='" + N + "'");
            res = ps.executeQuery();
            if(res.next()){
                setID_CLIE( res.getInt("ID_CLIE"));
                setRFC_CLIE(""+res.getString("RFC_CLIE"));
                setCP_CLIE(res.getInt("CP_CLIE"));
                setMAIL_CLIE(res.getString("MAIL_CLIE"));
                setTELF_CLIE("" + res.getString("TELF_CLIE"));
                setDIR_CLIE(""+res.getString("DIR_CLIE"));
                setREGI_CLIE("" + res.getString("REGI_CLIE"));
                setDIRFIS_CLIE("" + res.getString("DIRFIS_CLIE"));
            }
        }catch(SQLException e){
            System.out.println(e);
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
