package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "evaluaciones")
public class Evaluaciones implements Serializable
{
    @Id
    @Column(name = "codeva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int _codeva;
    
    //@JoinColumn(name = "coddoc", referencedColumnName = "cedula")
    //@ManyToOne(optional = false)
    @Column(name = "coddoc")
    private int _coddocente;
    
    //@JoinColumn(name = "codprod", referencedColumnName = "codproducto")
    //@ManyToOne(optional = false)  
    @Column(name = "codprod")
    private int _codproducto;
    
    @NotNull
    @Column(name = "calificacion")    
    private float _calificacion;

    public Evaluaciones() {  }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this._coddocente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evaluaciones other = (Evaluaciones) obj;
        if (!Objects.equals(this._coddocente, other._coddocente)) {
            return false;
        }
        if (!Objects.equals(this._codproducto, other._codproducto)) {
            return false;
        }
        if (Double.doubleToLongBits(this._calificacion) != Double.doubleToLongBits(other._calificacion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluaciones{" + "_coddocente=" + _coddocente + ", _codproducto=" + _codproducto + ", _calificacion=" + _calificacion + '}';
    }

    public int getCoddocente() {
        return _coddocente;
    }

    public void setCoddocente(int _coddocente) {
        this._coddocente = _coddocente;
    }

    public int getCodproducto() {
        return _codproducto;
    }

    public void setCodproducto(int _codproducto) {
        this._codproducto = _codproducto;
    }

    public int getCodeva() {
        return _codeva;
    }

    public void setCodeva(int _codeva) {
        this._codeva = _codeva;
    }

    public float getCalificacion() {
        return _calificacion;
    }

    public void setCalificacion(float _calificacion) {
        this._calificacion = _calificacion;
    }
    
    
}
