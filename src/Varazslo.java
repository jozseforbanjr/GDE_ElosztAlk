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
        System.out.println( "Karakter (" + this.getClass().getTypeName().replace("src.", "")  +") | neve: " + this.getNev() + " ÉP: " +this.getEletero() + " Gy: " +this.getGyorsasag() +" T: " +this.getTargy() +" MP: " +this.getManna());
    }

}
