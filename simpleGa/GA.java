package simpleGa;

import java.sql.Time;
import java.util.Arrays;

public class GA {

	public static void main(String[] args) {

		// Set a candidate solution
		int GENE_POOL = 1000;
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
		long t1 = System.currentTimeMillis();
		Arrays.sort(sortedArray);
		long t2 = System.currentTimeMillis();
		long span1 = t2-t1;
		System.out.println("Time to calculate: ");
		System.out.print(span1);
		System.out.println(" ms");

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

		//        System.out.println("Population Initial: ");
		//    	for(int i = 0; i<10; i++){
		//    		System.out.println(myPop.individuals[i].toString());
		//    	}

		// Evolve our population until we reach an optimum solution

		long t3 = System.currentTimeMillis();
		int generationCount = 0;
		while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
			generationCount++;
//			System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
			myPop = myAlg.evolvePopulation(myPop);
		}
		long t4 = System.currentTimeMillis();
		System.out.println("Solution found!");
		System.out.println("Generation: " + generationCount);
		System.out.println("Genes:");
		System.out.println(myPop.getFittest());

		long span2 = t4-t3;
		System.out.println("Time to calculate: ");
		System.out.print(span2);
		System.out.println(" ms");

	}
}