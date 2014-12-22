package pieces;

// ZShape.java
// Sophey Dong

/**
 * Z tetrimino.
 * 
 * @author Sophey
 *
 */

public class ZShape extends Piece {

	/**
	 * Constructor that sets the values in the 3d array to form a Z and its
	 * rotations
	 */
	public ZShape() {
		piece = new boolean[][] { { true, true, false, false },
				{ false, true, true, false }, { false, false, false, false },
				{ false, false, false, false } };
		boolean[][] piece2 = { { false, false, true, false },
				{ false, true, true, false }, { false, true, false, false },
				{ false, false, false, false } };
		piecesArray = new boolean[][][] { piece, piece2 };
	}

}
