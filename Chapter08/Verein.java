package Chapter08;

public class Verein {
    public Verein(int v_ID, String name, int liga) {
        this.v_ID = v_ID;
        this.name = name;
        this.liga = liga;
    }

    private int v_ID;
    private String name;
    private int liga;

    public int getV_ID() {
        return v_ID;
    }

    public String getName() {
        return name;
    }

    public int getLiga() {
        return liga;
    }

    public String toString() {
        return v_ID + " " + name + " (" + liga + ")";
    }
}
