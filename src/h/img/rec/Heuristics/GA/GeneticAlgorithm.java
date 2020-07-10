package h.img.rec.Heuristics.GA;

import h.img.rec.EditImage;
import h.img.rec.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm {
    //TODO
    //crossover rates and different implementations
    private int popSize;
    private int muteRate;
    private double crossOverRate;
    private int maxX;
    private int maxY;
    private int generations;
    private ArrayList<Chromosome> population = new ArrayList<>();

    //set values
    public GeneticAlgorithm(int popSize, int x, int y, int muteRate, double crossOverRate, int generations){
        this.popSize = popSize;
        this.generations = generations;
        this.maxX = x;
        this.maxY = y;
        this.muteRate = muteRate;
        this.crossOverRate = crossOverRate;
    }

    public ArrayList<Chromosome> run(){
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
        population.clear();
        for(int i=0;i<popSize;i++){
            addToPopulation(null);
        }
    }

    private void addToPopulation(ArrayList<String> child){
        EditImage edit = EditImage.getInstance();
        switch(edit.getStyle()){
            case LINE:
//                if(child == null){
//
//                }else{
//
//                }
                break;
            case THICK_LINE:
                if(child == null){
                    population.add(new ThickLineChromosome(maxX, maxY, edit.getStyleHeight(), edit.getStyleWidth()));
                }else{
                    population.add(new ThickLineChromosome(child));
                }
                break;
            case ARC:
//                if(child == null){
//
//                }else{
//
//                }
                break;
            case CIRCLE:
                if(child == null){
                    population.add(new CircleChromosome(maxX, maxY, edit.getStyleHeight(), edit.getStyleWidth()));
                }else{
                    population.add(new CircleChromosome(child));
                }
                break;
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
            ArrayList<String> child0 = new ArrayList<>();
            ArrayList<String> child1 = new ArrayList<>();
//            System.out.println("-----1?>" + parent0.size());
//            System.out.println("-----2?>" + parent1.size());

            for(int j=0; j<p0.getFeatures(); j++){

                if( util.rng(1) == 0 ){
                    child0.add(parent0.get(j));
                    child1.add(parent1.get(j));
//                    System.out.println(parent0.get(j));

                }else{
                    child0.add(parent0.get(j));
                    child1.add(parent1.get(j));
//                    System.out.println(parent1.get(j));
                }

            }
            addToPopulation(child0);
            addToPopulation(child1);
        }

    }

    //mutation
    //TODO move to individual chromo, easier to control
    private void mutate(){
        Utilities util = new Utilities();
        for(Chromosome chromosome : population ){
            for(int j=0; j<3; j++) {
                if (util.rng(100) <= muteRate) {
                    switch( util.rng(3) ){
                        case 0:
                            chromosome.setX(util.rng(maxX));
                            break;
                        case 1:
                            chromosome.setY(util.rng(maxY));
                            break;
                        case 2:
                            chromosome.setColor(new Color(util.rng(255),util.rng(255),util.rng(255)));
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
