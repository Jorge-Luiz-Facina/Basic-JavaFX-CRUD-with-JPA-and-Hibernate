package sale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SaleDao {
    private static SaleDao instance;
    protected EntityManager entityManager;
           
    public static SaleDao getInstance(){
        if(instance == null){
            instance = new SaleDao();
        }
        return instance;
    }
    
    private SaleDao(){
        entityManager = getEntityManager();
    }
    
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProdutoFxPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
    
    public void persist(Sale sale) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sale);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
