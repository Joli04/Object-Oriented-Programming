package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class SchrijverView extends View {
    @Override
    protected Parent initializeView(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);

        HBox hboxVoornaam = new HBox();
        hboxVoornaam.setSpacing(23);

        Label voornaam = new Label("Voornaam:");
        TextArea textAreaVoornaam = new TextArea();
        textAreaVoornaam.setMinWidth(500);
        textAreaVoornaam.setMaxHeight(7);
        hboxVoornaam.getChildren().addAll(voornaam,textAreaVoornaam);

        HBox hboxAchternaam = new HBox();
        hboxAchternaam.setSpacing(14);

        Label achternaam = new Label("Achternaam:");
        TextArea textAreaAchternaam = new TextArea();
        textAreaAchternaam.setMinWidth(500);
        textAreaAchternaam.setMaxHeight(7);

        hboxAchternaam.getChildren().addAll(achternaam,textAreaAchternaam);

        HBox hboxLeeftijd = new HBox();
        hboxLeeftijd.setSpacing(40);

        Label leeftijd = new Label("Leeftijd:");
        TextField textFieldLeeftijd = new TextField();

        hboxLeeftijd.getChildren().addAll(leeftijd,textFieldLeeftijd);


        HBox hboxNogActief = new HBox();
        hboxNogActief.setSpacing(22);
        Label nogActief = new Label("Nog Actief:");
        CheckBox checkBoxNogActief = new CheckBox();
        hboxNogActief.getChildren().addAll(nogActief,checkBoxNogActief);

        Label geschrevenBoeken = new Label("Geschreven boeken:");
        ListView geschrevenBoekenListView = new ListView();

        GridPane knoppen = new GridPane();
        Button opslaan = new Button("Opslaan");
        Button nieuw = new Button("Nieuw");
        Button bewerken = new Button("Bewerken");
        Button schakelen = new Button("Schakelen");
        knoppen.setHgap(95);
        knoppen.setAlignment(Pos.CENTER);
        knoppen.addRow(1,opslaan,nieuw,bewerken,schakelen);


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.addColumn(1,hboxVoornaam,hboxAchternaam,hboxLeeftijd,hboxNogActief,geschrevenBoeken,geschrevenBoekenListView,knoppen);
        grid.setVgap(10);


        vbox.getChildren().addAll(grid);
        return vbox;
    }
}
