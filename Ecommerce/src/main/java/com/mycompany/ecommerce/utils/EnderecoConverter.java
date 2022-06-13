package com.mycompany.ecommerce.utils;

import com.mycompany.ecommerce.domains.Endereco;
import com.mycompany.ecommerce.services.EnderecoService;
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
@FacesConverter(value = "enderecoConverter", managed = true)
public class EnderecoConverter implements Converter<Endereco>, Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EnderecoService enderecoService;

    @Override
    public Endereco getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return enderecoService.getEnderecoPorCodigo(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um endereço válido."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Endereco value) {
        if (value != null) {
            return String.valueOf(value.getEndCod());
        } else {
            return null;
        }
    }
}
