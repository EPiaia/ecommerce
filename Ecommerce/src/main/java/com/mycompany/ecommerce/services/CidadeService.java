package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Cidade;
import com.mycompany.ecommerce.domains.Estado;
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
public class CidadeService extends BaseService<Cidade> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "c.cidCod = ?cidCod", "cidCod", filtros.get("cidCod"));
        add(fp, "UPPER(c.cidDesc) LIKE UPPER('%?cidDesc%')", "cidDesc", filtros.get("cidDesc"));
        return fp;
    }

    public List<Cidade> getCidades() {
        String query = "SELECT C.* FROM CIDADE C";
        return super.executeNativeQuery(Cidade.class, query);
    }

    public Cidade getCidadePorCodigo(Integer codigo) {
        String sql = "SELECT C.* FROM CIDADE C WHERE C.CID_COD = " + codigo;
        List<Cidade> retorno = executeNativeQuery(Cidade.class, sql);
        if (retorno.isEmpty()) {
            return null;
        } else {
            return retorno.get(0);
        }
    }

    public List<Cidade> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT c FROM Cidade c";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(Cidade cidade) {
        String sql = "DELETE FROM CIDADE WHERE CID_COD = " + cidade.getCidCod();
        super.executeNativeUpdate(sql);
    }

    public boolean isExisteCidadeNoEstado(Estado estado) {
        if (estado == null) {
            return false;
        }
        String sql = "SELECT C.* FROM CIDADE C WHERE C.CID_ESTADO = " + estado.getEstCod();
        List<Cidade> retorno = executeNativeQuery(Cidade.class, sql);
        return retorno != null && !retorno.isEmpty();
    }

}
