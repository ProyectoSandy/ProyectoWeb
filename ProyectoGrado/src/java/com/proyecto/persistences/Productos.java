package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "codproducto")
public class Productos implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codproducto")
    private int _codproducto;
    
    @Column(name = "descripcion")
    @Size(min = 1, max = 100)
    @NotNull
    private String _descripcion;
    
    @Column(name = "fechacompromiso")
    @Size(min = 1, max = 100)
    @NotNull
    private Date _fechacompromiso;
    
    @Column(name = "fechaentrega")
    @Size(min = 1, max = 100)
    @NotNull
    private Date _fechaentrega;
    
    @Column(name = "comentarios")
    @Size(min = 1, max = 100)
    @NotNull
    private String _comentarios;
    
    @Column(name = "codactividad")
    @Size(min = 1, max = 100)
    @NotNull
    private Actividades _codactividad;

    public Productos() {  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this._codproducto;
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
        final Productos other = (Productos) obj;
        if (this._codproducto != other._codproducto) {
            return false;
        }
        if (!Objects.equals(this._descripcion, other._descripcion)) {
            return false;
        }
        if (!Objects.equals(this._fechacompromiso, other._fechacompromiso)) {
            return false;
        }
        if (!Objects.equals(this._fechaentrega, other._fechaentrega)) {
            return false;
        }
        if (!Objects.equals(this._comentarios, other._comentarios)) {
            return false;
        }
        if (this._codactividad != other._codactividad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Productos{" + "_descripcion=" + _descripcion + '}';
    }

    public int getCodproducto() {
        return _codproducto;
    }

    public void setCodproducto(int _codproducto) {
        this._codproducto = _codproducto;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public Date getFechacompromiso() {
        return _fechacompromiso;
    }

    public void setFechacompromiso(Date _fechacompromiso) {
        this._fechacompromiso = _fechacompromiso;
    }

    public Date getFechaentrega() {
        return _fechaentrega;
    }

    public void setFechaentrega(Date _fechaentrega) {
        this._fechaentrega = _fechaentrega;
    }

    public String getComentarios() {
        return _comentarios;
    }

    public void setComentarios(String _comentarios) {
        this._comentarios = _comentarios;
    }

    public Actividades getCodactividad() {
        return _codactividad;
    }

    public void setCodactividad(Actividades _codactividad) {
        this._codactividad = _codactividad;
    }
    
}
