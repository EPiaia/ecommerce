package com.mycompany.ecommerce.utils;

import com.mycompany.ecommerce.domains.Estado;
import com.mycompany.ecommerce.services.EstadoService;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Named
@FacesConverter(value = "estadoConverter", managed = true)
public class EstadoConverter implements Converter<Estado> {

    @EJB
    private EstadoService estadoService;

    @Override
    public Estado getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return estadoService.getEstadoPorCodigo(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um estado válido."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Estado value) {
        if (value != null) {
            return String.valueOf(value.getEstCod());
        } else {
            return null;
        }
    }
}
