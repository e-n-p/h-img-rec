package h.img.rec;

import java.awt.Color;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Set;

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
        String image = "flowers";
        String fileType = ".jpg";
        String path = filePath + image + fileType;

        ImageComparison imgCtrl = ImageComparison.getInstance();
        Utilities util = new Utilities();
        BufferedImage targetImage = util.accessImg(path);
        EditImage edit = EditImage.getInstance();
        imgCtrl.setTargetImage(targetImage);
        int w = imgCtrl.getW();
        int h = imgCtrl.getH();
        int iter = 100000;
        long processTime, completionTime;

        BufferedImage outputImg = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
        edit.setBackGround(outputImg);
        imgCtrl.setStored(outputImg);
        imgCtrl.setStoredScore(Integer.MAX_VALUE);
        Set<Color> colors = edit.readColour(h,w,targetImage);
        edit.setStyle(EditImage.drawStyle.THICK_LINE);
        processTime = System.currentTimeMillis();
        Logging logWrite = new Logging(image, edit.getStyle().toString(),String.valueOf(processTime) ,2);
        logWrite.logImageDetails(w,h,colors.size());

        /////
        GeneticAlgorithm ga = new GeneticAlgorithm(10,w,h,0,0,10);
        /////

        for( int i = 1; i<=iter; i++ ){

            edit.drawFromInput(outputImg, ga.run());

            int newAttempt = imgCtrl.comparisonWhole(outputImg);

//            edit.draw(outputImg, colors);
//            int newAttempt = imgCtrl.comparisonWhole(outputImg);
//            int storedScore = imgCtrl.getStoredScore();
//
//            if(storedScore < 0.1){
//                logWrite.logCompletion(i);
//                imgCtrl.setStored(outputImg);
//                imgCtrl.setStoredScore(newAttempt);
//                break;
//            }else if(newAttempt > storedScore){
//                outputImg = imgCtrl.getStored();
//            }else{
//                imgCtrl.setStored(outputImg);
//                imgCtrl.setStoredScore(newAttempt);
//            }
            if(i % 100 == 0){
                logWrite.logLoopData(i,newAttempt);
                util.saveImg(outputImg, "res/out/output" + i + ".jpg");
            }

        }
        completionTime = System.currentTimeMillis() - processTime;
        logWrite.logImageDetails(w,h,colors.size());
        logWrite.logTimeInfo(completionTime);

//        util.displayImage(outputImg);
    }
}