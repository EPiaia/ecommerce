package com.mycompany.ecommerce.utils;

import com.mycompany.ecommerce.domains.FormaPag;
import com.mycompany.ecommerce.services.FormaPagService;
import java.io.Serializable;
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
@FacesConverter(value = "formaPagConverter", managed = true)
public class FormaPagConverter implements Converter<FormaPag>, Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private FormaPagService formaPagService;

    @Override
    public FormaPag getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return formaPagService.getFormaPagPorCodigo(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um endereço válido."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, FormaPag value) {
        if (value != null) {
            return String.valueOf(value.getFopCod());
        } else {
            return null;
        }
    }
}
