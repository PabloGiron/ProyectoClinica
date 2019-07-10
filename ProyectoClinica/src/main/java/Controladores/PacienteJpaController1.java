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
import Entidades.Tutorpaciente;
import Entidades.Telefono;
import java.util.ArrayList;
import java.util.List;
import Entidades.Historialpaciente;
import Entidades.Paciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nasc_
 */
public class PacienteJpaController1 implements Serializable {

    public PacienteJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) {
        if (paciente.getTelefonoList() == null) {
            paciente.setTelefonoList(new ArrayList<Telefono>());
        }
        if (paciente.getHistorialpacienteList() == null) {
            paciente.setHistorialpacienteList(new ArrayList<Historialpaciente>());
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
            List<Historialpaciente> attachedHistorialpacienteList = new ArrayList<Historialpaciente>();
            for (Historialpaciente historialpacienteListHistorialpacienteToAttach : paciente.getHistorialpacienteList()) {
                historialpacienteListHistorialpacienteToAttach = em.getReference(historialpacienteListHistorialpacienteToAttach.getClass(), historialpacienteListHistorialpacienteToAttach.getIdHistorialPaciente());
                attachedHistorialpacienteList.add(historialpacienteListHistorialpacienteToAttach);
            }
            paciente.setHistorialpacienteList(attachedHistorialpacienteList);
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
            for (Historialpaciente historialpacienteListHistorialpaciente : paciente.getHistorialpacienteList()) {
                Paciente oldPacienteidOfHistorialpacienteListHistorialpaciente = historialpacienteListHistorialpaciente.getPacienteid();
                historialpacienteListHistorialpaciente.setPacienteid(paciente);
                historialpacienteListHistorialpaciente = em.merge(historialpacienteListHistorialpaciente);
                if (oldPacienteidOfHistorialpacienteListHistorialpaciente != null) {
                    oldPacienteidOfHistorialpacienteListHistorialpaciente.getHistorialpacienteList().remove(historialpacienteListHistorialpaciente);
                    oldPacienteidOfHistorialpacienteListHistorialpaciente = em.merge(oldPacienteidOfHistorialpacienteListHistorialpaciente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            Tutorpaciente tutorPacienteidOld = persistentPaciente.getTutorPacienteid();
            Tutorpaciente tutorPacienteidNew = paciente.getTutorPacienteid();
            List<Telefono> telefonoListOld = persistentPaciente.getTelefonoList();
            List<Telefono> telefonoListNew = paciente.getTelefonoList();
            List<Historialpaciente> historialpacienteListOld = persistentPaciente.getHistorialpacienteList();
            List<Historialpaciente> historialpacienteListNew = paciente.getHistorialpacienteList();
            List<String> illegalOrphanMessages = null;
            for (Historialpaciente historialpacienteListOldHistorialpaciente : historialpacienteListOld) {
                if (!historialpacienteListNew.contains(historialpacienteListOldHistorialpaciente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialpaciente " + historialpacienteListOldHistorialpaciente + " since its pacienteid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
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
            List<Historialpaciente> attachedHistorialpacienteListNew = new ArrayList<Historialpaciente>();
            for (Historialpaciente historialpacienteListNewHistorialpacienteToAttach : historialpacienteListNew) {
                historialpacienteListNewHistorialpacienteToAttach = em.getReference(historialpacienteListNewHistorialpacienteToAttach.getClass(), historialpacienteListNewHistorialpacienteToAttach.getIdHistorialPaciente());
                attachedHistorialpacienteListNew.add(historialpacienteListNewHistorialpacienteToAttach);
            }
            historialpacienteListNew = attachedHistorialpacienteListNew;
            paciente.setHistorialpacienteList(historialpacienteListNew);
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
            for (Historialpaciente historialpacienteListNewHistorialpaciente : historialpacienteListNew) {
                if (!historialpacienteListOld.contains(historialpacienteListNewHistorialpaciente)) {
                    Paciente oldPacienteidOfHistorialpacienteListNewHistorialpaciente = historialpacienteListNewHistorialpaciente.getPacienteid();
                    historialpacienteListNewHistorialpaciente.setPacienteid(paciente);
                    historialpacienteListNewHistorialpaciente = em.merge(historialpacienteListNewHistorialpaciente);
                    if (oldPacienteidOfHistorialpacienteListNewHistorialpaciente != null && !oldPacienteidOfHistorialpacienteListNewHistorialpaciente.equals(paciente)) {
                        oldPacienteidOfHistorialpacienteListNewHistorialpaciente.getHistorialpacienteList().remove(historialpacienteListNewHistorialpaciente);
                        oldPacienteidOfHistorialpacienteListNewHistorialpaciente = em.merge(oldPacienteidOfHistorialpacienteListNewHistorialpaciente);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Historialpaciente> historialpacienteListOrphanCheck = paciente.getHistorialpacienteList();
            for (Historialpaciente historialpacienteListOrphanCheckHistorialpaciente : historialpacienteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paciente (" + paciente + ") cannot be destroyed since the Historialpaciente " + historialpacienteListOrphanCheckHistorialpaciente + " in its historialpacienteList field has a non-nullable pacienteid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
