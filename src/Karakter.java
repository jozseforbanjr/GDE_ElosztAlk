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
            this.eletero = WvW_game.kockadobasD6();
            System.out.println("Hibás Életerő értékmegadás! d6 érték lett beállítva");
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
            this.pozicio = WvW_game.kockadobasD6();
            System.out.println("Hibás Gyorsaság értékmegadás! d6 érték lett beállítva");
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
        if (1<pozicio && pozicio< WvW_game.jatekter.length){
            this.pozicio = pozicio;
        }
        else {
            this.pozicio = 2;
            System.out.println("Hibás pozíció megadás! Karakter a 2-es pozícióba került!");
        }
    }

    //Konstruktor, a hivaslanc a jelen programban nem szukseges
    // csak mukodesbiztonsagi okokbol
    public Karakter() {
        this("Karakter");
        System.out.println("Hiba! Karakter nevet meg kell adni!");
        //"Karakter" + this.hashCode() nem mukodik,
        // mert az obj nincs meg kesz es nincs hashcode-ja
        /* fuggvenyhivas sem lehetseges, mert
        nem lehet a visszateresi erteket atadni (a konstruktorlancban):
        String nev;
        nev = randomNevGeneralas();*/
    }
    public Karakter(String nev) {
        this(nev, WvW_game.kockadobasD6()+3);
        /*int eletero;
        eletero = WvW_game.kockadobasD6()+3;*/
        
    }
    public Karakter(String nev, int eletero) {
        this(nev, eletero, WvW_game.kockadobasD6());
        /*int gyorsasag;
        gyorsasag = WvW_game.kockadobasD6();*/
        
    }
    public Karakter(String nev, int eletero, int gyorsasag) {
        this(nev, eletero, gyorsasag, "n.a.");
        //String targy = targygeneralas();//
    }

    private String targygeneralas() {
        String targy;
        if (this.getClass().getTypeName().equals("Harcos")) {
            targy = "kard";
        }
        else {
            targy = "varazsbot";
        }
        return targy;
    }

    public Karakter(String nev, int eletero, int gyorsasag, String targy) {
        this.nev = nev;
        this.eletero = eletero;
        this.gyorsasag = gyorsasag;
        this.targy = targy;
    }

    @Override
    public String toString() {
        String adat1 = "Karakter | neve: " + this.getNev() + " ÉP: " + this.getEletero() +
                " Gy: " + this.getGyorsasag() + " Tárgy: " + this.getTargy();
        // az egyedi (H / V) tulajdonsagokat nem tudom lekerdezni/kiiratni
        return adat1;
    }

    public void kiir() {  //refaktoralva
        System.out.println(this.toString());
    }

    private String randomNevGeneralas() {
        int a_Limit = 97; // "a" betu ASCII kodja
        int z_Limit = 122; // "z" betu ASCII kodja
        int nevHossz = 6;
        Random r = new Random();
        StringBuilder buffer = new StringBuilder(nevHossz);
        for (int k = 0; k < nevHossz; k++) {
            int randomLimitedInt = a_Limit + (int)(r.nextFloat() * (z_Limit - a_Limit +1 ));
            // elso karakter nagybetu
            if (k==0) {
                Character c = (char)randomLimitedInt;
                buffer.append(Character.toUpperCase(c));
            }
            else {
                buffer.append((char) randomLimitedInt);
            }

        }
        String generaltString = buffer.toString();
        return generaltString;
    }
}
