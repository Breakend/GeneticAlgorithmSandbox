package simpleGa;

import java.util.Arrays;

public class GA {

    public static void main(String[] args) {

        // Set a candidate solution
    	int GENE_POOL = 10;
    	System.out.println("Creating random array of ints");
    	Object[] array = new Object[GENE_POOL];
    	for(int i = 0; i<GENE_POOL; i++){
    		array[i] = (int) (Math.random()*10000%100);
    	}
    	System.out.println("Array is: ");
    	for(int i = 0; i<GENE_POOL; i++){
    		System.out.print(array[i]);
    		System.out.print(", ");
    	}
    	
    	System.out.println("");
    	
    	Object[] sortedArray = new Object[GENE_POOL];
    	System.arraycopy(array, 0, sortedArray, 0, array.length);
    	Arrays.sort(sortedArray);
    	System.out.println("Conventionally sorted array: ");
    	for(int i = 0; i<GENE_POOL; i++){
    		System.out.print(sortedArray[i]);
    		System.out.print(", ");
    	}       
    	System.out.print("");
    	FitnessCalc.setSolution(sortedArray);
//    	System.out.println("Regular array again: ");
//    	for(int i = 0; i<100; i++){
//    		System.out.print(array[i]);
//    		System.out.print(", ");
//    	}
        // Create an initial population
        Population myPop = new SortingPopulation(10, array, true);
        Algorithm myAlg = new SortingAlgorithm(); 
        
        System.out.println("Population Initial: ");
    	for(int i = 0; i<10; i++){
    		System.out.println(myPop.individuals[i].toString());
    	}
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            myPop = myAlg.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());

    }
}