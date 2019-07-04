/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
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

/**
 *
 * @author nasc_
 */
@Entity
@Table(name = "historial")
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findById", query = "SELECT h FROM Historial h WHERE h.id = :id"),
    @NamedQuery(name = "Historial.findByTipo", query = "SELECT h FROM Historial h WHERE h.tipo = :tipo"),
    @NamedQuery(name = "Historial.findByDetalles", query = "SELECT h FROM Historial h WHERE h.detalles = :detalles")})
public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Tipo")
    private String tipo;
    @Column(name = "Detalles")
    private String detalles;
    @JoinColumn(name = "Empleados_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleados empleadosid;

    public Historial() {
    }

    public Historial(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Empleados getEmpleadosid() {
        return empleadosid;
    }

    public void setEmpleadosid(Empleados empleadosid) {
        this.empleadosid = empleadosid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Historial[ id=" + id + " ]";
    }
    
}
