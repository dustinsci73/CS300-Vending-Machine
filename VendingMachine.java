//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (descriptive title of the program making use of this file)
// Files:           (a list of all source files used by that program)
// Course:          (course number, term, and year)
//
// Author:          (Dustin Li)
// Email:           (dli284@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class VendingMachine 
{
	private Item[] items;  // store items in a min-heap
	private int itemCount; // number of items in this heap
	// Note use of min-heap here, to prioritize the smallest (soonest) expiration day.
	// You may decide to use either 0 or 1 as the top-index in this items array.

	public VendingMachine(int capacity)
	{
		itemCount = 0;
		items = new Item[capacity];
	}
	
	private int getParent(int childIndex) {
	    // return the parent index of the given child index 
	    return (childIndex - 1) / 2;
	}
	 
	private int getLeftChild(int parentIndex) {
	    // return the left child index of the given parent index
	    return 2 * parentIndex + 1;
	}
	 
	private int getRightChild(int parentIndex) {
	    // return the right child index of the given parent index
	    return 2 * parentIndex + 2;
	}
	 
	private void swap(int itemOneIndex, int itemTwoIndex) {
	    // swaps the Item references at itemOneIndex and itemTwoIndex in the items array
		Item temp = items[itemOneIndex];
		items[itemOneIndex] = items[itemTwoIndex];
		items[itemTwoIndex] = temp;
	}
	 
	private void addHelper(int index) {
	    // Propagates the min-heap order property from the node at position index,
	    // up through it's ancestor nodes. Assumes that only the node at position
	    // index may be in violation of this property. This is useful when adding
	    // a new item to the bottom of the heap.
		int i = index;
		if (items[getParent(i)] != null && items[i] != null)
		{
			if (i > 0 && items[getParent(i)].getExpirationDay() < items[i].getExpirationDay())
			{
				swap(i, getParent(i));
				addHelper(getParent(i));
			}
		}
	}
	 
	private void removeHelper(int index) {
	    // Propagates the min-heap order property from the node at position index,
	    // down through it's highest priority descendant nodes. Assumes that the
	    // children of the node at position index conform to this heap property. 
	    // This is useful when removing an item from the top of the heap.
	
		int i = index;
		if (getLeftChild(i) < items.length && getRightChild(i) < items.length)
		{
			int smaller = i;
			int left = getLeftChild(i);
			int right = getRightChild(i);
			if (items[left] != null && items[right] != null)
			{
				if (items[left].getExpirationDay() > items[right].getExpirationDay() && 
						items[left].getExpirationDay() > items[i].getExpirationDay())
				{
					smaller = left;
				}
				if (items[right].getExpirationDay() > items[left].getExpirationDay() && 
						items[right].getExpirationDay() > items[i].getExpirationDay())
				{
					smaller = right;
				}
				if (smaller != i)
				{
					swap(i, smaller);
					removeHelper(smaller);
				}
			}
		}
	}
	
	public void addItem(Item item) {
	    // Add the given item to the items array and perform the necessary
	    // actions to maintain the min-heap properties.
		if (itemCount == items.length)
		{
			throw new IllegalStateException("WARNING: Item not added. "
					+ "This vending machine is already filled to capcity.");
		}
		else
		{
			Item[] array = new Item[items.length];
			int day = item.getExpirationDay();
			int i = 0;
			while (items[i] != null && items[i].getExpirationDay() >= day && i < items.length - 1)
			{
				i++;
			}
			for (int j = 0; j < i; j++)
			{
				array[j] = items[j];
			}
			array[i] = item;
			for (int k = i + 1; k < items.length; k++)
			{
				array[k] = items[k-1];
			}
			this.items = array;
			addHelper(i);
			itemCount++;
		}
	}
	 
	public Item dispenseNextItem() {
	    // Dispense the item with the smallest expiration date from this 
	    // vending machine, while maintaining the min-heap properties.
	    // This method removes the item returned from the heap.
	    if (itemCount == 0)
	    {
	    		throw new IllegalStateException("WARNING: Operation not allowed. "
	    				+ "This vending machine is empty.");
	    }
	    else
	    {
//	    		Item[] array = new Item[items.length];
//	    		Item temp = items[0];
//	    		for (int i = 0; i < array.length - 1; i++)
//	    		{
//	    			array[i] = items[i+1];
//	    		}
//	    		this.items = array;
	    		int i = 0;
	    		while (i+1 < items.length && items[i+1] != null)
	    		{
	    			i++;
	    		}
	    		Item temp = items[i];
	    		items[i] = null;
	    		removeHelper(0);
	    		itemCount--;
	    		return temp;
	    }
	}
	     
	public Item getNextItem() {
	    // This method returns a reference to the next item that will be dispensed.
	    // This method does NOT change the state of the Vending Machine or its heap.
		if (itemCount == 0)
	    {
			throw new IllegalStateException("WARNING: Operation not allowed. "
					+ "This vending machine is empty.");
	    }
	    else
	    {
	    		int i = 0;
	    		while (i+1 < items.length && items[i+1] != null)
	    		{
	    			i++;
	    		}
	    		Item temp = items[i];
	    		return temp;
	    }
	}
	 
	 public Item dispenseItemAtIndex(int index) {
	    // Dispense the item from a particular array index, while maintaining
	    // the min-heap properties.  This method removes that item from the heap.
	    // This index parameter assumes the top-index is zero.  So you'll need to
	    // add one to this index, if you are using the top-index = 1 convention.
		if (itemCount == 0)
		{
			throw new IllegalStateException("WARNING: Operation not allowed. "
					+ "This vending machine is empty.");
		}
		else
		{
			if (index < 0 || index > items.length - 1 || items[index] == null)
			{
				throw new IndexOutOfBoundsException("WARNING: Operation not allowed. Index is invalid.");
			}
			else
			{
				Item[] array = new Item[items.length];
				for (int i = 0; i < index; i++)
				{
					array[i] = items[i];
				}
				Item temp = items[index];
				for (int i = index + 1; i < array.length; i++)
				{
					array[i-1] = items[i];
				}
				this.items = array;
				removeHelper(index);
				itemCount--;
				return temp;
			}
		}
	}
	 
	public Item getItemAtIndex(int index) {
	    // This method returns a reference to the item at position index.
	    // This method does not change the state of the vending machine.
	    // This index parameter assumes the top-index is zero. So you'll need to 
	    // add one to this index, if you are using the top-index = 1 convention.     
	    if (itemCount == 0)
	    {
	    		throw new IllegalStateException("WARNING: Operation not allowed. "
	    				+ "This vending machine is empty.");
	    }
	    else
	    {
	    		if (index < 0 || index > items.length - 1 || items[index] == null)
			{
				throw new IndexOutOfBoundsException("WARNING: Operation not allowed. Index is invalid.");
			}
			else
			{
//				for (int i = 0; i < items.length; i++)
//				{
//					System.out.println(items[i]);
//					System.out.println();
//					System.out.println(items[4]);
//				}
				return items[index];
			}
	    }
	}
}