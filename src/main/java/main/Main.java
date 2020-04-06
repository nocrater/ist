package main;

import com.sun.xml.internal.ws.util.StringUtils;
import controllers.Controller;
import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.ControllerCallback;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/main_view.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setApplication(this);

        primaryStage.setTitle("Shelter app");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public void viewTable(String name) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/" + name + "_view.fxml"));
            VBox page = (VBox) loader.load();

            Controller controller = loader.getController();
            controller.setApplication(this);

            Stage dialogStage = new Stage();
            dialogStage.setTitle(StringUtils.capitalize(name));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
        }
        catch (IOException e)
        {}
    }

    public void editWindow(String name, ControllerCallback callback) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/" + name + "_view.fxml"));
            VBox page = (VBox) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editing");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            callback.call(loader);

            dialogStage.showAndWait();
        }
        catch (IOException e)
        {}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
