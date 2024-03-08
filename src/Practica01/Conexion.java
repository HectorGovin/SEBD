package Practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    String bd="sebd";
    String url="jdbc:mysql://localhost:3306/sebd";
    String user="root";
    String password="";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;
    PreparedStatement ps;
    ResultSet rs;

    public Conexion() {
        
    }
    
    public Connection conectar(){
        
        try {
            Class.forName(driver);
            cx=DriverManager.getConnection(url, user, password);
            /*ps = cx.prepareStatement("INSERT INTO USUARIOS (NOM_USU, TELF_USU, CARGO_USU, PSSWD_USU) VALUES (?, ?, ?, ?)");
            ps.setString(1, "JAVIER LOPEZ");
            ps.setString(2, "9384812426");
            ps.setString(3, "TEMP");
            ps.setString(4, "JaLo9583");
            ps.executeUpdate();*/
            System.out.println("CONEXION EXITOSA A "+bd);
        } catch (ClassNotFoundException |SQLException ex) {
            System.out.println("CONEXION FALLIDA A "+bd);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }
    
    public void desconectar(){
        try{
            cx.close();
        } catch (SQLException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
            Conexion conexion=new Conexion();
            conexion.conectar();
    }
    
}

