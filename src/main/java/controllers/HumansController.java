package controllers;

import gateways.Gateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Human;
import registries.GWRegistry;

public class HumansController extends Controller {

    @FXML
    private TableView<Human> tableView;

    @FXML
    private TableColumn<Human, String> nameTableColumn;

    private Gateway<Human> humanGateway = GWRegistry.getInstance().getHumanGateway();

    ObservableList<Human> humans = FXCollections.observableArrayList(humanGateway.all());

    @FXML
    public void initialize(){
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableView.setItems(humans);
    }

    public void add(){
        Human human = new Human();
        app.editWindow("human", (loader) -> {
            HumanController controller = loader.getController();
            controller.setHuman(human);
        });
        humans.add(human);
    }

    public void delete(){
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index != -1){
            humanGateway.delete(humans.get(index));
            humans.remove(index);
        }
    }

    public void edit(){
        Human human = tableView.getSelectionModel().getSelectedItem();
        app.editWindow("human", (loader) -> {
            HumanController controller = loader.getController();
            controller.setHuman(human);
        });
    }
}
