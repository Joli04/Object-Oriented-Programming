package practicumopdracht.data;

import javafx.scene.control.Alert;
import practicumopdracht.models.Boek;

import java.io.*;
import java.util.InputMismatchException;

public class ObjectBoekDAO extends BoekDAO{
    @Override
    public boolean save() {
            File file = new File("boeken.obj");

        try(
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeInt(objects.size());

            for (Boek boek : objects) {
                objectOutputStream.writeObject(boek);
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Er gaat iets fout tijdens het opslaan van de gegevens bij de boeken!");
            alert.show();
        }

        return true;

    }

    @Override
    public boolean load() {
        File file = new File("boeken.obj");
        objects.clear();
        try(
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            int aantalBoeken = objectInputStream.readInt();

            for(int i = 0; i < aantalBoeken; i++) {
                Boek boek = (Boek) objectInputStream.readObject();
                addOrUpdate(boek);
            }

        }
        catch (FileNotFoundException e) {
            System.err.println("Bestand is niet gevonden!");
        }
        catch (InputMismatchException e) {
            System.err.println("Er is een verkeerde waarde ingevuld");
        }
        catch (Exception e){
            System.err.println("Er is iets fout gegaan!");
            e.printStackTrace();
        }

        return false;
    }

}
