package controllers;

public class MainController extends Controller {

    public void onHumansClick() {
        app.viewTable("humans");
    }

    public void onAnimalsClick() {
        app.viewTable("animals");
    }

    public void onClientsClick() {
        app.viewTable("clients");
    }
}
