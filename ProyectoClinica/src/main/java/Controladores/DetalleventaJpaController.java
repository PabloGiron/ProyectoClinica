/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Detalleventa;
import Entidades.Servicio;
import Entidades.Ventas;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author nasc_
 */
public class DetalleventaJpaController implements Serializable {

    public DetalleventaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleventa detalleventa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicioid = detalleventa.getServicioid();
            if (servicioid != null) {
                servicioid = em.getReference(servicioid.getClass(), servicioid.getId());
                detalleventa.setServicioid(servicioid);
            }
            Ventas ventasid = detalleventa.getVentasid();
            if (ventasid != null) {
                ventasid = em.getReference(ventasid.getClass(), ventasid.getId());
                detalleventa.setVentasid(ventasid);
            }
            em.persist(detalleventa);
            if (servicioid != null) {
                servicioid.getDetalleventaList().add(detalleventa);
                servicioid = em.merge(servicioid);
            }
            if (ventasid != null) {
                ventasid.getDetalleventaList().add(detalleventa);
                ventasid = em.merge(ventasid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleventa detalleventa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleventa persistentDetalleventa = em.find(Detalleventa.class, detalleventa.getId());
            Servicio servicioidOld = persistentDetalleventa.getServicioid();
            Servicio servicioidNew = detalleventa.getServicioid();
            Ventas ventasidOld = persistentDetalleventa.getVentasid();
            Ventas ventasidNew = detalleventa.getVentasid();
            if (servicioidNew != null) {
                servicioidNew = em.getReference(servicioidNew.getClass(), servicioidNew.getId());
                detalleventa.setServicioid(servicioidNew);
            }
            if (ventasidNew != null) {
                ventasidNew = em.getReference(ventasidNew.getClass(), ventasidNew.getId());
                detalleventa.setVentasid(ventasidNew);
            }
            detalleventa = em.merge(detalleventa);
            if (servicioidOld != null && !servicioidOld.equals(servicioidNew)) {
                servicioidOld.getDetalleventaList().remove(detalleventa);
                servicioidOld = em.merge(servicioidOld);
            }
            if (servicioidNew != null && !servicioidNew.equals(servicioidOld)) {
                servicioidNew.getDetalleventaList().add(detalleventa);
                servicioidNew = em.merge(servicioidNew);
            }
            if (ventasidOld != null && !ventasidOld.equals(ventasidNew)) {
                ventasidOld.getDetalleventaList().remove(detalleventa);
                ventasidOld = em.merge(ventasidOld);
            }
            if (ventasidNew != null && !ventasidNew.equals(ventasidOld)) {
                ventasidNew.getDetalleventaList().add(detalleventa);
                ventasidNew = em.merge(ventasidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleventa.getId();
                if (findDetalleventa(id) == null) {
                    throw new NonexistentEntityException("The detalleventa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleventa detalleventa;
            try {
                detalleventa = em.getReference(Detalleventa.class, id);
                detalleventa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleventa with id " + id + " no longer exists.", enfe);
            }
            Servicio servicioid = detalleventa.getServicioid();
            if (servicioid != null) {
                servicioid.getDetalleventaList().remove(detalleventa);
                servicioid = em.merge(servicioid);
            }
            Ventas ventasid = detalleventa.getVentasid();
            if (ventasid != null) {
                ventasid.getDetalleventaList().remove(detalleventa);
                ventasid = em.merge(ventasid);
            }
            em.remove(detalleventa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleventa> findDetalleventaEntities() {
        return findDetalleventaEntities(true, -1, -1);
    }

    public List<Detalleventa> findDetalleventaEntities(int maxResults, int firstResult) {
        return findDetalleventaEntities(false, maxResults, firstResult);
    }

    private List<Detalleventa> findDetalleventaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleventa.class));
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

    public Detalleventa findDetalleventa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleventa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleventaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleventa> rt = cq.from(Detalleventa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
