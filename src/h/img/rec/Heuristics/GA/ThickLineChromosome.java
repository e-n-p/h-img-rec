package h.img.rec.Heuristics.GA;

import h.img.rec.ImageComparison;

import java.awt.*;
import java.util.ArrayList;

public class ThickLineChromosome extends Chromosome {


    private int width;
    private int height;

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getFeatures(){ return 5; }

    ThickLineChromosome(ArrayList<String> constructor) {
        super(constructor);
        this.width = Integer.valueOf(constructor.get(3));
        this.height = Integer.valueOf(constructor.get(4));
    }

    ThickLineChromosome(int maxX, int maxY, int height, int width) {
        super(maxX, maxY);
        this.height = height;
        this.width = width;
    }

    //TODO move method to imageComparison
    int fitness(){
        ImageComparison imgC =  ImageComparison.getInstance();
        return imgC.partComparison(color, this.y, this.x, this.height, this.width);
    }

    @Override
    ArrayList<String> toArray() {
        ArrayList<String> chromosomeRepresentation = super.toArray();
        chromosomeRepresentation.add(String.valueOf(this.width));
        chromosomeRepresentation.add(String.valueOf(this.height));
        return chromosomeRepresentation;
    }

    void print() {
        System.out.println("this chromosome = x co-ord" +
                            this.x + ", y co-ord " + this.y +
                            " RGB values=" + this.color.getRed() + "," + this.color.getBlue() + "," + this.color.getGreen() +
                            " line thickness " + this.width + ", line height " + this.height);
    }
}
