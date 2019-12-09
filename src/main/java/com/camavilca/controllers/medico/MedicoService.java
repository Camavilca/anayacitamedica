package com.camavilca.controllers.medico;

import com.camavilca.model.Area;
import com.camavilca.model.Medico;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;

public interface MedicoService {

    void save(Medico producto);

    void delete(Medico producto);

    List<Medico> all();

    List<Medico> allDynatable(DynatableFilter filter);

    List<Area> allArea();

    
    

}
