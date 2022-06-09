package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Estado;
import com.mycompany.ecommerce.domains.Pais;
import com.mycompany.ecommerce.services.EstadoService;
import com.mycompany.ecommerce.services.PaisService;
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
public class CadastroEstadoBean implements Serializable {

    @EJB
    private EstadoService es;
    @EJB
    private PaisService ps;

    private Estado estado;
    private Estado filtroEstado = new Estado();
    private List<Estado> estadosDisponiveis = new ArrayList<>();
    private Estado estadoSelecionado;
    private List<Pais> filtroPaises = new ArrayList<>();

    @PostConstruct
    private void init() {
        this.estado = new Estado();
    }

    public void novoEstado() {
        this.estado = new Estado();
    }

    public boolean validaCampos() {
        if (estado.getEstDesc() == null) {
            JsfUtil.warn("O campo \"Descrição\" é obrigatório");
            return false;
        }
        return true;
    }

    public void salvar() {
        if (!validaCampos()) {
            return;
        }
        es.save(estado);
        JsfUtil.info("Registro salvo com sucesso");
        novoEstado();
    }

    public void deletar() {
        if (estado == null || estado.getEstCod() == null) {
            JsfUtil.warn("Selecione um registro para deletar");
            return;
        }
        es.delete(estado);
        JsfUtil.info("Registro deletado com sucesso");
        novoEstado();
    }

    public void abrirPesquisaEstado() {
        this.filtroEstado = new Estado();
        pesquisarEstados();
        filtroPaises = ps.getPaises();
        JsfUtil.pfShowDialog("wvBuscaEstado");
    }

    public void pesquisarEstados() {
        Map<String, Object> filtros = new HashMap<>();
        if (filtroEstado.getEstCod() != null) {
            filtros.put("estCod", filtroEstado.getEstCod());
        } else {
            filtros.put("estDesc", filtroEstado.getEstDesc());
            filtros.put("estPais", filtroEstado.getEstPais());
        }
        this.estadosDisponiveis = es.filtrar(filtros);
    }

    public void selecionarEstado() {
        if (estadoSelecionado == null) {
            JsfUtil.warn("Selecione um registro");
            return;
        }
        this.estado = estadoSelecionado;
        JsfUtil.pfHideDialog("wvBuscaEstado");
    }

    public List<Pais> completePais(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Pais> paises = ps.getPaises();
        return paises.stream().filter(t -> t.getPaisDesc().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void limparPesquisaEstados() {
        this.filtroEstado = new Estado();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getFiltroEstado() {
        return filtroEstado;
    }

    public void setFiltroEstado(Estado filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

    public List<Estado> getEstadosDisponiveis() {
        return estadosDisponiveis;
    }

    public void setEstadosDisponiveis(List<Estado> estadosDisponiveis) {
        this.estadosDisponiveis = estadosDisponiveis;
    }

    public Estado getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(Estado estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

    public List<Pais> getFiltroPaises() {
        return filtroPaises;
    }

    public void setFiltroPaises(List<Pais> filtroPaises) {
        this.filtroPaises = filtroPaises;
    }

}
