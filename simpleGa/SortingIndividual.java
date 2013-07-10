package simpleGa;

public class SortingIndividual extends Individual{
	
	private Object[] base;
	private int swappedBaseIndex1;
	private int swappedBaseIndex2;
	
	public SortingIndividual(Object[] array){
		this.base = array;
	}
	
	@Override
	public void generateIndividual() {
			swappedBaseIndex1 = (int) (Math.random() % base.length);
			swappedBaseIndex2 = (int) (Math.random() % base.length);
			genes = swap(base, swappedBaseIndex1, swappedBaseIndex2);
	}

	public void setBaseArray(Object[] array){
		this.base = array;
	}
	
	public Object[] swap(Object[] array, int index1, int index2){
		Object temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return array;
	}
}
