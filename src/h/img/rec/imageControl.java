package h.img.rec;
import java.awt.Color;
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
public class imageControl {
    BufferedImage image;
    BufferedImage store;
    int h, w;
    public BufferedImage makeImage(String path) throws IOException{
        File file = new File(path);
        this.image = ImageIO.read(file);
        this.h = image.getHeight();
        this.w = image.getWidth();
        this.store = new BufferedImage(w, h,TYPE_INT_ARGB); 
        return image;
    }
    
    public void swapBetter(BufferedImage betterCopy){
        this.store = betterCopy;
    }
    public BufferedImage getStore(){
        return store;
    }
    public BufferedImage getImage(){
        return image;
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
    //T/F is A more like image
    public double comparison(BufferedImage A, int[] pos){
        int highH=0, lowH=0, highW=0, lowW=0;
        
        if(pos[0] < pos[2]){
            highW = pos[2];
            lowW = pos[0];
        }else{ 
            highW = pos[0];
            lowW = pos[2];
        }
        if(pos[1] < pos[3]){
            highH = pos[3];
            lowH = pos[1];
        }else{ 
            highH = pos[1];
            lowH = pos[3];
        }
        long diff=0;
        for(int i=lowH;i<highH;i++){
            for(int j=lowW;j<highW;j++){
                Color aColor = new Color(A.getRGB(j,i));
                Color bColor = new Color(image.getRGB(j,i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
                diff =+ redDiff+blueDiff+greenDiff;
            }
        }
        long maxDiff = 3L * 255 * w * h;
        return 100.0 * diff/maxDiff;
    }
}
