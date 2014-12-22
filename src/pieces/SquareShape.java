package pieces;

// SquareShape.java
// Sophey Dong

/**
 * Square tetrimino.
 * 
 * @author Sophey
 *
 */

public class SquareShape extends Piece {

	/**
	 * Constructor that sets the values in the 3d array to form a square
	 */
	public SquareShape() {
		piece = new boolean[][] { { false, true, true, false },
				{ false, true, true, false }, { false, false, false, false },
				{ false, false, false, false } };
		piecesArray = new boolean[][][] { piece };
	}

	/**
	 * does nothing, since squares don't change when rotated
	 */
	public void rotateClockwise() {
	}

	/**
	 * does nothing, since squares don't change when rotated
	 */
	public void rotateCClockwise() {
	}

}
