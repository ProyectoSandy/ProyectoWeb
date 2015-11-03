
package com.proyecto.controllers;

import com.proyecto.utilities.Formulario;
import com.proyecto.utilities.Mensajes;
import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.ProductosFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


@ManagedBean
@SessionScoped
public class ProductosController implements Serializable
{  
    @EJB
    private DocentesFacade docentesFacade;
    @EJB
    private ProductosFacade _ejbFacade;
    
    @EJB
    private ActividadesFacade _actividadesFacade;
    
    private Productos _obj;
    
    private String _rutaTxt = "/com/java/utilities/txtProductos"; 
    private String _titulo="Operacion";
    private String _mensajeCorrecto = "Se ha realizado correctamente";
    private String _mensajeError = "No se completo la operacion";
    
    public ProductosController() { }
    
    public Productos getCampo()
    {
        if(_obj==null)  _obj= new Productos();
        return _obj;        
    }   
    
    
    public void abrirCrear() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("faces/productos/crear", options, null);
    }
    
    public void cerrarCrear(SelectEvent event) 
    {        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"prueba","se supone k esto se muestra");         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void agregar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exitoso");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardaExitoso");
            Mensajes.exito(titulo, detalle);
            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,titulo,detalle);
           // FacesContext.getCurrentInstance().addMessage(null, message);
            _ejbFacade.crear(_obj);           
            RequestContext context = RequestContext.getCurrentInstance();
            context.closeDialog(null);
            //return "";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("guardarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE,null,e);
            //return "crear";
        }
    }
    
    public SelectItem[] combo(String texto)
    {
        return Formulario.addObject(_ejbFacade.listado(), texto);
    }
    
    public SelectItem[] traerItem(Docentes coddoc)
    {
        if(coddoc!=null){
            System.out.println("CEDULA QUE LLENA EL COMBO "+coddoc.getCedula());
            int valor = coddoc.getCedula();
            List<Actividades> listaActividades =_actividadesFacade.buscarCampo("_coddocente",valor+"");
            SelectItem[] itemList = new SelectItem[listaActividades.size()];
            
            List<Productos> listaProd = new ArrayList<>();
            for (Actividades acti : listaActividades)
            {
                List<Productos> listaTemp = _ejbFacade.buscarCampo("_codactividad",acti.getCodactividad()+"");
                if(!listaTemp.isEmpty()) listaProd.addAll(listaTemp);
            } 
            for(int i=0; i<listaProd.size(); i++){
                    Productos pro = new Productos();
                    pro=listaProd.get(i);
                    itemList [i] =new SelectItem(pro,pro.getDescripcion()); 
                }
            
            return itemList;
        }
                  
        return null;        
    }
    
    public List<Productos> getListado()
    {        
        Docentes doc =docentesFacade.getCurrentDocente();       
        List<Actividades> listaActividades =_actividadesFacade.buscarCampo("_coddocente",doc.getCedula()+"");
        
        List<Productos> listaProd = new ArrayList<>();
        for (Actividades acti : listaActividades)
        {
            List<Productos> listaTemp = _ejbFacade.buscarCampo("_codactividad",acti.getCodactividad()+"");
            if(!listaTemp.isEmpty()) listaProd.addAll(listaTemp);
        }                
       
        return listaProd;
    }
    
    public String redireccionar(String faces, Productos facesObj)
    {
        _obj = facesObj;
        return faces;
    }
    
    public String borrar(Productos faceObj)
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
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }    
    
    public String actualizar()
    {
        String titulo,detalle;
        
        try {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("exito");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarExitoso");
            Mensajes.exito(titulo, detalle);
            _ejbFacade.actualizar(_obj);
            return "administrar";
            
        } catch (Exception e) 
        {
            titulo = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("error");
            detalle = ResourceBundle.getBundle("/com/proyecto/utilities/GeneralTxt").getString("actualizarError");
            Mensajes.error(titulo, detalle);
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE,null,e);
            return "administrar";
        }
    }  
    
    public void resetear()
    {
        _obj = null;
    }
    
    @FacesConverter(forClass = Productos.class, value = "productosConverter")
    public static class ProductosControllerConverter implements Converter{

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            try{
                if (value == null || value.length() == 0) return null;
                
                Integer id = Integer.parseInt(value);
                ProductosController controller = (ProductosController) context.getApplication().getELResolver().
                        getValue(context.getELContext(), null, "productosController");
                System.out.println("Productos NUMERO ENCONTRADO EN EL COMBO "+controller._ejbFacade.buscar(id));
                return controller._ejbFacade.buscar(id);
                
            }catch(NumberFormatException e){
                System.out.println("Productos Error en converter");
                Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Productos){
                Productos obj = (Productos) value;
                return String.valueOf(obj.getCodproducto());
            }
            return null;
        }
    }
}
