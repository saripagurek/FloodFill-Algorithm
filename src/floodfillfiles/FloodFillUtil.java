package floodfillfiles;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import princeton.introcs.StdDraw;

public class FloodFillUtil {

	private static final Map<Integer, Color> COLOR_MAP = init();

	// SEE COLOUR CODES FOR TXT FILE BELOW:
	private static Map<Integer, Color> init() {
		Map<Integer, Color> m = new HashMap<>();
		m.put(0, Color.BLACK);
		m.put(1, Color.WHITE);
		m.put(3, Color.GRAY);
		m.put(4, Color.RED);
		m.put(5, Color.BLUE);
		m.put(6, Color.YELLOW);
		m.put(7, Color.GREEN);
		m.put(8, Color.MAGENTA);
		m.put(9, Color.CYAN);
		return m;
	}

	/**
	 * Draws an array using the array values as colors. Valid array values are from
	 * 0 up to and including 9. Any value outside of this range is treated as 0.
	 * 
	 * @param arr a non-empty two-dimensional array
	 * @throws RuntimeException if arr has no rows or no columns
	 */
	public static void draw(int[][] arr) {
		int rows = arr.length;
		int cols = arr[0].length;

		int max = Math.max(rows, cols);
		StdDraw.setScale(0.5, max + 0.5);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = arr[r][c];
				if (val < 0 || val > 9) {
					val = 0;
				}
				Color color = COLOR_MAP.get(val);
				StdDraw.setPenColor(color);
				StdDraw.filledSquare(c + 1, max - r, 0.5);
			}

		}
	}

	/**
	 * Reads a plain-text file consisting of lines of only digits returning a
	 * two-dimensional array containing the digits in the file. The number of rows
	 * in the returned array is equal to the number of lines in the file. The number
	 * of columns in the returned array is equal to the length of the longest line
	 * in the file. Values outside of the range 0 to 9 (inclusive) are set to zero
	 * in the array.
	 * 
	 * @param filename the filename of the file to read (assumed to be in the floodfillfiles
	 *                 directory of the project)
	 * @return a two-dimensional array containing the digits in the file
	 * @throws IOException      if the file cannot be read
	 * @throws RuntimeException if the number of rows or columns in the file is less
	 *                          than 1
	 */
	public static final int[][] readArray(String filename) throws IOException {
		Path path = FileSystems.getDefault().getPath("src", "floodfillfiles", filename);
		List<String> lines = Files.readAllLines(path);

		// number of rows
		int rows = lines.size();
		if (rows < 1) {
			throw new RuntimeException("rows < 1");
		}

		// number of columns
		int cols = 0;
		for (int r = 0; r < rows; r++) {
			String row = lines.get(r);
			if (row.length() > cols) {
				cols = row.length();
			}
		}
		if (cols < 1) {
			throw new RuntimeException("cols < 1");
		}

		// create and populate the array
		int[][] arr = new int[rows][cols];
		for (int r = 0; r < rows; r++) {
			String row = lines.get(r);
			for (int c = 0; c < row.length(); c++) {
				int val = row.charAt(c) - '0';
				if (val < 0 || val > 9) {
					val = 0;
				}
				arr[r][c] = val;
			}
		}
		return arr;
	}

}
