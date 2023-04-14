package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Boek;
import practicumopdracht.models.Schrijver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class TextBoekDAO extends BoekDAO{
    private static final String FILENAME = "boeken.txt";

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try ( PrintWriter printwriter = new PrintWriter(file)) {

            printwriter.println(objects.size());

            for (Boek boek : objects) {
                printwriter.println(boek.getTitel());
                printwriter.println(boek.getLancering());
                printwriter.println(boek.getGemiddeldeCijfer());
                printwriter.println(MainApplication.getSchrijverDAO().getIdFor(boek.getHoortBij()));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Bestand is niet gevonden!");
        }
        return true;
    }

    @Override
    public boolean load() {
        File file = new File(FILENAME);
        objects.clear();

        try (Scanner scanner = new Scanner(file)) {

            int aantal = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < aantal; i++) {
                String titel = scanner.nextLine();
                String lancering = scanner.nextLine();
                scanner.nextLine();
                double gemiddeldeCijfer = scanner.nextDouble();
                int index = scanner.nextInt();
                Schrijver schrijver = MainApplication.getSchrijverDAO().getById(index);


               Boek boek = new Boek(titel,LocalDate.parse(lancering), gemiddeldeCijfer,schrijver);

               addOrUpdate(boek);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Bestand is niet gevonden!");
        }
        return false;
    }
}
