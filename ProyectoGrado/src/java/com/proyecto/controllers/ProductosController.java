
package com.proyecto.controllers;

import com.java.utilities.Formulario;
import com.java.utilities.Mensajes;
import com.proyecto.facades.ActividadesFacade;
import com.proyecto.facades.DocentesFacade;
import com.proyecto.facades.ProductosFacade;
import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Docentes;
import com.proyecto.persistences.Productos;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    public String agregar()
    {
        String titulo,detalle;
        
        try {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarOk");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("GrabarDetalleOk");
            Mensajes.exito(titulo, detalle);*/
            _ejbFacade.crear(_obj);
            return "crear";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("GrabarError");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("GrabarDetalleError");
            Mensajes.error(titulo, detalle);*/
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE,null,e);
            return "crear";
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

            /*for(Productos pro : listaProd){
                    
            }*/
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
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE,null,e);
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
            _ejbFacade.actualizar(_obj);
            return "administrar";//nombre de la face a la que debe redireccionar
            
        } catch (Exception e) 
        {
            /*titulo = ResourceBundle.getBundle(_rutaTxt).getString("Actualizando");
            detalle = ResourceBundle.getBundle(_rutaTxt).getString("ActualizarError");
            Mensajes.error(titulo, detalle);*/
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
