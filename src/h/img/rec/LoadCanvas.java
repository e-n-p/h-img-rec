
package h.img.rec;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import javax.swing.JFrame;

/**
 *
 * @author nick
 */
public class LoadCanvas {
     BufferedImage blank;
    public LoadCanvas(LoadImage image){
        
        Dimension size = new Dimension();
        size = image.getPreferredSize();
        
       
        blank = new BufferedImage((int)size.getWidth(), (int)size.getHeight(),TYPE_INT_ARGB);
        
    }
    
    public void generateCanvas(){
        JFrame f = new JFrame("Generated Canvas");
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        f.add(new LoadCanvas());
        f.pack();
        f.setVisible(true);
    }
    
    
    
}
