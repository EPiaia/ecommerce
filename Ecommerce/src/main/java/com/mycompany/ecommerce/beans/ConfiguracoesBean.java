package com.mycompany.ecommerce.beans;

import com.mycompany.ecommerce.domains.Configuracao;
import com.mycompany.ecommerce.services.ConfiguracaoService;
import com.mycompany.ecommerce.utils.JsfUtil;
import java.io.Serializable;
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
public class ConfiguracoesBean implements Serializable {

    @EJB
    private ConfiguracaoService cs;

    private Configuracao configuracao;

    @PostConstruct
    private void init() {
        this.configuracao = cs.getConfiguracao();
    }

    public void salvar() {
        cs.save(configuracao);
        JsfUtil.info("Registro salvo com sucesso");
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }
}
