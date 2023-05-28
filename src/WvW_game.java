package src;
/*
Warrior versus Wizard játék
*/

import java.util.ArrayList;
import java.util.Random;

public class WvW_game {

    public static String[] jatekter = {"|", " ", " ", " ", "|"}; //= new String[5]
    public static Harcos h1 = null;
    public static Varazslo v1 = null;

    public static void main(String[] args) throws RuntimeException, KivetelCheckedException {

        final int h1ee = kockadobasD6() +3; //életerő
        final int h1ero = kockadobasD6() +6; //erő
        final int v1ee = kockadobasD6() +3; //életerő
        final int v1mp = kockadobasD6() + kockadobasD6() +2; //manna pont
        Jatek jatek = new Jatek();

        /* "Polimorfizmus" példa: -- az egyszerűsítés kedvéért a 7. feladatnál törölve,
           korábbi verzióban funkcionált
        //nev; eletero; gyorsasag; targy;
        Karakter harcos_KarakterTipus = new Harcos("Ron",h1ee,8,"kard");
        Karakter varazslo_KarakterTipus = new Varazslo("Trix",v1ee,12,"varazsbot");

        // a helyes mukodeshez at kell castolni a megfelelo tipusra a ket Karakter objektumot
        Harcos h1 = (Harcos) harcos_KarakterTipus;
        Varazslo v1 = (Varazslo) varazslo_KarakterTipus; */

        Boolean kezdes_valasztas = jatek.felhasznalovalasz("Szeretnéd betölteni egy korábbi játék állását? (i/n): ");

        if (kezdes_valasztas) {
            ArrayList<Karakter> karakterek = new ArrayList<>();

            karakterek = jatek.jatekBetoltes(h1, v1);
            // feldolgozas
            h1 = (Harcos) karakterek.get(0);
            v1 = (Varazslo) karakterek.get(1);
            h1.kiir();
            v1.kiir();
            System.out.println("Indul a játék!");
        }
        else {
            //nev; eletero; gyorsasag; targy;
            h1 = new Harcos("Ron",h1ee,8,"kard");
            v1 = new Varazslo("Trix",v1ee,12,"varazsbot");

            h1.setEro(h1ero);
            h1.setPozicio(2);
            v1.setManna(v1mp);
            v1.setPozicio(4);

            h1.kiir(); //refaktoralva egy .toString @Override implementalas miatt
            v1.kiir();
        }
        System.out.println("0");

        try {
            System.out.println("1");
            jatek.jatekMenet(h1,v1);

        }
        catch (KivetelCheckedException kiv) {
            throw new KivetelCheckedException(kiv.getMessage().toString());
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // NotSerializableException hiba a Karakter implements Serializable -vel kezelve
        // a játék végi mentés utáni kilépés biztosítása,
        // mentési opció implementálása előtt nem volt szükséges
        System.exit(0);
    }

    public static int kockadobasD6() {
        int dertek; //= new Int;
        Random r = new Random();
        dertek = r.nextInt(1,7); // [1-6]
        //System.out.println("D6 dobás értéke: " + Integer.toString(dertek) );//
        return dertek;
    }


}
