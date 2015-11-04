
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.facades.ClasesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.persistences.Clases;
import com.proyecto.persistences.Docentes;
import com.proyecto.utilities.Mensajes;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;


@ManagedBean
@SessionScoped
public class ClasesController implements Serializable{
    @EJB
    private DocentesFacade docentesFacade;
    
    @EJB
    private ClasesFacade clasesFacade;
    private Clases _objClase;
    private ScheduleModel eventModel;
    
    public ClasesController() {
    }
    
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", today1Pm(), today1Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today1Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", today1Pm(), today1Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", today1Pm(), today1Pm()));         
        
    }
    
    public ScheduleModel getEventModel() {
        return eventModel;
    }
    
    public Clases getCampo()
    {
        if(_objClase==null)  _objClase= new Clases();
        return _objClase;        
    }
    
    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);
         
        return t.getTime();
    }
    
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("faces/clases/crear", options, null);
    }
    
    public void agregar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            Mensajes.exito(titulo, detalle);
            _objClase.setCoddocente(docentesFacade.getCurrentDocente());
            clasesFacade.crear(_objClase);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            //return "crear";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);
            //return "crear";
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
    
    public void onEventSelect(SelectEvent selectEvent) 
    {
        System.out.print("onEventSelect: " + selectEvent);
        //event = (ScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent)
    {
        System.out.print("onDateSelect: " + selectEvent);
        //event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event)
    {
        System.out.print("onEventMove: " + event);
        /*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);*/
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) 
    {
        System.out.print("onEventResize: " + event);
        /*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);*/
    }
    
    public void borrar(Clases faceObj)
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarExitoso");
            Mensajes.exito(titulo, detalle);
            clasesFacade.borrar(faceObj);
            //return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("eliminarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);
            //return "administrar";
        }
    }    
    
    public void abrirActualizar(Clases objTemp) {
        _objClase = objTemp;
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("faces/clases/actualizar", options, null);
    }
    
    public void actualizar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            Mensajes.exito(titulo, detalle);
            System.out.println("CLASES: " + docentesFacade.getCurrentDocente());
            _objClase.setCoddocente(docentesFacade.getCurrentDocente());
            clasesFacade.actualizar(_objClase);
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            //return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Clases.class.getName()).log(Level.SEVERE,null,e);
            //return "administrar";
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
