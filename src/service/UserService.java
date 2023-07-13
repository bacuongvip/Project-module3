package service;

import config.Constants;
import model.User;
import util.DataBase;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IGenericService<User, Integer> {
    private List<User> users;
    private DataBase<User> userData = new DataBase();
    public UserService() {
        List<User> list = userData.readFromFile(DataBase.USER_PATH); // Doc du lieu tu file
        if(list == null) {
            list = new ArrayList<>();
        }
        this.users = list;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void save(User user) {
        if(findbyId(user.getId()) == null) {
            users.add(user);
        } else {
            users.set(users.indexOf(findbyId(user.getId())), user);
        }
        userData.writeToFile(users, DataBase.USER_PATH);
    }

    @Override
    public void delete(Integer id) {
        users.remove(findbyId(id));
        userData.writeToFile(users, DataBase.USER_PATH);
    }

    @Override
    public User findbyId(Integer id) {
        for (User user : users) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public void changeStatus(Integer id) {
        findbyId(id).setStatus(!findbyId(id).isStatus());
        save(findbyId(id));
    }
    public User login(String username, String password){
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public int getNewId(){
        int max = 0;
        for (User user : users) {
            if (user.getId() > max) {
                max = user.getId();
            }
        }
        return max+1;
    }
}
