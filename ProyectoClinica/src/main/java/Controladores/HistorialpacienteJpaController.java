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
import Entidades.Citaortodoncia;
import java.util.ArrayList;
import java.util.List;
import Entidades.Citanormal;
import Entidades.Historialpaciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nasc_
 */
public class HistorialpacienteJpaController implements Serializable {

    public HistorialpacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historialpaciente historialpaciente) {
        if (historialpaciente.getCitaortodonciaList() == null) {
            historialpaciente.setCitaortodonciaList(new ArrayList<Citaortodoncia>());
        }
        if (historialpaciente.getCitanormalList() == null) {
            historialpaciente.setCitanormalList(new ArrayList<Citanormal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Citaortodoncia> attachedCitaortodonciaList = new ArrayList<Citaortodoncia>();
            for (Citaortodoncia citaortodonciaListCitaortodonciaToAttach : historialpaciente.getCitaortodonciaList()) {
                citaortodonciaListCitaortodonciaToAttach = em.getReference(citaortodonciaListCitaortodonciaToAttach.getClass(), citaortodonciaListCitaortodonciaToAttach.getIdCitaOrtodoncia());
                attachedCitaortodonciaList.add(citaortodonciaListCitaortodonciaToAttach);
            }
            historialpaciente.setCitaortodonciaList(attachedCitaortodonciaList);
            List<Citanormal> attachedCitanormalList = new ArrayList<Citanormal>();
            for (Citanormal citanormalListCitanormalToAttach : historialpaciente.getCitanormalList()) {
                citanormalListCitanormalToAttach = em.getReference(citanormalListCitanormalToAttach.getClass(), citanormalListCitanormalToAttach.getIdCitaNormal());
                attachedCitanormalList.add(citanormalListCitanormalToAttach);
            }
            historialpaciente.setCitanormalList(attachedCitanormalList);
            em.persist(historialpaciente);
            for (Citaortodoncia citaortodonciaListCitaortodoncia : historialpaciente.getCitaortodonciaList()) {
                Historialpaciente oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListCitaortodoncia = citaortodonciaListCitaortodoncia.getHistorialPacienteidHistorialPaciente();
                citaortodonciaListCitaortodoncia.setHistorialPacienteidHistorialPaciente(historialpaciente);
                citaortodonciaListCitaortodoncia = em.merge(citaortodonciaListCitaortodoncia);
                if (oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListCitaortodoncia != null) {
                    oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListCitaortodoncia.getCitaortodonciaList().remove(citaortodonciaListCitaortodoncia);
                    oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListCitaortodoncia = em.merge(oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListCitaortodoncia);
                }
            }
            for (Citanormal citanormalListCitanormal : historialpaciente.getCitanormalList()) {
                Historialpaciente oldHistorialPacienteidHistorialPacienteOfCitanormalListCitanormal = citanormalListCitanormal.getHistorialPacienteidHistorialPaciente();
                citanormalListCitanormal.setHistorialPacienteidHistorialPaciente(historialpaciente);
                citanormalListCitanormal = em.merge(citanormalListCitanormal);
                if (oldHistorialPacienteidHistorialPacienteOfCitanormalListCitanormal != null) {
                    oldHistorialPacienteidHistorialPacienteOfCitanormalListCitanormal.getCitanormalList().remove(citanormalListCitanormal);
                    oldHistorialPacienteidHistorialPacienteOfCitanormalListCitanormal = em.merge(oldHistorialPacienteidHistorialPacienteOfCitanormalListCitanormal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialpaciente historialpaciente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialpaciente persistentHistorialpaciente = em.find(Historialpaciente.class, historialpaciente.getIdHistorialPaciente());
            List<Citaortodoncia> citaortodonciaListOld = persistentHistorialpaciente.getCitaortodonciaList();
            List<Citaortodoncia> citaortodonciaListNew = historialpaciente.getCitaortodonciaList();
            List<Citanormal> citanormalListOld = persistentHistorialpaciente.getCitanormalList();
            List<Citanormal> citanormalListNew = historialpaciente.getCitanormalList();
            List<String> illegalOrphanMessages = null;
            for (Citaortodoncia citaortodonciaListOldCitaortodoncia : citaortodonciaListOld) {
                if (!citaortodonciaListNew.contains(citaortodonciaListOldCitaortodoncia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Citaortodoncia " + citaortodonciaListOldCitaortodoncia + " since its historialPacienteidHistorialPaciente field is not nullable.");
                }
            }
            for (Citanormal citanormalListOldCitanormal : citanormalListOld) {
                if (!citanormalListNew.contains(citanormalListOldCitanormal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Citanormal " + citanormalListOldCitanormal + " since its historialPacienteidHistorialPaciente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Citaortodoncia> attachedCitaortodonciaListNew = new ArrayList<Citaortodoncia>();
            for (Citaortodoncia citaortodonciaListNewCitaortodonciaToAttach : citaortodonciaListNew) {
                citaortodonciaListNewCitaortodonciaToAttach = em.getReference(citaortodonciaListNewCitaortodonciaToAttach.getClass(), citaortodonciaListNewCitaortodonciaToAttach.getIdCitaOrtodoncia());
                attachedCitaortodonciaListNew.add(citaortodonciaListNewCitaortodonciaToAttach);
            }
            citaortodonciaListNew = attachedCitaortodonciaListNew;
            historialpaciente.setCitaortodonciaList(citaortodonciaListNew);
            List<Citanormal> attachedCitanormalListNew = new ArrayList<Citanormal>();
            for (Citanormal citanormalListNewCitanormalToAttach : citanormalListNew) {
                citanormalListNewCitanormalToAttach = em.getReference(citanormalListNewCitanormalToAttach.getClass(), citanormalListNewCitanormalToAttach.getIdCitaNormal());
                attachedCitanormalListNew.add(citanormalListNewCitanormalToAttach);
            }
            citanormalListNew = attachedCitanormalListNew;
            historialpaciente.setCitanormalList(citanormalListNew);
            historialpaciente = em.merge(historialpaciente);
            for (Citaortodoncia citaortodonciaListNewCitaortodoncia : citaortodonciaListNew) {
                if (!citaortodonciaListOld.contains(citaortodonciaListNewCitaortodoncia)) {
                    Historialpaciente oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListNewCitaortodoncia = citaortodonciaListNewCitaortodoncia.getHistorialPacienteidHistorialPaciente();
                    citaortodonciaListNewCitaortodoncia.setHistorialPacienteidHistorialPaciente(historialpaciente);
                    citaortodonciaListNewCitaortodoncia = em.merge(citaortodonciaListNewCitaortodoncia);
                    if (oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListNewCitaortodoncia != null && !oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListNewCitaortodoncia.equals(historialpaciente)) {
                        oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListNewCitaortodoncia.getCitaortodonciaList().remove(citaortodonciaListNewCitaortodoncia);
                        oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListNewCitaortodoncia = em.merge(oldHistorialPacienteidHistorialPacienteOfCitaortodonciaListNewCitaortodoncia);
                    }
                }
            }
            for (Citanormal citanormalListNewCitanormal : citanormalListNew) {
                if (!citanormalListOld.contains(citanormalListNewCitanormal)) {
                    Historialpaciente oldHistorialPacienteidHistorialPacienteOfCitanormalListNewCitanormal = citanormalListNewCitanormal.getHistorialPacienteidHistorialPaciente();
                    citanormalListNewCitanormal.setHistorialPacienteidHistorialPaciente(historialpaciente);
                    citanormalListNewCitanormal = em.merge(citanormalListNewCitanormal);
                    if (oldHistorialPacienteidHistorialPacienteOfCitanormalListNewCitanormal != null && !oldHistorialPacienteidHistorialPacienteOfCitanormalListNewCitanormal.equals(historialpaciente)) {
                        oldHistorialPacienteidHistorialPacienteOfCitanormalListNewCitanormal.getCitanormalList().remove(citanormalListNewCitanormal);
                        oldHistorialPacienteidHistorialPacienteOfCitanormalListNewCitanormal = em.merge(oldHistorialPacienteidHistorialPacienteOfCitanormalListNewCitanormal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historialpaciente.getIdHistorialPaciente();
                if (findHistorialpaciente(id) == null) {
                    throw new NonexistentEntityException("The historialpaciente with id " + id + " no longer exists.");
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
            Historialpaciente historialpaciente;
            try {
                historialpaciente = em.getReference(Historialpaciente.class, id);
                historialpaciente.getIdHistorialPaciente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialpaciente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Citaortodoncia> citaortodonciaListOrphanCheck = historialpaciente.getCitaortodonciaList();
            for (Citaortodoncia citaortodonciaListOrphanCheckCitaortodoncia : citaortodonciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Historialpaciente (" + historialpaciente + ") cannot be destroyed since the Citaortodoncia " + citaortodonciaListOrphanCheckCitaortodoncia + " in its citaortodonciaList field has a non-nullable historialPacienteidHistorialPaciente field.");
            }
            List<Citanormal> citanormalListOrphanCheck = historialpaciente.getCitanormalList();
            for (Citanormal citanormalListOrphanCheckCitanormal : citanormalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Historialpaciente (" + historialpaciente + ") cannot be destroyed since the Citanormal " + citanormalListOrphanCheckCitanormal + " in its citanormalList field has a non-nullable historialPacienteidHistorialPaciente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(historialpaciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialpaciente> findHistorialpacienteEntities() {
        return findHistorialpacienteEntities(true, -1, -1);
    }

    public List<Historialpaciente> findHistorialpacienteEntities(int maxResults, int firstResult) {
        return findHistorialpacienteEntities(false, maxResults, firstResult);
    }

    private List<Historialpaciente> findHistorialpacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialpaciente.class));
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

    public Historialpaciente findHistorialpaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialpaciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialpacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialpaciente> rt = cq.from(Historialpaciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
