package com.proyecto.persistences;

import java.io.Serializable;
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
@Table(name = "docentes")
public class Docentes implements Serializable
{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cedula")
    private int _cedula;
    
    @Column(name = "nombres")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombres;
    
    @Column(name = "apellidos")
    @Size(min = 1, max = 100)
    @NotNull
    private String _apellidos;   
    
    @Column(name = "codigo")
    @NotNull
    private String _codigo;  
    
    @Column(name = "semestre")   
    @NotNull
    private int _semestre;  
    
    @NotNull
    @Column(name = "direccion")
    @Size(min = 1, max = 50)
    private String _direccion;
    
    @NotNull
    @Column(name = "telefono")
    @Size(min = 1, max = 50)
    private String _telefono;
    
    @NotNull
    @Column(name = "correo")
    @Size(min = 1, max = 50)
    private String _correo;
    
    @NotNull
    @Column(name = "unidadacademica") 
    private int _unidad;
    
    @NotNull
    @Column(name = "facultad") 
    private int _facultad;   

    @NotNull
    @Column(name = "formacion")
    private String _formacion; 
    
    public Docentes() { }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this._cedula;
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
        final Docentes other = (Docentes) obj;
        if (this._cedula != other._cedula) {
            return false;
        }
        if (!Objects.equals(this._nombres, other._nombres)) {
            return false;
        }
        if (!Objects.equals(this._apellidos, other._apellidos)) {
            return false;
        }
        if (!Objects.equals(this._codigo, other._codigo)) {
            return false;
        }
        if (this._semestre != other._semestre) {
            return false;
        }
        if (!Objects.equals(this._direccion, other._direccion)) {
            return false;
        }
        if (!Objects.equals(this._telefono, other._telefono)) {
            return false;
        }
        if (!Objects.equals(this._correo, other._correo)) {
            return false;
        }
        if (this._unidad != other._unidad) {
            return false;
        }
        if (this._facultad != other._facultad) {
            return false;
        }
        if (!Objects.equals(this._formacion, other._formacion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return _nombres + " " + _apellidos ;
    }
    
    public int getCedula() {
        return _cedula;
    }

    public void setCedula(int cedula) {
        this._cedula = cedula;
    }

    public String getNombres() {
        return _nombres;
    }

    public void setNombres(String _nombres) {
        this._nombres = _nombres;
    }

    public String getApellidos() {
        return _apellidos;
    }

    public void setApellidos(String _apellidos) {
        this._apellidos = _apellidos;
    }

    public String getDireccion() {
        return _direccion;
    }

    public void setDireccion(String _direccion) {
        this._direccion = _direccion;
    }

    public String getTelefono() {
        return _telefono;
    }

    public void setTelefono(String _telefono) {
        this._telefono = _telefono;
    }

    public String getCorreo() {
        return _correo;
    }

    public void setCorreo(String _correo) {
        this._correo = _correo;
    }

    public int getUnidad() {
        return _unidad;
    }

    public void setUnidad(int _unidad) {
        this._unidad = _unidad;
    }

    public int getFacultad() {
        return _facultad;
    }

    public void setFacultad(int _facultad) {
        this._facultad = _facultad;
    }
    
    public int getSemestre() {
        return _semestre;
    }

    public void setSemestre(int _semestre) {
        this._semestre = _semestre;
    }    

    public String getFormacion() {
        return _formacion;
    }

    public void setFormacion(String _formacion) {
        this._formacion = _formacion;
    }

    public String getCodigo() {
        return _codigo;
    }

    public void setCodigo(String _codigo) {
        this._codigo = _codigo;
    }
    
}
