
package com.proyecto.facades;

import com.proyecto.persistences.Docentes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class DocentesFacade extends AbstractFacade<Docentes>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public DocentesFacade()
    {
        super(Docentes.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
