import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.ArrayList;
import java.util.List;

public class IMatBackendController {
    private static IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();
    private static ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();



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

    //Shopping cart
    ShoppingCartListener shoppingCartListener = new ShoppingCartListener() {
        @Override
        public void shoppingCartChanged(CartEvent cartEvent) {
            //do something
        }
    };

    public void addToCart(Product product){
        if(!checkShoppingItem(product)){
            ShoppingItem shoppingItem = new ShoppingItem(product);
            shoppingItem.setAmount(1.0);
            shoppingCart.addItem(shoppingItem);
            System.out.println(shoppingItem.getAmount());
        }
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
    private int getShoppingItemIndex(Product product){

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




    //end shopping cart
}
