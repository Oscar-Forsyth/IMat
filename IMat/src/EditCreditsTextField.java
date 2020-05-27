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

public class EditCreditsTextField extends AnchorPane {
    private IMatController parentController;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField validMonth;
    @FXML
    private TextField validYear;


    public EditCreditsTextField(IMatController iMatController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditCreditsTextField.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = iMatController;

        updateCreditsInfo();

    }
    public void saveCreditsInfo(){
        if(!cardNumber.getText().isEmpty())
            parentController.setCardNumber(cardNumber.getText());
        if(!validMonth.getText().isEmpty())
            parentController.setValidMonth(Integer.parseInt(validMonth.getText()));
        if(!validYear.getText().isEmpty())
            parentController.setValidYear(Integer.parseInt(validYear.getText()));

        if(cardNumber.getText().equals("")){
            parentController.setCardNumber(null);
        }
        if(validMonth.getText().equals("")){
            parentController.setValidMonth(0);
        }
        if(validYear.getText().equals("")){
            parentController.setValidYear(0);
        }

    }
    public void updateCreditsInfo(){
        if(parentController.getCardNumber()!=null){
            cardNumber.setText(parentController.getCardNumber());
        }
        if(parentController.getValidMonth()!=0){
            validMonth.setText(String.valueOf(parentController.getValidMonth()));
        }
        if(parentController.getValidYear()!=0){
            validYear.setText(String.valueOf(parentController.getValidYear()));
        }

    }
    public boolean check(){
        int x=0;
        if(parentController.getCardNumber().isEmpty()){
            cardNumber.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  cardNumber.setStyle("-fx-border-color: black ; ");}
        if((parentController.getValidMonth()<=0) || ( parentController.getValidMonth()>12) ){
            validMonth.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  validMonth.setStyle("-fx-border-color: black ; ");}
        if(parentController.getValidYear()<2020){
            validYear.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            x++;
        }
        else {  validYear.setStyle("-fx-border-color: black ; ");}
        return (x==0);}
}
