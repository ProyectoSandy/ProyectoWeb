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
import javax.persistence.OneToOne;
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
    
    @JoinColumn(name = "coddoc", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes _coddoc;
    
    @JoinColumn(name = "codprod", referencedColumnName = "codproducto")
    @OneToOne(optional = false)  
    //@Column(name = "codprod")
    private Productos _codprod;
    
    @NotNull
    @Column(name = "calificacion")    
    private int _calificacion;

    public Evaluaciones() {  }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this._codeva;
        hash = 53 * hash + Objects.hashCode(this._coddoc);
        hash = 53 * hash + Objects.hashCode(this._codprod);
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
        if (this._codeva != other._codeva) {
            return false;
        }
        if (!Objects.equals(this._coddoc, other._coddoc)) {
            return false;
        }
        if (!Objects.equals(this._codprod, other._codprod)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Evaluaciones{" + "_coddocente=" + _coddoc + ", _codproducto=" + _codprod + ", _calificacion=" + _calificacion + '}';
    }

    public Docentes getCoddoc() {
        return _coddoc;
    }

    public void setCoddoc(Docentes _coddoc) {
        this._coddoc = _coddoc;
    }

    public Productos getCodprod() {
        return _codprod;
    }

    public void setCodprod(Productos _codprod) {
        this._codprod = _codprod;
    }

    

    public int getCodeva() {
        return _codeva;
    }

    public void setCodeva(int _codeva) {
        this._codeva = _codeva;
    }   

    public int getCalificacion() {
        return _calificacion;
    }

    public void setCalificacion(int _calificacion) {
        this._calificacion = _calificacion;
    }
    
}
