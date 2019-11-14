package Entidades;

import Entidades.Telefono;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Empleados.class)
public class Empleados_ { 

    public static volatile SingularAttribute<Empleados, String> apellidos;
    public static volatile SingularAttribute<Empleados, String> esatadocivil;
    public static volatile SingularAttribute<Empleados, Date> fechadeingreso;
    public static volatile ListAttribute<Empleados, Telefono> telefonoList;
    public static volatile SingularAttribute<Empleados, String> direccion;
    public static volatile SingularAttribute<Empleados, Float> salariobase;
    public static volatile SingularAttribute<Empleados, Integer> id;
    public static volatile SingularAttribute<Empleados, String> habilitado;
    public static volatile SingularAttribute<Empleados, Date> fechadenacimiento;
    public static volatile SingularAttribute<Empleados, String> nombres;
    public static volatile SingularAttribute<Empleados, Float> salarioextra;

}