package h.img.rec;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    BufferedImage stored;
    int h, w;
    public BufferedImage makeImage(String path) throws IOException{
        File file = new File(path);
        this.image = ImageIO.read(file);
        this.h = image.getHeight();
        this.w = image.getWidth();
        this.stored = new BufferedImage(w, h,image.getType()); 
        return image;
    }
    
    public void swapBetter(BufferedImage betterCopy){
        BufferedImage b = new BufferedImage(betterCopy.getWidth(), betterCopy.getHeight(), betterCopy.getType());
        Graphics g = b.getGraphics();
        g.drawImage(betterCopy, 0, 0, null);
        g.dispose();        
        this.stored = b;
    }
    public BufferedImage getStored(){
        BufferedImage storeCopy = new BufferedImage(stored.getWidth(), stored.getHeight(), stored.getType());
        Graphics g = storeCopy.getGraphics();
        g.drawImage(stored, 0, 0, null);
        g.dispose();
        
        return storeCopy;
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
    public double comparisonRect(BufferedImage A,BufferedImage B, int[] pos){
        int highW,lowW,highH,lowH;
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
        //System.out.println("lowH: "+ lowH+" highH: "+highH);
        //System.out.println("lowW: "+ lowW+" highW: "+highW);
        for(int i=lowH;i<=highH;i++){
            for(int j=lowW;j<=highW;j++){
                Color aColor = new Color(A.getRGB(j,i));
                Color bColor = new Color(B.getRGB(j,i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
                //System.out.println("comparisonRect red val"+aColor.getRed());
                //System.out.println("image red val"+bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
                //System.out.println("comparisonRect blue val"+aColor.getBlue());
                //System.out.println("image blue val"+bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
                //System.out.println("comparisonRect green val"+aColor.getGreen());
                //System.out.println("image green val"+bColor.getGreen());
                diff =+ redDiff+blueDiff+greenDiff;
            }
        }
        return diff;
    }
    
    public double comparisonWhole(BufferedImage A,BufferedImage B, int[] pos){
        long diff=0;
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                Color aColor = new Color(A.getRGB(j,i));
                Color bColor = new Color(B.getRGB(j,i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
                //System.out.println("comparisonRect red val"+aColor.getRed());
                //System.out.println("image red val"+bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
                //System.out.println("comparisonRect blue val"+aColor.getBlue());
                //System.out.println("image blue val"+bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
                //System.out.println("comparisonRect green val"+aColor.getGreen());
                //System.out.println("image green val"+bColor.getGreen());
                diff =+ redDiff+blueDiff+greenDiff;
            }
        }
        return diff;
    }
}


/*
only check rectangled area of change
 for(int i=lowH;i<=highH;i++){
            for(int j=lowW;j<=highW;j++){
check entire picture
 for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){

*/