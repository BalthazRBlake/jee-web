package co.com.fhhf.sga.datos;

import co.com.fhhf.sga.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class PersonaDaoImpl implements PersonaDao{
    
    @PersistenceContext(unitName="SgaPU")
    EntityManager em;

    @Override
    public List<Persona> findAllPersonas() {
        return em.createNamedQuery("Persona.findAll").getResultList();
    }

    @Override
    public Persona findPersonaByID(Persona persona) {
        return em.find(Persona.class, persona.getIdPersona());
    }

    @Override
    public Persona findPersonaByEmail(Persona persona) {
        Query query = em.createQuery("FROM Persona WHERE p.email =: email");//Crear Query fuera de name query
        query.setParameter("email", persona.getEmail());
        return (Persona) query.getSingleResult();
    }

    @Override
    public void insertPersona(Persona persona) {
        em.persist(persona);
    }

    @Override
    public void updatePersona(Persona persona) {
        em.merge(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
        em.remove(em.merge(persona));
    }
}
