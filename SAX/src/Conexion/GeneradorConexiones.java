package Conexion;

import java.sql.*;

public class GeneradorConexiones {

    public static Connection getConexion() throws SQLException {
        String url="jdbc:sqlserver://localhost;databaseName=Ejemplos;user=prueba;password=1234;";
        return DriverManager.getConnection(url);
    }
    public static void cerrar ( ResultSet rs ) {
        try{
            rs.close();
        }catch (Exception ignored){}
    }
    public static void cerrar ( Statement st ) {
        try{
            st.close();
        }catch (Exception ignored){}
    }
    public static void cerrar (Connection con) {
        try{
            con.close();
        }catch (Exception ignored){}
    }

}
