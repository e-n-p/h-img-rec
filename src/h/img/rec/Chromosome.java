package h.img.rec;

import java.awt.*;
import java.util.ArrayList;

class Chromosome implements Comparable<Chromosome>{

    //basic eles
    Color color;
    int x;
    int y;

    Color getColor() { return color; }

    void setColor(Color color) { this.color = color; }

    int getX() { return x; }

    void setX(int x){ this.x = x; }

    int getY(){ return y; }

    void setY(int y) { this.y = y; }

    Chromosome(){ }

    Chromosome(ArrayList<String> constructor){

        String[] rgb = constructor.get(0).split(",");
        this.x = Integer.valueOf(constructor.get(1));
        this.y = Integer.valueOf(constructor.get(2));
        this.color = new Color(Integer.valueOf(rgb[0]),Integer.valueOf(rgb[0]),Integer.valueOf(rgb[0]));
    }

    Chromosome(int maxX, int maxY) {
        Utilities util = new Utilities();
        this.x = util.rng(maxX);
        this.y = util.rng(maxY);
        this.color = new Color(util.rng(255),util.rng(255),util.rng(255));
//        print();
    }

    int fitness() {
        return 1;
    }

    ArrayList<String> toArray(){
        ArrayList<String> chromosomeRepresentation = new ArrayList<>();
        String delimitedColor = this.color.getRed() + "," + this.color.getGreen() + "," + this.color.getBlue();
        chromosomeRepresentation.add(delimitedColor);
        chromosomeRepresentation.add(String.valueOf(this.x));
        chromosomeRepresentation.add(String.valueOf(this.y));

        return chromosomeRepresentation;
    }

    void print(){
        System.out.println("this chromosome = " + this.x + " " + this.y + " " +
                            this.color.getRed() + "," + this.color.getBlue() + "," + this.color.getGreen() );
    }

    @Override
    public int compareTo(Chromosome chromo) {
        return (this.fitness() < chromo.fitness() ? -1 : (this.fitness() == chromo.fitness() ? 0 : 1));
    }


    //    int getFit(){
//        EditImage edit = EditImage.getInstance();
//        int result = -1;
//        switch (edit.getStyle()) {
//            case LINE:
//                result = lineFitness();
//                break;
//            case THICK_LINE:
//                //have dimensions of change so can directly check
//                result = thickLineFitness();
//                break;
//            case CIRCLE:
//                break;
//            case ARC:
//                break;
//        }
//        return result;
//    }

//    private int thickLineFitness(){
//        int diff = 0;
//        ImageComparison imgC =  ImageComparison.getInstance();
//
//        Color aColor = color;
//        for(int i=this.x; i < (this.x+width) ; i++){
//            for(int j=this.y; j < (this.y+height) ; j++){
//                Color bColor = new Color(imgC.getTargetImg().getRGB(j, i));
//                int redDiff = Math.abs(aColor.getRed() - bColor.getRed());
//                int blueDiff = Math.abs(aColor.getBlue() - bColor.getBlue());
//                int greenDiff = Math.abs(aColor.getGreen() - bColor.getGreen());
//                diff += redDiff + blueDiff + greenDiff;
//            }
//        }
//        return diff;
//    }

//    private int lineFitness(){
//        //Bresenham's algorithm
//
//
//
//        return 1;
//    }

}