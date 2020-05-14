import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.List;
import java.util.Random;

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
}
