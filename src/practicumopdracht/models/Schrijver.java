package practicumopdracht.models;

public class Schrijver {
    private String voornaam;
    private String achternaam;
    private int leeftijd;
    private boolean nogActief;

    public Schrijver(String voornaam, String achternaam, int leeftijd, boolean nogActief) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.leeftijd = leeftijd;
        this.nogActief = nogActief;
    }



    @Override
    public String toString() {
        return String.format("voornaam: %s\nachternaam: %s\nleeftijd: %d \nactief: %b",voornaam,achternaam,leeftijd,nogActief);
    }

}
