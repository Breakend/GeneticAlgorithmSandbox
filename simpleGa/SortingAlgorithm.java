package simpleGa;

public class SortingAlgorithm extends Algorithm {

	/* Public methods */

	/**
	 * Evolve a population
	 * @param pop
	 * @return
	 */
	@Override
	public Population evolvePopulation(Population pop) {
		SortingIndividual baseIndiv = (SortingIndividual) pop.individuals[0];
		Population newPopulation = new SortingPopulation(pop.size(), baseIndiv.getBase(), false);
	
		// Keep our best individual
		if (elitism) {
			newPopulation.saveIndividual(0, pop.getFittest());
		}

		// Crossover population
		int elitismOffset;
		if (elitism) {
			elitismOffset = 1;
		} else {
			elitismOffset = 0;
		}
		// Loop over the population size and create new individuals with
		// crossover
		for (int i = elitismOffset; i < pop.size(); i++) {
			Individual indiv1 = tournamentSelection(pop);
			Individual indiv2 = tournamentSelection(pop);
			Individual newIndiv = crossover(indiv1, indiv2);
			newPopulation.saveIndividual(i, newIndiv);
		}

		// Mutate population
		for (int i = elitismOffset; i < newPopulation.size(); i++) {
			mutate(newPopulation.getIndividual(i));
		}

		return newPopulation;
	}

	@Override
	protected Individual crossover(Individual indiv1, Individual indiv2) {
		Individual newSol = null;
		// Loop through genes
		for (int i = 0; i < indiv1.size(); i++) {
			// Crossover
			if (Math.random() <= uniformRate) {
				newSol = new SortingIndividual(indiv1.genes);
			} else {
				newSol = new SortingIndividual(indiv2.genes);
			}
		}
		return newSol;
	}

	@Override
	// Mutate an individual
	protected void mutate(Individual indiv) {
		// Loop through genes
		for (int i = 0; i < indiv.size(); i++) {
			if (Math.random() <= mutationRate) {
				// Create random gene
				indiv.generateIndividual();
			}
		}
	}

	@Override
	// Select individuals for crossover
	protected Individual tournamentSelection(Population pop) {
		// Create a tournament population
		SortingIndividual baseIndiv = (SortingIndividual) pop.individuals[0];
		Population tournament = new SortingPopulation(tournamentSize, baseIndiv.getBase(), false);
		// For each place in the tournament get a random individual
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.size());
			tournament.saveIndividual(i, pop.getIndividual(randomId));
		}
		// Get the fittest
		Individual fittest = tournament.getFittest();
		return fittest;
	}
}