package Entidades;

import Entidades.Historialpaciente;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Citanormal.class)
public class Citanormal_ { 

    public static volatile SingularAttribute<Citanormal, String> descripcion;
    public static volatile SingularAttribute<Citanormal, Date> fecha;
    public static volatile SingularAttribute<Citanormal, Integer> idCitaNormal;
    public static volatile SingularAttribute<Citanormal, Historialpaciente> historialPacienteidHistorialPaciente;

}