import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javafx.event.*;
import java.io.IOException;

import java.net.URL;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.*;

import javafx.scene.layout.Pane;
import javafx.util.converter.DateTimeStringConverter;
import se.chalmers.cse.dat216.project.*;


public class IMatController implements Initializable {
    private Map<String, ProductListItem> productListItemMap = new HashMap<String, ProductListItem>();
    private Map<String, CheckoutProduct> checkoutProductListItemMap = new HashMap<String, CheckoutProduct>();
    private List<PreviousBuyItem> orders = new ArrayList<>();
    private List<ShoppingBagItem> shoppingBagItems = new ArrayList<>();
    private List<ShoppingItem> latestbuy = new ArrayList<>();
    private StringBuilder products = new StringBuilder();
    private StringBuilder prices = new StringBuilder();
    private StringBuilder amounts = new StringBuilder();
    private Order order;
    private double price = 0;

    private PreviousBuyItem previousBuyItem;
    private IMatBackendController imatbc;
    private ProductListItem productListItem;
    private MyPagesTextField myPagesTextField;
    private EditCreditsTextField editCreditsTextField;
    private MyPagesTextField PayTextField;
    private EditCreditsTextField PayCreditsTextField;
    private Product currentProduct;
    private DatePicker datePicker;
    private DatePicker datePicker1;

    private final ToggleGroup radioButtonGroup = new ToggleGroup();

    @FXML private TextField searchTextField;
    @FXML private TextField cvcTextField;
    @FXML private TextField timeTextField;

    @FXML private RadioButton homeDeliveryRadioButton;
    @FXML private RadioButton pickupAtStoreRadioButton;

    @FXML private Button editCreditsButton;
    @FXML private Button detailedViewAddtoCartButton;
    @FXML private Button searchButton;
    @FXML private Button buyAgainButton;

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
    @FXML private Label detailedViewProductECO;
    @FXML private Label detailedViewProductTitle;
    @FXML private Label detailedViewProductPrice;
    @FXML private Label totalPriceLabel;
    @FXML private Label pricesLabel;
    @FXML private Label productsLabel;
    @FXML private Label amountLabel;
    @FXML private Label detailedViewAmountOfProductsLabel;
    @FXML private Label checkoutTotalPrice;
    @FXML private Label checkoutTotalPriceNoTax;
    @FXML private Label checkoutTax;
    @FXML private Label checkoutTransportFees;
    @FXML private Label checkoutTotalPriceWithFees;
    @FXML private Label personalInformationSavedLabel;
    @FXML private Label thankYouScreenTransportFee;
    @FXML private Label thankYouScreenTax;
    @FXML private Label thankYouScreenPriceNoTax;
    @FXML private Label thankYouScreenTotalPayed;
    @FXML private Label totalPriceShoppingLabel;



    @FXML private FlowPane listItemsFlowPane;
    @FXML private FlowPane previousBuyFlowPane;
    @FXML private FlowPane textfieldFlowPane;
    @FXML private FlowPane shoppingBagFlowPane;
    @FXML private FlowPane PayTextFieldFlowPane;
    @FXML private FlowPane PayCreditsFlowPane;
    @FXML private FlowPane homePageListItemsFlowPane;
    @FXML private FlowPane checkOutProductListPane;
    @FXML private FlowPane thankYouComeAgain;

    @FXML private AnchorPane MyPagesAnchorPane;
    @FXML private AnchorPane NavigationAnchorPane;
    @FXML private AnchorPane homepageBigAnchorPane;
    @FXML private AnchorPane ProductsAnchorPane;
    @FXML private AnchorPane HomepageBigAnchorPane;
    @FXML private AnchorPane PreviousOrderAnchorPane;
    @FXML private AnchorPane detailedViewPane;
    @FXML private AnchorPane shadowPane;
    @FXML private AnchorPane shadowPane1;
    @FXML private AnchorPane ProductListItemAnchorPane;
    @FXML private AnchorPane detailedViewAmountSelectorPane;
    @FXML private AnchorPane searchPane;
    @FXML private AnchorPane checkoutPane;
    @FXML private AnchorPane homeDeliverySecondStepPane;
    @FXML private AnchorPane pickupAtStoreSecondStepPane;
    @FXML private AnchorPane PayStepThreeAnchorPane;
    @FXML private AnchorPane thankYouScreen;
    @FXML private AnchorPane ShoppingBagAnchorPane;


