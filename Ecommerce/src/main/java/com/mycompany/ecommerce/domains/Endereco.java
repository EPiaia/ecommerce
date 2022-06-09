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
@Table(name = "ENDERECO")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "END_COD")
    private Integer endCod;
    @NotNull
    @JoinColumn(name = "END_CIDADE", referencedColumnName = "CID_COD")
    @ManyToOne
    private Cidade endCidade;
    @NotNull
    @Column(name = "END_BAIRRO")
    private String endBairro;
    @NotNull
    @Column(name = "END_RUA")
    private String endRua;
    @NotNull
    @Column(name = "END_NUM")
    private String endNum;
    @Column(name = "END_COMPL")
    private String endCompl;

    public Endereco() {
    }

    public Integer getEndCod() {
        return endCod;
    }

    public void setEndCod(Integer endCod) {
        this.endCod = endCod;
    }

    public Cidade getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(Cidade endCidade) {
        this.endCidade = endCidade;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndRua() {
        return endRua;
    }

    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }

    public String getEndNum() {
        return endNum;
    }

    public void setEndNum(String endNum) {
        this.endNum = endNum;
    }

    public String getEndCompl() {
        return endCompl;
    }

    public void setEndCompl(String endCompl) {
        this.endCompl = endCompl;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.endCod);
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
        final Endereco other = (Endereco) obj;
        return Objects.equals(this.endCod, other.endCod);
    }

}
