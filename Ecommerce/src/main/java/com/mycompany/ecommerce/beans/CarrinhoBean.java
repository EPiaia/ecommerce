/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Carrinho;
import com.mycompany.ecommerce.domains.Cidade;
import com.mycompany.ecommerce.domains.Configuracao;
import com.mycompany.ecommerce.domains.Endereco;
import com.mycompany.ecommerce.domains.FormaPag;
import com.mycompany.ecommerce.domains.Item;
import com.mycompany.ecommerce.domains.Produto;
import com.mycompany.ecommerce.services.CidadeService;
import com.mycompany.ecommerce.services.ConfiguracaoService;
import com.mycompany.ecommerce.services.EnderecoService;
import com.mycompany.ecommerce.services.FormaPagService;
import com.mycompany.ecommerce.services.PedidoService;
import com.mycompany.ecommerce.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Eduar
 */
@Named
@SessionScoped
public class CarrinhoBean implements Serializable {

    @EJB
    private EnderecoService es;
    @EJB
    private CidadeService cs;
    @EJB
    private FormaPagService fps;
    @EJB
    private ConfiguracaoService confService;
    @EJB
    private PedidoService ps;

    @Inject
    private GeralBean geralBean;

    private Carrinho carrinho = new Carrinho();
    private List<Endereco> enderecosDisponiveis = new ArrayList<>();
    private Endereco enderecoCadastro;
    private List<FormaPag> formasPagDisponiveis = new ArrayList<>();
    private Integer indexSteps = 0;

    @PostConstruct
    private void init() {
        inicializarCarrinho();
    }

    public void inicializarCarrinho() {
        this.carrinho = new Carrinho();
        this.carrinho.setCliente(geralBean.getClienteLogado());
        Configuracao configuracao = confService.getConfiguracao();
        this.carrinho.setConfiguracoes(configuracao);
        preencherEnderecos();
        formasPagDisponiveis = fps.getFormasDePagamentoAtivas();
    }

    public void cancelarCarrinho() {
        inicializarCarrinho();
        JsfUtil.redirect("/Ecommerce/index.xhtml");
    }

    public void concluirPedido() {
        ps.salvarPedido(carrinho);
        inicializarCarrinho();
        JsfUtil.redirect("/Ecommerce/restrito/pedidos.xhtml");
    }

    public String onFlowProcess(FlowEvent event) {
        JsfUtil.pfUpdate("form1:mensagens");
        if ("tabFormaPag".equals(event.getNewStep()) || "tabRevPed".equals(event.getNewStep())) {
            if (this.carrinho.getEnderecoEntrega() == null) {
                JsfUtil.error("Selecione um Endereço de Entrega");
                return event.getOldStep();
            }
        }
        if ("tabRevPed".equals(event.getNewStep())) {
            if (this.carrinho.getFormaPagamento() == null) {
                JsfUtil.error("Selecione uma Forma de Pagamento");
                return event.getOldStep();
            }
        }
        if ("tabItens".equals(event.getNewStep())) {
            indexSteps = 0;
        } else if ("tabEndEntrega".equals(event.getNewStep())) {
            indexSteps = 1;
        } else if ("tabFormaPag".equals(event.getNewStep())) {
            indexSteps = 2;
        } else if ("tabRevPed".equals(event.getNewStep())) {
            indexSteps = 3;
        }
        return event.getNewStep();
    }

    public void adicionarProdutoAoCarrinho(Produto produto) {
        if (geralBean.isTipoAcessoUsuarioPublico()) {
            JsfUtil.redirect("/Ecommerce/login.xhtml");
        } else if (geralBean.isTipoAcessoCliente()) {
            Item item = new Item(produto);
            this.carrinho.getItens().add(item);
            this.carrinho.recalcularValores();
            JsfUtil.info("Produto adicionado ao carrinho com sucesso");
        }
    }

    public void abrirCadastroEndereco() {
        enderecoCadastro = new Endereco();
        JsfUtil.pfShowDialog("wvCadEndereco");
    }

    public List<Cidade> completeCidade(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Cidade> cidades = cs.getCidades();
        return cidades.stream().filter(t -> t.getCidDesc().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void cadastrarEndereco() {
        if (!validarEndereco()) {
            return;
        }
        enderecoCadastro.setEndCliente(this.carrinho.getCliente());
        enderecoCadastro = es.save(enderecoCadastro);
        preencherEnderecos();
        this.carrinho.recalcularValores();
        JsfUtil.pfHideDialog("wvCadEndereco");
        JsfUtil.info("Endereço cadastrado com sucesso");
    }

    public boolean validarEndereco() {
        return this.enderecoCadastro.getEndCidade() != null;
    }

    public void preencherEnderecos() {
        enderecosDisponiveis = es.getEnderecosDoCliente(this.carrinho.getCliente());
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public List<Endereco> getEnderecosDisponiveis() {
        return enderecosDisponiveis;
    }

    public void setEnderecosDisponiveis(List<Endereco> enderecosDisponiveis) {
        this.enderecosDisponiveis = enderecosDisponiveis;
    }

    public List<FormaPag> getFormasPagDisponiveis() {
        return formasPagDisponiveis;
    }

    public void setFormasPagDisponiveis(List<FormaPag> formasPagDisponiveis) {
        this.formasPagDisponiveis = formasPagDisponiveis;
    }

    public Endereco getEnderecoCadastro() {
        return enderecoCadastro;
    }

    public void setEnderecoCadastro(Endereco enderecoCadastro) {
        this.enderecoCadastro = enderecoCadastro;
    }

    public Integer getIndexSteps() {
        return indexSteps;
    }

    public void setIndexSteps(Integer indexSteps) {
        this.indexSteps = indexSteps;
    }
}
