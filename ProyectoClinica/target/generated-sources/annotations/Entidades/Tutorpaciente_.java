package Entidades;

import Entidades.Paciente;
import Entidades.Telefono;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Tutorpaciente.class)
public class Tutorpaciente_ { 

    public static volatile ListAttribute<Tutorpaciente, Telefono> telefonoList;
    public static volatile SingularAttribute<Tutorpaciente, String> direccion;
    public static volatile SingularAttribute<Tutorpaciente, Integer> id;
    public static volatile SingularAttribute<Tutorpaciente, String> nombre;
    public static volatile ListAttribute<Tutorpaciente, Paciente> pacienteList;

}