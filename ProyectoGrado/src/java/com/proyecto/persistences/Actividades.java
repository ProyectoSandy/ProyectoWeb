package com.proyecto.persistences;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Actividades implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codactividad")
    private int _codactividad;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;
    
    @Column(name = "descripcion")
    @Size(min = 1, max = 100)
    @NotNull
    private String _descripcion;
    
    @Column(name = "responsable")
    @Size(min = 1, max = 100)
    @NotNull
    private String _responsable;
    
    @Column(name = "valoracion")
    @Size(min = 1, max = 100)
    @NotNull
    private int _valoracion;
    
    @JoinColumn(name = "coddocente", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Docentes _coddocente;
    
    @JoinColumn(name = "codtipo", referencedColumnName = "codtipo")
    @ManyToOne(optional = false)
    private TipoModalidad _codtipo;

    public Actividades() {  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this._codactividad;
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
        final Actividades other = (Actividades) obj;
        if (this._codactividad != other._codactividad) {
            return false;
        }
        if (!Objects.equals(this._nombre, other._nombre)) {
            return false;
        }
        if (!Objects.equals(this._descripcion, other._descripcion)) {
            return false;
        }
        if (!Objects.equals(this._responsable, other._responsable)) {
            return false;
        }
        if (this._valoracion != other._valoracion) {
            return false;
        }
        if (!Objects.equals(this._coddocente, other._coddocente)) {
            return false;
        }
        if (!Objects.equals(this._codtipo, other._codtipo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actividades{" + "_nombre=" + _nombre + '}';
    }

    public int getCodactividad() {
        return _codactividad;
    }

    public void setCodactividad(int _codactividad) {
        this._codactividad = _codactividad;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String getResponsable() {
        return _responsable;
    }

    public void setResponsable(String _responsable) {
        this._responsable = _responsable;
    }

    public int getValoracion() {
        return _valoracion;
    }

    public void setValoracion(int _valoracion) {
        this._valoracion = _valoracion;
    }

    public Docentes getCoddocente() {
        return _coddocente;
    }

    public void setCoddocente(Docentes _coddocente) {
        this._coddocente = _coddocente;
    }

    public TipoModalidad getCodtipo() {
        return _codtipo;
    }

    public void setCodtipo(TipoModalidad _codtipo) {
        this._codtipo = _codtipo;
    }
}