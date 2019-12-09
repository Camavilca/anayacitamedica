package com.camavilca.controllers.usuario;

import com.camavilca.model.Usuario;

public interface UsuarioService {

    void save(Usuario usuario);

    Usuario iniciar(Usuario usuario);

}
