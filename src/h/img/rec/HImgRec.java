package h.img.rec;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static javafx.application.Platform.exit;

/**
 *
 * @author nick - 09/06/20
 --//TODO
 --add heuristics to control drawing
 --details of image
 --add pixelation functions
 --automate image selection
 --
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        //set image to edit with full file path
/*
        flowers.jpg
        blackCircle.jpg
        blackSquare.jpg
        cat.jpg
        coloredRectangles.jpg
        20x20b&w.jpg
        String path = "/home/nick/Projects/h-img-rec/res/input/flowers.jpg";
*/
        String path = "D:/Documents/workspace/h-img-rec/res/input/myFace.jpg";

        //create bufferedImage type from path
        ImageControl imgCtrl = new ImageControl();
        BufferedImage targetImage = imgCtrl.makeImage(path);
        Utilities util = new Utilities();
        EditImage edit = new EditImage(util);
        int w = imgCtrl.getW();
        int h = imgCtrl.getH();
        int iter = 100000;
        long processTime, completionTime;

        //displayImage blank canvas with same dimensions
        BufferedImage outputImg = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
        edit.setBackGround(outputImg);
        imgCtrl.setStored(outputImg);
        imgCtrl.setStoredScore(Double.MAX_VALUE);
        Set<Color> colors = edit.readColour(h,w,targetImage);
        System.out.println("Input image details: ");
        System.out.println("Width: " + w + " Height: " + h);
        System.out.println("There are " + colors.size() + " colors");
        processTime = System.currentTimeMillis();

        for( int i = 1; i<=iter; i++ ){

            edit.draw(outputImg, EditImage.drawStyle.THICK_LINE, colors);

            double newAttempt = imgCtrl.comparisonWhole(outputImg);
            double storedScore = imgCtrl.getStoredScore();
            //util.saveImg(outputImg, "res/out/output" + i + "A.jpg");
            //util.saveImg(imgCtrl.getStored(), "res/out/output" + i + "B.jpg");

            if(storedScore < 0.1){
                System.out.println();
                System.out.println("*******************************************");
                System.out.println("*  Program completed in " + i + " iterations   *");
                System.out.println("*******************************************");
                imgCtrl.setStored(outputImg);
                imgCtrl.setStoredScore(newAttempt);
                break;
            }else if(newAttempt > storedScore){
                outputImg = imgCtrl.getStored();
            }else{
                imgCtrl.setStored(outputImg);
                imgCtrl.setStoredScore(newAttempt);
            }
            if(i % 100 == 0){
                System.out.println("loop count: " + i);
                System.out.println("Stored canvas score: " + storedScore);
                util.saveImg(outputImg, "res/out/output" + i + ".jpg");
            }
        }
        completionTime = System.currentTimeMillis() - processTime;
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(completionTime),
                TimeUnit.MILLISECONDS.toMinutes(completionTime) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(completionTime) % TimeUnit.MINUTES.toSeconds(1));
        System.out.println("Processing time taken: " + hms);
        System.out.println("Input image details: ");
        System.out.println("Width: " + w + " Height: " + h);
        System.out.println("There are " + colors.size() + " colors");

        util.displayImage(outputImg);
    }
}