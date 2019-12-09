package com.camavilca.dao.hibernate;

import com.camavilca.dao.AreaDAO;
import com.camavilca.model.Area;
import java.util.List;
import org.springframework.stereotype.Repository;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.dynatable.DynatableSql;
import pe.albatross.octavia.easydao.AbstractEasyDAO;

@Repository
public class AreaDAOH extends AbstractEasyDAO<Area> implements AreaDAO {

    public AreaDAOH() {
        super();
        setClazz(Area.class);
    }

    @Override
    public List<Area> all(DynatableFilter filter) {
        DynatableSql sql = new DynatableSql(filter)
                .from(Area.class, "a")
                .searchFields("a.nombre", "a.paterno", "a.materno");
        return all(sql);
    }

}
