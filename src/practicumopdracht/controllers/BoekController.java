package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.Main;
import practicumopdracht.MainApplication;
import practicumopdracht.data.BoekDAO;
import practicumopdracht.data.DummyBoekDAO;
import practicumopdracht.data.SchrijverDAO;
import practicumopdracht.models.Boek;
import practicumopdracht.models.Schrijver;
import practicumopdracht.views.BoekView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoekController extends Controller {

    private BoekView view;
    private final int MAX_CONTROLLE = 4;
    String schrijvernaam;
    String titel;
    LocalDate lancering;
    double gemiddeldeCijfer;
    Boek boek;

    BoekDAO boekDAO = MainApplication.getBoekDAO();

    List<Boek> boeken = boekDAO.getAll();
    ObservableList<Boek> observableboeken = FXCollections.observableArrayList(boeken);

    List<Schrijver> schrijversDAO = MainApplication.getSchrijverDAO().getObjects();
    Schrijver hoortBij;


    public BoekController() {
        view = new BoekView();
        view.getAlleBoekenListView().setItems(observableboeken);
        view.getVerwijderen().setOnAction(actionEvent -> verwijderen());
        view.getNieuw().setOnAction(actionEvent -> nieuw());
        view.getSchakelen().setOnAction(actionEvent -> schakelen());
        view.getOpslaan().setOnAction(actionEvent -> opslaan());


        view.getComboBox().getItems().addAll(schrijversDAO);

        try{
            hoortBij = schrijversDAO.get(0);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Er is geen schrijver.");
        }

    }

    private void nieuw() {
        view.getTitelTextArea().clear();
        view.getLancering().getEditor().clear();
        view.getGemiddeldeCijferTextField().clear();
        view.getComboBox().cancelEdit();
        view.getAlleBoekenListView().getSelectionModel().clearSelection();
    }

    private void verwijderen() {
        Boek geselecteerdeBoek = view.getAlleBoekenListView().getSelectionModel().getSelectedItem();
        if(geselecteerdeBoek == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Er is geen boek geselecteerd");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Weet je zeker dat je dit boek wilt verwijderen?");
        Optional<ButtonType> resultaat = alert.showAndWait();
        if(resultaat.isPresent() && resultaat.get() == ButtonType.OK){
            view.getAlleBoekenListView().getItems().remove(geselecteerdeBoek);
            MainApplication.getBoekDAO().remove(geselecteerdeBoek);
        }

    }

    private void opslaan() {

        Pattern pattern = Pattern.compile("[a-zA-Z]");
        int count = 0;

        try{
            titel = view.getTitelTextArea().getText();
            Matcher titelmatcher = pattern.matcher(titel);
            boolean titelvalidatie = titelmatcher.find();

            if(titel == null || titelvalidatie == false){
                throw new ArithmeticException();
            }
            count++;
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet een titel invoeren");
            alert.show();
        }

        try{
            String tekstveld = view.getGemiddeldeCijferTextField().getText();
            gemiddeldeCijfer = Double.parseDouble(tekstveld);
            count++;
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet een getal invoeren");
            alert.show();
        }


        try{
            lancering = view.getLancering().getValue();
//            String datumInString = datum.toString();
//            Pattern datumpatern = Pattern.compile("[0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((?:19|20)[0-9][0-9]]");
//            Matcher datummatcher = datumpatern.matcher(datumInString);
//            boolean datumvalidatie = datummatcher.find();

            if(lancering == null){
                throw new ArithmeticException();
            }
            count++;
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet een datum invoeren");
            alert.show();
        }


        try{
            schrijvernaam = view.getComboBox().toString();

            if(hoortBij == null){
                throw new ArithmeticException();
            }
            count++;

        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Je moet een schrijver kiezen");
            alert.show();
        }


        if (count == MAX_CONTROLLE){
            boek = new Boek(titel,lancering,gemiddeldeCijfer);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("-Alle waarden zijn correct ingevuld.\n" + boek.toString());
            alert.show();

            view.getAlleBoekenListView().getItems().addAll(boek);
            MainApplication.getBoekDAO().addOrUpdate(boek);

            nieuw();
        }
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
