package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.FormaPag;
import com.mycompany.ecommerce.services.FormaPagService;
import com.mycompany.ecommerce.services.PedidoService;
import com.mycompany.ecommerce.utils.JsfUtil;
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
 * @author Eduardo
 */
@Named
@ViewScoped
public class CadastroFormaPagBean implements Serializable {

    @EJB
    private FormaPagService fps;
    @EJB
    private PedidoService ps;

    private FormaPag formaPag;
    private FormaPag filtroFormaPag = new FormaPag();
    private List<FormaPag> formasDisponiveis = new ArrayList<>();
    private FormaPag formaSelecionada;

    @PostConstruct
    private void init() {
        this.formaPag = new FormaPag();
    }

    public void novaFormaPag() {
        this.formaPag = new FormaPag();
    }

    public boolean validaCampos() {
        if (formaPag.getFopDesc() == null) {
            JsfUtil.warn("O campo \"Descrição\" é obrigatório");
            return false;
        }
        return true;
    }

    public void salvar() {
        if (!validaCampos()) {
            return;
        }
        fps.save(formaPag);
        JsfUtil.info("Registro salvo com sucesso");
        novaFormaPag();
    }

    public void deletar() {
        if (formaPag == null || formaPag.getFopCod() == null) {
            JsfUtil.warn("Selecione um registro para deletar");
            return;
        }
        if (ps.isExistePedidoComFormaPag(formaPag)) {
            JsfUtil.warn("Esta forma de pagamento não pode ser deletada porque um pedido faz referência a ela");
            return;
        }
        fps.delete(formaPag);
        JsfUtil.info("Registro deletado com sucesso");
        novaFormaPag();
    }

    public void abrirPesquisaFormaPag() {
        this.filtroFormaPag = new FormaPag();
        pesquisarFormasDePagamento();
        formasDisponiveis = fps.getFormasDePagamento();
        JsfUtil.pfShowDialog("wvBuscaFormaPag");
    }

    public void pesquisarFormasDePagamento() {
        Map<String, Object> filtros = new HashMap<>();
        if (filtroFormaPag.getFopCod() != null) {
            filtros.put("fopCod", filtroFormaPag.getFopCod());
        } else {
            filtros.put("fopDesc", filtroFormaPag.getFopDesc());
        }
        this.formasDisponiveis = fps.filtrar(filtros);
    }

    public void selecionarFormaPag() {
        if (formaSelecionada == null) {
            JsfUtil.warn("Selecione um registro");
            return;
        }
        this.formaPag = formaSelecionada;
        JsfUtil.pfHideDialog("wvBuscaFormaPag");
    }

    public void limparPesquisaFormaPag() {
        this.filtroFormaPag = new FormaPag();
    }

    public FormaPag getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(FormaPag formaPag) {
        this.formaPag = formaPag;
    }

    public FormaPag getFiltroFormaPag() {
        return filtroFormaPag;
    }

    public void setFiltroFormaPag(FormaPag filtroFormaPag) {
        this.filtroFormaPag = filtroFormaPag;
    }

    public List<FormaPag> getFormasDisponiveis() {
        return formasDisponiveis;
    }

    public void setFormasDisponiveis(List<FormaPag> formasDisponiveis) {
        this.formasDisponiveis = formasDisponiveis;
    }

    public FormaPag getFormaSelecionada() {
        return formaSelecionada;
    }

    public void setFormaSelecionada(FormaPag formaSelecionada) {
        this.formaSelecionada = formaSelecionada;
    }
}
