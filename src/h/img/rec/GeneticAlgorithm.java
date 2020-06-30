package h.img.rec;

import java.awt.*;
import java.util.ArrayList;

public class GeneticAlgorithm {

    private int popSize;
    private double muteRate;
    private double crossOverRate;
    private int maxX;
    private int maxY;
    private int generations;
    private ArrayList<Chromosome> population = new ArrayList<>();

    //set values
    GeneticAlgorithm(int popSize, int x, int y, double muteRate, double crossOverRate, int iters){
        this.popSize = popSize;
        this.generations = iters;
        this.maxX = x;
        this.maxY = y;
        this.muteRate = muteRate;
        this.crossOverRate = crossOverRate;
    }

    //main for class
    //change return type
    void run(){
        initPopulation();
        for(int i=0;i<generations;i++){
            crossover();
            mutate();
            cull();
        }
        //sort ArrayList by fitness
        //return best -- top %?
    }

    //create population
    private void initPopulation(){
        EditImage edit = EditImage.getInstance();
        population.clear();
        for(int i=0;i<popSize;i++){
            population.add(new Chromosome(maxX, maxY, edit.getStyle()));
        }
    }

    //get Avgerage fitness? totally necessary?
    // int/double computeAVGFitness(){ }

    //breeding
    private void onePointCross(){

    }

    private void uniformCross(){

    }

    private void crossover(){
        Utilities util = new Utilities();
        for(int i=0; i < (popSize/2); i++ ){
            Chromosome p0 = population.get(i);
            Chromosome p1 = population.get(i+1);
//            Chromosome c0 = new Chromosome();
//            Chromosome c1 = new Chromosome();
            //TODO move to Chromosome as method
            ArrayList<String> parent0 = new ArrayList<>();
            ArrayList<String> parent1 = new ArrayList<>();
            ArrayList<String> child = new ArrayList<>();
            String delimitedColor0 = p0.getColor().getRed() + "," + p0.getColor().getGreen() + "," + p0.getColor().getBlue();
            String delimitedColor1 = p1.getColor().getRed() + "," + p1.getColor().getGreen() + "," + p1.getColor().getBlue();
            parent0.add(delimitedColor0);
            parent0.add(Integer.toString(p0.getX()));
            parent0.add(Integer.toString(p0.getY()));

            parent1.add(delimitedColor1);
            parent1.add(Integer.toString(p1.getX()));
            parent1.add(Integer.toString(p1.getY()));

            for(int j=0; j<3; j++){

                if( util.rng(1) == 0 ){
                    child.add(parent0.get(i));

                }else{
                    child.add(parent1.get(i));
                }

            }
            population.add(new Chromosome(child));

        }

    }

    //mutation
    private void mutate(){

    }


    //culling popSize
    private void cull(){

    }

        class Chromosome {

        //how to manage this?
        //we have a colour to pick -- maybe let it choose whatever colours? see how they run
        //and a position x,y at the very least

            //basic eles
            private Color color;
            private int x;
            private int y;

            //line eles
            private int x2;
            private int y2;

            //thickLine eles
            private int width;
            private int height;

            Color getColor() { return color; }

            void setColor(Color color) { this.color = color; }

            int getX() { return x; }

            void setX(int x){ this.x = x; }

            int getY(){ return y; }

            void setY(int y) { this.y = y; }

            Chromosome(){ }

            Chromosome(ArrayList<String> constructor){


                this.x = Integer.valueOf(constructor.get(1));
                this.y = Integer.valueOf(constructor.get(2));
                //this.color = new Color();

            }

            Chromosome(int maxX, int maxY, EditImage.drawStyle style) {
                Utilities util = new Utilities();
                this.x = util.rng(maxX);
                this.y = util.rng(maxY);
                this.color = new Color(util.rng(255),util.rng(255),util.rng(255));
                switch (style) {
                    case LINE:
                        this.x2 = util.rng(maxX);
                        this.y2 = util.rng(maxY);
                        break;
                    case THICK_LINE:
                        this.width = 3;
                        this.height = 60;
                        break;
                    case CIRCLE:
                        break;
                    case ARC:
                        break;
                }

            }

            int getFit(){
                EditImage edit = EditImage.getInstance();
                int result = -1;
                switch (edit.getStyle()) {
                    case LINE:
                        result = lineFitness();
                        break;
                    case THICK_LINE:
                        //have dimensions of change so can directly check
                        result = thickLineFitness();
                        break;
                    case CIRCLE:
                        break;
                    case ARC:
                        break;
                }
                return result;
            }

            private int thickLineFitness(){
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

            private int lineFitness(){
             //Bresenham's algorithm



                return 1;
            }

        }
}
