package com.camavilca.controllers.citamedica;

import com.camavilca.model.CitaMedica;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;

public interface CitaMedicaService {

    void save(CitaMedica citaMedica);

    List<CitaMedica> allDynatable(DynatableFilter filter);

    void delete(CitaMedica citaMedica);

    List<CitaMedica> all();

}
