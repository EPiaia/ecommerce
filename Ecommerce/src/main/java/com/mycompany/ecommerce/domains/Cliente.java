package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "CLI_COD")
    private Integer cliCod;
    @NotNull
    @Column(name = "CLI_CPF")
    private String cliCpf;
    @NotNull
    @Column(name = "CLI_NOME")
    private String cliNome;
    @Column(name = "CLI_TELEFONE")
    private String cliTelefone;
    @Size(max = 50)
    @Column(name = "CLI_EMAIL")
    private String cliEmail;
    @NotNull
    @JoinColumn(name = "CLI_USUARIO", referencedColumnName = "USR_LOGIN")
    @OneToOne
    private Usuario cliUsuario;

    public Cliente() {
    }

    public Integer getCliCod() {
        return cliCod;
    }

    public void setCliCod(Integer cliCod) {
        this.cliCod = cliCod;
    }

    public String getCliCpf() {
        return cliCpf;
    }

    public void setCliCpf(String cliCpf) {
        this.cliCpf = cliCpf;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliTelefone() {
        return cliTelefone;
    }

    public void setCliTelefone(String cliTelefone) {
        this.cliTelefone = cliTelefone;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public Usuario getCliUsuario() {
        return cliUsuario;
    }

    public void setCliUsuario(Usuario cliUsuario) {
        this.cliUsuario = cliUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.cliCod);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.cliCod, other.cliCod);
    }
}
