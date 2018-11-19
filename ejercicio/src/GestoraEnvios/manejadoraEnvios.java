package GestoraEnvios;

import Clases.ConecBD;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public void MostrarPedidoSinAsignar(int id,Statement sentencia){


        try {

            //Utilizar como ejemplos.
            String miOrden = "SELECT * FROM BuscarEnvioSinAsignar(" + id + ")";
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


    public int comprobarAlmacenPreferido(Statement sentencia,int id){

        int ret = 0;

        try {

            //Utilizar como ejemplos.
            String miOrden = "exec ComprobarAlmacenPreferido " + id;
            System.out.println(miOrden);
            ret = sentencia.executeUpdate(miOrden);
            System.out.println(ret);

        }catch (SQLException e){

            e.printStackTrace();

        }

        return ret;

    }

}


