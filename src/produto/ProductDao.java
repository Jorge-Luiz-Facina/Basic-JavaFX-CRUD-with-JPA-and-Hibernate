package produto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductDao {

    private static ProductDao instance;
    protected EntityManager entityManager;
           
    public static ProductDao getInstance(){
        if(instance == null){
            instance = new ProductDao();
        }
        return instance;
    }
    
    private ProductDao(){
        entityManager = getEntityManager();
    }
    
    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProdutoFxPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }
    
    public Product getById(final Long id) {
        return entityManager.find(Product.class, id);
    }
   
    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        return entityManager.createQuery("FROM " + Product.class.getName()).getResultList();
    }
    
    public void deleteAll(){
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("delete from product where id > 0").executeUpdate();
        entityManager.getTransaction().commit();
    }
        
   
    public void persist(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
   
    public void merge(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
   
    public void remove(Product product) {
        try {
            entityManager.getTransaction().begin();
            product = entityManager.find(Product.class, product.getId());
            entityManager.remove(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
   
    public void removeById(final Long id) {
        try {
            Product product = getById(id);
            remove(product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Product getProduct(final Long id) {
        Product product = null;
        try {
            product = getById(id);    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }
    
    public void update(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
