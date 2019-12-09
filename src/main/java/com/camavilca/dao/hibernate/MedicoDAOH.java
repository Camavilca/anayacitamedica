package com.camavilca.dao.hibernate;

import com.camavilca.dao.MedicoDAO;
import com.camavilca.model.Medico;
import java.util.List;
import org.springframework.stereotype.Repository;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.dynatable.DynatableSql;
import pe.albatross.octavia.easydao.AbstractEasyDAO;

@Repository
public class MedicoDAOH extends AbstractEasyDAO<Medico> implements MedicoDAO {
    
    public MedicoDAOH() {
        super();
        setClazz(Medico.class);
    }
    
    @Override
    public List<Medico> all(DynatableFilter filter) {
        DynatableSql sql = new DynatableSql(filter)
                .from(Medico.class, "m")
                .join("area a")
                .searchFields("m.nombre", "m.paterno", "m.materno");
        return all(sql);
    }
}
