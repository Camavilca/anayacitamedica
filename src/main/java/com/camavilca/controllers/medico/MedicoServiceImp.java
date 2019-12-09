package com.camavilca.controllers.medico;

import com.camavilca.dao.AreaDAO;
import com.camavilca.dao.MedicoDAO;
import com.camavilca.model.Area;
import com.camavilca.model.Medico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.zelpers.miscelanea.ObjectUtil;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class MedicoServiceImp implements MedicoService {

    @Autowired
    MedicoDAO medicoDAO;

    @Autowired
    AreaDAO areaDAO;

    @Override
    @Transactional
    public void save(Medico medico) {
        if (medico.getNombre() == null) {
            throw new PhobosException("Debe completar los campos");
        }
        ObjectUtil.printAttr(medico);
        medicoDAO.save(medico);
    }

    @Override
    @Transactional
    public void delete(Medico medico) {
        medicoDAO.delete(medico);
    }

    @Override
    public List<Medico> all() {
        return medicoDAO.all();
    }

    private String forLike(String nombre) {
        return "%" + nombre.replaceAll(" ", "%") + "%";
    }

    @Override
    public List<Medico> allDynatable(DynatableFilter filter) {
        return medicoDAO.all(filter);
    }

    @Override
    public List<Area> allArea() {
        return areaDAO.all();
    }

}
