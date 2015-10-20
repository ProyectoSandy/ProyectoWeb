
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.facades.ClasesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Clases;
import com.proyecto.persistences.Docentes;
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
public class ClasesController implements Serializable{
    @EJB
    private DocentesFacade docentesFacade;
    
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
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            Mensajes.exito(titulo, detalle);
            _objClase.setCoddocente(docentesFacade.getCurrentDocente());
            clasesFacade.crear(_objClase);
            return "crear";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            Mensajes.error(titulo, detalle);
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
        Docentes doc = docentesFacade.getCurrentDocente();
        String cedula= doc.getCedula()+"";
       
        return clasesFacade.buscarCampo("_coddocente",cedula);
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
            System.out.println("CLASES: " + docentesFacade.getCurrentDocente());
            _objClase.setCoddocente(docentesFacade.getCurrentDocente());
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
