import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShoppingBagItem extends AnchorPane {
    private IMatController parentController;
    private ShoppingItem shoppingItem;
    private IMatBackendController imatbc = new IMatBackendController();


    @FXML private Label productLabel;
    @FXML private Label price;
    @FXML private ImageView productImage;
    @FXML private Label listItemAmountOfProductsLabel;

    public ShoppingBagItem(ShoppingItem shoppingItem, IMatController iMatController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingBagItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = iMatController;
        this.shoppingItem = shoppingItem;

        productLabel.setText(shoppingItem.getProduct().getName());
        productImage.setImage(iMatController.getImage(shoppingItem.getProduct()));
        price.setText(shoppingItem.getTotal() +" kr");
        listItemAmountOfProductsLabel.setText(String.valueOf(shoppingItem.getAmount()));


    }
    public void addToCart(){
        parentController.changeProductListAmount(shoppingItem,true);
        updateLabel();
    }
    public void removeFromCart(){
        if(shoppingItem.getAmount()==1){
            parentController.deleteShoppingItem(this,shoppingItem);
        }
        else if(shoppingItem.getAmount()>1) {
            parentController.changeProductListAmount(shoppingItem, false);
            updateLabel();
        }
    }
    public void delete(){
        shoppingItem.setAmount(1);
        parentController.deleteShoppingItem(this,shoppingItem);

    }
    private void updateLabel(){
        productLabel.setText(shoppingItem.getProduct().getName());
        productImage.setImage(parentController.getImage(shoppingItem.getProduct()));
        price.setText(shoppingItem.getTotal() +" kr");
        listItemAmountOfProductsLabel.setText(String.valueOf(shoppingItem.getAmount()));
        //System.out.println(imatbc.getAmount(product));

    }


}

