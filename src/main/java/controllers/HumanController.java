package controllers;

import gateways.Gateway;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Human;
import registries.GWRegistry;


public class HumanController extends Controller {

    private Human human;

    private Gateway<Human> humanGateway = GWRegistry.getInstance().getHumanGateway();

    @FXML
    TextField nameEdit;

    public void setHuman(Human human) {
        this.human = human;
        nameEdit.setText(human.getName());
    }

    public void clickOk(){
        String name = nameEdit.getText().trim();

        if (name.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name should not be empty or contains only spaces");
            alert.showAndWait();
            return;
        }

        human.setName(name);

        try {
            if (human.getId() != 0)
                humanGateway.update(human);
            else
                humanGateway.insert(human);

            Stage stage = (Stage) nameEdit.getScene().getWindow();
            stage.close();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }
}
