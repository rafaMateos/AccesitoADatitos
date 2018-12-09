package Maibn;
import Clases.ConecBD;
import GestoraEnvios.manejadoraEnvios;
import GestoraMenus.Menus;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{

        //Declaracion de variables y objetos
        int opcion;
        int pedido = 0;
        int almacen;
        int almacenMasCercano;
        int capacidad;
        int asignar;
        int asignacion;
        ArrayList<Integer> ids;
        Scanner teclado = new Scanner(System.in);
        Menus menus = new Menus();
        manejadoraEnvios gestoraEnvios = new manejadoraEnvios();


        //Abrimos conexcion con la base de datos
        ConecBD metodos = new ConecBD();
        Connection connexionBaseDatos = metodos.iniciarConex("AlmacenesLeo");
        Statement sentencia = null;

        //Si les pasas la conexcionesto te lo ahorras nene y ya despues pues crear el statement que te haga falta
        try{

             sentencia = connexionBaseDatos.createStatement();

        }catch (Exception e){

            e.printStackTrace();

        }
        do {
            opcion = menus.MenuPrincipal();

            switch (opcion) {

                case 1:
                    //System.out.println("En construccion");

                    break;

                case 2:
                    //Mostramos los pedidos sin asignar
                    //System.out.println("En construccion");
                    System.out.println("Envios sin asignar \n");
                    gestoraEnvios.MostrarPedididosSinAsignar(sentencia);
                    ids = gestoraEnvios.ArrayIdsEnvios(connexionBaseDatos);

                    //Validamos que el pedido que introduzca el usuario
                    do{

                        System.out.println("Dime el pedido que quieres asignar \n");
                        pedido = teclado.nextInt();

                    }while(!gestoraEnvios.ValidarEnvio(ids,pedido));

                    //Mostramos el pedido que a elegido el Usuario
                    System.out.println("Este es el pedido que has elegido");
                    ResultSet res = gestoraEnvios.MostrarPedidoSinAsignar(pedido,sentencia);
                    System.out.println("");

                    //Intentamos asignarla al almacen preferido
                    System.out.println("Voy a asignarlo...Podre al almacen preferido?");
                    almacen = gestoraEnvios.comprobarAlmacenPreferido(connexionBaseDatos,pedido);
                    Thread.sleep(5000);//Paramos la ejecucion para asi poder bien paso a paso lo que hace
                    //Si el almacen preferido tiene espacio
                    if(almacen == 1){

                        //Asignamos al almacen preferido
                        almacen = gestoraEnvios.ObtenerAlmacenPreferido(connexionBaseDatos,pedido);
                        gestoraEnvios.ActualizarAsignacion(connexionBaseDatos,pedido);
                        gestoraEnvios.InsertPedidoAsignacion(connexionBaseDatos,pedido,almacen);
                        System.out.println("Asignado al almacenPreferido");

                    }else{//Si no hay espacio en el almacen preferido

                        System.out.println("No he podido asignarlos al almacen preferido");

                        //Buscamos el almacen mas cercano
                        System.out.println("Voy a asignarlo al mas cercano");
                        almacen = gestoraEnvios.ObtenerAlmacenPreferido(connexionBaseDatos,pedido);
                        almacenMasCercano = gestoraEnvios.AlmacenMasCercano(connexionBaseDatos,pedido,almacen);
                        asignacion =  gestoraEnvios.ComprobarAlmCercano(connexionBaseDatos,pedido,almacenMasCercano);

                        Thread.sleep(5000);//Si no hay espacio en el almacen preferido

                        //Si hay espacio en el almacen mas cercano
                       if(asignacion == 1){

                           //Asignamos el pedido
                           gestoraEnvios.ActualizarAsignacion(connexionBaseDatos,pedido);
                           gestoraEnvios.InsertPedidoAsignacion(connexionBaseDatos,pedido,almacenMasCercano);
                           System.out.println("Asignado Perfecto");

                       }else{//Si no hay espacio

                           //Mostramos mensaje de que no hemos podido asignar ni al mas cercano ni al preferido de ese pedido
                           System.out.println("No se puede asignar tampoco al mas cercano");

                       }
                    }
                    break;

                case 3:
                    //System.out.println("En construccion");


                    //Mostramos todos los envios
                    gestoraEnvios.MostrarEnvios(sentencia);

                    break;

                default:
                    System.out.println("En construccion");
                    break;
            }

        }while(opcion != 0);


    }

}
