package controllers;

import gateways.Gateway;
import gateways.HumanGateway;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Human;
import registries.GWRegistry;


public class HumanController {

    private Human human;

    private Gateway<Human> humanGateway = GWRegistry.getInstance().getHumanGateway();

    @FXML
    TextField nameEdit;

    @FXML
    public void initialize(){
    }

    public void setHuman(Human human) {
        this.human = human;
        nameEdit.setText(human.getName());
    }

    public void clickOk(){
        String name = nameEdit.getText();
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
            alert.setTitle("Ошибка");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void clickCancel(){
        Stage stage = (Stage) nameEdit.getScene().getWindow();
        stage.close();
    }
}
