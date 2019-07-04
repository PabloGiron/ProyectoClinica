/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.List;
import Entidades.Servicio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author oem
 */
public class Main {
        public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Clinica");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Servicio> query = em.createNamedQuery("Servicio.findByNombre", Servicio.class);
        query.setParameter("nombre", "Extracción");
        List<Servicio> listaDatos = query.getResultList();
        for(Servicio s : listaDatos){
            System.out.println(s.getPrecio());
        }
    }
}
