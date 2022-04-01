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

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public boolean isNogActief() {
        return nogActief;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public void setNogActief(boolean nogActief) {
        this.nogActief = nogActief;
    }

    @Override
    public String toString() {
        return String.format("voornaam: %s\nachternaam: %s\nleeftijd: %d \nactief: %b",voornaam,achternaam,leeftijd,nogActief);
    }

}
