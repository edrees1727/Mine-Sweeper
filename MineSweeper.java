// TO DO: add your implementation and JavaDocs.

import java.util.Random;
/** MineSweeper class.
* @author Edrees Porjosh.
*/
public class MineSweeper{
    /** enum of the different levels.
     */
    public enum Level {
        /** tiny level.
        */
        TINY, 
        /** enum of the different levels.
        */
        EASY, 
        /** enum of the different levels.
        */
        MEDIUM, 
        /** enum of the different levels.
        */
        HARD, 
        /** enum of the different levels.
        */
        CUSTOM 
    }
    
    //each level has a different board size (number of rows/columns) 
    //and a different number of mines
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int ROWS_EASY = 9;
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int COLS_EASY = 9;
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int MINES_EASY = 10;
    /** various row or column with difficulty.
     * @param int int.
     */

    private static int ROWS_TINY = 5;
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int COLS_TINY = 5;
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int MINES_TINY = 3;
    /** various row or column with difficulty.
     * @param int int.
     */
    
    private static int ROWS_MEDIUM = 16;
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int COLS_MEDIUM = 16;
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int MINES_MEDIUM = 40;
    /** various row or column with difficulty.
     * @param int int.
     */

    private static int ROWS_HARD = 16;
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int COLS_HARD = 30;
    /** various row or column with difficulty.
     * @param int int.
     */
    private static int MINES_HARD = 99;

    /** board.
     * @param DynGrid310<Cell> type.
     */
    private DynGrid310<Cell> board;

    /** counter for rows.
     * @param int counter.
     */
    private int rowCount;
    
    //number of columns of the board
    /** counter for col.
     * @param int counter.
     */
    private int colCount;

    /** counter for mines.
     * @param int counter.
     */
    private int mineTotalCount;

    /** counter for number of cells clicked.
     * @param int counter.
     */
    private int clickedCount; 


    /** counter for number of flags that were "flagged".
     * @param int counter.
     */
    private int flaggedCount; 


    //game possible status
    /** enum of the different status you can have in your game.
     */
    public enum Status {
        /** enum of the different levels.
        */
        INIT, 
        /** enum of the different levels.
        */
        INGAME, 
        /** enum of the different levels.
        */
        EXPLODED, 
        /** enum of the different levels.
        */
        SOLVED
    }
    /** field representing status.
     * @param Status status.
     */
    private Status status; 

    /** The array of strings representing a type of status.
     * @param String int.
     */
    public final static String[] Status_STRINGS = {
        "INIT", "IN_GAME", "EXPLODED", "SOLVED"
    };
    
    
    //constructor
    // initialize game based on a provided seed for random numbers and 
    // the specified level
    /** constructor for minesweeper.
     * @param seed seed.
     * @param level level.
     */
    public MineSweeper(int seed, Level level){
    
    	
        if (level==Level.CUSTOM)
            throw new IllegalArgumentException("Customized games need more parameters!");
            
        //set number of rows, columns, mines based on the pre-defined levels
        switch(level){
            case TINY:
                rowCount = ROWS_TINY;
                colCount = COLS_TINY;
                mineTotalCount = MINES_TINY;
                break;
            case EASY:
                rowCount = ROWS_EASY;
                colCount = COLS_EASY;
                mineTotalCount = MINES_EASY;
                break;
            case MEDIUM:
                rowCount = ROWS_MEDIUM;
                colCount = COLS_MEDIUM;
                mineTotalCount = MINES_MEDIUM;
                break;
            case HARD:
                rowCount = ROWS_HARD;
                colCount = COLS_HARD;
                mineTotalCount = MINES_HARD;
                break;
            default:
                rowCount = ROWS_TINY;
                colCount = COLS_TINY;
                mineTotalCount = MINES_TINY;
		}
        
        //create an empty board of the needed size
        //TODO: you implement this method
        board = genEmptyBoard(rowCount, colCount);
        
        //place mines, and initialize cells
        //TODO: you implement part of this method
        initBoard(seed);
    }
    
