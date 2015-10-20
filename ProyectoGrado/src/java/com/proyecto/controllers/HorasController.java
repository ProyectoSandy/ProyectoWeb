
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.HorasFacade;
import com.proyecto.persistences.Horas;
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
public class HorasController implements Serializable
{  
    @EJB
    private HorasFacade _ejbFacade;
    private Horas _obj;
    
    private String _rutaTxt = "/com/java/utilities/txtHoras"; 
    private String _titulo="Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    public HorasController() { }
    
    public Horas getCampo()
    {
        if(_obj==null)  _obj= new Horas();
        return _obj;        
    }
    
    public String agregar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            Mensajes.exito(titulo, detalle);
            _ejbFacade.crear(_obj);
            return "crear";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Horas.class.getName()).log(Level.SEVERE,null,e);
            return "crear";
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    
    public List<Horas> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public String redireccionar(String faces, Horas facesObj)
    {
        _obj = facesObj;
        return faces;
    }
    
    public String borrar(Horas faceObj)
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
            Logger.getLogger(Horas.class.getName()).log(Level.SEVERE,null,e);
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
            Logger.getLogger(Horas.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }  
    
    public void resetear()
    {
        _obj = null;
    }
    
    @FacesConverter(forClass = Horas.class, value = "horasConverter")
    public static class HorasControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                HorasController controller = (HorasController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "horasController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Horas.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Horas){
                Horas obj = (Horas) value;
                return String.valueOf(obj.getCodhora());
            }
            return null;
        }
    }
}
