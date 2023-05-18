package src;

public class Harcos extends Karakter {
    public Harcos(String nev, int eletero, int gyorsasag, String targy) {
        super(nev, eletero, gyorsasag, targy);
    }

    private int ero;

    public int getEro() {
        return ero;
    }

    public void setEro(int ero) {
        this.ero = ero;
    }
    @Override
    public void kiir() {
        System.out.println( "Karakter (" + this.getClass().getTypeName().replace("src.", "")  +") | neve: " + this.getNev() + " ÉP: " +this.getEletero() + " Gy: " +this.getGyorsasag() +" T: " +this.getTargy() +" Erő: " +this.getEro());
    }

}