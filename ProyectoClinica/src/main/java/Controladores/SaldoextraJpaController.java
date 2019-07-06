/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Empleados;
import Entidades.Saldoextra;
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
public class SaldoextraJpaController implements Serializable {

    public SaldoextraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Saldoextra saldoextra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados empleadosid = saldoextra.getEmpleadosid();
            if (empleadosid != null) {
                empleadosid = em.getReference(empleadosid.getClass(), empleadosid.getId());
                saldoextra.setEmpleadosid(empleadosid);
            }
            em.persist(saldoextra);
            if (empleadosid != null) {
                empleadosid.getSaldoextraList().add(saldoextra);
                empleadosid = em.merge(empleadosid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Saldoextra saldoextra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Saldoextra persistentSaldoextra = em.find(Saldoextra.class, saldoextra.getId());
            Empleados empleadosidOld = persistentSaldoextra.getEmpleadosid();
            Empleados empleadosidNew = saldoextra.getEmpleadosid();
            if (empleadosidNew != null) {
                empleadosidNew = em.getReference(empleadosidNew.getClass(), empleadosidNew.getId());
                saldoextra.setEmpleadosid(empleadosidNew);
            }
            saldoextra = em.merge(saldoextra);
            if (empleadosidOld != null && !empleadosidOld.equals(empleadosidNew)) {
                empleadosidOld.getSaldoextraList().remove(saldoextra);
                empleadosidOld = em.merge(empleadosidOld);
            }
            if (empleadosidNew != null && !empleadosidNew.equals(empleadosidOld)) {
                empleadosidNew.getSaldoextraList().add(saldoextra);
                empleadosidNew = em.merge(empleadosidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = saldoextra.getId();
                if (findSaldoextra(id) == null) {
                    throw new NonexistentEntityException("The saldoextra with id " + id + " no longer exists.");
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
            Saldoextra saldoextra;
            try {
                saldoextra = em.getReference(Saldoextra.class, id);
                saldoextra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The saldoextra with id " + id + " no longer exists.", enfe);
            }
            Empleados empleadosid = saldoextra.getEmpleadosid();
            if (empleadosid != null) {
                empleadosid.getSaldoextraList().remove(saldoextra);
                empleadosid = em.merge(empleadosid);
            }
            em.remove(saldoextra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Saldoextra> findSaldoextraEntities() {
        return findSaldoextraEntities(true, -1, -1);
    }

    public List<Saldoextra> findSaldoextraEntities(int maxResults, int firstResult) {
        return findSaldoextraEntities(false, maxResults, firstResult);
    }

    private List<Saldoextra> findSaldoextraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Saldoextra.class));
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

    public Saldoextra findSaldoextra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Saldoextra.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaldoextraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Saldoextra> rt = cq.from(Saldoextra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
