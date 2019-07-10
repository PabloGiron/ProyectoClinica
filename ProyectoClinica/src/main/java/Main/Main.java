package Main;

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
        Query v = em.createNativeQuery("SELECT SUM(Subtotal) FROM detalleventa WHERE detalleventa.Ventas_id = ?");
        v.setParameter(1, 1);
        double tot = (Double) v.getSingleResult();
        System.out.println(tot);
    }
}
