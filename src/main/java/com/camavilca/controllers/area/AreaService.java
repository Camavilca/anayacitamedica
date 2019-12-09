package com.camavilca.controllers.area;

import com.camavilca.model.Area;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;

public interface AreaService {

    void save(Area area);

    List<Area> allDynatable(DynatableFilter filter);

    void delete(Area area);

    List<Area> all();

}
