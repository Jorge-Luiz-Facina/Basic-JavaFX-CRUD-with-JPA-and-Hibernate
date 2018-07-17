package view;

import java.util.List;
import produto.Product;
import produto.ProductDao;
import sale.Sale;
import sale.SaleDao;

public class InterfaceView {
    
    public void include(String name, double coastValue, double saleValue, int amount) {
        Product product = new Product();
        product.setName(name);
        product.setCoastValue(coastValue);
        product.setSaleValue(saleValue);
        product.setAmount(amount);
        ProductDao.getInstance().persist(product); 
    }
    
    public String getList() {
        return ProductDao.getInstance().findAll().toString();
    }
    
    public void delete(long id) {
        ProductDao.getInstance().removeById(id);
    }
    
    public void update(long id, String name, double coastValue, double saleValue, int amount) {
        Product product = getProduct(id);
        if(product != null){
           product.setName(name);
           product.setCoastValue(coastValue);
           product.setSaleValue(saleValue);
           product.setAmount(amount);
        }
        ProductDao.getInstance().update(product);
    }
    
    public String getListClient() {
        String listProducts = "";
        List<Product> products = ProductDao.getInstance().findAll();
        for(Product product : products){
            listProducts += "\nid: " + product.getId() + " name: " + product.getName() + " Amount: " + product.getAmount() + " Value: " + product.getSaleValue();
        }
        return listProducts;
    }
    
    public String getFinish(int amount, long idClient ) {
        int amountClient = amount;
        
        Product product = getProduct(idClient);
        int amountAdmin = product.getAmount();
        if(amountClient <= amountAdmin && amountClient > 0){
            updateAmountClient(idClient,amount);
            return setSale(product, amountClient);
        }else{
            return "Not enough stock!";
        }
    }
    
    private Product getProduct(Long id){
        Product product = ProductDao.getInstance().getProduct(id);
        return product;
    }
    
    private String setSale(Product product,int amount){
        Double valueTotal = product.getSaleValue() * amount;
        Sale sale = new Sale();
        sale.setIdProduct(product.getId());
        sale.setNameProduct(product.getName());
        sale.setValueTotal(valueTotal);
        SaleDao.getInstance().persist(sale);
        
        return valueTotal.toString();
    }
    
    private void updateAmountClient(Long id, int amount){
        Product product = getProduct(id);
        if(product != null){
           int newAmount = product.getAmount() - amount;
           product.setAmount(newAmount);
        }
        ProductDao.getInstance().update(product);   
    }
}

