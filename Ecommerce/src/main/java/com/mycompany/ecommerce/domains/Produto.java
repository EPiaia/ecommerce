package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "PRO_COD")
    private Integer proCod;
    @Column(name = "PRO_DESC")
    private String proDesc;
    @JoinColumn(name = "PRO_MARCA", referencedColumnName = "MAR_COD")
    @ManyToOne(fetch = FetchType.LAZY)
    private Marca proMarca;
    @JoinColumn(name = "PRO_LINHA", referencedColumnName = "LIN_COD")
    @ManyToOne(fetch = FetchType.LAZY)
    private Linha proLinha;
    @Column(name = "PRO_OBSERVACOES")
    private String proObservacoes;
    @Column(name = "PRO_VALORUNI")
    private BigDecimal proValorUni;
    @Column(name = "PRO_PERDESC")
    private BigDecimal proPerDesc;
    @Temporal(TemporalType.DATE)
    @Column(name = "PRO_DESC_DTINI")
    private Date proDescDtIni;
    @Temporal(TemporalType.DATE)
    @Column(name = "PRO_DESC_DTFIN")
    private Date proDescDtFin;
    @Transient
    private List<ProdutoxImagem> fotosProduto = new ArrayList<>();

    public Produto() {
    }

    public Integer getProCod() {
        return proCod;
    }

    public void setProCod(Integer proCod) {
        this.proCod = proCod;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public Marca getProMarca() {
        return proMarca;
    }

    public void setProMarca(Marca proMarca) {
        this.proMarca = proMarca;
    }

    public Linha getProLinha() {
        return proLinha;
    }

    public void setProLinha(Linha proLinha) {
        this.proLinha = proLinha;
    }

    public String getProObservacoes() {
        return proObservacoes;
    }

    public void setProObservacoes(String proObservacoes) {
        this.proObservacoes = proObservacoes;
    }

    public BigDecimal getProValorUni() {
        return proValorUni;
    }

    public void setProValorUni(BigDecimal proValorUni) {
        this.proValorUni = proValorUni;
    }

    public BigDecimal getProPerDesc() {
        return proPerDesc;
    }

    public void setProPerDesc(BigDecimal proPerDesc) {
        this.proPerDesc = proPerDesc;
    }

    public Date getProDescDtIni() {
        return proDescDtIni;
    }

    public void setProDescDtIni(Date proDescDtIni) {
        this.proDescDtIni = proDescDtIni;
    }

    public Date getProDescDtFin() {
        return proDescDtFin;
    }

    public void setProDescDtFin(Date proDescDtFin) {
        this.proDescDtFin = proDescDtFin;
    }

    public List<ProdutoxImagem> getFotosProduto() {
        return fotosProduto;
    }

    public void setFotosProduto(List<ProdutoxImagem> fotosProduto) {
        this.fotosProduto = fotosProduto;
    }

    public String getProDescDtIniFormatada() {
        if (this.proDescDtIni != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(this.proDescDtIni);
        } else {
            return "";
        }
    }

    public String getProDescDtFinFormatada() {
        if (this.proDescDtFin != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(this.proDescDtFin);
        } else {
            return "";
        }
    }

    public boolean isDescontoValido() {
        Date hoje = new Date();
        return (proDescDtIni != null && hoje.after(proDescDtIni) && (proDescDtFin == null || hoje.before(proDescDtFin)));
    }

    public boolean isPossuiDesconto() {
        return proPerDesc != null && isDescontoValido();
    }

    public String getImgPrincipal() {
        if (fotosProduto.isEmpty()) {
            return "";
        } else {
            return fotosProduto.get(0).getImgBase64();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.proCod);
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
        final Produto other = (Produto) obj;
        return Objects.equals(this.proCod, other.proCod);
    }
}
