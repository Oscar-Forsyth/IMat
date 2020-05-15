import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import java.io.IOException;

public class ProductListItem extends AnchorPane {
    private IMatController parentController;
    private Product product;

    @FXML private ImageView ImagePreview;
    @FXML private Label LabelPreview;
    @FXML private Label PricePreview;

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
        PricePreview.setText(product.getPrice()+ " " + product.getUnit());
    }
}
