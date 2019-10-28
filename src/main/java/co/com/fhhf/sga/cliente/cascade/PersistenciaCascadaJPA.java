package co.com.fhhf.sga.cliente.cascade;

import co.com.fhhf.sga.domain.Persona;
import co.com.fhhf.sga.domain.Usuario;
import javax.persistence.*;
import org.apache.logging.log4j.*;

public class PersistenciaCascadaJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        
        //Paso 1. Crear Objeto en estado Transitivo
        Persona persona1 = new Persona("Persik", "Hernandez", "ph@email.com", "66654");
        
        //Paso 2. Crear Objeto Usuario (tiene dependencia con el objeto Persona)
        Usuario usuario1 = new Usuario("PHernandez", "345", persona1);
        
        //Paso 3. Persistimos el objeto Usuario únicamente
        em.persist(usuario1);
        
        //Paso 4. commit transaction
        tx.commit();
        
        //Objetos en estado detached
        log.debug("Objeto persona persistido = " + persona1);
        log.debug("Objeto usuario persistido = " + usuario1);
        
        em.close();
    }
}
