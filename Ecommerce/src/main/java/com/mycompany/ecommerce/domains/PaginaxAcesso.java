package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "PAGINAXACESSO")
public class PaginaxAcesso implements Serializable {

    @EmbeddedId
    private PaginaxAcessoPK pk;

    public PaginaxAcesso() {
    }

    public PaginaxAcesso(PaginaxAcessoPK pk) {
        this.pk = pk;
    }

    public PaginaxAcessoPK getPk() {
        return pk;
    }

    public void setPk(PaginaxAcessoPK pk) {
        this.pk = pk;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.pk);
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
        final PaginaxAcesso other = (PaginaxAcesso) obj;
        return Objects.equals(this.pk, other.pk);
    }
}
