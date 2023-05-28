package src;

import java.io.*;
import java.util.ArrayList;
// import java.util.Scanner; //hibakereses kozben lecserelve
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static src.WvW_game.jatekter;

public class Jatek {
    public static int korokszama = 0;

    FileIO fileio = new FileIO();
    void jatekMenet(Harcos h, Varazslo v) throws InterruptedException, KivetelCheckedException {

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

            TimeUnit.MILLISECONDS.sleep(200); // throws InterruptedException
            korokszama += 1;
            // saját kivétel függvény, teszteléshez célszerű pl. 3-ra állítani a korokszama max. szintjét
            if (korokszama > 10) {
                jatekVegKiiras(h, v, korokszama);
                try {
                    fileio.fajlvalasztas(h, v);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String error_info = "Túl sokáig tart a játék!";
                System.err.println(error_info); //java: unreachable statement hibát dobnak futtatáskor
                Logger.getLogger(WvW_game.class.getName()).log(Level.WARNING, null, error_info);
                throw new KivetelCheckedException(error_info);
            }

        }

        //Jatek vegi kiiratasok (Ver 5 utani refaktoralas)
        jatekVegKiiras(h, v, korokszama);
        Boolean kiiras_valasztas = felhasznalovalasz("Szeretnéd kimenteni a játék állását? (i/n): ");

        if (kiiras_valasztas) {
            try {
                fileio.fajlvalasztas(h, v);
                System.out.println("NEM");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Gyere vissza játszani! Itt a vége!");
        }
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
        else {
            h.vegKiiras("Elfáradt, hazamegy!        ");
            v.vegKiiras("Elfáradt, szintén hazamegy! ");
        }
    }

    private void kirajzol(Harcos h, Varazslo v) {
        int vPoz = v.getPozicio();
        int hPoz = h.getPozicio();
        String jatekrajz = "";
        String eleteroAdat = null;
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

    //FILEIO (refaktoralva a FileIO-ba, bar ott nem mukodik teljesen)

    /*public FileInputStream, FileOutputStream fajlValasztas(String iodirection) throws FileNotFoundException {
        // parent component of the dialog
        JFrame parentFrame = new JFrame();
        String fajlnev = "";
        FileInputStream file_selected_in = null; //new FileInputStream("");
        FileOutputStream file_selected_out = null; //new FileOutputStream("");
        //FileOutputStream file_selected = null; // = new FileOutputStream("jatek.bin");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Add meg a fájl nevét! .bin");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fajlnev = fileChooser.getSelectedFile().getAbsolutePath().toString();
            
        }
        if (iodirection = "in") {
            file_selected_in = new FileInputStream(fajlnev);
            //file_selected_out = FileOutputStream("");
        }
        else {
            //FileInputStream file_selected_in = new FileInputStream("");
            file_selected_out = FileOutputStream(fajlnev);
        }
        System.out.println("Fájl mentése: " + fajlnev); // file_selected.getFD().toString());
        return file_selected_in return file_selected_out;
    }*/

    // overload a betoltes es mentes elkulonitesere
    public ArrayList<Karakter> jatekBetoltes(Harcos h, Varazslo v) {
        ArrayList<Karakter> karakterek =  new ArrayList<Karakter>();
        karakterek = fileio.fajlBeolvasas();

        System.out.println("Harcos és Varázsló betöltve:");
        return karakterek;
    }

    public boolean felhasznalovalasz(String kerdes) throws RuntimeException {
        boolean valasz_bool = Boolean.FALSE;
        //Scanner prompt = new Scanner(System.in);
        var prompt = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(kerdes);
        String valasz = ""; //nextLine
        try {
            valasz = (String) prompt.readLine(); //java szerint a cast felesleges, de hibat dobott
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!(valasz.isBlank() || valasz.isEmpty() || valasz == "")) {
            if (valasz.equals("i")) {
                valasz_bool = Boolean.TRUE;
            }
            else {
                valasz_bool = Boolean.FALSE;
            }
        }
        return valasz_bool;
    }

}

