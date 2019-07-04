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
@Table(name = "saldoextra")
@NamedQueries({
    @NamedQuery(name = "Saldoextra.findAll", query = "SELECT s FROM Saldoextra s"),
    @NamedQuery(name = "Saldoextra.findById", query = "SELECT s FROM Saldoextra s WHERE s.id = :id"),
    @NamedQuery(name = "Saldoextra.findByHoras", query = "SELECT s FROM Saldoextra s WHERE s.horas = :horas"),
    @NamedQuery(name = "Saldoextra.findByTipo", query = "SELECT s FROM Saldoextra s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "Saldoextra.findBySaldo", query = "SELECT s FROM Saldoextra s WHERE s.saldo = :saldo"),
    @NamedQuery(name = "Saldoextra.findByDescripci\u00f3n", query = "SELECT s FROM Saldoextra s WHERE s.descripci\u00f3n = :descripci\u00f3n"),
    @NamedQuery(name = "Saldoextra.findByFecha", query = "SELECT s FROM Saldoextra s WHERE s.fecha = :fecha")})
public class Saldoextra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Horas")
    private Integer horas;
    @Column(name = "Tipo")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Saldo")
    private Float saldo;
    @Column(name = "Descripci\u00f3n")
    private String descripción;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Empleados_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleados empleadosid;

    public Saldoextra() {
    }

    public Saldoextra(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof Saldoextra)) {
            return false;
        }
        Saldoextra other = (Saldoextra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Saldoextra[ id=" + id + " ]";
    }
    
}
