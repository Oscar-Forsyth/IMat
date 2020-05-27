import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class MyPagesTextField extends AnchorPane {
    private IMatController parentController;
    @FXML private TextField prename;
    @FXML private TextField surname;
    @FXML private TextField adress;
    @FXML private TextField email;
    @FXML private TextField phonenumber;
    @FXML private TextField postalCode;
    @FXML private TextField coveredCredit;

    public MyPagesTextField(IMatController iMatController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyPagesTextField.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = iMatController;

        updateCustomerInfo();


    }

     public void saveCustomerInfo(){

           parentController.setFirstName(prename.getText());
           parentController.setLastName(surname.getText());
            parentController.setAddress(adress.getText());
            parentController.setEmail(email.getText());
            parentController.setPhoneNumber(phonenumber.getText());
            parentController.setPostCode(postalCode.getText());
         System.out.println(parentController.getFirstname());

    }
    public void updateCustomerInfo(){
        if(parentController.getFirstname()!=null){
            prename.setText(parentController.getFirstname());
        }
        if(parentController.getLastname()!=null){
            surname.setText(parentController.getLastname());
        }
        if(parentController.getAddress()!=null){
            adress.setText(parentController.getAddress());
        }
        if(parentController.getEmail()!=null){
            email.setText(parentController.getEmail());
        }
        if(parentController.getPhoneNumber()!=null){
            phonenumber.setText(parentController.getPhoneNumber());
        }
        if(parentController.getPostCode()!=null){
            postalCode.setText(parentController.getPostCode());
        }
    }
    public boolean check() {
        int x=0;
        if (parentController.getFirstname().isEmpty()) {
            prename.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  prename.setStyle("-fx-border-color: black ; ");}
        if (parentController.getLastname().isEmpty()) {
            surname.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  surname.setStyle("-fx-border-color: black ; ");}
        if (parentController.getAddress().isEmpty()) {
            adress.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  adress.setStyle("-fx-border-color: black ; ");}
        if (parentController.getEmail().isEmpty()) {
            email.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  email.setStyle("-fx-border-color: black ; ");}
        if (parentController.getPhoneNumber().isEmpty()) {
            phonenumber.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  phonenumber.setStyle("-fx-border-color: black ; ");}
        if (parentController.getPostCode().isEmpty()) {
            postalCode.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  postalCode.setStyle("-fx-border-color: black ; ");}
        return (x==0);}
}
