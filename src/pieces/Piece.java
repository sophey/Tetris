package pieces;
// Piece.java
// Sophey Dong

/**
 * Abstract class that is the superclass to all the tetriminos.
 * 
 * @author Sophey
 *
 */

public abstract class Piece {

	protected boolean[][] piece; // 4x4 array of booleans that holds the piece
	protected boolean[][][] piecesArray; // array of pieces holding the pieces
											// at different rotations
	protected int rotation; // current rotation number (0-3, 0-1, or 0,
							// depending on the shape)

	/**
	 * Constructor that sets the rotation to 0
	 */
	public Piece() {
		rotation = 0;
	}

	/**
	 * @return piece
	 */
	public boolean[][] getPiece() {
		return piece;
	}

	/**
	 * @return rotation
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * returns the piece at the inputed rotation
	 * 
	 * @param rot
	 * @return piece at the inputed rotation
	 */
	public boolean[][] getPieceAtRotation(int rot) {
		return piecesArray[rot];
	}

	/**
	 * rotates the piece clockwise
	 */
	public void rotateClockwise() {
		// if the current rotation is less than the last index, add one to the
		// rotation. otherwise reset to 0
		if (rotation < piecesArray.length - 1)
			rotation++;
		else
			rotation = 0;
		piece = piecesArray[rotation];
	}

	/**
	 * rotates the piece counter-clockwise
	 */
	public void rotateCClockwise() {
		// if the current rotation greater than 0, subtract one from the
		// rotation. otherwise reset to the length
		if (rotation > 0)
			rotation--;
		else
			rotation = piecesArray.length - 1;
		piece = piecesArray[rotation];
	}

}
