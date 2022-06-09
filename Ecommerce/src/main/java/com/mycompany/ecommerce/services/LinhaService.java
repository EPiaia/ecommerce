package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Linha;
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
public class LinhaService extends BaseService<Linha> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "l.linCod = ?linCod", "linCod", filtros.get("linCod"));
        add(fp, "UPPER(l.linDesc) LIKE UPPER('%?linDesc%')", "linDesc", filtros.get("linDesc"));
        return fp;
    }

    public List<Linha> getLinhas() {
        String query = "SELECT L.* FROM LINHA L";
        return super.executeNativeQuery(Linha.class, query);
    }

    public Linha getLinhaPorCodigo(int codigo) {
        return getEntityManager().find(Linha.class, codigo);
    }

    public List<Linha> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT l FROM Linha l";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(Linha linha) {
        Linha linhaManaged = getEntityManager().find(Linha.class, linha.getLinCod());
        getEntityManager().remove(linhaManaged);
    }

}
