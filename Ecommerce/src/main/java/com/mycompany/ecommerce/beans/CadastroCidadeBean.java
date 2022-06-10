package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Cidade;
import com.mycompany.ecommerce.domains.Estado;
import com.mycompany.ecommerce.services.CidadeService;
import com.mycompany.ecommerce.services.EstadoService;
import com.mycompany.ecommerce.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
public class CadastroCidadeBean implements Serializable {

    @EJB
    private CidadeService cs;
    @EJB
    private EstadoService es;

    private Cidade cidade;
    private Cidade filtroCidade = new Cidade();
    private List<Cidade> cidadesDisponiveis = new ArrayList<>();
    private Cidade cidadeSelecionada;
    private List<Estado> filtroEstados = new ArrayList<>();

    @PostConstruct
    private void init() {
        this.cidade = new Cidade();
    }

    public void novaCidade() {
        this.cidade = new Cidade();
    }

    public boolean validaCampos() {
        if (cidade.getCidDesc() == null) {
            JsfUtil.warn("O campo \"Descrição\" é obrigatório");
            return false;
        }
        return true;
    }

    public void salvar() {
        if (!validaCampos()) {
            return;
        }
        cs.save(cidade);
        JsfUtil.info("Registro salvo com sucesso");
        novaCidade();
    }

    public void deletar() {
        if (cidade == null || cidade.getCidCod() == null) {
            JsfUtil.warn("Selecione um registro para deletar");
            return;
        }
        cs.delete(cidade);
        JsfUtil.info("Registro deletado com sucesso");
        novaCidade();
    }

    public void abrirPesquisaCidade() {
        this.filtroCidade = new Cidade();
        pesquisarCidades();
        cidadesDisponiveis = cs.getCidades();
        JsfUtil.pfShowDialog("wvBuscaCidade");
    }

    public void pesquisarCidades() {
        Map<String, Object> filtros = new HashMap<>();
        if (filtroCidade.getCidCod() != null) {
            filtros.put("cidCod", filtroCidade.getCidCod());
        } else {
            filtros.put("cidDesc", filtroCidade.getCidDesc());
        }
        this.cidadesDisponiveis = cs.filtrar(filtros);
    }

    public void selecionarCidade() {
        if (cidadeSelecionada == null) {
            JsfUtil.warn("Selecione um registro");
            return;
        }
        this.cidade = cidadeSelecionada;
        JsfUtil.pfHideDialog("wvBuscaCidade");
    }

    public List<Estado> completeEstado(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Estado> estados = es.getEstados();
        return estados.stream()
                .filter(t -> t.getEstDesc().toLowerCase().contains(queryLowerCase) || t.getEstPais().getPaisDesc().toLowerCase().contains(queryLowerCase))
                .collect(Collectors.toList());
    }

    public void limparPesquisaCidades() {
        this.filtroCidade = new Cidade();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cidade getFiltroCidade() {
        return filtroCidade;
    }

    public void setFiltroCidade(Cidade filtroCidade) {
        this.filtroCidade = filtroCidade;
    }

    public List<Cidade> getCidadesDisponiveis() {
        return cidadesDisponiveis;
    }

    public void setCidadesDisponiveis(List<Cidade> cidadesDisponiveis) {
        this.cidadesDisponiveis = cidadesDisponiveis;
    }

    public Cidade getCidadeSelecionada() {
        return cidadeSelecionada;
    }

    public void setCidadeSelecionada(Cidade cidadeSelecionada) {
        this.cidadeSelecionada = cidadeSelecionada;
    }

    public List<Estado> getFiltroEstados() {
        return filtroEstados;
    }

    public void setFiltroEstados(List<Estado> filtroEstados) {
        this.filtroEstados = filtroEstados;
    }

}
