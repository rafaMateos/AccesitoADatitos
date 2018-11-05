
package Clases;

import com.sun.corba.se.spi.orbutil.fsm.Guard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author rmateos
 */


public class ConecBD {
    
     public Connection iniciarConex(String nombreBD){
        
        Connection conex = null;
        String url = " ";
        url="jdbc:sqlserver://localhost;databaseName="+nombreBD+";user=rmateos;password=123;";
         System.out.println("Conectado");
        
        try{
        
            conex = DriverManager.getConnection(url);
            
        }catch(SQLException e){
          System.out.println("Conectado");
           e.printStackTrace();
        }
        
        return conex;

    } 
     
      public void cerrar (ResultSet rs) {
          
        try{
            
            rs.close();
            
        }catch (SQLException e){
            
            e.printStackTrace();
        }
    }
    public void cerrar ( Statement st ) {
        try{
            
            st.close();
            
        }catch (SQLException e){
            
            e.printStackTrace();
        }
    }
    public void cerrar (Connection con) {
        try{
            
            con.close();
            
        }catch (SQLException e){
            
            e.printStackTrace();
        }
    }
}
