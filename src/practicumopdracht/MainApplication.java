package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import practicumopdracht.views.BoekView;
import practicumopdracht.views.SchrijverView;
import practicumopdracht.views.View;


public class MainApplication extends Application {
    private final String TITLE = "Schrijfersnominatie";
    private final int WIDTH = 750;
    private final int HEIGHT = 480;

    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setWidth(640);
        stage.setHeight(480);

        Circle circle = new Circle();
        circle.setRadius(15);
        circle.setFill(Color.BLUE);

        View view = new SchrijverView();
        Scene scene = new Scene(view.getRoot());
        stage.setScene(scene);
        stage.show();
    }
}
