package simpleGa;

public abstract class FitnessCalc {

    static Object[] solution;

    /* Public methods */
    // Set a candidate solution as a byte array
    public static void setSolution(Object[] newSolution) {
        solution = newSolution;
    }

    // To make it easier we can use this method to set our candidate solution 
    // with string of 0s and 1s
    static Object[] setSolution(String newSolution) {
        solution = new Object[newSolution.length()];
        // Loop through each character of our string and save it in our byte 
        // array
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
                solution[i] = Integer.parseInt(character);
        }
        return solution;
    }

    // Calculate individual's fitness by comparing it to our candidate solution
    static int getFitness(Individual individual) {
    	if(individual.getGene(0) == null) return -1;
        int fitness = 0;
        // Loop through our individuals genes and compare them to our candidates
        try{
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getGene(i).equals(solution[i])) {
                fitness++;
            }
        }
        }
        catch(Exception e){
        	System.out.println(e);
        	System.out.println("Individual at exception");
        	System.out.println(individual);
        }
        return fitness;
    }
    
    // Get optimum fitness
    static int getMaxFitness() {
        int maxFitness = solution.length;
        return maxFitness;
    }
}