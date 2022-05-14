package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Produto;
import com.mycompany.ecommerce.domains.ProdutoxImagem;
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
public class ProdutoxImagemService extends BaseService {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        return fp;
    }

    public Integer getMaxOrdem(Produto produto) {
        String jpql = "SELECT MAX(p.pxiOrdem) FROM ProdutoxImagem p WHERE p.produtoxImagemPK.pxiProCod = " + produto.getProCod();
        Query query = getEntityManager().createQuery(jpql);
        Integer max = query.getFirstResult();
        if (max == null) {
            return 0;
        } else {
            return max;
        }
    }

    public List<ProdutoxImagem> getFotosProduto(Produto produto) {
        String jpql = "SELECT p FROM ProdutoxImagem p WHERE p.produtoxImagemPK.pxiProCod = " + produto.getProCod() + " ORDER BY p.pxiOrdem DESC";
        Query query = getEntityManager().createQuery(jpql);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ProdutoxImagem save(ProdutoxImagem produtoxImagem) {
        return getEntityManager().merge(produtoxImagem);
    }

}
