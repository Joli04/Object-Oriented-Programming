package practicumopdracht.comparators;

import practicumopdracht.models.Boek;

import java.util.Comparator;

public class GemiddeldeComparator implements Comparator<Boek> {
    @Override
    public int compare(Boek object1, Boek object2) {
        int compare = Double.compare(object2.getGemiddeldeCijfer(), object1.getGemiddeldeCijfer());

        if(compare == 0) {
            return object2.getTitel().compareTo(object1.getTitel());
        }

        return compare;
    }

}
