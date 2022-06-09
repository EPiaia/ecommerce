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
public class ClixEndPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CXE_CLICOD")
    private Integer cxeCliCod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CXE_ENDCOD")
    private Integer cxeEndCod;

    public ClixEndPK() {
    }

    public ClixEndPK(Integer cxeCliCod, Integer cxeEndCod) {
        this.cxeCliCod = cxeCliCod;
        this.cxeEndCod = cxeEndCod;
    }

    public Integer getCxeCliCod() {
        return cxeCliCod;
    }

    public void setCxeCliCod(Integer cxeCliCod) {
        this.cxeCliCod = cxeCliCod;
    }

    public Integer getCxeEndCod() {
        return cxeEndCod;
    }

    public void setCxeEndCod(Integer cxeEndCod) {
        this.cxeEndCod = cxeEndCod;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.cxeCliCod);
        hash = 59 * hash + Objects.hashCode(this.cxeEndCod);
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
        final ClixEndPK other = (ClixEndPK) obj;
        if (!Objects.equals(this.cxeCliCod, other.cxeCliCod)) {
            return false;
        }
        return Objects.equals(this.cxeEndCod, other.cxeEndCod);
    }

}
