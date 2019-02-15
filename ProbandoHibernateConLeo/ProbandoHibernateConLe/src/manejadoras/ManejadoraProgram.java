/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejadoras;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import modelos.Criaturita;
import modelos.RegaloParaCriaturitaConRegalos;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author rmateos
 */
public class ManejadoraProgram {
    
    public void CrearCriaturita(Session ses){
        
        Scanner teclado = new Scanner(System.in);
        
        Criaturita newCriatura = new Criaturita();
        
        Transaction tr = ses.beginTransaction();
        
        System.out.println("Dime el nombre de la criatura");
        String nombre = teclado.next();
        
        Byte id = 101;
        newCriatura.setId(id);
        newCriatura.setNombre(nombre);
        
        ses.save(newCriatura);
        tr.commit();
    
    }
    
    public void CrearRegalo(Session ses){
        
      Scanner teclado = new Scanner(System.in);
        
        RegaloParaCriaturitaConRegalos newRegalo = new RegaloParaCriaturitaConRegalos();
        
        Transaction tr = ses.beginTransaction();
    
        int id = 99;
        String denominacion;
        int ancho;
        int Largo;
        int alto;
        char tipo;
        int edadMinima;
        BigDecimal precio;
        
        System.out.println("Dime el nombre");
        denominacion = teclado.next();
        
        System.out.println("Dime el ancho");
        ancho = teclado.nextInt();
        
        System.out.println("Dime el Largo");
        Largo =  teclado.nextInt();
        
        System.out.println("Dime el alto");
        alto =  teclado.nextInt();
        
        System.out.println("Dime el tipo");
        tipo = teclado.next().charAt(0);
        
        System.out.println("Dime la edadMinima");
        edadMinima = teclado.nextInt();
        
        System.out.println("Dime el precio");
        precio = teclado.nextBigDecimal();
        
        
        newRegalo.setId(id);
        newRegalo.setAlto(alto);
        newRegalo.setAncho(ancho);
        newRegalo.setDenominacion(denominacion);
        newRegalo.setEdadMinima(edadMinima);
        newRegalo.setLargo(Largo);
        newRegalo.setPrecio(precio);
        newRegalo.setPropietario(null);
        newRegalo.setTipo(tipo);
        
        
         ses.save(newRegalo);
        tr.commit();
        
    }
    
    
    public void BorrarRegalo(Session ses){
        
         Scanner teclado = new Scanner(System.in);
        
        RegaloParaCriaturitaConRegalos newRegalo = new RegaloParaCriaturitaConRegalos();
        
        Transaction tr = ses.beginTransaction();
    
         Query query1 = ses.createQuery("from modelos.RegaloParaCriaturitaConRegalos");
                 List<RegaloParaCriaturitaConRegalos> regalo = query1.list();
                
                for(int i = 0; i < regalo.size(); i++){
                    System.out.println("id: " + regalo.get(i).getId() +
                            " Nombre Regalo: " + regalo.get(i).getDenominacion()); 
                } 
                
                
         System.out.println("¿Que regalo quieres borrar?");
         int idRegalo = teclado.nextInt();
         
         RegaloParaCriaturitaConRegalos  nene = (RegaloParaCriaturitaConRegalos)ses.get(RegaloParaCriaturitaConRegalos.class, idRegalo);
           
         ses.delete(nene);
         
         tr.commit();
         
        
        
    }
    
}
