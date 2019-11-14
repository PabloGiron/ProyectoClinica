package Entidades;

import Entidades.Detalleventa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile ListAttribute<Servicio, Detalleventa> detalleventaList;
    public static volatile SingularAttribute<Servicio, Float> precio;
    public static volatile SingularAttribute<Servicio, Integer> id;
    public static volatile SingularAttribute<Servicio, String> nombre;

}