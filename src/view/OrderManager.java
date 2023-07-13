package view;

import config.Constants;
import config.InputMethods;
import config.Message;
import controller.CartController;
import controller.OrderController;
import controller.ProductController;
import model.CartItem;
import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private OrderController orderController;
    public OrderManager() {
        orderController= new OrderController();
        while (true){
            System.out.println("+--------------------------------------------------------------------------------------+");
            System.out.println("|                     ********** History Order **********                              |");
            System.out.println("+-----+--------------------------------------------------------------------------------+");
            System.out.println("|  1  | Show All Order.                                                                |");
            System.out.println("|  2  | Show Order Waiting.                                                            |");
            System.out.println("|  3  | Show Order Accepted.                                                           |");
            System.out.println("|  4  | Show Order Canceled.                                                           |");
            System.out.println("|  5  | Show Order Detail.                                                             |");
            System.out.println("|  0  | Back.                                                                          |");
            System.out.println("+-----+--------------------------------------------------------------------------------+");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllOrder();
                    break;
                case 2:
                    showOrderByCode((byte) 0);
                    break;
                case 3:
                    showOrderByCode((byte) 1);
                    break;
                case 4:
                    showOrderByCode((byte) 2);
                    break;
                case 5:
                    showOrderDetail();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("please enter number from 1 to 5");
            }
            if (choice == 0) {
                break;
            }
        }
    }
    public void showAllOrder(){
        List<Order> list = orderController.findOrderByUserId();
        if (list.isEmpty()){
            System.err.println("Order is empty");
            return;
        }
        for (Order o :list) {
            System.out.println(o);
        }
    }
    public void showOrderByCode(byte code){
        List<Order> orders = orderController.findOrderByUserId();
        List<Order> filter = new ArrayList<>();
        for (Order o :orders) {
            if(o.getStatus()==code){
                filter.add(o);
            }
        }
        if (filter.isEmpty()){
            System.err.println("Order " + Message.getStatusByCode(code) + " is empty");
            return;
        }
        for (Order o :filter) {
            System.out.println(o);
        }
    }
    public  void showOrderDetail(){
        ProductController productController = new ProductController();
        System.out.println("Enter order ID");
        int orderId = InputMethods.getInteger();
        Order order = orderController.findById(orderId);
        if (order ==null){
            System.err.println(Constants.NOT_FOUND);
            return;
        }
        System.out.printf("---------------------OrderDetail-----------------------\n");
        System.out.printf("                    Id:%2d                              \n",order.getId());
        System.out.println("--------------------Infomation--------------------------");
        System.out.print("Receiver: "+order.getReceiver() + "\n" + "Phone : "+order.getPhoneNumber()+"\n");
        System.out.println("Address : "+order.getAddress());
        System.out.println("--------------------Detail-------------------------------");
        for (CartItem ci: order.getOrderDetail()){
            System.out.println(ci);
        }
        System.out.println("Total : "+order.getTotal() + " $ ");
        System.out.println("------------------------End------------------------------");
        if(order.getStatus()==0){
            System.out.println("Do you want to cancel this order?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            if (choice==1){
                for (CartItem cartItem : order.getOrderDetail()) {
                    Product product = productController.findbyId(cartItem.getProduct().getId());
                    product.setStock(product.getStock() + cartItem.getQuantity());
                    productController.save(product);
                }
                order.setStatus((byte) 2);
                orderController.save(order);
                System.out.println("Order cancelled !!!");
            }
        }
    }
}
