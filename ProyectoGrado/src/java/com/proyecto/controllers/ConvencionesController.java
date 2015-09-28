
package com.proyecto.controllers;

import com.java.utilities.Formulario;
import com.proyecto.facades.ConvencionesFacade;
import com.proyecto.persistences.Convenciones;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;


@ManagedBean
@SessionScoped
public class ConvencionesController implements Serializable{

    @EJB
    private ConvencionesFacade _ejbFacade;
    private Convenciones _obj;
    
    public ConvencionesController() {
    }
    
    public Convenciones getCampo()
    {
        if(_obj==null)  _obj= new Convenciones();
        return _obj;        
    }
    
    public String agregar()
    {
        String titulo,detalle;
        
        try {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarOk");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("GrabarDetalleOk");
            Mensajes.exito(titulo, detalle);*/
            System.out.println("Docente: " + _obj);
            _ejbFacade.crear(_obj);
            return "crear";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarError");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("GrabarDetalleError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE,null,e);
            return "crear";
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    
    public List<Convenciones> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public String redireccionar(String faces, Convenciones facesObj)
    {
        _obj = facesObj;
        return faces;
    }
    
    public String borrar(Convenciones faceObj)
    {
        String titulo,detalle;
        
        try {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("BorrarOk");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("BorrarDetalleOk");
            Mensajes.exito(titulo, detalle);*/
            _ejbFacade.borrar(faceObj);
            return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarError");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("BorrarDetalleError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE,null,e);
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
            _ejbFacade.actualizar(_obj);
            return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("Actualizando");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("ActualizarError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }  
    
    public void resetear()
    {
        _obj = null;
    }
    
    @FacesConverter(forClass = Convenciones.class, value = "convencionesConverter")
    public static class ConvencionesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                ConvencionesController controller = (ConvencionesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "convencionesController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Convenciones){
                Convenciones obj = (Convenciones) value;
                return String.valueOf(obj.getCodconvencion());
            }
            return null;
        }
    }
}
