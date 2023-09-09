// TO DO: add your implementation and JavaDoc
/** Dynamic Array class.
     * @author Edrees Porjosh
	 * @param <T> type.
     */
public class DynArr310<T> {

	//underlying array for storage -- you MUST use this for credit!
	//Do NOT change the name or type
	/** The field storage.
     * @param T array of storage
     */
	private T[] storage;	//underlying array
	/** The field s or size counter.
     * @param int count
     */
	private int size=0; //size
	/** The field MINCAP.
     * @param int minimum capacity
     */
	private static final int MINCAP = 2;	//default initial capacity / minimum capacity
	

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/** Creating storage array with a min size of MINCAP (2).
     */	
	@SuppressWarnings("unchecked")
	public DynArr310(){

		storage=(T[]) new Object[MINCAP];
		
		//constructor
		//initial capacity of the array should be MINCAP
		
		// Hint: Can't remember how to make an array of generic Ts? It's in the textbook...
	}
	/** Constructor with.
	 * @param initCap gets taken in
	 * @throws IllegalArgumentException is thrown
     */
	@SuppressWarnings("unchecked")
	public DynArr310(int initCap)throws IllegalArgumentException{
		// Constructor
		if (initCap>MINCAP){
			storage=(T[]) new Object[initCap];
			size=0;
		}
		else{
			throw new IllegalArgumentException("Capacity must be atleast 2");
		}
		// Initial capacity of the storage should be initCap.

		// - Throw IllegalArgumentException if initCap is smaller than MINCAP
		// - Use this _exact_ error message for the exception
		//   (quotes are not part of the message):
		//    "Capacity must be at least 2!"
				
	}
	

	
	/** Returns the number of elements in storage.
	 * @return int that returns the storage size
	 */
	public int size() {	
		
		
		//report current number of elements
		// O(1)
		
		return size; //default return, remove or change as needed(-1)
	}  	
	/** returns the max num of elements in storage.
	 * @return int that returns the storage capacity
	 */
	public int capacity() { 
		//report max number of elements
		// O(1)
	
		return storage.length; //default return, remove or change as needed(-1)
	} 
	

