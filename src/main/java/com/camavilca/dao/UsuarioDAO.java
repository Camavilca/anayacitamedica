package com.camavilca.dao;

import com.camavilca.model.Usuario;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.easydao.EasyDAO;

public interface UsuarioDAO extends EasyDAO<Usuario> {

    Usuario findUser(String correo, String password);

    List<Usuario> all(DynatableFilter filter);

}
