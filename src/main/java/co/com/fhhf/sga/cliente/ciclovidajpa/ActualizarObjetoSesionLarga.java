package co.com.fhhf.sga.cliente.ciclovidajpa;

import co.com.fhhf.sga.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActualizarObjetoSesionLarga {
            static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        
        //Inicia la 1ra Transaccion
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        //Paso2. Ejecutar SQL de tipo SELECT
        Persona persona1 = em.find(Persona.class, 1);
        
        //Paso 3. setValue
        persona1.setEmail("jjuarez@email.com");
        
        persona1.setEmail("jj@email.com");
        
        //Paso 4. termina la 1ra transaccion
        tx.commit();
        
        //Objeto en estado detached
        log.debug("Objeto modificado " + persona1);
        
        //Cerramos EntityManager
        em.close();
    }
}