    @FXML private ImageView exitViewPaneImage;
    @FXML private ImageView detailedViewProductImage;

    @FXML private Pane lonelyPane;
    @FXML private Pane datePickerPane;
    @FXML private Pane datePickerPanePickUpAtStore;

    @FXML private ComboBox comboBoxStores;

    private int transportFee = 0;
    private boolean onHomePage = true;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imatbc = new IMatBackendController();
        pickupAtStoreRadioButton.setToggleGroup(radioButtonGroup);
        homeDeliveryRadioButton.setToggleGroup(radioButtonGroup);
        homeDeliveryRadioButton.setSelected(true);
        setStartingPrices();
        System.out.println(getFirstname());

        for(Product product : imatbc.getProducts()){
            ProductListItem productListItem = new ProductListItem(product,this);
            productListItemMap.put(product.getName(), productListItem);
        }
        for(Product product : imatbc.getProducts()){
            CheckoutProduct checkoutProduct = new CheckoutProduct(this,product);
            checkoutProductListItemMap.put(product.getName(), checkoutProduct);
        }


        updateOrderList();
        textfieldFlowPane.getChildren().clear();
        MyPagesTextField myPagesTextField = new MyPagesTextField(this);
        textfieldFlowPane.getChildren().add(myPagesTextField);
        this.myPagesTextField=myPagesTextField;

        setStartingProducts();
        homePagePaneToFront();
        openDatePicker();
        updateShoppingBag();
        updatePayStepThree();

