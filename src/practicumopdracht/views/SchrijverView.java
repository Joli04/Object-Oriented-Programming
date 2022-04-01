package practicumopdracht.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Schrijver;


public class SchrijverView extends View {
    private TextArea textAreaVoornaam;
    private TextArea textAreaAchternaam;
    private TextField textFieldLeeftijd;
    private CheckBox checkBoxNogActief;
    private ListView<Schrijver> alleSchrijversListView;
    private MenuItem laden;
    private MenuItem save;
    private MenuItem sluiten;


    @Override
    protected Parent initializeView(){
        VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(20);
        BorderPane borderPane = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Bestand");
        laden = new MenuItem("Laden");
        save = new MenuItem("Opslaan");
        sluiten = new MenuItem("Afsluiten");
        menu.getItems().addAll(laden,save,sluiten);
        menuBar.getMenus().add(menu);

        borderPane.setTop(menuBar);

        HBox hboxVoornaam = new HBox();
            hboxVoornaam.setSpacing(23);

            Label voornaam = new Label("Voornaam:");
                textAreaVoornaam = new TextArea();
                textAreaVoornaam.setMinWidth(500);
                textAreaVoornaam.setMaxHeight(7);

        hboxVoornaam.getChildren().addAll(voornaam,textAreaVoornaam);

        HBox hboxAchternaam = new HBox();
            hboxAchternaam.setSpacing(14);

            Label achternaam = new Label("Achternaam:");
                textAreaAchternaam = new TextArea();
                textAreaAchternaam.setMinWidth(500);
                textAreaAchternaam.setMaxHeight(7);

        hboxAchternaam.getChildren().addAll(achternaam,textAreaAchternaam);

        HBox hboxLeeftijd = new HBox();
            hboxLeeftijd.setSpacing(40);

            Label leeftijd = new Label("Leeftijd:");
            textFieldLeeftijd = new TextField();

        hboxLeeftijd.getChildren().addAll(leeftijd,textFieldLeeftijd);


        HBox hboxNogActief = new HBox();
            hboxNogActief.setSpacing(22);
            Label nogActief = new Label("Nog Actief:");
            checkBoxNogActief = new CheckBox();
        hboxNogActief.getChildren().addAll(nogActief,checkBoxNogActief);

        Label alleSchrijvers = new Label("Lijst met alle schrijvers:");

        ObservableList<Schrijver> SchrijverObservableList = FXCollections.emptyObservableList();
        alleSchrijversListView = new ListView<>();
        alleSchrijversListView.setItems(SchrijverObservableList);

        GridPane knoppen = new GridPane();
            knoppen.setHgap(95);
            knoppen.setAlignment(Pos.CENTER);
            opslaan = new Button("Opslaan");
            nieuw = new Button("Nieuw");
            verwijderen = new Button("Verwijderen");
            schakelen = new Button("Schakelen");

        knoppen.addRow(1,opslaan,nieuw,verwijderen,schakelen);

        GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(10);

        grid.addColumn(1,menuBar,hboxVoornaam,hboxAchternaam,hboxLeeftijd,hboxNogActief,alleSchrijvers,alleSchrijversListView,knoppen);


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

    public TextArea getTextAreaVoornaam() {
        return textAreaVoornaam;
    }

    public TextArea getTextAreaAchternaam() {
        return textAreaAchternaam;
    }

    public TextField getTextFieldLeeftijd() {
        return textFieldLeeftijd;
    }

    public CheckBox getCheckBoxNogActief() {
        return checkBoxNogActief;
    }

    public ListView<Schrijver> getAlleSchrijversListView() {
        return alleSchrijversListView;
    }

    public MenuItem getLaden(){
        return laden;
    }

    public MenuItem getSave() {
        return save;
    }

    public MenuItem getSluiten() {
        return sluiten;
    }
}
