// TO DO: add your implementation and JavaDoc
/** Dynamic Grod class.
     * @author Edrees Porjosh
	 * @param <T> type.
     */
public class DynGrid310<T> {
	//underlying 2-d array for storage -- you MUST use this for credit!
	//Do NOT change the name or type
	/** The field storage.
     * @param DynArr310<T> array of storage.
     */
	private DynArr310<DynArr310<T>> storage;
	/** The field nrows.
     * @param int rows of storage
     */	
	private int nrows=0;
	/** The field ncols.
     * @param int cols of storage
     */	
	private int ncols=0;
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/** Creating/initializing storage.
     */	
	public DynGrid310(){
		storage=new DynArr310<>();
		// constructor
		// create an empty grid (no content)		
		// only use the parameterless constructor of DynArr310 to initialize 		
	}
	/** returns number of rows in storage.
	 * @return int that returns the storage rows.
	 */
	public int getNumRow() {
		// report number of rows with contents in the grid
		// Note: this might be different from the max number of rows 
		// 		 of the underlying storage.
		// O(1) 
		return nrows; //default return, remove or change as needed
		
	}
	/** returns number of cols in storage.
	 * @return int that returns the storage cols.
	 */
	public int getNumCol() { 
		// report number of columns with contents in the grid
		// Note: similarly, this might be different from the max number of columns 
		// 		 of the underlying storage.
		// O(1) 	
		return ncols;//default return, remove or change as needed
	}
	/** returns if the cells is valid.
	* @param row row.
	* @param col col.
	* @return true or false if cell is valid based on argument.
	*/
	public boolean isValidCell(int row, int col){
		boolean ji=false;
		if (row>=0&&col>=0){
			if(row<=nrows&&col<=ncols){
				if (row<storage.size()&&col<storage.get(row).size()){

					T ed= storage.get(row).get(col);
					if (ed!=null){
						ji=true;
					}
					if (ed==null){
						ji=false;
					}
				}
				
			}
		}
		
		return ji; //default return, remove or change as needed
    }
    
	/** returns cell at given index.
	 * @param row row.
	 * @param col col.
	 * @return the value at the given "coordinate".
	 */
	public T get(int row, int col){
		// report cell value at (row, col)
		if (isValidCell(row, col)==false){
			throw new IndexOutOfBoundsException("Index("+row+","+col+")");
		}
		T ed = storage.get(row).get(col);
		if (isValidCell(row, col)==true){
			ed=storage.get(row).get(col);	
		}
		// - Throw IndexOutOfBoundsException if any index is not valid
		// - Use this code to produce the correct error message for
		// the exception (do not use a different message):
		//	  "Index("+row+","+col+") out of bounds!"
		// O(1)
		return ed; //default return, remove or change as needed
	}
	/** returns and sets a value at given index.
	 * @param row row.
	 * @param col col.
	 * @param value value.
	 * @return and set value at the given "coordinate".
	 */
	public T set(int row, int col, T value) {
		// change cell value at (row, col) to be value, and return the old cell value
		if (value==null){
			throw new IllegalArgumentException("Null values not accepted");
		}
		if (isValidCell(row, col)==false){
			throw new IndexOutOfBoundsException("Index("+row+","+col+")");
		}
		T ed=storage.get(row).get(col);
		storage.get(row).set(col,value);
		// Use the exception (and error message) described in set()
		// for invalid indicies.
		// For valid indicies, if value is null, throw IllegalArgumentException. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Null values not accepted!"
		// O(1)
		return ed; //default return, remove or change as needed
	}
	/** returns true if row is added.
	 * @param index index
	 * @param newRow wanted row to add.
	 * @return if row was successfully added.
	 */
	public boolean addRow(int index, DynArr310<T> newRow){
		boolean ji=true;
		if (index<0){
			ji=false;
		}
		else if (newRow==null){
			ji=false;
		}
		else if (index>nrows){
			ji=false;
		}
		else{
			storage.insert(index,newRow);
			nrows+=1;
			ncols=newRow.size();
			
		}
		// insert newRow into the grid at index, shifting rows if needed
		// a new row can be appended 
		// return false if newRow can not be added correctly, e.g.
		// 	- invalid index
		//  - newRow is null or empty
		//	- the number of items in newRow does not match existing rows
		//
		// return true otherwise
		// 
		// O(R) 
		return ji;//default return, remove or change as needed
	}
	/** returns whether or not column was added correctly.
	 * @param index index.
	 * @param newCol desired column to be added.
	 * @return if column was added correctly.
	 */
	public boolean addCol(int index, DynArr310<T> newCol){
		if (index<0){
			return false;
		}
		else if (newCol==null){
			return false;
		}
		else if (newCol.size()<storage.size()){
			return false;
		}
		else{
			for (int i=0; i<storage.size();i++){	
				DynArr310<T> p=storage.get(i);
				T ed=newCol.get(i);
				p.insert(index, ed);
			}
			ncols+=1;
			return true;
		}
		// insert newCol as a new column into the grid at index, shifting cols if needed
		// a new column can be appended
		
		// return false if newCol can be added correctly, e.g.
		// 	- invalid index
		//  - newCol is null or empty
		//	- the number of items in newCol does not match existing columns
		//
		// return true otherwise

		// O(CR) where R is the number of rows and C is the number of columns of the grid
		//default return, remove or change as needed
	}
	/** returns whether or not row was removed correctly.
	 * @param index index.
	 * @return if row was removed correctly.
	 */
	public DynArr310<T> removeRow(int index){
		if (index<0){
			return null;
		}
		else if (index>=nrows){
			return null;
		}
		DynArr310<T> og=storage.remove(index);
		nrows-=1;
		int c=0;
		for (int i=0; i<storage.size(); i++){
			DynArr310<T> nr=storage.get(i);
			int er=nr.size();
			if (er>c){
				c=er;
			}

		}
		ncols=c;
		// remove and return a row at index, shift rows as needed to remove the gap		
		// return null for an invalid index				
		// O(R) where R is the number of rows of the grid	
		return og;//default return, remove or change as needed
	}
	/** returns whether or not col was removed correctly.
	 * @param index index.
	 * @return if col was removed correctly.
	 */
	public DynArr310<T> removeCol(int index){
		if (index<0){
			return null;
		}
		else if (index>=nrows){
			return null;
		}
		DynArr310<T> ogg= new DynArr310<>();
		for (int i=0; i<nrows;i++){
			DynArr310<T> er=storage.get(i);
			if (index<er.size()){
				T adv=er.remove(index);
				ogg.add(adv);
			}
		}
		ncols-=1;
		if (ncols==0){
			nrows=0;
		}
		// remove and return a column at index, shift cols as needed to remove the gap
		// return null for an invalid index
		//
		// O(RC) where R is the number of rows and C is the number of columns 
		return ogg;//default return, remove or change as needed
	}
	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************
	
