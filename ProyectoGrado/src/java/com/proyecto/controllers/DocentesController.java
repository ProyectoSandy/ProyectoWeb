
package com.proyecto.controllers;

import com.java.utilities.Formulario;
import com.java.utilities.Mensajes;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Docentes;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;


@ManagedBean
@SessionScoped
public class DocentesController implements Serializable
{  
    @EJB
    private DocentesFacade _ejbFacade;
    private Docentes _obj;
    
    private String _rutaTxt = "/com/java/utilities/txtDocentes"; 
    private String _titulo="Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    
    
    private String usuDocente;
    
    //@ManagedProperty(value="#{loginController}")
    private LoginController _loginController;
    
    public DocentesController() { }
    
    public Docentes getCampo()
    {
        if(_obj==null)  _obj= new Docentes();
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
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE,null,e);
            return "crear";
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    
    public List<Docentes> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public String redireccionar(String faces, Docentes facesObj)
    {
        _obj = facesObj;
        return faces;
    }
    
    public String borrar(Docentes faceObj)
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
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE,null,e);
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
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }  
    
    public void resetear()
    {
        _obj = null;
    }
    
    public Docentes buscar(){
        System.out.println("USUARIO DE DOCENTE "+_loginController);
       
        
        usuDocente =_loginController.getUsuario();
        return _ejbFacade.buscar(Integer.parseInt(usuDocente));
    }
    
    @FacesConverter(forClass = Docentes.class, value = "docentesConverter")
    public static class DocentesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                DocentesController controller = (DocentesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "docentesController");
                System.out.println("Docentse NUMERO ENCONTRADO EN EL COMBO "+controller._ejbFacade.buscar(id));
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Docentes){
                Docentes obj = (Docentes) value;
                return String.valueOf(obj.getCedula());
            }
            return null;
        }
    }
}
