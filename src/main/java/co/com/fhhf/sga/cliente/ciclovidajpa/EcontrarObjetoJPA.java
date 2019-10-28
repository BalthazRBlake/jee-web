package co.com.fhhf.sga.cliente.ciclovidajpa;

import co.com.fhhf.sga.domain.Persona;
import javax.persistence.*;
import org.apache.logging.log4j.*;

public class EcontrarObjetoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        
        //Inicia la transaccion
        EntityTransaction tx = em.getTransaction();
        //Paso 1. Iniciar transaccion
        tx.begin();
        
        //Paso2. Ejecutar SQL de tipo SELECT
        Persona persona1 = em.find(Persona.class, 2);
        
        //Paso 3. termina la transaccion
        tx.commit();
        
        //Objeto en estado detached
        log.debug("Objeto recuperado" + persona1);
        
        //Cerramos EntityManager
        em.close();
    }
}
