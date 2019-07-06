/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Empleados;
import Entidades.Telefonos;
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
public class TelefonosJpaController implements Serializable {

    public TelefonosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Telefonos telefonos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados empleadosid = telefonos.getEmpleadosid();
            if (empleadosid != null) {
                empleadosid = em.getReference(empleadosid.getClass(), empleadosid.getId());
                telefonos.setEmpleadosid(empleadosid);
            }
            em.persist(telefonos);
            if (empleadosid != null) {
                empleadosid.getTelefonosList().add(telefonos);
                empleadosid = em.merge(empleadosid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Telefonos telefonos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Telefonos persistentTelefonos = em.find(Telefonos.class, telefonos.getId());
            Empleados empleadosidOld = persistentTelefonos.getEmpleadosid();
            Empleados empleadosidNew = telefonos.getEmpleadosid();
            if (empleadosidNew != null) {
                empleadosidNew = em.getReference(empleadosidNew.getClass(), empleadosidNew.getId());
                telefonos.setEmpleadosid(empleadosidNew);
            }
            telefonos = em.merge(telefonos);
            if (empleadosidOld != null && !empleadosidOld.equals(empleadosidNew)) {
                empleadosidOld.getTelefonosList().remove(telefonos);
                empleadosidOld = em.merge(empleadosidOld);
            }
            if (empleadosidNew != null && !empleadosidNew.equals(empleadosidOld)) {
                empleadosidNew.getTelefonosList().add(telefonos);
                empleadosidNew = em.merge(empleadosidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = telefonos.getId();
                if (findTelefonos(id) == null) {
                    throw new NonexistentEntityException("The telefonos with id " + id + " no longer exists.");
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
            Telefonos telefonos;
            try {
                telefonos = em.getReference(Telefonos.class, id);
                telefonos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The telefonos with id " + id + " no longer exists.", enfe);
            }
            Empleados empleadosid = telefonos.getEmpleadosid();
            if (empleadosid != null) {
                empleadosid.getTelefonosList().remove(telefonos);
                empleadosid = em.merge(empleadosid);
            }
            em.remove(telefonos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Telefonos> findTelefonosEntities() {
        return findTelefonosEntities(true, -1, -1);
    }

    public List<Telefonos> findTelefonosEntities(int maxResults, int firstResult) {
        return findTelefonosEntities(false, maxResults, firstResult);
    }

    private List<Telefonos> findTelefonosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Telefonos.class));
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

    public Telefonos findTelefonos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Telefonos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelefonosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Telefonos> rt = cq.from(Telefonos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
