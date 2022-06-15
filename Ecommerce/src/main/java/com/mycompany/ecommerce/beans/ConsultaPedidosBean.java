package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Cliente;
import com.mycompany.ecommerce.domains.Pedido;
import com.mycompany.ecommerce.services.ClienteService;
import com.mycompany.ecommerce.services.ParcelaService;
import com.mycompany.ecommerce.services.PedidoService;
import com.mycompany.ecommerce.services.PedxProdService;
import com.mycompany.ecommerce.utils.DateUtil;
import com.mycompany.ecommerce.utils.JsfUtil;
import com.mycompany.ecommerce.utils.StatusPedido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Eduardo
 */
@Named
@ViewScoped
public class ConsultaPedidosBean implements Serializable {

    @EJB
    private PedidoService pedidoService;
    @EJB
    private ClienteService cs;
    @EJB
    private PedxProdService pedxProdService;
    @EJB
    private ParcelaService parcelaService;

    @Inject
    private GeralBean gerBean;

    private Integer filtroCod;
    private Date filtroDateIni;
    private Date filtroDateFin;
    private StatusPedido filtroStatus;
    private List<StatusPedido> statusDisponiveis = new ArrayList<>();
    private Cliente filtroCliente;
    private List<Pedido> pedidosFiltrados = new ArrayList<>();
    private Pedido pedidoDetalhe;

    @PostConstruct
    private void init() {
        statusDisponiveis = StatusPedido.getStatusDisponiveis();
        if (gerBean.isTipoAcessoCliente()) {
            filtroCliente = gerBean.getClienteLogado();
            pesquisar();
        }
    }

    public void pesquisar() {
        Map<String, Object> filtros = new HashMap<>();
        if (filtroCliente != null) {
            filtros.put("pedCliente", filtroCliente.getCliCod());
        }
        if (filtroCod != null) {
            filtros.put("pedCod", filtroCod);
        } else {
            filtros.put("pedDataIni", DateUtil.getDataHoraFormatadaSql(filtroDateIni));
            filtros.put("pedDataFin", DateUtil.getDataHoraFormatadaSql(filtroDateFin));
            if (filtroStatus != null) {
                filtros.put("pedStatus", filtroStatus.getStatusCodigo());
            }
        }
        pedidosFiltrados = pedidoService.filtrar(filtros);
    }

    public void limparFiltros() {
        this.filtroCod = null;
        this.filtroDateIni = null;
        this.filtroDateFin = null;
        this.filtroStatus = null;
    }

    public List<Cliente> completeCliente(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Cliente> clientes = cs.getClientes();
        return clientes.stream().filter(t -> t.getCliNome().toLowerCase().contains(queryLowerCase) || String.valueOf(t.getCliCod()).contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void abrirDetalhesPedido(Pedido pedido) {
        this.pedidoDetalhe = pedido;
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("pxpPedCod", pedido.getPedCod());
        filtros.put("prcPedCod", pedido.getPedCod());
        pedido.setPedProdutos(pedxProdService.filtrar(filtros));
        pedido.setPedParcelas(parcelaService.filtrar(filtros));
        JsfUtil.pfShowDialog("wvDetalhePedido");
    }

    public void gravarAlteracoes() {
        pedidoService.save(pedidoDetalhe);
        JsfUtil.info("O Pedido foi salvo com sucesso");
    }

    public Integer getFiltroCod() {
        return filtroCod;
    }

    public void setFiltroCod(Integer filtroCod) {
        this.filtroCod = filtroCod;
    }

    public Date getFiltroDateIni() {
        return filtroDateIni;
    }

    public void setFiltroDateIni(Date filtroDateIni) {
        this.filtroDateIni = filtroDateIni;
    }

    public Date getFiltroDateFin() {
        return filtroDateFin;
    }

    public void setFiltroDateFin(Date filtroDateFin) {
        this.filtroDateFin = filtroDateFin;
    }

    public StatusPedido getFiltroStatus() {
        return filtroStatus;
    }

    public void setFiltroStatus(StatusPedido filtroStatus) {
        this.filtroStatus = filtroStatus;
    }

    public List<StatusPedido> getStatusDisponiveis() {
        return statusDisponiveis;
    }

    public void setStatusDisponiveis(List<StatusPedido> statusDisponiveis) {
        this.statusDisponiveis = statusDisponiveis;
    }

    public Cliente getFiltroCliente() {
        return filtroCliente;
    }

    public void setFiltroCliente(Cliente filtroCliente) {
        this.filtroCliente = filtroCliente;
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public void setPedidosFiltrados(List<Pedido> pedidosFiltrados) {
        this.pedidosFiltrados = pedidosFiltrados;
    }

    public Pedido getPedidoDetalhe() {
        return pedidoDetalhe;
    }

    public void setPedidoDetalhe(Pedido pedidoDetalhe) {
        this.pedidoDetalhe = pedidoDetalhe;
    }
}
