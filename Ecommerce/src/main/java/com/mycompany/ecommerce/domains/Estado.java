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
@Table(name = "ESTADO")
public class Estado implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "EST_COD")
    private Integer estCod;
    @NotNull
    @Column(name = "EST_DESC")
    private String estDesc;
    @NotNull
    @JoinColumn(name = "EST_PAIS", referencedColumnName = "PAIS_COD")
    @ManyToOne
    private Pais estPais;

    public Estado() {
    }

    public Integer getEstCod() {
        return estCod;
    }

    public void setEstCod(Integer estCod) {
        this.estCod = estCod;
    }

    public String getEstDesc() {
        return estDesc;
    }

    public void setEstDesc(String estDesc) {
        this.estDesc = estDesc;
    }

    public Pais getEstPais() {
        return estPais;
    }

    public void setEstPais(Pais estPais) {
        this.estPais = estPais;
    }

    public String getEstadoLabel() {
        return this.estCod + " - " + this.estDesc + " (" + this.estPais.getPaisDesc() + ")";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.estCod);
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
        final Estado other = (Estado) obj;
        return Objects.equals(this.estCod, other.estCod);
    }

}
