package com.camavilca.dao.hibernate;

import com.camavilca.dao.PasienteDAO;
import com.camavilca.model.Paciente;
import java.util.List;
import org.springframework.stereotype.Repository;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.dynatable.DynatableSql;
import pe.albatross.octavia.easydao.AbstractEasyDAO;

@Repository
public class PasienteDAOH extends AbstractEasyDAO<Paciente> implements PasienteDAO {

    public PasienteDAOH() {
        super();
        setClazz(Paciente.class);
    }

    @Override
    public List<Paciente> all(DynatableFilter filter) {
        DynatableSql sql = new DynatableSql(filter)
                .from(Paciente.class, "m")
                .searchFields("m.nombre", "m.paterno", "m.materno");
        return all(sql);
    }

}
