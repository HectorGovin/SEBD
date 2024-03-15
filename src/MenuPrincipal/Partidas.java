
package MenuPrincipal;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class Partidas {
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd", USERNAME = "root", PASSWORD = "";
    
    MenuPrincipal mp = new MenuPrincipal();
    Reportes r = new Reportes();
    
    int ID_PAR, ID_REP, ID_PROD, CAN_PAR;
    double SUBT_PAR, IVA_PAR, TOT_PAR;
    
    String[][] CLIENTES = new String[30][9];
    String[] NOMBRES = new String[9];
    
    public int getID_PAR() {return ID_PAR;}

    public void setID_PAR(int ID_PAR) {this.ID_PAR = ID_PAR;}
    
    /*public int getID_REP() {return ID_REP;}

    public void setID_REP(int ID_REP) {this.ID_REP = ID_REP;}*/
    
    public int getID_PROD() {return ID_PROD;}

    public void setID_PROD(int ID_PROD) {this.ID_PROD = ID_PROD;}
    
    public int getCAN_PAR() {return CAN_PAR;}

    public void setCAN_PAR(int CAN_PAR) {this.CAN_PAR = CAN_PAR;}
    
    public double getSUBT_PAR() { return SUBT_PAR;}

    public void setSUBT_PAR(double SUBT_PAR) { this.SUBT_PAR = SUBT_PAR;}

    public double getIVA_PAR() { return IVA_PAR;}

    public void setIVA_PAR(double IVA_PAR) { this.IVA_PAR = IVA_PAR;}
    
    public double getTOT_PAR() { return TOT_PAR;}

    public void setTOT_PAR(double TOT_PAR) { this.TOT_PAR = TOT_PAR;}
    
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
    
    public void S(){
        mp.EnviarPRODUCTOSaPartidas();
            System.out.println("\n\n");
        for(int i = 0; mp.PRODUCTOS[i][9] != null; i++){
            System.out.println("\nSí pasó por aqui");
            SetDatos(mp.PRODUCTOS[i][9], mp.PRODUCTOS[i][7], Double.parseDouble("5"), Double.parseDouble("5"), Double.parseDouble(mp.PRODUCTOS[i][8]));
            SubirDatosPartidas();
        mp.Limpiar();
        }
    }
    
    public void SetDatos(String IDProd, String Cant, double SubT, double IVA, double Tot){
        setID_PROD(Integer.parseInt(IDProd));
        setCAN_PAR(Integer.parseInt(Cant));
        setSUBT_PAR(SubT);
        setIVA_PAR(IVA);
        setTOT_PAR(Tot);
    }
    
    public void SubirDatosPartidas(){
        try{
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("INSERT INTO PARTIDAS (`ID_REP`, `ID_PROD`, `CAN_PAR`, `SUBT_PAR`, `IVA_PAR`, `TOT_PAR`) VALUES ( ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, r.getID_REP());
            ps.setInt(2, getID_PROD());
            ps.setInt(3, getCAN_PAR());
            ps.setDouble(4, getSUBT_PAR());
            ps.setDouble(5, getIVA_PAR());
            ps.setDouble(6, getTOT_PAR());
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla, error: "+ex.toString());
        }
    }
    
    /*public void SetDatos(int IDUsu, int IDClie, String Nota, String FP){
            setID_USU(IDUsu);
            setID_CLIE(IDClie);
            setNOTA_REP(Nota);
            setFP_REP(FP);
    }
    
    public void SubirDatosReportes(){
        try{
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("INSERT INTO REPORTES (`ID_REP`, `ID_USU`, `NOTA_REP`, `DATE_REP`, `FP_REP`, `ID_CLIE`) VALUES ( null, ?, ?, ?, ?, ?)");
                ps.setInt(1, getID_USU());
                ps.setString(2, getNOTA_REP());
                ps.setString(3, getDATE_REP());
                ps.setString(4, getFP_REP());
                ps.setInt(5, getID_CLIE());
            ps.executeUpdate();
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla, error: "+ex.toString());
        }
    }*/
    
    
}
