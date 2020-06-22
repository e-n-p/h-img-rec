package h.img.rec;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static javafx.application.Platform.exit;

/**
 * @author nick - 15/06/20
 --//TODO
 --add more heuristics, PSO, annealing?
 --add pixelation functions
 --automate image selection / total configurations as input file?
 --
 */
public class HImgRec{
    
    public static void main(String[] args) throws IOException {
/*
        flowers.jpg
        blackCircle.jpg
        blackSquare.jpg
        cat.jpg
        coloredRectangles.jpg
        20x20b&w.jpg
        String filePath = "D:/Documents/workspace/h-img-rec/res/input/";
*/
        String filePath = "/home/nick/Projects/h-img-rec/res/input/";
        String image = "coloredRectangles";
        String fileType = ".jpg";
        String path = filePath + image + fileType;

        ImageControl imgCtrl = new ImageControl();
        BufferedImage targetImage = imgCtrl.makeImage(path);
        Utilities util = new Utilities();
        EditImage edit = EditImage.getInstance();
        int w = imgCtrl.getW();
        int h = imgCtrl.getH();
        int iter = 1000000;
        long processTime, completionTime;

        BufferedImage outputImg = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
        edit.setBackGround(outputImg);
        imgCtrl.setStored(outputImg);
        imgCtrl.setStoredScore(Integer.MAX_VALUE);
        Set<Color> colors = edit.readColour(h,w,targetImage);
        edit.setStyle(EditImage.drawStyle.LINE);
        processTime = System.currentTimeMillis();
        Logging logWrite = new Logging(image, edit.getStyle().toString(),String.valueOf(processTime) ,2);
        logWrite.logImageDetails(w,h,colors.size());

        for( int i = 1; i<=iter; i++ ){

            edit.draw(outputImg, colors);
            int newAttempt = imgCtrl.comparisonWhole(outputImg);
            int storedScore = imgCtrl.getStoredScore();

            if(storedScore < 0.1){
                logWrite.logCompletion(i);
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
                logWrite.logLoopData(i,storedScore);
                util.saveImg(outputImg, "res/out/output" + i + ".jpg");
            }
        }
        completionTime = System.currentTimeMillis() - processTime;
        logWrite.logImageDetails(w,h,colors.size());
        logWrite.logTimeInfo(completionTime);

//        util.displayImage(outputImg);
    }
}