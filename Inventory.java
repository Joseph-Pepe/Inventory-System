import java.util.Arrays;
public class Inventory<T extends Copyable> implements ListInterface<T>, Copyable{
	private T[] List;
	private int numberOfItems;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 20;
	private static final int MINIMUM_CAPACITY = 1;
	private static final int MAX_CAPACITY = 1000000;

	public Inventory(){
		this(DEFAULT_CAPACITY);
	}

	public Inventory(int initialCapacity){
		if(initialCapacity < MINIMUM_CAPACITY || initialCapacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create an inventory whose size exceeds allowed maximum: " + MAX_CAPACITY + ", and allowed Minimum: " + MINIMUM_CAPACITY );

			List = (T[]) new Copyable[initialCapacity];
			numberOfItems = 0;
			initialized = true;
	}

	/** Adds the object into the Inventory list.
		@return true if object != null
	*/
	public boolean add(T item){

		 checkInitialization();

		 if(numberOfItems >= MAX_CAPACITY)
		 	return false;

		 if(checkIsNull(item))
			return false;

		 if(isFull())
			Resize();

			List[numberOfItems] = item;
			numberOfItems++;
			return true;
	}

	/** Insert item into specified position of the Inventory list
		@throws  ListException if item == null.
		@throws  IndexOutOfBounds if  the specified position is illegal.
	*/
	public void insert(int position, T item)throws ListException{

		checkInitialization();
		checkCapacity();

		if(checkIsNull(item))
			throw new ListException("Cannot insert. Null is not allowed");

		if(position < 0 || position >= numberOfItems)
			throw new IndexOutOfBoundsException("Illegal position given.");

		if(isFull())
			Resize();

			makeRoomForInserting(position);
			List[position] = item;
			numberOfItems++;
	}

	/** replaces the object in a specific location with the specified object.
		@return item at the end of the list.
		@throws ListException if item is null, exceeds the position of the list or if the list is empty.
	*/
	public T set(T item, int position)throws ListException
	{
		 checkInitialization();
		 checkPosition(position);

		 if(checkIsNull(item))
		    throw new ListException("Error. Unable to replace. Replacement cannot be null.");

		 if(isEmpty())
		    throw new ListException("Error. Unable to replace. List is empty.");

		    T savedItem = List[position];
		    List[position] = item;
		    return savedItem;
  	}

  	/** Delete last item in the list
		@return item at the end of the list.
		@throws ListException if the inventory list is empty.
	*/
	public T remove()throws ListException{
		 checkInitialization();

		 if(isEmpty())
			throw new ListException("Cannot delete because list is empty.");

			T savedItem = List[numberOfItems - 1];
			List[numberOfItems - 1] = null;
			numberOfItems--;
			return savedItem;
	}


	/** Delete specified item in the list.
		@return true if item is in list.
		@throws ListException if list is empty or item == null.
	*/
	public boolean remove(T item){

		checkInitialization();

		if(isEmpty())
		    return false;

		if(checkIsNull(item))
		    return false;

		    int indexPosition = getIndexOf(item);
		    List[indexPosition] = null;
		    closeListGap(indexPosition);
		    numberOfItems--;
		    return true;
	}

	/** Delete  item in the specified position of the list.
		@return item at the specified position.
		@throws ListException if position exceeds list position or the list is empty.
	*/
	public T remove(int position)throws ListException{

		checkInitialization();
		checkPosition(position);

		if(isEmpty())
		  throw new ListException("Cannot delete, List is empty.");

		  T deletedObject = List[position];
		  List[position] = null;
		  closeListGap(position);
		  numberOfItems--;
		  return deletedObject;
	}

	/** Get the item in the the specified position of the list.
		@return item at the specified position.
		@throws ListException if position exceeds list size, list is empty or position <= 0
	*/
	public T get(int position)throws ListException{
		checkPosition(position);
		
	    	if (isEmpty())
		    throw new ListException("Error. Unable to get list. List is empty.");

	 	    return  List[position];
  	}

	/** Get the item in the the specified location of the list.
		@return position of the specified objects location.
		@throws IndexOutOfBoundsException if the position given is illegal.
	*/
	public int find(T item, int start,int end)
	{
		int objectIndex = -4;

		if(start < 0 || start > List.length || end < 0 || end > List.length  )
		    throw new IndexOutOfBoundsException("Error. Unable to find. Start and/or end position bad.");
		if(start > end)
			return -1;
		if(checkIsNull(item))
			return -2;
		if(isEmpty())
			return -3;

		if(start == end){
			if(List[start].equals(item))
				objectIndex = start;
		}
		else
		{
			for(int elementIndex = start; elementIndex < end; elementIndex++){
			   if(List[elementIndex].equals(item))
				  objectIndex = elementIndex;
			}
		}
		return objectIndex;
 	}

	/** Get the first occurrence of the item in the list.
		@return the position of the specified item.
	*/
	public int findFirstOccurrence(T item)
	{
		for(int index = 0; index < numberOfItems; index++){
			if(List[index].equals(item))
				return index;
		}
		return -1;
	}

	/** Get all the occurrences of the item in the list.
		@return the position of the specified item.
	*/
	public int[] findAll(T item){

		int[] location = new int[numberOfItems];

		for(int index = 0; index < numberOfItems; index++)
			location[index] = -1;

		if(item == null)
			return location;

		int index = 0;
		int numFound = 0;
		while(index < numberOfItems){
			if(List[index].equals(item)){
				location[numFound] = index;
				numFound++;
			}
			index++;
		}
		return location;
	}

	/** Finds the specified object in the list and returns the position.
		@return  The position of the object in the list.
	*/
	private int getIndexOf(T item){

		int objectPosition = -1;
		boolean found = false;

		int index = 0;
		while(!found && (index < numberOfItems)){
			if(item.equals(List[index])){
				found = true;
				objectPosition = index;
			}
			index++;
		}
		assert objectPosition >= 0 : objectPosition;
		return objectPosition;
	}

	/** Counts the amount of duplicate objects in the list for the specified item.
		@return The number of occurrences of the object in the list.
	*/
	public int getFrequencyOf(T item){
		int countSameItem = 0;

		for(int index = 0; index < numberOfItems; index++){
			if(item.equals(List[index]))
				countSameItem++;
		}
		return countSameItem;
	}

	/** Fills an array with the items in the list.
		@return An array filled with the items in the list.
	*/
	public T[] toArray(){
		checkInitialization();
		T[] arrayCopy = (T[]) new Copyable[numberOfItems];
		arrayCopy = Arrays.copyOf(List,List.length);
		return arrayCopy;
	}

	private void Resize(){
		ensureCapacity(15);
	}

	private void ensureCapacity(int amount){
		try
		{
			T [] resizedList = (T[]) new Copyable[List.length + amount];

			for(int index = 0; index < numberOfItems; index++){
				resizedList[index] = List[index];
			}
			List = resizedList;
		}
		catch(OutOfMemoryError e){
			System.out.println("You are low on memory.");
		}
	}

	/** Reduces the size of the inventory to the number of items present in the list.
	*/
	public void trimToSize()
	{
		T [] ModifiedList = (T[]) new Copyable[numberOfItems];

		for(int index = 0; index < numberOfItems; index++){
			ModifiedList[index] = List[index];
		}
	    List = ModifiedList;
	}

	private void checkCapacity()
	{
		if(numberOfItems > MAX_CAPACITY)
		    throw new IllegalStateException("Attempt to create a List with a capacity that exceeds " + MAX_CAPACITY);
	}

	private void checkInitiallyGivenCapacity(int initialCapacity)
	{
	    if (initialCapacity > MAX_CAPACITY)
	            throw new IllegalStateException("Attempt to create a List whose capacity exceeds " + "allowed maximum of " + MAX_CAPACITY);
    }

	/** Empties the list of items
	*/
	public void clear()throws ListException{
		while(!isEmpty())
		    remove();
	}

	/** Checks to see if the specified item is in the list.
		@return true if the item is in the list.
	*/
	public boolean contains(T item){
		checkInitialization();
		return getFrequencyOf(item) > -1;
	}

	/** Tells the size that the inventory was initialized to.
		@return The size of the list.
	*/
	public int size(){
		return List.length;
	}

	/** Gives the number of items in the inventory.
		@return The number of items present in the list.
	*/
	public int getCurrentSize(){
		return numberOfItems;
	}

	private boolean isFull(){
		return numberOfItems >= List.length;
	}

	private boolean isEmpty(){
		return numberOfItems == 0;
	}

	private boolean checkIsNull(Object item){
		if(item == null)
			return true;

			return false;
	}

	private void checkPosition(int position){

		if(position < 0 || position >= numberOfItems)
			throw new IndexOutOfBoundsException("Illegal position given.");
	}

	private void closeListGap(int position){
		for(int index = position; index < numberOfItems; index++)
			List[index] = List[index + 1];
	}

	private void makeRoomForInserting(int position){
		for(int index = numberOfItems - 1; index >= position; index--)
			List[index + 1] = List[index];
	}

	private void checkInitialization(){
		if(!initialized)
			throw new SecurityException("List is not initialized properly.");
	}

	public boolean equals(Object newInventory)
	{
		if(checkIsNull(newInventory) || newInventory.getClass() != Inventory.class)
			return false;

		Inventory <T> PolymorphicInventory;
		PolymorphicInventory = (Inventory) newInventory;

		if(List.length != PolymorphicInventory.List.length)
			return false;

		try{
			for(int index = 0; index < numberOfItems; index++){
				if(!List[index].equals(PolymorphicInventory.get(index)))
					return false;
		    }
		}
		catch(ListException e){
			System.out.println("Error. Comparing two object using the get Method.");
		}
		return true;
	}

	private String printArray(){
		String itemInfo = "";

		int ListSize = 0;
		while(ListSize < numberOfItems){
			itemInfo += List[ListSize] + "\n";
			ListSize++;
		}
		return itemInfo;
	}

	public String toString(){
		if(isEmpty())
		 return "The Inventory is empty.";

		 return printArray();
	}

	public Object clone()
	{
		Inventory<T> copyInventory = null;
		try
		{
			copyInventory = (Inventory<T>)super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			throw new Error(e.toString());
		}
		copyInventory.List = List.clone();

		for(int index = 0; index < numberOfItems; index++)
			copyInventory.List[index] = (T)List[index].clone();

			return copyInventory;
	}

	protected void finalize(){
		numberOfItems = 0;
		initialized = false;
	}
}
