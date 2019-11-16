/*
 * Main Class to initialize Calc scene
 * @author ivpo3178
 */

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class MainCalc extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

           Parent fxmlLayout = FXMLLoader.load(getClass().getResource("layout.fxml"));
           Scene scene = new Scene(fxmlLayout, 287,419);
           scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
           stage.setResizable(false);
           stage.setScene(scene);
           stage.show();
    }
}
