package com.camavilca.controllers.usuario;

import com.camavilca.model.Usuario;
import java.util.List;

public interface UsuarioService {

    void save(Usuario usuario);

    Usuario iniciar(Usuario usuario);

    List<Usuario> all();

    void delete(Usuario usuario);

}
