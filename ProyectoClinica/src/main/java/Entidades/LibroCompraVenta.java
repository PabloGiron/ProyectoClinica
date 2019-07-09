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
@Table(name = "libro_compra_venta")
@NamedQueries({
    @NamedQuery(name = "LibroCompraVenta.findAll", query = "SELECT l FROM LibroCompraVenta l"),
    @NamedQuery(name = "LibroCompraVenta.findById", query = "SELECT l FROM LibroCompraVenta l WHERE l.id = :id"),
    @NamedQuery(name = "LibroCompraVenta.findByVentastotal", query = "SELECT l FROM LibroCompraVenta l WHERE l.ventastotal = :ventastotal"),
    @NamedQuery(name = "LibroCompraVenta.findByComprastotal", query = "SELECT l FROM LibroCompraVenta l WHERE l.comprastotal = :comprastotal"),
    @NamedQuery(name = "LibroCompraVenta.findByTotal", query = "SELECT l FROM LibroCompraVenta l WHERE l.total = :total"),
    @NamedQuery(name = "LibroCompraVenta.findByPerdidaGanancia", query = "SELECT l FROM LibroCompraVenta l WHERE l.perdidaGanancia = :perdidaGanancia"),
    @NamedQuery(name = "LibroCompraVenta.findByFecha", query = "SELECT l FROM LibroCompraVenta l WHERE l.fecha = :fecha"),
    @NamedQuery(name = "LibroCompraVenta.idMax", query = "SELECT MAX(l.id) FROM LibroCompraVenta l")})
public class LibroCompraVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Ventas_total")
    private Float ventastotal;
    @Column(name = "Compras_total")
    private Float comprastotal;
    @Column(name = "Total")
    private Float total;
    @Column(name = "Perdida_Ganancia")
    private String perdidaGanancia;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "librocompraventaid")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "librocompraventaid")
    private List<Ventas> ventasList;

    public LibroCompraVenta() {
    }

    public LibroCompraVenta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getVentastotal() {
        return ventastotal;
    }

    public void setVentastotal(Float ventastotal) {
        this.ventastotal = ventastotal;
    }

    public Float getComprastotal() {
        return comprastotal;
    }

    public void setComprastotal(Float comprastotal) {
        this.comprastotal = comprastotal;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getPerdidaGanancia() {
        return perdidaGanancia;
    }

    public void setPerdidaGanancia(String perdidaGanancia) {
        this.perdidaGanancia = perdidaGanancia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
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
        if (!(object instanceof LibroCompraVenta)) {
            return false;
        }
        LibroCompraVenta other = (LibroCompraVenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.LibroCompraVenta[ id=" + id + " ]";
    }
    
}
