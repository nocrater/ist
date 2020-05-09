package controllers;

import gateways.Gateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Animal;
import models.Client;
import registries.GWRegistry;

import java.util.List;


public class ClientController extends Controller {

    private Client client;

    private Gateway<Client> clientGateway = GWRegistry.getInstance().getClientGateway();

    @FXML
    TextField nameEdit;

    @FXML
    TableView<Animal> givingTableView;

    @FXML
    private TableColumn<Animal, String> givingNameTableColumn;

    ObservableList<Animal> givingAnimals;

    @FXML
    TableView<Animal> takingTableView;

    @FXML
    private TableColumn<Animal, String> takingNameTableColumn;

    ObservableList<Animal> takingAnimals;

    boolean alreadyAdded;

    @FXML
    public void initialize(){
        givingNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        takingNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    public void setClient(Client client) {
        this.client = client;

        givingAnimals = FXCollections.observableList(this.client.getGivingAnimals());
        givingTableView.setItems(givingAnimals);

        takingAnimals = FXCollections.observableList(this.client.getTakingAnimals());
        takingTableView.setItems(takingAnimals);

        nameEdit.setText(client.getName());
    }

    public void addGivingAnimal() {
        Animal animal = new Animal();

        app.editWindow("animals", "Selecting giving animal", (loader) -> {
            AnimalsController controller = loader.getController();
            controller.setApplication(app);
            controller.selecting(animal);
        });

        givingTableView.getItems().forEach((item) -> {
            if (animal.getId() == item.getId())
                alreadyAdded = true;
        });

        takingTableView.getItems().forEach((item) -> {
            if (animal.getId() == item.getId())
                alreadyAdded = true;
        });

        if (alreadyAdded) {
            alreadyAdded = false;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Client already has this animal in giving or taking group");
            alert.showAndWait();
            return;
        }

        givingTableView.getItems().add(animal);
    }

    public void deleteGivingAnimal() {
        int index = givingTableView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            givingTableView.getItems().remove(index);
        }
    }

    public void addTakingAnimal() {
        Animal animal = new Animal();

        app.editWindow("animals", "Selecting taking animal", (loader) -> {
            AnimalsController controller = loader.getController();
            controller.setApplication(app);
            controller.selecting(animal);
        });

        givingTableView.getItems().forEach((item) -> {
            if (animal.getId() == item.getId())
                alreadyAdded = true;
        });

        takingTableView.getItems().forEach((item) -> {
            if (animal.getId() == item.getId())
                alreadyAdded = true;
        });

        if (alreadyAdded) {
            alreadyAdded = false;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Client already has this animal in giving or taking group");
            alert.showAndWait();
            return;
        }

        takingTableView.getItems().add(animal);
    }

    public void deleteTakingAnimal() {
        int index = takingTableView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            takingTableView.getItems().remove(index);
        }
    }

    public void clickOk() {
        String name = nameEdit.getText().trim();

        if (name.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name should not be empty or contains only spaces");
            alert.showAndWait();
            return;
        }

        client.setName(name);

        client.setGivingAnimals(givingTableView.getItems());
        client.setTakingAnimals(takingTableView.getItems());

        try {
            if (client.getId() != 0)
                clientGateway.update(client);
            else
                clientGateway.insert(client);

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
