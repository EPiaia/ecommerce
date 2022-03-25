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
public class PaginaxAcessoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PXA_PAGINA")
    private String pxaPagina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PXA_TPACESSO")
    private Integer pxaTpAcesso;

    public PaginaxAcessoPK() {
    }

    public PaginaxAcessoPK(String pxaPagina, Integer pxaTpAcesso) {
        this.pxaPagina = pxaPagina;
        this.pxaTpAcesso = pxaTpAcesso;
    }

    public String getPxaPagina() {
        return pxaPagina;
    }

    public void setPxaPagina(String pxaPagina) {
        this.pxaPagina = pxaPagina;
    }

    public Integer getPxaTpAcesso() {
        return pxaTpAcesso;
    }

    public void setPxaTpAcesso(Integer pxaTpAcesso) {
        this.pxaTpAcesso = pxaTpAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.pxaPagina);
        hash = 19 * hash + Objects.hashCode(this.pxaTpAcesso);
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
        final PaginaxAcessoPK other = (PaginaxAcessoPK) obj;
        if (!Objects.equals(this.pxaPagina, other.pxaPagina)) {
            return false;
        }
        return Objects.equals(this.pxaTpAcesso, other.pxaTpAcesso);
    }
}
