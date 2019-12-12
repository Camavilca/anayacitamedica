package com.camavilca.dao;

import com.camavilca.model.Paciente;
import com.camavilca.model.Usuario;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.easydao.EasyDAO;

public interface PasienteDAO extends EasyDAO<Paciente> {

    List<Paciente> all(DynatableFilter filter);

}
