package Entidades;

import Entidades.Citanormal;
import Entidades.Citaortodoncia;
import Entidades.Paciente;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Historialpaciente.class)
public class Historialpaciente_ { 

    public static volatile SingularAttribute<Historialpaciente, Integer> idHistorialPaciente;
    public static volatile SingularAttribute<Historialpaciente, Date> fechaInicio;
    public static volatile ListAttribute<Historialpaciente, Citanormal> citanormalList;
    public static volatile ListAttribute<Historialpaciente, Citaortodoncia> citaortodonciaList;
    public static volatile SingularAttribute<Historialpaciente, Paciente> pacienteid;
    public static volatile SingularAttribute<Historialpaciente, Date> fechaFin;

}