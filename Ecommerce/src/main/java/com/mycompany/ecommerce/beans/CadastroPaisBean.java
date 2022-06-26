package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Pais;
import com.mycompany.ecommerce.services.EstadoService;
import com.mycompany.ecommerce.services.PaisService;
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
public class CadastroPaisBean implements Serializable {

    @EJB
    private PaisService ps;
    @EJB
    private EstadoService es;

    private Pais pais;
    private Pais filtroPais;
    private List<Pais> paisesDisponiveis = new ArrayList<>();
    private Pais paisSelecionado;

    @PostConstruct
    private void init() {
        this.pais = new Pais();
    }

    public void novoPais() {
        this.pais = new Pais();
    }

    public boolean validaCampos() {
        if (pais.getPaisDesc() == null) {
            JsfUtil.warn("O campo \"Descrição\" é obrigatório");
            return false;
        }
        return true;
    }

    public void salvar() {
        if (!validaCampos()) {
            return;
        }
        ps.save(pais);
        JsfUtil.info("Registro salvo com sucesso");
        novoPais();
    }

    public void deletar() {
        if (pais == null || pais.getPaisCod() == null) {
            JsfUtil.warn("Selecione um registro para deletar");
            return;
        }
        if (es.isExisteEstadoDoPais(pais)) {
            JsfUtil.warn("Este país não pode ser deletado porque está sendo referenciado por um estado");
            return;
        }
        ps.delete(pais);
        JsfUtil.info("Registro deletado com sucesso");
        novoPais();
    }

    public void abrirPesquisaPais() {
        this.filtroPais = new Pais();
        pesquisarPaises();
        JsfUtil.pfShowDialog("wvBuscaPais");
    }

    public void pesquisarPaises() {
        Map<String, Object> filtros = new HashMap<>();
        if (filtroPais.getPaisCod() != null) {
            filtros.put("paisCod", filtroPais.getPaisCod());
        } else {
            filtros.put("paisDesc", filtroPais.getPaisDesc());
        }
        this.paisesDisponiveis = ps.filtrar(filtros);
    }

    public void selecionarPais() {
        if (paisSelecionado == null) {
            JsfUtil.warn("Selecione um registro");
            return;
        }
        this.pais = paisSelecionado;
        JsfUtil.pfHideDialog("wvBuscaPais");
    }

    public void limparPesquisaPaises() {
        this.filtroPais = new Pais();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Pais getFiltroPais() {
        return filtroPais;
    }

    public void setFiltroPais(Pais filtroPais) {
        this.filtroPais = filtroPais;
    }

    public List<Pais> getPaisesDisponiveis() {
        return paisesDisponiveis;
    }

    public void setPaisesDisponiveis(List<Pais> paisesDisponiveis) {
        this.paisesDisponiveis = paisesDisponiveis;
    }

    public Pais getPaisSelecionado() {
        return paisSelecionado;
    }

    public void setPaisSelecionado(Pais paisSelecionado) {
        this.paisSelecionado = paisSelecionado;
    }

}
