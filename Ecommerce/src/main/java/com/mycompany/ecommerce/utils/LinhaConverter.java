package com.mycompany.ecommerce.utils;

import com.mycompany.ecommerce.domains.Linha;
import com.mycompany.ecommerce.services.LinhaService;
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
@FacesConverter(value = "linhaConverter", managed = true)
public class LinhaConverter implements Converter<Linha> {

    @EJB
    private LinhaService linhaService;

    @Override
    public Linha getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return linhaService.getLinhaPorCodigo(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é uma linha válida."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Linha value) {
        if (value != null) {
            return String.valueOf(value.getLinCod());
        } else {
            return null;
        }
    }
}
