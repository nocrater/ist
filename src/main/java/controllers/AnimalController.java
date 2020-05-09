package controllers;

import enums.Species;
import enums.State;
import enums.WhereNow;
import gateways.Gateway;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Animal;
import registries.GWRegistry;


public class AnimalController extends Controller {

    private Animal animal;

    private Gateway<Animal> animalGateway = GWRegistry.getInstance().getAnimalGateway();

    @FXML
    TextField nameEdit;

    @FXML
    TextArea descriptionEdit;

    @FXML
    ComboBox speciesEdit;

    @FXML
    ComboBox stateEdit;

    @FXML
    ComboBox whereNowEdit;

    public void setAnimal(Animal animal) {
        this.animal = animal;
        nameEdit.setText(animal.getName());
        descriptionEdit.setText(animal.getDescription());

        switch (animal.getSpecies()) {
            case Cat:
                speciesEdit.setValue("Cat");
                break;
            case Dog:
                speciesEdit.setValue("Dog");
                break;
        }

        switch (animal.getState()) {
            case Healthy:
                stateEdit.setValue("Healthy");
                break;
            case Sick:
                stateEdit.setValue("Sick");
                break;
            case Vaccine_Need:
                stateEdit.setValue("Vaccine need");
                break;
            case Sick_And_Vaccine_Need:
                stateEdit.setValue("Sick and vaccine need");
                break;
        }

        switch (animal.getWhereNow()) {
            case Housing:
                whereNowEdit.setValue("Housing");
                break;
            case Clinic:
                whereNowEdit.setValue("Clinic");
                break;
        }
    }

    public void clickOk(){
        String name = nameEdit.getText().trim();

        if (name.equals("")) {
            System.out.println("11111");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name should not be empty or contains only spaces");
            alert.showAndWait();
            return;
        }

        animal.setName(name);

        String description = descriptionEdit.getText().trim();
        animal.setDescription(description);

        switch (speciesEdit.getValue().toString()) {
            case "Cat":
                animal.setSpecies(Species.Cat);
                break;
            case "Dog":
                animal.setSpecies(Species.Dog);
                break;
        }

        switch (stateEdit.getValue().toString()) {
            case "Healthy":
                animal.setState(State.Healthy);
                break;
            case "Sick":
                animal.setState(State.Sick);
                break;
            case "Vaccine need":
                animal.setState(State.Vaccine_Need);
                break;
            case "Sick and vaccine need":
                animal.setState(State.Sick_And_Vaccine_Need);
                break;
        }

        switch (whereNowEdit.getValue().toString()) {
            case "Housing":
                animal.setWhereNow(WhereNow.Housing);
                break;
            case "Clinic":
                animal.setWhereNow(WhereNow.Clinic);
                break;
        }

        try {
            if (animal.getId() != 0)
                animalGateway.update(animal);
            else
                animalGateway.insert(animal);

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
