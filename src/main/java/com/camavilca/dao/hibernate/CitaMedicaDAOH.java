package com.camavilca.dao.hibernate;

import com.camavilca.dao.CitaMedicaDAO;
import com.camavilca.model.CitaMedica;
import java.util.List;
import org.springframework.stereotype.Repository;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.dynatable.DynatableSql;
import pe.albatross.octavia.easydao.AbstractEasyDAO;

@Repository
public class CitaMedicaDAOH extends AbstractEasyDAO<CitaMedica> implements CitaMedicaDAO {

    public CitaMedicaDAOH() {
        super();
        setClazz(CitaMedica.class);
    }

    @Override
    public List<CitaMedica> all(DynatableFilter filter) {
        DynatableSql sql = new DynatableSql(filter)
                .from(CitaMedica.class, "cm")
                .join("paciente p", "medico m", "m.area")
                .searchFields("p.nombre", "m.nombre");
        return all(sql);
    }
}
