package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "LINHA")
public class Linha implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "LIN_COD")
    private Integer linCod;
    @NotNull
    @Column(name = "LIN_DESC")
    private String linDesc;

    public Linha() {
    }

    public Integer getLinCod() {
        return linCod;
    }

    public void setLinCod(Integer linCod) {
        this.linCod = linCod;
    }

    public String getLinDesc() {
        return linDesc;
    }

    public void setLinDesc(String linDesc) {
        this.linDesc = linDesc;
    }

    public String getLinhaLabel() {
        return linCod + " - " + linDesc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.linCod);
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
        final Linha other = (Linha) obj;
        return Objects.equals(this.linCod, other.linCod);
    }
}
