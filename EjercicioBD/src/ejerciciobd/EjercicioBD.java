/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciobd;

import Clases.ConecBD;
import java.sql.Connection;

/**
 *
 * @author Rafa
 */
public class EjercicioBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       ConecBD metodos = new ConecBD();
       
       
       Connection conec = metodos.iniciarConex();
       
        System.out.println(conec.getClass().toString());
       
       
       

    }
    
}
