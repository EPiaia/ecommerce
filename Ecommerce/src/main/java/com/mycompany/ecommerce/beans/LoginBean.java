package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Usuario;
import com.mycompany.ecommerce.services.GeralService;
import com.mycompany.ecommerce.utils.JsfUtil;
import com.mycompany.ecommerce.utils.TipoAcessoEnum;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Piaia
 */
@ViewScoped
@Named
public class LoginBean implements Serializable {

    @EJB
    private GeralService gs;

    @Inject
    private GeralBean gerBean;

    private TipoAcessoEnum tipoAcesso = TipoAcessoEnum.ADMIN;
    private String inputLogin;
    private String inputSenha;

    public void autenticar() throws IOException {
        if (tipoAcesso == null || inputLogin == null || "".equals(inputLogin.trim()) || inputSenha == null || "".equals(inputSenha.trim())) {
            JsfUtil.error("Os campos \"Tipo de Acesso\", \"Usuário\" e \"Senha\" são obrigatórios");
            return;
        }

        String sql = String.format("SELECT u FROM Usuario u WHERE u.usrLogin = '%s' AND u.usrSenha = '%s' AND u.usrTpAcesso = %d", inputLogin, inputSenha, tipoAcesso.getTipoAcessoCodigo());

        Query query = gs.getEntityManager().createQuery(sql);
        List<Usuario> usuarios = new ArrayList<>(query.getResultList());
        if (usuarios.size() == 1) {
            gerBean.setUsuarioLogado(usuarios.get(0));
            JsfUtil.getSessionMap().put("user", gerBean.getUsuarioLogado());
            JsfUtil.redirect("/Ecommerce/index.xhtml");
        } else if (usuarios.size() < 1) {
            JsfUtil.error("Usuário não encontrado com Usuário e Senhas informados");
        }
    }

    public void sair() {
        gerBean.setUsuarioLogado(gs.getEntityManager().find(Usuario.class, "usuario_publico"));
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public List<TipoAcessoEnum> getTiposAcessoLogin() {
        return TipoAcessoEnum.getTiposDisponiveisLogin();
    }

    public TipoAcessoEnum getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcessoEnum tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public String getInputLogin() {
        return inputLogin;
    }

    public void setInputLogin(String inputLogin) {
        this.inputLogin = inputLogin;
    }

    public String getInputSenha() {
        return inputSenha;
    }

    public void setInputSenha(String inputSenha) {
        this.inputSenha = inputSenha;
    }

}
