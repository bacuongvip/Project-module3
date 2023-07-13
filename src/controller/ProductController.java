package controller;

import model.Product;
import service.ProductService;

import java.util.List;

public class ProductController {

    private ProductService productService;

    public ProductController() {
        productService = new ProductService();
    }

    public List<Product> findAll() {
        return productService.findAll();
    }

    public void save(Product product) {
        productService.save(product);
    }

    public void delete(Integer id) {
        productService.delete(id);
    }

    public Product findbyId(Integer id) {
        return productService.findbyId(id);
    }
    public int getNewId(){
        return productService.getNewId();
    }
}
