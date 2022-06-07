/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce.domains;

import com.mycompany.ecommerce.utils.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Eduar
 */
public class Carrinho implements Serializable {

    private List<Item> itens = new ArrayList<>();

    public Carrinho() {
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public boolean isProdutoEstaNoCarrinho(Produto produto) {
        for (Item item : getItens()) {
            if (item.getProduto().equals(produto)) {
                return true;
            }
        }
        return false;
    }

    public void removerItem(Item item) {
        this.itens.remove(item);
        JsfUtil.info("Produto removido com sucesso");
    }

    public void aumentarQuantidade(Item item) {
        for (Item itemCarrinho : itens) {
            if (itemCarrinho.equals(item)) {
                itemCarrinho.setQuantidade(itemCarrinho.getQuantidade().add(BigDecimal.ONE));
            }
        }
    }

    public void diminuirQuantidade(Item item) {
        for (Item itemCarrinho : itens) {
            if (itemCarrinho.equals(item)) {
                if (itemCarrinho.getQuantidade().compareTo(BigDecimal.ONE) == 0) {
                    JsfUtil.warn("A quantidade mínima é 1");
                } else {
                    itemCarrinho.setQuantidade(itemCarrinho.getQuantidade().subtract(BigDecimal.ONE));
                }
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.itens);
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
        final Carrinho other = (Carrinho) obj;
        return Objects.equals(this.itens, other.itens);
    }
}
