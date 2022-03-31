package practicumopdracht.models;

import com.sun.glass.ui.Menu;

import java.time.LocalDate;

public class Boek {
    private Schrijver hoortBij;
    private String titel;
    private LocalDate lancering;
    private double gemiddeldeCijfer;


    public Boek(String titel, LocalDate lancering, double gemiddeldeCijfer) {
        this.titel = titel;
        this.lancering = lancering;
        this.gemiddeldeCijfer = gemiddeldeCijfer;
    }

    public void setHoortBij(Schrijver hoortBij) {
        this.hoortBij = hoortBij;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setLancering(LocalDate lancering) {
        this.lancering = lancering;
    }

    public void setGemiddeldeCijfer(double gemiddeldeCijfer) {
        this.gemiddeldeCijfer = gemiddeldeCijfer;
    }

    @Override
    public String toString() {
        return "titel: " + titel+"\n"+"lancering: "+lancering+"\n"+"gemiddeldeCijfer: " + gemiddeldeCijfer +"\n";
    }
}
