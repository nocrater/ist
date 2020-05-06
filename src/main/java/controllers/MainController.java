package controllers;

import main.Main;

public class MainController extends Controller {

    public void onHumansClick() {
        app.viewTable("humans");
    }

    public void onAnimalsClick() {
        app.viewTable("animals");
    }
}
