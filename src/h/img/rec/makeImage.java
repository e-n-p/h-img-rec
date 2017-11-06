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
        this.image = ImageIO.read(file);
        System.out.print("img width:"+image.getWidth());
        System.out.print(" img height:"+image.getHeight()+ "    ");
        return image;
    }
    
    public BufferedImage makeCanvas() throws IOException{
        BufferedImage blank;
        blank = new BufferedImage((int)image.getWidth(), (int)image.getHeight(),TYPE_INT_ARGB); 
        return blank;
    }
    
    public void generate(BufferedImage toDisplay) throws IOException{
        
        JLabel label1 = new JLabel(new ImageIcon(toDisplay));
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label1);
        f.pack();
        f.setLocation(200,200);
        f.setVisible(true);
        
    }
}
