package com.mycompany.ecommerce.utils;

import com.mycompany.ecommerce.domains.Marca;
import com.mycompany.ecommerce.domains.Pais;
import com.mycompany.ecommerce.services.PaisService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Named
@FacesConverter(value = "paisConverter", managed = true)
public class PaisConverter implements Converter<Pais> {

    @Inject
    private PaisService paisService;

    @Override
    public Pais getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return paisService.getPaisPorCodigo(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um país válido."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Pais value) {
        if (value != null) {
            return String.valueOf(value.getPaisCod());
        } else {
            return null;
        }
    }
}
