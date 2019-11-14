package Entidades;

import Entidades.Empleados;
import Entidades.Paciente;
import Entidades.Tutorpaciente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Telefono.class)
public class Telefono_ { 

    public static volatile SingularAttribute<Telefono, Empleados> empleadosid;
    public static volatile SingularAttribute<Telefono, String> numero;
    public static volatile SingularAttribute<Telefono, Tutorpaciente> tutorPacienteid;
    public static volatile SingularAttribute<Telefono, Integer> id;
    public static volatile SingularAttribute<Telefono, Paciente> pacienteid;

}