
package com.proyecto.facades;

import com.proyecto.persistences.Actividades;
import com.proyecto.persistences.Horas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ActividadesFacade extends AbstractFacade<Actividades>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public ActividadesFacade()
    {
        super(Actividades.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
