package h.img.rec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author nick
 */
class ImageControl {

    private BufferedImage targetImg;
    private BufferedImage stored;


    private double storedScore;
    private int h;
    private int w;

    void setStoredScore(double storedScore) { this.storedScore = storedScore; }

    int getH() { return h; }
    int getW() { return w; }
    double getStoredScore() { return storedScore; }
    BufferedImage getTargetImg(){ return targetImg; }

    //TODO -separate bufferedImg constructor to util and move rest to constructor?
    BufferedImage makeImage(String path) throws IOException{
        File file = new File(path);
        this.targetImg = ImageIO.read(file);
        this.h = targetImg.getHeight();
        this.w = targetImg.getWidth();
        return targetImg;
    }

    void setStored(BufferedImage newToStore) {
        BufferedImage storing = new BufferedImage(newToStore.getWidth(), newToStore.getHeight(), newToStore.getType());
        Graphics g = storing.getGraphics();
        g.drawImage(newToStore, 0, 0, null);
        g.dispose();
        this.stored = storing;
    }

    BufferedImage getStored(){
        BufferedImage storeCopy = new BufferedImage(stored.getWidth(), stored.getHeight(), stored.getType());
        Graphics g = storeCopy.getGraphics();
        g.drawImage(stored, 0, 0, null);
        g.dispose();

        return storeCopy;
    }

    double comparisonWhole(BufferedImage inputImage){
        long diff=0;
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                Color aColor = new Color(inputImage.getRGB(j,i));
                Color bColor = new Color(this.targetImg.getRGB(j,i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
                diff += (redDiff+blueDiff+greenDiff);
            }
        }
        return diff;
    }
}


/*
only check rectangled area of change
 for(int i=lowH;i<=highH;i++){
            for(int j=lowW;j<=highW;j++){
check entire picture
 for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){

*/

//    //T/F is A more like targetImg
//    public double comparisonRect(BufferedImage A,BufferedImage B, int[] pos){
//        int highW,lowW,highH,lowH;
//        if(pos[0] < pos[2]){
//            highW = pos[2];
//            lowW = pos[0];
//        }else{
//            highW = pos[0];
//            lowW = pos[2];
//        }
//        if(pos[1] < pos[3]){
//            highH = pos[3];
//            lowH = pos[1];
//        }else{
//            highH = pos[1];
//            lowH = pos[3];
//        }
//        long diff=0;
//        //System.out.println("lowH: "+ lowH+" highH: "+highH);
//        //System.out.println("lowW: "+ lowW+" highW: "+highW);
//        for(int i=lowH;i<=highH;i++){
//            for(int j=lowW;j<=highW;j++){
//                Color aColor = new Color(A.getRGB(j,i));
//                Color bColor = new Color(B.getRGB(j,i));
//                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
//                //System.out.println("comparisonRect red val"+aColor.getRed());
//                //System.out.println("targetImg red val"+bColor.getRed());
//                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
//                //System.out.println("comparisonRect blue val"+aColor.getBlue());
//                //System.out.println("targetImg blue val"+bColor.getBlue());
//                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
//                //System.out.println("comparisonRect green val"+aColor.getGreen());
//                //System.out.println("targetImg green val"+bColor.getGreen());
//                diff =+ redDiff+blueDiff+greenDiff;
//            }
//        }
//        return diff;
//    }
