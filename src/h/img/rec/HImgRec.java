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
 * --drawing on blank image
 * --way to compare images
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        BufferedImage image;
        //set image to edit with full file path
        String path = "/home/nick/NetBeansProjects/gitProjects/h-img-rec/res/flowers.jpg";
        

        //create bufferedImage type from path
        makeImage make = new makeImage();
        image = make.makeImage(path);
        //display in JFrame given image
        //make.generate(image);
        //generate blank canvas with same dimensions
        BufferedImage canvas = make.makeCanvas();
        
        Set<Integer> colours = new HashSet<Integer>();
        editImage edit = new editImage(image);
        colours = edit.readColour();
        
        Graphics2D graphics = canvas.createGraphics();
        
        for(int i =0;i<10000;i++){
        Color ranCol = colourSet(image, colours);
        graphics.setColor(ranCol);
        
        int x1 = rng(image.getWidth());
        int x2 = rng(image.getWidth());
        int y1 = rng(image.getHeight());
        int y2 = rng(image.getHeight());
        // x1, y1      x2 y2
        graphics.drawLine(x1, y1, x2, y2);
        }
        make.generate(canvas);
    }
    
    public static Color colourSet(BufferedImage i, Set<Integer> colours){
        
        Integer[] palette = colours.toArray(new Integer[colours.size()]);
        int randomPick = rng(palette.length);
        //randomise palette grab w/ method below
        int blue = palette[randomPick] & 0xff;
        int green = (palette[randomPick] & 0xff00) >> 8;
        int red = (palette[randomPick] & 0xff0000) >> 16;
        Color newCol = new Color(red, green, blue);
        
        return newCol;
    }
    public static int rng(int max){
        int ran = ThreadLocalRandom.current().nextInt(0,max + 1);
        return ran;
    }
    
}