	/** Dynamic Grid toString.
	 * Provided toString.
	 */
	@Override
	public String toString(){
		if(getNumRow() == 0 || getNumCol() == 0 ){ return "empty board"; }
    	StringBuilder sb = new StringBuilder();
    	for(int i=0; i<getNumRow(); i++){
            sb.append("|");
    		for (int j=0;j<getNumCol(); j++){
      			sb.append(get(i,j).toString());
      		    sb.append("|");
      		}
      		sb.append("\n");
    	}
    	return sb.toString().trim();

	}

	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************

	/** Dynamic Grid Main(testing).
	 * @param args are the parameters for debugging/testing.
	 */
	public static void main(String[] args){
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellend first step! But it does NOT guarantee your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!

		//create a grid of strings
		DynGrid310<String> sgrid = new DynGrid310<>();
		
		//prepare one row to add
		DynArr310<String> srow = new DynArr310<>();
		srow.add("English");
		srow.add("Spanish");
		srow.add("German");
		
		//addRow and checking
		if (sgrid.getNumRow() == 0 && sgrid.getNumCol() == 0 && !sgrid.addRow(1,srow)
			&& sgrid.addRow(0,srow) && sgrid.getNumRow() == 1 && sgrid.getNumCol() == 3){
			System.out.println("Yay 1");
		}
		
		//get, set, isValidCell
		if (sgrid.get(0,0).equals("English") && sgrid.set(0,1,"Espano").equals("Spanish") 
			&& sgrid.get(0,1).equals("Espano") && sgrid.isValidCell(0,0) 
			&& !sgrid.isValidCell(-1,0) && !sgrid.isValidCell(3,2)) {
			System.out.println("Yay 2");
		}

		//a grid of integers
		DynGrid310<Integer> igrid = new DynGrid310<Integer>();
		boolean ok = true;

		//add some rows (and implicitly some columns)
		for (int i=0; i<3; i++){
			DynArr310<Integer> irow = new DynArr310<>();
			irow.add((i+1) * 10);
			irow.add((i+1) * 11);
        
			ok = ok && igrid.addRow(igrid.getNumRow(),irow);
		}
		
		//toString
		//System.out.println(igrid);
		if (ok && igrid.toString().equals("|10|11|\n|20|22|\n|30|33|")){
			System.out.println("Yay 3");		
		}
				
		//prepare a column 
		DynArr310<Integer> icol = new DynArr310<>();
		
		//add two rows
		icol.add(-10);
		icol.add(-20);
		
		//attempt to add, should fail
		ok = igrid.addCol(1,icol);
		
		//expand column to three rows
		icol.add(-30);
		
		//addCol and checking
		if (!ok && !igrid.addCol(1,null) && igrid.addCol(1,icol) && igrid.getNumRow() == 3 && igrid.getNumCol() == 3){
			System.out.println("Yay 4");		
		}
		
		//System.out.println(igrid);
		
		//removeRow
		if (igrid.removeRow(5) == null && 
			igrid.removeRow(1).toString().equals("[20, -20, 22]") && 
			igrid.getNumRow() == 2 && igrid.getNumCol() == 3 ){
			System.out.println("Yay 5");	
		}
		
		//removeCol
		if (igrid.removeCol(0).toString().equals("[10, 30]") && 
			igrid.removeCol(1).toString().equals("[11, 33]") &&
			igrid.removeCol(0).toString().equals("[-10, -30]") &&
			igrid.getNumRow() == 0 && igrid.getNumCol() == 0 ){
			System.out.println("Yay 6");	
		}
		
				
	}
	
}