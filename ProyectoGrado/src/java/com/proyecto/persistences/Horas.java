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
@Table(name = "horas")
public class Horas implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codhora")
    private int _codhora;
    
    @Column(name = "nombre")
    @Size(min = 1, max = 100)
    @NotNull
    private String _hora;   

    public Horas() {   }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this._codhora;
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
        final Horas other = (Horas) obj;
        if (this._codhora != other._codhora) {
            return false;
        }
        if (!Objects.equals(this._hora, other._hora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Horas{" + "_hora=" + _hora + '}';
    }

    public int getCodhora() {
        return _codhora;
    }

    public void setCodhora(int _codhora) {
        this._codhora = _codhora;
    }

    public String getHora() {
        return _hora;
    }

    public void setHora(String _hora) {
        this._hora = _hora;
    }
    
    
}
