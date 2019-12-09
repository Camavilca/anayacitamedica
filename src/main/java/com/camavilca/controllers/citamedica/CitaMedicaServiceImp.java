package com.camavilca.controllers.citamedica;

import com.camavilca.dao.CitaMedicaDAO;
import com.camavilca.model.CitaMedica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.zelpers.miscelanea.ObjectUtil;

@Service
@Transactional
public class CitaMedicaServiceImp implements CitaMedicaService {

    @Autowired
    CitaMedicaDAO citaMedicaDAO;

    @Override
    public void save(CitaMedica citaMedica) {
        ObjectUtil.printAttr(citaMedica);
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
        citaMedicaDAO.delete(citaMedica);
    }

}
