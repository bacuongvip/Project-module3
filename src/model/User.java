package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String address;
    private String phone;
    private boolean status = true;
    private Set<RoleName> roles;
    private List<CartItem> cart = new ArrayList<>();

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public User() {
        roles = new HashSet<>();
        roles.add(RoleName.USER);
    }

    public User(int id, String username, String password, String address, String phone, boolean status, Set<RoleName> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<RoleName> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleName> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
            return "ID : " + id + " | Username : " + username + "   | Status : " + (status ? "Active" : "Disabled")
                    + "   | Role : " + roles;

    }
}
