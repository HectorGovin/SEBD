
package MenuPrincipal;

import javax.swing.*;
import java.sql.*;



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
            
            ps = con.prepareStatement("SELECT * FROM CLIENTES");
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
    
}
