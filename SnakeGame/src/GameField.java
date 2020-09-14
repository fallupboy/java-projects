import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 528;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 1089;
    private Image apple;
    private Image body;
    private Image head;
    private Image tail;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private int tempXr = x[0];
    private int tempYr = y[0];
    private int tempXd = x[0];
    private int tempYd = y[0];
    private int tempXl = x[0];
    private int tempYl = y[0];
    private int tempXu = x[0];
    private int tempYu = y[0];
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    ImageIcon iia = new ImageIcon("apple.png");
    ImageIcon iib = new ImageIcon("body.png");
    ImageIcon iih_r = new ImageIcon("head_r.png");
    ImageIcon iih_d = new ImageIcon("head_d.png");
    ImageIcon iih_l = new ImageIcon("head_l.png");
    ImageIcon iih_u = new ImageIcon("head_u.png");
    ImageIcon iit_r = new ImageIcon("tail_r.png");
    ImageIcon iit_d = new ImageIcon("tail_d.png");
    ImageIcon iit_l = new ImageIcon("tail_l.png");
    ImageIcon iit_u = new ImageIcon("tail_u.png");

    public GameField() {
        setBackground(Color.pink);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 16;
            y[i] = 16;
        }
        timer = new Timer(70, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(32) * DOT_SIZE;
        appleY = new Random().nextInt(32) * DOT_SIZE;
    }

    public void loadImages() {
        apple = iia.getImage();
        body = iib.getImage();
        tail = iit_r.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(body, x[i], y[i], this);
            }
            g.drawImage(head, x[0], y[0], this);
            g.drawImage(tail, x[dots], y[dots], this);
        } else {
            String str = "Game Over";
            String str2 = "Press 'Space' to restart";
            Font f = new Font("Arial", Font.BOLD, 36);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, SIZE / 3, SIZE / 2);
            g.drawString(str2, SIZE / 8, SIZE - 200);
        }
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];

            if (x[dots] == tempXl && y[dots] == tempYl) {
                tail = iit_l.getImage();
            }
            if (x[dots] == tempXu && y[dots] == tempYu) {
                tail = iit_u.getImage();
            }
            if (x[dots] == tempXr && y[dots] == tempYr) {
                tail = iit_r.getImage();
            }
            if (x[dots] == tempXd && y[dots] == tempYd) {
                tail = iit_d.getImage();
            }

        }
        if (left) {
            head = iih_l.getImage();
            x[0] -= DOT_SIZE;
        }
        if (right) {
            head = iih_r.getImage();
            x[0] += DOT_SIZE;
        }
        if (up) {
            head = iih_u.getImage();
            y[0] -= DOT_SIZE;
        }
        if (down) {
            head = iih_d.getImage();
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            createApple();
        }
    }

    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }

        if (x[0] > SIZE) {
            x[0] = 0;
        }

        if (x[0] < 0) {
            x[0] = SIZE;
        }
        if (y[0] > SIZE) {
            y[0] = 0;
        }
        if (y[0] < 0) {
            y[0] = SIZE;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right || key == KeyEvent.VK_A && !right) {
                tempXl = x[0];
                tempYl = y[0];
                left = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_RIGHT && !left || key == KeyEvent.VK_D && !left) {
                tempXr = x[0];
                tempYr = y[0];
                right = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_UP && !down || key == KeyEvent.VK_W && !down) {
                tempXu = x[0];
                tempYu = y[0];
                up = true;
                right = false;
                left = false;
            }

            if (key == KeyEvent.VK_DOWN && !up || key == KeyEvent.VK_S && !up) {
                tempXd = x[0];
                tempYd = y[0];
                down = true;
                right = false;
                left = false;
            }

            if (key == KeyEvent.VK_SPACE) {
                MainWindow mw = new MainWindow();
                mw.createUI();
            }
        }
    }
}