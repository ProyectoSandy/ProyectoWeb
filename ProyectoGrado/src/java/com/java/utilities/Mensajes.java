/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author sala14
 */
public class Mensajes 
{
    public static void error(String titulo, String contenido)
    {
        FacesMessage mensajito=new FacesMessage(FacesMessage.SEVERITY_ERROR,titulo,contenido);
        FacesContext.getCurrentInstance().addMessage(null, mensajito);
    }
    
    public static void exito(String titulo, String contenido)
    {
        FacesMessage mensajito=new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,contenido);
        FacesContext.getCurrentInstance().addMessage("SuccessInfo", mensajito);
    }
}
