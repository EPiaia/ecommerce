package com.mycompany.ecommerce.services;

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
public class GeralService extends BaseService {

    public GeralService() {
    }

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        return new ArrayList<>();
    }

}