        TextField tf = timeTextField;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            tf.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("12:30")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void thankYouForYourPurchase(){
        thankYouScreen.toFront();
        thankYouComeAgain.getChildren().clear();
        for (ShoppingItem item : imatbc.getShoppingList()) {
            thankYouComeAgain.getChildren().add(checkoutProductListItemMap.get(item.getProduct().getName()));
            checkoutProductListItemMap.get(item.getProduct().getName()).setLabels();
        }
        int tax = (int) (imatbc.getTotalValueOfProducts() * 0.12);
        thankYouScreenPriceNoTax.setText(imatbc.getTotalValueOfProducts() - tax + " kr");
        thankYouScreenTax.setText(tax + " kr");
        if(homeDeliveryRadioButton.isSelected()){
            transportFee = 49;
        }
        else{
            transportFee = 0;
        }
        thankYouScreenTransportFee.setText(transportFee + " kr");
        thankYouScreenTotalPayed.setText(transportFee + imatbc.getTotalValueOfProducts() + 15 + " kr");



    }
    public void nextStepPayment(){
        if(homeDeliveryRadioButton.isSelected()){
            homeDeliverySecondStepPane.toFront();
        }
        else if(pickupAtStoreRadioButton.isSelected()){
            pickupAtStoreSecondStepPane.toFront();
            comboBoxStores.getItems().addAll(
                    "Johanneberg",
                    "Nolvik",
                    "Olofstorp",
                    "Torslanda"
            );
        }
        System.out.println(imatbc.getShoppingCart().getItems().size());
    }
    public void openDatePicker(){
        datePicker = new DatePicker();
        datePicker.setStyle("-fx-font-size:35px");
        datePickerPane.getChildren().clear();
        datePickerPane.getChildren().add(datePicker);

        datePicker1 = new DatePicker();
        datePicker1.setStyle("-fx-font-size:35px");
        datePickerPanePickUpAtStore.getChildren().clear();
        datePickerPanePickUpAtStore.getChildren().add(datePicker1);
    }
    private void setStartingProducts(){
        homePageListItemsFlowPane.getChildren().clear();
        homePageListItemsFlowPane.getChildren().add(productListItemMap.get("Mjölk"));
        homePageListItemsFlowPane.getChildren().add(productListItemMap.get("Mango"));
        homePageListItemsFlowPane.getChildren().add(productListItemMap.get("Cola flaska"));
        homePageListItemsFlowPane.getChildren().add(productListItemMap.get("Ananas"));
    }
    private void setStartingPrices(){
        imatbc.getSingleProduct("Mjölk").setPrice(7.0);
        imatbc.getSingleProduct("Ananas").setPrice(15.0);
        imatbc.getSingleProduct("Cola flaska").setPrice(13.0);
        imatbc.getSingleProduct("Mango").setPrice(10.0);
        imatbc.getSingleProduct("Earl grey").setPrice(10.0);
    }
    public void openEarlGrey(){

        detailedViewPaneToFront(productListItemMap.get("Earl grey").getProduct());
    }
    public void goToCheckOut(){
        checkOutProductListPane.getChildren().clear();
        checkoutPane.toFront();
        lonelyPane.toBack();
        closeShoppingBag();
        checkoutTotalPrice.setText(imatbc.getShoppingCart().getTotal() + " kr");
        if(imatbc.getShoppingList().isEmpty()){
            lonelyPane.toFront();
        }
        if(imatbc.getShoppingList() != null) {
            for (ShoppingItem item : imatbc.getShoppingList()) {
                checkOutProductListPane.getChildren().add(checkoutProductListItemMap.get(item.getProduct().getName()));
                checkoutProductListItemMap.get(item.getProduct().getName()).setLabels();
            }
        }
        int tax = (int) (imatbc.getShoppingCart().getTotal() * 0.12);
        checkoutTotalPriceNoTax.setText(imatbc.getShoppingCart().getTotal() - tax + " kr");
        checkoutTax.setText(tax + " kr");
        if(homeDeliveryRadioButton.isSelected()){
            transportFee = 49;
        }
        else{
            transportFee = 0;
        }
        checkoutTransportFees.setText(transportFee + " kr");
        checkoutTotalPriceWithFees.setText(transportFee + imatbc.getTotalValueOfProducts() + 15 + " kr");


    }
    public void navigationPaneToFront(){
        onHomePage = false;
        ProductsAnchorPane.toFront();
        //HomepageBigAnchorPane.toBack();
        detailedViewPane.toBack();
        checkoutPane.toBack();
    }
    public void homePagePaneToFront(){

        onHomePage = true;
        checkColor();
        setStartingProducts();
        NavigationAnchorPane.toFront();
        HomepageBigAnchorPane.toFront();;
    }
    @FXML private void openMyPages(){
        personalInformationSavedLabel.setVisible(false);
        onHomePage = false;
        myPagesTextField.updateCustomerInfo();
        MyPagesAnchorPane.toFront();
    }
    @FXML private void goToPayStepThree(){
        if(checkinfo()) {
            cvcTextField.clear();
            PayStepThreeAnchorPane.toFront();
        }
    }
    private boolean checkinfo(){
        int x=0;
        if(homeDeliveryRadioButton.isSelected()){
            if(datePicker.getEditor().getText().isEmpty()){
                  datePicker.getEditor().setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                  x++;
             }
            else {
               datePicker.getEditor().setStyle("-fx-border-width: 0px ;");
            }
            if(timeTextField.getText().isEmpty()){
                timeTextField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                x++;
              }
             else {
               timeTextField.setStyle("-fx-border-width: 0px ;");
             }
        }
        if(pickupAtStoreRadioButton.isSelected()){
            if(datePicker1.getEditor().getText().isEmpty()){
                datePicker1.getEditor().setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                x++;
            }
            else {
                datePicker1.getEditor().setStyle("-fx-border-width: 0px ;");
            }
            if(comboBoxStores.getValue()==null){
                comboBoxStores.setStyle("-fx-border-color: red ;");
                x++;
            }
            else{
                comboBoxStores.setStyle("-fx-border-width: 0px ;");
            }
        }
    return (x==0);}

