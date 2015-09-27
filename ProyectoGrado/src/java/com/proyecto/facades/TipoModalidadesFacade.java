/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.facades;

import com.proyecto.persistences.TipoModalidades;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class TipoModalidadesFacade extends AbstractFacade<TipoModalidades>
{
   @PersistenceContext(unitName = "ProyectoGradoPU")
    private EntityManager _em;
    
    public TipoModalidadesFacade()
    {
        super(TipoModalidades.class);
    }

    @Override
    protected EntityManager obtenerEntidad() 
    {
        return _em;
    }   
}
