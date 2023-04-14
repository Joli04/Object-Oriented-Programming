package practicumopdracht.data;

import practicumopdracht.models.Schrijver;

import java.util.ArrayList;
import java.util.List;

public abstract class SchrijverDAO implements DAO<Schrijver> {
    protected List<Schrijver> objects = new ArrayList<>();

    public Schrijver getById(int id) {
        try{
            return objects.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public int getIdFor(Schrijver object){
        if(objects.contains(object)){
            return objects.indexOf(object);
        }
        else{
            return -1;
        }

    }

    public abstract boolean save();

    public abstract boolean load();


    @Override
    public void addOrUpdate(Schrijver object) {
        if(objects.contains(object)) {
            return;
        }
        objects.add(object);

    }

    @Override
    public void remove(Schrijver object) {
        objects.remove(object);
    }


    public List<Schrijver> getObjects() {
        return objects;
    }

    @Override
    public List<Schrijver> getAll() {
        return objects;
    }

}
