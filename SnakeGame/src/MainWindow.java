import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {
    JFrame frame;

    public void createUI() {
        frame = new JFrame("Snake");
        frame.setSize(556, 580);

        Container pane = frame.getContentPane();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        pane.add(new GameField());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.createUI();
    }
}
