/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Tutorpaciente;
import Entidades.Empleados;
import Entidades.Telefono;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nasc_
 */
public class TelefonoJpaController implements Serializable {

    public TelefonoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Telefono telefono) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutorpaciente tutorPacienteid = telefono.getTutorPacienteid();
            if (tutorPacienteid != null) {
                tutorPacienteid = em.getReference(tutorPacienteid.getClass(), tutorPacienteid.getId());
                telefono.setTutorPacienteid(tutorPacienteid);
            }
            Empleados empleadosid = telefono.getEmpleadosid();
            if (empleadosid != null) {
                empleadosid = em.getReference(empleadosid.getClass(), empleadosid.getId());
                telefono.setEmpleadosid(empleadosid);
            }
            em.persist(telefono);
            if (tutorPacienteid != null) {
                tutorPacienteid.getTelefonoList().add(telefono);
                tutorPacienteid = em.merge(tutorPacienteid);
            }
            if (empleadosid != null) {
                empleadosid.getTelefonoList().add(telefono);
                empleadosid = em.merge(empleadosid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Telefono telefono) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Telefono persistentTelefono = em.find(Telefono.class, telefono.getId());
            Tutorpaciente tutorPacienteidOld = persistentTelefono.getTutorPacienteid();
            Tutorpaciente tutorPacienteidNew = telefono.getTutorPacienteid();
            Empleados empleadosidOld = persistentTelefono.getEmpleadosid();
            Empleados empleadosidNew = telefono.getEmpleadosid();
            if (tutorPacienteidNew != null) {
                tutorPacienteidNew = em.getReference(tutorPacienteidNew.getClass(), tutorPacienteidNew.getId());
                telefono.setTutorPacienteid(tutorPacienteidNew);
            }
            if (empleadosidNew != null) {
                empleadosidNew = em.getReference(empleadosidNew.getClass(), empleadosidNew.getId());
                telefono.setEmpleadosid(empleadosidNew);
            }
            telefono = em.merge(telefono);
            if (tutorPacienteidOld != null && !tutorPacienteidOld.equals(tutorPacienteidNew)) {
                tutorPacienteidOld.getTelefonoList().remove(telefono);
                tutorPacienteidOld = em.merge(tutorPacienteidOld);
            }
            if (tutorPacienteidNew != null && !tutorPacienteidNew.equals(tutorPacienteidOld)) {
                tutorPacienteidNew.getTelefonoList().add(telefono);
                tutorPacienteidNew = em.merge(tutorPacienteidNew);
            }
            if (empleadosidOld != null && !empleadosidOld.equals(empleadosidNew)) {
                empleadosidOld.getTelefonoList().remove(telefono);
                empleadosidOld = em.merge(empleadosidOld);
            }
            if (empleadosidNew != null && !empleadosidNew.equals(empleadosidOld)) {
                empleadosidNew.getTelefonoList().add(telefono);
                empleadosidNew = em.merge(empleadosidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = telefono.getId();
                if (findTelefono(id) == null) {
                    throw new NonexistentEntityException("The telefono with id " + id + " no longer exists.");
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
            Telefono telefono;
            try {
                telefono = em.getReference(Telefono.class, id);
                telefono.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The telefono with id " + id + " no longer exists.", enfe);
            }
            Tutorpaciente tutorPacienteid = telefono.getTutorPacienteid();
            if (tutorPacienteid != null) {
                tutorPacienteid.getTelefonoList().remove(telefono);
                tutorPacienteid = em.merge(tutorPacienteid);
            }
            Empleados empleadosid = telefono.getEmpleadosid();
            if (empleadosid != null) {
                empleadosid.getTelefonoList().remove(telefono);
                empleadosid = em.merge(empleadosid);
            }
            em.remove(telefono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Telefono> findTelefonoEntities() {
        return findTelefonoEntities(true, -1, -1);
    }

    public List<Telefono> findTelefonoEntities(int maxResults, int firstResult) {
        return findTelefonoEntities(false, maxResults, firstResult);
    }

    private List<Telefono> findTelefonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Telefono.class));
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

    public Telefono findTelefono(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Telefono.class, id);
        } finally {
            em.close();
        }
    }

    public int getTelefonoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Telefono> rt = cq.from(Telefono.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
