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
 * @author oem
 */
@Entity
@Table(name = "empleados")
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findById", query = "SELECT e FROM Empleados e WHERE e.id = :id"),
    @NamedQuery(name = "Empleados.findByNombres", query = "SELECT e FROM Empleados e WHERE e.nombres = :nombres"),
    @NamedQuery(name = "Empleados.findByApellidos", query = "SELECT e FROM Empleados e WHERE e.apellidos = :apellidos"),
    @NamedQuery(name = "Empleados.findByFechadenacimiento", query = "SELECT e FROM Empleados e WHERE e.fechadenacimiento = :fechadenacimiento"),
    @NamedQuery(name = "Empleados.findByDireccion", query = "SELECT e FROM Empleados e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleados.findBySalariobase", query = "SELECT e FROM Empleados e WHERE e.salariobase = :salariobase"),
    @NamedQuery(name = "Empleados.findByHabilitado", query = "SELECT e FROM Empleados e WHERE e.habilitado = :habilitado"),
    @NamedQuery(name = "Empleados.findBySalarioextra", query = "SELECT e FROM Empleados e WHERE e.salarioextra = :salarioextra"),
    @NamedQuery(name = "Empleados.findByEsatadocivil", query = "SELECT e FROM Empleados e WHERE e.esatadocivil = :esatadocivil"),
    @NamedQuery(name = "Empleados.findByFechadeingreso", query = "SELECT e FROM Empleados e WHERE e.fechadeingreso = :fechadeingreso")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Nombres")
    private String nombres;
    @Column(name = "Apellidos")
    private String apellidos;
    @Column(name = "Fecha_de_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechadenacimiento;
    @Column(name = "Direccion")
    private String direccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Salario_base")
    private Float salariobase;
    @Column(name = "Habilitado")
    private String habilitado;
    @Column(name = "Salario_extra")
    private Float salarioextra;
    @Column(name = "Esatado_civil")
    private String esatadocivil;
    @Column(name = "Fecha_de_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechadeingreso;
    @OneToMany(mappedBy = "empleadosid")
    private List<Telefono> telefonoList;

    public Empleados() {
    }

    public Empleados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(Date fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Float getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(Float salariobase) {
        this.salariobase = salariobase;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Float getSalarioextra() {
        return salarioextra;
    }

    public void setSalarioextra(Float salarioextra) {
        this.salarioextra = salarioextra;
    }

    public String getEsatadocivil() {
        return esatadocivil;
    }

    public void setEsatadocivil(String esatadocivil) {
        this.esatadocivil = esatadocivil;
    }

    public Date getFechadeingreso() {
        return fechadeingreso;
    }

    public void setFechadeingreso(Date fechadeingreso) {
        this.fechadeingreso = fechadeingreso;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
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
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Empleados[ id=" + id + " ]";
    }
    
}
