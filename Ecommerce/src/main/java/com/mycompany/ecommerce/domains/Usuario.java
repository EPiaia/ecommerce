package com.mycompany.ecommerce.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @NotNull
    @Column(name = "USR_LOGIN")
    private String usrLogin;
    @NotNull
    @Column(name = "USR_SENHA")
    private String usrSenha;
    @NotNull
    @Column(name = "USR_DESC")
    private String usrDesc;
    @Column(name = "USR_EMAIL")
    private String usrEmail;
    @NotNull
    @Column(name = "USR_TPACESSO")
    private Integer usrTpAcesso; // 1 - Usuário Publico     2 - Cliente     3 - Admin

    public Usuario() {
    }

    public String getUsrLogin() {
        return usrLogin;
    }

    public void setUsrLogin(String usrLogin) {
        this.usrLogin = usrLogin;
    }

    public String getUsrSenha() {
        return usrSenha;
    }

    public void setUsrSenha(String usrSenha) {
        this.usrSenha = usrSenha;
    }

    public String getUsrDesc() {
        return usrDesc;
    }

    public void setUsrDesc(String usrDesc) {
        this.usrDesc = usrDesc;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public Integer getUsrTpAcesso() {
        return usrTpAcesso;
    }

    public void setUsrTpAcesso(Integer usrTpAcesso) {
        this.usrTpAcesso = usrTpAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.usrLogin);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.usrLogin, other.usrLogin);
    }
}
