/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce.domains;

import com.mycompany.ecommerce.utils.DateUtil;
import com.mycompany.ecommerce.utils.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Eduar
 */
public class Carrinho implements Serializable {

    private List<Item> itens = new ArrayList<>();
    private Cliente cliente;
    private Configuracao configuracoes;
    private Endereco enderecoEntrega;
    private FormaPag formaPagamento;
    private BigDecimal valorTotal = BigDecimal.ZERO; // Valor dos itens, menos desconto da forma de pagamento mais valor do frete
    private BigDecimal valorFrete = BigDecimal.ZERO;
    private BigDecimal valorDescFormaPag = BigDecimal.ZERO;
    private List<Parcela> parcelas = new ArrayList<>();

    public Carrinho() {
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Configuracao getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(Configuracao configuracoes) {
        this.configuracoes = configuracoes;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public FormaPag getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPag formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorDescFormaPag() {
        return valorDescFormaPag;
    }

    public void setValorDescFormaPag(BigDecimal valorDescFormaPag) {
        this.valorDescFormaPag = valorDescFormaPag;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public boolean isProdutoEstaNoCarrinho(Produto produto) {
        for (Item item : getItens()) {
            if (item.getProduto().equals(produto)) {
                return true;
            }
        }
        return false;
    }

    public void removerItem(Item item) {
        this.itens.remove(item);
        recalcularValores();
        JsfUtil.info("Produto removido com sucesso");
    }

    public void aumentarQuantidade(Item item) {
        for (Item itemCarrinho : itens) {
            if (itemCarrinho.equals(item)) {
                itemCarrinho.setQuantidade(itemCarrinho.getQuantidade().add(BigDecimal.ONE));
            }
        }
        recalcularValores();
    }

    public void diminuirQuantidade(Item item) {
        for (Item itemCarrinho : itens) {
            if (itemCarrinho.equals(item)) {
                if (itemCarrinho.getQuantidade().compareTo(BigDecimal.ONE) == 0) {
                    JsfUtil.warn("A quantidade mínima é 1");
                } else {
                    itemCarrinho.setQuantidade(itemCarrinho.getQuantidade().subtract(BigDecimal.ONE));
                }
            }
        }
        recalcularValores();
    }

    public BigDecimal getValorTotalItens() {
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : itens) {
            total = total.add(item.getValorTotal());
        }
        return total;
    }

    public BigDecimal getValorTotalBrutoItens() {
        BigDecimal totalBruto = BigDecimal.ZERO;
        for (Item item : itens) {
            totalBruto = totalBruto.add(item.getProduto().getProValorUni().multiply(item.getQuantidade()));
        }
        return totalBruto;
    }

    public BigDecimal getValorTotalDescontos() {
        BigDecimal totalDesc = BigDecimal.ZERO;
        for (Item item : itens) {
            totalDesc = totalDesc.add(item.getProduto().getDescontoUnitario().multiply(item.getQuantidade()));
        }
        totalDesc = totalDesc.add(this.valorDescFormaPag);
        return totalDesc;
    }

    public BigDecimal getValorTotalSemFrete() {
        return valorTotal.subtract(valorFrete);
    }

    public boolean isPossuiDescontoFormaPag() {
        return this.formaPagamento != null && this.formaPagamento.isPossuiDesconto();
    }

    public boolean isPossuiFrete() {
        return this.valorFrete != null && this.valorFrete.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isPossuiFreteOuDesconto() {
        return isPossuiDescontoFormaPag() || isPossuiFrete();
    }

    public boolean isPossuiDesconto() {
        return getValorTotalDescontos().compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isRenderFreteGratis() {
        return this.configuracoes.isCfgCtrlFrete() && this.configuracoes.getCfgVlrMinFrete() != null && this.getValorTotalSemFrete().compareTo(this.configuracoes.getCfgVlrMinFrete()) <= 0;
    }

    public void recalcularValores() {
        setValorFrete(BigDecimal.ZERO);
        setValorTotal(BigDecimal.ZERO);
        setValorDescFormaPag(BigDecimal.ZERO);
        this.parcelas = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (Item item : getItens()) {
            valorTotal = valorTotal.add(item.getValorTotal());
        }
        if (getFormaPagamento() != null && getFormaPagamento().isPossuiDesconto()) {
            BigDecimal descontoFormaPag = valorTotal.multiply(getFormaPagamento().getFopPrcDesc().divide(new BigDecimal(100)));
            setValorDescFormaPag(descontoFormaPag);
            valorTotal = valorTotal.subtract(descontoFormaPag);
        }
        if (getConfiguracoes().isCfgCtrlFrete() && BigDecimal.ZERO.compareTo(getConfiguracoes().getCfgFretePad()) < 0) {
            BigDecimal frete = getConfiguracoes().getCfgFretePad();
            if (getConfiguracoes().getCfgVlrMinFrete() != null) {
                if (getConfiguracoes().getCfgVlrMinFrete().compareTo(valorTotal) > 0) {
                    setValorFrete(frete);
                }
            } else {
                setValorFrete(frete);
            }
        }
        valorTotal = valorTotal.add(getValorFrete());
        setValorTotal(valorTotal);
        if (getFormaPagamento() != null) {
            dividirParcelas();
        }
    }

    public void dividirParcelas() {
        int qtdParcelas = getFormaPagamento().getFopParcelas();
        int dias = getFormaPagamento().getFopDiasPrc();
        BigDecimal vlrParcelas = getValorTotal().divide(new BigDecimal(qtdParcelas), 2, RoundingMode.HALF_UP);
        Date dtVencimento = new Date();
        for (int i = 1; i <= getFormaPagamento().getFopParcelas(); i++) {
            Parcela parcela = new Parcela();
            parcela.getParcelaPk().setPrcSeq(i);
            parcela.setPrcVlr(vlrParcelas);
            parcela.setPrcDtVenc(DateUtil.addDays(dtVencimento, dias * i));
            getParcelas().add(parcela);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.itens);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carrinho other = (Carrinho) obj;
        return Objects.equals(this.itens, other.itens);
    }
}
