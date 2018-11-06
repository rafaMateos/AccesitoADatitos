package Maibn;
import Clases.ConecBD;
import GestoraMenus.Menus;
import java.sql.*;
public class Main {

    public static void main(String[] args) {

        Menus menus = new Menus();
        ConecBD metodos = new ConecBD();
        //Debes de hacer un metodo que lo abra todo.
        Connection connexionBaseDatos = metodos.iniciarConex("AlmacenesLeo");
        Statement sentencia = connexionBaseDatos.createStatement();
        int opcion;

        do {
            opcion = menus.MenuPrincipal();

            switch (opcion) {

                case 1:
                    System.out.println("En construccion");
                    break;

                case 2:
                    System.out.println("En construccion");
                    break;

                case 3:
                    System.out.println("En construccion");
                    try {




                        //Utilizar como ejemplos.
                        String miOrden = "SELECT * FROM ListadoEnvios()";

                        ResultSet res = sentencia.executeQuery(miOrden);

                        while (res.next()){

                            System.out.println("ID: " + res.getInt(1));
                        }

                        metodos.cerrar(res);
                        metodos.cerrar(sentencia);
                        metodos.cerrar(connexionBaseDatos);

                    }catch (SQLException e){

                        e.printStackTrace();

                    }
                    break;

                default:
                    System.out.println("En construccion");
                    break;
            }

        }while(opcion != 0);


    }

}
