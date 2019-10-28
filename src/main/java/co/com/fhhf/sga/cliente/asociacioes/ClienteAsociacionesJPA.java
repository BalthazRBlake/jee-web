package co.com.fhhf.sga.cliente.asociacioes;

import co.com.fhhf.sga.domain.Persona;
import co.com.fhhf.sga.domain.Usuario;
import java.util.List;
import javax.persistence.*;
import org.apache.logging.log4j.*;

public class ClienteAsociacionesJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        List<Persona> personas = em.createNamedQuery("Persona.findAll").getResultList();
        
        em.close();
        
        for(Persona persona: personas){
            log.debug("Personas = " + persona);
            
            //Recuperamos los Usuarios de Cada Persona
            for(Usuario usuario: persona.getUsuarioList()){
                log.debug("Usuario = " + usuario);
            }
        }
    }
}
