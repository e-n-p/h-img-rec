package h.img.rec;

import java.awt.*;
import java.util.ArrayList;

public class ThickLineChromosome extends Chromosome {

    private int width;
    private int height;

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }

    ThickLineChromosome(ArrayList<String> constructor) {
        super(constructor);
        this.width =3;
        this.height=60;
    }

    ThickLineChromosome(int maxX, int maxY) {
        super(maxX, maxY);
        this.width =3;
        this.height=60;
    }

    public void setHeight(int height) { this.height = height; }

    int fitness(){
        int diff = 0;
        ImageComparison imgC =  ImageComparison.getInstance();

        Color aColor = color;
        for(int i=this.y; i < (this.y+height) ; i++){
            for(int j=this.x; j < (this.x+width) ; j++){
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
    void print() {
        System.out.println("this chromosome = x co-ord" +
                            this.x + ", y co-ord " + this.y +
                            " RGB values=" + this.color.getRed() + "," + this.color.getBlue() + "," + this.color.getGreen() +
                            " line thickness " + this.width + ", line height " + this.height);

    }

//    @Override
//    public int compareTo(ThickLineChromosome chromo) {
//        return (this.fitness() < chromo.fitness() ? -1 : (this.fitness() == chromo.fitness() ? 0 : 1));
//    }

}
