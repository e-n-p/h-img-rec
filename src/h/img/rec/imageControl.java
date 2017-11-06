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
    public int comparison(BufferedImage A){
        int score=0;
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                Color aColor = new Color(A.getRGB(j,i));
                Color bColor = new Color(image.getRGB(j,i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
                
                if(redDiff < 30 || blueDiff < 30 || greenDiff < 30){
                    score++;
                }
            }
        }
        return score;
    }
}
