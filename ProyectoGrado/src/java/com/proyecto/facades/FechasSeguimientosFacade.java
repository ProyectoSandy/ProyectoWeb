
package com.proyecto.facades;

import com.proyecto.persistences.FechasSeguimientos;
import com.proyecto.persistences.Horas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class FechasSeguimientosFacade extends AbstractFacade<FechasSeguimientos>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public FechasSeguimientosFacade()
    {
        super(FechasSeguimientos.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
