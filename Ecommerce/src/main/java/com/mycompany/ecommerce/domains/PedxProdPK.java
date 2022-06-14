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
public class PedxProdPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PXP_PEDCOD")
    private Integer pxpPedCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PXP_SEQ")
    private Integer pxpSeq;

    public PedxProdPK() {
    }

    public PedxProdPK(Integer pxpPedCod, Integer pxpSeq) {
        this.pxpPedCod = pxpPedCod;
        this.pxpSeq = pxpSeq;
    }

    public Integer getPxpPedCod() {
        return pxpPedCod;
    }

    public void setPxpPedCod(Integer pxpPedCod) {
        this.pxpPedCod = pxpPedCod;
    }

    public Integer getPxpSeq() {
        return pxpSeq;
    }

    public void setPxpSeq(Integer pxpSeq) {
        this.pxpSeq = pxpSeq;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.pxpPedCod);
        hash = 67 * hash + Objects.hashCode(this.pxpSeq);
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
        final PedxProdPK other = (PedxProdPK) obj;
        if (!Objects.equals(this.pxpPedCod, other.pxpPedCod)) {
            return false;
        }
        return Objects.equals(this.pxpSeq, other.pxpSeq);
    }

}
