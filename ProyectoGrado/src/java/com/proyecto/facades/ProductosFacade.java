
package com.proyecto.facades;

import com.proyecto.persistences.Clases;
import com.proyecto.persistences.Productos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ProductosFacade extends AbstractFacade<Productos>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public ProductosFacade()
    {
        super(Productos.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
