import java.sql.*;
import java.util.ArrayList;

public class GestoraBD {

    String sourceURL = "jdbc:sqlserver://localhost";
    String usuario = "amazon";
    String password = "allavoy";
    String baseDeDatos = "ACDAT";
    Connection conexion;

    //Abre la conexión a la base de datos
    public void abrirConexion(){
        try {
            this.conexion = DriverManager.getConnection(sourceURL, usuario, password);
            this.conexion.setCatalog(baseDeDatos);
            System.out.println("Conect");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Cierra la conex
   public void cerrarConexion(){
        try {
            this.conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Devuelve resulsetActualizable de la tablaPedidos
    public ResultSet getResultSetActualizablePedidos(){
        ResultSet resultSet = null;
        Statement sentencia;
        String consultaPedidos = "select P.IDPedido,P.FechaPedido,C.Nombre,C.NombreEmpresa,P.FechaServido from Pedidos as P inner join Clientes as C on P.IDCliente = C.ID where P.FechaServido is null";
        try {
            sentencia = this.conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = sentencia.executeQuery(consultaPedidos);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }


    /*
    *
    * Nombre:ActualizarProductoStock
    * descripcion: Funcion la cual nos actualiza el stock que quedara disponible despues de servir un evio
    * Entradas: el idDelProducto , y la nueva cantidad de stock
    * Salidas: nada
    * */
    public void ActualizarProductoStock(int idProducto,int CantidadNueva){
        ResultSet resultSet = null;
        PreparedStatement sentencia;
        String consultaProductos = "select IDProducto,Stock from Productos where IDProducto = ?";
        try {

            sentencia = conexion.prepareStatement(consultaProductos,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            sentencia.setInt(1, idProducto);
            resultSet = sentencia.executeQuery();

            resultSet.absolute(1);
            resultSet.updateInt("Stock",CantidadNueva);
            resultSet.updateRow();

        } catch (SQLException e) {

            e.printStackTrace();
        }


    }


    /*
     *
     * Nombre:getResulsetObtenerCantidadStockAActualizar
     * descripcion: Funcion que nos da los productos, la cantidad de ellos y el stock q nos queda en el pedido elegido por el user
     * Entradas: Id del pedido
     * Salidas:resulset
     * */
    public ResultSet getResulsetObtenerCantidadStockAActualizar(int id){

        ResultSet resultSet = null;
        PreparedStatement sentencia;
        String consultaProductos = "select Productos.IDProducto,Cantidad,Stock from LineasPedidos inner join Productos on Productos.IDProducto = LineasPedidos.IDProducto where IDPedido = ?";

        try{

            sentencia = conexion.prepareStatement(consultaProductos);
            sentencia.setInt(1,id);
            resultSet = sentencia.executeQuery();

        }catch (SQLException e){

            e.printStackTrace();
        }

        return resultSet;
    }


    /*
     *
     * Nombre:MostrarPedidos
     * descripcion: Muestra los pedidos
     * Entradas: Resulset a mostrar
     * Salidas: Nada
     * */
    public void MostrarPedidos(ResultSet resMostrar){

        int contadorIDPedido = 0;
       try{

           while(resMostrar.next()){

               System.out.println("Pedido: " + contadorIDPedido + " FechaPedido: " + resMostrar.getDate(2) + " NombreCliente: " + resMostrar.getString(3) + " NombreEmpresa: " + resMostrar.getString(4));
               contadorIDPedido++;

           }


       }catch (SQLException e){

           e.printStackTrace();

       }

    }


    /*
     *
     * Nombre:ObtenerIDPedido
     * descripcion: Metodo el cual nos guarda las ids de los pedidos que no estan servidos
     * Entradas:Ningunas
     * Salidas: ArrayList<Integer>
     * */
    public ArrayList<Integer> ObtenerIDPedido(){

        ArrayList<Integer> IDPedidos = new ArrayList<>();

        ResultSet resultSet = null;
        Statement sentencia;
        String consultaPedidos = "select P.IDPedido,P.FechaPedido,C.Nombre,C.NombreEmpresa from Pedidos as P inner join Clientes as C on P.IDCliente = C.ID where P.FechaServido is null";


        try{
            sentencia = this.conexion.createStatement();
            resultSet = sentencia.executeQuery(consultaPedidos);
            while(resultSet.next()){

                IDPedidos.add(resultSet.getInt(1));
            }

            resultSet.close();
        }catch (SQLException e){

            e.printStackTrace();

        }

        return IDPedidos;

    }



    /*
     *
     * Nombre:ComprobarSiPuedePedido
     * descripcion: Comprueba si el pedido puede ser servido
     * Entradas:IdDelPedido
     * Salidas:Bolean
     * */
    public Boolean ComprobarSiPuedePedido(int Idpedido){

        Boolean entra = false;
        ResultSet resultSet = null;
        PreparedStatement sentencia;
        String consultaPedidos = "select IDPedido,Productos.IDProducto,Cantidad,Stock from LineasPedidos inner join Productos on Productos.IDProducto = LineasPedidos.IDProducto where IDPedido = ?";
        try {

            sentencia = conexion.prepareStatement(consultaPedidos);
            sentencia.setInt(1,Idpedido);
            resultSet = sentencia.executeQuery();

            while(resultSet.next()){

                if(resultSet.getInt("Stock") - resultSet.getInt("Cantidad") >= 0){

                    entra = true;
                }

            }
        }catch(SQLException e){
            e.printStackTrace();

        }

        return  entra;
    }


    /*
     *
     * Nombre:ObtenerIdPedido
     * descripcion: Metodo el cual nos proporciona la Id del pedido real de la base datos
     * Entradas: ArrayList y IdPedido
     * Salidas: Entero IdPedido
     * */
    public int ObtenerIdPedido(ArrayList<Integer> IDS,int pedido){

        int IdPedido;

        IdPedido = IDS.get(pedido);

        return IdPedido;


    }

    /*
     *
     * Nombre:ComprobarSiPuedePedidoSust
     * descripcion: Comprueba si el pedido puede ser servido
     * Entradas:IdDelPedido
     * Salidas:Bolean
     * */


}