    //constructor: should only be used for customized games
    /** constructor for minesweeper.
     * @param seed seed.
     * @param level level.
     * @param rowCount row count.
     * @param colCount column count.
     * @param mineCount mine count.
     */
    public MineSweeper(int seed, Level level, int rowCount, int colCount, int mineCount){
        
        if (level != Level.CUSTOM)
        	throw new IllegalArgumentException("Only customized games need more parameters!");
        
        //set number of rows/columns/mines
        //assume all numbers are valid (check MineGUI for additional checking code)	
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.mineTotalCount = mineCount;
        
        
        //create an empty board of the needed size: you implement this method
        board = genEmptyBoard(rowCount, colCount);
        
        //place mines, and initialize cells: you implement part of this method
       	initBoard(seed);
        
    }
    
    //method to initialize the game, including placing mines.
    //- assume it is invoked only after an empty board (rowCount x colCount) 
    //  has been created and set. check code above for examples.
        
    //TODO: you implement some important steps of this method
    /** the init board.
     * @param seed seed.
     */
    public void initBoard(int seed){
        
        //use seed to initialize a random number sequence
        Random random = new Random(seed);
        
        //randomly place mines on board
        int mineNum = 0;
        for ( ;mineNum<mineTotalCount;){
        
            int row = random.nextInt(rowCount);
            int col = random.nextInt(colCount);
            
             
            //cell already has a mine: try again
            if (hasMine(row, col)){
                continue;
            }
            
            //place mine
            board.get(row,col).setMine();
            mineNum++;
        }
        //System.out.println(board);
        
        //calculate nbr counts for each cell
        for (int row=0; row<rowCount; row++){
            for (int col=0; col<colCount; col++){
            
            
                int count = countNbrMines(row, col);
                board.get(row,col).setCount(count);
            }
        }
        
        //initialize other game settings   
        status = Status.INIT;
           
        flaggedCount = 0;
        clickedCount = 0;

    }
    /** returns rowCount.
     * @return rowCount
     */
    public int rowCount() { return rowCount; }
    /** returns colCount.
     * @return colCount
     */
    //report number of columns
    public int colCount() { return colCount; }
    /** returns if board is solved.
     * @return the status of solved
     */
    //report whether board is solved
    public boolean isSolved(){ return status == Status.SOLVED;    }
    /** returns if a mine exploded.
     * @return the status of exploded
     */
    //report whether a mine has exploded
    public boolean isExploded(){ return status == Status.EXPLODED; }

    /** toString for MineSweeper.
     * @return toString
	 */
    public String boardToString(){
        StringBuilder sb = new StringBuilder();
        
        //header of column indexes
        sb.append("- |");
        for (int j=0; j<board.getNumCol(); j++){
			sb.append(j +"|");
		}
        sb.append("\n");
        
    	for(int i=0; i<board.getNumRow(); i++){
            sb.append(i+" |");
    		for (int j=0;j<board.getNumCol(); j++){
      			sb.append(board.get(i,j).toString());
      		    sb.append("|");
      		}
      		sb.append("\n");
    	}
    	return sb.toString().trim();

    }
    
    //display the game status and board
    //use this for debugging
    /** MineSweeper toString.
	 * @return toString().
	 */
    @Override
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Board Size: " + rowCount() + " x " + colCount() + "\n");
        sb.append("Total mines: " + mineTotalCount + "\n");
        sb.append("Remaining mines: " + mineLeft() + "\n");
        sb.append("Game status: " + getStatus() + "\n");
        
