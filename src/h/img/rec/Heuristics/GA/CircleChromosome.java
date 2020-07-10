package h.img.rec.Heuristics.GA;

import h.img.rec.ImageComparison;

import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class CircleChromosome extends Chromosome {


    private int width;
    private int height;

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getFeatures(){ return 5; }

    //["color[255,255,255]","x","y","width","height"]
    CircleChromosome(ArrayList<String> constructor){
        super(constructor);
        this.width = Integer.valueOf(constructor.get(3));
        this.height= Integer.valueOf(constructor.get(4));
//        print();
    }

    CircleChromosome(int maxX, int maxY, int height, int width){
        super(maxX,maxY);
        this.height = height;
        this.width = width;
//        print();
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

    @Override
    void print() {
        System.out.println("this chromosome = x co-ord " +
                            this.x + ", y co-ord " + this.y +
                            " RGB values=" + this.color.getRed() + "," +
                            this.color.getBlue() + "," + this.color.getGreen() +
                            " circle dimensions " + this.width + ", line height " + this.height);
    }
}
