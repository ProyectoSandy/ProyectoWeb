/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.facades;

import com.proyecto.persistences.Horas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class HorasFacade extends AbstractFacade<Horas>
{
   @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public HorasFacade()
    {
        super(Horas.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
