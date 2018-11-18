package GestoraMenus;

import java.util.Scanner;

public class Menus {

    public int MenuPrincipal(){

        Scanner teclado =  new Scanner(System.in);
        int opcion = 0;

        do{
            System.out.println("Menu principal");
            System.out.println("1-Realizar envio");
            System.out.println("2-Asignar envio");
            System.out.println("3-Mostrar envios");
            System.out.println("0-Salir");
            opcion = teclado.nextInt();
        }while (opcion < 0 || opcion > 3);

        return opcion;
    }
}
