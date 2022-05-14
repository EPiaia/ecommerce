package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Embeddable
public class ProdutoxImagemPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PXI_PROCOD")
    private Integer pxiProCod;
    @Basic(optional = false)
    @NotNull
    @GeneratedValue
    @Column(name = "PXI_SEQ")
    private Integer pxiSeq;

    public ProdutoxImagemPK() {
    }

    public Integer getPxiProCod() {
        return pxiProCod;
    }

    public void setPxiProCod(Integer pxiProCod) {
        this.pxiProCod = pxiProCod;
    }

    public Integer getPxiSeq() {
        return pxiSeq;
    }

    public void setPxiSeq(Integer pxiSeq) {
        this.pxiSeq = pxiSeq;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.pxiProCod);
        hash = 37 * hash + Objects.hashCode(this.pxiSeq);
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
        final ProdutoxImagemPK other = (ProdutoxImagemPK) obj;
        if (!Objects.equals(this.pxiProCod, other.pxiProCod)) {
            return false;
        }
        return Objects.equals(this.pxiSeq, other.pxiSeq);
    }

}
