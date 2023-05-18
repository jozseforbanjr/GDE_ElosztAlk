package src;

import java.util.Random;

public class WvW_game {
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
        v1.setManna(v1mp);
        h1.kiir();
        v1.kiir();


        //v1.toString();//

    }

    public static int kockadobasD6() {
        int dertek; //= new Int;
        Random r = new Random();
        dertek = r.nextInt(1,6)+1; // [1-6]
        //System.out.println("D6 dobás értéke: " + Integer.toString(dertek) );//
        return dertek;
    }
}