    public void detailedViewAmountSelectorPaneToFront(){
        detailedViewAmountSelectorPane.toFront();
    }
    public void detailedViewPaneToFront(Product product){
        currentProduct = product;
        if(imatbc.getShoppingItemIndex(product) >= 0){
            detailedViewAmountOfProductsLabel.setText(imatbc.getAmount(product) + "");
            detailedViewAmountSelectorPaneToFront();
        }
        else{
            detailedViewAddtoCartButton.toFront();
        }
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
        amountLabel.setText(String.valueOf(getAmount(order)));
        pricesLabel.setText(String.valueOf(getProductPrices(order)));
        totalPriceLabel.setText(String.valueOf(getOrderPrice(order)));
        this.order=order;
    }

    public void homeDeliveryButton(){
        homeDeliveryRadioButton.getStyleClass().clear();
        homeDeliveryRadioButton.getStyleClass().add("RadioButtonSelected");
        pickupAtStoreRadioButton.getStyleClass().add("RadioButtonNotSelected");
        goToCheckOut();
    }
    public void pickupAtStoreButton(){
        pickupAtStoreRadioButton.getStyleClass().clear();
        pickupAtStoreRadioButton.getStyleClass().add("RadioButtonSelected");
        homeDeliveryRadioButton.getStyleClass().add("RadioButtonNotSelected");
        goToCheckOut();
    }

