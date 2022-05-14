package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Linha;
import com.mycompany.ecommerce.services.LinhaService;
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
 * @author Piaia
 */
@ViewScoped
@Named
public class CadastroLinhaBean implements Serializable {

    @EJB
    private LinhaService ls;

    private Linha linha;
    private List<Linha> linhasDisponiveis = new ArrayList<>();
    private Linha filtroLinha = new Linha();
    private Linha linhaSelecionada;

    @PostConstruct
    private void init() {
        this.linha = new Linha();
    }

    public void abrirDialogCadastroLinha() {
        this.linha = new Linha();
        JsfUtil.pfShowDialog("wvLinha");
    }

    public void novaLinha() {
        this.linha = new Linha();
    }

    public boolean validaCampos() {
        if (linha.getLinDesc() == null) {
            JsfUtil.warn("O campo \"Descrição\" é obrigatório");
            return false;
        }
        return true;
    }

    public void salvar() {
        if (!validaCampos()) {
            return;
        }
        ls.save(linha);
        JsfUtil.info("Registro salvo com sucesso");
        novaLinha();
    }

    public void deletar() {
        if (linha == null || linha.getLinCod() == null) {
            JsfUtil.warn("Selecione um registro para deletar");
            return;
        }
        ls.delete(linha);
        JsfUtil.info("Registro deletado com sucesso");
        novaLinha();
    }

    public void abrirPesquisaLinha() {
        pesquisarLinhas();
        this.filtroLinha = new Linha();
        JsfUtil.pfShowDialog("wvBuscaLinha");
    }

    public void pesquisarLinhas() {
        Map<String, Object> filtros = new HashMap<>();
        if (filtroLinha.getLinCod() != null) {
            filtros.put("linCod", filtroLinha.getLinCod());
        } else {
            filtros.put("linDesc", filtroLinha.getLinDesc());
        }
        this.linhasDisponiveis = ls.filtrar(filtros);
    }

    public void selecionarLinha() {
        if (linhaSelecionada == null) {
            JsfUtil.warn("Selecione um registro");
            return;
        }
        this.linha = linhaSelecionada;
        JsfUtil.pfHideDialog("wvBuscaLinha");
    }

    public void limparPesquisaLinhas() {
        this.filtroLinha = new Linha();
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public List<Linha> getLinhasDisponiveis() {
        return linhasDisponiveis;
    }

    public void setLinhasDisponiveis(List<Linha> linhasDisponiveis) {
        this.linhasDisponiveis = linhasDisponiveis;
    }

    public Linha getFiltroLinha() {
        return filtroLinha;
    }

    public void setFiltroLinha(Linha filtroLinha) {
        this.filtroLinha = filtroLinha;
    }

    public Linha getLinhaSelecionada() {
        return linhaSelecionada;
    }

    public void setLinhaSelecionada(Linha linhaSelecionada) {
        this.linhaSelecionada = linhaSelecionada;
    }

}
