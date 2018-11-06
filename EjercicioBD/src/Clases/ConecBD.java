
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
    
    
    
     public Connection iniciarConex(){
        
        Connection conex = null;
        String url = " ";
<<<<<<< HEAD:ViendoTema/src/Clases/ConecBD.java
        url="jdbc:sqlserver://localhost;databaseName="+nombreBD+";user=rmateos;password=123;";
         System.out.println("Conectado");
=======
        url="jdbc:sqlserver://;database=AlmacenesLeo;integratedSecurity=true;";
>>>>>>> master:EjercicioBD/src/Clases/ConecBD.java
        
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
