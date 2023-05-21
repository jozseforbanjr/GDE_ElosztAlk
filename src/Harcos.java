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
        System.out.println( this ); //Java javaslat alapjan .toString() nem szukseges
    }
    @Override
    public String toString() {
        return "Karakter  (" + this.getClass().getTypeName().replace("src.", "")  +") | neve: " + this.getNev() + " EP: " +this.getEletero() + " Gy: " +this.getGyorsasag() +" T: " +this.getTargy() +" Erő: " +this.getEro();
    }

    public void vegKiiras(String informacio) {
        System.out.println();
        System.out.println( (informacio + this) ); //Java javaslat alapjan .toString() nem szukseges
    }
    // tulterheleses Polimorfizmus eset
    public void vegKiiras(String informacio, int korokSzama) {
        System.out.println();
        System.out.println( (informacio + "Gyors végzet, " + korokSzama  + " kör alatt: " + this) ); //Java javaslat alapjan .toString() nem szukseges
    }

}