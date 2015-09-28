
package com.proyecto.controllers;

import com.java.utilities.Formulario;
import com.proyecto.facades.JornadasFacade;
import com.proyecto.persistences.Jornadas;
import com.proyecto.persistences.Jornadas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;


@ManagedBean
@RequestScoped
public class JornadasController {
    
    @EJB
    private JornadasFacade jornadasFacade;

    private Jornadas _objJornadas;
    
    public JornadasController() {
    }
    
    public Jornadas getCampo()
    {
        if(_objJornadas==null)  _objJornadas= new Jornadas();
        return _objJornadas;        
    }
    
    public String agregar()
    {
        String titulo,detalle;
        
        try {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarOk");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("GrabarDetalleOk");
            Mensajes.exito(titulo, detalle);*/
            jornadasFacade.crear(_objJornadas);
            return "crear";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarError");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("GrabarDetalleError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Jornadas.class.getName()).log(Level.SEVERE,null,e);
            return "crear";
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(jornadasFacade.listado(), texto);
    }
    
    public List<Jornadas> getListado()
    {
        return jornadasFacade.listado();
    }
    
    public String redireccionar(String faces, Jornadas facesObj)
    {
        _objJornadas = facesObj;
        return faces;
    }
    
    public String borrar(Jornadas faceObj)
    {
        String titulo,detalle;
        
        try {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("BorrarOk");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("BorrarDetalleOk");
            Mensajes.exito(titulo, detalle);*/
            jornadasFacade.borrar(faceObj);
            return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarError");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("BorrarDetalleError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Jornadas.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }    
    
    public String actualizar()
    {
        String titulo,detalle;
        
        try {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("Actualizando");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("ActualizarOk");
            Mensajes.exito(titulo, detalle);*/
            jornadasFacade.actualizar(_objJornadas);
            return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("Actualizando");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("ActualizarError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Jornadas.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }  
    
    public void resetear()
    {
        _objJornadas = null;
    }
    
    @FacesConverter(forClass = Jornadas.class, value = "horasConverter")
    public static class JornadasControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                JornadasController controller = (JornadasController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "horasController");
                return controller.jornadasFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Jornadas.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Jornadas){
                Jornadas obj = (Jornadas) value;
                return String.valueOf(obj.getCodhora());
            }
            return null;
        }
    }
}
