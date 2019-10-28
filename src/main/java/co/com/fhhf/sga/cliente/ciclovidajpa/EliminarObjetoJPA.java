package co.com.fhhf.sga.cliente.ciclovidajpa;

import co.com.fhhf.sga.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EliminarObjetoJPA {
            static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        
        //Inicia la 1ra Transaccion
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        //Paso2. Ejecutar SQL de tipo SELECT
        Persona persona1 = em.find(Persona.class, 1);
        
        //Paso 3. termina la 1ra transaccion
        tx.commit();
        
        //Objeto en estado detached
        log.debug("Objeto recuperado" + persona1);
        
        //Paso 4. Inicia 2da Transaccion
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //Paso 5. Ejecuta SQL de tipo DELETE
        em.remove(em.merge(persona1));
        
        //Paso 6. Termina la 2da transaccion
        tx2.commit();
        
        //Objeto en estado detached
        log.debug("Objeto eliminado" + persona1);
        
        //Cerramos EntityManager
        em.close();
    }    
}
