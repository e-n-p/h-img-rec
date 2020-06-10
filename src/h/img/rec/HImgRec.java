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
 --automate image selection
 --
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
        //set image to edit with full file path
        //String path = "D:/Documents/workspace/h-img-rec/res/drawingB.png";
/*      flowers.jpg
        blackCircle.jpg
        blackSquare.jpg
        cat.jpg
        coloredRectangles.jpg
*/
        String path = "/home/nick/Projects/h-img-rec/res/input/blackSquare.jpg";

        //create bufferedImage type from path
        ImageControl imgCtrl = new ImageControl();
        BufferedImage targetImage = imgCtrl.makeImage(path);
        Utilities util = new Utilities();
        EditImage edit = new EditImage(util);
        int w = imgCtrl.getW();
        int h = imgCtrl.getH();

        //displayImage blank canvas with same dimensions
        BufferedImage outputImg = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
        edit.setBackGround(outputImg);
        imgCtrl.setStored(outputImg);
        Set<Color> colors = edit.readColour(h,w,targetImage);

        for( int i = 0; i<300; i++ ){

            edit.draw(outputImg, EditImage.drawStyle.CIRCLE, colors);

            double newAttempt = imgCtrl.comparisonWhole(outputImg);
            //TODO
            //add function to save storedScore to imgCtrl so doesn't need to be re-computed each time
            double storedScore = imgCtrl.comparisonWhole(imgCtrl.getStored());
            //util.saveImg(outputImg, "res/out/output" + i + "A.jpg");
            //util.saveImg(imgCtrl.getStored(), "res/out/output" + i + "B.jpg");
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
            if(i % 100 == 0){
                System.out.println("loop count = " + i);
                System.out.println("New attempt score:" + newAttempt + " stored canvas score: " + storedScore);
                util.saveImg(outputImg, "res/out/output" + i + ".jpg");
            }
        }
        util.displayImage(outputImg);
    }
}