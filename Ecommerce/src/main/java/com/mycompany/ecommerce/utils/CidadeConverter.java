package com.mycompany.ecommerce.utils;

import com.mycompany.ecommerce.domains.Cidade;
import com.mycompany.ecommerce.services.CidadeService;
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
@FacesConverter(value = "cidadeConverter", managed = true)
public class CidadeConverter implements Converter<Cidade> {

    @EJB
    private CidadeService cidadeService;

    @Override
    public Cidade getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return cidadeService.getCidadePorCodigo(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é uma cidade válido."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Cidade value) {
        if (value != null) {
            return String.valueOf(value.getCidCod());
        } else {
            return null;
        }
    }
}
