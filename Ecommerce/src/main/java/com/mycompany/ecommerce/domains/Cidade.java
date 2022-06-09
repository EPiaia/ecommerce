package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "CIDADE")
public class Cidade implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "CID_COD")
    private Integer cidCod;
    @NotNull
    @Column(name = "CID_DESC")
    private String cidDesc;
    @NotNull
    @JoinColumn(name = "CID_ESTADO", referencedColumnName = "EST_COD")
    @ManyToOne
    private Estado cidEstado;

    public Cidade() {
    }

    public Integer getCidCod() {
        return cidCod;
    }

    public void setCidCod(Integer cidCod) {
        this.cidCod = cidCod;
    }

    public String getCidDesc() {
        return cidDesc;
    }

    public void setCidDesc(String cidDesc) {
        this.cidDesc = cidDesc;
    }

    public Estado getCidEstado() {
        return cidEstado;
    }

    public void setCidEstado(Estado cidEstado) {
        this.cidEstado = cidEstado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.cidCod);
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
        final Cidade other = (Cidade) obj;
        return Objects.equals(this.cidCod, other.cidCod);
    }
}
