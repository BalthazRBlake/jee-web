package co.com.fhhf.sga.datos;

import co.com.fhhf.sga.domain.Persona;
import java.util.List;

public interface PersonaDao {
    public List<Persona> findAllPersonas();
    
    public Persona findPersonaByID(Persona persona);
    
    public Persona findPersonaByEmail(Persona persona);
    
    public void insertPersona(Persona persona);
    
    public void updatePersona(Persona persona);
    
    public void deletePersona(Persona persona);
}
