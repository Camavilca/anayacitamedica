package com.camavilca.controllers.citamedica;

import com.camavilca.dao.CitaMedicaDAO;
import com.camavilca.model.CitaMedica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.zelpers.miscelanea.ObjectUtil;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Service
@Transactional
public class CitaMedicaServiceImp implements CitaMedicaService {

    @Autowired
    CitaMedicaDAO citaMedicaDAO;

    @Override
    @Transactional
    public void save(CitaMedica citaMedica) {

        List<CitaMedica> citas = citaMedicaDAO.all();
        citas.forEach(cita -> {
            System.out.println("cita: " + cita.getFechaInicio() + "   " + citaMedica.getFechaInicio());
            if (citaMedica.getFechaInicio() == cita.getFechaInicio()
                    || citaMedica.getFechaFin() == cita.getFechaFin()) {
                throw new PhobosException("La fecha ya esta ocupada");
            }
        });

        if (citaMedica.getId() == null) {
            citaMedicaDAO.save(citaMedica);
        } else {
            citaMedicaDAO.update(citaMedica);
        }
    }

    @Override
    public List<CitaMedica> allDynatable(DynatableFilter filter) {
        return citaMedicaDAO.all(filter);
    }

    @Override
    public void delete(CitaMedica citaMedica) {
        ObjectUtil.printAttr(citaMedica);
        citaMedicaDAO.delete(citaMedica);
    }

    @Override
    public List<CitaMedica> all() {
        return citaMedicaDAO.allFull();
    }

}
