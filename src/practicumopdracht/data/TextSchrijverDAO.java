package practicumopdracht.data;
import practicumopdracht.models.Schrijver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TextSchrijverDAO extends SchrijverDAO {
    private static final String FILENAME = "schrijvers.txt";

    @Override
    public boolean save() {
        File file = new File(FILENAME);
        try ( PrintWriter printwriter = new PrintWriter(file)) {
            printwriter.println(objects.size());

            for (Schrijver schrijver : objects) {
                printwriter.println(schrijver.getVoornaam());
                printwriter.println(schrijver.getAchternaam());
                printwriter.println(schrijver.getLeeftijd());
                printwriter.println(schrijver.isNogActief());
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
                String voornaam = scanner.nextLine();
                String achternaam = scanner.nextLine();
                int leeftijd = scanner.nextInt();
                scanner.nextLine();
                boolean nogActief = scanner.hasNext();
                scanner.nextLine();

                Schrijver schrijver = new Schrijver(voornaam, achternaam, leeftijd, nogActief);

                addOrUpdate(schrijver);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Bestand is niet gevonden!");
        }
        return false;
    }

}