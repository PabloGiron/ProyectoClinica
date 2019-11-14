package Entidades;

import Entidades.Historialpaciente;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Citaortodoncia.class)
public class Citaortodoncia_ { 

    public static volatile SingularAttribute<Citaortodoncia, String> descripcion;
    public static volatile SingularAttribute<Citaortodoncia, Date> fecha;
    public static volatile SingularAttribute<Citaortodoncia, Float> precio;
    public static volatile SingularAttribute<Citaortodoncia, Short> pagado;
    public static volatile SingularAttribute<Citaortodoncia, Historialpaciente> historialPacienteidHistorialPaciente;
    public static volatile SingularAttribute<Citaortodoncia, Integer> idCitaOrtodoncia;

}