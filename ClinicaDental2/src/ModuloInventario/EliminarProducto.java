/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloInventario;

import ModuloInventario.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author oem
 */
public class EliminarProducto implements IModificarBD {

    public EliminarProducto() {
        this.emf = Persistence.createEntityManagerFactory("ClinicaDental2PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



    @Override
    public void Operar(Producto producto) {
        EntityManager em = null;
        int id = producto.getId();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto2 = null;
            try {
                producto2 = em.getReference(Producto.class, id);
                producto2.getId();
            } catch (EntityNotFoundException enfe) {
                try {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(EliminarProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            em.remove(producto2);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    
}
