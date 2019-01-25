/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probandohibernateconle;

import modelos.Criaturita;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import probandohibernate.SesionFactory;

/**
 *
 * @author rmateos
 */
public class ProbandoHibernateConLe {

    /**
     * @param args the command line arguments
     */
    private static SessionFactory sessionFactory = null;
    
    public static void main(String[] args) {
        
        Session session = null;
        sessionFactory = SesionFactory.getSessionFactory();
        session = sessionFactory.openSession();
       
        Transaction tx = session.beginTransaction();
        Criaturita criatura = new Criaturita();
        
        byte id = 71;
        byte id2 = 2;
        
        System.out.println("Guardamos a Juan con id "+ id);
        criatura.setId(id);
        criatura.setNombre("Juan");
        session.save(criatura);
        
         System.out.println("Recojemos un registro(Adelita)");
        Criaturita nene = (Criaturita)session.get(Criaturita.class, id2);
        System.out.println(nene.getNombre());
        
        System.out.println("Le cambiamos el nombre a Adelita(LeoApruebame)");
        nene.setNombre("LeoApruebame");
        
        System.out.println("Borramos a Juan con id 70");
        id2 = 70;
        Criaturita juan = (Criaturita)session.get(Criaturita.class, id2);
        session.delete(juan);
        
        tx.commit();
        session.close();
        
        
        
       
        
        
        
    }
    
}
