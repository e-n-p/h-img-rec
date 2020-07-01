package h.img.rec;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

class GeneticAlgorithm {
    //TODO
    //mutation rate
    //crossover rates and different implementations
    private int popSize;
    private int muteRate;
    private double crossOverRate;
    private int maxX;
    private int maxY;
    private int generations;
    private ArrayList<Chromosome> population = new ArrayList<>();

    //set values
    GeneticAlgorithm(int popSize, int x, int y, int muteRate, double crossOverRate, int iters){
        this.popSize = popSize;
        this.generations = iters;
        this.maxX = x;
        this.maxY = y;
        this.muteRate = muteRate;
        this.crossOverRate = crossOverRate;
    }

    ArrayList<Chromosome> run(){
        initPopulation();
        for(int i=0;i<generations;i++){
            crossover();
            mutate();
            cull();
//            computeAVGFitness();
        }
        return population;
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
                    population.add(new ThickLineChromosome(maxX-3, maxY-60));
                    break;
                case ARC:
                    break;
                case CIRCLE:
                    //e.g
//                    population.add(new CircleChromosome(0,0,0));
                    break;
            }
        }
    }

    void computeAVGFitness(){
        int avg=0;
        for(Chromosome tlc : population){
            avg += tlc.fitness();
        }
        System.out.println("population average fitness:= " + (avg / popSize));

    }

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
                    child.add(parent0.get(j));
//                    System.out.println(parent0.get(j));

                }else{
                    child.add(parent1.get(j));
//                    System.out.println(parent1.get(j));
                }

            }
            population.add(new ThickLineChromosome(child));

        }

    }

    //mutation
    private void mutate(){
        Utilities util = new Utilities();
        for(Chromosome tlc : population ){
            for(int j=0; j<3; j++) {
                if (util.rng(100) <= muteRate) {
                    switch( util.rng(3) ){
                        case 0:
                            tlc.setX(util.rng(maxX-3));
                            break;
                        case 1:
                            tlc.setY(util.rng(maxY-60));
                            break;
                        case 2:
                            tlc.setColor(new Color(util.rng(255),util.rng(255),util.rng(255)));
                            break;
                    }

                }
            }
        }

    }


    //culling popSize
    private void cull(){
        Collections.sort(population);
//        System.out.println(":::::::::::::");
//        System.out.println("best fit " + population.get(0).fitness() );
//        System.out.println("worst fit " + population.get(population.size()-1).fitness() );
//        System.out.println(":::::::::::::");
        while (population.size() > popSize){
            int newSize = population.size();
            population.remove(newSize-1);
        }
    }

}
