import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import java.io.IOException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import javafx.scene.control.Label;



public class IMatController implements Initializable {
    private Map<String, ProductListItem> productListItemMap = new HashMap<String, ProductListItem>();
    private ProductListItem productListItem;
    private IMatBackendController imatbc;

    @FXML private FlowPane listItemsFlowPane;
    @FXML private AnchorPane ProductListItemAnchorPane;
    @FXML private AnchorPane MyPagesAnchorPane;
    @FXML private Label CategoryLabel;

    @FXML private Label fruitSideMenu;
    @FXML private Label vegSideMenu;
    @FXML private Label breadSideMenu;
    @FXML private Label dairySideMenu;
    @FXML private Label meatSideMenu;
    @FXML private Label fishSideMenu;
    @FXML private Label pantrySideMenu;
    @FXML private Label drinksSideMenu;
    @FXML private Label sweetsSideMenu;
    @FXML private Label otherSideMenu;
    @FXML private AnchorPane NavigationAnchorPane;
    @FXML private AnchorPane homepageBigAnchorPane;
    @FXML private AnchorPane ProductsAnchorPane;
    @FXML private AnchorPane HomepageBigAnchorPane;
    @FXML private ImageView exitViewPaneImage;
    @FXML private AnchorPane detailedViewPane;
    @FXML private AnchorPane shadowPane;
    @FXML private ImageView detailedViewProductImage;
    @FXML private Label detailedViewProductECO;
    @FXML private Label detailedViewProductTitle;
    @FXML private Label detailedViewProductPrice;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imatbc = new IMatBackendController();
        homePagePaneToFront();

        for(Product product : imatbc.getProducts()){
            ProductListItem productListItem = new ProductListItem(product,this);
            productListItemMap.put(product.getName(), productListItem);

        }


    }



    public void navigationPaneToFront(){
        ProductsAnchorPane.toFront();
        HomepageBigAnchorPane.toBack();
        detailedViewPane.toBack();
    }
    public void homePagePaneToFront(){
        ProductsAnchorPane.toBack();
        HomepageBigAnchorPane.toFront();
    }

    public void detailedViewPaneToFront(Product product){
        String isEco ="";
        if(product.isEcological()){
            isEco = "Ja";
        }
        else{
            isEco = "Nej";
        }
        detailedViewPane.toFront();
        detailedViewProductECO.setText("ECO: " + isEco);
        detailedViewProductImage.setImage(this.getImage(product));
        detailedViewProductPrice.setText(product.getPrice() + " " + product.getUnit());
        detailedViewProductTitle.setText(product.getName());

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

    public void checkColor(){
        switch(CategoryLabel.getText()){
            case "Frukt":
                fruitSideMenu.getStyleClass().clear();
                fruitSideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Grönsaker":
                vegSideMenu.getStyleClass().clear();
                vegSideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Bröd":
                breadSideMenu.getStyleClass().clear();
                breadSideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Mejeri":
                dairySideMenu.getStyleClass().clear();
                dairySideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Kött":
                meatSideMenu.getStyleClass().clear();
                meatSideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Fisk":
                fishSideMenu.getStyleClass().clear();
                fishSideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Skafferi":
                pantrySideMenu.getStyleClass().clear();
                pantrySideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Dryck":
                drinksSideMenu.getStyleClass().clear();
                drinksSideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Godis & snacks":
                sweetsSideMenu.getStyleClass().clear();
                sweetsSideMenu.getStyleClass().add("SideMenuListItems");
                break;
            case "Övrigt":
                otherSideMenu.getStyleClass().clear();
                otherSideMenu.getStyleClass().add("SideMenuListItems");
                break;

        }

    }

    public void fruit(){
        navigationPaneToFront();
        checkColor();
        fruitSideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Frukt");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.FRUIT)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.EXOTIC_FRUIT)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.CITRUS_FRUIT)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.BERRY)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.MELONS)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }
    public void veg(){
        navigationPaneToFront();
        checkColor();
        vegSideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Grönsaker");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.ROOT_VEGETABLE)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.CABBAGE)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.VEGETABLE_FRUIT)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }

    public void bread(){
        navigationPaneToFront();
        checkColor();
        breadSideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Bröd");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.BREAD)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }
    public void dairy(){
        navigationPaneToFront();
        checkColor();
        dairySideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Mejeri");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.DAIRIES)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }
    public void meat(){
        navigationPaneToFront();
        checkColor();
        meatSideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Kött");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.MEAT)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }
    public void fish(){
        navigationPaneToFront();
        checkColor();
        fishSideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Fisk");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.FISH)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }
    public void pantry(){
        navigationPaneToFront();
        checkColor();
        pantrySideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Skafferi");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.FLOUR_SUGAR_SALT)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.HERB)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.PASTA)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.NUTS_AND_SEEDS)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.POTATO_RICE)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }
    public void drinks(){
        navigationPaneToFront();
        checkColor();
        drinksSideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Dryck");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.COLD_DRINKS)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.HOT_DRINKS)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }
    public void sweets(){
        navigationPaneToFront();
        checkColor();
        sweetsSideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Godis & snacks");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.SWEET)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }
    }
    public void other(){
        navigationPaneToFront();
        checkColor();
        otherSideMenu.getStyleClass().add("SideMenuListItemsSelected");
        CategoryLabel.setText("Övrigt");
        listItemsFlowPane.getChildren().clear();
        for(Product product : imatbc.getProductsFromCategory(ProductCategory.POD)){
            listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
        }

    }
    @FXML
    public void closeButtonMouseEntered(){
        exitViewPaneImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "img/icon_close_hover.png")));
        exitViewPaneImage.setStyle("-fx-cursor:hand");
    }

    @FXML
    public void closeButtonMousePressed(){
        exitViewPaneImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "img/icon_close.png")));
        navigationPaneToFront();
    }

    @FXML
    public void closeButtonMouseExited(){
        exitViewPaneImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "img/icon_close.png")));
    }


}
