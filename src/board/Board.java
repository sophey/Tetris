package board;
import pieces.*;

// Board.java
// Sophey Dong

/**
 * Class that holds a 2D array which stores which cells are full.
 * 
 * @author Sophey
 *
 */

public class Board {

	private boolean[][] board; // stores which cells are full
	private Piece currentPiece; // stores the current piece being controlled
	private int[] currentPosition; // stores the upper left hand position of the
									// current piece
	private static int NUM_COLS = 10; // static number of columns the board has
	private static int NUM_ROWS = 18; // static number of rows the board has
	private int tetrises; // stores the number of tetrises the player has
							// cleared
	private int lines; // stores the number of lines the player has cleared

	/**
	 * Constructor, initiates the board
	 */
	public Board() {
		initBoard();
	}

	/**
	 * Checks if the line is full to clear
	 * 
	 * @param lineNum
	 * @return true for full, false for not full
	 */
	public boolean checkLine(int lineNum) {
		boolean[] check = board[lineNum];
		for (boolean b : check) {
			if (!b) // returns false if there is an empty space
				return false;
		}
		return true;
	}

	/**
	 * Clears the board
	 */
	public void clearBoard() {
		board = new boolean[NUM_ROWS][NUM_COLS];
	}

	/**
	 * Clears the line that is passed through
	 * 
	 * @param lineNum
	 */
	public void clearLine(int lineNum) {
		// creates new empty board
		boolean[][] newBoard = new boolean[NUM_ROWS][NUM_COLS];
		int index = NUM_ROWS - 1;

		// copies lines from old board from bottom to line being cleared
		for (int i = NUM_ROWS - 1; i > lineNum; i--) {
			newBoard[index] = board[i];
			index--;
		}

		// skips the line being cleared and copies lines above into new board
		for (int j = lineNum - 1; j >= 0; j--) {
			newBoard[index] = board[j];
			index--;
		}

		// makes the board newBoards
		board = newBoard;
	}

