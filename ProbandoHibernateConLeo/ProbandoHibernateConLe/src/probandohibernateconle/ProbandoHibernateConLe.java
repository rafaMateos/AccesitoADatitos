/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probandohibernateconle;


import java.util.ArrayList;
import java.util.List;
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
import org.hibernate.Query;
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
        
         SessionFactory instancia = SesionFactory.getSessionFactory();
          Session ses = instancia.openSession();
          
         do{
             
             do{
         manejadora.MostrarMenu();
         opcion = teclado.nextInt();
        }while(opcion < 0 || opcion > 10);
        
        switch(opcion){
        
            case 1:
                
                
                 Query query = ses.createQuery("FROM modelos.Criaturita");
                 List<Criaturita> criaturita = query.list();
                
                for(int i = 0; i < criaturita.size(); i++){
                    System.out.println("id: " + criaturita.get(i).getId() +
                            " Nombre: " + criaturita.get(i).getNombre()); 
                } 
                break;
                
            case 2: 
                
               
                 Query query1 = ses.createQuery("from modelos.RegaloParaCriaturitaConRegalos");
                 List<RegaloParaCriaturitaConRegalos> regalo = query1.list();
                
                for(int i = 0; i < regalo.size(); i++){
                    System.out.println("id: " + regalo.get(i).getId() +
                            " Nombre Regalo: " + regalo.get(i).getDenominacion()); 
                } 
                
                break;
                
                
            case 3:
                
               
                 Query query2 = ses.createQuery("FROM modelos.Criaturita");
                 List<Criaturita> criaturita2 = query2.list();
                
                for(int i = 0; i < criaturita2.size(); i++){
                    System.out.println("id: " + criaturita2.get(i).getId() +
                            " Nombre: " + criaturita2.get(i).getNombre()); 
                } 
                
                System.out.println("Ecoja una criaturita");
                byte idCriaturita = teclado.nextByte();
            
            
                recuperaCriaturitaConRegalos(ses,idCriaturita);
                break;
                        
                        
                
            
        }
       
         
         }while(opcion != 0);
        
                
        /*
        SessionFactory instancia = SesionFactory.getSessionFactory();
        try{
            
            byte idC = 3;
            Session ses = instancia.openSession();
            
             int idR = 6;
             idC = 3;
            System.out.println("======================================================");
    
            recuperaCriaturitaConRegalos(ses,idC);
            ses.close();
            
        }catch(Exception e){};
        */

        
    }
    
}
