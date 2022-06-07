package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Usuario;
import com.mycompany.ecommerce.utils.FiltrosPesquisa;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Stateless
@Named
public class UsuarioService extends BaseService<Usuario> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        return fp;
    }

    public Usuario getUsuarioPorLogin(String login) {
        String query = "SELECT USR.* FROM USUARIO USR WHERE USR.USR_LOGIN = '" + login + "'";
        List<Usuario> usuarios = executeNativeQuery(Usuario.class, query);
        if (usuarios == null || usuarios.isEmpty()) {
            return null;
        } else {
            return (Usuario) usuarios.get(0);
        }
    }
}
