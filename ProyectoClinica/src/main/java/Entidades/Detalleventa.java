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
@Table(name = "detalleventa")
@NamedQueries({
    @NamedQuery(name = "Detalleventa.findAll", query = "SELECT d FROM Detalleventa d"),
    @NamedQuery(name = "Detalleventa.findById", query = "SELECT d FROM Detalleventa d WHERE d.id = :id"),
    @NamedQuery(name = "Detalleventa.findBySubtotal", query = "SELECT d FROM Detalleventa d WHERE d.subtotal = :subtotal"),
    @NamedQuery(name = "Detalleventa.findByCantidad", query = "SELECT d FROM Detalleventa d WHERE d.cantidad = :cantidad")})
public class Detalleventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    //@Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Subtotal")
    private Float subtotal;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @JoinColumn(name = "Servicio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Servicio servicioid;
    @JoinColumn(name = "Ventas_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ventas ventasid;

    public Detalleventa() {
    }

    public Detalleventa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Servicio getServicioid() {
        return servicioid;
    }

    public void setServicioid(Servicio servicioid) {
        this.servicioid = servicioid;
    }

    public Ventas getVentasid() {
        return ventasid;
    }

    public void setVentasid(Ventas ventasid) {
        this.ventasid = ventasid;
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
        if (!(object instanceof Detalleventa)) {
            return false;
        }
        Detalleventa other = (Detalleventa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detalleventa[ id=" + id + " ]";
    }
    
}
