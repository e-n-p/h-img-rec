package h.img.rec;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author nick
 */
public class ImageComparison {

    private static ImageComparison instance;

    private BufferedImage targetImg;
    private BufferedImage stored;

    private int storedScore;
    private int h;
    private int w;


    private ImageComparison(){}

    public static ImageComparison getInstance(){
        if( instance == null){
            instance = new ImageComparison();
        }
        return instance;
    }

    void setStoredScore(int storedScore) { this.storedScore = storedScore; }
    int getStoredScore() { return storedScore; }

    int getH() { return h; }
    int getW() { return w; }

    public BufferedImage getTargetImg(){ return targetImg; }
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

    public int partComparison(Color aColor, int startY, int startX, int endY, int endX){
        int diff =0;
        int heightToCheck = boundsCheck(h, startY, (startY+endY));
        int widthToCheck = boundsCheck(w, startX, (startX+endX));
        for( int i = startY; i < heightToCheck ; i++ ){
            for( int j = startX; j < widthToCheck ; j++ ){
                Color bColor = new Color(targetImg.getRGB(j, i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
                diff += redDiff + blueDiff + greenDiff;
            }
        }
        return diff;
    }

    private int boundsCheck(int maxDimension, int dimension , int toRange){
        return ( toRange > maxDimension ? maxDimension : toRange);
    }

}
