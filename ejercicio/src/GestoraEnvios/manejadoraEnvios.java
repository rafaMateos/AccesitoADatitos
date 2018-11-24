package GestoraEnvios;

import Clases.ConecBD;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class manejadoraEnvios {


    /*
    * Nombre: MostrarEnvios
    * Descripcion: Funcion la cual nos mostrara todos los pedidos
    * que tenemos en la base de datos.
    * Entradas: Statement sentencia
    * Salidas: No hay
    *
    * */
    public void MostrarEnvios(Statement sentencia){

        try {

            //Utilizar como ejemplos.
            String miOrden = "SELECT * FROM ListadoEnvios()";

            ResultSet res = sentencia.executeQuery(miOrden);

            while (res.next()){

                System.out.println("ID: " + res.getInt(1) + " Numero de contenedores: " + res.getString(2)
                + " FechaCreacion: " + res.getDate(3) + " FechaAsignacion: " + res.getDate(4));
            }



        }catch (SQLException e){

            e.printStackTrace();

        }

    }

    /*
    * Nombre: MostrarPedididosSinAsignar
    * Descripcion:Funcion la cual nos mostrara todos los pedididos sin asignar
    * Entradas: Statement Sentencia
    * Salidas: No hay
    * */
    public void MostrarPedididosSinAsignar(Statement sentencia){

        try {

            //Utilizar como ejemplos.
            String miOrden = "SELECT * FROM func_Mostrar_Pedidos_Sin_Asignar()";

            ResultSet res = sentencia.executeQuery(miOrden);

            while (res.next()){

                System.out.println("ID: " + res.getInt(1) + " Numero de contenedores: " + res.getString(2)
                        + " FechaCreacion: " + res.getDate(3) + " FechaAsignacion: " + res.getDate(4));
            }



        }catch (SQLException e){

            e.printStackTrace();

        }

    }


    /*
    * Nombre MostrarPedidoSinAsignar
    * Descripcion: Funcion la cual nos buscara y mostrara el pedido sin asignar que queramos.
    * Entradas:Statement sentencia, id del pedido
    * Salidas: no hay
    * */
    public ResultSet MostrarPedidoSinAsignar(int id,Statement sentencia){

        ResultSet res = null;

        try {

            //Utilizar como ejemplos.
            String miOrden = "SELECT * FROM BuscarEnvioSinAsignar(" + id + ")";
             res = sentencia.executeQuery(miOrden);


            while (res.next()){

                    System.out.println("ID: " + res.getInt(1) + " Numero de contenedores: " + res.getString(2)
                            + " FechaCreacion: " + res.getDate(3) + " FechaAsignacion: " + res.getDate(4));
                }

        }catch (SQLException e){

            e.printStackTrace();

        }

        return res;

    }


    /*
    * Nombre: MostrarAlmacenesConCapacidadLibre
    *  Descripcion: Funcion la cual nos mostrara la capacidad libre de cada almacen
    *  Entradas: No hay
    *  Salidas:No hay
    */

    public void MostrarAlmacenesConCapacidadLibre(Statement sentencia){

        try {

            //Utilizar como ejemplos.
            String miOrden = "SELECT * FROM MostrarAlmacenesConCapacidadLibre()";
            ResultSet res = sentencia.executeQuery(miOrden);
            while (res.next()){

                System.out.println("ID: " + res.getInt(1) + " Capacidad:  " + res.getInt(2)
                        + " Ocupado: " + res.getInt(3) + " Disponible: " + res.getInt(4));
            }


        }catch (SQLException e){

            e.printStackTrace();

        }

    }



    /*
     * Nombre: MostrarAlmacenConCapacidadLibre
     *  Descripcion: Funcion la cual nos mostrara la capacidad libre de cada almacen
     *  Entradas: No hay
     *  Salidas:No hay
     */

    public void MostrarAlmacenConCapacidadLibre(Statement sentencia,int id){


        try {

            //Utilizar como ejemplos.
            String miOrden = "SELECT * FROM MostrarAlmacenConCapacidadLibre(" + id + ")";
            ResultSet res = sentencia.executeQuery(miOrden);
            while (res.next()){

                System.out.println("ID: " + res.getInt(1) + " Capacidad:  " + res.getInt(2)
                        + " Ocupado: " + res.getInt(3) + " Disponible: " + res.getInt(4));
            }


        }catch (SQLException e){

            e.printStackTrace();

        }


    }



    /*
     * Nombre: ComprobarAlmacen
     *  Descripcion: Funcion la cual nos comprobara lsi se puede asignar un almacen
     *  Entradas: No hay
     *  Salidas:Codigo de error o asignacion
     */

    public int ComprobarAlmacen(Statement sentencia,int id,int capacidad){

        int ret = 0;
        try {

            //Utilizar como ejemplos.
            String miOrden = "exec ComprobarAlmacnes "+id+capacidad;
            System.out.println(miOrden);

            ResultSet res = sentencia.executeQuery(miOrden);

            ret = res.getInt(1);

        }catch (SQLException e){

            e.printStackTrace();

        }

        return ret;

    }


    public int comprobarAlmacenPreferido(Connection conex,int id){

        int ret = 0;
        CallableStatement senllamable;
        try {

            //Utilizar como ejemplos.
            String miOrden = "exec ComprobarAlmacenPreferido ? ,?";
            senllamable = conex.prepareCall(miOrden);
            senllamable.setInt(1,id);
            senllamable.registerOutParameter(2, Types.INTEGER);
            senllamable.executeUpdate();
            ret = senllamable.getInt(2);

        }catch (SQLException e){

            e.printStackTrace();

        }

        return ret;


        /*
        *
        * String sql = “Execute ObtenerGanador ?, ?”;
            CallableStatement senllamable =
            conexion.prepareCall (sql);
            senllamable.setInt (1, 13);
            senllamable.registerOutParameter
            (2,java.sql.Types.VARCHAR);
            senllamable.executeUpdate();
            String valorDevuelto = senllamable.getString(2);
        *
        * */

    }


    public int comprobarSiEnvioAsignado(Connection conex,int id){

        int ret = 0;
        CallableStatement senllamable;
        try {
            //Utilizar como ejemplos.
            String miOrden = "exec ComprobarSiAlmacenAsignado ? ,?";
            senllamable = conex.prepareCall(miOrden);
            senllamable.setInt(1,id);
            senllamable.registerOutParameter(2, Types.INTEGER);
            senllamable.executeUpdate();
            ret = senllamable.getInt(2);
        }catch (SQLException e){

            e.printStackTrace();
        }

        return ret;


    }

    public int ActualizarAsignacion(Connection conex, int id){

        java.util.Date d = new java.util.Date();
        SimpleDateFormat plantilla = new SimpleDateFormat("dd/MM/yyyy H:mm");
        String tiempo = plantilla.format(d);
        java.sql.Date date2 = new java.sql.Date(d.getTime());


        ResultSet res;

        int ret = 0;

        String sqlSelect = "Select ID,NumeroContenedores,FechaCreacion,FechaAsignacion,AlmacenPreferido from Envios where id =?";
        PreparedStatement sent;

        try{
            //Buscamos el pedido que quiere asignar
        sent = conex.prepareStatement(sqlSelect,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        sent.setInt(1,id);
        res = sent.executeQuery();

        //Actualizamos el resulter indicado
        res.absolute(1);
        res.updateDate("FechaAsignacion",date2);
        res.updateRow();

        }catch (SQLException e){

            e.printStackTrace();
        }

        return ret;

    }

    public int AlmacenMasCercano(Connection conex,int idEnvio,int idAlmacenPrefe){

        int ret = 0;
        CallableStatement senllamable;
        try {

            //Utilizar como ejemplos.
            String miOrden = "exec BuscarAlmacenMasCercano ? ,? ,?";
            senllamable = conex.prepareCall(miOrden);
            senllamable.setInt(1,idEnvio);
            senllamable.setInt(2,idAlmacenPrefe);
            senllamable.registerOutParameter(3, Types.INTEGER);
            senllamable.executeUpdate();
            ret = senllamable.getInt(3);

        }catch (SQLException e){

            e.printStackTrace();

        }

        return ret;


    }

    public int ObtenerAlmacenPreferido(Connection conex,int idEnvio){

        int ret = 0;
        CallableStatement senllamable;
        try {

            //Utilizar como ejemplos.
            String miOrden = "exec ObtenerAlmacenPreferido ? ,?";
            senllamable = conex.prepareCall(miOrden);
            senllamable.setInt(1,idEnvio);
            senllamable.registerOutParameter(2, Types.INTEGER);
            senllamable.executeUpdate();
            ret = senllamable.getInt(2);

        }catch (SQLException e){

            e.printStackTrace();

        }

        return ret;

    }

    public void InsertPedidoAsignacion(Connection conex,int idEnvio,int IdAlmacen){


        PreparedStatement senIsertPrep;
        String miOrden = "insert into Asignaciones(IDEnvio,IDAlmacen) Values(?,?)";

        try {
            senIsertPrep = conex.prepareStatement(miOrden);
            senIsertPrep.setInt(1,idEnvio);
            senIsertPrep.setInt(2,IdAlmacen);
            senIsertPrep.executeUpdate();

        }catch (SQLException e){

            e.printStackTrace();

        }





    }


    public int ComprobarAlmCercano(Connection conex,int id,int almacenCercano){

        int ret = 0;
        CallableStatement senllamable;
        try {

            //Utilizar como ejemplos.
            String miOrden = "exec ComprobarAlmacenCercano ? ,? ,?";
            senllamable = conex.prepareCall(miOrden);
            senllamable.setInt(1,id);
            senllamable.setInt(2,almacenCercano);
            senllamable.registerOutParameter(3, Types.INTEGER);
            senllamable.executeUpdate();
            ret = senllamable.getInt(3);

        }catch (SQLException e){

            e.printStackTrace();

        }

        return ret;


        /*
        *
        * String sql = “Execute ObtenerGanador ?, ?”;
            CallableStatement senllamable =
            conexion.prepareCall (sql);
            senllamable.setInt (1, 13);
            senllamable.registerOutParameter
            (2,java.sql.Types.VARCHAR);
            senllamable.executeUpdate();
            String valorDevuelto = senllamable.getString(2);
        *
        * */

    }


    public ArrayList<Integer> ArrayIdsEnvios(Connection conex){

        ArrayList<Integer> ret = new ArrayList<>();
        Statement sentencia;

        try {
            sentencia = conex.createStatement();

            String miOrden = "select Id from envios where FechaAsignacion is null ";

            ResultSet res = sentencia.executeQuery(miOrden);

            while (res.next()){

                ret.add(res.getInt(1));

            }



        }catch (SQLException e){

            e.printStackTrace();

        }

        return ret;

    }

    public boolean ValidarEnvio(ArrayList<Integer> Ids,int envio){

        boolean ret = false;

       List<Integer> Array = Ids;
       if(Ids.contains(envio)){ ret = true; }

        return ret;

    }

}


