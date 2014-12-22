package pieces;

// SShape.java
// Sophey Dong

/**
 * S shaped tetrimino.
 * 
 * @author Sophey
 *
 */

public class SShape extends Piece {

	/**
	 * Constructor that sets the values in the 3d array to form an S and its
	 * rotations
	 */
	public SShape() {
		piece = new boolean[][] { { false, true, true, false },
				{ true, true, false, false }, { false, false, false, false },
				{ false, false, false, false } };
		boolean[][] piece2 = { { false, true, false, false },
				{ false, true, true, false }, { false, false, true, false },
				{ false, false, false, false } };
		piecesArray = new boolean[][][] { piece, piece2 };
	}

}
