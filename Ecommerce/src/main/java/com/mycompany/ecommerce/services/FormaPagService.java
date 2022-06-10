package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Cidade;
import com.mycompany.ecommerce.domains.FormaPag;
import com.mycompany.ecommerce.utils.FiltrosPesquisa;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Piaia
 */
@Stateless
@Named
public class FormaPagService extends BaseService<FormaPag> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "fp.fopCod = ?fopCod", "fopCod", filtros.get("fopCod"));
        add(fp, "UPPER(fp.fopDesc) LIKE UPPER('%?fopDesc%')", "fopDesc", filtros.get("fopDesc"));
        return fp;
    }

    public List<FormaPag> getFormasDePagamento() {
        String query = "SELECT FP.* FROM FORMA_PAG FP";
        return super.executeNativeQuery(FormaPag.class, query);
    }

    public List<FormaPag> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT fp FROM FormaPag fp";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(FormaPag formaPag) {
        String sql = "DELETE FROM FORMA_PAG WHERE FOP_COD = " + formaPag.getFopCod();
        super.executeNativeUpdate(sql);
    }

}
