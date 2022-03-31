package practicumopdracht.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import practicumopdracht.models.Boek;
import practicumopdracht.models.Schrijver;

import java.util.ArrayList;
import java.util.List;

public class DummySchrijverDAO extends SchrijverDAO{

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        objects.add(new Schrijver("Test1","Testen1",12,true));
        objects.add(new Schrijver("Test2","Testen2",15,false));
        objects.add(new Schrijver("Test3","Testen3",14,true));

        return true;
    }

    @Override
    public List<Schrijver> getAll() {
        return super.getAll();
    }
}
