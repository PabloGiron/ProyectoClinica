package Entidades;

import Entidades.Servicio;
import Entidades.Ventas;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Detalleventa.class)
public class Detalleventa_ { 

    public static volatile SingularAttribute<Detalleventa, Servicio> servicioid;
    public static volatile SingularAttribute<Detalleventa, Float> subtotal;
    public static volatile SingularAttribute<Detalleventa, Ventas> ventasid;
    public static volatile SingularAttribute<Detalleventa, Integer> id;
    public static volatile SingularAttribute<Detalleventa, Integer> cantidad;

}