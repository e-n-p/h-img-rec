package h.img.rec;

import java.awt.*;
import java.util.ArrayList;

public class ThickLineChromosome extends Chromosome implements Comparable<ThickLineChromosome> {

//    private Color color;
//    private int x;
//    private int y;

    //thickLine eles
    private int width;
    private int height;

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }

//    ThickLineChromosome() {
//        super();
//    }

    ThickLineChromosome(ArrayList<String> constructor) {
        super(constructor);
    }

    ThickLineChromosome(int maxX, int maxY) {
        super(maxX, maxY);
    }

    public void setHeight(int height) { this.height = height; }

    int fitness(){
        int diff = 0;
        ImageComparison imgC =  ImageComparison.getInstance();

        Color aColor = color;
        for(int i=this.x; i < (this.x+width) ; i++){
            for(int j=this.y; j < (this.y+height) ; j++){
                Color bColor = new Color(imgC.getTargetImg().getRGB(j, i));
                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
                diff += redDiff + blueDiff + greenDiff;
            }
        }
        return diff;
    }

    @Override
    public int compareTo(ThickLineChromosome chromo) {
        return (this.fitness() < chromo.fitness() ? -1 : (this.fitness() == chromo.fitness() ? 0 : 1));
    }

}
