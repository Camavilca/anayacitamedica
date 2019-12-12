package com.camavilca.dao.hibernate;

import com.camavilca.dao.UsuarioDAO;
import com.camavilca.model.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;
import pe.albatross.octavia.Octavia;
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.dynatable.DynatableSql;
import pe.albatross.octavia.easydao.AbstractEasyDAO;

@Repository
public class UsuarioDAOH extends AbstractEasyDAO<Usuario> implements UsuarioDAO {

    public UsuarioDAOH() {
        super();
        setClazz(Usuario.class);
    }

    @Override
    public Usuario findUser(String correo, String password) {
        Octavia sql = Octavia.query()
                .from(Usuario.class, "u")
                .filter("correo", correo)
                .filter("password", password);
        return find(sql);
    }

    @Override
    public List<Usuario> all(DynatableFilter filter) {
        DynatableSql sql = new DynatableSql(filter)
                .from(Usuario.class, "u");
        return all(sql);
    }

}
