package h.img.rec;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author nick - 09/06/20
 --//TODO
 --move code to proper methods/classes/locations
 --add heuristics to control drawing
 --details of image
 --add pixelation functions
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        //set image to edit with full file path
        //String path = "D:/Documents/workspace/h-img-rec/res/drawingB.png";
        String path = "/home/nick/Projects/h-img-rec/res/20x20b&w.jpg";

        //create bufferedImage type from path
        imageControl imgCtrl = new imageControl();
        BufferedImage targetImage = imgCtrl.makeImage(path);
        int h = imgCtrl.getH();
        int w = imgCtrl.getW();

        //display in JFrame given image
//        imgCtrl.generate(targetImage);

        //generate blank canvas with same dimensions
        BufferedImage outputImg = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
        setBackGround(outputImg);
        imgCtrl.setStored(outputImg);
        editImage edit = new editImage(targetImage);
        Set<Color> colors = edit.readColour();

        for( int i = 0; i<100000; i++ ){

            Graphics2D graphics = outputImg.createGraphics();
            Color rngColor = getRNGColor(colors);
            graphics.setColor(rngColor);

            int[] positions = lengthControl(h,w);
            // x1, y1      x2 y2
            graphics.drawLine(positions[0], positions[1], positions[2], positions[3]);
            graphics.dispose();

            double newAttempt = imgCtrl.comparisonWhole(outputImg);
            double storedScore = imgCtrl.comparisonWhole(imgCtrl.getStored());
//            saveImg(outputImg, "res/out/output" + i + "A.jpg");
//            saveImg(imgCtrl.getStored(), "res/out/output" + i + "B.jpg");
            System.out.println("New attempt score:" + newAttempt + " stored canvas score: " + storedScore);
            if(storedScore < 0.5){
                System.out.println("*******************************************");
                System.out.println("*Program completed in " + i + " iterations*");
                System.out.println("*******************************************");
                break;
            }else if(newAttempt > storedScore){
                outputImg = imgCtrl.getStored();
            }else{
                imgCtrl.setStored(outputImg);
            }
            saveImg(outputImg, "res/out/output" + i + ".jpg");
//            if(i % 100 == 0){ System.out.println("loop count = " + i); }
        }
//        imgCtrl.generate(outputImg);

    }
    
    private static Color getRNGColor(Set<Color> colours){
        Color[] palette = colours.toArray(new Color[0]);
        int randomPick = rng(palette.length);
        return palette[randomPick];
    }

    private static int rng(int max){
        return ThreadLocalRandom.current().nextInt(0,max);
    }

    private static void saveImg(BufferedImage anImg, String aPath){
        try {
            BufferedImage bi = anImg;
            File imageOut = new File(aPath);
            ImageIO.write(bi, "jpg", imageOut);
        } catch (IOException e) {
            System.out.println("Exception caught while trying to save image to file: " + e );
        }
    }

    private static void setBackGround(BufferedImage anImg){
        Graphics2D fill = anImg.createGraphics();
        fill.setColor(Color.WHITE);
        fill.fillRect(0, 0, anImg.getWidth()-1, anImg.getHeight()-1);
        fill.dispose();
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
