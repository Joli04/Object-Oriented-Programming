package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.data.BoekDAO;
import practicumopdracht.data.SchrijverDAO;
import practicumopdracht.models.Schrijver;
import practicumopdracht.views.SchrijverView;
import practicumopdracht.views.View;
import practicumopdracht.MainApplication;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchrijverController extends Controller {
    String schrijvervoornaam;
    String schrijverachternaam;
    int leeftijd;
    boolean nogActief;
    private SchrijverView view;
    private final int MAX_CONTROLLE = 3;
    Schrijver schrijver;
    SchrijverDAO schrijverDAO = MainApplication.getSchrijverDAO();
    BoekDAO boekDAO = MainApplication.getBoekDAO();

    private void loader() {

        List<Schrijver> schrijvers = schrijverDAO.getAll();
        ObservableList<Schrijver> observableschrijvers = FXCollections.observableArrayList(schrijvers);
        view.getAlleSchrijversListView().setItems(observableschrijvers);
    }


    public SchrijverController() {
        view = new SchrijverView();
        schrijverDAO.load();
        view.getVerwijderen().setOnAction(actionEvent -> verwijderen());
        view.getNieuw().setOnAction(actionEvent -> nieuw());
        view.getSchakelen().setOnAction(actionEvent -> schakelen());
        view.getOpslaan().setOnAction(actionEvent -> opslaan());
        view.getLaden().setOnAction(actionEvent -> laden());
        view.getSave().setOnAction(actionEvent -> save());
        view.getSluiten().setOnAction(actionEvent -> sluiten());

        view.getAlleSchrijversListView().getSelectionModel().selectedItemProperty().addListener((test) -> {

            int getal = view.getAlleSchrijversListView().getSelectionModel().getSelectedIndex();

            if(schrijverDAO.getById(getal) != null) {
                view.getTextAreaVoornaam().setText(schrijverDAO.getById(getal).getVoornaam());
                view.getTextAreaAchternaam().setText(String.valueOf(schrijverDAO.getById(getal).getAchternaam()));
                view.getTextFieldLeeftijd().setText(String.valueOf(schrijverDAO.getById(getal).getLeeftijd()));
                view.getCheckBoxNogActief().setSelected(schrijverDAO.getById(getal).isNogActief());
            }
        });

        view.getSchakelen().setDisable(true);
        view.getAlleSchrijversListView().getSelectionModel().selectedItemProperty().addListener((test) -> {

            if( test == null) {
              view.getSchakelen().setDisable(true);
            }
            else{
                view.getSchakelen().setDisable(false);
            }
        });

    }


    private void nieuw() {
        view.getAlleSchrijversListView().getSelectionModel().clearSelection();
        view.getTextAreaAchternaam().clear();
        view.getTextAreaVoornaam().clear();
        view.getTextFieldLeeftijd().clear();
        view.getCheckBoxNogActief().setSelected(false);
    }

    private void verwijderen(){
        Schrijver geselecteerdeSchrijver = view.getAlleSchrijversListView().getSelectionModel().getSelectedItem();
            if(geselecteerdeSchrijver == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Er is geen schrijver geselecteerd");
                alert.show();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Weet je zeker dat je deze schrijver wilt verwijderen?");
            Optional<ButtonType> resultaat = alert.showAndWait();
        if(resultaat.isPresent() && resultaat.get() == ButtonType.OK){
            view.getAlleSchrijversListView().getItems().remove(geselecteerdeSchrijver);
            schrijverDAO.remove(geselecteerdeSchrijver);
            nieuw();
        }

    }

    private void opslaan() {
        int count = 0;
        Pattern pattern = Pattern.compile("[a-zA-Z]");

        try {
            schrijvervoornaam = view.getTextAreaVoornaam().getText();

            Matcher schrijvermatcher = pattern.matcher(schrijvervoornaam);
            boolean schrijvervoornaamvalidatie = schrijvermatcher.find();

            if(schrijvervoornaam == null || schrijvervoornaamvalidatie == false){
                throw new Exception();
            }
            count++;


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet de voornaam invoeren");
            alert.show();
        }


        try {
            schrijverachternaam = view.getTextAreaAchternaam().getText();
            Matcher schrijverachternaammatcher = pattern.matcher(schrijverachternaam);
            boolean schrijverachternaamvalidatie = schrijverachternaammatcher.find();

            if(schrijverachternaam == null || schrijverachternaamvalidatie == false){
                throw new Exception();
            }
            count++;

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet de achternaam invoeren");
            alert.show();
        }


        try {
            String tekstveld = view.getTextFieldLeeftijd().getText();
            leeftijd = Integer.parseInt(tekstveld);
            count++;

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet een geldige leeftijd invoeren");
            alert.show();
        }

        nogActief = view.getCheckBoxNogActief().isSelected();

        if(count == MAX_CONTROLLE){
            try{
                if (schrijver == null) {
                    schrijver = new Schrijver(schrijvervoornaam,schrijverachternaam,leeftijd,nogActief);

                    view.getAlleSchrijversListView().getItems().addAll(schrijver);
                    schrijverDAO.addOrUpdate(schrijver);
                }
                else{
                    schrijver.setVoornaam(schrijvervoornaam);
                    schrijver.setAchternaam(schrijverachternaam);
                    schrijver.setLeeftijd(leeftijd);
                    schrijver.setNogActief(nogActief);

                    view.getAlleSchrijversListView().refresh();
                }
            }
            catch(Exception e){
             Alert  alert = new Alert(Alert.AlertType.ERROR, "Er is geen schrijver geselecteerd. ");
             alert.show();

            }
            schrijver = new Schrijver(schrijvervoornaam,schrijverachternaam,leeftijd,nogActief);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Alle waarden zijn correct ingevuld.\n"+ schrijver.toString());
            Optional<ButtonType> resultaat = alert.showAndWait();
            if(resultaat.isPresent() && resultaat.get() == ButtonType.CANCEL){
               return;
            }

            schrijverDAO.addOrUpdate(schrijver);
            view.getAlleSchrijversListView().getItems().addAll(schrijver);
            schrijverDAO.save();
            nieuw();
        }

    }
    private void schakelen() {

            Controller controller = new BoekController(view.getAlleSchrijversListView().getSelectionModel().getSelectedItem());
            MainApplication.switchController(controller);

    }

    private void laden() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Wil je de gegevens laden?");
        Optional<ButtonType> resultaat = alert.showAndWait();
        if(resultaat.get() == ButtonType.OK){
            schrijverDAO.load();
            boekDAO.load();
            loader();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Alle gegevens zijn goed ingeladen.");
            alert.show();

        }

    }
    private void save() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Wil je de gegegevens opslaan?");
        Optional<ButtonType> resultaat = alert.showAndWait();
        if (view.getAlleSchrijversListView().getEditingIndex() != 0) {
            if (resultaat.get() == ButtonType.OK) {
                boekDAO.save();
                schrijverDAO.save();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Alle gegegevens zijn goed opgeslagen.");
                alert.show();

            }
        }
    }

    private void sluiten() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Wil je de code nog een keer opslaan?");
        Optional<ButtonType> resultaat = alert.showAndWait();
        if(resultaat.isPresent() && resultaat.get() == ButtonType.OK){
            schrijverDAO.save();
            boekDAO.save();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Alle gegegevens zijn goed opgeslagen.");
            alert.showAndWait();
            System.exit(0);
        }
        System.exit(0);
    }

    @Override
    public View getView(){
        return view;
    }
}
