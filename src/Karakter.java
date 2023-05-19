package src;

import java.util.Random;

public class Karakter {
    private String nev;
    private int eletero;
    private int gyorsasag;
    private String targy;
    private int pozicio; // játéktérbeli pozíció

    public void lep() {
        int lepesmeret;
        Random r = new Random();
        //aktuális lépés értéke:
        lepesmeret = r.nextInt(0,2); // [0: nem lép, 1: lép egy mezőt]

        Boolean irany_bool; // egy soros megjelenítes: jobbra: 1, balra: -1
        irany_bool = r.nextBoolean();
        int irany;
        if (irany_bool) {
            irany = 1;
        }
        else {
            irany = -1;
        }
        lepes(lepesmeret, irany);
    }

    private void lepes(int meret, int irany) {
        //játéktér határai általi limit
        int ujpozicio = this.pozicio + meret*irany;

        if (!(ujpozicio < 2 || ujpozicio > 4)) {
            this.setPozicio(ujpozicio);
        }
        /*else {
            //this.setPozicio(this.pozicio); // + meret * irany;
        }*/
        //DEBUG test line
        //System.out.println(this.nev +  " itt áll: " + this.pozicio);
    }

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
        //megkötéssel
        if (0 <= eletero && eletero < 13) {
            this.eletero = eletero;
        }
        else {
            System.out.println("Hibás Életerő értékmegadás!");
        }
    }

    public int getGyorsasag() {
        return gyorsasag;
    }

    public void setGyorsasag(int gyorsasag) {
        //megkötéssel
        if (0 < gyorsasag && gyorsasag < 13) {
            this.gyorsasag = gyorsasag;
        }
        else {
            System.out.println("Hibás Gyorsaság értékmegadás!");
        }
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getPozicio() {
        return pozicio;
    }

    public void setPozicio(int pozicio) {
        this.pozicio = pozicio;
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
