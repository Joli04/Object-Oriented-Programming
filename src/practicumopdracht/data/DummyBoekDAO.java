package practicumopdracht.data;

import com.sun.tools.javac.Main;
import practicumopdracht.MainApplication;
import practicumopdracht.controllers.SchrijverController;
import practicumopdracht.models.Boek;
import practicumopdracht.models.Schrijver;

import java.util.List;

public class DummyBoekDAO extends BoekDAO {

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        objects.add(new Boek("Dit is een Boek1",null,7.5));
        objects.add(new Boek("Dit is een Boek2",null,8.5));
        objects.add(new Boek("Dit is een Boek3",null,9.5));


        return true;



    }
}
