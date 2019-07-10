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
import Entidades.Telefono;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author oem
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController( ) {
        this.emf = Persistence.createEntityManagerFactory("Clinica");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) {
        if (paciente.getTelefonoList() == null) {
            paciente.setTelefonoList(new ArrayList<Telefono>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutorpaciente tutorPacienteid = paciente.getTutorPacienteid();
            if (tutorPacienteid != null) {
                tutorPacienteid = em.getReference(tutorPacienteid.getClass(), tutorPacienteid.getId());
                paciente.setTutorPacienteid(tutorPacienteid);
            }
            List<Telefono> attachedTelefonoList = new ArrayList<Telefono>();
            for (Telefono telefonoListTelefonoToAttach : paciente.getTelefonoList()) {
                telefonoListTelefonoToAttach = em.getReference(telefonoListTelefonoToAttach.getClass(), telefonoListTelefonoToAttach.getId());
                attachedTelefonoList.add(telefonoListTelefonoToAttach);
            }
            paciente.setTelefonoList(attachedTelefonoList);
            em.persist(paciente);
            if (tutorPacienteid != null) {
                tutorPacienteid.getPacienteList().add(paciente);
                tutorPacienteid = em.merge(tutorPacienteid);
            }
            for (Telefono telefonoListTelefono : paciente.getTelefonoList()) {
                Paciente oldPacienteidOfTelefonoListTelefono = telefonoListTelefono.getPacienteid();
                telefonoListTelefono.setPacienteid(paciente);
                telefonoListTelefono = em.merge(telefonoListTelefono);
                if (oldPacienteidOfTelefonoListTelefono != null) {
                    oldPacienteidOfTelefonoListTelefono.getTelefonoList().remove(telefonoListTelefono);
                    oldPacienteidOfTelefonoListTelefono = em.merge(oldPacienteidOfTelefonoListTelefono);
                }
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
            List<Telefono> telefonoListOld = persistentPaciente.getTelefonoList();
            List<Telefono> telefonoListNew = paciente.getTelefonoList();
            if (tutorPacienteidNew != null) {
                tutorPacienteidNew = em.getReference(tutorPacienteidNew.getClass(), tutorPacienteidNew.getId());
                paciente.setTutorPacienteid(tutorPacienteidNew);
            }
            List<Telefono> attachedTelefonoListNew = new ArrayList<Telefono>();
            for (Telefono telefonoListNewTelefonoToAttach : telefonoListNew) {
                telefonoListNewTelefonoToAttach = em.getReference(telefonoListNewTelefonoToAttach.getClass(), telefonoListNewTelefonoToAttach.getId());
                attachedTelefonoListNew.add(telefonoListNewTelefonoToAttach);
            }
            telefonoListNew = attachedTelefonoListNew;
            paciente.setTelefonoList(telefonoListNew);
            paciente = em.merge(paciente);
            if (tutorPacienteidOld != null && !tutorPacienteidOld.equals(tutorPacienteidNew)) {
                tutorPacienteidOld.getPacienteList().remove(paciente);
                tutorPacienteidOld = em.merge(tutorPacienteidOld);
            }
            if (tutorPacienteidNew != null && !tutorPacienteidNew.equals(tutorPacienteidOld)) {
                tutorPacienteidNew.getPacienteList().add(paciente);
                tutorPacienteidNew = em.merge(tutorPacienteidNew);
            }
            for (Telefono telefonoListOldTelefono : telefonoListOld) {
                if (!telefonoListNew.contains(telefonoListOldTelefono)) {
                    telefonoListOldTelefono.setPacienteid(null);
                    telefonoListOldTelefono = em.merge(telefonoListOldTelefono);
                }
            }
            for (Telefono telefonoListNewTelefono : telefonoListNew) {
                if (!telefonoListOld.contains(telefonoListNewTelefono)) {
                    Paciente oldPacienteidOfTelefonoListNewTelefono = telefonoListNewTelefono.getPacienteid();
                    telefonoListNewTelefono.setPacienteid(paciente);
                    telefonoListNewTelefono = em.merge(telefonoListNewTelefono);
                    if (oldPacienteidOfTelefonoListNewTelefono != null && !oldPacienteidOfTelefonoListNewTelefono.equals(paciente)) {
                        oldPacienteidOfTelefonoListNewTelefono.getTelefonoList().remove(telefonoListNewTelefono);
                        oldPacienteidOfTelefonoListNewTelefono = em.merge(oldPacienteidOfTelefonoListNewTelefono);
                    }
                }
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
            List<Telefono> telefonoList = paciente.getTelefonoList();
            for (Telefono telefonoListTelefono : telefonoList) {
                telefonoListTelefono.setPacienteid(null);
                telefonoListTelefono = em.merge(telefonoListTelefono);
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
    
}
