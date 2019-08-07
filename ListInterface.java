public interface ListInterface <T>
{
	public int findFirstOccurrence(T item);
	public boolean contains(T item);
	public int getFrequencyOf(T item);
	public int [] findAll(T item)throws ListException;

	public boolean add(T item);
	public void insert(int position, T item)throws ListException;

	public T set(T newItem, int position)throws ListException;
	public T get(int position)throws ListException;

	public T remove()throws ListException;
	public boolean remove(T item)throws ListException;
	public T remove(int position)throws ListException;

	public void clear()throws ListException;
	public T[] toArray();
	public int size();
	public int getCurrentSize();
}
