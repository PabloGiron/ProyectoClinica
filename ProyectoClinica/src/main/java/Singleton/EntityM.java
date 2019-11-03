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
    //FUNCION QUE RETORNA EL ENTITY MANAGER
    public static EntityManager getEm() {
        if(em == null){
            emf = Persistence.createEntityManagerFactory("Clinica");
            em = emf.createEntityManager();
        }
        return em;
    }
    //FUNCION QUE RETORNA EL ENTITY MANAGER FACTORY
    public static EntityManagerFactory getEmf() {
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("Clinica");
        }
        return emf;
    }
    
}
