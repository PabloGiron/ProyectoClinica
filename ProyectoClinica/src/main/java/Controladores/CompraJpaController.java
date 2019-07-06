/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Compra;
import Entidades.Detallecompra;
import Entidades.LibroCompraVenta;
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
public class CompraJpaController implements Serializable {

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) {
        if (compra.getDetallecompraList() == null) {
            compra.setDetallecompraList(new ArrayList<Detallecompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LibroCompraVenta librocompraventaid = compra.getLibrocompraventaid();
            if (librocompraventaid != null) {
                librocompraventaid = em.getReference(librocompraventaid.getClass(), librocompraventaid.getId());
                compra.setLibrocompraventaid(librocompraventaid);
            }
            List<Detallecompra> attachedDetallecompraList = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListDetallecompraToAttach : compra.getDetallecompraList()) {
                detallecompraListDetallecompraToAttach = em.getReference(detallecompraListDetallecompraToAttach.getClass(), detallecompraListDetallecompraToAttach.getId());
                attachedDetallecompraList.add(detallecompraListDetallecompraToAttach);
            }
            compra.setDetallecompraList(attachedDetallecompraList);
            em.persist(compra);
            if (librocompraventaid != null) {
                librocompraventaid.getCompraList().add(compra);
                librocompraventaid = em.merge(librocompraventaid);
            }
            for (Detallecompra detallecompraListDetallecompra : compra.getDetallecompraList()) {
                Compra oldCompraidOfDetallecompraListDetallecompra = detallecompraListDetallecompra.getCompraid();
                detallecompraListDetallecompra.setCompraid(compra);
                detallecompraListDetallecompra = em.merge(detallecompraListDetallecompra);
                if (oldCompraidOfDetallecompraListDetallecompra != null) {
                    oldCompraidOfDetallecompraListDetallecompra.getDetallecompraList().remove(detallecompraListDetallecompra);
                    oldCompraidOfDetallecompraListDetallecompra = em.merge(oldCompraidOfDetallecompraListDetallecompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getId());
            LibroCompraVenta librocompraventaidOld = persistentCompra.getLibrocompraventaid();
            LibroCompraVenta librocompraventaidNew = compra.getLibrocompraventaid();
            List<Detallecompra> detallecompraListOld = persistentCompra.getDetallecompraList();
            List<Detallecompra> detallecompraListNew = compra.getDetallecompraList();
            List<String> illegalOrphanMessages = null;
            for (Detallecompra detallecompraListOldDetallecompra : detallecompraListOld) {
                if (!detallecompraListNew.contains(detallecompraListOldDetallecompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompra " + detallecompraListOldDetallecompra + " since its compraid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (librocompraventaidNew != null) {
                librocompraventaidNew = em.getReference(librocompraventaidNew.getClass(), librocompraventaidNew.getId());
                compra.setLibrocompraventaid(librocompraventaidNew);
            }
            List<Detallecompra> attachedDetallecompraListNew = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListNewDetallecompraToAttach : detallecompraListNew) {
                detallecompraListNewDetallecompraToAttach = em.getReference(detallecompraListNewDetallecompraToAttach.getClass(), detallecompraListNewDetallecompraToAttach.getId());
                attachedDetallecompraListNew.add(detallecompraListNewDetallecompraToAttach);
            }
            detallecompraListNew = attachedDetallecompraListNew;
            compra.setDetallecompraList(detallecompraListNew);
            compra = em.merge(compra);
            if (librocompraventaidOld != null && !librocompraventaidOld.equals(librocompraventaidNew)) {
                librocompraventaidOld.getCompraList().remove(compra);
                librocompraventaidOld = em.merge(librocompraventaidOld);
            }
            if (librocompraventaidNew != null && !librocompraventaidNew.equals(librocompraventaidOld)) {
                librocompraventaidNew.getCompraList().add(compra);
                librocompraventaidNew = em.merge(librocompraventaidNew);
            }
            for (Detallecompra detallecompraListNewDetallecompra : detallecompraListNew) {
                if (!detallecompraListOld.contains(detallecompraListNewDetallecompra)) {
                    Compra oldCompraidOfDetallecompraListNewDetallecompra = detallecompraListNewDetallecompra.getCompraid();
                    detallecompraListNewDetallecompra.setCompraid(compra);
                    detallecompraListNewDetallecompra = em.merge(detallecompraListNewDetallecompra);
                    if (oldCompraidOfDetallecompraListNewDetallecompra != null && !oldCompraidOfDetallecompraListNewDetallecompra.equals(compra)) {
                        oldCompraidOfDetallecompraListNewDetallecompra.getDetallecompraList().remove(detallecompraListNewDetallecompra);
                        oldCompraidOfDetallecompraListNewDetallecompra = em.merge(oldCompraidOfDetallecompraListNewDetallecompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compra.getId();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallecompra> detallecompraListOrphanCheck = compra.getDetallecompraList();
            for (Detallecompra detallecompraListOrphanCheckDetallecompra : detallecompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compra (" + compra + ") cannot be destroyed since the Detallecompra " + detallecompraListOrphanCheckDetallecompra + " in its detallecompraList field has a non-nullable compraid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            LibroCompraVenta librocompraventaid = compra.getLibrocompraventaid();
            if (librocompraventaid != null) {
                librocompraventaid.getCompraList().remove(compra);
                librocompraventaid = em.merge(librocompraventaid);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
