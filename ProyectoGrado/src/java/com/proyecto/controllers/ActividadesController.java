
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


@ManagedBean
@SessionScoped
public class ActividadesController implements Serializable
{  
    @EJB
    private ActividadesFacade _ejbFacade;
    
    @EJB
    private DocentesFacade docentesFacade;
    
    private Actividades _obj;
    
    private String cedula="";
    
    private String _rutaTxt = "/com/java/utilities/txtActividades"; 
    private String _titulo="Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    
    public ActividadesController() { }
    
    public Actividades getCampo()
    {
        if(_obj==null)  _obj= new Actividades();
        return _obj;        
    }
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("faces/actividades/crear", options, null);
        
    }
    
    
    
    public void agregar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            Mensajes.exito(titulo, detalle);
            _obj.setCoddocente(docentesFacade.getCurrentDocente());
            _ejbFacade.crear(_obj);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            //return "crear";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE,null,e);
           // return "crear";
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    
    public SelectItem[] comboFiltrado(String texto)
    {
        Docentes doc=docentesFacade.getCurrentDocente();
        System.out.println("ActividadesController --- Docente: "+doc.toString());
        int cedula = doc.getCedula();
        System.out.println("ActividadesController --- Docente: "+cedula);
        return Formulario.addObject(_ejbFacade.buscarCampo("_coddocente", ""+cedula), texto);
    }
    
    public String btnBuscar(){
        
       
        System.out.println("CLICK EN BTN BUSCAR");
        cedula= _obj.getCoddocente().getCedula()+"";
        
        return "index_evaluador";
    }
    
    
    public List<Actividades> getListarEvaluaciones()
    {
       
       if(cedula==""){
           System.out.println("ES NULO");
            return null;
       }else{
           System.out.println("NO ES NULO");
            return _ejbFacade.buscarCampo("_coddocente",cedula);
       }
    }
     
    public List<Actividades> getListado()
    {
        Docentes doc = docentesFacade.getCurrentDocente();
        String cedula= doc.getCedula()+"";
       
        return _ejbFacade.buscarCampo("_coddocente",cedula);
    }
    
    
    public void borrar(Actividades faceObj)
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            _ejbFacade.borrar(faceObj);
            //return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE,null,e);
            //return "administrar";
        }
    }    
    
    public void abrirActualizar(Actividades objtemp) {
        
        System.out.println("ENTRO A ABRIR ACTUALIZAR");
        _obj = objtemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        System.out.println("VA A ABRIR ACTULIZAR");
        RequestContext.getCurrentInstance().openDialog("actividades/actualizar", options, null);
    }
    
    public void abrirEvaluacion(Actividades objTemp) {
        
        System.out.println("ENTRO A ABRIR EVALUACION");
        _obj= objTemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        System.out.println("VA A ABRIR EVALUACIO");
        RequestContext.getCurrentInstance().openDialog("evaluaciones/actualizar", options, null);
    }
    
    public void hixoClick(){
        System.out.println("HIXO CLICL EN EL BOTON ");
    }
    
    public void guardarEvaluacion()
    {
        System.out.println("ENTRO A GUARDAR EVAUACION1");
        String titulo,detalle;
        System.out.println("ENTRO GUARDAR EVALUACION ");
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            Mensajes.exito(titulo, detalle);
            //_obj.setCoddocente(docentesFacade.getCurrentDocente());
            System.out.println("GUARDAR EVALUACION "+_obj.getValoracion());
            _ejbFacade.actualizar(_obj);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
           // return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE,null,e);
            //return "administrar";
        }
    }  
    
    public void actualizar()
    {
        System.out.println("ENTRO A ACTULIZAR");
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            Mensajes.exito(titulo, detalle);
            System.out.println("INTENTA ACTULIZAR");
            _obj.setCoddocente(docentesFacade.getCurrentDocente());
            _ejbFacade.actualizar(_obj);
            System.out.println("YA ACTULIZA");
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
           // return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE,null,e);
            //return "administrar";
        }
    }  
    
    public void resetear()
    {
        _obj = null;
    }
    
    @FacesConverter(forClass = Actividades.class, value = "actividadesConverter")
    public static class ActividadesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                ActividadesController controller = (ActividadesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "actividadesController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Actividades.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Actividades){
                Actividades obj = (Actividades) value;
                return String.valueOf(obj.getCodactividad());
            }
            return null;
        }
    }
}
