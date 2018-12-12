package sunc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

public class Heart extends JWindow {

    private Color color = new Color(0, 0, 0, 0.0f);
    private static Integer seconds = 0;
    private Thread t = new Thread(Heart.this::task);
    private static boolean wait = true;
    private static boolean reset = false;

    private Heart() {
        super();
        setSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        setLocation(0, 0);
        setBackground(color);
        setContentPane(new MyPanel());
        addMouseListener(new CustomListener());
        setLayout(null);

        ImageIcon ig = new ImageIcon(Constant.imagePath_gif);
        JLabel label = new JLabel(ig);
        int iconWidth = ig.getIconWidth();
        int iconHeight = ig.getIconHeight();
        label.setBounds(new Rectangle(Constant.center_x - iconWidth / 2, Constant.center_y - iconHeight / 2, iconWidth, iconHeight));

        label.setLocation(Constant.center_x - ig.getIconWidth() / 2, Constant.center_y - ig.getIconHeight() / 2);

        add(label);
    }

    class CustomListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            seconds = 0;
            wait = false;
            reset = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            wait = true;
            reset = false;
            if (seconds > Constant.stop) {
                dispose();
            }
            seconds = 0;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private synchronized void task() {
        while (isValid()) {
            if (wait) {
                try {
                    this.wait(Constant.millSeconds);
                    continue;
                } catch (InterruptedException ignored) {
                }
            }
            if (reset && t.getState().equals(Thread.State.TIMED_WAITING)) {
                t.notify();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(Constant.millSeconds);
                seconds++;
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void reset() {
        setBackground(color);
    }

    public static void main(String[] args) throws InterruptedException {
        Heart dc = new Heart();

        dc.setVisible(true);

        dc.setAlwaysOnTop(true);

        dc.t.start();
        while (dc.isValid()) {
            dc.reset();
            Thread.sleep(500);
        }
    }

}