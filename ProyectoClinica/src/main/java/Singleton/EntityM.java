/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nasc_
 */
public class EntityM {
    
    private static EntityManager em;
    private static EntityManagerFactory emf;
    
    private EntityM()
    {
        
    }

    public static EntityManager getEm() {
        if(em == null){
            emf = Persistence.createEntityManagerFactory("Clinica");
            em = emf.createEntityManager();
        }
        return em;
    }

    public static EntityManagerFactory getEmf() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("Clinica");
        }
        return emf;
    }
    
}
