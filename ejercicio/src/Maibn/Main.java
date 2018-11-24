package Maibn;
import Clases.ConecBD;
import GestoraEnvios.manejadoraEnvios;
import GestoraMenus.Menus;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Declaracion de variables y objetos
        int opcion;
        int pedido;
        int almacen;
        int almacenMasCercano;
        int capacidad;
        int asignar;
        int asignacion;
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
                    System.out.println(gestoraEnvios.ComprobarAlmCercano(connexionBaseDatos,11,10));
                    System.out.println(gestoraEnvios.AlmacenMasCercano(connexionBaseDatos,11,2));

                    break;

                case 2:
                    //System.out.println("En construccion");
                    System.out.println("Envios sin asignar \n");
                    gestoraEnvios.MostrarPedididosSinAsignar(sentencia);
                    System.out.println("Dime el pedido que quieres asignar \n");
                    pedido = teclado.nextInt();
                    System.out.println("Este es el pedido que has elegido");
                    ResultSet res = gestoraEnvios.MostrarPedidoSinAsignar(pedido,sentencia);
                    System.out.println("");
                    System.out.println("Voy a asignarlo...Podre al almacen preferido?");
                    almacen = gestoraEnvios.comprobarAlmacenPreferido(connexionBaseDatos,pedido);
                    if(almacen == 1){

                        almacen = gestoraEnvios.ObtenerAlmacenPreferido(connexionBaseDatos,pedido);
                        gestoraEnvios.ActualizarAsignacion(connexionBaseDatos,pedido);
                        gestoraEnvios.InsertPedidoAsignacion(connexionBaseDatos,pedido,almacen);
                        System.out.println("Asignado al almacenPreferido");

                    }else{

                        System.out.println("Voy a asignarlo al mas cercano");
                        almacen = gestoraEnvios.ObtenerAlmacenPreferido(connexionBaseDatos,pedido);
                        almacenMasCercano = gestoraEnvios.AlmacenMasCercano(connexionBaseDatos,pedido,almacen);

                        asignacion =  gestoraEnvios.ComprobarAlmCercano(connexionBaseDatos,pedido,almacenMasCercano);

                       if(asignacion == 1){

                           gestoraEnvios.ActualizarAsignacion(connexionBaseDatos,pedido);
                           gestoraEnvios.InsertPedidoAsignacion(connexionBaseDatos,pedido,almacenMasCercano);
                           System.out.println("Asignado Perfecto");

                       }else{

                           System.out.println("No se puede asignar tampoco al mas cercano");

                       }
                    }
                    break;

                case 3:
                    //System.out.println("En construccion");


                    gestoraEnvios.MostrarEnvios(sentencia);

                    break;

                default:
                    System.out.println("En construccion");
                    break;
            }

        }while(opcion != 0);


    }

}
