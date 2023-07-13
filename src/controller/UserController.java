package controller;

import model.User;
import service.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public List<User> findAll() {
        return userService.findAll();
    }

    public void save(User user) {
        userService.save(user);
    }

    public void delete(Integer id) {
        userService.delete(id);
    }

    public User findbyId(Integer id) {
        return userService.findbyId(id);
    }

    public void changeStatus(Integer id) {
        userService.changeStatus(id);
    }
    public User login(String username, String password){
        return userService.login(username, password);
    }
    public int getNewId(){
        return userService.getNewId();
    }
}
