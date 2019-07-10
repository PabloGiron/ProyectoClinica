/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Paciente;
import java.util.ArrayList;
import java.util.List;
import Entidades.Telefono;
import Entidades.Tutorpaciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author oem
 */
public class TutorpacienteJpaController implements Serializable {

    public TutorpacienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Clinica");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tutorpaciente tutorpaciente) {
        if (tutorpaciente.getPacienteList() == null) {
            tutorpaciente.setPacienteList(new ArrayList<Paciente>());
        }
        if (tutorpaciente.getTelefonoList() == null) {
            tutorpaciente.setTelefonoList(new ArrayList<Telefono>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Paciente> attachedPacienteList = new ArrayList<Paciente>();
            for (Paciente pacienteListPacienteToAttach : tutorpaciente.getPacienteList()) {
                pacienteListPacienteToAttach = em.getReference(pacienteListPacienteToAttach.getClass(), pacienteListPacienteToAttach.getId());
                attachedPacienteList.add(pacienteListPacienteToAttach);
            }
            tutorpaciente.setPacienteList(attachedPacienteList);
            List<Telefono> attachedTelefonoList = new ArrayList<Telefono>();
            for (Telefono telefonoListTelefonoToAttach : tutorpaciente.getTelefonoList()) {
                telefonoListTelefonoToAttach = em.getReference(telefonoListTelefonoToAttach.getClass(), telefonoListTelefonoToAttach.getId());
                attachedTelefonoList.add(telefonoListTelefonoToAttach);
            }
            tutorpaciente.setTelefonoList(attachedTelefonoList);
            em.persist(tutorpaciente);
            for (Paciente pacienteListPaciente : tutorpaciente.getPacienteList()) {
                Tutorpaciente oldTutorPacienteidOfPacienteListPaciente = pacienteListPaciente.getTutorPacienteid();
                pacienteListPaciente.setTutorPacienteid(tutorpaciente);
                pacienteListPaciente = em.merge(pacienteListPaciente);
                if (oldTutorPacienteidOfPacienteListPaciente != null) {
                    oldTutorPacienteidOfPacienteListPaciente.getPacienteList().remove(pacienteListPaciente);
                    oldTutorPacienteidOfPacienteListPaciente = em.merge(oldTutorPacienteidOfPacienteListPaciente);
                }
            }
            for (Telefono telefonoListTelefono : tutorpaciente.getTelefonoList()) {
                Tutorpaciente oldTutorPacienteidOfTelefonoListTelefono = telefonoListTelefono.getTutorPacienteid();
                telefonoListTelefono.setTutorPacienteid(tutorpaciente);
                telefonoListTelefono = em.merge(telefonoListTelefono);
                if (oldTutorPacienteidOfTelefonoListTelefono != null) {
                    oldTutorPacienteidOfTelefonoListTelefono.getTelefonoList().remove(telefonoListTelefono);
                    oldTutorPacienteidOfTelefonoListTelefono = em.merge(oldTutorPacienteidOfTelefonoListTelefono);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tutorpaciente tutorpaciente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutorpaciente persistentTutorpaciente = em.find(Tutorpaciente.class, tutorpaciente.getId());
            List<Paciente> pacienteListOld = persistentTutorpaciente.getPacienteList();
            List<Paciente> pacienteListNew = tutorpaciente.getPacienteList();
            List<Telefono> telefonoListOld = persistentTutorpaciente.getTelefonoList();
            List<Telefono> telefonoListNew = tutorpaciente.getTelefonoList();
            List<String> illegalOrphanMessages = null;
            for (Paciente pacienteListOldPaciente : pacienteListOld) {
                if (!pacienteListNew.contains(pacienteListOldPaciente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Paciente " + pacienteListOldPaciente + " since its tutorPacienteid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Paciente> attachedPacienteListNew = new ArrayList<Paciente>();
            for (Paciente pacienteListNewPacienteToAttach : pacienteListNew) {
                pacienteListNewPacienteToAttach = em.getReference(pacienteListNewPacienteToAttach.getClass(), pacienteListNewPacienteToAttach.getId());
                attachedPacienteListNew.add(pacienteListNewPacienteToAttach);
            }
            pacienteListNew = attachedPacienteListNew;
            tutorpaciente.setPacienteList(pacienteListNew);
            List<Telefono> attachedTelefonoListNew = new ArrayList<Telefono>();
            for (Telefono telefonoListNewTelefonoToAttach : telefonoListNew) {
                telefonoListNewTelefonoToAttach = em.getReference(telefonoListNewTelefonoToAttach.getClass(), telefonoListNewTelefonoToAttach.getId());
                attachedTelefonoListNew.add(telefonoListNewTelefonoToAttach);
            }
            telefonoListNew = attachedTelefonoListNew;
            tutorpaciente.setTelefonoList(telefonoListNew);
            tutorpaciente = em.merge(tutorpaciente);
            for (Paciente pacienteListNewPaciente : pacienteListNew) {
                if (!pacienteListOld.contains(pacienteListNewPaciente)) {
                    Tutorpaciente oldTutorPacienteidOfPacienteListNewPaciente = pacienteListNewPaciente.getTutorPacienteid();
                    pacienteListNewPaciente.setTutorPacienteid(tutorpaciente);
                    pacienteListNewPaciente = em.merge(pacienteListNewPaciente);
                    if (oldTutorPacienteidOfPacienteListNewPaciente != null && !oldTutorPacienteidOfPacienteListNewPaciente.equals(tutorpaciente)) {
                        oldTutorPacienteidOfPacienteListNewPaciente.getPacienteList().remove(pacienteListNewPaciente);
                        oldTutorPacienteidOfPacienteListNewPaciente = em.merge(oldTutorPacienteidOfPacienteListNewPaciente);
                    }
                }
            }
            for (Telefono telefonoListOldTelefono : telefonoListOld) {
                if (!telefonoListNew.contains(telefonoListOldTelefono)) {
                    telefonoListOldTelefono.setTutorPacienteid(null);
                    telefonoListOldTelefono = em.merge(telefonoListOldTelefono);
                }
            }
            for (Telefono telefonoListNewTelefono : telefonoListNew) {
                if (!telefonoListOld.contains(telefonoListNewTelefono)) {
                    Tutorpaciente oldTutorPacienteidOfTelefonoListNewTelefono = telefonoListNewTelefono.getTutorPacienteid();
                    telefonoListNewTelefono.setTutorPacienteid(tutorpaciente);
                    telefonoListNewTelefono = em.merge(telefonoListNewTelefono);
                    if (oldTutorPacienteidOfTelefonoListNewTelefono != null && !oldTutorPacienteidOfTelefonoListNewTelefono.equals(tutorpaciente)) {
                        oldTutorPacienteidOfTelefonoListNewTelefono.getTelefonoList().remove(telefonoListNewTelefono);
                        oldTutorPacienteidOfTelefonoListNewTelefono = em.merge(oldTutorPacienteidOfTelefonoListNewTelefono);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tutorpaciente.getId();
                if (findTutorpaciente(id) == null) {
                    throw new NonexistentEntityException("The tutorpaciente with id " + id + " no longer exists.");
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
            Tutorpaciente tutorpaciente;
            try {
                tutorpaciente = em.getReference(Tutorpaciente.class, id);
                tutorpaciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutorpaciente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Paciente> pacienteListOrphanCheck = tutorpaciente.getPacienteList();
            for (Paciente pacienteListOrphanCheckPaciente : pacienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutorpaciente (" + tutorpaciente + ") cannot be destroyed since the Paciente " + pacienteListOrphanCheckPaciente + " in its pacienteList field has a non-nullable tutorPacienteid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Telefono> telefonoList = tutorpaciente.getTelefonoList();
            for (Telefono telefonoListTelefono : telefonoList) {
                telefonoListTelefono.setTutorPacienteid(null);
                telefonoListTelefono = em.merge(telefonoListTelefono);
            }
            em.remove(tutorpaciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tutorpaciente> findTutorpacienteEntities() {
        return findTutorpacienteEntities(true, -1, -1);
    }

    public List<Tutorpaciente> findTutorpacienteEntities(int maxResults, int firstResult) {
        return findTutorpacienteEntities(false, maxResults, firstResult);
    }

    private List<Tutorpaciente> findTutorpacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutorpaciente.class));
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

    public Tutorpaciente findTutorpaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutorpaciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutorpacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutorpaciente> rt = cq.from(Tutorpaciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
