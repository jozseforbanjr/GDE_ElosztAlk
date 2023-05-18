package src;

public class Varazslo extends Karakter {
    public Varazslo(String nev, int eletero, int gyorsasag, String targy) {
        super(nev, eletero, gyorsasag, targy);
    }
    private int Manna;
    public int getManna() {
        return Manna;
    }

    public void setManna(int manna) {
        Manna = manna;
    }

    @Override
    public void kiir() {
        System.out.println( "Karakter (" + this.getClass().getTypeName().replace("src.", "")  +") | neve: " + this.getNev() + " ÉP: " +this.getEletero() + " Gy: " +this.getGyorsasag() +" T: " +this.getTargy() +" MP: " +this.getManna());
    }

}
