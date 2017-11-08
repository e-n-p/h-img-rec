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
 * --To do
 * --fix comparison/image swapping
 * --details of image
 * --move code to proper methods/classes/locations
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        BufferedImage image;
        //set image to edit with full file path
        String path = "/home/nick/NetBeansProjects/gitProjects/h-img-rec/res/drawing.png";

        //create bufferedImage type from path
        imageControl imgCtrl = new imageControl();
        image = imgCtrl.makeImage(path);
        int h = image.getHeight(), w = image.getWidth();
        int[] positions = new int[4];
        //display in JFrame given image
        imgCtrl.generate(image);
        //generate blank canvas with same dimensions
        BufferedImage canvas = imgCtrl.getStore();
        BufferedImage storeOld;
        Set<Integer> colours = new HashSet<>();
        editImage edit = new editImage(image);
        colours = edit.readColour();
        
        Graphics2D graphics = canvas.createGraphics();
        
        for(int i =0;i<5000;i++){
            Color ranCol = colourSet(colours);
            graphics.setColor(ranCol);
            
            positions = lengthControl(h,w);
            // x1, y1      x2 y2
            graphics.drawLine(positions[0], positions[1], positions[2], positions[3]);
            
            storeOld = imgCtrl.getStore();
            double canvasScore = imgCtrl.comparison(canvas, positions);
            double storeScore = imgCtrl.comparison(storeOld, positions);
            if(canvasScore < storeScore){
                //canvas gets stored
                imgCtrl.swapBetter(canvas);
            }else{
                //canvas becomes store
                canvas = imgCtrl.getStore();
            }
            
        }
        imgCtrl.generate(canvas);
        graphics.dispose();
    }
    
    public static Color colourSet(Set<Integer> colours){
        Integer[] palette = colours.toArray(new Integer[colours.size()]);
        Integer randomPick = rng(palette.length);
        Color newColour = new Color(palette[randomPick]);
        return newColour;
    }
    public static int rng(int max){
        int ran = ThreadLocalRandom.current().nextInt(0,max);
        return ran;
    }
    
    public static int[] lengthControl(int h, int w){
        double length = Double.MAX_VALUE;
        int[] pos = new int[4];
        while(length > w/4){
            pos[0] = rng(w);
            pos[1] = rng(h);
            pos[2] = rng(w);
            pos[3] = rng(h);
        length = Math.sqrt(((pos[2]-pos[0])*(pos[2]-pos[0]))+((pos[3]-pos[1])*(pos[3]-pos[1])));
        }
       return pos;
    }
    
    
}
