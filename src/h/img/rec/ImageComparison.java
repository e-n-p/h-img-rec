package h.img.rec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author nick
 */
class ImageComparison {

    private static ImageComparison instance;

    private BufferedImage targetImg;
    private BufferedImage stored;

    private int storedScore;
    private int h;
    private int w;


    private ImageComparison(){}

    static ImageComparison getInstance(){
        if( instance == null){
            instance = new ImageComparison();
        }
        return instance;
    }

    void setStoredScore(int storedScore) { this.storedScore = storedScore; }

    int getH() { return h; }
    int getW() { return w; }
    int getStoredScore() { return storedScore; }
    BufferedImage getTargetImg(){ return targetImg; }

    void setTargetImage(BufferedImage TargetImg) {
        this.targetImg = TargetImg;
        this.h = targetImg.getHeight();
        this.w = targetImg.getWidth();
    }

    void setStored(BufferedImage newToStore) {
        BufferedImage storing = new BufferedImage(newToStore.getWidth(), newToStore.getHeight(), newToStore.getType());
        Graphics g = storing.getGraphics();
        g.drawImage(newToStore, 0, 0, null);
        g.dispose();
        this.stored = storing;
    }

    BufferedImage getStored() {
        BufferedImage storeCopy = new BufferedImage(stored.getWidth(), stored.getHeight(), stored.getType());
        Graphics g = storeCopy.getGraphics();
        g.drawImage(stored, 0, 0, null);
        g.dispose();

        return storeCopy;
    }

    int comparisonWhole(BufferedImage inputImage) {
        int diff = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Color aColor = new Color(inputImage.getRGB(j, i));
                Color bColor = new Color(this.targetImg.getRGB(j, i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
                diff += redDiff + blueDiff + greenDiff;
            }
        }
        return diff;
    }

}
//    double comparisonRect(BufferedImage inputImage, int[] pos) {
//        int highW, lowW, highH, lowH;
//        if (pos[0] < pos[2]) {
//            highW = pos[2];
//            lowW = pos[0];
//        } else {
//            highW = pos[0];
//            lowW = pos[2];
//        }
//        if (pos[1] < pos[3]) {
//            highH = pos[3];
//            lowH = pos[1];
//        } else {
//            highH = pos[1];
//            lowH = pos[3];
//        }
//        long diff = 0;
//        //System.out.println("lowH: "+ lowH+" highH: "+highH);
//        //System.out.println("lowW: "+ lowW+" highW: "+highW);
//        for (int i = lowH; i <= highH; i++) {
//            for (int j = lowW; j <= highW; j++) {
//                Color aColor = new Color(inputImage.getRGB(j, i));
//                Color bColor = new Color(this.targetImg.getRGB(j, i));
//                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
//                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
//                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
//                diff += redDiff + blueDiff + greenDiff;
//            }
//        }
//        return diff;
//    }
