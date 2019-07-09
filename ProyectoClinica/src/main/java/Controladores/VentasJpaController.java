package Controladores;

import Entidades.Detalleventa;
import Entidades.LibroCompraVenta;
import Entidades.Ventas;
import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nasc_
 */
public class VentasJpaController implements Serializable {

    public VentasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ventas ventas) {
        if (ventas.getDetalleventaList() == null) {
            ventas.setDetalleventaList(new ArrayList<Detalleventa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LibroCompraVenta librocompraventaid = ventas.getLibrocompraventaid();
            if (librocompraventaid != null) {
                librocompraventaid = em.getReference(librocompraventaid.getClass(), librocompraventaid.getId());
                ventas.setLibrocompraventaid(librocompraventaid);
            }
            List<Detalleventa> attachedDetalleventaList = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaListDetalleventaToAttach : ventas.getDetalleventaList()) {
                detalleventaListDetalleventaToAttach = em.getReference(detalleventaListDetalleventaToAttach.getClass(), detalleventaListDetalleventaToAttach.getId());
                attachedDetalleventaList.add(detalleventaListDetalleventaToAttach);
            }
            ventas.setDetalleventaList(attachedDetalleventaList);
            em.persist(ventas);
            if (librocompraventaid != null) {
                librocompraventaid.getVentasList().add(ventas);
                librocompraventaid = em.merge(librocompraventaid);
            }
            for (Detalleventa detalleventaListDetalleventa : ventas.getDetalleventaList()) {
                Ventas oldVentasidOfDetalleventaListDetalleventa = detalleventaListDetalleventa.getVentasid();
                detalleventaListDetalleventa.setVentasid(ventas);
                detalleventaListDetalleventa = em.merge(detalleventaListDetalleventa);
                if (oldVentasidOfDetalleventaListDetalleventa != null) {
                    oldVentasidOfDetalleventaListDetalleventa.getDetalleventaList().remove(detalleventaListDetalleventa);
                    oldVentasidOfDetalleventaListDetalleventa = em.merge(oldVentasidOfDetalleventaListDetalleventa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ventas ventas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventas persistentVentas = em.find(Ventas.class, ventas.getId());
            LibroCompraVenta librocompraventaidOld = persistentVentas.getLibrocompraventaid();
            LibroCompraVenta librocompraventaidNew = ventas.getLibrocompraventaid();
            List<Detalleventa> detalleventaListOld = persistentVentas.getDetalleventaList();
            List<Detalleventa> detalleventaListNew = ventas.getDetalleventaList();
            List<String> illegalOrphanMessages = null;
            for (Detalleventa detalleventaListOldDetalleventa : detalleventaListOld) {
                if (!detalleventaListNew.contains(detalleventaListOldDetalleventa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleventa " + detalleventaListOldDetalleventa + " since its ventasid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (librocompraventaidNew != null) {
                librocompraventaidNew = em.getReference(librocompraventaidNew.getClass(), librocompraventaidNew.getId());
                ventas.setLibrocompraventaid(librocompraventaidNew);
            }
            List<Detalleventa> attachedDetalleventaListNew = new ArrayList<Detalleventa>();
            for (Detalleventa detalleventaListNewDetalleventaToAttach : detalleventaListNew) {
                detalleventaListNewDetalleventaToAttach = em.getReference(detalleventaListNewDetalleventaToAttach.getClass(), detalleventaListNewDetalleventaToAttach.getId());
                attachedDetalleventaListNew.add(detalleventaListNewDetalleventaToAttach);
            }
            detalleventaListNew = attachedDetalleventaListNew;
            ventas.setDetalleventaList(detalleventaListNew);
            ventas = em.merge(ventas);
            if (librocompraventaidOld != null && !librocompraventaidOld.equals(librocompraventaidNew)) {
                librocompraventaidOld.getVentasList().remove(ventas);
                librocompraventaidOld = em.merge(librocompraventaidOld);
            }
            if (librocompraventaidNew != null && !librocompraventaidNew.equals(librocompraventaidOld)) {
                librocompraventaidNew.getVentasList().add(ventas);
                librocompraventaidNew = em.merge(librocompraventaidNew);
            }
            for (Detalleventa detalleventaListNewDetalleventa : detalleventaListNew) {
                if (!detalleventaListOld.contains(detalleventaListNewDetalleventa)) {
                    Ventas oldVentasidOfDetalleventaListNewDetalleventa = detalleventaListNewDetalleventa.getVentasid();
                    detalleventaListNewDetalleventa.setVentasid(ventas);
                    detalleventaListNewDetalleventa = em.merge(detalleventaListNewDetalleventa);
                    if (oldVentasidOfDetalleventaListNewDetalleventa != null && !oldVentasidOfDetalleventaListNewDetalleventa.equals(ventas)) {
                        oldVentasidOfDetalleventaListNewDetalleventa.getDetalleventaList().remove(detalleventaListNewDetalleventa);
                        oldVentasidOfDetalleventaListNewDetalleventa = em.merge(oldVentasidOfDetalleventaListNewDetalleventa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ventas.getId();
                if (findVentas(id) == null) {
                    throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventas ventas;
            try {
                ventas = em.getReference(Ventas.class, id);
                ventas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleventa> detalleventaListOrphanCheck = ventas.getDetalleventaList();
            for (Detalleventa detalleventaListOrphanCheckDetalleventa : detalleventaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ventas (" + ventas + ") cannot be destroyed since the Detalleventa " + detalleventaListOrphanCheckDetalleventa + " in its detalleventaList field has a non-nullable ventasid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            LibroCompraVenta librocompraventaid = ventas.getLibrocompraventaid();
            if (librocompraventaid != null) {
                librocompraventaid.getVentasList().remove(ventas);
                librocompraventaid = em.merge(librocompraventaid);
            }
            em.remove(ventas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ventas> findVentasEntities() {
        return findVentasEntities(true, -1, -1);
    }

    public List<Ventas> findVentasEntities(int maxResults, int firstResult) {
        return findVentasEntities(false, maxResults, firstResult);
    }

    private List<Ventas> findVentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ventas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ventas findVentas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ventas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ventas> rt = cq.from(Ventas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public void updateTotal(Ventas ventas){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            Query updateVentas = em.createNativeQuery("UPDATE Ventas SET Ventas.Total = ? WHERE Ventas.id = ?");
            updateVentas.setParameter(1, ventas.getTotal());
            updateVentas.setParameter(2, ventas.getId());
            updateVentas.executeUpdate();
            em.getTransaction().commit();
        }finally {
            if (em != null) {
                em.close();
            }
        }
    } 
}
