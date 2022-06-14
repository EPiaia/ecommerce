package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "PEDXPROD")
public class PedxProd implements Serializable {

    @EmbeddedId
    private PedxProdPK pedxProdPk = new PedxProdPK();
    @JoinColumn(name = "PXP_PEDCOD", referencedColumnName = "PED_COD", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pxpPedido;
    @JoinColumn(name = "PXP_PROCOD", referencedColumnName = "PRO_COD")
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto pxpProduto;
    @NotNull
    @Column(name = "PXP_VLRUNI")
    private BigDecimal pxpVlrUni;
    @NotNull
    @Column(name = "PXP_VLRDESCUNI")
    private BigDecimal pxpVlrDescUni;
    @NotNull
    @Column(name = "PXP_QUANTIDADE")
    private BigDecimal pxpQuantidade;

    public PedxProd() {
    }

    public PedxProdPK getPedxProdPk() {
        return pedxProdPk;
    }

    public void setPedxProdPk(PedxProdPK pedxProdPk) {
        this.pedxProdPk = pedxProdPk;
    }

    public Pedido getPxpPedido() {
        return pxpPedido;
    }

    public void setPxpPedido(Pedido pxpPedido) {
        this.pxpPedido = pxpPedido;
    }

    public Produto getPxpProduto() {
        return pxpProduto;
    }

    public void setPxpProduto(Produto pxpProduto) {
        this.pxpProduto = pxpProduto;
    }

    public BigDecimal getPxpVlrUni() {
        return pxpVlrUni;
    }

    public void setPxpVlrUni(BigDecimal pxpVlrUni) {
        this.pxpVlrUni = pxpVlrUni;
    }

    public BigDecimal getPxpVlrDescUni() {
        return pxpVlrDescUni;
    }

    public void setPxpVlrDescUni(BigDecimal pxpVlrDescUni) {
        this.pxpVlrDescUni = pxpVlrDescUni;
    }

    public BigDecimal getPxpQuantidade() {
        return pxpQuantidade;
    }

    public void setPxpQuantidade(BigDecimal pxpQuantidade) {
        this.pxpQuantidade = pxpQuantidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.pedxProdPk);
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
        final PedxProd other = (PedxProd) obj;
        return Objects.equals(this.pedxProdPk, other.pedxProdPk);
    }

}