	/** Sets desired value at given index.
	 * @param index is taken.
	 * @param value is taken in.
	 * @return T that returns the desired value at input index.
	 */
	public T set(int index, T value) {
		if (index<0){
			throw new IndexOutOfBoundsException("Index: "+ index + " out of bounds!");
		}
		
		if (value==null){
			throw new IllegalArgumentException("Null values not accepted");
		}
		T b=storage[index];
		storage[index]=value;
		return b;

		
		
		// Replace the item at the given index to be the given value.
		// Return the old item at that index.
		// Note: You cannot add new items (i.e. cannot increase size) with this method.
		
		// O(1)
		
		// - Throw IndexOutOfBoundsException if index is not valid
		// - Use this code to produce the correct error message for
		// the exception (do not use a different message):
		//	  "Index: " + index + " out of bounds!"
		
		// - Throw IllegalArgumentException if value is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Null values not accepted!"
		
		//default return, remove/change as needed

		
	}
	/** Gets value at given index.
	 * @param index taken in.
	 * @return T that returns the item at input index.
	 * @throws IndexOutOfBoundsException is what is thrown.
	 */
	public T get(int index)throws IndexOutOfBoundsException{
		// Return the item at the given index
		if (index<0){
			throw new IndexOutOfBoundsException("Index: "+ index + " out of bounds!");
		}
	
		
		// Use the exception (and error message) described in set()
		// for invalid indicies.
		
		// O(1)
		return storage[index];
		//default return, remove/change as needed

	}
	/** Adds whatever value to the end of the storage whilst doubling if full. 
	 * @param value is what's taken.
	 */
	@SuppressWarnings("unchecked")
	public void add(T value){
		if (value==null){
			throw new IllegalArgumentException("Null values not accepted!");
		}
		if (size()==capacity()){
			T[] edub=(T[]) new Object[storage.length*2];
			for (int i=0; i<storage.length; i++){
				edub[i]=storage[i];
			}
			edub[size]=value;
			storage=edub;
			size+=1;
		}
		else{
			storage[size]=value;
			size+=1;
		}	
		
	
		// Append an element to the end of the storage.		
		// Double the capacity if no space available.
		
		// For a null value, use the same exception and message 
		// as set(). 
		// You can assume we will never need to grow the capacity to a value 
		// beyond Integer.MAX_VALUE.  No need to check or test that boundary 
		// value when you grow the capacity.
		// Amortized O(1)
	}
	/** Inserting value at a given index, while shifitng elements to the right and returning the original value.
	 * @param index inserts a value at input index. Shifts units to the right if needed. Double storage capacity if needed.
	 * @param value inserts the value at index whilst shifting units to the right. Double storage capacity if needed.
	 */
	@SuppressWarnings("unchecked")
	public void insert(int index, T value) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
		if (value == null) {
			throw new IllegalArgumentException("Null values not accepted!");
		}
		if (size == capacity()) {
			T[] ji = (T[]) new Object[storage.length * 2];
			for (int i = 0; i < storage.length; i++) {
				ji[i] = storage[i];
			}
			ji[index] = value;
			for (int i=index; i<size; i++) {
				ji[i+1]=storage[i];
			}
			storage = ji;
		} 		
		else{
			T[] ji=(T[]) new Object[storage.length];
			for (int i=0; i<storage.length; i++){
				ji[i]=storage[i];
			}
			ji[index]=value;
			for (int i=index; i<storage.length-1; i++) {
				ji[i+1]=storage[i];
			}
			storage=ji;
			
		}
		size+=1;
		//T ed=set(index, value);
		// Insert the given value at the given index and shift elements if needed. 
		// NOTE: You can also append items with this method.
		// If no space available, grow your storage in the same way as required by add().
		// Assume the same as add() for the upper bound of capacity.
		// Code reuse! Consider using setCapacity (see below).
		// For an invalid index or a null value,  use the same exception and message 
		// as set(). However, remember that the condition of the exception is
		// different (a different invalid range for indexes).
		// O(N) where N is the number of elements in the storage			 
	}
	
	/** Removes value at a given index, but returning that removed value, and adjusting storage if needed. 
	 * @param index is returned which is the original value, while removing the value at index from the storage. 
	 * @return T is returned the original value at the param index. 
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index){
		if (index<0){
			throw new IndexOutOfBoundsException("Index: "+ index + " out of bounds!");
		}
		if (index>=size){
			throw new IndexOutOfBoundsException("Index: "+ index + " out of bounds!");
		}
		T[] ji=(T[]) new Object[storage.length];
		for (int i=0; i<size(); i++){
			ji[i]=storage[i];
		}
		for (int i = index; i < size()-1; i++) {
			ji[i] = storage[i+1];
		}
		T val=storage[index];
		storage=ji;
		size-=1;
		if (size <= capacity() / 3) {
			int ed =(capacity() / 2);
			if (ed<MINCAP){
				ed=MINCAP;
			}
			T[] nw = (T[]) new Object[ed];
			for (int i = 0; i < size(); i++) {
				nw[i] = storage[i];
			}
			storage = nw;
		}
		return val;
		// Remove and return the element at the given index. Shift elements
		// to ensure no gap. Throw an exception when there is an invalid
		// index (see set(), get(), etc. above).
		// If the number of elements after removal falls below or at 1/3 of the capacity, 
		// halve capacity (rounding down) of the storage. 
		// However, capacity should NOT go below MINCAP.
		// O(N) where N is the number of elements currently in the storage
		//default return, remove/change as needed			
	}  
	
	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************
	/** Dynamic Array toString.
	 * Provided toString.
	 */
	@Override
	public String toString() {
		//This method is provided. Add JavaDoc and comments.
		
		StringBuilder s = new StringBuilder("[");
		for (int i = 0; i < size(); i++) {
			s.append(get(i));
			if (i<size()-1)
				s.append(", ");
		}
		s.append("]");
		return s.toString().trim();
		
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************
	

	/** Dynamic Array toStringDebug.
	 * @return String.
	 */
	public String toStringDebug() {
		//This method is provided for debugging purposes
		//(use/modify as much as you'd like), it just prints
		//out the DynArr310 details for easy viewing.
		StringBuilder s = new StringBuilder("DynArr310 with " + size()
			+ " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n  ["+i+"]: " + get(i));
		}
		return s.toString().trim();
		
	}

	/** Dynamic Array Main(testing).
	 * @param args are the parameters for debugging/testing.
	 */
	public static void main (String args[]){
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellend first step! But it does NOT guarantee your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!

		//create a DynArr310 of integers
		DynArr310<Integer> ida = new DynArr310<>();
		if ((ida.size() == 0) && (ida.capacity() == 2)){
			System.out.println("Yay 1");
		}

		//add some numbers at the end
		for (int i=0; i<3; i++)
		
			ida.add(i*5);
		

		//uncomment to check details
		//System.out.println(ida);
		
		//checking dynamic array details
		if (ida.size() == 3 && ida.get(2) == 10 && ida.capacity() == 4){
			System.out.println("Yay 2");
		}
		
		//insert, set, get
		ida.insert(1,-10);
		ida.insert(4,100);
		if (ida.set(1,-20) == -10 && ida.get(2) == 5 && ida.size() == 5 
			&& ida.capacity() == 8 ){
			System.out.println("Yay 3");
		}
		
		//create a DynArr310 of strings
		DynArr310<String> letters = new DynArr310<>(6);
		
		//insert some strings
		letters.insert(0,"c");
		letters.insert(0,"a");
		letters.insert(1,"b");
		letters.insert(3,"z");
		
		//get, toString()
		if (letters.get(0).equals("a") && letters.toString().equals("[a, b, c, z]")){
			System.out.println("Yay 4");
		}
		
		//remove
		if (letters.remove(0).equals("a") && letters.remove(1).equals("c") &&
			letters.get(1).equals("z") && letters.size()==2 && letters.capacity()==3){
			System.out.println("Yay 5");			
		}

		//exception checking
		try{
			letters.set(-1,null);
		}
		catch (IndexOutOfBoundsException ex){
			if (ex.getMessage().equals("Index: -1 out of bounds!")){
				System.out.println("Yay 6");			
			}
		}
		
	}
        

}