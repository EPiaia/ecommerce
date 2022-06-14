package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Embeddable
public class ParcelaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PRC_PEDCOD")
    private Integer prcPedCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRC_SEQ")
    private Integer prcSeq;

    public ParcelaPK() {
    }

    public ParcelaPK(Integer prcPedCod, Integer prcSeq) {
        this.prcPedCod = prcPedCod;
        this.prcSeq = prcSeq;
    }

    public Integer getPrcPedCod() {
        return prcPedCod;
    }

    public void setPrcPedCod(Integer prcPedCod) {
        this.prcPedCod = prcPedCod;
    }

    public Integer getPrcSeq() {
        return prcSeq;
    }

    public void setPrcSeq(Integer prcSeq) {
        this.prcSeq = prcSeq;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.prcPedCod);
        hash = 71 * hash + Objects.hashCode(this.prcSeq);
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
        final ParcelaPK other = (ParcelaPK) obj;
        if (!Objects.equals(this.prcPedCod, other.prcPedCod)) {
            return false;
        }
        return Objects.equals(this.prcSeq, other.prcSeq);
    }

}
