package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Marca;
import com.mycompany.ecommerce.services.MarcaService;
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
 * @author Piaia
 */
@ViewScoped
@Named
public class CadastroMarcaBean implements Serializable {

    @EJB
    private MarcaService ms;

    private Marca marca;
    private List<Marca> marcasDisponiveis = new ArrayList<>();
    private Marca filtroMarca = new Marca();
    private Marca marcaSelecionada;

    @PostConstruct
    private void init() {
        this.marca = new Marca();
    }

    public List<Marca> completeMarca(String query) {
        String queryLC = query.toLowerCase();
        List<Marca> marcas = ms.getMarcas();
        return marcas.stream().filter(t -> t.getMarDesc().toLowerCase().contains(queryLC)).collect(Collectors.toList());
    }

    public void novaMarca() {
        this.marca = new Marca();
    }

    public boolean validaCampos() {
        if (marca.getMarDesc() == null) {
            JsfUtil.warn("O campo \"Descrição\" é obrigatório");
            return false;
        }
        return true;
    }

    public void salvar() {
        if (!validaCampos()) {
            return;
        }
        ms.save(marca);
        JsfUtil.info("Registro salvo com sucesso");
        novaMarca();
    }

    public void deletar() {
        if (marca == null || marca.getMarCod() == null) {
            JsfUtil.warn("Selecione um registro para deletar");
            return;
        }
        ms.delete(marca);
        JsfUtil.info("Registro deletado com sucesso");
        novaMarca();
    }

    public void abrirPesquisaMarca() {
        this.filtroMarca = new Marca();
        pesquisarMarcas();
        JsfUtil.pfShowDialog("wvBuscaMarca");
    }

    public void pesquisarMarcas() {
        Map<String, Object> filtros = new HashMap<>();
        if (filtroMarca.getMarCod() != null) {
            filtros.put("marCod", filtroMarca.getMarCod());
        } else {
            filtros.put("marDesc", filtroMarca.getMarDesc());
        }
        this.marcasDisponiveis = ms.filtrar(filtros);
    }

    public void selecionarMarca() {
        if (marcaSelecionada == null) {
            JsfUtil.warn("Selecione um registro");
            return;
        }
        this.marca = marcaSelecionada;
        JsfUtil.pfHideDialog("wvBuscaMarca");
    }

    public void limparPesquisaMarcas() {
        this.filtroMarca = new Marca();
    }

    public Marca getMarca() {
        return marca;
    }

    public List<Marca> getMarcasDisponiveis() {
        return marcasDisponiveis;
    }

    public void setMarcasDisponiveis(List<Marca> marcasDisponiveis) {
        this.marcasDisponiveis = marcasDisponiveis;
    }

    public Marca getFiltroMarca() {
        return filtroMarca;
    }

    public void setFiltroMarca(Marca filtroMarca) {
        this.filtroMarca = filtroMarca;
    }

    public Marca getMarcaSelecionada() {
        return marcaSelecionada;
    }

    public void setMarcaSelecionada(Marca marcaSelecionada) {
        this.marcaSelecionada = marcaSelecionada;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
