/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.fhhf.sga.cliente.ciclovidajpa;

import co.com.fhhf.sga.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author FHHF
 */
public class ActualizarObjetoJPA {
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
        
        //Paso 4. Set newValue
        persona1.setApellido("Juarez");
        
        //Paso 5. Inicia 2da Transaccion
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //Paso 6. Modificar el Objeto
        em.merge(persona1);
        
        //Paso 7. Termina la 2da transaccion
        tx2.commit();
        
        //Objeto en estado detached
        log.debug("Objeto modificado recuperado" + persona1);
        
        //Cerramos EntityManager
        em.close();
    }
}
