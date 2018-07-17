package product;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import produto.Product;
import produto.ProductDao;

public class ProductTest {
    Product product = new Product();
    public ProductTest() {
    }
    
     @Before
    public void setUp() {
        product.setName("product");
        product.setCoastValue(1.9);
        product.setSaleValue(2.4);
        product.setAmount(5);
        ProductDao.getInstance().persist(product);
    }
    
    @After
    public void tearDown() { 
        ProductDao.getInstance().deleteAll();
    }

    @Test
    public void testInclude() {
        Product result = ProductDao.getInstance().findAll().get(0);
        assertEquals(product, result);
        
    }
    
    @Test
    public void testGetList() {
        ProductDao.getInstance().deleteAll();
        Product product01 = new Product();
        product01.setName("product01");
        product01.setCoastValue(1.3);
        product01.setSaleValue(2.1);
        product01.setAmount(7);
        
        Product product02 = new Product();
        product02.setName("product02");
        product02.setCoastValue(1.95);
        product02.setSaleValue(2.32);
        product02.setAmount(3);
        
        List<Product> products = new ArrayList<>();
        products.add(product01);
        products.add(product02);
        ProductDao.getInstance().persist(product01);
        ProductDao.getInstance().persist(product02);
        assertEquals(products, ProductDao.getInstance().findAll());
    }

    @Test
    public void testDelete() {
        Product product = ProductDao.getInstance().findAll().get(0);
        ProductDao.getInstance().removeById(product.getId());
        assertEquals(0,ProductDao.getInstance().findAll().size());
    }

    @Test
    public void testUpdate() {
        Product product = ProductDao.getInstance().findAll().get(0);
        if(product != null){
           product.setName("test");
           product.setCoastValue(0.6);
           product.setSaleValue(0.9);
           product.setAmount(2);
        }
        ProductDao.getInstance().update(product);
        assertEquals(product,ProductDao.getInstance().findAll().get(0));
    }
}
