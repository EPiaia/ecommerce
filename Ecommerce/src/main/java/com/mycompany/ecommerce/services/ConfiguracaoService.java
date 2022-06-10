package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Configuracao;
import com.mycompany.ecommerce.utils.FiltrosPesquisa;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Stateless
@Named
public class ConfiguracaoService extends BaseService<Configuracao> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        return fp;
    }

    public Configuracao getConfiguracao() {
        String query = "SELECT C.* FROM CONFIGURACAO C WHERE C.CFG_ID = " + 1 + " LIMIT 1";
        List<Configuracao> configs = super.executeNativeQuery(Configuracao.class, query);
        if (configs.isEmpty()) {
            Configuracao config = new Configuracao();
            config.setCfgId(1);
            super.save(config);
            return config;
        } else {
            return configs.get(0);
        }
    }
}
