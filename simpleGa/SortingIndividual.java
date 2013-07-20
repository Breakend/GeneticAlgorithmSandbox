package simpleGa;

import java.util.LinkedList;
import java.util.Random;

public class SortingIndividual extends Individual {

	private Object[] base;
	public int swappedBaseIndex1;
	public int swappedBaseIndex2;
	protected LinkedList<SortingIndividual> ancestors;

	public SortingIndividual(Object[] array) {
		this.base = array;
		ancestors = new LinkedList<SortingIndividual>();
	}

	@Override
	public void generateIndividual() {
		try {
			ancestors.add((SortingIndividual) this.clone());
		} catch (CloneNotSupportedException e) {
			System.out.println("Clone not made");
			e.printStackTrace();
		}
		Random random = new Random();
		swappedBaseIndex1 = random.nextInt(base.length);
		swappedBaseIndex2 = random.nextInt(base.length);
		Object[] a = new Object[base.length];
		System.arraycopy(base, 0, a, 0, base.length);
		genes = swap(a, swappedBaseIndex1, swappedBaseIndex2);
	}

	public void setBaseArray(Object[] array) {
		this.base = array;
	}

	public Object[] getBase() {
		return this.base;
	}

	public static Object[] swap(Object[] array, int index1, int index2) {
		Object temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return array;
	}
}
