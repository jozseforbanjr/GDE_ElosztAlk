package src;

public class Varazslo extends Karakter {
    public Varazslo(String nev, int eletero, int gyorsasag, String targy) {
        super(nev, eletero, gyorsasag, targy);
    }
    private int mannapont;
    public int getManna() {
        return mannapont;
    }

    public void setManna(int manna) {
        //megkötéssel
        if (0 < manna && manna < 13) {
            mannapont = manna;
        }
        else {
            System.out.println("Hibás Varázserő értékmegadás!");
        }

    }

    @Override
    public void kiir() {
        System.out.println( this ); //Java javaslat alapjan .toString() nem szukseges
    }

    public void vegKiiras(String informacio) {
        System.out.println();
        System.out.println( (informacio + this) ); //Java javaslat alapjan .toString() nem szukseges
    }
    // tulterheleses Polimorfizmus eset
    public void vegKiiras(String informacio, int korokSzama) {
        System.out.println();
        System.out.println( (informacio + "Gyors végzet, " + korokSzama  + " kör alatt: " + this ) ); //Java javaslat alapjan .toString() nem szukseges
    }
    @Override
    public String toString() {
        return "Karakter (" + this.getClass().getTypeName().replace("src.", "")  +") | neve: " + this.getNev() + " EP: " +this.getEletero() + " Gy: " +this.getGyorsasag() + " T: " +this.getTargy() + " MP: " +this.getManna();
    }


}
