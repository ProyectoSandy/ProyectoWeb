
package com.proyecto.controllers;

import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Docentes;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@ManagedBean
@SessionScoped
public class LoginController implements Serializable{
    @EJB
    private DocentesFacade docentesFacade;

    
    
    private String _usuario;
    private String _clave;
    
    public LoginController() {
    }
    
    public String getUsuario() {
        return _usuario;
    }

    public void setUsuario(String _usuario) {
        this._usuario = _usuario;
    }

    public String getClave() {
        return _clave;
    }

    public void setClave(String _clave) {
        this._clave = _clave;
    }
    
    public String login(){
        
        String titulo="", detalle="";
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try 
        {         
            System.out.println("REQUEST: " + request);
            request.login(_usuario, _clave);
            //titulo=ResourceBundle.getBundle("/com/empresariales/utilities/txtLogin").getString("bienvenidos");
            //Mensajes.exito(titulo, detalle);
            
            System.out.println("CLAVE: " + _clave);
            System.out.println("USUARIO: " + _usuario);
            
            
            if(request.isUserInRole("administrador"))
            {   
                System.out.println("ADMINISTRADOR");           
                return "indexAdmin?faces-redirect=true";
            }else if(request.isUserInRole("docente"))
            {
                System.out.println("DOCENTES");
                docentesFacade.setCurrentDocente(buscarDocente());
                return "indexDocentes?faces-redirect=true";
            }else if(request.isUserInRole("evaluador"))
            {
                System.out.println("EVALUADOR");
                return "indexEvaluador?faces-redirect=true";
            }
            
            return "index";
        } catch (ServletException e)
        {          
            //titulo=ResourceBundle.getBundle("/com/empresariales/utilities/txtLogin").getString("errorEntrada");
            //detalle=ResourceBundle.getBundle("/com/empresariales/utilities/txtLogin").getString("msjErrorEntrada");
            //Mensajes.error(titulo, detalle);
            System.err.println("ERROR "+e);

            return "index";
        }
    }
    
    public String getHash(String clave) 
    {            
        try
        {           
            MessageDigest md = MessageDigest.getInstance("MD5");
            String text = "admin";
            md.update(text.getBytes("UTF-8")); // Change this to 'UTF-16' if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) 
        {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return clave;
    }
    
    public String logout() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) 
            context.getExternalContext().getRequest();
        try {
          request.logout();
          return "/faces/index?faces-redirect=true";
        } catch (ServletException e) {
          context.addMessage(null, new FacesMessage("Logout failed."));
          return "#";
        }
      }
    
    public void resetear()
    {
        _clave="";
        _usuario="";
    }
    
    public Docentes buscarDocente(){
        Docentes doc = docentesFacade.buscar(Integer.parseInt(_usuario));
        System.out.println("BUSCAR DOCENTE "+doc.getNombres());
        return doc;
        
    }
}
