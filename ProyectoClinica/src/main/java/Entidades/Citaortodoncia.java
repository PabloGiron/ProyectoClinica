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
@Table(name = "citaortodoncia")
@NamedQueries({
    @NamedQuery(name = "Citaortodoncia.findAll", query = "SELECT c FROM Citaortodoncia c"),
    @NamedQuery(name = "Citaortodoncia.findByIdCitaOrtodoncia", query = "SELECT c FROM Citaortodoncia c WHERE c.idCitaOrtodoncia = :idCitaOrtodoncia"),
    @NamedQuery(name = "Citaortodoncia.findByFecha", query = "SELECT c FROM Citaortodoncia c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Citaortodoncia.findByPrecio", query = "SELECT c FROM Citaortodoncia c WHERE c.precio = :precio"),
    @NamedQuery(name = "Citaortodoncia.findByDescripcion", query = "SELECT c FROM Citaortodoncia c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Citaortodoncia.findByPagado", query = "SELECT c FROM Citaortodoncia c WHERE c.pagado = :pagado")})
public class Citaortodoncia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCitaOrtodoncia")
    private Integer idCitaOrtodoncia;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Precio")
    private float precio;
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "Pagado")
    private short pagado;
    @JoinColumn(name = "HistorialPaciente_idHistorialPaciente", referencedColumnName = "idHistorialPaciente")
    @ManyToOne(optional = false)
    private Historialpaciente historialPacienteidHistorialPaciente;

    public Citaortodoncia() {
    }

    public Citaortodoncia(Integer idCitaOrtodoncia) {
        this.idCitaOrtodoncia = idCitaOrtodoncia;
    }

    public Citaortodoncia(Integer idCitaOrtodoncia, Date fecha, float precio, short pagado) {
        this.idCitaOrtodoncia = idCitaOrtodoncia;
        this.fecha = fecha;
        this.precio = precio;
        this.pagado = pagado;
    }

    public Integer getIdCitaOrtodoncia() {
        return idCitaOrtodoncia;
    }

    public void setIdCitaOrtodoncia(Integer idCitaOrtodoncia) {
        this.idCitaOrtodoncia = idCitaOrtodoncia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getPagado() {
        return pagado;
    }

    public void setPagado(short pagado) {
        this.pagado = pagado;
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
        hash += (idCitaOrtodoncia != null ? idCitaOrtodoncia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citaortodoncia)) {
            return false;
        }
        Citaortodoncia other = (Citaortodoncia) object;
        if ((this.idCitaOrtodoncia == null && other.idCitaOrtodoncia != null) || (this.idCitaOrtodoncia != null && !this.idCitaOrtodoncia.equals(other.idCitaOrtodoncia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Citaortodoncia[ idCitaOrtodoncia=" + idCitaOrtodoncia + " ]";
    }
    
}
