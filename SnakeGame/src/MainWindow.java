import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(545, 565);
        setLocationRelativeTo(null);
        add(new GameField());
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }
}
