package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 463849
 */
public class DBUtil {
    
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("NotePU");
    
    public static EntityManagerFactory getEmFactory() {
    
        return emf;
        
    }
    
}
