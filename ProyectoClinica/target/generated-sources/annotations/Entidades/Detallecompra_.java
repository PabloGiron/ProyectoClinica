package Entidades;

import Entidades.Compra;
import Entidades.Producto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(Detallecompra.class)
public class Detallecompra_ { 

    public static volatile SingularAttribute<Detallecompra, Float> subtotal;
    public static volatile SingularAttribute<Detallecompra, Producto> productoid;
    public static volatile SingularAttribute<Detallecompra, Integer> id;
    public static volatile SingularAttribute<Detallecompra, Integer> cantidad;
    public static volatile SingularAttribute<Detallecompra, Compra> compraid;

}