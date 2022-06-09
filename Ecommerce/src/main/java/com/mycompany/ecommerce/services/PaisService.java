package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Marca;
import com.mycompany.ecommerce.domains.Pais;
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
public class PaisService extends BaseService<Pais> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "p.paisCod = ?paisCod", "paisCod", filtros.get("paisCod"));
        add(fp, "UPPER(p.paisDesc) LIKE UPPER('%?paisDesc%')", "paisDesc", filtros.get("paisDesc"));
        return fp;
    }

    public List<Pais> getPaises() {
        String query = "SELECT P.* FROM PAIS P";
        return super.executeNativeQuery(Pais.class, query);
    }

    public List<Pais> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT p FROM Pais p";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public Pais getPaisPorCodigo(int codigo) {
        return getEntityManager().find(Pais.class, codigo);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(Pais pais) {
        String sql = "DELETE FROM PAIS WHERE PAIS_COD = " + pais.getPaisCod();
        super.executeNativeUpdate(sql);
    }

}
