/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Citaortodoncia;
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
public class CitaortodonciaJpaController implements Serializable {

    public CitaortodonciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Citaortodoncia citaortodoncia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialpaciente historialPacienteidHistorialPaciente = citaortodoncia.getHistorialPacienteidHistorialPaciente();
            if (historialPacienteidHistorialPaciente != null) {
                historialPacienteidHistorialPaciente = em.getReference(historialPacienteidHistorialPaciente.getClass(), historialPacienteidHistorialPaciente.getIdHistorialPaciente());
                citaortodoncia.setHistorialPacienteidHistorialPaciente(historialPacienteidHistorialPaciente);
            }
            em.persist(citaortodoncia);
            if (historialPacienteidHistorialPaciente != null) {
                historialPacienteidHistorialPaciente.getCitaortodonciaList().add(citaortodoncia);
                historialPacienteidHistorialPaciente = em.merge(historialPacienteidHistorialPaciente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Citaortodoncia citaortodoncia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Citaortodoncia persistentCitaortodoncia = em.find(Citaortodoncia.class, citaortodoncia.getIdCitaOrtodoncia());
            Historialpaciente historialPacienteidHistorialPacienteOld = persistentCitaortodoncia.getHistorialPacienteidHistorialPaciente();
            Historialpaciente historialPacienteidHistorialPacienteNew = citaortodoncia.getHistorialPacienteidHistorialPaciente();
            if (historialPacienteidHistorialPacienteNew != null) {
                historialPacienteidHistorialPacienteNew = em.getReference(historialPacienteidHistorialPacienteNew.getClass(), historialPacienteidHistorialPacienteNew.getIdHistorialPaciente());
                citaortodoncia.setHistorialPacienteidHistorialPaciente(historialPacienteidHistorialPacienteNew);
            }
            citaortodoncia = em.merge(citaortodoncia);
            if (historialPacienteidHistorialPacienteOld != null && !historialPacienteidHistorialPacienteOld.equals(historialPacienteidHistorialPacienteNew)) {
                historialPacienteidHistorialPacienteOld.getCitaortodonciaList().remove(citaortodoncia);
                historialPacienteidHistorialPacienteOld = em.merge(historialPacienteidHistorialPacienteOld);
            }
            if (historialPacienteidHistorialPacienteNew != null && !historialPacienteidHistorialPacienteNew.equals(historialPacienteidHistorialPacienteOld)) {
                historialPacienteidHistorialPacienteNew.getCitaortodonciaList().add(citaortodoncia);
                historialPacienteidHistorialPacienteNew = em.merge(historialPacienteidHistorialPacienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = citaortodoncia.getIdCitaOrtodoncia();
                if (findCitaortodoncia(id) == null) {
                    throw new NonexistentEntityException("The citaortodoncia with id " + id + " no longer exists.");
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
            Citaortodoncia citaortodoncia;
            try {
                citaortodoncia = em.getReference(Citaortodoncia.class, id);
                citaortodoncia.getIdCitaOrtodoncia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citaortodoncia with id " + id + " no longer exists.", enfe);
            }
            Historialpaciente historialPacienteidHistorialPaciente = citaortodoncia.getHistorialPacienteidHistorialPaciente();
            if (historialPacienteidHistorialPaciente != null) {
                historialPacienteidHistorialPaciente.getCitaortodonciaList().remove(citaortodoncia);
                historialPacienteidHistorialPaciente = em.merge(historialPacienteidHistorialPaciente);
            }
            em.remove(citaortodoncia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Citaortodoncia> findCitaortodonciaEntities() {
        return findCitaortodonciaEntities(true, -1, -1);
    }

    public List<Citaortodoncia> findCitaortodonciaEntities(int maxResults, int firstResult) {
        return findCitaortodonciaEntities(false, maxResults, firstResult);
    }

    private List<Citaortodoncia> findCitaortodonciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Citaortodoncia.class));
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

    public Citaortodoncia findCitaortodoncia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Citaortodoncia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitaortodonciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Citaortodoncia> rt = cq.from(Citaortodoncia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
