package h.img.rec;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author nick
 */
class Utilities {

    //TODO - logging
    //     - reading parameter input file?

    void saveImg(BufferedImage anImg, String aPath){
        try {
            BufferedImage bi = anImg;
            File imageOut = new File(aPath);
            ImageIO.write(bi, "jpg", imageOut);
        } catch (IOException e) {
            System.out.println("Exception caught while trying to save image to file: " + e );
        }
    }

    int rng(int max){ return ThreadLocalRandom.current().nextInt(0,max); }

    void displayImage(BufferedImage toDisplay){
        JLabel label1 = new JLabel(new ImageIcon(toDisplay));
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label1);
        f.pack();
        f.setLocation(200,200);
        f.setVisible(true);
    }


}
