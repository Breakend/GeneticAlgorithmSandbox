package simpleGa;

import java.util.LinkedList;

public class Evolution {
	
	LinkedList<Pattern> patterns;
	
	public Evolution(SortingIndividual ind){
		patterns = new LinkedList<Pattern>();
		for(SortingIndividual indie : ind.ancestors){
			patterns.add(new Pattern(indie.swappedBaseIndex1, indie.swappedBaseIndex2));
		}
	}
}
