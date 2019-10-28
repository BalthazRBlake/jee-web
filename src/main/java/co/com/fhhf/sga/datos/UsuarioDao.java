package co.com.fhhf.sga.datos;

import co.com.fhhf.sga.domain.Usuario;
import java.util.List;

public interface UsuarioDao {
    public List<Usuario> findAllUsuarios();
    
    public Usuario findUsuarioById(Usuario usuario);
    
    public Usuario findUsuarioByUsername(Usuario usuario);
    
    public void insertUsuario(Usuario usuario);
    
    public void updateUsuario(Usuario usuario);
    
    public void deleteUsuario(Usuario usuario);
}
