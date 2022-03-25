package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.PaginaxAcesso;
import com.mycompany.ecommerce.domains.Usuario;
import com.mycompany.ecommerce.services.GeralService;
import com.mycompany.ecommerce.utils.JsfUtil;
import com.mycompany.ecommerce.utils.TipoAcessoEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author Piaia
 */
@Named
@SessionScoped
public class GeralBean implements Serializable {

    @EJB
    private GeralService gs;

    private Usuario usuarioLogado;
    private Map<String, List<TipoAcessoEnum>> paginasxPermissao = new HashMap<>();

    @PostConstruct
    private void init() {
        usuarioLogado = gs.getEntityManager().find(Usuario.class, "usuario_publico");
        configurarPaginasxPermissao();
    }

    // Chamado em todas as páginas para realizar controle do acesso às mesmas
    public void verificarPermissaoUsuario() {
        if (JsfUtil.getPaginaAtual() != null && JsfUtil.getPaginaAtual().equals("")) {
            return;
        }
        if (paginasxPermissao.containsKey(JsfUtil.getPaginaAtual())) {
            for (TipoAcessoEnum tipoAcesso : paginasxPermissao.get(JsfUtil.getPaginaAtual())) {
                if (tipoAcesso.equals(TipoAcessoEnum.getTipoAcessoPorCodigo(usuarioLogado.getUsrTpAcesso()))) {
                    return;
                }
            }
        }
        JsfUtil.redirect("/Ecommerce/index.xhtml");
    }

    private void configurarPaginasxPermissao() {
        List<PaginaxAcesso> paginasxAcessos = new ArrayList<>();
        String sql = "SELECT p FROM PaginaxAcesso p";
        Query query = gs.getEntityManager().createQuery(sql);
        paginasxAcessos = query.getResultList();
        for (PaginaxAcesso pxa : paginasxAcessos) {
            if (paginasxPermissao.containsKey(pxa.getPk().getPxaPagina())) {
                paginasxPermissao.get(pxa.getPk().getPxaPagina()).add(TipoAcessoEnum.getTipoAcessoPorCodigo(pxa.getPk().getPxaTpAcesso()));
            } else {
                List<TipoAcessoEnum> tipoAcessoList = new ArrayList<>();
                tipoAcessoList.add(TipoAcessoEnum.getTipoAcessoPorCodigo(pxa.getPk().getPxaTpAcesso()));
                paginasxPermissao.put(pxa.getPk().getPxaPagina(), tipoAcessoList);
            }
        }
    }

    public boolean isTipoAcessoUsuarioPublico() {
        return usuarioLogado.getUsrTpAcesso().equals(1);
    }

    public boolean isTipoAcessoCliente() {
        return usuarioLogado.getUsrTpAcesso().equals(2);
    }

    public boolean isTipoAcessoAdmin() {
        return usuarioLogado.getUsrTpAcesso().equals(3);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
