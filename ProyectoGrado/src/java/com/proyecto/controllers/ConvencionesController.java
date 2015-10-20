
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.facades.ConvencionesFacade;
import com.proyecto.persistences.Convenciones;
import com.proyecto.utilities.Mensajes;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
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
            _ejbFacade.crear(_obj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            Mensajes.exito(titulo, detalle);
            return "crear";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            Mensajes.error(titulo, detalle);
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
            _ejbFacade.borrar(faceObj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            return "administrar";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Convenciones.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }    
    
    public String actualizar()
    {
        String titulo,detalle;
        
        try {
            _ejbFacade.actualizar(_obj);
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            Mensajes.exito(titulo, detalle);
            return "administrar";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            Mensajes.error(titulo, detalle);
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
