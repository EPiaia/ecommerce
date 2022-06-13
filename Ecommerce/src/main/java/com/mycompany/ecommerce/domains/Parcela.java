package com.mycompany.ecommerce.domains;

import com.mycompany.ecommerce.utils.DateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "PARCELA")
public class Parcela implements Serializable {

    @EmbeddedId
    private ParcelaPK parcelaPk = new ParcelaPK();
    @NotNull
    @Column(name = "PRC_VLR")
    private BigDecimal prcVlr;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PRC_DTVENC")
    private Date prcDtVenc;

    public Parcela() {
    }

    public ParcelaPK getParcelaPk() {
        return parcelaPk;
    }

    public void setParcelaPk(ParcelaPK parcelaPk) {
        this.parcelaPk = parcelaPk;
    }

    public BigDecimal getPrcVlr() {
        return prcVlr;
    }

    public void setPrcVlr(BigDecimal prcVlr) {
        this.prcVlr = prcVlr;
    }

    public Date getPrcDtVenc() {
        return prcDtVenc;
    }

    public void setPrcDtVenc(Date prcDtVenc) {
        this.prcDtVenc = prcDtVenc;
    }

    public String getDtVencFormatada() {
        return DateUtil.getDataFormatada(prcDtVenc);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.parcelaPk);
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
        final Parcela other = (Parcela) obj;
        return Objects.equals(this.parcelaPk, other.parcelaPk);
    }

}
