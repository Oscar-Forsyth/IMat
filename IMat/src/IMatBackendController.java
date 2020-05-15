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
}
