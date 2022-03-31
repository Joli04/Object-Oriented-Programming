package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    List<Schrijver> schrijvers = schrijverDAO.getAll();
    ObservableList<Schrijver> observableschrijvers = FXCollections.observableArrayList(schrijvers);


    public SchrijverController() {
        view = new SchrijverView();
        view.getAlleSchrijversListView().setItems(observableschrijvers);
        view.getVerwijderen().setOnAction(actionEvent -> verwijderen());
        view.getNieuw().setOnAction(actionEvent -> nieuw());
        view.getSchakelen().setOnAction(actionEvent -> schakelen());
        view.getOpslaan().setOnAction(actionEvent -> opslaan());

        view.getAlleSchrijversListView().getSelectionModel().selectedItemProperty().addListener((test) -> {

            int getal = view.getAlleSchrijversListView().getSelectionModel().getSelectedIndex();

            view.getTextAreaVoornaam().setText(schrijverDAO.getById(getal).toString());

//            view.getTextAreaVoornaam().setText(String.valueOf(schrijverDAO.getById(getal)));
            view.getTextFieldLeeftijd().setText(String.valueOf(getal));
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
            MainApplication.getSchrijverDAO().remove(geselecteerdeSchrijver);
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
                throw new ArithmeticException();
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
                throw new ArithmeticException();
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

        if(view.getCheckBoxNogActief().isSelected()){
            nogActief = true;
        }
        else{
            nogActief = false;
        }

        if(count == MAX_CONTROLLE){
            schrijver = new Schrijver(schrijvervoornaam,schrijverachternaam,leeftijd,nogActief);
//            if(schrijvers.contains(schrijver)){
//
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Deze schrijver zit al in de lijst");
//                alert.show();
//                nieuw();
//                return;
//            }
            schrijverDAO.addOrUpdate(schrijver);
            view.getAlleSchrijversListView().getItems().addAll(schrijver);
            schrijverDAO.save();






            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("-Alle waarden zijn correct ingevuld."+"\n"+schrijver.toString());
            alert.show();
            nieuw();



        }

    }
    private void schakelen() {
        Controller controller = new BoekController();
        MainApplication.switchController(controller);
    }

    @Override
    public View getView(){
        return view;
    }
}
