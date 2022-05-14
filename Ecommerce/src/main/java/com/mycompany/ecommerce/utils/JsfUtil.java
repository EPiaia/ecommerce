package com.mycompany.ecommerce.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Piaia
 */
public class JsfUtil implements Serializable {

    private static PrimeFaces pf = PrimeFaces.current();

    public static Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

    public static void info(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }

    public static void warn(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
    }

    public static void error(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    public static boolean isPostback() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    public static boolean isPage(String page) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRequestURI().contains(page);
    }

    public static boolean isPageIndex() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getRequestURI().equals("/Ecommerce/") || request.getRequestURI().equals("/Ecommerce/index.xhtml");
    }

    public static String getPaginaAtual() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String uri = request.getRequestURI().replace("/Ecommerce", "");
        if (uri.isEmpty() || uri.equals("/")) {
            return "index";
        }
        return uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".xhtml"));
    }

    public static void redirect(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pfShowDialog(String widgetVar) {
        pf.executeScript("PF('" + widgetVar + "').show();");
    }

    public static void pfHideDialog(String widgetVar) {
        pf.executeScript("PF('" + widgetVar + "').hide();");
    }

}
