/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Paciente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Tutorpaciente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nasc_
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutorpaciente tutorPacienteid = paciente.getTutorPacienteid();
            if (tutorPacienteid != null) {
                tutorPacienteid = em.getReference(tutorPacienteid.getClass(), tutorPacienteid.getId());
                paciente.setTutorPacienteid(tutorPacienteid);
            }
            em.persist(paciente);
            if (tutorPacienteid != null) {
                tutorPacienteid.getPacienteList().add(paciente);
                tutorPacienteid = em.merge(tutorPacienteid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            Tutorpaciente tutorPacienteidOld = persistentPaciente.getTutorPacienteid();
            Tutorpaciente tutorPacienteidNew = paciente.getTutorPacienteid();
            if (tutorPacienteidNew != null) {
                tutorPacienteidNew = em.getReference(tutorPacienteidNew.getClass(), tutorPacienteidNew.getId());
                paciente.setTutorPacienteid(tutorPacienteidNew);
            }
            paciente = em.merge(paciente);
            if (tutorPacienteidOld != null && !tutorPacienteidOld.equals(tutorPacienteidNew)) {
                tutorPacienteidOld.getPacienteList().remove(paciente);
                tutorPacienteidOld = em.merge(tutorPacienteidOld);
            }
            if (tutorPacienteidNew != null && !tutorPacienteidNew.equals(tutorPacienteidOld)) {
                tutorPacienteidNew.getPacienteList().add(paciente);
                tutorPacienteidNew = em.merge(tutorPacienteidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            Tutorpaciente tutorPacienteid = paciente.getTutorPacienteid();
            if (tutorPacienteid != null) {
                tutorPacienteid.getPacienteList().remove(paciente);
                tutorPacienteid = em.merge(tutorPacienteid);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public void actualizarPaciente(String nombre, String edad, String direccion, String nit, String telefono, int idTutor, int id){
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            Query queryUpdate = em.createNativeQuery("UPDATE paciente INNER JOIN telefono ON paciente.id = telefono.Paciente_id SET paciente.Nombre = '"+nombre+"', paciente.Edad = '"+edad+"', paciente.Direccion = '"+direccion+"', paciente.Nit = '"+ nit +"', paciente.TutorPaciente_id = "+ idTutor +", telefono.Numero = '"+telefono+"' WHERE paciente.id = "+id+";");
            queryUpdate.executeUpdate();
            em.getTransaction().commit();
        }finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
}
