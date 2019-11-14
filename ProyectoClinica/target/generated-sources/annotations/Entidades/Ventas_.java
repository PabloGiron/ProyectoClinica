package Entidades;

import Entidades.Detalleventa;
import Entidades.LibroCompraVenta;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Ventas.class)
public class Ventas_ { 

    public static volatile ListAttribute<Ventas, Detalleventa> detalleventaList;
    public static volatile SingularAttribute<Ventas, Date> fecha;
    public static volatile SingularAttribute<Ventas, Float> total;
    public static volatile SingularAttribute<Ventas, String> numero;
    public static volatile SingularAttribute<Ventas, Integer> id;
    public static volatile SingularAttribute<Ventas, LibroCompraVenta> librocompraventaid;

}