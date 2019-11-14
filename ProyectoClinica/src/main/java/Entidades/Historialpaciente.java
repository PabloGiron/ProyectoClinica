/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nasc_
 */
@Entity
@Table(name = "historialpaciente")
@NamedQueries({
    @NamedQuery(name = "Historialpaciente.findAll", query = "SELECT h FROM Historialpaciente h"),
    @NamedQuery(name = "Historialpaciente.findByIdHistorialPaciente", query = "SELECT h FROM Historialpaciente h WHERE h.idHistorialPaciente = :idHistorialPaciente"),
    @NamedQuery(name = "Historialpaciente.findByFechaInicio", query = "SELECT h FROM Historialpaciente h WHERE h.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Historialpaciente.findByFechaFin", query = "SELECT h FROM Historialpaciente h WHERE h.fechaFin = :fechaFin")})
public class Historialpaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHistorialPaciente")
    private Integer idHistorialPaciente;
    @Basic(optional = false)
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "FechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historialPacienteidHistorialPaciente")
    private List<Citaortodoncia> citaortodonciaList;
    @JoinColumn(name = "Paciente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Paciente pacienteid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historialPacienteidHistorialPaciente")
    private List<Citanormal> citanormalList;

    public Historialpaciente() {
    }

    public Historialpaciente(Integer idHistorialPaciente) {
        this.idHistorialPaciente = idHistorialPaciente;
    }

    public Historialpaciente(Integer idHistorialPaciente, Date fechaInicio, Date fechaFin) {
        this.idHistorialPaciente = idHistorialPaciente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdHistorialPaciente() {
        return idHistorialPaciente;
    }

    public void setIdHistorialPaciente(Integer idHistorialPaciente) {
        this.idHistorialPaciente = idHistorialPaciente;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Citaortodoncia> getCitaortodonciaList() {
        return citaortodonciaList;
    }

    public void setCitaortodonciaList(List<Citaortodoncia> citaortodonciaList) {
        this.citaortodonciaList = citaortodonciaList;
    }

    public Paciente getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(Paciente pacienteid) {
        this.pacienteid = pacienteid;
    }

    public List<Citanormal> getCitanormalList() {
        return citanormalList;
    }

    public void setCitanormalList(List<Citanormal> citanormalList) {
        this.citanormalList = citanormalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialPaciente != null ? idHistorialPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialpaciente)) {
            return false;
        }
        Historialpaciente other = (Historialpaciente) object;
        if ((this.idHistorialPaciente == null && other.idHistorialPaciente != null) || (this.idHistorialPaciente != null && !this.idHistorialPaciente.equals(other.idHistorialPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Historialpaciente[ idHistorialPaciente=" + idHistorialPaciente + " ]";
    }
    
}
