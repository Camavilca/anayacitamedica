package com.camavilca.dao;

import com.camavilca.model.CitaMedica;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.easydao.EasyDAO;

public interface CitaMedicaDAO extends EasyDAO<CitaMedica>{

    List<CitaMedica> all(DynatableFilter filter);

}
