/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Carrinho;
import com.mycompany.ecommerce.domains.Item;
import com.mycompany.ecommerce.domains.Produto;
import com.mycompany.ecommerce.utils.JsfUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Eduar
 */
@Named
@SessionScoped
public class CarrinhoBean implements Serializable {

    @Inject
    private GeralBean geralBean;

    private Carrinho carrinho = new Carrinho();

    public void adicionarProdutoAoCarrinho(Produto produto) {
        if (geralBean.isTipoAcessoUsuarioPublico()) {
            JsfUtil.redirect("/Ecommerce/login.xhtml");
        } else if (geralBean.isTipoAcessoCliente()) {
            Item item = new Item(produto);
            this.carrinho.getItens().add(item);
            JsfUtil.info("Produto adicionado ao carrinho com sucesso");
        }
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

}
