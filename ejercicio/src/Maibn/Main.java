package Maibn;
import Clases.ConecBD;
import GestoraEnvios.manejadoraEnvios;
import GestoraMenus.Menus;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int opcion;
        int pedido;
        int almacen;
        int capacidad;
        int asignar;
        Scanner teclado = new Scanner(System.in);
        Menus menus = new Menus();
        manejadoraEnvios gestoraEnvios = new manejadoraEnvios();
        //abrimos conexcion con la base de datos
        ConecBD metodos = new ConecBD();
        //Debes de hacer un metodo que lo abra todo.
        Connection connexionBaseDatos = metodos.iniciarConex("AlmacenesLeo");
        Statement sentencia = null;


        try{

             sentencia = connexionBaseDatos.createStatement();

        }catch (Exception e){
            e.printStackTrace();

        }

        do {
            opcion = menus.MenuPrincipal();

            switch (opcion) {

                case 1:
                    System.out.println("En construccion");
                    break;

                case 2:
                    //System.out.println("En construccion");
                    System.out.println("Envios sin asignar \n");
                    gestoraEnvios.MostrarPedididosSinAsignar(sentencia);
                    System.out.println("Dime el pedido que quieres asignar \n");
                    pedido = teclado.nextInt();
                    System.out.println("Este es el pedido que has elegido");
                    capacidad = gestoraEnvios.MostrarPedidoSinAsignar(pedido,sentencia);
                    System.out.println("Capacidaaaaaaa" + capacidad);
                    gestoraEnvios.MostrarAlmacenesConCapacidadLibre(sentencia);
                    System.out.println("¿A cual se los quieres asignar?");
                    almacen = teclado.nextInt();
                    gestoraEnvios.MostrarAlmacenConCapacidadLibre(sentencia,almacen);
                    System.out.println(gestoraEnvios.ComprobarAlmacen(sentencia,almacen,capacidad));

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
