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
import java.util.*;

import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import javafx.scene.control.Label;
import se.chalmers.cse.dat216.project.ShoppingItem;


public class IMatController implements Initializable {
    private Map<String, ProductListItem> productListItemMap = new HashMap<String, ProductListItem>();
    private ProductListItem productListItem;
    private List<PreviousBuyItem> orders = new ArrayList<>();
    private StringBuilder products = new StringBuilder();
    private StringBuilder prices = new StringBuilder();
    private PreviousBuyItem previousBuyItem;
    private IMatBackendController imatbc;
    private double price = 0;




    private MyPagesTextField myPagesTextField;
    private EditCreditsTextField editCreditsTextField;
    @FXML private FlowPane listItemsFlowPane;
    @FXML private AnchorPane ProductListItemAnchorPane;
    @FXML private AnchorPane MyPagesAnchorPane;
    @FXML private Label CategoryLabel;
    @FXML private FlowPane previousBuyFlowPane;
    @FXML private FlowPane textfieldFlowPane;
    @FXML private Button editCreditsButton;

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
    @FXML private Label totalPriceLabel;
    @FXML private Label pricesLabel;
    @FXML private Label productsLabel;
    @FXML private AnchorPane PreviousOrderAnchorPane;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imatbc = new IMatBackendController();
        homePagePaneToFront();
        imatbc.addOrderTest();
        imatbc.addOrderTest();
        imatbc.addOrderTest();
        imatbc.addOrderTest();

        for(Product product : imatbc.getProducts()){
            ProductListItem productListItem = new ProductListItem(product,this);
            productListItemMap.put(product.getName(), productListItem);
        }
        updateProductList();
        for(Order order: imatbc.getOrders()){
            PreviousBuyItem previousBuyItem = new PreviousBuyItem(order,this);
            orders.add(previousBuyItem);
        }
        Collections.reverse(orders);

        updateOrderList();
        textfieldFlowPane.getChildren().clear();
        MyPagesTextField myPagesTextField = new MyPagesTextField(this);
        textfieldFlowPane.getChildren().add(myPagesTextField);
        this.myPagesTextField=myPagesTextField;

    }



    public void navigationPaneToFront(){
        ProductsAnchorPane.toFront();
        HomepageBigAnchorPane.toBack();
        detailedViewPane.toBack();
    }
    public void homePagePaneToFront(){
        ProductsAnchorPane.toBack();
        HomepageBigAnchorPane.toFront();
        detailedViewPane.toBack();
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
    public void PreviousOrderToFront(Order order){
        PreviousOrderAnchorPane.toFront();
        productsLabel.setText(String.valueOf(getProductsFromOrder(order)));
        pricesLabel.setText(String.valueOf(getProductPrices(order)));
        totalPriceLabel.setText(String.valueOf(getOrderPrice(order)));
    }

    private double getOrderPrice(Order order){
        for(ShoppingItem shoppingItem: order.getItems()){
            price= (price + shoppingItem.getTotal());
        }
        return price;
    }
    private StringBuilder getProductsFromOrder(Order order){
        for(ShoppingItem shoppingItem: order.getItems()){
            products.append(shoppingItem.getProduct().getName());
            products.append(System.getProperty("line.separator"));
        }
        return products;
    }
    private StringBuilder getProductPrices(Order order){
        for(ShoppingItem shoppingItem: order.getItems()){
            prices.append(shoppingItem.getProduct().getPrice());
            prices.append(System.getProperty("line.separator"));
        }
        return prices;
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
    protected String getFirstname(){ return imatbc.getFirstName();}
    protected String getLastname(){ return imatbc.getLastName();}
    protected String getAddress(){ return imatbc.getAddress();}
    protected String getEmail(){ return imatbc.getEmail();}
    protected String getPhoneNumber(){ return imatbc.getPhoneNumber();}
    protected String getPostCode(){ return imatbc.getPostCode();}

    protected String getCardNumber(){ return imatbc.getCardNumber();}
    protected int getValidMonth(){ return imatbc.getValidMonth();}
    protected int getValidYear(){ return imatbc.getValidYear();}

    protected void setEmail(String s){ imatbc.setEmail(s); }
    protected void setFirstName(String s){  imatbc.setFirstName(s); }
    protected void setLastName(String s){  imatbc.setLastName(s); }
    protected void setAddress(String s){  imatbc.setAddress(s); }
    protected void setPhoneNumber(String s){  imatbc.setPhoneNumber(s); }
    protected void setPostCode(String s){  imatbc.setPostCode(s); }

    protected void setCardNumber(String s){  imatbc.setCardNumber(s); }
    protected void setValidMonth(int i){  imatbc.setValidMonth(i); }
    protected void setValidYear(int i){  imatbc.setValidYear(i); }


    @FXML private void saveCustomerInfoFromMyPages(){
        myPagesTextField.saveCustomerInfo();
        if(editCreditsTextField != null){
            editCreditsTextField.saveCreditsInfo();
        }

    }
    @FXML private void openEditCredits() {

        if (textfieldFlowPane.getChildren().contains(editCreditsTextField)){
            textfieldFlowPane.getChildren().remove(editCreditsTextField);
            textfieldFlowPane.getChildren().add(this.myPagesTextField);
            editCreditsButton.setText("Redigera kortuppgifter");

        }
        else{
            textfieldFlowPane.getChildren().clear();
            EditCreditsTextField editCreditsTextField = new EditCreditsTextField(this);
            textfieldFlowPane.getChildren().add(editCreditsTextField);
            this.editCreditsTextField=editCreditsTextField;
            editCreditsButton.setText("Tillbaka");
        }
    }
    @FXML private void openMyPages(){
        myPagesTextField.updateCustomerInfo();
        MyPagesAnchorPane.toFront();
    }

    @FXML private void closeMyPages(){
        MyPagesAnchorPane.toBack();
    }

    @FXML private void closeOrderItem(){
        PreviousOrderAnchorPane.toBack();
        prices.setLength(0);
        products.setLength(0);
        price=0;
    }
    @FXML private void buyOrderAgain(){
        //Lägg till i varukorgen TODO
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
