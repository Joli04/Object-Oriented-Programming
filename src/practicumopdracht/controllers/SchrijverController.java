package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.comparators.VoornaamComparator;
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
    List<Schrijver> schrijvers = schrijverDAO.getAll();

    private void loader() {
        ObservableList<Schrijver> observableschrijvers = FXCollections.observableArrayList(schrijvers);
        FXCollections.sort(observableschrijvers, new VoornaamComparator());
        view.getAlleSchrijversListView().setItems(observableschrijvers);
    }


    public SchrijverController() {
        view = new SchrijverView();
        loader();
        schrijverDAO.load();
        view.getVerwijderen().setOnAction(actionEvent -> verwijderen());
        view.getNieuw().setOnAction(actionEvent -> nieuw());
        view.getSchakelen().setOnAction(actionEvent -> schakelen());
        view.getOpslaan().setOnAction(actionEvent -> opslaan());
        view.getLaden().setOnAction(actionEvent -> laden());
        view.getSave().setOnAction(actionEvent -> save());
        view.getSluiten().setOnAction(actionEvent -> sluiten());
        view.getVoornaamAZ().setOnAction(actionEvent -> az());
        view.getVoornaamZA().setOnAction(actionEvent -> za());

        view.getAlleSchrijversListView().getSelectionModel().selectedItemProperty().addListener((schrijver) -> {

            if (schrijverDAO.getById(view.getAlleSchrijversListView().getSelectionModel().getSelectedIndex()) != null) {
                view.getTextAreaVoornaam().setText(view.getAlleSchrijversListView().getSelectionModel().getSelectedItem().getVoornaam());
                view.getTextAreaAchternaam().setText(view.getAlleSchrijversListView().getSelectionModel().getSelectedItem().getAchternaam());
                view.getTextFieldLeeftijd().setText(String.valueOf(view.getAlleSchrijversListView().getSelectionModel().getSelectedItem().getLeeftijd()));
                view.getCheckBoxNogActief().setSelected(view.getAlleSchrijversListView().getSelectionModel().getSelectedItem().isNogActief());
            }

        });

        view.getSchakelen().setDisable(true);
        view.getVoornaamAZ().setDisable(true);
        view.getAlleSchrijversListView().getSelectionModel().selectedItemProperty().addListener((schakel) -> {
            view.getSchakelen().setDisable(false);
        });
    }

    private void nieuw() {
        view.getAlleSchrijversListView().getSelectionModel().clearSelection();
        view.getTextAreaVoornaam().clear();
        view.getTextAreaVoornaam().setStyle("-fx-background-color: none");
        view.getTextAreaAchternaam().clear();
        view.getTextAreaAchternaam().setStyle("-fx-background-color: none");
        view.getTextFieldLeeftijd().clear();
        view.getCheckBoxNogActief().setSelected(false);
        view.getSchakelen().setDisable(true);
    }

    private void verwijderen() {
        Schrijver geselecteerdeSchrijver = view.getAlleSchrijversListView().getSelectionModel().getSelectedItem();
        if (geselecteerdeSchrijver == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Er is geen schrijver geselecteerd");
            alert.show();
            return;
        }
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Weet je zeker dat je deze schrijver wilt verwijderen?");
        Optional<ButtonType> resultaat = alert.showAndWait();
        if (resultaat.isPresent() && resultaat.get() == ButtonType.OK) {
            view.getAlleSchrijversListView().getItems().remove(geselecteerdeSchrijver);
            schrijverDAO.remove(geselecteerdeSchrijver);
            nieuw();
        }

    }

    private void opslaan() {
        int count = 0;
        Pattern woordpatroon = Pattern.compile("[a-zA-Z]");
        Pattern cijferpatroon = Pattern.compile("[0-9]");

        try {
            schrijvervoornaam = view.getTextAreaVoornaam().getText();

            Matcher schrijvermatcher = woordpatroon.matcher(schrijvervoornaam);
            Matcher cijferschrijvermatcher = cijferpatroon.matcher(schrijvervoornaam);

            boolean schrijvervoornaamvalidatie = schrijvermatcher.find();
            boolean cijferschrijvervoornaamvalidatie = cijferschrijvermatcher.find();

            if (schrijvervoornaam == null || !schrijvervoornaamvalidatie || cijferschrijvervoornaamvalidatie) {
                throw new Exception();
            }
            view.getTextAreaVoornaam().setStyle("-fx-background-color: none");
            count++;


        } catch (Exception e) {
            view.getTextAreaVoornaam().setStyle("-fx-background-color: red");
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet een geldige voornaam invoeren");
            alert.show();
        }

        try {
            schrijverachternaam = view.getTextAreaAchternaam().getText();

            Matcher schrijverAchternaamMatcher = woordpatroon.matcher(schrijverachternaam);
            Matcher schrijverAchternaamMatcherCijfer = cijferpatroon.matcher(schrijverachternaam);

            boolean schrijverAchternaamValidatie = schrijverAchternaamMatcher.find();
            boolean schrijverAchternaamValidatieCijfer = schrijverAchternaamMatcherCijfer.find();

            if (schrijverachternaam == null || !schrijverAchternaamValidatie || schrijverAchternaamValidatieCijfer) {
                throw new Exception();
            }
            view.getTextAreaAchternaam().setStyle("-fx-background-color: none");
            count++;

        } catch (Exception e) {
            view.getTextAreaAchternaam().setStyle("-fx-background-color: red");
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet een geldige achternaam invoeren");
            alert.show();
        }


        try {
            String tekstveld = view.getTextFieldLeeftijd().getText();
            leeftijd = Integer.parseInt(tekstveld);

            if (leeftijd < 12) {
                throw new Exception();
            }
            count++;

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet een geldige leeftijd invoeren");
            alert.show();
        }

        nogActief = view.getCheckBoxNogActief().isSelected();

        if (leeftijd >= 65) {
            nogActief = false;
        }

        if (count == MAX_CONTROLLE) {
            if (view.getAlleSchrijversListView().getSelectionModel().getSelectedItem() == null) {
                try {
                    schrijver = new Schrijver(schrijvervoornaam, schrijverachternaam, leeftijd, nogActief);
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Alle waarden zijn correct ingevuld.\n" + schrijver.toString());
                    Optional<ButtonType> resultaat = alert.showAndWait();
                    if (resultaat.isPresent() && resultaat.get() != ButtonType.CANCEL) {
                        schrijverDAO.addOrUpdate(schrijver);
                        FXCollections.sort(view.getAlleSchrijversListView().getItems(), new VoornaamComparator());
                        loader();
                        nieuw();
                    }
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR, "Het bestand is nog niet geladen.");
                    alert.show();
                }
            } else {
                try {
                    schrijver = view.getAlleSchrijversListView().getSelectionModel().getSelectedItem();
                    schrijver.setVoornaam(schrijvervoornaam);
                    schrijver.setAchternaam(schrijverachternaam);
                    schrijver.setLeeftijd(leeftijd);
                    schrijver.setNogActief(nogActief);

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Alle waarden zijn correct ingevuld.\n" + schrijver.toString());

                    Optional<ButtonType> resultaat = alert.showAndWait();

                    if (resultaat.isPresent() && resultaat.get() != ButtonType.CANCEL) {
                        view.getAlleSchrijversListView().refresh();
                        loader();
                        nieuw();

                    }
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Er is iets fout gegaan tijdens het wijzigen van de gegevens. Controlleer de velden nog eens.");
                    alert.show();
                }
            }
        }
    }

    private void schakelen() {
        Controller controller = new BoekController(view.getAlleSchrijversListView().getSelectionModel().getSelectedItem());
        MainApplication.switchController(controller);
    }

    private void laden() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Wil je de gegevens laden?");
        Optional<ButtonType> resultaat = alert.showAndWait();
        if (resultaat.isPresent() && resultaat.get() == ButtonType.OK) {
            loader();
            boekDAO.load();
            schrijverDAO.load();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Het bestand is goed ingeladen");
            alert.show();

        }
    }

    private void save() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Wil je de gegegevens opslaan?");

        try {
            Optional<ButtonType> resultaat = alert.showAndWait();
            if (view.getAlleSchrijversListView().getEditingIndex() != 0) {
                if (resultaat.isPresent() && resultaat.get() == ButtonType.OK) {
                    boekDAO.save();
                    schrijverDAO.save();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Alle gegegevens zijn goed opgeslagen.");
                    alert.show();
                }
            }
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Er is iets fout gegaan tijdens het opslaan van de gegevens.");
            alert.show();
        }
    }

    private void sluiten() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Wil je de code nog een keer opslaan?");
        Optional<ButtonType> resultaat = alert.showAndWait();
        if (resultaat.isPresent() && resultaat.get() == ButtonType.OK) {
            boekDAO.save();
            schrijverDAO.save();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Alle gegegevens zijn goed opgeslagen.");
            alert.showAndWait();
            System.exit(0);
        }
        System.exit(0);
    }

    private void az() {
        ObservableList<Schrijver> observableschrijvers = FXCollections.observableArrayList(schrijvers);
        FXCollections.sort(observableschrijvers, new VoornaamComparator());
        view.getAlleSchrijversListView().setItems(observableschrijvers);
        view.getVoornaamAZ().setDisable(true);
        view.getVoornaamZA().setDisable(false);
    }

    private void za() {
        ObservableList<Schrijver> observableschrijvers = FXCollections.observableArrayList(schrijvers);
        FXCollections.sort(observableschrijvers, new VoornaamComparator().reversed());
        view.getAlleSchrijversListView().setItems(observableschrijvers);
        view.getVoornaamAZ().setDisable(false);
        view.getVoornaamZA().setDisable(true);
    }

    @Override
    public View getView() {
        return view;
    }
}