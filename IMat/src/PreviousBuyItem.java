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

public class PreviousBuyItem extends AnchorPane {
    private IMatController parentController;
    private Order order;
    private double price=0;

    @FXML private Label dateLabel;
    @FXML private Label priceLabel;

    public PreviousBuyItem(Order order, IMatController iMatController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("previous_buy.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = iMatController;
        this.order = order;

        dateLabel.setText(String.valueOf(order.getDate().getYear()+1900)+"-"+String.valueOf(order.getDate().getMonth()+1)+"-"+String.valueOf(order.getDate().getDate()));
        priceLabel.setText(getPrice() +" kr");

    }

    private double getPrice(){
        for(ShoppingItem shoppingItem: order.getItems()){
            price= (price + shoppingItem.getTotal());
        }
        return price;
    }
    @FXML protected void openPreviousOrder(){
        parentController.PreviousOrderToFront(order);
    }

}

