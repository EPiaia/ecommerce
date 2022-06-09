package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Linha;
import com.mycompany.ecommerce.services.LinhaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Eduar
 */
@SessionScoped
@Named
public class MenuBean implements Serializable {

    @EJB
    private LinhaService linhaService;

    @Inject
    private GeralBean geralBean;

    private MenuModel menu = new DefaultMenuModel();

    private MenuModel menuCarrinho = new DefaultMenuModel();

    @PostConstruct
    public void init() {
        montarMenu();
    }

    public void montarMenu() {
        this.menu = new DefaultMenuModel();
        if (geralBean.isTipoAcessoAdmin()) {
            List<DefaultMenuItem> itensSubMenu = new ArrayList<>();
            itensSubMenu.add(criarMenuItem("Configurações", "/Ecommerce/restrito/configuracoes.xhtml", "pi pi-cog"));
            addElements(null, itensSubMenu);
            itensSubMenu.clear();
            DefaultSubMenu subMenuCadastros = criarSubMenu("Cadastros", "pi pi-plus");
            itensSubMenu.add(criarMenuItem("Produtos", "/Ecommerce/restrito/cadastros/cadastro_produto.xhtml"));
            itensSubMenu.add(criarMenuItem("Marcas", "/Ecommerce/restrito/cadastros/cadastro_marca.xhtml"));
            itensSubMenu.add(criarMenuItem("Linhas", "/Ecommerce/restrito/cadastros/cadastro_linha.xhtml"));
            itensSubMenu.add(criarMenuItem("Países", "/Ecommerce/restrito/cadastros/cadastro_pais.xhtml"));
            itensSubMenu.add(criarMenuItem("Estados", "/Ecommerce/restrito/cadastros/cadastro_estado.xhtml"));
            itensSubMenu.add(criarMenuItem("Cidades", "/Ecommerce/restrito/cadastros/cadastro_cidade.xhtml"));
            itensSubMenu.add(criarMenuItem("Formas de Pagamento", "/Ecommerce/restrito/cadastros/cadastro_formapag.xhtml"));
            addElements(subMenuCadastros, itensSubMenu);
        } else if (geralBean.isTipoAcessoCliente() || geralBean.isTipoAcessoUsuarioPublico()) {
            List<Linha> linhas = linhaService.getLinhas();
            List<DefaultMenuItem> linhasMenu = new ArrayList<>();
            int quantLinhas = 0;
            for (Linha linha : linhas) {
                if (quantLinhas < 6) {
                    linhasMenu.add(criarMenuItem(linha.getLinDesc(), "/Ecommerce/produtos.xhtml?linha=" + linha.getLinCod()));
                    quantLinhas++;
                } else {
                    break;
                }
            }
            addElements(null, linhasMenu);
            if (linhas.size() > 5) {
                DefaultSubMenu subMenuTodos = criarSubMenu("Todas as Linhas", "");
                linhasMenu.clear();
                for (Linha linha : linhas) {
                    linhasMenu.add(criarMenuItem(linha.getLinDesc(), "/Ecommerce/produtos.xhtml?linha=" + linha.getLinCod()));
                }
                addElements(subMenuTodos, linhasMenu);
            }
        }
    }

    private DefaultSubMenu criarSubMenu(String label, String icon) {
        DefaultSubMenu subMenu = new DefaultSubMenu();
        subMenu.setLabel(label);
        subMenu.setIcon(icon);
        return subMenu;
    }

    private DefaultMenuItem criarMenuItem(String label, String url) {
        DefaultMenuItem menuItem = new DefaultMenuItem();
        menuItem.setValue(label);
        menuItem.setUrl(url);
        return menuItem;
    }

    private DefaultMenuItem criarMenuItem(String label, String url, String icon) {
        DefaultMenuItem menuItem = new DefaultMenuItem();
        menuItem.setValue(label);
        menuItem.setUrl(url);
        menuItem.setIcon(icon);
        return menuItem;
    }

    private void addElements(DefaultSubMenu subMenu, List<DefaultMenuItem> itens) {
        if (subMenu == null) { //Os itens ficam direto no menu
            this.menu.getElements().addAll(itens);
        } else {
            subMenu.getElements().addAll(itens);
            this.menu.getElements().add(subMenu);
        }
    }

    public void montarMenuCarrinho() {
        DefaultMenuItem menuItem = new DefaultMenuItem();
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public MenuModel getMenuCarrinho() {
        return menuCarrinho;
    }

    public void setMenuCarrinho(MenuModel menuCarrinho) {
        this.menuCarrinho = menuCarrinho;
    }

}
