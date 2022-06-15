package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.PedxProd;
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
public class PedxProdService extends BaseService<PedxProd> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "p.pedxProdPk.pxpPedCod = ?pxpPedCod", "pxpPedCod", filtros.get("pxpPedCod"));
        return fp;
    }

    public List<PedxProd> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT p FROM PedxProd p";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
