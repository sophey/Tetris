package pieces;

// TShape.java
// Sophey Dong

/**
 * T tetrimino.
 * 
 * @author Sophey
 *
 */

public class TShape extends Piece {

	/**
	 * Constructor that sets the values in the 3d array to form a T and its
	 * rotations
	 */
	public TShape() {
		piece = new boolean[][] { { false, true, false, false },
				{ true, true, true, false }, { false, false, false, false },
				{ false, false, false, false } };
		boolean[][] piece2 = { { false, true, false, false },
				{ false, true, true, false }, { false, true, false, false },
				{ false, false, false, false } };
		boolean[][] piece3 = { { false, false, false, false },
				{ true, true, true, false }, { false, true, false, false },
				{ false, false, false, false } };
		boolean[][] piece4 = { { false, true, false, false },
				{ true, true, false, false }, { false, true, false, false },
				{ false, false, false, false } };
		piecesArray = new boolean[][][] { piece, piece2, piece3, piece4 };
	}

}
