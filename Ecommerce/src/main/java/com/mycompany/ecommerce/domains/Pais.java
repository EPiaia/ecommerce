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
@Table(name = "PAIS")
public class Pais implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "PAIS_COD")
    private Integer paisCod;
    @NotNull
    @Column(name = "PAIS_DESC")
    private String paisDesc;

    public Pais() {
    }

    public Integer getPaisCod() {
        return paisCod;
    }

    public void setPaisCod(Integer paisCod) {
        this.paisCod = paisCod;
    }

    public String getPaisDesc() {
        return paisDesc;
    }

    public void setPaisDesc(String paisDesc) {
        this.paisDesc = paisDesc;
    }

    public String getPaisLabel() {
        return paisCod + " - " + paisDesc;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.paisCod);
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
        final Pais other = (Pais) obj;
        return Objects.equals(this.paisCod, other.paisCod);
    }
}
