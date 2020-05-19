


import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class IMat extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("IMat");

        Parent root = FXMLLoader.load(getClass().getResource("IMat.fxml"), bundle);


        Scene scene = new Scene(root, 1980, 1080);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setOnCloseRequest((WindowEvent event1) -> {
            IMatDataHandler.getInstance().shutDown();
        });

        stage.show();

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
