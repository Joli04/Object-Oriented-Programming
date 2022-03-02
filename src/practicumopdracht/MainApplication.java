package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.BoekController;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.SchrijverController;
import practicumopdracht.views.BoekView;


public class MainApplication extends Application {
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private final int WIDTH = 750;
    private final int HEIGHT = 480;
    private static Stage stage;


    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);
            return;
        }

        Controller controller = new SchrijverController();
        switchController(controller);

    }

    public static void switchController(Controller controller){
        stage = new Stage();
        stage.setTitle("Practicumopdracht");
        Scene scene = new Scene(controller.getView().getRoot());
        stage.setScene(scene);
        stage.show();
    }
}
