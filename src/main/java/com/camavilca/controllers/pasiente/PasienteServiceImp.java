package com.camavilca.controllers.pasiente;

import com.camavilca.model.Paciente;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camavilca.dao.PasienteDAO;
import com.camavilca.dao.UsuarioDAO;
import com.camavilca.model.Usuario;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class PasienteServiceImp implements PasienteService {

    @Autowired
    PasienteDAO pasienteDAO;
    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    @Transactional
    public void save(Paciente pasiente) {
        if (pasiente.getId() == null) {
            pasienteDAO.save(pasiente);
        } else {
            pasienteDAO.update(pasiente);
        }
    }

    @Override
    public List<Paciente> all() {
        return pasienteDAO.all();
    }

    @Override
    @Transactional
    public void delete(Paciente pasiente) {
        pasienteDAO.delete(pasiente);
    }

    @Override
    public List<Paciente> allDynatable(DynatableFilter filter) {
        return pasienteDAO.all(filter);
    }

    @Override
    public List<Usuario> allDynatableUsuario(DynatableFilter filter) {
        return usuarioDAO.all(filter);
    }

}
