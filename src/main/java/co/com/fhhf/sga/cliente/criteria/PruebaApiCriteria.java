package co.com.fhhf.sga.cliente.criteria;

import co.com.fhhf.sga.domain.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.apache.logging.log4j.*;


public class PruebaApiCriteria {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = null;
        CriteriaQuery<Persona> criteriaQuery = null;
        Root<Persona> fromPersona = null;
        TypedQuery<Persona> query = null;
        Persona persona = null;
        List<Persona> personas = null;
        
        //Query utilizando el API de CRITERIA
        log.debug("\n 1. Consulta de todas las personas");
        //Paso 1. El objeto EntityManager crea intancia CriteriBuilder
        cb = em.getCriteriaBuilder();
        //Paso 2. Se crea un objeto CriteriBuilder
        criteriaQuery = cb.createQuery(Persona.class);
        //Paso 3. Creamos el objeto raiz de Query
        fromPersona = criteriaQuery.from(Persona.class);
        //Paso 4. Seleccinoamos lo necesario del from
        criteriaQuery.select(fromPersona);
        //Paso 5. Creamos el query typeSafe
        query = em.createQuery(criteriaQuery);
        //Paso 6. Ejecutamos la consulta
        personas = query.getResultList();
        mostrarPersonas(personas);
        
        log.debug("\n 2-a. Consulta la Persona con id determinado");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona).where(cb.equal(fromPersona.get("idPersona"), 2));
        persona = em.createQuery(criteriaQuery).getSingleResult();
        log.debug(persona);
        
        log.debug("\n 2-b. Consulta la Persona con id determinado");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona);
         
        //La clase Predicate permite agregar varios criterios dinamicamente
        List<Predicate> criterios = new ArrayList<Predicate>();
        
        //Verificamos si tenemos criterios que agregar
        Integer idPersonaParam = 4;
        ParameterExpression<Integer> parameter = cb.parameter(Integer.class, "idPersona");
        criterios.add(cb.equal(fromPersona.get("idPersona"), parameter));
        
        //Se agregan los criterios
        if(criterios.isEmpty()){
            throw new RuntimeException("Sin criterios");
        }else if(criterios.size() == 1){
            criteriaQuery.where(criterios.get(0));
        }else{
            criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
        }
        
        query = em.createQuery(criteriaQuery);
        query.setParameter("idPersona", idPersonaParam);
        
        //Se ejecuta el query
        persona = query.getSingleResult();
        log.debug(persona);
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for(Persona p: personas){
            log.debug(p);
        }
    }
}
