
package com.proyecto.facades;

import com.proyecto.persistences.Clases;
import com.proyecto.persistences.Jornadas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class JornadasFacade extends AbstractFacade<Jornadas>{

    @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public JornadasFacade()
    {
        super(Jornadas.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
