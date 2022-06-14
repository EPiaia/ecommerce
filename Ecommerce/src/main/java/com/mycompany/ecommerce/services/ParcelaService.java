package com.mycompany.ecommerce.services;

import com.mycompany.ecommerce.domains.Parcela;
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
public class ParcelaService extends BaseService<Parcela> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> fp = new ArrayList<>();
        return fp;
    }

}
