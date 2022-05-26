package Chapter08;

import java.util.Map;

public class Spiel {
    public Spiel(int spiel_ID, int spieltag, String datum, String uhrzeit, int heim, int gast, int tore_Heim,
            int tore_Gast) {
        this.spiel_ID = spiel_ID;
        this.spieltag = spieltag;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.heim = heim;
        this.gast = gast;
        this.tore_Heim = tore_Heim;
        this.toreGast = tore_Gast;
    }

    private int spiel_ID;
    private int spieltag;
    private String datum;
    private String uhrzeit;
    private int heim;
    private int gast;
    private int tore_Heim;
    private int toreGast;

    public int getSpiel_ID() {
        return spiel_ID;
    }

    public int getSpieltag() {
        return spieltag;
    }

    public String getDatum() {
        return datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public int getHeimID() {
        return heim;
    }

    public int getGastID() {
        return gast;
    }

    public int getToreHeim() {
        return tore_Heim;
    }

    public int getToreGast() {
        return toreGast;
    }

    public String toString() {
        return new StringBuilder()
                .append(spiel_ID).append(" ")
                .append(spieltag).append(" ")
                .append(datum + " " + uhrzeit).append(" ")
                .append(heim).append(" gegen ").append(gast).append(" ")
                .append(tore_Heim).append(":").append(toreGast).toString();
    }

    String toString(Map<Integer, Verein> vereine) {
        return new StringBuilder()
                .append(spiel_ID).append(" ")
                .append(spieltag).append(" ")
                .append(datum + " " + uhrzeit).append(" ")
                .append(vereine.get(heim).getName()).append(" gegen ").append(vereine.get(gast).getName()).append(" ")
                .append(tore_Heim).append(":").append(toreGast).toString();
    }
}