    public void search(){
        checkColor();
        CategoryLabel.setText("Sökresultat för: " +"'"+ searchTextField.getText()+"'");
        if(!searchTextField.getText().isEmpty()){
            navigationPaneToFront();
            listItemsFlowPane.getChildren().clear();
            for(Product product : imatbc.searchProducts(searchTextField.getText())){
                listItemsFlowPane.getChildren().add(productListItemMap.get(product.getName()));
            }
        }


    }
    @FXML public void keyPressed(KeyEvent e){
       if ( e.getCode().equals(KeyCode.ENTER)) {
                    search();
                }
            }
    public void addToCartFirstTime(){
        detailedViewAmountSelectorPaneToFront();
        addToCart();
    }
    public void addToCart(){
        imatbc.addToCart(currentProduct);
        updateLabel();
        imatbc.printShoppingList();
    }
    public void removeFromCart(){
        if(imatbc.removeFromCart(currentProduct)){
            detailedViewAddtoCartButton.toFront();
        }
        updateLabel();
    }
    public void updateLabel(){
        detailedViewAmountOfProductsLabel.setText(imatbc.getAmount(currentProduct) + "");
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
    private StringBuilder getAmount(Order order){
        for(ShoppingItem shoppingItem: order.getItems()){
            amounts.append(shoppingItem.getAmount());
            amounts.append(System.getProperty("line.separator"));
        }
        return amounts;
    }

    private void updateOrderList()  {
        orders.clear();
        for(Order order: imatbc.getOrders()){
            PreviousBuyItem previousBuyItem = new PreviousBuyItem(order,this);
            orders.add(previousBuyItem);
        }
        Collections.reverse(orders);

        previousBuyFlowPane.getChildren().clear();
        for(PreviousBuyItem previousBuyItem : orders){
            previousBuyFlowPane.getChildren().add(previousBuyItem);
        }

    }
    @FXML private void saveCustomerInfoFromMyPages(){
        if(myPagesTextField != null){
            myPagesTextField.saveCustomerInfo();
            personalInformationSavedLabel.setVisible(true);
        }
        if(editCreditsTextField != null){
            editCreditsTextField.saveCreditsInfo();
            personalInformationSavedLabel.setVisible(true);
        }

    }
    @FXML private void goBack() {
        if (homeDeliveryRadioButton.isSelected()){
            homeDeliverySecondStepPane.toFront();
        }
        else{
            pickupAtStoreSecondStepPane.toFront();
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

    @FXML private void closeOrderItem(){

        PreviousOrderAnchorPane.toBack();
        prices.setLength(0);
        products.setLength(0);
        price=0;
    }
    @FXML private void buyOrderAgain(){
        for(ShoppingItem shoppingItem: order.getItems())
            imatbc.getShoppingCart().addItem(shoppingItem);
        buyAgainButton.setText("Tillagd");
    }
    @FXML private void completeBuy(){
        System.out.println(imatbc.getShoppingCart().getItems().size());
        PayTextField.saveCustomerInfo();
        PayCreditsTextField.saveCreditsInfo();
        PayCreditsTextField.check();PayTextField.check();checkCVC();
        if((PayCreditsTextField.check()) && (PayTextField.check()) && (checkCVC())){
            PayStepThreeAnchorPane.toBack();
            for(ShoppingItem shoppingItem: imatbc.getShoppingCart().getItems()){
                productListItemMap.get(shoppingItem.getProduct().getName()).listItemAddToCartButtonToFront();
            }
            thankYouForYourPurchase();
            imatbc.placeOrder(imatbc.getShoppingCart());
            updateOrderList();



        }

    }
    @FXML private void openShoppingBag(){
        updateShoppingBag();
        shadowPane1.toFront();
        ShoppingBagAnchorPane.toFront();
    }
    @FXML private void closeShoppingBag(){
        shadowPane1.toBack();
        ShoppingBagAnchorPane.toBack();
    }
    private void updatePayStepThree()  {
        PayTextFieldFlowPane.getChildren().clear();
        PayTextField= new MyPagesTextField(this);
        PayTextFieldFlowPane.getChildren().add(PayTextField);
        PayCreditsFlowPane.getChildren().clear();
        PayCreditsTextField = new EditCreditsTextField(this);
        PayCreditsFlowPane.getChildren().add(PayCreditsTextField);
    }
    public boolean checkCVC(){
        if(cvcTextField.getText().isEmpty()){
            cvcTextField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            return false; }
        else{
            cvcTextField.setStyle("-fx-border-color: black ; ");
        }
        return true;}

    private void updateShoppingBag()  {
        shoppingBagItems.clear();
        for(ShoppingItem shoppingItem: imatbc.getShoppingCart().getItems()){
            ShoppingBagItem shoppingBagItem = new ShoppingBagItem(shoppingItem,this);
            shoppingBagItems.add(shoppingBagItem);
        }
        Collections.reverse(shoppingBagItems);

        shoppingBagFlowPane.getChildren().clear();
        for(ShoppingBagItem shoppingBagItem : shoppingBagItems){
            shoppingBagFlowPane.getChildren().add(shoppingBagItem);
        }

            totalPriceShoppingLabel.setText(String.valueOf(imatbc.getShoppingCart().getTotal()+" kr"));

    }
    protected void deleteShoppingItem(ShoppingBagItem shoppingBagItemRemove,ShoppingItem shoppingItem){
        shoppingBagItems.remove(shoppingBagItemRemove);
        imatbc.getShoppingCart().removeItem(shoppingItem);
        totalPriceShoppingLabel.setText(String.valueOf(imatbc.getShoppingCart().getTotal() + " kr"));
        shoppingBagFlowPane.getChildren().clear();
        for(ShoppingBagItem shoppingBagItem : shoppingBagItems){
            shoppingBagFlowPane.getChildren().add(shoppingBagItem);
        }
        productListItemMap.get(shoppingItem.getProduct().getName()).listItemAddToCartButtonToFront();
    }
    protected void changeProductListAmount(ShoppingItem shoppingItem, Boolean b){
        if (b) {
            productListItemMap.get(shoppingItem.getProduct().getName()).addToCart();
            totalPriceShoppingLabel.setText(String.valueOf(imatbc.getShoppingCart().getTotal() + " kr"));
        }
        else{
            productListItemMap.get(shoppingItem.getProduct().getName()).removeFromCart();
            totalPriceShoppingLabel.setText(String.valueOf(imatbc.getShoppingCart().getTotal()+" kr"));

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
        if(onHomePage){
            detailedViewPane.toBack();
        }
        else{
            navigationPaneToFront();
        }

        for(Product product : imatbc.getProducts()){
            productListItemMap.get(product.getName()).updateLabel();
        }


    }


    @FXML
    public void closeButtonMouseExited(){
        exitViewPaneImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "img/icon_close.png")));
    }


}
