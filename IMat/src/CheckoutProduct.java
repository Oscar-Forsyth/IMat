import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class CheckoutProduct extends AnchorPane {
    private IMatController parentController;
    private Product product;
    private IMatBackendController imatbc = new IMatBackendController();

    @FXML private ImageView listItemImage;
    @FXML private Button listItemRemoveButton;
    @FXML private Button listItemAddButton;
    @FXML private Label listItemNameLabel;
    @FXML private Label listItemPriceLabel;
    @FXML private Label listItemTotalPriceLabel;
    @FXML private Label listItemProductAmountLabel;



    public CheckoutProduct(IMatController iMatController, Product product) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkoutProduct.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            System.out.println("checkOutProduct");
            throw new RuntimeException(exception);
        }

        this.parentController = iMatController;
        this.product = product;

        listItemImage.setImage(parentController.getImage(product));
        listItemNameLabel.setText(product.getName());
        listItemPriceLabel.setText((int)product.getPrice()+ " " + product.getUnit());


    }
    public void setLabels(){
        int totalPrice = (int) (product.getPrice() * imatbc.getAmount(product));
        listItemTotalPriceLabel.setText(totalPrice + " kr");
        listItemProductAmountLabel.setText(imatbc.getAmount(product)+ "");
    }
        @FXML public void addToCart(){
            imatbc.addToCart(product);
            parentController.goToCheckOut();;
        }
        @FXML public void removeFromCart(){
            if(imatbc.removeFromCart(product)){
                parentController.goToCheckOut();
            }
            setLabels();
            parentController.goToCheckOut();;
        }
        @FXML public void removeProduct(){
            imatbc.removeProductFromCart(product);
            parentController.goToCheckOut();
        }





}
