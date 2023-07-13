package service;

import model.CartItem;
import model.User;

import java.util.List;

public class CartService implements IGenericService<CartItem, Integer> {
    private User userLogin;
    private UserService userService;

    public CartService(User userLogin) {
        this.userLogin = userLogin;
        userService = new UserService();
    }

    @Override
    public List<CartItem> findAll() {
        return userLogin.getCart();
    }

    @Override
    public void save(CartItem cartItem) {
        if (findbyId(cartItem.getId()) == null) {
            CartItem ci = findByProductId(cartItem.getProduct().getId());
            if (ci != null) {
                ci.setQuantity(ci.getQuantity() + cartItem.getQuantity());
            } else {
                userLogin.getCart().add(cartItem);
            }
        } else {
            userLogin.getCart().set(userLogin.getCart().indexOf(findbyId(cartItem.getId())), cartItem);
        }
        userService.save(userLogin);
    }

    @Override
    public void delete(Integer id) {
        userLogin.getCart().remove(findbyId(id));
        userService.save(userLogin);
    }

    @Override
    public CartItem findbyId(Integer id) {
        for (CartItem cartItem: userLogin.getCart()) {
            if (cartItem.getId() == id) {
                return cartItem;
            }
        }
        return null;
    }
    public CartItem findByProductId(int productId){
        for (CartItem ci : userLogin.getCart()) {
            if(ci.getProduct().getId()==productId){
                return  ci;
            }
        }
        return  null;
    }
    public int getNewId(){
        int max = 0;
        for (CartItem cartItem : userLogin.getCart()) {
            if (cartItem.getId() > max) {
                max = cartItem.getId();
            }
        }
        return max+1;
    }
    public void clearAll(){
        userLogin.getCart().clear();
        userService.save(userLogin);
    }
}
