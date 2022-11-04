
/**
 * Name: Shahrukh Saeed
 * ID: 501106701
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
@SuppressWarnings("unchecked")
public class ECommerceSystem {

    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;

    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    //String errMsg = null;

    // Random number generator
    Random random = new Random();

    public ECommerceSystem() {
        // NOTE: do not modify or add to these objects!! - the TAs will use for testing
        // If you do the class Shoes bonus, you may add shoe products

        try {
            readProds();
        } catch (IOException e) {
            System.out.println(e.getMessage()); 
            System.exit(1);
        }

        // Create some products. Notice how generateProductId() method is used
        // products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
        // products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
        // products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney"));
        // products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
        // products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
        // products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
        // products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast"));
        // products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive"));
        // products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney"));
        // products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));

        //Black, 6-1, Black, 7-1...Brown, 6-1, Brown, 7-1...10 
        //can leave blank if theres no stock or can fill in like Black, 6-0
        ArrayList<String> sizeStock = new ArrayList<>(Arrays.asList("Black, 6-1", "Black, 7-1", "", "", "", "", "", "", "", ""));
        products.add(new Shoes("Shoes", generateProductId(), 100.0, sizeStock, "6", "Addidas"));
        ArrayList<String> sizeStock1 = new ArrayList<>(Arrays.asList("Black, 6-1", "Black, 7-1", "Black, 8-5", "", "", "", "", "", "", ""));
        products.add(new Shoes("Shoes", generateProductId(), 120.0, sizeStock1, "8", "Nike"));

        // Create some customers. Notice how generateCustomerId() method is used
        customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
        customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
        customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
        customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }

    /**
     * Method Name: readProds 
     * Description: This method reads the details of the products
     * @throws IOException
     */
    private void readProds() throws IOException {
        String prodDetails = "";
        String[] prods;
        String[] elements;
        String[] subElements;
        String[] subElements1;
        
        //file to read
        File prodsFile = new File("products.txt");

        Scanner readFile = new Scanner(prodsFile);

        /*
        This loop reads the file and stores everything in the String prodDetails
         */
        while (readFile.hasNextLine()) {
            String toAdd = readFile.nextLine();

            if (toAdd.length() == 0) {
                prodDetails += " - ";
            } else {
                prodDetails += toAdd + ", ";
            }
        }
        readFile.close(); //closing

        prods = prodDetails.split(" - "); //each prod is split

        for (String prod : prods) {
            elements = prod.split(", "); //details of each prod is split

            if (elements[0].equalsIgnoreCase("BOOKS")) {

                subElements = elements[3].split(" ");//paperbackstock hardcoverstock
                subElements1 = elements[4].split(":");//title:author

                products.add(new Book(elements[1], generateProductId(), Double.parseDouble(elements[2]), 
                Integer.parseInt(subElements[0]), Integer.parseInt(subElements[1]), subElements1[0], subElements1[1]));
                
            } else {
                products.add(new Product(elements[1], generateProductId(), Double.parseDouble(elements[2]), 
                Integer.parseInt(elements[3]), Product.Category.valueOf(elements[0])));
            }
        }

    }// end of readProds

    private String generateOrderNumber() {
        return "" + orderNumber++;
    }

    private String generateCustomerId() {
        return "" + customerId++;
    }

    private String generateProductId() {
        return "" + productId++;
    }

    //public String getErrorMessage() {
    //    return errMsg;
    //}

    public void printAllProducts() {
        for (Product p : products) {
            p.print();
        }
    }

    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks() {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCategory() == Product.Category.BOOKS) {
                products.get(i).print();
            }
        }
    }

    // Print all current orders
    public void printAllOrders() {
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).print();
        }

    }

    // Print all shipped orders
    public void printAllShippedOrders() {
        for (int i = 0; i < shippedOrders.size(); i++) {
            shippedOrders.get(i).print();
        }
    }

    // Print all customers
    public void printCustomers() {
        for (int i = 0; i < customers.size(); i++) {
            customers.get(i).print();
        }
    }

    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId) {
        // Make sure customer exists - check using customerId
        // If customer does not exist, set errMsg String and return false
        // see video for an appropriate error message string
        // ... code here

        for (int i = 0; i < customers.size(); i++) {
            if (customerId.equals(customers.get(i).getId())) {
                break;
            } else if (!customerId.equals(customers.get(i).getId()) && i == customers.size() - 1) {
                throw new UnknownCustomerException("Customer " + customerId + " Not Found");
            }
        }

        // Print current orders of this customer 
        System.out.println("Current Orders of Customer " + customerId);
        // enter code here
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getCustomer().getId().equals(customerId)) {
                orders.get(i).print();
            }
        }

        // Print shipped orders of this customer 
        System.out.println("\nShipped Orders of Customer " + customerId);
        //enter code here
        for (int i = 0; i < shippedOrders.size(); i++) {
            if (shippedOrders.get(i).getCustomer().getId().equals(customerId)) {
                shippedOrders.get(i).print();
            }
        }
    }

    public String orderProduct(String productId, String customerId, String productOptions) {
        // First check to see if customer object with customerId exists in array list customers
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Customer object
        Customer cust = null;
        Product prod = null;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customerId)) {
                cust = customers.get(i);//     
                break;

            } else if (i == customers.size() - 1 && !customers.get(i).getId().equals(customerId)) {
                throw new UnknownCustomerException("Customer  ID " + customerId + " Not Found");
            }
        }

        // Check to see if product object with productId exists in array list of products
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Product object
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                prod = products.get(i);//     
                break;

            } else if (i == products.size() - 1 && !products.get(i).getId().equals(productId)) {
                throw new UnknownProductException("Product  ID " + productId + " Not Found");
            }
        }

        // Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
        // See class Product and class Book for the method vaidOptions()
        // If options are not valid, set errMsg string and return null;
        if (!prod.validOptions(productOptions)) {
            throw new InvalidProductOptionsException("Not Valid Option");
        }

        // Check if the product has stock available (i.e. not 0)
        // See class Product and class Book for the method getStockCount()
        // If no stock available, set errMsg string and return null
        if (prod.getStockCount(productOptions) == 0) {
            throw new NoStockException("No Stock");
        }

        // Create a ProductOrder, (make use of generateOrderNumber() method above)
        // reduce stock count of product by 1 (see class Product and class Book)
        // Add to orders list and return order number string
        prod.reduceStockCount(productOptions); //only reduced when we know stock count isn't 0

        String oNum = generateOrderNumber();
        orders.add(new ProductOrder(oNum, prod, cust, productOptions));

        return "Order #" + oNum;
    }

    /*
     * Create a new Customer object and add it to the list of customers
     */
    public void createCustomer(String name, String address) {
        // Check name parameter to make sure it is not null or ""
        // If it is not a valid name, set errMsg (see video) and return false
        // Repeat this check for address parameter

        if (name.isEmpty()) {
            throw new InvalidCustomerNameException("Not a Valid Name");
        }

        if (address.isEmpty()) {
            throw new InvalidCustomerAddressException("Not a Valid Address");
        }

        // Create a Customer object and add to array list
        customers.add(new Customer(generateCustomerId(), name, address));
    }

    public void shipOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
        // and return false
        // Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
        // return a reference to the order

        if (orders.isEmpty()) {
            throw new InvalidOrderNumberException("There are no orders.");
        }

        for (int i = 0; i < orders.size(); i++) {
            if (i == orders.size() - 1 && !orders.get(i).getOrderNumber().equals(orderNumber)) {
                throw new InvalidOrderNumberException("Order Number does not Exist");

            } else if (orders.get(i).getOrderNumber().equals(orderNumber)) {
                shippedOrders.add(orders.get(i));
                orders.remove(i);
                break;
            }
        }
    }

    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
        // and return false

        if (orders.isEmpty()) {
            throw new InvalidOrderNumberException("There are no orders.");
        }        

        for (int i = 0; i < orders.size(); i++) {
            if (i == orders.size() - 1 && !orders.get(i).getOrderNumber().equals(orderNumber)) {
                throw new InvalidOrderNumberException("Order Number does not Exist");

            } else if (orders.get(i).getOrderNumber().equals(orderNumber)) {
                orders.remove(i);
                break;
            }
        }
    }

    // Sort products by increasing price
    public void sortByPrice() {
        //selection sort
        double minP;//the minimum price
        Product prodMin;//the prod associated with the min. price
        int minIndex;//the index of the minimum price

        /*
        This loop uses selection sort in order to sort the P's in 
        ascending order, it also keeps the titles updated with the changing 
        indexes of the P's.
         */
        for (int i = 0; i < products.size(); i++) {

            minP = products.get(i).getPrice(); //minimum P is set
            minIndex = i; //minimum index is recorded

            prodMin = products.get(i); //following minP

            for (int j = i + 1; j < products.size(); j++) {

                if (products.get(j).getPrice() < minP) {
                    minP = products.get(j).getPrice(); //new min. P
                    minIndex = j; //new min. index

                    prodMin = products.get(j); //following minP
                }
            }

            products.set(minIndex, products.get(i));
            products.set(i, prodMin);
        }
        printAllProducts();
    }

    // Sort products alphabetically by product name
    public void sortByName() {
        //selection sort
        double minP;//the minimum char val
        Product prodMin;//the prod associated with the min. char val
        int minIndex;//the index of the minimum name

        /*
        This loop uses selection sort in order to sort the P's in 
        ascending order.
         */
        for (int i = 0; i < products.size(); i++) {

            minP = products.get(i).getName().charAt(0); //minimum val is set
            minIndex = i; //minimum index is recorded
            prodMin = products.get(i); //following minP
            for (int j = i + 1; j < products.size(); j++) {

                if (products.get(j).getName().charAt(0) < minP) {
                    minP = products.get(j).getName().charAt(0); //new min. P
                    minIndex = j; //new min. index

                    prodMin = products.get(j); //following minP
                }
            }

            products.set(minIndex, products.get(i));
            products.set(i, prodMin);
        }

        printAllProducts();
    }

    // Sort products alphabetically by product name
    public void sortCustomersByName() {
        //selection sort
        double minC;//the minimum char val
        Customer prodMin;//the cust associated with the min. char val
        int minIndex;//the index of the minimum name

        /*
        This loop uses selection sort in order to sort the C's in 
        ascending order.
         */
        for (int i = 0; i < customers.size(); i++) {

            minC = customers.get(i).getName().charAt(0); //minimum val is set
            minIndex = i; //minimum index is recorded
            prodMin = customers.get(i); //following minC
            for (int j = i + 1; j < customers.size(); j++) {

                if (customers.get(j).getName().charAt(0) < minC) {
                    minC = customers.get(j).getName().charAt(0); //new min. C
                    minIndex = j; //new min. index

                    prodMin = customers.get(j); //following minC
                }
            }

            customers.set(minIndex, customers.get(i));
            customers.set(i, prodMin);
        }

        printCustomers();
    }

    public String addToCart(String productId, String customerId, String productOptions) {

        Customer cust = null;
        Product prod = null;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customerId)) {
                cust = customers.get(i);//     
                break;

            } else if (i == customers.size() - 1 && !customers.get(i).getId().equals(customerId)) {
                throw new UnknownCustomerException("Customer  ID " + customerId + " Not Found");
            }
        }

        // Check to see if product object with productId exists in array list of products
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Product object
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                prod = products.get(i);//     
                break;

            } else if (i == products.size() - 1 && !products.get(i).getId().equals(productId)) {
                throw new UnknownProductException("Product  ID " + productId + " Not Found");
            }
        }

        // Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
        // See class Product and class Book for the method vaidOptions()
        // If options are not valid, set errMsg string and return null;
        if (!prod.validOptions(productOptions)) {
            throw new InvalidProductOptionsException("Not Valid Option");
        }

        // Check if the product has stock available (i.e. not 0)
        // See class Product and class Book for the method getStockCount()
        // If no stock available, set errMsg string and return null
        if (prod.getStockCount(productOptions) == 0) {
            throw new NoStockException("No Stock");
        }
      
        cust.getCart().getCartItems().add(new CartItem(prod, productOptions));

        return prod.getName() + " has been added to " + cust.getName() + "'s cart.";

    }

    public String remCartItem(String customerId, String productId) {
   
        Customer cust = null;
        Product prod = null;   

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customerId)) {
                cust = customers.get(i);//     
                break;

            } else if (i == customers.size() - 1 && !customers.get(i).getId().equals(customerId)) {
                throw new UnknownCustomerException("Customer  ID " + customerId + " Not Found");
            }
        }

        // Check to see if product object with productId exists in array list of products
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Product object
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                prod = products.get(i);//     
                break;

            } else if (i == products.size() - 1 && !products.get(i).getId().equals(productId)) {
                throw new UnknownProductException("Product  ID " + productId + " Not Found");
            }
        }

        if (cust.getCart().getCartItems().isEmpty()) {
            throw new UnknownCartItemException("The cart is empty");
        }           
        
        for (int i = 0; i < cust.getCart().getCartItems().size(); i++) {
            CartItem item = (CartItem) cust.getCart().getCartItems().get(i);
            
            if (item.getProduct().getId().equals(prod.getId())) {
                cust.getCart().getCartItems().remove(i);
                break;
            } else if (i == cust.getCart().getCartItems().size() - 1 && !item.getProduct().getId().equals(prod.getId())) {
                throw new UnknownCartItemException("Product  ID " + productId + " Not Found in Cart");
            }
        }

        return prod.getName() + " has been removed from " + cust.getName() + "'s cart."; 
    }

    public String printCart(String customerId) {
        Customer cust = null;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customerId)) {
                cust = customers.get(i);//
                break;

            } else if (i == customers.size() - 1 && !customers.get(i).getId().equals(customerId)) {
                throw new UnknownCustomerException("Customer  ID " + customerId + " Not Found");
            }
        }

        if (cust.getCart().getCartItems().isEmpty()) {
            throw new UnknownCartItemException("The cart is empty");
        }                   

        return cust.getCart().print();
    }

    public String orderItems(String customerId) {

        Customer cust = null;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customerId)) {
                cust = customers.get(i);//
                break;

            } else if (i == customers.size() - 1 && !customers.get(i).getId().equals(customerId)) {
                throw new UnknownCustomerException("Customer  ID " + customerId + " Not Found");
            }
        }

        if (cust.getCart().getCartItems().isEmpty()) {
            throw new UnknownCartItemException("The cart is empty");
        }

        for (int i = 0; i < cust.getCart().getCartItems().size(); i++) {
            CartItem item = (CartItem) cust.getCart().getCartItems().get(i);
            
            orderProduct(item.getProduct().getId(), customerId, item.getProductOptions());
        }    
        
        cust.getCart().getCartItems().clear();

        return "Orders successful. Cart is now empty.";
    }
}

