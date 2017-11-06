package h.img.rec;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author nick
 * loads image and displays file in Jframe - 23/10/17
 * --To add
 * --way to compare images
 * --details of image
 * --move code to proper methods/classes/locations
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        BufferedImage image;
        //set image to edit with full file path
        String path = "/home/nick/NetBeansProjects/gitProjects/h-img-rec/res/flowers.jpg";

        //create bufferedImage type from path
        imageControl imgCtrl = new imageControl();
        image = imgCtrl.makeImage(path);
        int h = image.getHeight(), w = image.getWidth();
        //display in JFrame given image
        imgCtrl.generate(image);
        //generate blank canvas with same dimensions
        BufferedImage canvas = imgCtrl.getStore();
        BufferedImage storeOld = imgCtrl.getStore();
        Set<Integer> colours = new HashSet<Integer>();
        editImage edit = new editImage(image);
        colours = edit.readColour();
        
        Graphics2D graphics = canvas.createGraphics();
        
        for(int i =0;i<1000;i++){
            Color ranCol = colourSet(colours);
            graphics.setColor(ranCol);
            // x1, y1      x2 y2
            graphics.drawLine(rng(w), rng(h), rng(w), rng(h));
            storeOld = imgCtrl.getStore();

            int canvasScore = imgCtrl.comparison(canvas);
            int storeScore = imgCtrl.comparison(storeOld);
            if(canvasScore > storeScore){
                //store gets stored
                imgCtrl.swapBetter(canvas);
            }else{
                //canvas becomes canvas
                canvas = imgCtrl.getStore();
            }
            
        }
        imgCtrl.generate(canvas);
    }
    
    public static Color colourSet(Set<Integer> colours){
        
        Integer[] palette = colours.toArray(new Integer[colours.size()]);
        Integer randomPick = rng(palette.length);
        Color newColour = new Color(palette[randomPick]);
        return newColour;
    }
    public static int rng(int max){
        int ran = ThreadLocalRandom.current().nextInt(0,max + 1);
        return ran;
    }

}
