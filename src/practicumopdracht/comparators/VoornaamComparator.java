package practicumopdracht.comparators;

import practicumopdracht.models.Schrijver;

import java.util.Comparator;

public class VoornaamComparator implements Comparator<Schrijver> {
    @Override
    public int compare(Schrijver object1, Schrijver object2) {
        int compare = object1.getVoornaam().compareTo(object2.getVoornaam());

        if(compare == 0) {
            return object1.getAchternaam().compareTo(object2.getAchternaam());
        }
        return compare;
    }
}
