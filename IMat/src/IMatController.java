import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.io.IOException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

public class IMatController implements Initializable {
    private Map<String, ProductListItem> productListItemMap = new HashMap<String, ProductListItem>();
    private ProductListItem productListItem;
    private IMatBackendController imatbc;

    @FXML private FlowPane listItemsFlowPane;
    @FXML private AnchorPane ProductListItemAnchorPane;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imatbc = new IMatBackendController();


        for(Product product : imatbc.getProducts()){
            ProductListItem productListItem = new ProductListItem(product,this);
            productListItemMap.put(product.getName(), productListItem);
            System.out.println(product.getName());
        }
            updateProductList();


    }

    private void updateProductList()  {
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProducts()){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }

    }

}
