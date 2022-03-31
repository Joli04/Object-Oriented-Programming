package practicumopdracht.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Boek;
import practicumopdracht.models.Schrijver;

public class BoekView extends View{

    private DatePicker lancering;
    private TextField gemiddeldeCijferTextField;
    private ComboBox<String> comboBox;
    private TextArea titelTextArea;
    private ListView<Boek> alleBoekenListView;
    @Override
    protected Parent initializeView() {
        VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(20);

        GridPane grid = new GridPane();
            grid.setVgap(10);
            grid.setAlignment(Pos.CENTER);

        /**
         * Cijfer Combobox
         */
        HBox hbox = new HBox();
            hbox.setSpacing(92);

            comboBox = new ComboBox<>();
            comboBox.setMinWidth(150);


            Label naamSchrijver = new Label("Schrijver: ");

        hbox.getChildren().addAll(naamSchrijver,comboBox);
        /**
         * Datepicker
         */
        HBox lanceringHbox = new HBox();
            lanceringHbox.setSpacing(40);
            Label lanceringLabel = new Label("Gelanceerd op: ");
            lancering = new DatePicker();

        lanceringHbox.getChildren().addAll(lanceringLabel, lancering);

        HBox grotereHbox = new HBox();
            grotereHbox.setSpacing(170);

        grotereHbox.getChildren().addAll(hbox, lanceringHbox);
        /**
         * Titel
         */
        HBox hzhbox = new HBox();
            hzhbox.setSpacing(165);

            HBox titelHbox = new HBox();
                titelHbox.setSpacing(115);
                Label titelLabel = new Label("Titel: ");
                titelTextArea = new TextArea();
                titelTextArea.setPrefWidth(150);
            titelHbox.getChildren().addAll(titelLabel,titelTextArea);

        /**
         * Gemiddelde cijfer
         */
        HBox gemiddeldeCijferHbox = new HBox();
            gemiddeldeCijferHbox.setSpacing(45);

        Label gemiddeldeCijferLabel = new Label("Gemiddelde cijfer: ");
            gemiddeldeCijferTextField = new TextField();

        gemiddeldeCijferHbox.getChildren().addAll(gemiddeldeCijferLabel,gemiddeldeCijferTextField);

        /**
         * Alle boeken
         */

        Label boeken = new Label("Boeken: ");

        ObservableList<Boek> boekObservableList = FXCollections.observableArrayList();
        alleBoekenListView = new ListView<>();
        alleBoekenListView.setItems(boekObservableList);


        GridPane knoppen = new GridPane();
            knoppen.setHgap(95);
            knoppen.setAlignment(Pos.CENTER);
            opslaan = new Button("Opslaan");
            nieuw = new Button("Nieuw");
            verwijderen = new Button("Verwijderen");
            schakelen = new Button("Schakelen");

        knoppen.addRow(1,opslaan,nieuw,verwijderen,schakelen);

        grid.addColumn(1,grotereHbox,titelHbox,gemiddeldeCijferHbox,boeken,alleBoekenListView,knoppen);

        vbox.getChildren().addAll(grid);


        return vbox;
    }

    public Button getOpslaan() {
        return opslaan;
    }

    public Button getNieuw() {
        return nieuw;
    }

    public Button getVerwijderen() {
        return verwijderen;
    }

    public Button getSchakelen() {
        return schakelen;
    }
    public DatePicker getLancering() {
        return lancering;
    }

    public TextField getGemiddeldeCijferTextField() {
        return gemiddeldeCijferTextField;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public TextArea getTitelTextArea() {
        return titelTextArea;
    }

    public ListView<Boek> getAlleBoekenListView() {
        return alleBoekenListView;
    }

}
