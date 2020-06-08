package h.img.rec;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author nick
 loads image and displays file in Jframe - 23/10/17
 --To do
 --fix comparisonRect/image swapping
 --details of image
 --move code to proper methods/classes/locations
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        BufferedImage targetImage;
        //set image to edit with full file path
//        String path = "D:/Documents/workspace/h-img-rec/res/drawingB.png";
        String path = "/home/nick/Projects/h-img-rec/res/drawingCB.png";

        //create bufferedImage type from path
        imageControl imgCtrl = new imageControl();
        targetImage = imgCtrl.makeImage(path);
        int h = targetImage.getHeight(), w = targetImage.getWidth();
        int[] positions = new int[4];
        //display in JFrame given image
        imgCtrl.generate(targetImage);
        //generate blank canvas with same dimensions
        BufferedImage outputImg = new BufferedImage(w, h,targetImage.getType());
        editImage edit = new editImage(targetImage);
        Set<Integer> colours = edit.readColour();
        
        for( int i = 0; i<100; i++ ){
            Graphics2D graphics = outputImg.createGraphics();
            
            Color ranCol = colourSet(colours);
            graphics.setColor(ranCol);
            
            positions = lengthControl(h,w);
            // x1, y1      x2 y2
            graphics.drawLine(positions[0], positions[1], positions[2], positions[3]);
            graphics.dispose();    

            BufferedImage storedOld = imgCtrl.getStored();
            
            //double canvasScore = imgCtrl.comparisonRect(canvas,image,positions);
            //double storedScore = imgCtrl.comparisonRect(storedOld,image,positions);
            
            double newAttempt = imgCtrl.comparisonWhole(outputImg);
            double storedScore = imgCtrl.comparisonWhole(storedOld);
            
//            System.out.println("New score:"+newAttempt);
//            System.out.println("Old Score:"+storedScore);
            if(newAttempt < storedScore){
                outputImg = imgCtrl.getStored();
//                System.out.println("newAttempt worse");
            }else{
                imgCtrl.swapBetter(outputImg);
//                System.out.println("storedScore better");
            }
        }
        imgCtrl.generate(outputImg);
        
    }
    
    private static Color colourSet(Set<Integer> colours){
        Color[] palette = colours.toArray(new Color[0]);
        int randomPick = rng(palette.length);
        return palette[randomPick];
    }

    private static int rng(int max){
        return ThreadLocalRandom.current().nextInt(0,max);
    }
    
    private static int[] lengthControl(int h, int w){
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
