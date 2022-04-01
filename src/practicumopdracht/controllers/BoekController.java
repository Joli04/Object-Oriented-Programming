package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.data.BoekDAO;
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
    private final int MAX_CONTROLLE = 3;
    Schrijver hoortBij;
    String titel;
    LocalDate lancering;
    double gemiddeldeCijfer;
    Boek boek;

    BoekDAO boekDAO = MainApplication.getBoekDAO();

    private void loader(){
        List<Boek> boeken = boekDAO.getAll();
        ObservableList<Boek> observableboeken = FXCollections.observableArrayList(boeken);
        view.getAlleBoekenListView().setItems(observableboeken);
    }


    List<Schrijver> schrijversDAO = MainApplication.getSchrijverDAO().getObjects();


    public BoekController(Schrijver schrijver) {
        view = new BoekView();
        loader();
         view.getComboBox().getSelectionModel().selectedItemProperty().addListener((test) -> {

            int getal = view.getComboBox().getSelectionModel().getSelectedIndex();

            hoortBij = schrijversDAO.get(getal);

            return;
        });
        view.getVerwijderen().setOnAction(actionEvent -> verwijderen());
        view.getNieuw().setOnAction(actionEvent -> nieuw());
        view.getSchakelen().setOnAction(actionEvent -> schakelen());
        view.getOpslaan().setOnAction(actionEvent -> opslaan());


        view.getComboBox().getItems().addAll(schrijversDAO);
        view.getComboBox().getSelectionModel().select(schrijver);

        view.getAlleBoekenListView().getSelectionModel().selectedItemProperty().addListener((test) ->{

            try {
                view.getTitelTextArea().setText(view.getAlleBoekenListView().getSelectionModel().getSelectedItem().getTitel());
                view.getGemiddeldeCijferTextField().setText(String.valueOf(view.getAlleBoekenListView().getSelectionModel().getSelectedItem().getGemiddeldeCijfer()));
                view.getLancering().getEditor().setText(String.valueOf(view.getAlleBoekenListView().getSelectionModel().getSelectedItem().getLancering()));
                loader();
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Het gaat fout");
                alert.show();
                return;
            }
        });
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


        if (count == MAX_CONTROLLE){
            boek = new Boek(titel,lancering,gemiddeldeCijfer,hoortBij);
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
