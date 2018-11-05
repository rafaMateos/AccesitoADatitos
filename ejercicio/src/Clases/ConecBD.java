package Clases;

import com.sun.corba.se.spi.orbutil.fsm.Guard;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConecBD {

    public Connection iniciarConex(String nombreBaseDatos){

        Connection conex = null;
        String url = " ";
        url="jdbc:sqlserver://localhost;databaseName="+nombreBaseDatos+";user=rmateos;password=123;";

        try{

            conex = DriverManager.getConnection(url);
            System.out.println("Conectado nene");

        }catch(SQLException e){

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
