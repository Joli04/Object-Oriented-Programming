package practicumopdracht.views;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoekView extends View{

    @Override
    protected Parent initializeView() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);

        GridPane grid = new GridPane();

        ComboBox comboBox = new ComboBox();
        Label naamSchrijver = new Label("Schrijver: ");

        HBox hbox = new HBox();
        hbox.setSpacing(60);
        hbox.getChildren().addAll(naamSchrijver, comboBox);

        HBox lanceringHbox = new HBox();
        Label lanceringLabel = new Label("Gelanceerd op: ");
        DatePicker lancering = new DatePicker();
        lanceringHbox.setSpacing(10);
        lanceringHbox.getChildren().addAll(lanceringLabel, lancering);

        HBox grotereHbox = new HBox();
        grotereHbox.setSpacing(180);
        grotereHbox.getChildren().addAll(hbox, lanceringHbox);

        HBox titelHbox = new HBox();
        titelHbox.setSpacing(80);
        Label titelLabel = new Label("Titel: ");
        TextArea titelTextArea = new TextArea();
        titelTextArea.setPrefHeight(10);
        titelTextArea.setPrefWidth(150);
        titelHbox.getChildren().addAll(titelLabel,titelTextArea);

        HBox gemiddeldeCijferHbox = new HBox();
        gemiddeldeCijferHbox.setSpacing(10);
        Label gemiddeldeCijferLabel = new Label("Gemiddelde cijfer: ");
        TextField gemiddeldeCijferTextField = new TextField();
        gemiddeldeCijferHbox.getChildren().addAll(gemiddeldeCijferLabel,gemiddeldeCijferTextField);


        VBox hoofdstukkenListView  = new VBox();
        hoofdstukkenListView.setSpacing(10);

        Label hoofdstukken = new Label("Hoofdstukken: ");
        ListView listView = new ListView();
        listView.getItems().addAll("Deel 1","Deel 2","Deel 3");

        hoofdstukkenListView.getChildren().addAll(hoofdstukken,listView);

        GridPane knoppen = new GridPane();
        Button opslaan = new Button("Opslaan");
        Button nieuw = new Button("Nieuw");
        Button bewerken = new Button("Bewerken");
        Button schakelen = new Button("Schakelen");
        knoppen.setHgap(95);
        knoppen.setAlignment(Pos.CENTER);
        knoppen.addRow(1,opslaan,nieuw,bewerken,schakelen);

        grid.addColumn(1,grotereHbox,titelHbox,gemiddeldeCijferHbox,hoofdstukkenListView,knoppen);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(grid);


        return vbox;
    }
}
