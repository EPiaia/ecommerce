package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Cliente;
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
public class ClienteService extends BaseService<Cliente> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        return fp;
    }

    public List<Cliente> getClientes() {
        String sql = "SELECT CLI.* FROM CLIENTE CLI";
        return executeNativeQuery(Cliente.class, sql);
    }

    public Cliente getClientePorCodigo(int codigo) {
        return getEntityManager().find(Cliente.class, codigo);
    }

    public Cliente getClientePorUsuario(String usuario) {
        String sql = "SELECT CLI.* FROM CLIENTE CLI WHERE CLI_USUARIO = '" + usuario + "'";
        List<Cliente> retorno = executeNativeQuery(Cliente.class, sql);
        if (retorno.isEmpty()) {
            return null;
        } else {
            return retorno.get(0);
        }
    }
}