	/**
	 * Deletes the piece from the previous position
	 */
	public void deletePos() {
		boolean[][] piece = currentPiece.getPiece(); // boolean array of the
														// piece
		int x = currentPosition[0];
		int y = currentPosition[1];

		// changes the trues into falses from the previous position
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (piece[row][col]) {
					int newX = x + row;
					int newY = y + col;
					if (newX < NUM_ROWS && newX >= 0 && newY < NUM_COLS
							&& newY >= 0)
						board[newX][newY] = false;
				}
			}
		}
	}

	/**
	 * Checks if placing the piece at grid position (row, col) would cause a
	 * collision (i.e., if there would be a block on an already-filled grid
	 * square).
	 * 
	 * @param piece
	 * @param row
	 * @param col
	 * @return true for collision, false for no collision
	 */
	private boolean detectCollision(Piece piece, int row, int col) {
		boolean[][] p = piece.getPiece(); // boolean array of the piece

		// checks each part of the piece array for true values
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				int newX = r + row;
				int newY = c + col;
				// checks if the position being checked is out of bounds
				if ((newX < NUM_ROWS && newX >= 0 && newY < NUM_COLS && newY >= 0))
					if (p[r][c] && board[newX][newY]) // if piece collides,
														// return true
						return true;
			}
		}
		return false;
	}

	/**
	 * Returns the number of lines cleared
	 * 
	 * @return lines
	 */
	public int getNumLines() {
		return lines;
	}

	/**
	 * Returns the number of tetrises cleared
	 * 
	 * @return tetrises
	 */
	public int getNumTetrises() {
		return tetrises;
	}

	/**
	 * returns the 2D boolean array
	 * 
	 * @return board
	 */
	public boolean[][] getBoard() {
		return board;
	}

	/**
	 * returns the current grid position in a 2D array
	 * 
	 * @return currentPosition
	 */
	public int[] getCurrentGridPosition() {
		return currentPosition;
	}

	/**
	 * returns the current piece
	 * 
	 * @return currentPiece
	 */
	public Piece getCurrentPiece() {
		return currentPiece;
	}

	/**
	 * returns the number of columns on the board
	 * 
	 * @return NUM_COLS
	 */
	public int getNumCols() {
		return NUM_COLS;
	}

	/**
	 * returns the number of rows on the board
	 * 
	 * @return NUM_ROWS
	 */
	public int getNumRows() {
		return NUM_ROWS;
	}

	/**
	 * makes the current tetrimino go down, if possible
	 * 
	 * @return true for down, false for no down
	 */
	public boolean goDown() {
		deletePos(); // deletes the previous position from the board

		// if the piece collides, undo the move, put a new piece on the board,
		// and return false
		if (detectCollision(currentPiece, currentPosition[0] + 1,
				currentPosition[1])) {
			updateBoard();
			newPiece();
			return false;
		}

		// try updating the board, if the piece is out of bounds,
		// undo the move and put a new piece on the board
		try {
			currentPosition[0] += 1;
			updateBoard();
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			deletePos();
			currentPosition[0] -= 1;
			updateBoard();
			newPiece();
			return false;
		}
	}

	/**
	 * makes the current tetrimino go left, if possible
	 * 
	 * @return true for left, false for no right
	 */
	public boolean goLeft() {
		deletePos(); // deletes the previous position from the board

		// if the piece collides, undo the move and return false
		if (detectCollision(currentPiece, currentPosition[0],
				currentPosition[1] - 1)) {
			updateBoard();
			return false;
		}

		// try updating the board, if the piece is out of bounds, undo
		try {
			currentPosition[1] -= 1;
			updateBoard();
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			deletePos();
			currentPosition[1] += 1;
			updateBoard();
			return false;
		}
	}

	/**
	 * makes the current tetrimino go right, if possible
	 * 
	 * @return true for right, false for no right
	 */
	public boolean goRight() {
		deletePos(); // deletes the previous position from the board

		// if the piece collides, undo the move and return false
		if (detectCollision(currentPiece, currentPosition[0],
				currentPosition[1] + 1)) {
			updateBoard();
			return false;
		}

		// try updating the board, if the piece is out of bounds, undo
		try {
			currentPosition[1] += 1;
			updateBoard();
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			deletePos();
			currentPosition[1] -= 1;
			updateBoard();
			return false;
		}
	}

	/**
	 * rotates the current tetrimino counter-clockwise, if possible
	 * 
	 * @return true for rotate, false for no rotate
	 */
	public boolean rotateCCW() {
		deletePos(); // deletes the previous rotation from the board
		currentPiece.rotateCClockwise(); // rotates the current piece

		// if the piece collides, undo the rotation and return false
		if (detectCollision(currentPiece, currentPosition[0],
				currentPosition[1])) {
			currentPiece.rotateClockwise();
			return false;
		}

		// try updating the board, if the piece is out of bounds, undo
		try {
			updateBoard();
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			deletePos();
			currentPiece.rotateClockwise();
			updateBoard();
			return false;
		}
	}

	/**
	 * rotates the current tetrimino clockwise, if possible
	 * 
	 * @return true for rotate, false for no rotate
	 */
	public boolean rotateCW() {
		deletePos(); // deletes the previous rotation from the board
		currentPiece.rotateClockwise(); // rotates the current piece

		// if the piece collides, undo the rotation and return false
		if (detectCollision(currentPiece, currentPosition[0],
				currentPosition[1])) {
			currentPiece.rotateCClockwise();
			return false;
		}

		// try updating the board, if the piece is out of bounds, undo
		try {
			updateBoard();
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			deletePos();
			currentPiece.rotateCClockwise();
			updateBoard();
			return false;
		}
	}

	/**
	 * Initiates board
	 */
	public void initBoard() {
		board = new boolean[NUM_ROWS][NUM_COLS]; // sets the board to all false

		// sets point values to 0
		tetrises = 0;
		lines = 0;
		currentPosition = new int[] { 0, 3 }; // sets the initial position
		newPiece(); // adds a new piece to the board
	}

	/**
	 * Update the board array to reflect the piece's filled squares using the
	 * currentGridPosition values and the currentPiece's rotation value.
	 */
	public void updateBoard() {
		boolean[][] piece = currentPiece.getPiece();
		int x = currentPosition[0];
		int y = currentPosition[1];

		// goes thru the piece array, updates the board depending on the current
		// position and the filled piece array
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (piece[row][col])
					board[x + row][y + col] = true;
			}
		}
	}

	/**
	 * Checks the lines where the piece just landed for completed lines, clears
	 * if needed
	 */
	public void checkLines() {
		int currentRow = currentPosition[0]; // first row to check, top of where
												// piece lands
		int linesCleared = 0; // counts the number of lines cleared (max 4)

		// checks 4 lines below the initial for fullness
		for (int i = 0; i < 4; i++) {
			int row = currentRow + i;
			if (row < NUM_ROWS) { // checks if the line is within range
				// if line is full, clear line and add to the count
				if (checkLine(currentRow + i)) {
					linesCleared += 1;
					clearLine(currentRow + i);
				}
			}
		}

		if (linesCleared == 4) // add to tetris count if tetris
			tetrises += 1;
		else
			lines += linesCleared;
	}

	/**
	 * Puts a new random piece onto the board
	 */
	public void newPiece() {
		checkLines(); // checks if there are lines to be cleared before putting
						// a new piece on the boards

		int randNum = (int) (Math.random() * 7); // random number (0-6)

		// assigns a random piece to the currentPiece
		switch (randNum) {
		case 0:
			currentPiece = new LineShape();
			break;
		case 1:
			currentPiece = new LShape();
			break;
		case 2:
			currentPiece = new MLShape();
			break;
		case 3:
			currentPiece = new SquareShape();
			break;
		case 4:
			currentPiece = new SShape();
			break;
		case 5:
			currentPiece = new TShape();
			break;
		case 6:
			currentPiece = new ZShape();
			break;
		default:
			currentPiece = new LineShape();
			break;
		}
		// sets the piece's current position to the standard starting position
		currentPosition = new int[] { 0, 3 };

		// displays the piece on the board
		updateBoard();

	}

}
