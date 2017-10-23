package h.img.rec;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class LoadImageApp extends Component {
          
    BufferedImage img;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public LoadImageApp() {
       try {
           img = ImageIO.read(new File("/home/nick/NetBeansProjects/h-img-rec/res/cat.jpg"));
       } catch (IOException e) {
           System.out.println("Failure");
       }

    }

    public Dimension getPreferredSize() {
        if (img == null) {
            System.out.println("Failure null");
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
}