package src;

public class Karakter {
    private String nev;
    private int eletero;
    private int gyorsasag;
    private String targy;

    public String getTargy() {
        return targy;
    }

    public void setTargy(String targy) {
        this.targy = targy;
    }

    public int getEletero() {
        return eletero;
    }

    public void setEletero(int eletero) {
        this.eletero = eletero;
    }

    public int getGyorsasag() {
        return gyorsasag;
    }

    public void setGyorsasag(int gyorsasag) {
        this.gyorsasag = gyorsasag;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    //constructor
    public Karakter(String nev, int eletero, int gyorsasag, String targy) {
        this.nev = nev;
        this.eletero = eletero;
        this.gyorsasag = gyorsasag;
        this.targy = targy;
    }

    @Override
    public String toString() {
        return "Karakter | neve: " +nev + "ÉP: " +eletero + "Gy: " +gyorsasag +"T: " +targy;
    }

    public void kiir() {
        System.out.println( "Karakter | neve: " + this.getNev() + " ÉP: " +this.getEletero() + " Gy: " +this.getGyorsasag() +" T: " +this.getTargy());
    }

}
