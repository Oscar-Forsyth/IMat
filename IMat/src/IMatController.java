import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.*;

import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;

public class IMatController implements Initializable {
    private Map<String, ProductListItem> productListItemMap = new HashMap<String, ProductListItem>();
    private List<PreviousBuyItem> orders = new ArrayList<>();
    private ProductListItem productListItem;
    private PreviousBuyItem previousBuyItem;
    private IMatBackendController imatbc;

    @FXML private FlowPane listItemsFlowPane;
    @FXML private FlowPane previousBuyFlowPane;

    @FXML private AnchorPane ProductListItemAnchorPane;
    @FXML private AnchorPane MyPagesAnchorPane;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imatbc = new IMatBackendController();
        imatbc.addOrder();
        imatbc.addOrder();
        imatbc.addOrder();

        for(Product product : imatbc.getProducts()){
            ProductListItem productListItem = new ProductListItem(product,this);
            productListItemMap.put(product.getName(), productListItem);
            System.out.println(product.getName());
        }
            updateProductList();


        for(Order order: imatbc.getOrders()){
            PreviousBuyItem previousBuyItem = new PreviousBuyItem(order,this);
            orders.add(previousBuyItem);
            System.out.println(order.getOrderNumber());
        }
        updateOrderList();


    }

    private void updateProductList()  {
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProducts()){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }

    }
    private void updateOrderList()  {
        previousBuyFlowPane.getChildren().clear();
        for(PreviousBuyItem previousBuyItem : orders){
            previousBuyFlowPane.getChildren().add(previousBuyItem);
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
