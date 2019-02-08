/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probandohibernateconle;


import java.util.Scanner;
import manejadoras.ManejadoraMenus;
import modelos.Criaturita;
import org.hibernate.Session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import modelos.CriaturitaConRegalos;
import modelos.RegaloParaCriaturitaConRegalos;
import probandohibernate.SesionFactory;

/**
 *
 * @author rmateos
 */
public class ProbandoHibernateConLe {
    
  private static void recuperaCriaturitaConRegalos(Session s, byte id){
 
        CriaturitaConRegalos nene;
     
        nene = (CriaturitaConRegalos)s.get(CriaturitaConRegalos.class, id);
        System.out.println();
        System.out.println(nene.toString());
        System.out.println("Regalos");
        int cont = 1;
        for(RegaloParaCriaturitaConRegalos surprise:nene.getRegalitos()){
            System.out.println(cont+" -> "+surprise.toString());
            cont++;
        }
    }

    /**
     * @param args the command line arguments
     */
    private static SessionFactory sessionFactory = null;
    
    public static void main(String[] args) {
        
        int opcion;
        ManejadoraMenus manejadora = new ManejadoraMenus();
        Scanner teclado  = new Scanner(System.in);
        
        do{
         manejadora.MostrarMenu();
         opcion = teclado.nextInt();
        }while(opcion < 0 || opcion > 10);
        
        switch(opcion){
        
            case 1:
                
                break;
                
            
        }
       
                
        /*
        SessionFactory instancia = SesionFactory.getSessionFactory();
        try{
            
            byte idC = 3;
            Session ses = instancia.openSession();
            
             int idR = 6;
             idC = 3;
            System.out.println("======================================================");
    
            recuperaCriaturitaConRegalos (ses,idC);
            ses.close();
            
        }catch(Exception e){};
        */

        
    }
    
}
