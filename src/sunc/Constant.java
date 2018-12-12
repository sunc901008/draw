package sunc;

import java.awt.*;

/**
 * creator: sunc date: 2018/12/11 description:
 */
class Constant {

    private static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    static int WINDOW_WIDTH = dimension.width;
    static int WINDOW_HEIGHT = dimension.height;

    static int center_x = WINDOW_WIDTH / 2;
    static int center_y = WINDOW_HEIGHT / 2;

    static int stop = 2;

    static final Long millSeconds = 1000L;

    static final String imagePath = System.getProperty("user.dir") + "/source/a3.png";
    static final String imagePath_gif = System.getProperty("user.dir") + "/source/c.gif";
}
