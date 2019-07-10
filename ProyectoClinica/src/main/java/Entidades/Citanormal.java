/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nasc_
 */
@Entity
@Table(name = "citanormal")
@NamedQueries({
    @NamedQuery(name = "Citanormal.findAll", query = "SELECT c FROM Citanormal c"),
    @NamedQuery(name = "Citanormal.findByIdCitaNormal", query = "SELECT c FROM Citanormal c WHERE c.idCitaNormal = :idCitaNormal"),
    @NamedQuery(name = "Citanormal.findByFecha", query = "SELECT c FROM Citanormal c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Citanormal.findByDescripcion", query = "SELECT c FROM Citanormal c WHERE c.descripcion = :descripcion")})
public class Citanormal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCitaNormal")
    private Integer idCitaNormal;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "HistorialPaciente_idHistorialPaciente", referencedColumnName = "idHistorialPaciente")
    @ManyToOne(optional = false)
    private Historialpaciente historialPacienteidHistorialPaciente;

    public Citanormal() {
    }

    public Citanormal(Integer idCitaNormal) {
        this.idCitaNormal = idCitaNormal;
    }

    public Citanormal(Integer idCitaNormal, Date fecha) {
        this.idCitaNormal = idCitaNormal;
        this.fecha = fecha;
    }

    public Integer getIdCitaNormal() {
        return idCitaNormal;
    }

    public void setIdCitaNormal(Integer idCitaNormal) {
        this.idCitaNormal = idCitaNormal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Historialpaciente getHistorialPacienteidHistorialPaciente() {
        return historialPacienteidHistorialPaciente;
    }

    public void setHistorialPacienteidHistorialPaciente(Historialpaciente historialPacienteidHistorialPaciente) {
        this.historialPacienteidHistorialPaciente = historialPacienteidHistorialPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCitaNormal != null ? idCitaNormal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citanormal)) {
            return false;
        }
        Citanormal other = (Citanormal) object;
        if ((this.idCitaNormal == null && other.idCitaNormal != null) || (this.idCitaNormal != null && !this.idCitaNormal.equals(other.idCitaNormal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Citanormal[ idCitaNormal=" + idCitaNormal + " ]";
    }
    
}
