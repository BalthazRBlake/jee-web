package co.com.fhhf.sga.cliente.ciclovidajpa;

import co.com.fhhf.sga.domain.Persona;
import javax.persistence.*;
import org.apache.logging.log4j.*;


public class PersistirObjetoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //Inicia la transaccion
        
        //Paso 1. Crea nuevo Objeto
        //Objeto en estado transitivo
        Persona persona1 = new Persona("Perdo","Luna","pl@email.com","65283");
        
        //Paso 2. Inicia transacción
        tx.begin();
        
        //Paso 3. Ejecuta SQL
        em.persist(persona1);
        log.debug("Objeto persistido - aun sin commit" + persona1);
        
        //Paso 4. commit/rollback
        tx.commit();
        
        //Objeto en estado detached
        log.debug("Objeto persistido - estado detached" + persona1);
        
        //Cerramos EntityManager
        em.close();
    }
}
