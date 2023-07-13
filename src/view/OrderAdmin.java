package view;

import config.Constants;
import config.InputMethods;
import controller.OrderController;
import model.Order;
import util.DataBase;

import java.util.List;

public class OrderAdmin {
    private OrderController orderController;
    public  OrderAdmin() {
        orderController = new OrderController();
        while (true) {
            NavBar.menuOrder();
            System.out.println("| Enter your choice: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllOrder();
                    break;
                case 2:
                    orderAction();
                    break;
                case 3:
                    NavBar.menuAdmin();
                    break;
                default:
                    System.err.println("Please enter 1 to 3");
                    break;
            }
        }
    }
    public void showAllOrder(){
        List<Order> list = orderController.findAll();
        if (list.isEmpty()){
            System.err.println("Order is empty");
            return;
        }
        for (Order o :list) {
            System.out.println(o);
        }
    }
    public void orderAction(){
        DataBase<Order> orderData;
        List<Order> orders;
        orderData= new DataBase<>();
        orders = orderData.readFromFile(DataBase.ORDER_PATH);
        byte code;
        System.out.println("Enter Id Order");
        int id = InputMethods.getInteger();
        Order order = orderController.findByIdAdmin(id);
        if(order == null){
            System.err.println(Constants.NOT_FOUND);
        }else{
            System.out.println(order);
            NavBar.menuAction();
            System.out.println("| Enter your choice: ");
            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    code = 1;
                    order.setStatus(code);
                    orderController.save(order);
                    System.out.println("Confirm Success !!!");
                    break;
                case 2:
                    code = 2;
                    order.setStatus(code);
                    orderController.save(order);
                    System.out.println("Cancel Success !!!");
                    break;
                case 3:
                    break;
                default:
                    System.err.println("Please enter 1 to 3");
                    break;
            }
        }
    }
}
