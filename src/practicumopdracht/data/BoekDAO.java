package practicumopdracht.data;

import practicumopdracht.models.Boek;
import practicumopdracht.models.Schrijver;

import java.util.ArrayList;
import java.util.List;

public abstract class BoekDAO implements DAO<Boek> {
    protected List<Boek> objects = new ArrayList<>();

    public List<Boek> getAllFor(Schrijver object){
        return (List<Boek>) object;
    }

    public abstract boolean save();

    public abstract boolean load();

    @Override
    public List<Boek> getAll() {
        return objects;
    }

    @Override
    public void addOrUpdate(Boek object) {
        if(objects.contains(object)) {
            return;
        }

        objects.add(object);
    }

    @Override
    public void remove(Boek object) {
        objects.remove(object);
    }
}

