package com.camavilca.controllers.pasiente;

import com.camavilca.model.Paciente;
import java.util.List;
import pe.albatross.octavia.dynatable.DynatableFilter;

public interface PasienteService {

    void save(Paciente panamericano);

    List<Paciente> all();

    void delete(Paciente panamericano);

    List<Paciente> allDynatable(DynatableFilter filter);

}
