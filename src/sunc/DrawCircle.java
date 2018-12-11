package sunc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class DrawCircle extends JWindow {

    private static final Boolean debug = false;
    private static int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private static int width = WINDOW_WIDTH;
    private static int height = WINDOW_HEIGHT;

    private static int center_width = WINDOW_WIDTH / 2;
    private static int center_height = WINDOW_HEIGHT / 2;

    private static int loc_width = 0;
    private static int loc_height = 0;

    double theta = 0.00;

    public DrawCircle() {
        super();
        initialize();//调用初始化方法
        if (debug) {
            width = WINDOW_WIDTH / 3;
            height = WINDOW_HEIGHT / 3;
            loc_width = center_width - WINDOW_WIDTH / 6;
            loc_height = center_height - WINDOW_HEIGHT / 6;
            loc_width = center_width - WINDOW_WIDTH;
            loc_height = center_height - WINDOW_HEIGHT;
        }
    }

    private Color color = new Color(0, 0, 0, 0.1f);

    class CustomWindowListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            dispose();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    //初始化方法
    private void initialize() {
        setSize(width, height);//设置窗体的大小
        System.out.println(String.format("width:%s,height:%s", width, height));
        setLocation(loc_width, loc_height);
        setBackground(color);
        setContentPane(new DrawPanel());
        addMouseListener(new CustomWindowListener());
    }

    private void reset(){
        setBackground(color);
    }

    class DrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            int r = 15;
            double x, y;

            for (int i = 0; i < 40; i++) {
                x = width / 2 - r * (28 * Math.pow(Math.sin(theta), 3));
                y = height / 2 - r * (20 * Math.cos(theta) - 6 * Math.cos(2 * theta) - 3 * Math.cos(3 * theta) - Math.cos(4 * theta));
                System.out.println(String.format("x:%s,y:%s", x, y));
                System.out.println(String.format("width:%s,height:%s", width, height));
                BufferedImage image;
                try {
                    image = ImageIO.read(new File(Constant.imagePath));
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                g.drawImage(image, (int) x, (int) y, null);
                theta += 1;
            }
        }

    }

    public void clearComponent() {
        Component[] components = this.getComponents();
        if (components.length > 0) {
            setContentPane(new DrawPanel());
        }
        if (components.length > 10) {
            this.remove(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DrawCircle dc = new DrawCircle();//初始化对象且调用构造方法

        dc.setVisible(true);//窗体可视化

        while (dc.isValid()) {
            Thread.sleep(3000);
            dc.reset();
            dc.getContentPane().repaint();
        }

    }

}