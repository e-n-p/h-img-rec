/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package h.img.rec;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * @author nick
 */
public class makeImage {
    BufferedImage image;
    public BufferedImage makeImage(String path) throws IOException{
        File file = new File(path);
        image = ImageIO.read(file);
        return image;
    }
    
    
    public void makeSubject() throws IOException{
        
        JLabel label0 = new JLabel(new ImageIcon(image));
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label0);
        f.pack();
        f.setLocation(200,200);
        f.setVisible(true);
    }
    public void makeCanvas() throws IOException{
         
        BufferedImage blank;
        blank = new BufferedImage((int)image.getWidth(), (int)image.getHeight(),TYPE_INT_ARGB); 

        JLabel label1 = new JLabel(new ImageIcon(blank));
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label1);
        f.pack();
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
