/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Citanormal;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Historialpaciente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nasc_
 */
public class CitanormalJpaController implements Serializable {

    public CitanormalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Citanormal citanormal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialpaciente historialPacienteidHistorialPaciente = citanormal.getHistorialPacienteidHistorialPaciente();
            if (historialPacienteidHistorialPaciente != null) {
                historialPacienteidHistorialPaciente = em.getReference(historialPacienteidHistorialPaciente.getClass(), historialPacienteidHistorialPaciente.getIdHistorialPaciente());
                citanormal.setHistorialPacienteidHistorialPaciente(historialPacienteidHistorialPaciente);
            }
            em.persist(citanormal);
            if (historialPacienteidHistorialPaciente != null) {
                historialPacienteidHistorialPaciente.getCitanormalList().add(citanormal);
                historialPacienteidHistorialPaciente = em.merge(historialPacienteidHistorialPaciente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Citanormal citanormal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Citanormal persistentCitanormal = em.find(Citanormal.class, citanormal.getIdCitaNormal());
            Historialpaciente historialPacienteidHistorialPacienteOld = persistentCitanormal.getHistorialPacienteidHistorialPaciente();
            Historialpaciente historialPacienteidHistorialPacienteNew = citanormal.getHistorialPacienteidHistorialPaciente();
            if (historialPacienteidHistorialPacienteNew != null) {
                historialPacienteidHistorialPacienteNew = em.getReference(historialPacienteidHistorialPacienteNew.getClass(), historialPacienteidHistorialPacienteNew.getIdHistorialPaciente());
                citanormal.setHistorialPacienteidHistorialPaciente(historialPacienteidHistorialPacienteNew);
            }
            citanormal = em.merge(citanormal);
            if (historialPacienteidHistorialPacienteOld != null && !historialPacienteidHistorialPacienteOld.equals(historialPacienteidHistorialPacienteNew)) {
                historialPacienteidHistorialPacienteOld.getCitanormalList().remove(citanormal);
                historialPacienteidHistorialPacienteOld = em.merge(historialPacienteidHistorialPacienteOld);
            }
            if (historialPacienteidHistorialPacienteNew != null && !historialPacienteidHistorialPacienteNew.equals(historialPacienteidHistorialPacienteOld)) {
                historialPacienteidHistorialPacienteNew.getCitanormalList().add(citanormal);
                historialPacienteidHistorialPacienteNew = em.merge(historialPacienteidHistorialPacienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = citanormal.getIdCitaNormal();
                if (findCitanormal(id) == null) {
                    throw new NonexistentEntityException("The citanormal with id " + id + " no longer exists.");
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
            Citanormal citanormal;
            try {
                citanormal = em.getReference(Citanormal.class, id);
                citanormal.getIdCitaNormal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citanormal with id " + id + " no longer exists.", enfe);
            }
            Historialpaciente historialPacienteidHistorialPaciente = citanormal.getHistorialPacienteidHistorialPaciente();
            if (historialPacienteidHistorialPaciente != null) {
                historialPacienteidHistorialPaciente.getCitanormalList().remove(citanormal);
                historialPacienteidHistorialPaciente = em.merge(historialPacienteidHistorialPaciente);
            }
            em.remove(citanormal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Citanormal> findCitanormalEntities() {
        return findCitanormalEntities(true, -1, -1);
    }

    public List<Citanormal> findCitanormalEntities(int maxResults, int firstResult) {
        return findCitanormalEntities(false, maxResults, firstResult);
    }

    private List<Citanormal> findCitanormalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Citanormal.class));
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

    public Citanormal findCitanormal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Citanormal.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitanormalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Citanormal> rt = cq.from(Citanormal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
