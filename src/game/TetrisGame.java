package game;

import board.Board;
// TetrisGame.java
// Sophey Dong

/**
 * The TetrisGame class maintains a Tetris game.
 * 
 * @author Sophey
 *
 */
public class TetrisGame {

	static int CCW = 0;
	static int CW = 1;
	static int DOWN = 2;
	static int LEFT = 3;
	static int RIGHT = 4;
	private Board board;

	/**
	 * Constructor. Initiates variables.
	 */
	public TetrisGame() {
		board = new Board();
	}

	/**
	 * Try to move the current piece with RIGHT, LEFT, DOWN, CW, CCW
	 */
	public void attemptMove(int moveType) {
		switch (moveType) {
		case 0:
			board.rotateCCW();
			break;
		case 1:
			board.rotateCW();
			break;
		case 2:
			board.goDown();
			break;
		case 3:
			board.goLeft();
			break;
		case 4:
			board.goRight();
			break;
		default:
			break;
		}
	}

	/**
	 * Returns the board
	 * 
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}

}
