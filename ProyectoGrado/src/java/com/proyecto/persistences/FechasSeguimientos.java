package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fechaseguimientos")
public class FechasSeguimientos implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codfechseg")
    private int _codfechaseg;
    
    @Column(name = "fechainicio")   
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date _fechainicio;
    
    @Column(name = "fechafin")    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date _fechafin;
    
    @JoinColumn(name = "codact", referencedColumnName = "codactividad")
    @ManyToOne(optional = false)
    private Actividades _codactividad;  
    
    @Column(name = "hora")
    @NotNull
    private int hora;

    public FechasSeguimientos() {  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this._codfechaseg;
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
        final FechasSeguimientos other = (FechasSeguimientos) obj;
        if (this._codfechaseg != other._codfechaseg) {
            return false;
        }
        if (!Objects.equals(this._fechainicio, other._fechainicio)) {
            return false;
        }
        if (!Objects.equals(this._fechafin, other._fechafin)) {
            return false;
        }
        if (!Objects.equals(this._codactividad, other._codactividad)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FechasSeguimientos{" + "_fechainicio=" + _fechainicio + ", _fechafin=" + _fechafin + '}';
    }

    public int getCodfechaseg() {
        return _codfechaseg;
    }

    public void setCodfechaseg(int _codfechaseg) {
        this._codfechaseg = _codfechaseg;
    }
    
    public Actividades getCodactividad() {
        return _codactividad;
    }

    public void setCodactividad(Actividades _codactividad) {
        this._codactividad = _codactividad;
    }

    public Date getFechainicio() {
        return _fechainicio;
    }

    public void setFechainicio(Date _fechainicio) {
        this._fechainicio = _fechainicio;
    }

    public Date getFechafin() {
        return _fechafin;
    }

    public void setFechafin(Date _fechafin) {
        this._fechafin = _fechafin;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    
    
}
