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
@Table(name = "detallecompra")
@NamedQueries({
    @NamedQuery(name = "Detallecompra.findAll", query = "SELECT d FROM Detallecompra d"),
    @NamedQuery(name = "Detallecompra.findById", query = "SELECT d FROM Detallecompra d WHERE d.id = :id"),
    @NamedQuery(name = "Detallecompra.findBySubtotal", query = "SELECT d FROM Detallecompra d WHERE d.subtotal = :subtotal"),
    @NamedQuery(name = "Detallecompra.findByCantidad", query = "SELECT d FROM Detallecompra d WHERE d.cantidad = :cantidad")})
public class Detallecompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Subtotal")
    private Float subtotal;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @JoinColumn(name = "Compra_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Compra compraid;
    @JoinColumn(name = "Producto_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Producto productoid;

    public Detallecompra() {
    }

    public Detallecompra(Integer id) {
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

    public Compra getCompraid() {
        return compraid;
    }

    public void setCompraid(Compra compraid) {
        this.compraid = compraid;
    }

    public Producto getProductoid() {
        return productoid;
    }

    public void setProductoid(Producto productoid) {
        this.productoid = productoid;
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
        if (!(object instanceof Detallecompra)) {
            return false;
        }
        Detallecompra other = (Detallecompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detallecompra[ id=" + id + " ]";
    }
    
}
