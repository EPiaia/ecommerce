package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Marca;
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
public class MarcaService extends BaseService {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "m.marCod = ?marCod", "marCod", filtros.get("marCod"));
        add(fp, "UPPER(m.marDesc) LIKE UPPER('%?marDesc%')", "marDesc", filtros.get("marDesc"));
        return fp;
    }

    public List<Marca> getMarcas() {
        Query query = getEntityManager().createQuery("SELECT m FROM Marca m");
        return query.getResultList();
    }

    public Marca getMarcaPorCodigo(int codigo) {
        return getEntityManager().find(Marca.class, codigo);
    }

    public List<Marca> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT m FROM Marca m";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Marca save(Marca marca) {
        return getEntityManager().merge(marca);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(Marca marca) {
        Marca marcaManaged = getEntityManager().find(Marca.class, marca.getMarCod());
        getEntityManager().remove(marcaManaged);
    }
}
