package com.mycompany.ecommerce.domains;

import com.mycompany.ecommerce.utils.DateUtil;
import com.mycompany.ecommerce.utils.StatusPedido;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "PED_COD")
    private Integer pedCod;
    @NotNull
    @JoinColumn(name = "PED_ENDENTREGA", referencedColumnName = "END_COD")
    @ManyToOne
    private Endereco pedEndEntrega;
    @NotNull
    @JoinColumn(name = "PED_FORPAG", referencedColumnName = "FOP_COD")
    @ManyToOne
    private FormaPag pedForPag;
    @NotNull
    @Column(name = "PED_VLRTOTAL")
    private BigDecimal pedVlrTotal;
    @NotNull
    @Column(name = "PED_VLRDESC")
    private BigDecimal pedVlrDesc;
    @NotNull
    @Column(name = "PED_VLRDESC_FORPAG")
    private BigDecimal pedVlrDescForPag;
    @NotNull
    @Column(name = "PED_VLRFRETE")
    private BigDecimal pedVlrFrete;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PED_DATA")
    private Date pedData;
    @NotNull
    @Column(name = "PED_STATUS")
    private Integer pedStatus = 1; // 1 - Em Processamento   2 - Processado   3 - Enviado   4 - Entregue
    @Lob
    @Column(name = "PED_OBSERVACOES", columnDefinition = "TEXT")
    private String pedObservacoes;
    @Transient
    private List<Parcela> pedParcelas = new ArrayList<>();
    @Transient
    private List<PedxProd> pedProdutos = new ArrayList<>();

    public Pedido() {
    }

    public Integer getPedCod() {
        return pedCod;
    }

    public void setPedCod(Integer pedCod) {
        this.pedCod = pedCod;
    }

    public Endereco getPedEndEntrega() {
        return pedEndEntrega;
    }

    public void setPedEndEntrega(Endereco pedEndEntrega) {
        this.pedEndEntrega = pedEndEntrega;
    }

    public FormaPag getPedForPag() {
        return pedForPag;
    }

    public void setPedForPag(FormaPag pedForPag) {
        this.pedForPag = pedForPag;
    }

    public BigDecimal getPedVlrTotal() {
        return pedVlrTotal;
    }

    public void setPedVlrTotal(BigDecimal pedVlrTotal) {
        this.pedVlrTotal = pedVlrTotal;
    }

    public BigDecimal getPedVlrDesc() {
        return pedVlrDesc;
    }

    public void setPedVlrDesc(BigDecimal pedVlrDesc) {
        this.pedVlrDesc = pedVlrDesc;
    }

    public BigDecimal getPedVlrDescForPag() {
        return pedVlrDescForPag;
    }

    public void setPedVlrDescForPag(BigDecimal pedVlrDescForPag) {
        this.pedVlrDescForPag = pedVlrDescForPag;
    }

    public BigDecimal getPedVlrFrete() {
        return pedVlrFrete;
    }

    public void setPedVlrFrete(BigDecimal pedVlrFrete) {
        this.pedVlrFrete = pedVlrFrete;
    }

    public Date getPedData() {
        return pedData;
    }

    public void setPedData(Date pedData) {
        this.pedData = pedData;
    }

    public Integer getPedStatus() {
        return pedStatus;
    }

    public void setPedStatus(Integer pedStatus) {
        this.pedStatus = pedStatus;
    }

    public String getPedObservacoes() {
        return pedObservacoes;
    }

    public void setPedObservacoes(String pedObservacoes) {
        this.pedObservacoes = pedObservacoes;
    }

    public List<Parcela> getPedParcelas() {
        return pedParcelas;
    }

    public void setPedParcelas(List<Parcela> pedParcelas) {
        this.pedParcelas = pedParcelas;
    }

    public List<PedxProd> getPedProdutos() {
        return pedProdutos;
    }

    public void setPedProdutos(List<PedxProd> pedProdutos) {
        this.pedProdutos = pedProdutos;
    }

    public StatusPedido getStatus() {
        return StatusPedido.getStatusPorCodigo(this.pedStatus);
    }

    public String getDataFormatada() {
        return DateUtil.getDataHoraFormatada(pedData);
    }

    public BigDecimal getTotalBruto() {
        BigDecimal total = BigDecimal.ZERO;
        for (PedxProd prod : this.pedProdutos) {
            total = total.add(prod.getPxpVlrUni().multiply(prod.getPxpQuantidade()));
        }
        return total;
    }

    public BigDecimal getTotalDescUnitario() {
        BigDecimal total = BigDecimal.ZERO;
        for (PedxProd prod : this.pedProdutos) {
            total = total.add(prod.getPxpVlrDescUni().multiply(prod.getPxpQuantidade()));
        }
        return total;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.pedCod);
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
        final Pedido other = (Pedido) obj;
        return Objects.equals(this.pedCod, other.pedCod);
    }

}
