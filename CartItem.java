/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author shahr
 */
public class CartItem {
    
    private Product product;
    private String productOptions;
    
    public CartItem (Product product, String productOptions) {
        this.product = product;
        this.productOptions = productOptions;
    }
    
    public void setProduct (Product product) {
        this.product = product;
    }
    
    public void setProductOptions (String productOptions) {
        this.productOptions = productOptions;
    }
    
    public Product getProduct() {
        return this.product;
    }
    
    public String getProductOptions() {
        return this.productOptions;
    }
}
