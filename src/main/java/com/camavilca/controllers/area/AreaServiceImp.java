package com.camavilca.controllers.area;

import com.camavilca.dao.AreaDAO;
import com.camavilca.model.Area;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.albatross.octavia.dynatable.DynatableFilter;

@Service
@Transactional(readOnly = true)
public class AreaServiceImp implements AreaService {

    @Autowired
    AreaDAO areaDAO;

    @Override
    @Transactional
    public void save(Area area) {
        areaDAO.save(area);
    }

    @Override
    public List<Area> allDynatable(DynatableFilter filter) {
        return areaDAO.all(filter);
    }

    @Override
    @Transactional
    public void delete(Area area) {
        areaDAO.delete(area);
    }

    @Override
    public List<Area> all() {
        return areaDAO.all();
    }

}
