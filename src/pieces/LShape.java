package pieces;

// LShape.java
// Sophey Dong

/**
 * L tetrimino.
 * 
 * @author Sophey
 *
 */

public class LShape extends Piece {

	/**
	 * Constructor that sets the values in the 3d array to form an L and its
	 * rotations
	 */
	public LShape() {
		piece = new boolean[][] { { false, true, false, false },
				{ false, true, false, false }, { false, true, true, false },
				{ false, false, false, false } };
		boolean[][] piece2 = { { false, false, false, false },
				{ true, true, true, false }, { true, false, false, false },
				{ false, false, false, false } };
		boolean[][] piece3 = { { false, true, true, false },
				{ false, false, true, false }, { false, false, true, false },
				{ false, false, false, false } };
		boolean[][] piece4 = { { false, false, false, false },
				{ false, false, false, true }, { false, true, true, true },
				{ false, false, false, false } };
		piecesArray = new boolean[][][] { piece, piece2, piece3, piece4 };
	}

}
