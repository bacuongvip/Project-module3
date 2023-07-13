package view;

import config.Constants;
import config.InputMethods;
import controller.UserController;
import model.User;

public class UserManager {
    private UserController userController;

    public UserManager(UserController userController) {
        this.userController = userController;
        while (true){
            NavBar.menuAccountManager();
            System.out.println("| Enter your choice: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showAllAccount();
                    break;
                case 2:
                    changeStatus();
                    break;
                case 3:
                    NavBar.menuAdmin();
                    break;
                default:
                    System.err.println("Please enter 1 to 3");
            }
        }
    }
    public void showAllAccount() {
        for (User user: userController.findAll()) {
            System.out.println(user);
        }
    }
    public void changeStatus(){
        System.out.println("Enter Account Id: ");
        int id = InputMethods.getInteger();
        if(userController.findbyId(id) == null){
            System.err.println(Constants.NOT_FOUND);
        } else if (userController.findbyId(id).getId() == 1){
            System.err.println("Cannot disable account admin");
        } else {
            userController.changeStatus(id);
            System.out.println("Account Id " + id + " is " + (userController.findbyId(id).isStatus() ? "unlocked" : "disabled"));
        }
    }
}
