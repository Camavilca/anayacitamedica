package com.camavilca.controllers.usuario;

import com.camavilca.dao.UsuarioDAO;
import com.camavilca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.albatross.zelpers.miscelanea.ObjectUtil;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Service
@Transactional(readOnly = true)
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    @Transactional
    public void save(Usuario usuario) {
        ObjectUtil.printAttr(usuario);
        if (usuario.getNombre() == null
                || usuario.getCorreo() == null
                || usuario.getPassword() == null) {
            throw new PhobosException("Debe completar los campos");
        }
        usuarioDAO.save(usuario);
    }

    @Override
    public Usuario iniciar(Usuario usuario) {
        if (usuario.getCorreo() == null || usuario.getPassword() == null) {
            throw new PhobosException("Debe completar los campos");
        }
        Usuario usuarioDB = usuarioDAO.findUser(usuario.getCorreo(), usuario.getPassword());
        if (usuarioDB == null) {
            throw new PhobosException("No existe los datos de usuario");
        }
        return usuarioDB;
    }

}
