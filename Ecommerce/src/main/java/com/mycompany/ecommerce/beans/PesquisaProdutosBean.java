package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Linha;
import com.mycompany.ecommerce.domains.Marca;
import com.mycompany.ecommerce.domains.Produto;
import com.mycompany.ecommerce.services.LinhaService;
import com.mycompany.ecommerce.services.MarcaService;
import com.mycompany.ecommerce.services.ProdutoService;
import com.mycompany.ecommerce.utils.JsfUtil;
import com.mycompany.ecommerce.utils.RegraNegocioException;
import com.mycompany.ecommerce.utils.StringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class PesquisaProdutosBean implements Serializable {

    @EJB
    private ProdutoService ps;
    @EJB
    private LinhaService ls;
    @EJB
    private MarcaService ms;

    private List<Produto> produtos = new ArrayList<>();
    private boolean linkValido = true;
    private String descPesquisa;

    @PostConstruct
    private void init() {
        String txtDesc = JsfUtil.getParamFromUrl("desc");
        String txtLinha = JsfUtil.getParamFromUrl("linha");
        String txtMarca = JsfUtil.getParamFromUrl("marca");
        boolean isDesc = !StringUtil.isNullOrEmpty(txtDesc);
        boolean isLinha = !StringUtil.isNullOrEmpty(txtLinha) && StringUtil.containsOnlyNumbers(txtLinha);
        boolean isMarca = !StringUtil.isNullOrEmpty(txtMarca) && StringUtil.containsOnlyNumbers(txtMarca);
        Map<String, String> filtros = new HashMap<>();
        try {
            if (isDesc) {
                descPesquisa = txtDesc;
                filtros.put("desc", txtDesc);
            } else if (isLinha) {
                Linha linha = ls.getLinhaPorCodigo(Integer.parseInt(txtLinha));
                if (linha == null) {
                    throw new RegraNegocioException("A Linha pesquisada não existe");
                }
                descPesquisa = linha.getLinDesc();
            } else if (isMarca) {
                Marca marca = ms.getMarcaPorCodigo(Integer.parseInt(txtMarca));
                if (marca == null) {
                    throw new RegraNegocioException("A Marca pesquisada não existe");
                }
                descPesquisa = marca.getMarDesc();
            } else {
                throw new RegraNegocioException("Link inválido");
            }
            produtos = ps.pesquisarProdutos(filtros);
        } catch (RegraNegocioException e) {
            JsfUtil.error(e.getMessage());
            linkValido = false;
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isLinkValido() {
        return linkValido;
    }

    public void setLinkValido(boolean linkValido) {
        this.linkValido = linkValido;
    }

    public String getDescPesquisa() {
        return descPesquisa;
    }

    public void setDescPesquisa(String descPesquisa) {
        this.descPesquisa = descPesquisa;
    }
}
