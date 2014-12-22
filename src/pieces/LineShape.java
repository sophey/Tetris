package pieces;

// LineShape.java
// Sophey Dong

/**
 * Line tetrimino.
 * 
 * @author Sophey
 *
 */

public class LineShape extends Piece {

	/**
	 * Constructor that sets the values in the 3d array to form a line and its
	 * rotations
	 */
	public LineShape() {
		piece = new boolean[][] { { true, true, true, true },
				{ false, false, false, false }, { false, false, false, false },
				{ false, false, false, false } };
		boolean[][] piece2 = { { false, true, false, false },
				{ false, true, false, false }, { false, true, false, false },
				{ false, true, false, false } };
		piecesArray = new boolean[][][] { piece, piece2 };
	}
}
