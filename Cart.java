
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author shahr
 */
@SuppressWarnings("unchecked")
public class Cart {
    
    ArrayList<CartItem> cartItems = new ArrayList<>();    
    
    public Cart () {
    }
    
    public Cart (ArrayList cartItems) {
        this.cartItems = cartItems;
    }

    public void setCart (ArrayList cartItems) {
        this.cartItems = cartItems;
    }
    
    public ArrayList getCartItems() {
        return this.cartItems;
    }

    public String print() {
        String items = "";
        int index = 1;

        for (CartItem cartItem : cartItems) {

            items += " " + index + ") " + cartItem.getProduct().getName();

            index++;
        }

        return items;
    }
}