        sb.append(boardToString());
        return sb.toString().trim();
    }

    /** returns whether a cell is flagged.
     * @param row row.
     * @param col col.
	 * @return boolean.
	 */
    public boolean isFlagged(int row, int col){
        if (!board.isValidCell(row,col)){
            return false;
        }
 
        Cell cell = board.get(row, col);
        return (cell.isFlagged());
    }
    /** returns if cell is not hidden.
     * @param row row
     * @param col col
	 * @return toString().
	 */
    public boolean isVisible(int row, int col){
    	
        if (!board.isValidCell(row,col)){
            return false;
        }
 
        Cell cell = board.get(row, col);
        return (cell.visible());               
    }
    /** returns if cell is has mine.
     * @param row row
     * @param col col
	 * @return true if has a mine false if it doesn't 
	 */
    public boolean hasMine(int row, int col){
        if (!board.isValidCell(row,col)){
            return false;
        }
 
        Cell cell = board.get(row, col);
        return (cell.hasMine());               
    }
    
    /** returns the number of mine associated with cell.
     * @param row row
     * @param col col
	 * @return the count of mines.
	 */
    public int getCount(int row, int col){
    	
        if (!board.isValidCell(row,col)){
            return -2;
        }
 
        Cell cell = board.get(row, col);
        return (cell.getCount());                    
    }
    /** returns how many mines left.
	 * @return how many mines left.
	 */
    public int mineLeft() { 
    	// report how many mines have not be flagged
    	return mineTotalCount-flaggedCount; 
    	
    }
    /** returns the status in String.
	 * @return the status in String
	 */
    public String getStatus() { 
    	// report current game status
    	return Status_STRINGS[status.ordinal()]; 
    	
    }


    //return the game board
    /** board getter.
	 * @return board
	 */
    public DynGrid310<Cell> getBoard(){ return board;}

	
    /** board setter.
     * @param newBoard newBoard
     * @param mineCount mineCount
	 */
    public void setBoard(DynGrid310<Cell> newBoard, int mineCount) {
		//set board
		this.board = newBoard;
		
		//set size
		rowCount = board.getNumRow();
		colCount = board.getNumCol();
		
		
	 	status = Status.INIT;
           
        flaggedCount = 0;
        clickedCount = 0;
        mineTotalCount = mineCount;
	}
    /** returns empty board.
     * @param rowNum rowNum
     * @param colNum colNum
	 * @return empty board
	 */
    public static DynGrid310<Cell> genEmptyBoard(int rowNum, int colNum){
        DynGrid310<Cell> em=new DynGrid310<>();
        if (rowNum<0||colNum<0){
            return null;
        }
        if (!(rowNum>=0)){
            return null;
        }
        if (!(colNum>=0)){
            return null;
        }
        for (int i=0; i<rowNum;i++){
            DynArr310<Cell> r= new DynArr310<>();
            for (int j=0; j<colNum;j++){
                r.add(new Cell());
            }
            int er=em.getNumRow();
            em.addRow(er,r);
        }

        
		
    	return em; //default return, remove or change as needed

    }
    
    /** count number of mines.
     * @param row row.
     * @param col col.
	 * @return number of mines.
	 */
    public int countNbrMines(int row, int col){
    	if (row<0){
            return -2;
        }
        else if (col<0){
            return -2;
        }
        else if (row>=rowCount()){
            return -2;
        }
        else if (col>=colCount()){
            return -2;
        }
        int nmine=0;
        if (board.get(row,col).hasMine()){
            return -1;
        }
        if (row-1>=0&&col+1<colCount()){
            if (board.get(row-1, col).hasMine()){
                nmine+=1;
            }
            if (board.get(row-1, col+1).hasMine()){
                nmine+=1;
            }
        }
        if (col-1>=0&&row+1<rowCount()){
            if (board.get(row+1, col-1).hasMine()){
                nmine+=1;
            }
            if (board.get(row, col-1).hasMine()){
                nmine+=1;
            }
        }
        if (col-1>=0&& row-1>=0){
            if (board.get(row-1, col-1).hasMine()){
                nmine+=1;
            }
        }
        if (col+1<colCount()){
            if (board.get(row, col+1).hasMine()){
                nmine+=1;
            }
        }
        if (row+1<rowCount()){
            if (board.get(row+1, col).hasMine()){
                nmine+=1;
            }
        }
        if (row+1<rowCount&&col+1<colCount()){
            if (board.get(row+1, col+1).hasMine()){
                nmine+=1;
            }
        }
        
    	return nmine; //default return, remove or change as needed
    }
    /** clickAt method, used recursion.
     * @param row row.
     * @param col col.
	 * @return number of mines whether was clicked
	 */
    public int clickAt(int row, int col){
        int nmine=0;
        if (row<0){
            return -2;
        }
        else if (col<0){
            return -2;
        }
        else if (row>=rowCount()){
            return -2;
        }
        else if (col>=colCount()){
            return -2;
        }
        if (board.get(row,col).isFlagged()||board.get(row,col).visible()){
            return -2;
        }
        if (board.get(row,col).hasMine()){
            status=Status.EXPLODED;
            board.get(row,col).setVisible();
            return -1;
        }
        else{
            if (board.isValidCell(row, col)){
                nmine=getCount(row, col);
            }
            clickedCount+=1;
            board.get(row,col).setVisible();
            status=Status.INGAME;
            
            if (nmine==0){
                if (row+1<rowCount()){
                    if (board.get(row+1,col).hasMine()){
                        status=Status.EXPLODED;
                        board.get(row+1,col).setVisible();
                    }
                    else{
                        board.get(row+1,col).setVisible();
                        System.out.println(board);
                        clickAt(row+1,col);
                    }
                }
                if (row-1>=0){
                    if (board.get(row-1, col).hasMine()){
                        status=Status.EXPLODED;
                        board.get(row-1, col).setVisible();
                    }
                    else{
                        board.get(row-1, col).setVisible();
                        System.out.println(board);
                        clickAt(row-1, col);
                    }
                }
                if (row-1>=0&&col+1<colCount()){
                    if (board.get(row-1, col+1).hasMine()){
                        status=Status.EXPLODED;
                        board.get(row-1, col+1).setVisible();
                    }
                    else{
                        board.get(row-1, col+1).setVisible();
                        System.out.println(board);
                        clickAt(row-1,col+1);
                    }
                }
                if (col+1<colCount()){
                    if (board.get(row, col+1).hasMine()){
                        status=Status.EXPLODED;
                        board.get(row, col+1).setVisible();
                    }
                    else{
                        board.get(row, col+1).setVisible();
                        System.out.println(board);
                        clickAt(row, col+1);
                    }
                }
                if (col+1<colCount()&&row+1<colCount()){
                    if (board.get(row+1,col+1).hasMine()){
                        status=Status.EXPLODED;
                        board.get(row+1, col+1).setVisible();
                    }
                    else{
                        board.get(row+1, col+1).setVisible();
                        System.out.println(board);
                        clickAt(row+1, col+1);
                    }
                }
                if (row+1<rowCount()&&col-1>=0){
                    if (board.get(row+1,col-1).hasMine()){
                        status=Status.EXPLODED;
                        board.get(row+1, col-1).setVisible();
                    }
                    else{
                        board.get(row+1, col-1).setVisible();
                        System.out.println(board);
                        clickAt(row+1,col-1);
                    }
                }
                if (col-1>=0){
                    if (board.get(row,col-1).hasMine()){
                        status=Status.EXPLODED;
                        board.get(row, col-1).setVisible();
                        
                    }
                    else{
                        board.get(row, col-1).setVisible();
                        System.out.println(board);
                        clickAt(row, col-1);
                    }
                }
                if (col-1>=0&&row-1>=0){
                    if (board.get(row-1,col-1).hasMine()){
                        status=Status.EXPLODED;
                        board.get(row-1, col-1).setVisible();
                    }
                    else{
                        board.get(row-1, col-1).setVisible();
                        System.out.println(board);
                        clickAt(row-1, col-1);
                    }
                }
            }
            if (flaggedCount==mineTotalCount){
                int total=rowCount()*colCount();
                if (clickedCount==total-mineTotalCount){
                    status=Status.SOLVED;
                }
            }
        }
        
        


    	// open cell located at (row,col)
    	// for a valid cell location:
    	//	- no change if cell is already flagged or exposed, return -2
    	//  - if cell has a mine, open it would explode the mine, 
    	//		update game status accordingly and return -1
    	//  - otherwise, open this cell and return number of mines adjacent to it
    	//		- if the cell is not adjacent to any mines (i.e. a zero-count cell), 
    	//			also open all zero-count cells that are connected to this cell, 
    	//			as well as all cells that are orthogonally or diagonally adjacent 
    	//			to those zero-count cells. 
    	//		- HINT: recursion can really help! Consider define private helper methods.
    	//  - update game status as needed
    	//	- update other game features as needed
    	//
    	// for an invalid cell location:
    	//	- no change and return -2
    	
    	return nmine; //default return, remove or change as needed

   	}

    /** if cell was flagged or not.
     * @param row row.
     * @param col col.
	 * @return if cell was flagged or not
	 */
    public boolean flagAt(int row, int col){
        if (row<0){
            return false;
        }
        if (board.get(row,col).visible()){
            return false;
        }
        else if (col<0){
            return false;
        }
        else if (row>=rowCount()){
            return false;
        }
        else if (col>=colCount()){
            return false;
        }
        

        if (board.get(row,col).isFlagged()==false){
            flaggedCount+=1;
            board.get(row,col).setFlagged();
        }
        if (flaggedCount==mineTotalCount){
            int total=rowCount()*colCount();
            if (clickedCount==total-mineTotalCount){
                status=Status.SOLVED;
            }
        }


    	
        
        return true; //default return, remove or change as needed
         
    }
    /** flagAt method returns if cell went from flagged to unflagged.
     * @param row row.
     * @param col col.
	 * @return if cell went from flagged to unflagged.
	 */
    public boolean unFlagAt(int row, int col){
    	if (row<0){
            return false;
        }
        if (board.get(row,col).visible()){
            return false;
        }
        else if (col<0){
            return false;
        }
        else if (row>=rowCount()){
            return false;
        }
        else if (col>=colCount()){
            return false;
        }
        if (board.get(row,col).isFlagged()){
            board.get(row, col).unFlagged();
            flaggedCount-=1;
            if (status==Status.SOLVED){
                status=Status.INGAME;
            }
            return true;
        }
        return false;


        
        //default return, remove or change as needed
      
    }

    
       

	

    /** MineSweeper main method.
	 * @param args are the parameters for debugging/testing.
	 */
    public static void main(String args[]){
    	
    	DynGrid310<Cell> myBoard = MineSweeper.genEmptyBoard(3,4);
    	
    	//board size, all 12 cells should be in the default state, no mines
    	if (myBoard.getNumRow() == 3 && myBoard.getNumCol()==4 &&
    		!myBoard.get(0,0).hasMine() && !myBoard.get(1,3).visible() &&
    		!myBoard.get(2,2).isFlagged() && myBoard.get(2,1).getCount()==-1){
    		System.out.println("Yay 0");
            
    	}

		Random random = new Random(10);
        MineSweeper game = new MineSweeper(random.nextInt(),Level.TINY);
        
          
        
        if (game.countNbrMines(0,0) == 0 && game.countNbrMines(4,2) == 1 &&
        	game.countNbrMines(3,3) == 3 &&	game.countNbrMines(2,3) == -1 &&
        	game.countNbrMines(5,5) == -2){
        	System.out.println("Yay 1");
        }
        
        //first click at (3,3)
        if (game.clickAt(-1,0) == -2 && game.clickAt(3,3) == 3 &&
        	game.isVisible(3,3) && !game.isVisible(0,0) && 
        	game.getStatus().equals("IN_GAME") && game.mineLeft() == 3){
        	System.out.println("Yay 2");
        }
        
        if (game.clickAt(2,3) == -1 && game.isVisible(2,3) &&
        	game.getStatus().equals("EXPLODED") ){
        	System.out.println("Yay 3");
        }
        

		//start over with the same board
		random = new Random(10);
        game = new MineSweeper(random.nextInt(),Level.TINY);
        game.clickAt(3,3);
        
        //flag and unflag
        if (game.flagAt(2,3) && !game.isVisible(2,3)  &&
        	game.isFlagged(2,3) 
            && game.flagAt(2,4) && 
        	game.mineLeft() == 1 
            && game.unFlagAt(2,3) &&
        	!game.isFlagged(2,3) && game.mineLeft() == 2){
        	System.out.println("Yay 4");
        }
        
        
		if (game.clickAt(2,4) == -2 && game.flagAt(2,4) &&
			!game.flagAt(3,3) && !game.unFlagAt(3,3) &&
			!game.unFlagAt(2,3)){
        	System.out.println("Yay 5");
        }

		//clicking on a zero-count cell
		if (game.clickAt(0,0) == 0 && game.isVisible(0,0) && game.isVisible(4,0) &&  game.isVisible(0,4) && game.isVisible(3,2) && !game.isVisible(3,4) && !game.isVisible(4,3)){
        	System.out.println("Yay 6");
        }
        System.out.println(game);
       
		
		//open all none-mine cells without any explosion solve the game!
		if (game.clickAt(4,4) == 1 && game.clickAt(3,4) == 3 && 
			game.getStatus().equals("SOLVED")){
        	System.out.println("Yay 7");
        }
		//System.out.println(game);
		//expected board:
		//- |0|1|2|3|4|
		//0 | | | | | |
		//1 | | |1|2|2|
		//2 | | |1|?|F|
		//3 | | |2|3|3|
		//4 | | |1|?|1|
    } 

}