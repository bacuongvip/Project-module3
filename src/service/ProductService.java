package service;

import model.CartItem;
import model.Category;
import model.Product;
import model.User;
import util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IGenericService<Product, Integer> {
    private List<Product> products;
    private DataBase<Product> productData = new DataBase();
    public ProductService() {
        List<Product> list = productData.readFromFile(DataBase.PRODUCT_PATH); // Doc du lieu tu file
        if(list == null) {
            list = new ArrayList<>();
        }
        this.products = list;
    }
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        if(findbyId(product.getId()) == null) {
            products.add(product);
        } else {
            products.set(products.indexOf(findbyId(product.getId())), product);
        }
        productData.writeToFile(products, DataBase.PRODUCT_PATH);
    }

    @Override
    public void delete(Integer id) {
        products.remove(findbyId(id));
        productData.writeToFile(products, DataBase.PRODUCT_PATH);
    }

    @Override
    public Product findbyId(Integer id) {
        for (Product product: products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public int getNewId(){
        int max = 0;
        for (Product product : products) {
            if (product.getId() > max) {
                max = product.getId();
            }
        }
        return max+1;
    }
}
