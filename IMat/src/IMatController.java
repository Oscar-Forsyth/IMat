import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import se.chalmers.cse.dat216.project.Product;

public class IMatController implements Initializable {
    private Map<String, ProductListItem> productListItemMap = new HashMap<String, ProductListItem>();
    private ProductListItem productListItem;
    private IMatBackendController imatbc;

    @FXML private FlowPane listItemsFlowPane;
    @FXML private AnchorPane ProductListItemAnchorPane;
    @FXML private AnchorPane MyPagesAnchorPane;




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
    public Image getImage(Product product){
        return imatbc.getImage(product);
    }

    @FXML public void openMyPages(){
        MyPagesAnchorPane.toFront();
    }
    @FXML public void closeMyPages(){
        MyPagesAnchorPane.toBack();
    }
}
