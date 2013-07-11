package simpleGa;

import java.util.Random;

public class SortingIndividual extends Individual{
	
	private Object[] base;
	private int swappedBaseIndex1;
	private int swappedBaseIndex2;
	
	public SortingIndividual(Object[] array){
		this.base = array;
	}
	
	@Override
	public void generateIndividual() {
		Random random = new Random();
			swappedBaseIndex1 = random.nextInt(base.length) ;
			swappedBaseIndex2 = random.nextInt(base.length) ;
			Object[] a = new Object[base.length];
			System.arraycopy(base, 0, a, 0, base.length);
			genes = swap(a, swappedBaseIndex1, swappedBaseIndex2);
	}

	public void setBaseArray(Object[] array){
		this.base = array;
	}
	
	public Object[] getBase(){
		return this.base;
	}
	
	public Object[] swap(Object[] array, int index1, int index2){
		Object temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return array;
	}
}
