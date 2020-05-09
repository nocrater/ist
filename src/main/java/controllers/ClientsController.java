package controllers;

import gateways.Gateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Client;
import registries.GWRegistry;

public class ClientsController extends Controller {

    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, String> nameTableColumn;

    private Gateway<Client> clientGateway = GWRegistry.getInstance().getClientGateway();

    ObservableList<Client> clients = FXCollections.observableArrayList(clientGateway.all());

    @FXML
    public void initialize(){
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableView.setItems(clients);
    }

    public void add(){
        Client client = new Client();
        boolean is_cancelled = app.editWindow("client", "Adding client", (loader) -> {
            ClientController controller = loader.getController();
            controller.setApplication(app);
            controller.setClient(client);
        });

        if (is_cancelled)
            return;

        clients.add(client);
    }

    public void delete(){
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index != -1){
            clientGateway.delete(clients.get(index));
            clients.remove(index);
        }
    }

    public void edit(){
        Client client = tableView.getSelectionModel().getSelectedItem();

        if (client == null)
            return;

        app.editWindow("client", "Editing client", (loader) -> {
            ClientController controller = loader.getController();
            controller.setApplication(app);
            controller.setClient(client);
        });
    }
}
