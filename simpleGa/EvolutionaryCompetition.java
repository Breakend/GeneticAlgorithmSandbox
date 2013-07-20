package simpleGa;

import java.util.Arrays;
import java.util.LinkedList;

public class EvolutionaryCompetition {

	LinkedList<Evolution> possibleEvolutions;

	public EvolutionaryCompetition() {
		possibleEvolutions = new LinkedList<Evolution>();
	}

	public void addEvolution(Evolution evol) {
		possibleEvolutions.add(evol);
	}

	private Object[] applyPattern(Object[] array, Pattern p) {
		int switchone = (int) ((double) array.length * p.percentSwitchOne);
		int switchtwo = (int) ((double) array.length * p.percentSwitchTwo);
		return SortingIndividual.swap(array, switchone, switchtwo);
	}

	public Object[] applyEvolution(Object[] array, Evolution evolve) {
		for (Pattern p : evolve.patterns) {
			applyPattern(array, p);
		}
		return array;
	}

	public int computeAccuracy(Object[] base, Object[] sorted) {
		int score = 0;
		for (int i = 0; i <= base.length; i++) {
			if (base[i].equals(sorted[i])) {
				score++;
			}
		}
		return score;
	}

	public double computeEffectiveness(Object[] array, long time_span) {
		Object[] copy = new Object[array.length];
		System.arraycopy(array, 0, copy, 0, array.length);
		Arrays.sort(copy);
		double eff = (double) computeAccuracy(array, copy) / (double) array.length;
		System.out.println("Accuracy: ");
		System.out.println(eff);
		System.out.println("Time to complete:");
		System.out.println(time_span);
		double score = eff / (double) time_span;
		System.out.println("Overall Score: ");
		System.out.println(score);
		return score;
	}

	public Evolution competeEvolutions(Object[] array) {
		double currentBest = 0.0;
		Evolution bestEvol = null;
		for (Evolution e : possibleEvolutions) {
			Object[] copy = new Object[array.length];
			System.arraycopy(array, 0, copy, 0, array.length);
			long t1 = System.currentTimeMillis();
			copy = applyEvolution(copy, e);
			long t2 = System.currentTimeMillis();
			long span1 = t2 - t1;
			double eff = computeEffectiveness(copy, span1);
			if (eff > currentBest) {
				currentBest = eff;
				bestEvol = e;
			}
		}
		System.out.println("Best score: ");
		System.out.println(currentBest);
		return bestEvol;
	}
}
