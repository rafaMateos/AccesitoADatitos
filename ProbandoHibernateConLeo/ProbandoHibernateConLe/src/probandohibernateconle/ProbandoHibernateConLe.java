/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probandohibernateconle;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import manejadoras.ManejadoraMenus;
import manejadoras.ManejadoraProgram;
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
       
        
        for(RegaloParaCriaturitaConRegalos surprise:nene.getRegalitos()){
            System.out.println(surprise.getId()+" -> "+surprise.toString());
            
        }
    }

    /**
     * @param args the command line arguments
     */
    private static SessionFactory sessionFactory = null;
    
    public static void main(String[] args) {
        
        int opcion;
        ManejadoraProgram maneja = new ManejadoraProgram();
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
                        
                        
            case 4:
                
                Query query3 = ses.createQuery("FROM modelos.Criaturita");
                 List<Criaturita> criaturita3 = query3.list();
                
                for(int i = 0; i < criaturita3.size(); i++){
                    System.out.println("id: " + criaturita3.get(i).getId() +
                            " Nombre: " + criaturita3.get(i).getNombre()); 
                } 
                
                System.out.println("Ecoja una criaturita");
                byte idCriaturita1 = teclado.nextByte();
                CriaturitaConRegalos criaturitaParaEliminar = (CriaturitaConRegalos)ses.get(CriaturitaConRegalos.class, idCriaturita1);
            
                recuperaCriaturitaConRegalos(ses,idCriaturita1);
                int idRegalo = teclado.nextInt();
                
                RegaloParaCriaturitaConRegalos nene;
                Transaction tr = ses.beginTransaction();
                nene = (RegaloParaCriaturitaConRegalos)ses.get(RegaloParaCriaturitaConRegalos.class, idRegalo);
              
                
               boolean result = criaturitaParaEliminar.EliminarRegalo(nene);
               nene.setPropietario(null);
                 
                 

               tr.commit();

                
                if(result){
                    System.out.println("Borrado perfe");
                }else{
                    System.out.println("Borrado no posible nene");
                }
                break;
                
                
            case 5:
                
                Query query4 = ses.createQuery("FROM modelos.Criaturita");
                 List<Criaturita> criaturita4 = query4.list();
                
                for(int i = 0; i < criaturita4.size(); i++){
                    System.out.println("id: " + criaturita4.get(i).getId() +
                            " Nombre: " + criaturita4.get(i).getNombre()); 
                } 
                
                System.out.println("Ecoja una criaturita para asignarle el regalo");
                byte idCriaturita2 = teclado.nextByte();
                
                CriaturitaConRegalos criaturitaParaRegalar = (CriaturitaConRegalos)ses.get(CriaturitaConRegalos.class, idCriaturita2);
                
                
                Query query5 = ses.createQuery("from modelos.RegaloParaCriaturitaConRegalos as reg where reg.propietario is null");
                  List<RegaloParaCriaturitaConRegalos> criaturita5 = query5.list();
                  
                   for(int i = 0;  i< criaturita5.size(); i++){
                   
                       System.out.println("id: " + criaturita5.get(i).getId() +" Nombre: " +   criaturita5.get(i).getDenominacion());
                   }
                   
                System.out.println("Ecoja el regalo que quiere");
                int idRegalo2 = teclado.nextInt();
                
                Transaction tr1 = ses.beginTransaction();
                
                nene = (RegaloParaCriaturitaConRegalos)ses.get(RegaloParaCriaturitaConRegalos.class, idRegalo2);
                  
                 nene.setPropietario(criaturitaParaRegalar);
                
                   tr1.commit();
                break;
                
                
            case 6:
                
                maneja.CrearCriaturita(ses);//Cambiar id por autogenerado
                
                break;
                
                
            case 7:
                
                maneja.CrearRegalo(ses);//Cambiar id por autogenerado
                break;
                
                
            case 8:
                
                maneja.BorrarRegalo(ses);
                
                break;
                 
        }
       
         
         }while(opcion != 0);
        
        ses.close();

        
    }
    
}
