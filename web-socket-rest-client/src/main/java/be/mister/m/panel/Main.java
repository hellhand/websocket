package be.mister.m.panel;

/**
 * Created by marc on 26/03/15.
 */
public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPanel().setVisible(true);
            }
        });
    }
}
