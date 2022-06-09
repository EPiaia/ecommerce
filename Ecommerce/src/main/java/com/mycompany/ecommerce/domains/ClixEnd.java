package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "CLIXEND")
public class ClixEnd implements Serializable {

    @EmbeddedId
    private ClixEndPK clixEndPk;
    @NotNull
    @JoinColumn(name = "CXE_CLICOD", referencedColumnName = "CLI_COD", insertable = false, updatable = false)
    @ManyToOne
    private Cliente cxeCliente;
    @NotNull
    @JoinColumn(name = "CXE_ENDCOD", referencedColumnName = "END_COD", insertable = false, updatable = false)
    @OneToOne
    private Endereco cxeEndereco;

    public ClixEnd() {
    }

    public ClixEndPK getClixEndPk() {
        return clixEndPk;
    }

    public void setClixEndPk(ClixEndPK clixEndPk) {
        this.clixEndPk = clixEndPk;
    }

    public Cliente getCxeCliente() {
        return cxeCliente;
    }

    public void setCxeCliente(Cliente cxeCliente) {
        this.cxeCliente = cxeCliente;
    }

    public Endereco getCxeEndereco() {
        return cxeEndereco;
    }

    public void setCxeEndereco(Endereco cxeEndereco) {
        this.cxeEndereco = cxeEndereco;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.clixEndPk);
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
        final ClixEnd other = (ClixEnd) obj;
        return Objects.equals(this.clixEndPk, other.clixEndPk);
    }
}
