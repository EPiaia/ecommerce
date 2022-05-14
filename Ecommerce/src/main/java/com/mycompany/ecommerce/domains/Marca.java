package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "MARCA")
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAR_COD")
    private Integer marCod;
    @NotNull
    @Column(name = "MAR_DESC")
    private String marDesc;

    public Marca() {
    }

    public Integer getMarCod() {
        return marCod;
    }

    public void setMarCod(Integer marCod) {
        this.marCod = marCod;
    }

    public String getMarDesc() {
        return marDesc;
    }

    public void setMarDesc(String marDesc) {
        this.marDesc = marDesc;
    }

    public String getMarcaLabel() {
        return marCod + " - " + marDesc;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.marCod);
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
        final Marca other = (Marca) obj;
        return Objects.equals(this.marCod, other.marCod);
    }
}
