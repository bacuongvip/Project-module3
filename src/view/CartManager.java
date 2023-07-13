package view;

import config.Constants;
import config.InputMethods;
import controller.CartController;
import controller.OrderController;
import controller.ProductController;
import model.CartItem;
import model.Order;
import model.Product;
import model.User;
import service.UserService;

public class CartManager {
    private static CartController cartController;
    private ProductController productController;

    public CartManager() {
        productController = new ProductController();
        cartController = new CartController(NavBar.userLogin);
        while (true) {
            NavBar.menuCart();
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showCart();
                    break;
                case 2:
                    changeQuantity();
                    break;
                case 3:
                    deleteItem();
                    break;
                case 4:
                    cartController.clearAll();
                    System.out.println("Delete All Success !!!");
                    break;
                case 5:
                    checkout(productController);
                    break;
                case 6:
                    NavBar.menuUser();
                    break;
                default:
                    System.err.println("please enter number from 1 to 6");
            }

        }
    }

    public void showCart() {
        if (cartController.findAll().isEmpty()) {
            System.err.println("Cart is empty");
            return;
        }
        for (CartItem cartItem : cartController.findAll()) {
            System.out.println(cartItem);
        }

    }
    public void changeQuantity(){
        System.out.println("Enter CartItem Id");
        int cartItemID = InputMethods.getInteger();
        CartItem cartItem = cartController.findbyId(cartItemID);
        if(cartItem == null){
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        System.out.println("Enter Quantity");
        cartItem.setQuantity(InputMethods.getInteger());
        cartController.save(cartItem);
        System.out.println("Change Quantity Success !!!");
    }
    public void deleteItem(){
        System.out.println("Enter CartItem Id");
        int cartItemID = InputMethods.getInteger();
        if(cartController.findbyId(cartItemID) == null){
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        cartController.delete(cartItemID);
        System.out.println("Delete Successful !!!");
    }

    public static void addToCart() {
        cartController = new CartController(NavBar.userLogin);
        ProductController productController = new ProductController();
        System.out.println("Enter Product id");
        int id = InputMethods.getInteger();
        Product pro = productController.findbyId(id);
        if (pro == null) {
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(cartController.getNewId());
        cartItem.setProduct(pro);
        System.out.println("Enter quantity");
        cartItem.setQuantity(InputMethods.getIntegerQuantity());
        cartController.save(cartItem);
        System.out.println("Add Product Success !!!");
    }
    public void checkout(ProductController productController){
        OrderController orderController = new OrderController();
        User userLogin = NavBar.userLogin;
        if(userLogin.getCart().isEmpty()){
            System.err.println("Cart is Empty");
            return;
        }
        for (CartItem ci: userLogin.getCart()) {
            Product p = productController.findbyId(ci.getProduct().getId());
            if(ci.getQuantity() > p.getStock() ){
                System.err.println("Product: "+p.getName() + " stock "+ p.getStock() +" item, please decrease quantity");
                return;
            }
        }

        Order newOrder = new Order();
        newOrder.setId(orderController.getNewId());
        newOrder.setOrderDetail(userLogin.getCart());

        double total = 0;
        for (CartItem ci: userLogin.getCart()) {
            total+= ci.getQuantity()*ci.getProduct().getPrice();
        }
        newOrder.setTotal(total);

        newOrder.setUserId(userLogin.getId());
        System.out.println("Enter Name Receiver: ");
        newOrder.setReceiver(InputMethods.getString());
        System.out.println("Ennter Phone number: ");
        newOrder.setPhoneNumber(InputMethods.getNumberWithCriteria());
        System.out.println("Enter address: ");
        newOrder.setAddress(InputMethods.getString());
        orderController.save(newOrder);

        for (CartItem ci: userLogin.getCart()) {
            Product p = productController.findbyId(ci.getProduct().getId());
            p.setStock(p.getStock()-ci.getQuantity());
            productController.save(p);
        }
        cartController.clearAll();
        System.out.println("Check Out Success !!!");
    }
}
