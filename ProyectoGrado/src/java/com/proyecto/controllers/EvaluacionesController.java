package com.proyecto.controllers;

import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.EvaluacionesFacade;
import com.proyecto.facades.ProductosFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Evaluaciones;
import com.proyecto.persistences.Productos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.primefaces.context.RequestContext;


@ManagedBean
@SessionScoped
public class EvaluacionesController implements Serializable{
   

    @EJB
    private EvaluacionesFacade _ejbFacade;
    private Evaluaciones _obj;
    @EJB
    private ActividadesFacade _actividadesFacade;
    
    @EJB
    private DocentesFacade docentesFacade;
    
    private Docentes coddoc;
    
    private String cedula="";
    private Actividades act = new Actividades();
    
    public EvaluacionesController() {
    }
    
    public Evaluaciones getCampo()
    {
        if(_obj==null)  _obj= new Evaluaciones();
        return _obj;        
    }
    
    public void abrirActualizar(Actividades obj) {
        act=obj;
        System.out.println("ABRIR ACTUALIZAR "+act.getNombre());
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("faces/evaluaciones/actualizar", options, null);
    }
    
    public void agregar()
    {
        String titulo,detalle;
       // act=acti;
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            Mensajes.exito(titulo, detalle);
            System.out.println("ACTIVIDAD PARA AGREGAR: " + act.getNombre());
            coddoc=_obj.getCoddoc();
            System.out.println("DOCENTE "+coddoc.getCedula());
            //_obj.setCoddoc(coddoc);
            //_obj.setCoact(act);
            _actividadesFacade.actualizar(act);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
           // return "crear";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Evaluaciones.class.getName()).log(Level.SEVERE,null,e);
            //return "crear";
        }
    }
    
    
    public List<Evaluaciones> getListado()
    {
        return _ejbFacade.listado();
    }
    
    public String borrar(Evaluaciones faceObj)
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            _ejbFacade.borrar(faceObj);
            return "administrar";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Evaluaciones.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }    
    
    public String actualizar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            System.out.println("actualizarExitoso");
            Mensajes.exito(titulo, detalle);
           // _actividadesFacade.actualizar(_obj);
            return "administrar";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Evaluaciones.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }  
    
    
    public void btnBuscar(){
        
        System.out.println("CODIGO DE DOCENTES "+_obj.getCoddoc().getCedula());
        cedula= _obj.getCoddoc().getCedula()+"";
        getListar();
    }
    
   public List<Actividades> getListar()
    {
       
       if(cedula==""){
           System.out.println("ES NULO");
            return null;
       }else{
           System.out.println("NO ES NULO");
            return _actividadesFacade.buscarCampo("_coddocente",cedula);
       }
      
       
        //return _actividadesFacade.listado();
    }
    
   /* @FacesConverter(forClass = Evaluaciones.class, value = "evaluacionesConverter")
    public static class EvaluacionesControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                EvaluacionesController controller = (EvaluacionesController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "evaluacionesController");
                return controller._ejbFacade.buscar(id);
            }catch(NumberFormatException e){
                Logger.getLogger(Evaluaciones.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Evaluaciones){
                Evaluaciones obj = (Evaluaciones) value;
                return String.valueOf(obj.getCodeva());
            }
            return null;
        }
    }*/

    public Docentes getCoddoc() {
        return coddoc;
    }

    public void setCoddoc(Docentes coddoc) {
        this.coddoc = coddoc;
    }

    public Actividades getAct() {
        return act;
    }

    public void setAct(Actividades act) {
        this.act = act;
    }

   
    
    

}
