package h.img.rec;

import java.util.ArrayList;
import java.util.Collections;

class GeneticAlgorithm {
    //TODO correct polymorphism
    //mutation rate
    //crossover rates and different implementations
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
    ArrayList<ThickLineChromosome> run(){
        initPopulation();
        for(int i=0;i<generations;i++){
            crossover();
//            mutate();
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
                    break;
            }
        }
    }

    void computeAVGFitness(){
        int avg=0;
        for(ThickLineChromosome tlc : population){
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
