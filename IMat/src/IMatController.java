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

@FXML private FlowPane listItemsFlowPane;

private ProductListItem productListItem;
private IMatBackendController imatbc;
private AnchorPane ProductListItemAnchorPane;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Hi");
        /*for (Product product : imatbc.getProductsFromCategory(ProductCategory.FRUIT)) {
            productListItemMap.put(product.getName(), productListItem);
        }

        for(Product product : imatbc.getProducts()){
            ProductListItem productListItem = new ProductListItem(product,this);
            productListItemMap.put(product.getName(), productListItem);
            System.out.println(product.getName());
        }

         */
        try {
            updateProductList();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateProductList() throws IOException {

       /* listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProducts()){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        */
        FXMLLoader.load(getClass().getResource("product_listitem.fxml"));

    }

}
