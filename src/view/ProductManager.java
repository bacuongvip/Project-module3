package view;

import config.Constants;
import config.InputMethods;
import controller.CategoryController;
import controller.ProductController;
import model.Category;
import model.Product;

public class ProductManager {
    private ProductController productController;
    public static void showAllProduct(){
        ProductController productController = new ProductController();
        if (productController.findAll().isEmpty()) {
            System.err.println("Product is empty");
        } else {
            for (Product product: productController.findAll()) {
                if (product.isStatus()) {
                    System.out.println(product);
                }
            }
        }
    }
    public ProductManager(ProductController productController) {
        this.productController = productController;
        while (true){
            NavBar.menuProduct();
            System.out.println("| Enter your choice: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllProductAdmin();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
//                    updateCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    NavBar.menuAdmin();
                    break;
                default:
                    System.err.println("Please enter 1 to 5");
            }
        }
    }
    public void showAllProductAdmin(){
        ProductController productController = new ProductController();
        if (productController.findAll().isEmpty()) {
            System.err.println("Product is empty");
        } else {
            for (Product product: productController.findAll()) {
                System.out.println(product);
            }
        }
    }
    public void addProduct(){
        Product product = new Product();
        product.setId(productController.getNewId());
        System.out.println("Enter name product: ");
        product.setName(InputMethods.getString());
        System.out.println("Enter price product: ");
        product.setPrice(InputMethods.getIntegerQuantity());
        System.out.println("Enter capacity product: ");
        product.setCapacity(InputMethods.getIntegerQuantity());
        System.out.println("Enter stock product: ");
        product.setStock(InputMethods.getIntegerQuantity());
        System.out.println("List Category: ");
        CategoryController categoryController = new CategoryController();
        for (Category category : categoryController.findAll()) {
            System.out.println(category);
        }
        boolean check = false;
        while (true) {
            System.out.println("Enter id category: ");
            int id = InputMethods.getInteger();
            for (Category category : categoryController.findAll()) {
                if (category.getId() == id) {
                    check = true;
                    product.setCategory(category);
                }
            }
            if (check) {
                break;
            } else {
                System.err.println(Constants.NOT_FOUND);
            }
        }
        productController.save(product);
        System.out.println("Add Product Success !!!");
    }
//    public void updateCategory(){
//        System.out.println("Enter Id Category: ");
//        int id = InputMethods.getInteger();
//        if(categoryController.findbyId(id) == null){
//            System.err.println(Constants.NOT_FOUND);
//            return;
//        } else {
//            System.out.println("Enter category: ");
//            String cate = InputMethods.getString();
//            for (Category category : categoryController.findAll()) {
//                if (category.getName().equals(cate)){
//                    System.err.println("Category exists !!!");
//                    return;
//                }
//            }
//            categoryController.findbyId(id).setName(cate);
//        }
//        categoryController.save(categoryController.findbyId(id));
//        System.out.println("Update Success !!!");
//    }
    public void deleteCategory(){
        System.out.println("Enter Id Category: ");
        int id = InputMethods.getInteger();
        if(productController.findbyId(id) == null){
            System.err.println(Constants.NOT_FOUND);
        } else {
            productController.delete(id);
            System.out.println("Delete Product Success !!!");
        }
    }
}
