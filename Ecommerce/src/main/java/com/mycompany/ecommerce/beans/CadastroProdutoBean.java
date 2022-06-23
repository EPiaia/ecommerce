package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Linha;
import com.mycompany.ecommerce.domains.Marca;
import com.mycompany.ecommerce.domains.Produto;
import com.mycompany.ecommerce.domains.ProdutoxImagem;
import com.mycompany.ecommerce.domains.ProdutoxImagemPK;
import com.mycompany.ecommerce.services.LinhaService;
import com.mycompany.ecommerce.services.MarcaService;
import com.mycompany.ecommerce.services.ProdutoService;
import com.mycompany.ecommerce.services.ProdutoxImagemService;
import com.mycompany.ecommerce.utils.ImageUtil;
import com.mycompany.ecommerce.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Piaia
 */
@ViewScoped
@Named
public class CadastroProdutoBean implements Serializable {

    @EJB
    private ProdutoService ps;
    @EJB
    private MarcaService ms;
    @EJB
    private LinhaService ls;
    @EJB
    private ProdutoxImagemService pis;

    private Produto produto;

    private List<Produto> produtosDisponiveis = new ArrayList<>();
    private Produto filtroProduto = new Produto();
    private boolean filtroComDescProd = false;
    private Produto produtoSelecionado;

    private Marca marcaSelecionada;
    private Linha linhaSelecionada;

    @PostConstruct
    private void init() {
        criarNovo();
    }

    public void criarNovo() {
        this.produto = new Produto();
    }

    public boolean validaCampos() {
        if (produto == null) {
            JsfUtil.warn("Escolha ou crie um produto para salvar");
            return false;
        }
        if ((produto.getProDescDtIni() != null || produto.getProDescDtFin() != null) && produto.getProPerDesc() == null) {
            JsfUtil.warn("Insira um percentual para o desconto");
            return false;
        }
        return true;
    }

    public void salvar() {
        if (!validaCampos()) {
            return;
        }
        if (produto.getProPerDesc() != null && produto.getProDescDtIni() == null) {
            produto.setProDescDtIni(new Date());
        }
        this.produto = ps.save(this.produto);
        // percorrer imagens mudando o cod do produto na pk porque se for um novo produto só tem a
        JsfUtil.info("Registro salvo com sucesso");
    }

    public void deletar() {
        if (produto == null || produto.getProCod() == null) {
            JsfUtil.warn("Selecione um registro para deletar");
            return;
        }
        ps.delete(produto);
        JsfUtil.info("Registro deletado com sucesso");
        criarNovo();
    }

    public void abrirPesquisaProdutos() {
        this.filtroProduto = new Produto();
        this.filtroComDescProd = false;
        pesquisarProdutos();
        JsfUtil.pfShowDialog("wvBuscaProduto");
    }

    public void pesquisarProdutos() {
        Map<String, Object> filtros = new HashMap<>();
        if (filtroProduto.getProCod() != null) {
            filtros.put("proCod", filtroProduto.getProCod());
        } else {
            filtros.put("proDesc", filtroProduto.getProDesc());
            filtros.put("proMarca", filtroProduto.getProMarca());
            filtros.put("proLinha", filtroProduto.getProLinha());
            if (filtroComDescProd) {
                filtros.put("proDescData", new Date());
            }
        }
        produtosDisponiveis = ps.filtrar(filtros);
    }

    public void selecionarProduto() {
        if (produtoSelecionado == null) {
            JsfUtil.warn("Selecione um registro");
            return;
        }
        this.produto = produtoSelecionado;
        JsfUtil.pfHideDialog("wvBuscaProduto");
    }

    public void limparPesquisaProdutos() {
        this.filtroProduto = new Produto();
        filtroComDescProd = false;
    }

    public List<Marca> completeMarca(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Marca> marcas = ms.getMarcas();
        return marcas.stream().filter(t -> t.getMarDesc().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public List<Linha> completeLinha(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Linha> linhas = ls.getLinhas();
        return linhas.stream().filter(t -> t.getLinDesc().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (event.getFile() == null) {
            return;
        }
        if (produto == null || produto.getProCod() == null) {
            JsfUtil.warn("Para fazer o upload de uma imagem escolha ou salve o produto primeiro.");
            return;
        }
        ProdutoxImagem imagem = new ProdutoxImagem();
        imagem.setPxiImg(event.getFile().getContent());
        imagem.setProdutoxImagemPK(new ProdutoxImagemPK());
        imagem.getProdutoxImagemPK().setPxiProCod(produto.getProCod());
        imagem.setPxiOrdem(pis.getMaxOrdem(produto) + 1);
        this.produto.getFotosProduto().add(imagem);
    }

    public void removerImagem(ProdutoxImagem prodxImg) {
        this.produto.getFotosProduto().remove(prodxImg);
        ps.save(this.produto);
    }

    public List<ProdutoxImagem> getImagensDoProduto() {
        if (this.produto == null || this.produto.getFotosProduto().isEmpty()) {
            List<ProdutoxImagem> semFotoList = new ArrayList<>();
            ProdutoxImagem semFoto = new ProdutoxImagem();
            semFoto.setPxiImg(ImageUtil.getByteNoPhoto());
            semFotoList.add(semFoto);
            return semFotoList;
        } else {
            return this.produto.getFotosProduto();
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutosDisponiveis() {
        return produtosDisponiveis;
    }

    public void setProdutosDisponiveis(List<Produto> produtosDisponiveis) {
        this.produtosDisponiveis = produtosDisponiveis;
    }

    public Produto getFiltroProduto() {
        return filtroProduto;
    }

    public void setFiltroProduto(Produto filtroProduto) {
        this.filtroProduto = filtroProduto;
    }

    public boolean isFiltroComDescProd() {
        return filtroComDescProd;
    }

    public void setFiltroComDescProd(boolean filtroComDescProd) {
        this.filtroComDescProd = filtroComDescProd;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public Marca getMarcaSelecionada() {
        return marcaSelecionada;
    }

    public void setMarcaSelecionada(Marca marcaSelecionada) {
        this.marcaSelecionada = marcaSelecionada;
    }

    public Linha getLinhaSelecionada() {
        return linhaSelecionada;
    }

    public void setLinhaSelecionada(Linha linhaSelecionada) {
        this.linhaSelecionada = linhaSelecionada;
    }
}
