/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Carrinho;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Eduar
 */
@Named
@SessionScoped
public class CarrinhoBean implements Serializable {

    private Carrinho carrinho;

}
