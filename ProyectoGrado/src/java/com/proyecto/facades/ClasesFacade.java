
package com.proyecto.facades;

import com.proyecto.persistences.Clases;
import com.proyecto.persistences.Horas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ClasesFacade extends AbstractFacade<Clases>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public ClasesFacade()
    {
        super(Clases.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
