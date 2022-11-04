
/**
 * Name: Shahrukh Saeed
 * ID: 501106701
 */

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shahr
 */
@SuppressWarnings("unchecked")
public class Shoes extends Product {

    private String size;
    private String brand;
    
    // Stock related information 
    ArrayList<String> sizeStock = new ArrayList<>(10);

    public Shoes(String name, String id, double price, ArrayList sizeStock, String size, String brand) {
        // Make use of the constructor in the super class Product. Initialize additional Shoe instance variables. 
        // Set category to SHOES 
        super(name, id, price, 100000, Product.Category.SHOES);
        this.size = size;
        this.brand = brand;
        this.sizeStock = sizeStock;
    }

    // Check if a valid format  
    public boolean validOptions(String productOptions) {
        // check productOptions for "Black" or "Brown" with available sizes
        // if it is one of these, return true, else return false
        String[] cols = productOptions.split(", ");

        if ((cols[0].contains("Black") || cols[0].contains("Brown")) && (cols[1].contains("6") || cols[1].contains("7") || cols[1].contains("8") || cols[1].contains("9") || cols[1].contains("10"))) {
            return true;
        }
        return false;
    }

    // Override getStockCount() in super class.
    public int getStockCount(String productOptions) {
        // Use the productOptions to check for (and return) the number of stock for "Black, 6" etc

        String[] cols;

        for (String i : sizeStock) {
            if (i.contains(productOptions)) {
                cols = i.split("-");
                return Integer.parseInt(cols[1]);
            }
        }
        return 0;
    }

    public void setStockCount(int stockCount, String productOptions) {
        // Use the productOptions to check for (and set) the number of stock for "Black, 6" etc

        for (int i = 0; i < sizeStock.size(); i++) {
            if (sizeStock.get(i).contains(productOptions)) {
                sizeStock.set(i, productOptions + "-" + stockCount);
            }
        }
    }

    /*
   * When a shoe is ordered, reduce the stock count for the specific stock type
     */
    public void reduceStockCount(String productOptions) {
        // Use the productOptions to check for (and reduce) the number of stock for "Black, 6" etc
        String[] cols;
        int nStock;

        for (int i = 0; i < sizeStock.size(); i++) {
            if (sizeStock.get(i).contains(productOptions)) {
                cols = sizeStock.get(i).split("-");
                nStock = Integer.parseInt(cols[1]) - 1;
                sizeStock.set(i, productOptions + "-" + nStock);
            }
        }
    }

    /*
   * Print product information in super class and append Shoe specific information title and author
     */
    public void print() {
        // Replace the line below.
        // Make use of the super class print() method and append the brand and size info.
        super.print();
        System.out.print(" Shoe Brand: " + brand + " Size: " + size);
    }
}
