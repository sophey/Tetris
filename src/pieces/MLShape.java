package pieces;

// MLShape.java
// Sophey Dong

/**
 * Mirrored L tetrimino.
 * 
 * @author Sophey
 *
 */

public class MLShape extends Piece {

	/**
	 * Constructor that sets the values in the 3d array to form a mirrored L and
	 * its rotations
	 */
	public MLShape() {
		piece = new boolean[][] { { false, false, true, false },
				{ false, false, true, false }, { false, true, true, false },
				{ false, false, false, false } };
		boolean[][] piece2 = { { false, false, false, false },
				{ false, true, false, false }, { false, true, true, true },
				{ false, false, false, false } };
		boolean[][] piece3 = { { false, true, true, false },
				{ false, true, false, false }, { false, true, false, false },
				{ false, false, false, false } };
		boolean[][] piece4 = { { false, false, false, false },
				{ false, true, true, true }, { false, false, false, true },
				{ false, false, false, false } };
		piecesArray = new boolean[][][] { piece, piece2, piece3, piece4 };
	}

}
