package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.utils.FiltrosPesquisa;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Piaia
 */
public abstract class BaseService {

    @PersistenceContext(name = "Ecommerce")
    protected EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    protected abstract List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros);

    protected void add(List<FiltrosPesquisa> list, String expressao, String campo, Object valor) {
        if (valor == null || (valor instanceof String && String.valueOf(valor).isEmpty())) {
            return;
        }
        FiltrosPesquisa filtro = new FiltrosPesquisa();
        filtro.add(expressao, campo, valor);
        list.add(filtro);
    }

    protected String adicionarFiltros(String sql, List<FiltrosPesquisa> filtros) {
        String retorno = sql + "";
        if (filtros.isEmpty()) {
            return retorno;
        }
        retorno += " WHERE ";
        List<String> filtrosReplace = new ArrayList<>();
        for (FiltrosPesquisa filtro : filtros) {
            filtrosReplace.add(filtro.getExpressao().replace(filtro.getCampoJpql(), filtro.getValor().toString()));
        }
        retorno += String.join(" AND ", filtrosReplace);
        return retorno;
    }

}
