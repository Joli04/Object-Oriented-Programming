package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Boek;

import java.time.LocalDate;

public class DummyBoekDAO extends BoekDAO {
    SchrijverDAO schrijverDAO = MainApplication.getSchrijverDAO();

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        objects.add(new Boek("Dit is een Boek1", LocalDate.now(),7.5,schrijverDAO.getById(0)));
        objects.add(new Boek("Dit is een Boek2",null,8.5,schrijverDAO.getById(1)));
        objects.add(new Boek("Dit is een Boek3",LocalDate.of(2005,05,12),9.5,schrijverDAO.getById(2)));


        return true;



    }
}
