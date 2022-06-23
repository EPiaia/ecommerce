package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Cliente;
import com.mycompany.ecommerce.utils.FiltrosPesquisa;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

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
        add(fp, "c.cliCpf = '?cliCpf'", "cliCpf", filtros.get("cliCpf"));
        add(fp, "c.cliEmail = '?cliEmail'", "cliEmail", filtros.get("cliEmail"));
        return fp;
    }

    public List<Cliente> getClientes() {
        String sql = "SELECT CLI.* FROM CLIENTE CLI";
        return executeNativeQuery(Cliente.class, sql);
    }

    public Cliente getClientePorCodigo(int codigo) {
        return getEntityManager().find(Cliente.class, codigo);
    }

    public List<Cliente> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT c FROM Cliente c";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
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
