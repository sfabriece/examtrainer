package starter;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JFrame{
    private MainPanel mainPanel;
    static Reader reader;
    public Main(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel = new MainPanel();
        getContentPane().add(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(dim);
        setLocationRelativeTo(null);
        setVisible(true);

        reader = new Reader(mainPanel);
    }

    public static void main(String[] args) {
        new Main();
    }
}
