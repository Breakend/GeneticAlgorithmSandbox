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
		Population newPopulation = new DefaultPopulation(pop.size(), false);

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
		Individual newSol = new DefaultIndividual();
		// Loop through genes
		for (int i = 0; i < indiv1.size(); i++) {
			// Crossover
			if (Math.random() <= uniformRate) {
				newSol.setGene(i, indiv1.getGene(i));
			} else {
				newSol.setGene(i, indiv2.getGene(i));
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
				byte gene = (byte) Math.round(Math.random());
				indiv.setGene(i, gene);
			}
		}
	}

	@Override
	// Select individuals for crossover
	protected Individual tournamentSelection(Population pop) {
		// Create a tournament population
		Population tournament = new DefaultPopulation(tournamentSize, false);
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