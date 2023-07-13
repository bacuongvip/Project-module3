package view;

import config.InputMethods;
import controller.CategoryController;
import controller.ProductController;
import controller.UserController;
import model.RoleName;
import model.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NavBar {

    private static UserController userController = new UserController();
    private static ProductController productController = new ProductController();
    private static CategoryController categoryController = new CategoryController();
    public static User userLogin;


    public static void menuStore() {
        while (true) {
            System.out.println("+--------------------------------------------------------------------------------------+");
            System.out.println("|                     ********** Menu Store **********                                 |");
            System.out.println("+-----+--------------------------------------------------------------------------------+");
            System.out.println("|  1  | Login.                                                                         |");
            System.out.println("|  2  | Register.                                                                      |");
            System.out.println("|  3  | View Product.                                                                  |");
            System.out.println("|  4  | Exit.                                                                          |");
            System.out.println("+-----+--------------------------------------------------------------------------------+");
            System.out.println("| Enter your choice: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    ProductManager.showAllProduct();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Please enter 1 to 4");
            }
        }
    }

    public static void menuUser() {
        while (true) {
            System.out.println("+--------------------------------------------------------------------------------------+");
            System.out.println("|                     ********** Menu User **********                                  |");
            System.out.println("+-----+--------------------------------------------------------------------------------+");
            System.out.println("|  1  | Show list Product.                                                             |");
            System.out.println("|  2  | Add to cart.                                                                   |");
            System.out.println("|  3  | View Cart.                                                                     |");
            System.out.println("|  4  | Change Password.                                                               |");
            System.out.println("|  5  | Order history.                                                                 |");
            System.out.println("|  0  | Log Out.                                                                       |");
            System.out.println("+-----+--------------------------------------------------------------------------------+");
            System.out.println("Enter your choice: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    ProductManager.showAllProduct();
                    break;
                case 2:
                    CartManager.addToCart();
                    break;
                case 3:
                    new CartManager();
                    break;
                case 4:
                    changePassword();
                    break;
                case 5:
                    new OrderManager();
                    break;
                case 0:
                    logout();
                    break;
                default:
                    System.err.println("please enter number from 1 to 5");
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public static void menuAdmin() {
        while (true) {
            System.out.println("+--------------------------------------------------------------------------------------+");
            System.out.println("|                     ********** ADMIN **********                                      |");
            System.out.println("+-----+--------------------------------------------------------------------------------+");
            System.out.println("|  1  | Account Manager.                                                               |");
            System.out.println("|  2  | Category Manager.                                                              |");
            System.out.println("|  3  | Product Manager.                                                               |");
            System.out.println("|  4  | Order Manager.                                                                 |");
            System.out.println("|  0  | Logout.                                                                        |");
            System.out.println("+-----+--------------------------------------------------------------------------------+");
            System.out.println("| Enter your choice: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    new UserManager(userController);
                    break;
                case 2:
                    new CategoryManager(categoryController);
                    break;
                case 3:
                    new ProductManager(productController);
                    break;
                case 4:
                    new OrderAdmin();
                    break;
                case 0:
                    logout();
                default:
                    System.err.println("Please enter 1 to 5");
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public static void login() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                     ********** Login **********                                      |");
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("Enter Username: ");
        String username = InputMethods.getString();
        System.out.println("Enter Password: ");
        String password = InputMethods.getString();
        // check login
        User user = userController.login(username, password);
        if (user == null) {
            System.err.println("Account not exist!");
        } else {
            if (user.getRoles().contains(RoleName.ADMIN)) {
                userLogin = user;
                menuAdmin();
            } else {
                if (user.isStatus()) {
                    userLogin = user;
                    menuUser();
                } else {
                    System.err.println("Account is disabled");
                    login();
                }
            }
        }

    }

    public static void register() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                     ********** Register **********                                   |");
        System.out.println("+--------------------------------------------------------------------------------------+");
        User user = new User();
        user.setId(userController.getNewId());
        System.out.println("Enter Username: ");
        String username = InputMethods.getString();
        user.setUsername(username);
        System.out.println("Enter Password: ");
        user.setPassword(InputMethods.getInputPassword());
        for (User u : userController.findAll()) {
            if (u.getUsername().equals(username)) {
                System.err.println("Username existed");
                return;
            }
        }
        userController.save(user);
        System.out.println("Register successfully !!!");
        System.out.println("Please login !!!");
    }

    public static void logout() {
        userLogin = null;
        System.out.println("Logout successfully !!!");
        menuStore();
    }

    public static void menuAccountManager() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                     ********** Menu Account **********                               |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
        System.out.println("|  1  | Show All Account.                                                              |");
        System.out.println("|  2  | Disable/Enable Account.                                                        |");
        System.out.println("|  3  | Back.                                                                          |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
    }

    public static void menuCart() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                     ********** Menu Cart **********                                  |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
        System.out.println("|  1  | Show Cart.                                                                     |");
        System.out.println("|  2  | Change Quantity.                                                               |");
        System.out.println("|  3  | Delete Item.                                                                   |");
        System.out.println("|  4  | Delete All.                                                                    |");
        System.out.println("|  5  | Check Out.                                                                     |");
        System.out.println("|  6  | Back.                                                                          |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
    }

    public static void changePassword() {
        System.out.println("Enter Old Password: ");
        String oldPassword = InputMethods.getString();
        if (userLogin.getPassword().equals(oldPassword)) {
            System.out.println("Enter New Password: ");
            String newPassword = InputMethods.getInputPassword();
            if (newPassword.equals(oldPassword)) {
                System.err.println("New password same old password");
                return;
            }
            userLogin.setId(userLogin.getId());
            userLogin.setUsername(userLogin.getUsername());
            userLogin.setPassword(newPassword);
            userController.save(userLogin);
            System.out.println("Update Password Success !!!");
        } else {
            System.err.println("Password is incorrect, please enter again: ");
            changePassword();
        }
    }
    public static void menuCategory() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                     ********** Menu Category **********                              |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
        System.out.println("|  1  | Show All Category.                                                             |");
        System.out.println("|  2  | Add Category.                                                                  |");
        System.out.println("|  3  | Update Category.                                                               |");
        System.out.println("|  4  | Delete Category.                                                               |");
        System.out.println("|  5  | Back.                                                                          |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
    }
    public static void menuProduct() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                     ********** Menu Product **********                              |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
        System.out.println("|  1  | Show All Product.                                                             |");
        System.out.println("|  2  | Add Product.                                                                  |");
        System.out.println("|  3  | Update Product.                                                               |");
        System.out.println("|  4  | Delete Product.                                                               |");
        System.out.println("|  5  | Back.                                                                          |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
    }
    public static void menuOrder() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                     ********** Menu Order **********                                 |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
        System.out.println("|  1  | Show All Order.                                                                |");
        System.out.println("|  2  | Confirm / Cancel Order.                                                        |");
        System.out.println("|  3  | Back.                                                                          |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
    }
    public static void menuAction() {
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                     ********** Action **********                                     |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
        System.out.println("|  1  | Confirm.                                                                       |");
        System.out.println("|  2  | Cancel.                                                                        |");
        System.out.println("|  3  | Back.                                                                          |");
        System.out.println("+-----+--------------------------------------------------------------------------------+");
    }
}

