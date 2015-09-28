
package com.proyecto.controllers;

import com.java.utilities.Formulario;
import com.proyecto.facades.ClasesFacade;
import com.proyecto.persistences.Clases;
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
public class ClasesController implements Serializable{
    
    @EJB
    private ClasesFacade clasesFacade;
    private Clases _objClase;
    
    public ClasesController() {
    }
    
    public Clases getCampo()
    {
        if(_objClase==null)  _objClase= new Clases();
        return _objClase;        
    }
    
    public String agregar()
    {
        String titulo,detalle;
        
        try {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarOk");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("GrabarDetalleOk");
            Mensajes.exito(titulo, detalle);*/
            System.out.println("Docente: " + _objClase);
            clasesFacade.crear(_objClase);
            return "crear";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarError");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("GrabarDetalleError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);
            return "crear";
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(clasesFacade.listado(), texto);
    }
    
    public List<Clases> getListado()
    {
        return clasesFacade.listado();
    }
    
    public String redireccionar(String faces, Clases facesObj)
    {
        _objClase = facesObj;
        return faces;
    }
    
    public String borrar(Clases faceObj)
    {
        String titulo,detalle;
        
        try {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("BorrarOk");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("BorrarDetalleOk");
            Mensajes.exito(titulo, detalle);*/
            clasesFacade.borrar(faceObj);
            return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarError");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("BorrarDetalleError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);
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
            clasesFacade.actualizar(_objClase);
            return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("Actualizando");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("ActualizarError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }  
    
    public void resetear()
    {
        _objClase = null;
    }
    
    @FacesConverter(forClass = Clases.class, value = "clasesConverter")
    public static class ClasesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                ClasesController controller = (ClasesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "clasesController");
                return controller.clasesFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Clases.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Clases){
                Clases obj = (Clases) value;
                return String.valueOf(obj.getCodclase());
            }
            return null;
        }
    }
}
