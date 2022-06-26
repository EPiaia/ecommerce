package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Linha;
import com.mycompany.ecommerce.domains.Marca;
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
        add(fp, "p.proAtiina = '?proAtiina'", "proAtiina", filtros.get("proAtiina"));
        return fp;
    }

    public List<Produto> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT p FROM Produto p";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public List<Produto> getProdutosDisponiveis() {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("proAtiina", "A");
        List<Produto> produtos = filtrar(filtros);
        for (Produto produto : produtos) {
            produto.setFotosProduto(pis.getFotosProduto(produto));
        }
        return produtos;
    }

    public List<Produto> pesquisarProdutos(Map<String, String> filtros) {
        boolean isDesc = filtros.containsKey("desc") && filtros.get("desc") != null;
        boolean isLinha = filtros.containsKey("linha") && filtros.get("linha") != null;
        boolean isMarca = filtros.containsKey("marca") && filtros.get("marca") != null;
        String sql = "SELECT P.* FROM PRODUTO P ";
        if (isDesc) {
            String desc = filtros.get("desc");
            sql += " INNER JOIN MARCA M ON M.MAR_COD = P.PRO_MARCA "
                    + " INNER JOIN LINHA L ON L.LIN_COD = P.PRO_LINHA "
                    + " WHERE (P.PRO_DESC LIKE '%" + desc + "%' "
                    + " OR M.MAR_DESC LIKE '%" + desc + "%' "
                    + " OR L.LIN_DESC LIKE '%" + desc + "%')";
        } else if (isLinha) {
            String linha = filtros.get("linha");
            sql += " INNER JOIN LINHA L ON L.LIN_COD = P.PRO_LINHA "
                    + " WHERE L.LIN_COD = " + linha;
        } else if (isMarca) {
            String marca = filtros.get("marca");
            sql += " INNER JOIN MARCA M ON M.MAR_COD = P.PRO_MARCA "
                    + " WHERE M.MAR_COD = " + marca;
        } else {
            return new ArrayList<>();
        }
        sql += " AND P.PRO_ATIINA = 'A'";
        return executeNativeQuery(Produto.class, sql);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(Produto produto) {
        Produto produtoManaged = getEntityManager().find(Produto.class, produto.getProCod());
        getEntityManager().remove(produtoManaged);
    }

    public boolean isExisteProdutoDaMarca(Marca marca) {
        String sql = "SELECT P.* FROM PRODUTO P WHERE P.PRO_MARCA = " + marca.getMarCod();
        List<Produto> retorno = executeNativeQuery(Produto.class, sql);
        return retorno != null && !retorno.isEmpty();
    }

    public boolean isExisteProdutoDaLinha(Linha linha) {
        String sql = "SELECT P.* FROM PRODUTO P WHERE P.PRO_LINHA = " + linha.getLinCod();
        List<Produto> retorno = executeNativeQuery(Produto.class, sql);
        return retorno != null && !retorno.isEmpty();
    }

}
