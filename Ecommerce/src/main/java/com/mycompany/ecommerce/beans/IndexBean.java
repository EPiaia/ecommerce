package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Produto;
import com.mycompany.ecommerce.services.ProdutoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class IndexBean implements Serializable {

    @EJB
    private ProdutoService ps;

    private List<Produto> produtos = new ArrayList<>();

    @PostConstruct
    private void init() {
        produtos = ps.getProdutosDisponiveis();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
