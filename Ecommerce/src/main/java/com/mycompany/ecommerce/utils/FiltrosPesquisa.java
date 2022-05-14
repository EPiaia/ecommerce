package com.mycompany.ecommerce.utils;

/**
 *
 * @author Piaia
 */
public class FiltrosPesquisa {

    private String expressao;
    private String campo;
    private Object valor;

    public FiltrosPesquisa() {
    }

    public FiltrosPesquisa(String expressao, String campo, Object valor) {
        this.expressao = expressao;
        this.campo = campo;
        this.valor = valor;
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String getCampoJpql() {
        return "?" + campo;
    }

    public void add(String expressao, String campo, Object valor) {
        if (valor != null) {
            this.expressao = expressao;
            this.campo = campo;
            this.valor = valor;
        }
    }

}
