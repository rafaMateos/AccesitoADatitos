package modelos;
// Generated 29-ene-2019 11:52:53 by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Criaturitas"
)
public class CriaturitaConRegalos  implements java.io.Serializable {

    @Id 

    @Column(name="Id", nullable=false)	
     private byte id;
     
    @Column(name="Nombre")     
     private String nombre;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="GoesTo")
    private List<RegaloParaCriaturitaConRegalos> regalitos = new ArrayList<>();

    public CriaturitaConRegalos() {
    }

    public CriaturitaConRegalos(byte id) {
        this.id = id;
    }
    public CriaturitaConRegalos(byte id, String nombre) {
       this.id = id;
       this.nombre = nombre;
    }
   
    public byte getId() {
        return this.id;
    }
    
    public void setId(byte id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<RegaloParaCriaturitaConRegalos> getRegalitos() {
        return regalitos;
    }

    public void setRegalitos(List<RegaloParaCriaturitaConRegalos> regalitos) {
        this.regalitos = regalitos;
    }
    @Override
    public String toString() {
        return "Criaturita{" + "id=" + id + ", nombre=" + nombre + '}';
    }

}


