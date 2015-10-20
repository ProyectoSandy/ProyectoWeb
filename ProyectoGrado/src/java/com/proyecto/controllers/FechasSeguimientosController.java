
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.facades.FechasSeguimientosFacade;
import com.proyecto.persistences.FechasSeguimientos;
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
public class FechasSeguimientosController implements Serializable{

    @EJB
    private FechasSeguimientosFacade _ejbFacade;
    private FechasSeguimientos _obj;
    
    public FechasSeguimientosController() {
    }
    
    public FechasSeguimientos getCampo()
    {
        if(_obj==null)  _obj= new FechasSeguimientos();
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
            Logger.getLogger(FechasSeguimientos.class.getName()).log(Level.SEVERE,null,e);
            return "crear";
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    
    public List<FechasSeguimientos> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public String redireccionar(String faces, FechasSeguimientos facesObj)
    {
        _obj = facesObj;
        return faces;
    }
    
    public String borrar(FechasSeguimientos faceObj)
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
            Logger.getLogger(FechasSeguimientos.class.getName()).log(Level.SEVERE,null,e);
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
            Logger.getLogger(FechasSeguimientos.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }  
    
    public void resetear()
    {
        _obj = null;
    }
    
    @FacesConverter(forClass = FechasSeguimientos.class, value = "fechasSeguimientosConverter")
    public static class FechasSeguimientosControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                FechasSeguimientosController controller = (FechasSeguimientosController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "fechasSeguimientosController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(FechasSeguimientos.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof FechasSeguimientos){
                FechasSeguimientos obj = (FechasSeguimientos) value;
                return String.valueOf(obj.getCodfechaseg());
            }
            return null;
        }
    }
}
