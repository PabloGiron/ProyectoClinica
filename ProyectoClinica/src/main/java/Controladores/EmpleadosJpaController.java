/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Empleados;
import Entidades.Historial;
import Entidades.Saldoextra;
import Entidades.Telefonos;
import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nasc_
 */
public class EmpleadosJpaController implements Serializable {

    public EmpleadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleados empleados) {
        if (empleados.getHistorialList() == null) {
            empleados.setHistorialList(new ArrayList<Historial>());
        }
        if (empleados.getTelefonosList() == null) {
            empleados.setTelefonosList(new ArrayList<Telefonos>());
        }
        if (empleados.getSaldoextraList() == null) {
            empleados.setSaldoextraList(new ArrayList<Saldoextra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historial> attachedHistorialList = new ArrayList<Historial>();
            for (Historial historialListHistorialToAttach : empleados.getHistorialList()) {
                historialListHistorialToAttach = em.getReference(historialListHistorialToAttach.getClass(), historialListHistorialToAttach.getId());
                attachedHistorialList.add(historialListHistorialToAttach);
            }
            empleados.setHistorialList(attachedHistorialList);
            List<Telefonos> attachedTelefonosList = new ArrayList<Telefonos>();
            for (Telefonos telefonosListTelefonosToAttach : empleados.getTelefonosList()) {
                telefonosListTelefonosToAttach = em.getReference(telefonosListTelefonosToAttach.getClass(), telefonosListTelefonosToAttach.getId());
                attachedTelefonosList.add(telefonosListTelefonosToAttach);
            }
            empleados.setTelefonosList(attachedTelefonosList);
            List<Saldoextra> attachedSaldoextraList = new ArrayList<Saldoextra>();
            for (Saldoextra saldoextraListSaldoextraToAttach : empleados.getSaldoextraList()) {
                saldoextraListSaldoextraToAttach = em.getReference(saldoextraListSaldoextraToAttach.getClass(), saldoextraListSaldoextraToAttach.getId());
                attachedSaldoextraList.add(saldoextraListSaldoextraToAttach);
            }
            empleados.setSaldoextraList(attachedSaldoextraList);
            em.persist(empleados);
            for (Historial historialListHistorial : empleados.getHistorialList()) {
                Empleados oldEmpleadosidOfHistorialListHistorial = historialListHistorial.getEmpleadosid();
                historialListHistorial.setEmpleadosid(empleados);
                historialListHistorial = em.merge(historialListHistorial);
                if (oldEmpleadosidOfHistorialListHistorial != null) {
                    oldEmpleadosidOfHistorialListHistorial.getHistorialList().remove(historialListHistorial);
                    oldEmpleadosidOfHistorialListHistorial = em.merge(oldEmpleadosidOfHistorialListHistorial);
                }
            }
            for (Telefonos telefonosListTelefonos : empleados.getTelefonosList()) {
                Empleados oldEmpleadosidOfTelefonosListTelefonos = telefonosListTelefonos.getEmpleadosid();
                telefonosListTelefonos.setEmpleadosid(empleados);
                telefonosListTelefonos = em.merge(telefonosListTelefonos);
                if (oldEmpleadosidOfTelefonosListTelefonos != null) {
                    oldEmpleadosidOfTelefonosListTelefonos.getTelefonosList().remove(telefonosListTelefonos);
                    oldEmpleadosidOfTelefonosListTelefonos = em.merge(oldEmpleadosidOfTelefonosListTelefonos);
                }
            }
            for (Saldoextra saldoextraListSaldoextra : empleados.getSaldoextraList()) {
                Empleados oldEmpleadosidOfSaldoextraListSaldoextra = saldoextraListSaldoextra.getEmpleadosid();
                saldoextraListSaldoextra.setEmpleadosid(empleados);
                saldoextraListSaldoextra = em.merge(saldoextraListSaldoextra);
                if (oldEmpleadosidOfSaldoextraListSaldoextra != null) {
                    oldEmpleadosidOfSaldoextraListSaldoextra.getSaldoextraList().remove(saldoextraListSaldoextra);
                    oldEmpleadosidOfSaldoextraListSaldoextra = em.merge(oldEmpleadosidOfSaldoextraListSaldoextra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getId());
            List<Historial> historialListOld = persistentEmpleados.getHistorialList();
            List<Historial> historialListNew = empleados.getHistorialList();
            List<Telefonos> telefonosListOld = persistentEmpleados.getTelefonosList();
            List<Telefonos> telefonosListNew = empleados.getTelefonosList();
            List<Saldoextra> saldoextraListOld = persistentEmpleados.getSaldoextraList();
            List<Saldoextra> saldoextraListNew = empleados.getSaldoextraList();
            List<String> illegalOrphanMessages = null;
            for (Historial historialListOldHistorial : historialListOld) {
                if (!historialListNew.contains(historialListOldHistorial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historial " + historialListOldHistorial + " since its empleadosid field is not nullable.");
                }
            }
            for (Telefonos telefonosListOldTelefonos : telefonosListOld) {
                if (!telefonosListNew.contains(telefonosListOldTelefonos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Telefonos " + telefonosListOldTelefonos + " since its empleadosid field is not nullable.");
                }
            }
            for (Saldoextra saldoextraListOldSaldoextra : saldoextraListOld) {
                if (!saldoextraListNew.contains(saldoextraListOldSaldoextra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Saldoextra " + saldoextraListOldSaldoextra + " since its empleadosid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historial> attachedHistorialListNew = new ArrayList<Historial>();
            for (Historial historialListNewHistorialToAttach : historialListNew) {
                historialListNewHistorialToAttach = em.getReference(historialListNewHistorialToAttach.getClass(), historialListNewHistorialToAttach.getId());
                attachedHistorialListNew.add(historialListNewHistorialToAttach);
            }
            historialListNew = attachedHistorialListNew;
            empleados.setHistorialList(historialListNew);
            List<Telefonos> attachedTelefonosListNew = new ArrayList<Telefonos>();
            for (Telefonos telefonosListNewTelefonosToAttach : telefonosListNew) {
                telefonosListNewTelefonosToAttach = em.getReference(telefonosListNewTelefonosToAttach.getClass(), telefonosListNewTelefonosToAttach.getId());
                attachedTelefonosListNew.add(telefonosListNewTelefonosToAttach);
            }
            telefonosListNew = attachedTelefonosListNew;
            empleados.setTelefonosList(telefonosListNew);
            List<Saldoextra> attachedSaldoextraListNew = new ArrayList<Saldoextra>();
            for (Saldoextra saldoextraListNewSaldoextraToAttach : saldoextraListNew) {
                saldoextraListNewSaldoextraToAttach = em.getReference(saldoextraListNewSaldoextraToAttach.getClass(), saldoextraListNewSaldoextraToAttach.getId());
                attachedSaldoextraListNew.add(saldoextraListNewSaldoextraToAttach);
            }
            saldoextraListNew = attachedSaldoextraListNew;
            empleados.setSaldoextraList(saldoextraListNew);
            empleados = em.merge(empleados);
            for (Historial historialListNewHistorial : historialListNew) {
                if (!historialListOld.contains(historialListNewHistorial)) {
                    Empleados oldEmpleadosidOfHistorialListNewHistorial = historialListNewHistorial.getEmpleadosid();
                    historialListNewHistorial.setEmpleadosid(empleados);
                    historialListNewHistorial = em.merge(historialListNewHistorial);
                    if (oldEmpleadosidOfHistorialListNewHistorial != null && !oldEmpleadosidOfHistorialListNewHistorial.equals(empleados)) {
                        oldEmpleadosidOfHistorialListNewHistorial.getHistorialList().remove(historialListNewHistorial);
                        oldEmpleadosidOfHistorialListNewHistorial = em.merge(oldEmpleadosidOfHistorialListNewHistorial);
                    }
                }
            }
            for (Telefonos telefonosListNewTelefonos : telefonosListNew) {
                if (!telefonosListOld.contains(telefonosListNewTelefonos)) {
                    Empleados oldEmpleadosidOfTelefonosListNewTelefonos = telefonosListNewTelefonos.getEmpleadosid();
                    telefonosListNewTelefonos.setEmpleadosid(empleados);
                    telefonosListNewTelefonos = em.merge(telefonosListNewTelefonos);
                    if (oldEmpleadosidOfTelefonosListNewTelefonos != null && !oldEmpleadosidOfTelefonosListNewTelefonos.equals(empleados)) {
                        oldEmpleadosidOfTelefonosListNewTelefonos.getTelefonosList().remove(telefonosListNewTelefonos);
                        oldEmpleadosidOfTelefonosListNewTelefonos = em.merge(oldEmpleadosidOfTelefonosListNewTelefonos);
                    }
                }
            }
            for (Saldoextra saldoextraListNewSaldoextra : saldoextraListNew) {
                if (!saldoextraListOld.contains(saldoextraListNewSaldoextra)) {
                    Empleados oldEmpleadosidOfSaldoextraListNewSaldoextra = saldoextraListNewSaldoextra.getEmpleadosid();
                    saldoextraListNewSaldoextra.setEmpleadosid(empleados);
                    saldoextraListNewSaldoextra = em.merge(saldoextraListNewSaldoextra);
                    if (oldEmpleadosidOfSaldoextraListNewSaldoextra != null && !oldEmpleadosidOfSaldoextraListNewSaldoextra.equals(empleados)) {
                        oldEmpleadosidOfSaldoextraListNewSaldoextra.getSaldoextraList().remove(saldoextraListNewSaldoextra);
                        oldEmpleadosidOfSaldoextraListNewSaldoextra = em.merge(oldEmpleadosidOfSaldoextraListNewSaldoextra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleados.getId();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
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
            Empleados empleados;
            try {
                empleados = em.getReference(Empleados.class, id);
                empleados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historial> historialListOrphanCheck = empleados.getHistorialList();
            for (Historial historialListOrphanCheckHistorial : historialListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleados (" + empleados + ") cannot be destroyed since the Historial " + historialListOrphanCheckHistorial + " in its historialList field has a non-nullable empleadosid field.");
            }
            List<Telefonos> telefonosListOrphanCheck = empleados.getTelefonosList();
            for (Telefonos telefonosListOrphanCheckTelefonos : telefonosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleados (" + empleados + ") cannot be destroyed since the Telefonos " + telefonosListOrphanCheckTelefonos + " in its telefonosList field has a non-nullable empleadosid field.");
            }
            List<Saldoextra> saldoextraListOrphanCheck = empleados.getSaldoextraList();
            for (Saldoextra saldoextraListOrphanCheckSaldoextra : saldoextraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleados (" + empleados + ") cannot be destroyed since the Saldoextra " + saldoextraListOrphanCheckSaldoextra + " in its saldoextraList field has a non-nullable empleadosid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleados> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleados> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleados> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleados.class));
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

    public Empleados findEmpleados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleados> rt = cq.from(Empleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
