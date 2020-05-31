import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import java.io.IOException;

public class ProductListItem extends AnchorPane {
    private IMatController parentController;
    private Product product;
    private IMatBackendController imatbc = new IMatBackendController();

    @FXML private Button listItemAddToCartButton;
    @FXML private AnchorPane amountSelectorPane;
    @FXML private ImageView ImagePreview;
    @FXML private Label LabelPreview;
    @FXML private Label PricePreview;
    @FXML private Label listItemAmountOfProductsLabel;

    public ProductListItem(Product product, IMatController iMatController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = iMatController;
        this.product = product;
        ImagePreview.setImage(parentController.getImage(product));
        LabelPreview.setText(product.getName());
        PricePreview.setText((int)product.getPrice()+ " " + product.getUnit());
    }
     public void openDetailView(){
        parentController.detailedViewPaneToFront(product);
    }
    public void listItemAddToCartButtonToFront(){
        listItemAddToCartButton.toFront();
    }
    public void amountSelectorPaneToFront(){
        amountSelectorPane.toFront();
    }
    public void addToCartFirstButton(){
        amountSelectorPaneToFront();
        addToCart();
    }
    public void addToCart(){
        imatbc.addToCart(product);
        parentController.updateLabel();
        updateLabel();

    }
    public void removeFromCart(){
        if(imatbc.removeFromCart(product)){
            listItemAddToCartButtonToFront();
        }
        parentController.updateLabel();
        updateLabel();
    }
    public void updateLabel(){
        if(imatbc.getShoppingItemIndex(product) != -1){
            amountSelectorPaneToFront();
            listItemAmountOfProductsLabel.setText(imatbc.getAmount(product) + "");
        }
        else{
            listItemAddToCartButton.toFront();
        }

    }
    public Product getProduct(){
        return product;
    }



}
