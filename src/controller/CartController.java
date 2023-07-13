package controller;

import model.CartItem;
import model.User;
import service.CartService;

import java.util.List;

public class CartController {
    private CartService cartService;

    public CartController(User userLogin) {
        this.cartService = new CartService(userLogin);
    }
    public List<CartItem> findAll() {
        return cartService.findAll();
    }
    public void save(CartItem cartItem) {
        cartService.save(cartItem);
    }

    public void delete(Integer id) {
        cartService.delete(id);
    }
    public CartItem findbyId(Integer id) {
        return cartService.findbyId(id);
    }
    public int getNewId(){
        return cartService.getNewId();
    }
    public void clearAll(){
        cartService.clearAll();
    }
}
