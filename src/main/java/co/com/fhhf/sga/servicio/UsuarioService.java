package co.com.fhhf.sga.servicio;

import co.com.fhhf.sga.domain.Usuario;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UsuarioService {
    public List<Usuario> listarUsuarios();
    
    public Usuario encontrarUsuarioPorId(Usuario usuario);
    
    public Usuario encontrarUsuarioPorUsername(Usuario usuario);
    
    public void registrarUsuario(Usuario usuario);
    
    public void modificarUsuario(Usuario usuario);
    
    public void eliminarUsuario(Usuario usuario);
}
