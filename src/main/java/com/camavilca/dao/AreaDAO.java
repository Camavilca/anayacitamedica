package com.camavilca.dao;

import com.camavilca.model.Area;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.easydao.EasyDAO;

public interface AreaDAO extends EasyDAO<Area>{

    List<Area> all(DynatableFilter filter);

}
