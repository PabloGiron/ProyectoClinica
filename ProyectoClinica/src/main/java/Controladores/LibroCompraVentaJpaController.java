/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Compra;
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
public class LibroCompraVentaJpaController implements Serializable {

    public LibroCompraVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LibroCompraVenta libroCompraVenta) {
        if (libroCompraVenta.getCompraList() == null) {
            libroCompraVenta.setCompraList(new ArrayList<Compra>());
        }
        if (libroCompraVenta.getVentasList() == null) {
            libroCompraVenta.setVentasList(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Compra> attachedCompraList = new ArrayList<Compra>();
            for (Compra compraListCompraToAttach : libroCompraVenta.getCompraList()) {
                compraListCompraToAttach = em.getReference(compraListCompraToAttach.getClass(), compraListCompraToAttach.getId());
                attachedCompraList.add(compraListCompraToAttach);
            }
            libroCompraVenta.setCompraList(attachedCompraList);
            List<Ventas> attachedVentasList = new ArrayList<Ventas>();
            for (Ventas ventasListVentasToAttach : libroCompraVenta.getVentasList()) {
                ventasListVentasToAttach = em.getReference(ventasListVentasToAttach.getClass(), ventasListVentasToAttach.getId());
                attachedVentasList.add(ventasListVentasToAttach);
            }
            libroCompraVenta.setVentasList(attachedVentasList);
            em.persist(libroCompraVenta);
            for (Compra compraListCompra : libroCompraVenta.getCompraList()) {
                LibroCompraVenta oldLibrocompraventaidOfCompraListCompra = compraListCompra.getLibrocompraventaid();
                compraListCompra.setLibrocompraventaid(libroCompraVenta);
                compraListCompra = em.merge(compraListCompra);
                if (oldLibrocompraventaidOfCompraListCompra != null) {
                    oldLibrocompraventaidOfCompraListCompra.getCompraList().remove(compraListCompra);
                    oldLibrocompraventaidOfCompraListCompra = em.merge(oldLibrocompraventaidOfCompraListCompra);
                }
            }
            for (Ventas ventasListVentas : libroCompraVenta.getVentasList()) {
                LibroCompraVenta oldLibrocompraventaidOfVentasListVentas = ventasListVentas.getLibrocompraventaid();
                ventasListVentas.setLibrocompraventaid(libroCompraVenta);
                ventasListVentas = em.merge(ventasListVentas);
                if (oldLibrocompraventaidOfVentasListVentas != null) {
                    oldLibrocompraventaidOfVentasListVentas.getVentasList().remove(ventasListVentas);
                    oldLibrocompraventaidOfVentasListVentas = em.merge(oldLibrocompraventaidOfVentasListVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LibroCompraVenta libroCompraVenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LibroCompraVenta persistentLibroCompraVenta = em.find(LibroCompraVenta.class, libroCompraVenta.getId());
            List<Compra> compraListOld = persistentLibroCompraVenta.getCompraList();
            List<Compra> compraListNew = libroCompraVenta.getCompraList();
            List<Ventas> ventasListOld = persistentLibroCompraVenta.getVentasList();
            List<Ventas> ventasListNew = libroCompraVenta.getVentasList();
            List<String> illegalOrphanMessages = null;
            for (Compra compraListOldCompra : compraListOld) {
                if (!compraListNew.contains(compraListOldCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compra " + compraListOldCompra + " since its librocompraventaid field is not nullable.");
                }
            }
            for (Ventas ventasListOldVentas : ventasListOld) {
                if (!ventasListNew.contains(ventasListOldVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ventas " + ventasListOldVentas + " since its librocompraventaid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Compra> attachedCompraListNew = new ArrayList<Compra>();
            for (Compra compraListNewCompraToAttach : compraListNew) {
                compraListNewCompraToAttach = em.getReference(compraListNewCompraToAttach.getClass(), compraListNewCompraToAttach.getId());
                attachedCompraListNew.add(compraListNewCompraToAttach);
            }
            compraListNew = attachedCompraListNew;
            libroCompraVenta.setCompraList(compraListNew);
            List<Ventas> attachedVentasListNew = new ArrayList<Ventas>();
            for (Ventas ventasListNewVentasToAttach : ventasListNew) {
                ventasListNewVentasToAttach = em.getReference(ventasListNewVentasToAttach.getClass(), ventasListNewVentasToAttach.getId());
                attachedVentasListNew.add(ventasListNewVentasToAttach);
            }
            ventasListNew = attachedVentasListNew;
            libroCompraVenta.setVentasList(ventasListNew);
            libroCompraVenta = em.merge(libroCompraVenta);
            for (Compra compraListNewCompra : compraListNew) {
                if (!compraListOld.contains(compraListNewCompra)) {
                    LibroCompraVenta oldLibrocompraventaidOfCompraListNewCompra = compraListNewCompra.getLibrocompraventaid();
                    compraListNewCompra.setLibrocompraventaid(libroCompraVenta);
                    compraListNewCompra = em.merge(compraListNewCompra);
                    if (oldLibrocompraventaidOfCompraListNewCompra != null && !oldLibrocompraventaidOfCompraListNewCompra.equals(libroCompraVenta)) {
                        oldLibrocompraventaidOfCompraListNewCompra.getCompraList().remove(compraListNewCompra);
                        oldLibrocompraventaidOfCompraListNewCompra = em.merge(oldLibrocompraventaidOfCompraListNewCompra);
                    }
                }
            }
            for (Ventas ventasListNewVentas : ventasListNew) {
                if (!ventasListOld.contains(ventasListNewVentas)) {
                    LibroCompraVenta oldLibrocompraventaidOfVentasListNewVentas = ventasListNewVentas.getLibrocompraventaid();
                    ventasListNewVentas.setLibrocompraventaid(libroCompraVenta);
                    ventasListNewVentas = em.merge(ventasListNewVentas);
                    if (oldLibrocompraventaidOfVentasListNewVentas != null && !oldLibrocompraventaidOfVentasListNewVentas.equals(libroCompraVenta)) {
                        oldLibrocompraventaidOfVentasListNewVentas.getVentasList().remove(ventasListNewVentas);
                        oldLibrocompraventaidOfVentasListNewVentas = em.merge(oldLibrocompraventaidOfVentasListNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = libroCompraVenta.getId();
                if (findLibroCompraVenta(id) == null) {
                    throw new NonexistentEntityException("The libroCompraVenta with id " + id + " no longer exists.");
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
            LibroCompraVenta libroCompraVenta;
            try {
                libroCompraVenta = em.getReference(LibroCompraVenta.class, id);
                libroCompraVenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libroCompraVenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Compra> compraListOrphanCheck = libroCompraVenta.getCompraList();
            for (Compra compraListOrphanCheckCompra : compraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LibroCompraVenta (" + libroCompraVenta + ") cannot be destroyed since the Compra " + compraListOrphanCheckCompra + " in its compraList field has a non-nullable librocompraventaid field.");
            }
            List<Ventas> ventasListOrphanCheck = libroCompraVenta.getVentasList();
            for (Ventas ventasListOrphanCheckVentas : ventasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This LibroCompraVenta (" + libroCompraVenta + ") cannot be destroyed since the Ventas " + ventasListOrphanCheckVentas + " in its ventasList field has a non-nullable librocompraventaid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(libroCompraVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LibroCompraVenta> findLibroCompraVentaEntities() {
        return findLibroCompraVentaEntities(true, -1, -1);
    }

    public List<LibroCompraVenta> findLibroCompraVentaEntities(int maxResults, int firstResult) {
        return findLibroCompraVentaEntities(false, maxResults, firstResult);
    }

    private List<LibroCompraVenta> findLibroCompraVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LibroCompraVenta.class));
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

    public LibroCompraVenta findLibroCompraVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LibroCompraVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibroCompraVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LibroCompraVenta> rt = cq.from(LibroCompraVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
