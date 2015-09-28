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
@Table(name = "jornadas")
public class Jornadas implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codjornada")
    private int _codjornada;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _nombre;    
    
    @JoinColumn(name = "codhora", referencedColumnName = "codhora")
    @ManyToOne(optional = false)
    private Horas _codhora;

    public Jornadas() { }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this._codjornada;
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
        final Jornadas other = (Jornadas) obj;
        if (this._codjornada != other._codjornada) {
            return false;
        }
        if (!Objects.equals(this._nombre, other._nombre)) {
            return false;
        }
        if (!Objects.equals(this._codhora, other._codhora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  _nombre;
    }

    public int getCodjornada() {
        return _codjornada;
    }

    public void setCodjornada(int _codjornada) {
        this._codjornada = _codjornada;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Horas getCodhora() {
        return _codhora;
    }

    public void setCodhora(Horas _codhora) {
        this._codhora = _codhora;
    }
    
    
}
