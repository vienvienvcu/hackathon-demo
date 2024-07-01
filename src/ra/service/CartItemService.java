package ra.service;

import ra.model.CartItem;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartItemService implements ICartItem {
    public static List<CartItem> cartItems = new ArrayList<>();
    public Product product ;

    @Override
    public List<CartItem> getAll() {
        return cartItems;
    }

    @Override
    public void save(CartItem cartItem) {
        CartItem existingCartItem = findByProduct(cartItem.getProduct());
        if (existingCartItem != null) {
            int newQuantity = existingCartItem.getQuantity() + cartItem.getQuantity();
            if (newQuantity <= cartItem.getProduct().getStock()) {
                existingCartItem.setQuantity(newQuantity);
                existingCartItem.setPrice(existingCartItem.getProduct().getProductPrice() * newQuantity);
                cartItem.getProduct().setStock(cartItem.getProduct().getStock() - cartItem.getQuantity());
            } else {
                System.out.println("Not enough stock available.");
            }
        } else {
            if (cartItem.getQuantity() <= cartItem.getProduct().getStock()) {
                cartItems.add(cartItem);
                cartItem.getProduct().setStock(cartItem.getProduct().getStock() - cartItem.getQuantity());
            } else {
                System.out.println("Not enough stock available.");
            }
        }
    }

    @Override
    public CartItem findById(Integer id) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == id){
                return cartItem;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        CartItem cartItem = findById(id);
        if (cartItem != null) {
            cartItem.getProduct().setStock(cartItem.getProduct().getStock() + cartItem.getQuantity());
            cartItems.remove(cartItem);
        }
    }

    public CartItem findByProduct(Product product) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                return cartItem;
            }
        }
        return null;
    }

    public void updateQuantity(int id, int newQuantity) {
        CartItem cartItem = findById(id);
        if (cartItem != null) {
            Product productUpdate = cartItem.getProduct();
            int availableStock = productUpdate.getStock() + cartItem.getQuantity();
            if (newQuantity <= availableStock) {
                productUpdate.setStock(availableStock - newQuantity);
                cartItem.setQuantity(newQuantity);
                cartItem.setPrice(productUpdate.getProductPrice() * newQuantity);
            } else {
                System.out.println("Not enough stock available.");
            }
        }
    }
}
