package GUI;
import src.*;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class WizardvsWarrior extends JFrame {
    private JPanel WizardvsWarrior = new JPanel();
    private JPanel titlePanel;
    private JLabel titletext;
    private JPanel pnl_Body;
    private JPanel footPanel;
    private JTextField tf_jatekMeneteTextField;
    //forms: .. WizardvsWarrior.form: Cannot bind: field is static: GUI.WizardvsWarrior.tf_jatekkiiras
    // ha meg nem static, akkor nem tudok beleirni,  mert nem hivhato static fuggvenybol
    private JTextField tf_jatekkiiras; // vagy JLabel
    private JTextField tf_foot; // vagy JLabel
    private JPanel pnlMain;
    private JPanel pnl_Gameblock;

    // jatek init
    private static Jatek guijatek = new Jatek();
    private static Harcos h;
    private static Varazslo v;

    public static void main(String[] args) {

        WizardvsWarrior wvw_gui = new WizardvsWarrior();

    }

    // konstruktor
    public WizardvsWarrior() {
        setTitle("WizardvsWarrior");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,400);
        setLocationRelativeTo(null);
        setVisible(true);
        tf_foot.setVisible(false);
        setContentPane(pnlMain);

    }

    private void futtatas() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    guiJatekmenet();
                } catch (InterruptedException inte) {
                    System.err.println(inte.getMessage());
                    tf_foot.setVisible(true);
                    tf_foot.setText(inte.getMessage());
                } catch (KivetelCheckedException kive) {
                    System.err.println(kive.getMessage());
                    tf_foot.setVisible(true);
                    tf_foot.setText(kive.getMessage());
                }
            }
        });
        thread.start();
    }
    public void guiJatekmenet() throws InterruptedException, KivetelCheckedException {

        //játékkör, amíg ... "Csak egy maradhat!"
        while (guijatek.int2bool(h.getEletero()) && guijatek.int2bool(v.getEletero())) {

            //lepesek
            h.lep();
            v.lep();

            TimeUnit.MILLISECONDS.sleep(200); // throws InterruptedException
            String korinfo = guijatek.jatekkor(h, v);
            jtextkiiras(korinfo);

        }


        //Jatek vegi kiiratasok (Ver 5 utani refaktoralas)
        String veginfo = guijatek.jatekVegKiiras(h, v, guijatek.korokszama, "gui");
        jtextkiiras(veginfo);
        //System.out.println(veginfo);

    }

    private void jtextkiiras(String szoveg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                tf_jatekkiiras.setText(szoveg);
            }
        });
    }

}
