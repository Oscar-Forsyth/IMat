import javafx.fxml.FXML;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.ArrayList;
import java.util.List;

public class IMatBackendController {
    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();


    public List<Product> getProducts(){
        List<Product> products = iMatDataHandler.getProducts();
        return products;
    }
    public Product getSingleProduct(String name){
        for(Product product : getProducts() ){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    public List<Product> getProductsFromCategory(ProductCategory pc){
        List<Product> products = iMatDataHandler.getProducts(pc);
        return products;
    }
    public Image getImage(Product product){
        return iMatDataHandler.getFXImage(product);
    }
    public List<ShoppingItem> getShoppingList(){
        return shoppingCart.getItems();
    }
    public List<Product> searchProducts(String searchText){
        return iMatDataHandler.findProducts(searchText);
    }
    public void addToCart(Product product){
        if(!checkShoppingItem(product)){
            ShoppingItem shoppingItem = new ShoppingItem(product);
            shoppingItem.setAmount(1.0);
            shoppingCart.addItem(shoppingItem);

        }
    }

    public void removeAllProducts(){
        shoppingCart.clear();
    }
    public void removeProductFromCart(Product product){
        int index = getShoppingItemIndex(product);
        shoppingCart.removeItem(index);
    }
    public boolean removeFromCart(Product product){
        int index = getShoppingItemIndex(product);
        ShoppingItem shoppingItem = shoppingCart.getItems().get(index);
        if(shoppingItem.getAmount() == 1){
            shoppingCart.removeItem(index);
            return true;
        }
        else{
            double currentAmount = shoppingItem.getAmount();
            double removeAmount = 1.0;
            shoppingItem.setAmount(currentAmount - removeAmount);
        }
        return false;
    }
    public void printShoppingList(){
        for (int i = 0; i <= shoppingCart.getItems().size() - 1; i++) {
            System.out.println("Product: " + shoppingCart.getItems().get(i).getProduct().getName() + ". Amount: " + shoppingCart.getItems().get(i).getAmount());
        }

    }
    public double getAmount(Product product){
        int index = getShoppingItemIndex(product);
        if(index != -1){
            return shoppingCart.getItems().get(index).getAmount();

        }
        return -1;

    }
    private boolean checkShoppingItem(Product product){
        int index = getShoppingItemIndex(product);
        if(index != -1) {
            ShoppingItem shoppingItem = shoppingCart.getItems().get(index);
            if (shoppingItem.getProduct() == product) {
                double currentAmount = shoppingItem.getAmount();
                double newAmount = 1.0;
                shoppingItem.setAmount(currentAmount + newAmount);
                return true;
            }
        }
        return false;
    }
    public int getShoppingItemIndex(Product product){

        if(shoppingCart.getItems().isEmpty()){ //check if shopping cart is empty
            return -1;
        }
        for (int i = 0; i <= shoppingCart.getItems().size() - 1; i++) { //check if product already has a shopping item
            if(shoppingCart.getItems().get(i).getProduct() == product){
                return i;
            }
        }
        return -1;
    }
    public int getTotalValueOfProducts(){
        return (int) shoppingCart.getTotal();
    }

    public void addOrderTest(){
        System.out.println("Testing...");
        final ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();
        shoppingCart.addShoppingCartListener(new ShoppingCartListener() {
            public void shoppingCartChanged(CartEvent evt) {
                System.out.println("cart changed, total: " + shoppingCart.getTotal());
            }
        });
        shoppingCart.addProduct(iMatDataHandler.getProduct(69));
        shoppingCart.addProduct(iMatDataHandler.getProduct(72));
        shoppingCart.addProduct(iMatDataHandler.getProduct(80));
        iMatDataHandler.placeOrder(true);
    }
    public void clearOrders(){
        iMatDataHandler.reset();
        iMatDataHandler.getOrders().clear();
        System.out.println(iMatDataHandler.getOrders().isEmpty());
    }
    public List<Order> getOrders(){
        return iMatDataHandler.getOrders();}

    public String getEmail(){ return iMatDataHandler.getCustomer().getEmail(); }
    public String getFirstName(){ return iMatDataHandler.getCustomer().getFirstName(); }
    public String getLastName(){ return iMatDataHandler.getCustomer().getLastName(); }
    public String getAddress(){ return iMatDataHandler.getCustomer().getAddress(); }
    public String getPhoneNumber(){ return iMatDataHandler.getCustomer().getMobilePhoneNumber(); }
    public String getPostCode(){ return iMatDataHandler.getCustomer().getPostCode(); }
    public String getCardNumber(){ return iMatDataHandler.getCreditCard().getCardNumber(); }
    public int getValidMonth(){ return iMatDataHandler.getCreditCard().getValidMonth(); }
    public int getValidYear(){ return iMatDataHandler.getCreditCard().getValidYear(); }

    public void setEmail(String s){ iMatDataHandler.getCustomer().setEmail(s); }
    public void setFirstName(String s){  iMatDataHandler.getCustomer().setFirstName(s); }
    public void setLastName(String s){  iMatDataHandler.getCustomer().setLastName(s); }
    public void setAddress(String s){  iMatDataHandler.getCustomer().setAddress(s); }
    public void setPhoneNumber(String s){  iMatDataHandler.getCustomer().setMobilePhoneNumber(s); }
    public void setPostCode(String s){  iMatDataHandler.getCustomer().setPostCode(s); }
    public void setCardNumber(String s){  iMatDataHandler.getCreditCard().setCardNumber(s); }
    public void setValidMonth(int i){  iMatDataHandler.getCreditCard().setValidMonth(i); }
    public void setValidYear(int i){  iMatDataHandler.getCreditCard().setValidYear(i); }

    //end my profile
}
