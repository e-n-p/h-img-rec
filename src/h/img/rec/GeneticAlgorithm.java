package h.img.rec;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GeneticAlgorithm {

    private int popSize;
    private double muteRate;
    private double crossOverRate;
    private int maxX;
    private int maxY;
    private int generations;
    private ArrayList<ThickLineChromosome> population = new ArrayList<>();

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
//            mutate();
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
            switch(edit.getStyle()){
                case LINE:
                    break;
                case THICK_LINE:
                    population.add(new ThickLineChromosome(maxX, maxY));
                    break;
                case ARC:
                    break;
                case CIRCLE:
                    break;
            }
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
            ArrayList<String> parent0 = p0.toArray();
            ArrayList<String> parent1 = p1.toArray();
            ArrayList<String> child = new ArrayList<>();

            for(int j=0; j<3; j++){

                if( util.rng(1) == 0 ){
                    child.add(parent0.get(i));

                }else{
                    child.add(parent1.get(i));
                }

            }
            population.add(new ThickLineChromosome(child));

        }

    }

    //mutation
    private void mutate(){

    }


    //culling popSize
    private void cull(){
        Collections.sort(population);
        while (population.size() > popSize){
            int newSize = population.size();
            population.remove(newSize-1);
        }

    }

}
