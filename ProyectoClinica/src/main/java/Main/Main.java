package Main;

import Entidades.Detalleventa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import Entidades.Servicio;
import Entidades.Ventas;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author oem
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Clinica");
        EntityManager em = emf.createEntityManager();
        /*EntityManager emVentas = emf.createEntityManager();
        TypedQuery<Servicio> query = em.createNamedQuery("Servicio.findByNombre", Servicio.class);
        query.setParameter("nombre", "Extracción");
        List<Servicio> listaDatos = query.getResultList();
        for(Servicio s : listaDatos){
            //System.out.println(s.getPrecio());
        }
        
        Query queryVentas = emVentas.createNamedQuery("Ventas.idMax", Ventas.class);
        int i = (Integer)queryVentas.getSingleResult();
        System.out.println(i);
        em.close();
        emVentas.close();*/
        Query v = em.createNativeQuery("SELECT servicio.Nombre, detalleventa.Cantidad, detalleventa.Subtotal FROM Servicio INNER JOIN detalleventa ON Servicio.id = detalleventa.Servicio_id WHERE detalleventa.Ventas_id = ?;");
        v.setParameter(1, 1);
        List<Object[]> listaDatos = v.getResultList();
        for(Object[] lista : listaDatos){
            System.out.println(lista[0] + " " + lista[1] + " " + lista[2]);
        }
        em.close();
    }
}
