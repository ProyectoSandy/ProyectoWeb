
package com.proyecto.facades;

import com.proyecto.persistences.Clases;
import com.proyecto.persistences.Evaluaciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class EvaluacionesFacade extends AbstractFacade<Evaluaciones>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public EvaluacionesFacade()
    {
        super(Evaluaciones.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
