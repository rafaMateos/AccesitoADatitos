import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    //Espero demostrar con lo realizado que tengo los conocimientos suficientes para poder aprobar leo. Creo que se aprecia q lo que estoy haciendo.
    public static void main(String[] args) {

        //Declaracion Varibales
        Scanner teclado = new Scanner(System.in);
        GestoraBD gestoraBD = new GestoraBD();
        int PedidoServir;
        int IdPedido;
        int Stock;
        int Producto;
        Boolean ComprobarSiPuedePedido;
        ResultSet rsActuPedidos;
        ResultSet rsStockProducto;
        ArrayList<Integer> IDpedidos = new ArrayList<>();

        //Obetenemos resulsets y abrimos conexciones
        gestoraBD.abrirConexion();
        rsActuPedidos = gestoraBD.getResultSetActualizablePedidos();
        IDpedidos = gestoraBD.ObtenerIDPedido();

        //Mostramos y recogemos el pedido que sea elegido
        gestoraBD.MostrarPedidos(rsActuPedidos);
        System.out.println("Dime el pedido que quieres servir");
        PedidoServir = teclado.nextInt();


        //Busco la id del Pedido que quiere servir
        IdPedido = gestoraBD.ObtenerIdPedido(IDpedidos,PedidoServir);

        //Compruebo si Puedo servir el pedido
        ComprobarSiPuedePedido = gestoraBD.ComprobarSiPuedePedido(IdPedido);


        //Si puedoIntroducir el pedido
        if(ComprobarSiPuedePedido){

            try{

                //Cast de la fecha
                java.util.Date d = new java.util.Date();
                SimpleDateFormat plantilla = new SimpleDateFormat("dd/MM/yyyy H:mm");
                String tiempo = plantilla.format(d);
                java.sql.Date date2 = new java.sql.Date(d.getTime());

                //Perdon por la chapuza del +1 pero no me di cuenta de que el array empieza en 0
                //Actualizo la fecha de servido
                //Le sumo uno para que , porque la variable PedidoServir es el numero de Pedido que muestro, entonces si le sumo uno a esa
                //variable se posicionara en la fila del resulset que quiero que actualize.
                //Espero que lo comprendas. :)), porque se q me explico como el culo
                rsActuPedidos.absolute(PedidoServir + 1);
                rsActuPedidos.updateDate("FechaServido",date2);
                rsActuPedidos.updateRow();


                //Actualizo el stock de ese producto
                //Recorro un resulset En el cual tengo el idDelProducto la cantidad y stock y con eso actualizo El stock restante.
                //rsStockProducto ese resulset nos da los productos, la cantidad de ellos y el stock q nos queda en el pedido elegido por el user
                rsStockProducto = gestoraBD.getResulsetObtenerCantidadStockAActualizar(IdPedido);
                while(rsStockProducto.next()){

                    Producto = rsStockProducto.getInt("IDProducto");
                    Stock = rsStockProducto.getInt("Stock") - rsStockProducto.getInt("Cantidad");
                    gestoraBD.ActualizarProductoStock(Producto,Stock);

                }

            }catch (SQLException e){

               e.printStackTrace();

            }


            }else {
                System.out.println("No he podido servir el pedido..Voy a ver si puedo con los sustitutivos");
                //Aqui que lo que me queda es..
                //Mirar que producto es el que no cabe, comprobar poner el stock a 0 y los que nos falten debo meterlos con el
                //producto sustitutivo , si el producto sustitutivo fuera nulo, pues no se haria nada mas , si el producto
                //no lo es pues se miraria cuanto stock tiene, se le asignaria ese producto con una nueva insercion en lineas de
                //pedidos , y se le actualizaria el stock de la misma forma que hemos echo anteriormente.

             }

             //Cerramos todo
        try{

            rsActuPedidos.close();
            gestoraBD.cerrarConexion();

        }catch (SQLException e){


            e.printStackTrace();
        }




    }

}
