package Entidades;

import Entidades.Compra;
import Entidades.Ventas;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2019-11-05T17:51:40", comments="EclipseLink-2.7.4.v20190115-rNA")
@StaticMetamodel(LibroCompraVenta.class)
public class LibroCompraVenta_ { 

    public static volatile SingularAttribute<LibroCompraVenta, Date> fecha;
    public static volatile SingularAttribute<LibroCompraVenta, Float> total;
    public static volatile SingularAttribute<LibroCompraVenta, Float> comprastotal;
    public static volatile SingularAttribute<LibroCompraVenta, Float> ventastotal;
    public static volatile ListAttribute<LibroCompraVenta, Ventas> ventasList;
    public static volatile SingularAttribute<LibroCompraVenta, Integer> id;
    public static volatile ListAttribute<LibroCompraVenta, Compra> compraList;
    public static volatile SingularAttribute<LibroCompraVenta, String> perdidaGanancia;

}