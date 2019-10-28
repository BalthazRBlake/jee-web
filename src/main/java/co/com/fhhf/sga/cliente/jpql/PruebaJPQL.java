package co.com.fhhf.sga.cliente.jpql;

import co.com.fhhf.sga.domain.Persona;
import co.com.fhhf.sga.domain.Usuario;
import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import org.apache.logging.log4j.*;


public class PruebaJPQL {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        String jpql = null;
        Query q = null;
        List<Persona> personas = null;
        Persona persona = null;
        Iterator iter = null;
        Object[] tuple = null;
        List<String> nombres = null;
        List<Usuario> usuarios = null;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        log.debug("\n 1. Consulta de todas las Personas");
        jpql = "select p from Persona p";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        log.debug("\n2. Consulta de Persona con único id");
        jpql = "select p from Persona p where p.idPersona = 2";
        persona = (Persona) em.createQuery(jpql).getSingleResult();
        log.debug(persona);
        
        log.debug("\n3. Consulta de la Persona por nombre");
        jpql = "select p from Persona p where p.nombre = 'Persik'";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        log.debug("\n4. Consulta de datos individuales, se crea un arreglo(tubple) de tipo Object de n columnas");
        jpql = "select p.nombre as Name, p.apellido as Surename, p.email as Email, p.telefono as Phone from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tuple = (Object[]) iter.next();
            String nombre = (String) tuple[0];
            String apellido = (String) tuple[1];
            String email = (String) tuple[2];
            String phone = (String) tuple[3];
            log.debug("Nombre: " + nombre + ", Apellido: " + apellido + ", eMail: " + email + ", Telefono: " + phone);
        }
        
        log.debug("\n5. Obtiene el Objeto Persona y el id, se crea un arreglo de tipo Objetc con 2 columnas");
        jpql = "select p, p.idPersona from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tuple = (Object[]) iter.next();
            persona = (Persona) tuple[0];
            int idPersona = (int) tuple[1];
            log.debug("Objeto persona: " + persona);
            log.debug("id Persona: " +idPersona);
        }
        
        log.debug("\n6. Consulta de todas las Personas");
        jpql = "select new co.com.fhhf.sga.domain.Persona( p.idPersona ) from Persona p";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        log.debug("\n7. Regresa el valor mínimo y máximio del idPersona (scaler, result)");
        jpql = "select min(p.idPersona) as MinId, max(p.idPersona) as MaxId, count (p.idPersona) as Counter from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while(iter.hasNext()){
            tuple = (Object[]) iter.next();
            Integer idMin = (Integer) tuple[0];
            Integer idMax = (Integer) tuple[1];
            Long count = (Long) tuple[2];
            log.debug("mínimo Id: " + idMin + ", máximo Id: " + idMax + ", Total Ids:" + count);
        }
        
        log.debug("\n8. Cuenta los nombres de las personas que son distintos");
        jpql = "select count(distinct p.nombre) from Persona p";
        Long contador = (Long) em.createQuery(jpql).getSingleResult();
        log.debug("No. de Personas con nombres distintos: " + contador);
        
        log.debug("\n9. Concatena y convierte a MAYÚSCULAS el nombre y apellido");
        jpql = "select CONCAT(upper(p.nombre), ' ' , upper(p.apellido)) as fullName from Persona p";
        nombres = em.createQuery(jpql).getResultList();
        for(String fullName: nombres){
            log.debug(fullName);
        }
        
        log.debug("\n10. Obtiene el Objeto persona con id igual al parámetro proporcionado");
        int idPersona = 3;
        jpql = "select p from Persona p where p.idPersona = :id";
        q = em.createQuery(jpql);
        q.setParameter("id", idPersona);
        persona = (Persona) q.getSingleResult();
        log.debug(persona);
        
        log.debug("\n11. Obtiene las personas que contengan la letra a en el nombre, sin importar si es mayuscula o miniscula");
        jpql = "select p from Persona p where upper(p.nombre) like upper(:parametro)";
        String cadena = "%a%"; //cadena que se envia al Like de base de datos
        q = em.createQuery(jpql);
        q.setParameter("parametro", cadena);
        personas = q.getResultList();
        mostrarPersonas(personas);
        
        log.debug("\n12. Uso de between");
        int param1 = 1, param2 = 3;
        jpql = "select p from Persona p where p.idPersona between :param1 and :param2";
        q = em.createQuery(jpql);
        q.setParameter("param1", param1);
        q.setParameter("param2", param2);
        personas = q.getResultList();
        mostrarPersonas(personas);
        
        log.debug("\n13. Uso del ordenamiento");
        jpql = "select p from Persona p where p.idPersona > 3 order by p.nombre desc, p.apellido desc";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        log.debug("\n14. Uso de subQuery");
        jpql = "select p from Persona p where p.idPersona in (select min(p1.idPersona) from Persona p1)";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        log.debug("\n15. Uso de Join con Lazy loading");
        jpql = "select u from Usuario u join u.persona p";
        usuarios = em.createQuery(jpql).getResultList();
        mostrarUsuarios(usuarios);
        
        log.debug("\n16. Uso de Left join con Eager loading");
        jpql = "select u from Usuario u left join fetch u.persona";
        usuarios = em.createQuery(jpql).getResultList();
        mostrarUsuarios(usuarios);
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for(Persona p: personas){
            log.debug(p);
        }
    }

    private static void mostrarUsuarios(List<Usuario> usuarios) {
         for(Usuario u: usuarios){
            log.debug(u);
        }
    }
}
