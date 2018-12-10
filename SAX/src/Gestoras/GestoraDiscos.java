package Gestoras;

import Clases.Disco;
import Conexion.*;
import java.sql.*;
import java.time.LocalDate;

public class GestoraDiscos
{
    /*
     ***********************************************************************************
     ********************************* C O N E X I O N *********************************
     ***********************************************************************************
     */
    private Connection conexion;
    private ResultSet rsDiscos;
    public GestoraDiscos() throws SQLException {
        conexion = GeneradorConexiones.getConexion();
        rsDiscos = obtenerResultSetDiscos();
    }
    public ResultSet ejecutarQuery(String sentenciaSQL) {
        ResultSet resultado;
        Statement sentencia;
        try{
            sentencia=conexion.createStatement();
            resultado=sentencia.executeQuery(sentenciaSQL);
        } catch(SQLException sql){
            System.out.println(sql.getMessage());
            return null;
        }
        return resultado;
    }


    /*
     ***********************************************************************************
     ********************************** F U N C I O N E S ******************************
     ***********************************************************************************
     */
    //EXAMEN: Ejemplo de Insert con ResultSet
    public boolean agregarDisco(Disco disco) throws SQLException
    {
        boolean creado = false;
        int tamanoPrevio = obtenerTamanoTablaDiscos();

        rsDiscos.moveToInsertRow();
        rsDiscos.updateString("autor", disco.getAutor());
        rsDiscos.updateString("titulo", disco.getTitulo());
        rsDiscos.updateString("formato", disco.getFormato());
        rsDiscos.updateString("localizacion", disco.getLocalizacion());
        rsDiscos.insertRow();
        rsDiscos.moveToCurrentRow();

        if(obtenerTamanoTablaDiscos() == tamanoPrevio+1)
            creado = true;

        return creado;
    }

    private int obtenerTamanoTablaDiscos() throws SQLException
    {
        String sql = "SELECT COUNT(*) AS cont FROM Discos";
        ResultSet rs = ejecutarQuery(sql);
        rs.next();

        return rs.getInt("cont");
    }

    private ResultSet obtenerResultSetDiscos() throws SQLException
    {
        String sql = "SELECT autor, titulo, formato, localizacion FROM Discos";
        Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        return sentencia.executeQuery(sql);
    }

}