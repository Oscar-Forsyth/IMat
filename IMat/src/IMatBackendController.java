import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.List;

public class IMatBackendController {
    private String productName;
    static IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    public List<Product> getProducts(){
        List<Product> products = iMatDataHandler.getProducts();
        return products;
    }

    public List<Product> getProductsFromCategory(ProductCategory pc){
        List<Product> products = iMatDataHandler.getProducts(pc);
        return products;
    }
    public Image getImage(Product product){
        return iMatDataHandler.getFXImage(product);
    }


public void addOrder(){
    System.out.println("Testing...");
    final ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();
    shoppingCart.addShoppingCartListener(new ShoppingCartListener() {
        public void shoppingCartChanged(CartEvent evt) {
            System.out.println("cart changed, total: " + shoppingCart.getTotal());
        }
    });
    shoppingCart.addProduct(iMatDataHandler.getProduct(80));
    shoppingCart.addProduct(iMatDataHandler.getProduct(80));
    shoppingCart.addProduct(iMatDataHandler.getProduct(80));
    iMatDataHandler.placeOrder(true);
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

}
