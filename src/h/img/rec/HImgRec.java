package h.img.rec;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Set;

/**
 *
 * @author nick - 09/06/20
 --//TODO
 --add heuristics to control drawing
 --details of image
 --add pixelation functions
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        //set image to edit with full file path
        //String path = "D:/Documents/workspace/h-img-rec/res/drawingB.png";
        String path = "/home/nick/Projects/h-img-rec/res/flowers.jpg";

        //create bufferedImage type from path
        imageControl imgCtrl = new imageControl();
        BufferedImage targetImage = imgCtrl.makeImage(path);
        editImage edit = new editImage();
        utilities util = new utilities();
        int h = imgCtrl.getH();
        int w = imgCtrl.getW();

        //displayImage blank canvas with same dimensions
        BufferedImage outputImg = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
        edit.setBackGround(outputImg);
        imgCtrl.setStored(outputImg);
        Set<Color> colors = edit.readColour(h,w,targetImage);

        for( int i = 0; i<1000; i++ ){

            Graphics2D graphics = outputImg.createGraphics();
            Color rngColor = edit.getRNGColor(colors, util);
            graphics.setColor(rngColor);

            int[] positions = edit.lengthControl(h,w, util);
            // x1, y1      x2 y2
            graphics.drawLine(positions[0], positions[1], positions[2], positions[3]);
            graphics.dispose();

            double newAttempt = imgCtrl.comparisonWhole(outputImg);
            double storedScore = imgCtrl.comparisonWhole(imgCtrl.getStored());
//            util.saveImg(outputImg, "res/out/output" + i + "A.jpg");
//            util.saveImg(imgCtrl.getStored(), "res/out/output" + i + "B.jpg");
            System.out.println("New attempt score:" + newAttempt + " stored canvas score: " + storedScore);
            if(storedScore < 0.1){
                System.out.println("*******************************************");
                System.out.println("*Program completed in " + i + " iterations*");
                System.out.println("*******************************************");
                break;
            }else if(newAttempt > storedScore){
                outputImg = imgCtrl.getStored();
            }else{
                imgCtrl.setStored(outputImg);
            }
            util.saveImg(outputImg, "res/out/output" + i + ".jpg");
//            if(i % 100 == 0){ System.out.println("loop count = " + i); }
        }
        util.displayImage(outputImg);
    }
}