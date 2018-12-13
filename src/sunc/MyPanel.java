package sunc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * creator: sunc date: 2018/12/12 description:
 */
public class MyPanel extends JPanel {

    private double theta = 0.00;

    public void paintComponent(Graphics g) {

        int r = 15;
        double x, y;

        for (int i = 0; i < 70; i++) {
            x = (Constant.WINDOW_WIDTH >> 1) - r * (28 * Math.pow(Math.sin(theta), 3));
            y = (Constant.WINDOW_HEIGHT >> 1) - r * (20 * Math.cos(theta) - 6 * Math.cos(2 * theta) - 3 * Math.cos(3 * theta) - Math.cos(4 * theta));
            BufferedImage image;
            try {
                File file = new File(Constant.imagePath);
                image = ImageIO.read(file);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
                return;
            }
            g.drawImage(image, (int) x, (int) y, null);
            theta += Math.PI / 40;
//            try {
//                TimeUnit.MILLISECONDS.sleep(3);
//            } catch (InterruptedException ignored) {
//
//            }
        }
    }

}
