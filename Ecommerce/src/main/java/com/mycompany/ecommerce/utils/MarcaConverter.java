package com.mycompany.ecommerce.utils;

import com.mycompany.ecommerce.domains.Marca;
import com.mycompany.ecommerce.services.MarcaService;
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
@FacesConverter(value = "marcaConverter", managed = true)
public class MarcaConverter implements Converter<Marca> {

    @EJB
    private MarcaService marcaService;

    @Override
    public Marca getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return marcaService.getMarcaPorCodigo(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é uma marca válida."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Marca value) {
        if (value != null) {
            return String.valueOf(value.getMarCod());
        } else {
            return null;
        }
    }
}
