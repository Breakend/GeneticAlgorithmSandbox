package simpleGa;

public class DefaultPopulation extends Population {
    /*
     * Constructors
     */
    // Create a population
    public DefaultPopulation(int populationSize, boolean initialise) {
        individuals = new SortingIndividual[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new DefaultIndividual();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }
}
