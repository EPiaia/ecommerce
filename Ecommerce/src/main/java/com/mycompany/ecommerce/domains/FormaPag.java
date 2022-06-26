package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "FORMA_PAG")
public class FormaPag implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "FOP_COD")
    private Integer fopCod;
    @NotNull
    @Column(name = "FOP_ATIINA")
    private String fopAtiina = "A";
    @NotNull
    @Column(name = "FOP_DESC")
    private String fopDesc;
    @NotNull
    @Column(name = "FOP_PARCELAS")
    private Integer fopParcelas;
    @NotNull
    @Column(name = "FOP_PRCDESC")
    private BigDecimal fopPrcDesc;
    @NotNull
    @Column(name = "FOP_DIASPRC")
    private Integer fopDiasPrc = 30;

    public FormaPag() {
    }

    public Integer getFopCod() {
        return fopCod;
    }

    public void setFopCod(Integer fopCod) {
        this.fopCod = fopCod;
    }

    public String getFopAtiina() {
        return fopAtiina;
    }

    public void setFopAtiina(String fopAtiina) {
        this.fopAtiina = fopAtiina;
    }

    public String getFopDesc() {
        return fopDesc;
    }

    public void setFopDesc(String fopDesc) {
        this.fopDesc = fopDesc;
    }

    public Integer getFopParcelas() {
        return fopParcelas;
    }

    public void setFopParcelas(Integer fopParcelas) {
        this.fopParcelas = fopParcelas;
    }

    public BigDecimal getFopPrcDesc() {
        return fopPrcDesc;
    }

    public void setFopPrcDesc(BigDecimal fopPrcDesc) {
        this.fopPrcDesc = fopPrcDesc;
    }

    public Integer getFopDiasPrc() {
        return fopDiasPrc;
    }

    public void setFopDiasPrc(Integer fopDiasPrc) {
        this.fopDiasPrc = fopDiasPrc;
    }

    public String getPercDescFormatado() {
        return String.format("%.2f", this.fopPrcDesc);
    }

    public boolean isPossuiDesconto() {
        return this.getFopPrcDesc() != null && this.getFopPrcDesc().compareTo(BigDecimal.ZERO) > 0;
    }

    public String getFormaPagLabel() {
        return this.fopCod + " - " + this.fopDesc;
    }

    public String getAtivoInativoCompleto() {
        if ("A".equals(this.fopAtiina)) {
            return "Ativo";
        } else if ("I".equals(this.fopAtiina)) {
            return "Inativo";
        } else {
            return "";
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.fopCod);
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
        final FormaPag other = (FormaPag) obj;
        return Objects.equals(this.fopCod, other.fopCod);
    }

}
