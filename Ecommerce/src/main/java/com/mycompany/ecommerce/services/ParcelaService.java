package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Parcela;
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
public class ParcelaService extends BaseService<Parcela> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "p.parcelaPk.prcPedCod = ?prcPedCod", "prcPedCod", filtros.get("prcPedCod"));
        return fp;
    }

    public List<Parcela> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT p FROM Parcela p";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
