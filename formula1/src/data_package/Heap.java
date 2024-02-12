package data_package;

import java.util.Arrays;

public class Heap {

	private int capacity;
	private int size;
	Team teams[];
	
	public Heap(int maxsize) {
		this.capacity=maxsize;
		teams=new Team[capacity];
		size=0;
	}
	
	//Take a any child (left or right) or parent's index from our items.
	private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1;}
	private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2;}
	private int getParentIndex(int childIndex) { return (childIndex-1) / 2;}
	
	//Check any child (left or right) or parent's index of our items.
	private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
	private boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
	private boolean hasParent (int index) { return getParentIndex(index) >= 0; }
	
	//Take a any child (left or right) or parent's Overall value from our items.
	private double leftChild(int index) { return teams[getLeftChildIndex(index)].score;}
	private double rightChild(int index) { return teams[getRightChildIndex(index)].score;}
	private double parent(int index) { return teams[getParentIndex(index)].score;}
	
	//Swap the parameter indexes in items.
	private void swap(int indexOne, int indexTwo) {
		Team temp = teams[indexOne];
		teams[indexOne] = teams[indexTwo];
		teams[indexTwo] = temp;
	}
	
	//Control the capacity of items array.
	private void ensureExtraCapacity() {
		if(size == capacity) {
			teams = Arrays.copyOf(teams, capacity * 2);
			capacity *=2;   // to fit both firest drivers and second drivers.
		}
	}
	
	//return a first value of items array ( max value for our heap ).
	public Team peek() {
		if(size == 0) throw new IllegalStateException();
		return teams[0];
	}
	
	//replace max and last value of items array and decrease size value one ( remove max value ).
	public Team pool() {
		if(size == 0) throw new IllegalStateException();
		Team item=teams[0];
		teams[0]=teams[size-1];
		size--;
		heapifyDown();
		return item;
	}
	
	//Check the first value is MAX.
	private void heapifyDown() {
		int index = 0;
		while(hasLeftChild(index)) {
			int greaterChildIndex=getLeftChildIndex(index);
			
			if(hasRightChild(index) && rightChild(index) > leftChild(index))
				greaterChildIndex = getRightChildIndex(index);
			
			if(teams[index].score > teams[greaterChildIndex].score)
				break;
			else
				swap(greaterChildIndex, index);
			
			index=greaterChildIndex;
		}
	}
	
	//Add a new item to the array and check with heapify methods.
	public void push(Team item) {
		ensureExtraCapacity();
		teams[size] = item;
		size++;
		heapifyUp();
	}
	
	//Check the last value is not greater than it's any parents.
	private void heapifyUp() {
		int index = size-1;
		while(hasParent(index) && teams[index].score > parent(index)) {
			swap(getParentIndex(index),index);
			index = getParentIndex(index);
		}
	}
}
