package simpleGa;

public class SortingPopulation extends Population {


	public SortingPopulation(int populationSize, Object[] startingArray, boolean initialise) {
		individuals = new SortingIndividual[populationSize];
		// Initialise population
		if (initialise) {
			// Loop and create individuals
			for (int i = 0; i < size(); i++) {
				Individual newIndividual = new SortingIndividual(startingArray);
				newIndividual.generateIndividual();
				saveIndividual(i, newIndividual);
			}
		}	
	}

}