class UnknownCustomerException extends RuntimeException {
    public UnknownCustomerException() {}

    public UnknownCustomerException(String message) {
        super(message);
    } 
}

class UnknownProductException extends RuntimeException {
    public UnknownProductException() {}

    public UnknownProductException(String message) {
        super(message);
    } 
}

class InvalidProductOptionsException extends RuntimeException {
    public InvalidProductOptionsException() {}

    public InvalidProductOptionsException(String message) {
        super(message);
    } 
}

class NoStockException extends RuntimeException {
    public NoStockException() {}

    public NoStockException(String message) {
        super(message);
    } 
}

class InvalidCustomerNameException extends RuntimeException {
    public InvalidCustomerNameException() {}

    public InvalidCustomerNameException(String message) {
        super(message);
    } 
}

class InvalidCustomerAddressException extends RuntimeException {
    public InvalidCustomerAddressException() {}

    public InvalidCustomerAddressException(String message) {
        super(message);
    } 
}

class InvalidOrderNumberException extends RuntimeException {
    public InvalidOrderNumberException() {}

    public InvalidOrderNumberException(String message) {
        super(message);
    } 
}

class UnknownCartItemException extends RuntimeException {
    public UnknownCartItemException() {}

    public UnknownCartItemException(String message) {
        super(message);
    }
}


