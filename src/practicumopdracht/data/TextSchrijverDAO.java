package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Schrijver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TextSchrijverDAO extends SchrijverDAO {
    private static final String SCHRIJVERS = "schrijvers.txt" ;
    @Override
    public boolean save() {
        File file = new File(SCHRIJVERS);
        PrintWriter printwriter = null;
        int i = 0;

        try{
            printwriter = new PrintWriter(file);
//            printwriter.printf("Gebruiker: %s\n------------------------------------------",MainApplication.getSchrijverDAO().getObjects());
            for(Schrijver schrijver: objects){
                printwriter.printf("%s\n------------------------------------------\n",MainApplication.getSchrijverDAO().getById(i));
                i++;
            }

//            printwriter.println(MainApplication.getSchrijverDAO().getObjects());
        }
        catch (FileNotFoundException e){
            System.out.println("Bestand is niet gevonden!");
        }
        finally {
            printwriter.close();
        }
        return true;
    }

    @Override
    public boolean load() {
        return false;
    }
}
