package src;
/*
Warrior versus Wizard játék
*/

import java.util.Random;

public class WvW_game {

    public static String[] jatekter = {"|", " ", " ", " ", "|"}; //= new String[5]

    public static void main(String[] args) {

        final int h1ee = kockadobasD6() +3; //életerő
        final int h1ero = kockadobasD6() +6; //erő
        final int v1ee = kockadobasD6() +3; //életerő
        final int v1mp = kockadobasD6() + kockadobasD6() +2; //manna pont

        //nev; eletero; gyorsasag; targy;
        Harcos h1 = new Harcos("Ron",h1ee,8,"kard");
        Varazslo v1 = new Varazslo("Trix",v1ee,12,"varazsbot");

        //h1.toString();//
        h1.setEro(h1ero);
        h1.setPozicio(2);
        v1.setManna(v1mp);
        v1.setPozicio(4);
        h1.kiir();
        v1.kiir();


        //v1.toString();//
        Jatek jatek = new Jatek();
        try {
            jatek.jatekMenet(h1,v1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int kockadobasD6() {
        int dertek; //= new Int;
        Random r = new Random();
        dertek = r.nextInt(1,7); // [1-6]
        //System.out.println("D6 dobás értéke: " + Integer.toString(dertek) );//
        return dertek;
    }


}
