package com.camavilca.dao;

import com.camavilca.model.Medico;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.easydao.EasyDAO;

public interface MedicoDAO extends EasyDAO<Medico> {

    List<Medico> all(DynatableFilter filter);

}
