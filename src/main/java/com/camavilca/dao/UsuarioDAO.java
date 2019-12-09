package com.camavilca.dao;

import com.camavilca.model.Usuario;
import pe.albatross.octavia.easydao.EasyDAO;

public interface UsuarioDAO extends EasyDAO<Usuario> {

    Usuario findUser(String correo, String password);

}
