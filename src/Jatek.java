package src;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static src.WvW_game.jatekter;

public class Jatek {
    public static int korokszama = 0;
    public void jatekMenet(Harcos h, Varazslo v) throws InterruptedException, KivetelCheckedException {
        h.setPozicio(2);
        v.setPozicio(4);
        String jatekmezo_kezdo = kirajzol(h, v); //kezdo allapot - GUI miatt refaktoralva
        System.out.println(jatekmezo_kezdo);

        //játékkör, amíg ... "Csak egy maradhat!"
        while (int2bool(h.getEletero()) && int2bool(v.getEletero())) {

            //lepesek
            h.lep();
            v.lep();
            //megjelenites
            /*String lepesinfo = kirajzol(h, v);
            System.out.println(lepesinfo);*/

            String korinfo = jatekkor(h, v);
            System.out.println(korinfo);
            /*TimeUnit.MILLISECONDS.sleep(200); // throws InterruptedException
            korokszama += 1;
            // saját kivétel függvény, teszteléshez célszerű pl. 3-ra állítani a korokszama max. szintjét
            if (korokszama > 10) {
                jatekVegKiiras( h, v, korokszama);
                String error_info = "Túl sokáig tart a játék!";
                System.err.println(error_info); //java: unreachable statement hibát dobnak futtatáskor
                Logger.getLogger(WvW_game.class.getName()).log(Level.WARNING, null, error_info);
                throw new KivetelCheckedException(error_info);
            }*/
        }
        
        //Jatek vegi kiiratasok (Ver 5 utani refaktoralas)
        String veginfo = jatekVegKiiras(h, v, korokszama, "console");
        System.out.println(veginfo);

    }

    public  String jatekkor(Harcos h, Varazslo v) throws InterruptedException, KivetelCheckedException {
        //lepesek
        h.lep();
        v.lep();
        //megjelenites
        String korinfo =  kirajzol(h, v);

        TimeUnit.MILLISECONDS.sleep(200); // throws InterruptedException
        korokszama += 1;
        // saját kivétel függvény, teszteléshez célszerű pl. 3-ra állítani a korokszama max. szintjét
        if (korokszama > 10) {
            String veginfo = jatekVegKiiras(h, v, korokszama, "console");
            String error_info = "Túl sokáig tart a játék!";
            System.err.println(error_info); //java: unreachable statement hibát dobnak futtatáskor
            Logger.getLogger(WvW_game.class.getName()).log(Level.WARNING, null, error_info);
            throw new KivetelCheckedException(error_info);
        }
        return korinfo;
    }

    public String jatekVegKiiras(Harcos h, Varazslo v, int korokszama, String caller) {
        String vegszoveg;
        if (!int2bool(h.getEletero()) && !int2bool(v.getEletero())) {
            System.out.println();
            vegszoveg = "Szörnyű tragédia! Nincs túlélő!";
            if (caller.equals("console")) {
                System.out.println(vegszoveg);
            }
        }
        else if (!int2bool(h.getEletero())) {
            System.out.println();
            vegszoveg = "A varázsló győzőtt! ";
            if (korokszama < 5) { // csak a Tulterheleses Polimorfizmus bemutatasara kialakitott elagazas
                if (caller.equals("console")) {
                    v.vegKiiras(vegszoveg, korokszama);
                }
            }
            else {
                if (caller.equals("console")) {
                    v.vegKiiras(vegszoveg);
                }
            }
        }
        else if (!int2bool(v.getEletero())) {
            vegszoveg = "A harcos győzőtt! ";
            if (korokszama < 5) { // csak a Tulterheleses Polimorfizmus bemutatasara kialakitott elagazas
                if (caller.equals("console")) {
                    h.vegKiiras(vegszoveg, korokszama);
                }
            }
            else {
                if (caller.equals("console")) {
                    h.vegKiiras(vegszoveg);
                }
            }
        }
        else {
            vegszoveg = "MINDENKI Elfáradt, hazamennek!";
            if (caller.equals("console")) {
                h.vegKiiras("Elfáradt, hazamegy!        ");
                v.vegKiiras("Elfáradt, szintén hazamegy! ");
            }
        }
        return vegszoveg;
    }

    private String kirajzol(Harcos h, Varazslo v) {
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
        return (jatekrajz + harcszoveg + eleteroAdat);
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

