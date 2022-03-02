package practicumopdracht.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import practicumopdracht.MainApplication;
import practicumopdracht.views.BoekView;
import practicumopdracht.views.View;

public class BoekController extends Controller {

    private BoekView view;

    public BoekController() {
        view = new BoekView();

        view.getBewerken().setOnAction(actionEvent -> bewerken());
        view.getNieuw().setOnAction(actionEvent -> nieuw());
        view.getSchakelen().setOnAction(actionEvent -> schakelen());
        view.getOpslaan().setOnAction(actionEvent -> opslaan());
    }

    private void nieuw() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nieuw");
        alert.setHeaderText(null);
        alert.setContentText("De nieuw knop is ingedrukt!");
        alert.show();
    }

    private void bewerken() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bewerken");
        alert.setHeaderText(null);
        alert.setContentText("De bewerken knop is ingedrukt!");
        alert.show();
    }

    private void opslaan() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.show();
    }
    private void schakelen() {
        Controller controller = new SchrijverController();
        MainApplication.switchController(controller);
    }

    @Override
    public View getView(){
        return view;
    }
}
