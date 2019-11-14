package Entidades;

import Entidades.Tutorpaciente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, String> nit;
    public static volatile SingularAttribute<Paciente, String> direccion;
    public static volatile SingularAttribute<Paciente, Tutorpaciente> tutorPacienteid;
    public static volatile SingularAttribute<Paciente, Integer> id;
    public static volatile SingularAttribute<Paciente, String> nombre;
    public static volatile SingularAttribute<Paciente, Integer> edad;

}