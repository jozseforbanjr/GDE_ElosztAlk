package src;

import java.util.concurrent.TimeUnit;
import static src.WvW_game.jatekter;

public class Jatek {
    public static int korokszama = 0;
    void jatekMenet(Harcos h, Varazslo v) throws InterruptedException {
        h.setPozicio(2);
        v.setPozicio(4);
        kirajzol(h, v); //kezdo allapot
        //játékkör, amíg ... "Csak egy maradhat!"
        while (int2bool(h.getEletero()) && int2bool(v.getEletero())) {

            //lepesek
            h.lep();
            v.lep();
            //megjelenites
            kirajzol(h, v);

            TimeUnit.MILLISECONDS.sleep(200);
            korokszama += 1;
        }
        
        //Jatek vegi kiiratasok (Ver 5 utani refaktoralas)
        jatekVegKiiras(h, v, korokszama);

    }

    private void jatekVegKiiras(Harcos h, Varazslo v, int korokszama) {

        if (!int2bool(h.getEletero()) && !int2bool(v.getEletero())) {
            System.out.println();
            System.out.println("Szörnyű tragédia! Nincs túlélő!");
        }
        else if (!int2bool(h.getEletero())) {
            System.out.println();
            if (korokszama < 5) { // csak a Tulterheleses Polimorfizmus bemutatasara kialakitott elagazas
                v.vegKiiras("A varázsló győzőtt! ", korokszama);
            }
            else {
                v.vegKiiras("A varázsló győzőtt! ");
            }
        }
        else if (!int2bool(v.getEletero())) {

            if (korokszama < 5) { // csak a Tulterheleses Polimorfizmus bemutatasara kialakitott elagazas
                h.vegKiiras("A harcos győzőtt! ", korokszama);
            }
            else {
                h.vegKiiras("A harcos győzőtt! ");
            }
        }
    }

    private void kirajzol(Harcos h, Varazslo v) {
        int vPoz = v.getPozicio();
        int hPoz = h.getPozicio();
        String jatekrajz = "";
        String eleteroAdat = "";
        String harcszoveg = "     ";

        for (int i = 1; i < jatekter.length+1; i++) {

            if (i == 1 || i == jatekter.length) {
                //System.out.print("|");
                jatekrajz = jatekrajz + "|";
            } else {

                if (vPoz == hPoz && hPoz == i) {
                    //System.out.print("X");
                    jatekrajz = jatekrajz + "X";
                    //harc
                    harc(h, v);
                    harcszoveg = " harc";
                } else if (vPoz == i) {
                    //System.out.print("V");
                    jatekrajz = jatekrajz + "V";
                } else if (hPoz == i) {
                    //System.out.print("H");
                    jatekrajz = jatekrajz + "H";
                } else {
                    //System.out.print(""); //Hibás pozíció adatok!");
                    jatekrajz = jatekrajz + " ";
                }
            }

        }
        eleteroAdat = " H(É): " + h.getEletero() + " V(É): " + v.getEletero();
        System.out.println(jatekrajz + harcszoveg + eleteroAdat);
    }

    private void harc(Harcos h, Varazslo v) {
        // varazslo es harcos is d6 sebzest ejt a masikon
        int v_sebzes = WvW_game.kockadobasD6();
        int h_sebzes = WvW_game.kockadobasD6();
        int v_eletero = v.getEletero() - h_sebzes;
        int h_eletero = h.getEletero() - v_sebzes;
        if (int2bool(v_eletero)) {
            v.setEletero(v_eletero);
        }
        else {
            v.setEletero(0); //jatek vege
        }

        if (int2bool(h_eletero)) {
            h.setEletero(h_eletero);
        }
        else {
            h.setEletero(0); //jatek vege
        }
    }

    public Boolean int2bool(int k) {
        Boolean bool;
        if (k>0) {
            bool = Boolean.TRUE;
        }
        else {
            bool = Boolean.FALSE;
        }
        return bool;
    }

}

