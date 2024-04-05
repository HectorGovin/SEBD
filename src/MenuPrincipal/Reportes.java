
package MenuPrincipal;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class Reportes {
    
    public static final String URL = "jdbc:mysql://localhost:3306/sebd", USERNAME = "root", PASSWORD = "";
    
    
    MenuPrincipal mp = new MenuPrincipal();
    
    int ID_REP, ID_USU, ID_CLIE;
    double TOTAL;
    String NOTA_REP, FP_REP, DATE_REP;
    String[][] CLIENTES = new String[30][9];
    String[] NOMBRES = new String[9];
    
    public int getID_REP() {return ID_REP;}

    public void setID_REP(int ID_REP) {this.ID_REP = ID_REP;}
    
    public int getID_USU () {return ID_USU;}

    public void setID_USU(int ID_USU) {this.ID_USU = ID_USU;}
    
    public int getID_CLIE() {return ID_CLIE;}

    public void setID_CLIE(int ID_CLIE) {this.ID_CLIE = ID_CLIE;}
    
    public String getNOTA_REP() {return NOTA_REP;}
    
    public void setNOTA_REP(String NOTA_REP) {this.NOTA_REP = NOTA_REP;}
    
    public double getTOTAL_REP() {return TOTAL;}

    public void setTOTAL_REP(double TOTAL) {this.TOTAL = TOTAL;}
    
    public String getDATE_REP() {
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM-dd-yyyy"); 
        Date date = new Date(); 
        String Fecha = DateFormat.format(date); 
        return Fecha;}
    
    public String getFP_REP() {return FP_REP;}
    
    public void setFP_REP(String FP_REP) {this.FP_REP = FP_REP;}
    
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
    
    public void SetDatos(int IDUsu, int IDClie, String Nota, String FP, String Total){
            setID_USU(IDUsu);
            setID_CLIE(IDClie);
            setNOTA_REP(Nota);
            setFP_REP(FP);
            setTOTAL_REP(Double.parseDouble(Total));
            SubirDatosReportes();
    }
    
    public void SubirDatosReportes(){
        try{
            Connection con = null;
            con = getConection();
            PreparedStatement ps;
            ResultSet res;
            ps = con.prepareStatement("INSERT INTO REPORTES (`ID_REP`, `ID_USU`, `NOTA_REP`, `DATE_REP`, `FP_REP`, `ID_CLIE`, `TOTAL_REP`) VALUES ( null, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, getID_USU());
                ps.setString(2, getNOTA_REP());
                ps.setString(3, getDATE_REP());
                ps.setString(4, getFP_REP());
                ps.setInt(5, getID_CLIE());
                ps.setDouble(6, getTOTAL_REP());
            ps.executeUpdate();
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla, error: "+ex.toString());
        }
    }
}
