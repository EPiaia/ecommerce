package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Produto;
import com.mycompany.ecommerce.services.ProdutoService;
import com.mycompany.ecommerce.utils.JsfUtil;
import com.mycompany.ecommerce.utils.StringUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Named
@ViewScoped
public class ProdutoBean implements Serializable {

    @EJB
    private ProdutoService ps;

    private Produto produto;
    private boolean linkValido = true;

    @PostConstruct
    private void init() {
        String txtPesquisa = JsfUtil.getParamFromUrl("produto");
        if (StringUtil.isNullOrEmpty(txtPesquisa) || !StringUtil.containsOnlyNumbers(txtPesquisa)) {
            JsfUtil.error("O link acessado é inválido");
            linkValido = false;
            return;
        }
        this.produto = ps.getEntityManager().find(Produto.class, Integer.valueOf(txtPesquisa));
        if (produto == null) {
            JsfUtil.error("O produto não pode ser encontrado");
            linkValido = false;
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean isLinkValido() {
        return linkValido;
    }

    public void setLinkValido(boolean linkValido) {
        this.linkValido = linkValido;
    }

}
