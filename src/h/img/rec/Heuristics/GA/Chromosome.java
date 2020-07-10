package h.img.rec.Heuristics.GA;

import h.img.rec.Utilities;

import java.awt.*;
import java.util.ArrayList;

abstract public class Chromosome implements Comparable<Chromosome>{

    //basic eles
    Color color;
    int x;
    int y;

    public Color getColor() { return color; }
    void setColor(Color color) { this.color = color; }

    public int getX() { return x; }
    void setX(int x){ this.x = x; }

    public int getY(){ return y; }
    void setY(int y) { this.y = y; }

    abstract int getFeatures();

    //["color[255,255,255]","x","y"]
    Chromosome(ArrayList<String> constructor){
        String[] rgb = constructor.get(0).split(",");
        this.color = new Color(Integer.valueOf(rgb[0]),Integer.valueOf(rgb[1]),Integer.valueOf(rgb[2]));
        this.x = Integer.valueOf(constructor.get(1));
        this.y = Integer.valueOf(constructor.get(2));
    }

    Chromosome(int maxX, int maxY) {
        Utilities util = new Utilities();
        this.x = util.rng(maxX);
        this.y = util.rng(maxY);
        this.color = new Color(util.rng(255),util.rng(255),util.rng(255));
//        print();
    }

    abstract int fitness();

    ArrayList<String> toArray(){
        ArrayList<String> chromosomeRepresentation = new ArrayList<>();
        String delimitedColor = this.color.getRed() + "," + this.color.getGreen() + "," + this.color.getBlue();
        chromosomeRepresentation.add(delimitedColor);
        chromosomeRepresentation.add(String.valueOf(this.x));
        chromosomeRepresentation.add(String.valueOf(this.y));
        return chromosomeRepresentation;
    }

    abstract void print();

    @Override
    public int compareTo(Chromosome chromo) {
        return (this.fitness() < chromo.fitness() ? -1 : (this.fitness() == chromo.fitness() ? 0 : 1));
    }

//    private int lineFitness(){
//        //Bresenham's algorithm
//
//
//
//        return 1;
//    }

}