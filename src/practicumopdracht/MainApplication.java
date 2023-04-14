package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.comparators.VoornaamComparator;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.SchrijverController;
import practicumopdracht.data.*;

import java.util.Comparator;


public class MainApplication extends Application {

    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private final int WIDTH = 950;
    private final int HEIGHT = 480;
    private static Stage stage;
    private static SchrijverDAO schrijverDAO;
    private static BoekDAO boekDAO;


    /**
     * @param stage is de stage waar de scene's op worden gezet.
     */



    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);
            return;


        }
        this.stage = new Stage();

        //schrijverDAO = new DummySchrijverDAO();
        //schrijverDAO = new TextSchrijverDAO();
        schrijverDAO = new BinarySchrijverDAO();

        schrijverDAO.load();

        //boekDAO = new DummyBoekDAO();
        boekDAO = new TextBoekDAO();
        //boekDAO = new ObjectBoekDAO();

        stage.setMinWidth(new MainApplication().WIDTH);

        Controller controller = new SchrijverController();
        switchController(controller);

    }

    /**
     *
     * @param controller is de controller die ervoor zorgt dat de views kunnen schakelen.
     */

    public static void switchController(Controller controller){
        Scene scene = new Scene(controller.getView().getRoot());
        stage.setTitle(new MainApplication().TITLE);
        stage.setHeight(new MainApplication().HEIGHT);
        stage.setMinWidth(new MainApplication().WIDTH);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @return geeft de waarden van de variabele schrijverDAO en boekDAO
     */

    public static SchrijverDAO getSchrijverDAO() {
        return schrijverDAO;
    }

    public static BoekDAO getBoekDAO() {
        return boekDAO;
    }

}





