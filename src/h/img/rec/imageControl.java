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
    private BufferedImage image;
    private BufferedImage stored;
    private int h;
    private int w;

    public int getH() {return h;}
    public int getW() {return w;}


    BufferedImage makeImage(String path) throws IOException{
        File file = new File(path);
        this.image = ImageIO.read(file);
        this.h = image.getHeight();
        this.w = image.getWidth();
        this.stored = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
        return image;
    }
    
    void swapBetter(BufferedImage betterCopy){
        BufferedImage b = new BufferedImage(betterCopy.getWidth(), betterCopy.getHeight(), betterCopy.getType());
        Graphics g = b.getGraphics();
        g.drawImage(betterCopy, 0, 0, null);
        g.dispose();        
        this.stored = b;
    }

    BufferedImage getStored(){
        BufferedImage storeCopy = new BufferedImage(stored.getWidth(), stored.getHeight(), stored.getType());
        Graphics g = storeCopy.getGraphics();
        g.drawImage(stored, 0, 0, null);
        g.dispose();
        
        return this.stored;
    }

    public BufferedImage getImage(){
        return image;
    }
    
    void generate(BufferedImage toDisplay){
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
    
    double comparisonWhole(BufferedImage inputImage){
        long diff=0;
//        System.out.println("----comparisonWhole----");
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                Color aColor = new Color(inputImage.getRGB(j,i));
                Color bColor = new Color(this.image.getRGB(j,i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
//                System.out.println("inputImage red val> "+aColor.getRed());
//                System.out.println("targetImage red val"+bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
//                System.out.println("inputImage blue val> "+aColor.getBlue());
//                System.out.println("targetImage blue val"+bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
//                System.out.println("inputImage green val> "+aColor.getGreen());
//                System.out.println("targetImage green val"+bColor.getGreen() + "\n");
                diff =+ redDiff+blueDiff+greenDiff;
            }
        }
//        System.out.println("----comparisonWhole----");
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