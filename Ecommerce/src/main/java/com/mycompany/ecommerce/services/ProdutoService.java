package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Produto;
import com.mycompany.ecommerce.utils.FiltrosPesquisa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
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
public class ProdutoService extends BaseService<Produto> {

    @EJB
    private ProdutoxImagemService pis;

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "p.proCod = ?proCod", "proCod", filtros.get("proCod"));
        add(fp, "UPPER(p.proDesc) like UPPER('%?proDesc%')", "proDesc", filtros.get("proDesc"));
        add(fp, "p.proMarca = ?proMarca", "proMarca", filtros.get("proMarca"));
        add(fp, "p.proLinha = ?proLinha", "proLinha", filtros.get("proLinha"));
        add(fp, "p.proDescData >= ?proDescData OR p.proDescData IS NULL", "proDescData", filtros.get("proDescData"));
        return fp;
    }

    public List<Produto> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT p FROM Produto p";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public List<Produto> getProdutosDisponiveis() {
        List<Produto> produtos = filtrar(new HashMap());
        for (Produto produto : produtos) {
            produto.setFotosProduto(pis.getFotosProduto(produto));
        }
        return produtos;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(Produto produto) {
        Produto produtoManaged = getEntityManager().find(Produto.class, produto.getProCod());
        getEntityManager().remove(produtoManaged);
    }
}
