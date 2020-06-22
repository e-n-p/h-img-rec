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
    private ArrayList<Chromosome> population;

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
        population.clear();
        for(int i=0;i<popSize;i++){
            population.add(new Chromosome(maxX,maxY));
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
            private Color color;
            private int x;
            private int y;

            Chromosome(int maxX, int maxY){
                Utilities util = new Utilities();
                this.x = util.rng(maxX);
                this.y = util.rng(maxY);
                this.color = new Color(util.rng(255),util.rng(255),util.rng(255));
            }

            double getFit(){
                EditImage edit = EditImage.getInstance();
                switch (edit.getStyle()) {
                    case LINE:

                        break;
                    case THICK_LINE:
                        break;
                    case CIRCLE:
                        break;
                    case ARC:
                        break;
                }


                return 1.0;
            }

        }
}
