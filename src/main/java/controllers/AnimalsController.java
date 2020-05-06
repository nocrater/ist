package controllers;

import enums.Species;
import enums.State;
import enums.WhereNow;
import gateways.Gateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Animal;
import registries.GWRegistry;

public class AnimalsController extends Controller {

    @FXML
    private TableView<Animal> tableView;

    @FXML
    private TableColumn<Animal, String> nameTableColumn;

    @FXML
    private TableColumn<Animal, String> descriptionTableColumn;

    @FXML
    private TableColumn<Animal, Species> speciesTableColumn;

    @FXML
    private TableColumn<Animal, State> stateTableColumn;

    @FXML
    private TableColumn<Animal, WhereNow> whereNowTableColumn;

    private Gateway<Animal> animalGateway = GWRegistry.getInstance().getAnimalGateway();

    ObservableList<Animal> animals = FXCollections.observableArrayList(animalGateway.all());

    @FXML
    public void initialize(){
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descriptionTableColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        speciesTableColumn.setCellValueFactory(cellData -> cellData.getValue().speciesProperty());
        stateTableColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        whereNowTableColumn.setCellValueFactory(cellData -> cellData.getValue().whereNowProperty());
        tableView.setItems(animals);
    }

    public void add(){
        Animal animal = new Animal();
        boolean is_cancelled = app.editWindow("animal", (loader) -> {
            AnimalController controller = loader.getController();
            controller.setAnimal(animal);
        });

        if (is_cancelled)
            return;

        animals.add(animal);
    }

    public void delete(){
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index != -1){
            animalGateway.delete(animals.get(index));
            animals.remove(index);
        }
    }

    public void edit(){
        Animal animal = tableView.getSelectionModel().getSelectedItem();
        app.editWindow("animal", (loader) -> {
            AnimalController controller = loader.getController();
            controller.setAnimal(animal);
        });
    }
}
