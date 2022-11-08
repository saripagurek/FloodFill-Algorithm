package floodfillfiles;

public class FloodFill {
	/**
	 * Print a 2-dimensional array using least 3 spaces for each value.
	 * Values for each row of the array appear on a single line, and each row
	 * appears on its own line.
	 * 
	 * @param arr a two-dimensional array
	 * @throws IllegalArgumentException if the specified array is empty
	 */
	public static void printArray(int[][] arr) throws Exception {
		testArrayNotEmpty(arr);
		int rows = numRows(arr);
		int cols = numCols(arr);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = arr[r][c];
				System.out.printf("%3d", val);
			}
			System.out.println();
		}
	}
	
	
	public static int numRows(int[][] arr) {
		return arr.length;
	}
	
	public static int numCols(int[][] arr) {
		if (numRows(arr) == 0) {
			return 0;
		}
		return arr[0].length;
	}
	
	public static void testArrayNotEmpty(int[][] arr) throws Exception {
		int rows = numRows(arr);
		if (rows <= 0) {
			throw new Exception("rows <= 0");
		}
		int cols = numCols(arr);
		if (cols <= 0) {
			throw new Exception("cols <= 0");
		}
	}
	
	public static boolean isValidIndex(Index2 args, int[][] arr) {
		if (args.row >= 0 && args.row < numRows(arr) && args.col >= 0 && args.col < numCols(arr)) {
			return true;
		}
		return false;
	}
	
	public static void floodFill(Index2 args, int[][] arr, int target, int repl) throws Exception {
		testArrayNotEmpty(arr);
		if (isValidIndex(args, arr) == false) {
			throw new Exception("index is not valid");
		}
		if (arr[args.row][args.col] != target) {
			return;
		}
		
		arr[args.row][args.col] = repl;
		FloodFillUtil.draw(arr);
		
		Index2 next = new Index2(args.row - 1, args.col);
		if (isValidIndex(next, arr)) {
			floodFill(next, arr, target, repl);
		}
		
		Index2 next1 = new Index2(args.row + 1, args.col);
		if (isValidIndex(next1, arr)) {
			floodFill(next1, arr, target, repl);
		}
		
		Index2 next2 = new Index2(args.row, args.col - 1);
		if (isValidIndex(next2, arr)) {
			floodFill(next2, arr, target, repl);
		}
		
		Index2 next3 = new Index2(args.row, args.col + 1);
		if (isValidIndex(next3, arr)) {
			floodFill(next3, arr, target, repl);
		}
	}
	
	public static void init(int[][] arr, int border, int interior) throws Exception {
		testArrayNotEmpty(arr);
	    int rows = numRows(arr);
	    int cols = numCols(arr);
	    
	    for (int i = 0; i < cols; i++) {
	    	arr[0][i] = border;
	    	arr[rows - 1][i] = border;
	    	}
	    
	    for (int j = 0; j < cols; j++) {
	    	arr[j][0] = border;
	    	arr[j][cols - 1] = border;
	    	}
	    
	    for (int r = 1; r < rows; r++) {
	    	for (int k = 1; k < cols; k++) {
	    		arr[r][k] = interior;
	    		}
	    	}
	}
	
	/**
	 * Flood fills an array printing the array before and after filling, and drawing
	 * an image of the array as it is filled.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) throws Exception {

		final int ROWS = 9;
		final int COLS = 9;
		int[][] arr = new int[ROWS][COLS];
		final int BORDER = 0;
		final int INTERIOR = 1;
		final int REPLACEMENT = 6;
		init(arr, BORDER, INTERIOR);
		
		System.out.println("number of rows = " + FloodFill.numRows(arr));
		System.out.println("number of columns = " + FloodFill.numCols(arr));
		FloodFill.testArrayNotEmpty(arr);
		System.out.println("index [1, 1] valid = " + FloodFill.isValidIndex(new Index2(1, 1), arr));
		

		try {
			// UNCOMMENT ONE OF THE FOLLOWING TWO LINES FOR DIFFERENT IMAGE
			// CHANGE FILENAME TO ANY ADDITIONAL TXT FILES ADDED
			
			arr = FloodFillUtil.readArray("face.txt"); 
			//arr = FloodFillUtil.readArray("random.txt"); 
		}
		catch (Exception x) {
			System.out.println(x.getMessage()); 
			System.exit(1); 
		}
		 

		printArray(arr);
		floodFill(new Index2(3, 3), arr, INTERIOR, REPLACEMENT);
		System.out.println();
		printArray(arr);

		// show an image of the stable configuration
		FloodFillUtil.draw(arr);
	}

}
