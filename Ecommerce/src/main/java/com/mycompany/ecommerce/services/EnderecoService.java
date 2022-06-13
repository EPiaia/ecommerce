package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Cliente;
import com.mycompany.ecommerce.domains.Endereco;
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
public class EnderecoService extends BaseService<Endereco> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        return fp;
    }

    public List<Endereco> getEnderecosDoCliente(Cliente cliente) {
        if (cliente == null) {
            return new ArrayList<>();
        }
        String sql = "SELECT E.* FROM ENDERECO E WHERE E.END_CLIENTE = " + cliente.getCliCod();
        return executeNativeQuery(Endereco.class, sql);
    }

    public Endereco getEnderecoPorCodigo(int codigo) {
        String sql = "SELECT E.* FROM ENDERECO E WHERE E.END_COD = " + codigo;
        List<Endereco> enderecos = executeNativeQuery(Endereco.class, sql);
        if (enderecos.isEmpty()) {
            return null;
        } else {
            return enderecos.get(0);
        }
    }
}
