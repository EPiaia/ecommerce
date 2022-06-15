package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Carrinho;
import com.mycompany.ecommerce.domains.Item;
import com.mycompany.ecommerce.domains.Parcela;
import com.mycompany.ecommerce.domains.Pedido;
import com.mycompany.ecommerce.domains.PedxProd;
import com.mycompany.ecommerce.utils.FiltrosPesquisa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Piaia
 */
@Stateless
@Named
public class PedidoService extends BaseService<Pedido> {

    @EJB
    private ParcelaService ps;
    @EJB
    private PedxProdService pps;

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        add(fp, "p.pedCod = ?pedCod", "pedCod", filtros.get("pedCod"));
        add(fp, "p.pedData >= ?pedDataIni", "pedDataIni", filtros.get("pedDataIni"));
        add(fp, "p.pedData <= ?pedDataFin", "pedDataFin", filtros.get("pedDataFin"));
        add(fp, "p.pedStatus = ?pedStatus", "pedStatus", filtros.get("pedStatus"));
        add(fp, "p.pedEndEntrega.endCliente.cliCod = ?pedCliente", "pedCliente", filtros.get("pedCliente"));
        return fp;
    }

    public List<Pedido> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT p FROM Pedido p";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        sql += " order by p.pedData desc";
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }

    public void salvarPedido(Carrinho carrinho) {
        Pedido pedido = new Pedido();
        pedido.setPedEndEntrega(carrinho.getEnderecoEntrega());
        pedido.setPedForPag(carrinho.getFormaPagamento());
        pedido.setPedVlrTotal(carrinho.getValorTotal());
        pedido.setPedVlrDesc(carrinho.getValorTotalDescontos());
        pedido.setPedVlrDescForPag(carrinho.getValorDescFormaPag());
        pedido.setPedVlrFrete(carrinho.getValorFrete());
        pedido.setPedData(new Date());
        pedido = super.save(pedido);

        int countPed = 1;
        for (Item item : carrinho.getItens()) {
            PedxProd prod = new PedxProd();
            prod.getPedxProdPk().setPxpPedCod(pedido.getPedCod());
            prod.getPedxProdPk().setPxpSeq(countPed);
            prod.setPxpProduto(item.getProduto());
            prod.setPxpVlrUni(item.getProduto().getProValorUni());
            prod.setPxpVlrDescUni(item.getProduto().getDescontoUnitario());
            prod.setPxpQuantidade(item.getQuantidade());
            pps.save(prod);
            countPed++;
        }

        countPed = 1;
        for (Parcela parcela : carrinho.getParcelas()) {
            parcela.getParcelaPk().setPrcPedCod(pedido.getPedCod());
            parcela.getParcelaPk().setPrcSeq(countPed);
            ps.save(parcela);
        }
    }

}
