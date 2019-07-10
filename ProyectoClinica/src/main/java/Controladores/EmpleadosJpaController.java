/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.Empleados;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Telefono;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author oem
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
        if (empleados.getTelefonoList() == null) {
            empleados.setTelefonoList(new ArrayList<Telefono>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Telefono> attachedTelefonoList = new ArrayList<Telefono>();
            for (Telefono telefonoListTelefonoToAttach : empleados.getTelefonoList()) {
                telefonoListTelefonoToAttach = em.getReference(telefonoListTelefonoToAttach.getClass(), telefonoListTelefonoToAttach.getId());
                attachedTelefonoList.add(telefonoListTelefonoToAttach);
            }
            empleados.setTelefonoList(attachedTelefonoList);
            em.persist(empleados);
            for (Telefono telefonoListTelefono : empleados.getTelefonoList()) {
                Empleados oldEmpleadosidOfTelefonoListTelefono = telefonoListTelefono.getEmpleadosid();
                telefonoListTelefono.setEmpleadosid(empleados);
                telefonoListTelefono = em.merge(telefonoListTelefono);
                if (oldEmpleadosidOfTelefonoListTelefono != null) {
                    oldEmpleadosidOfTelefonoListTelefono.getTelefonoList().remove(telefonoListTelefono);
                    oldEmpleadosidOfTelefonoListTelefono = em.merge(oldEmpleadosidOfTelefonoListTelefono);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getId());
            List<Telefono> telefonoListOld = persistentEmpleados.getTelefonoList();
            List<Telefono> telefonoListNew = empleados.getTelefonoList();
            List<Telefono> attachedTelefonoListNew = new ArrayList<Telefono>();
            for (Telefono telefonoListNewTelefonoToAttach : telefonoListNew) {
                telefonoListNewTelefonoToAttach = em.getReference(telefonoListNewTelefonoToAttach.getClass(), telefonoListNewTelefonoToAttach.getId());
                attachedTelefonoListNew.add(telefonoListNewTelefonoToAttach);
            }
            telefonoListNew = attachedTelefonoListNew;
            empleados.setTelefonoList(telefonoListNew);
            empleados = em.merge(empleados);
            for (Telefono telefonoListOldTelefono : telefonoListOld) {
                if (!telefonoListNew.contains(telefonoListOldTelefono)) {
                    telefonoListOldTelefono.setEmpleadosid(null);
                    telefonoListOldTelefono = em.merge(telefonoListOldTelefono);
                }
            }
            for (Telefono telefonoListNewTelefono : telefonoListNew) {
                if (!telefonoListOld.contains(telefonoListNewTelefono)) {
                    Empleados oldEmpleadosidOfTelefonoListNewTelefono = telefonoListNewTelefono.getEmpleadosid();
                    telefonoListNewTelefono.setEmpleadosid(empleados);
                    telefonoListNewTelefono = em.merge(telefonoListNewTelefono);
                    if (oldEmpleadosidOfTelefonoListNewTelefono != null && !oldEmpleadosidOfTelefonoListNewTelefono.equals(empleados)) {
                        oldEmpleadosidOfTelefonoListNewTelefono.getTelefonoList().remove(telefonoListNewTelefono);
                        oldEmpleadosidOfTelefonoListNewTelefono = em.merge(oldEmpleadosidOfTelefonoListNewTelefono);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            List<Telefono> telefonoList = empleados.getTelefonoList();
            for (Telefono telefonoListTelefono : telefonoList) {
                telefonoListTelefono.setEmpleadosid(null);
                telefonoListTelefono = em.merge(telefonoListTelefono);
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
