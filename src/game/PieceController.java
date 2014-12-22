package game;
// PieceController.java
// Sophey Dong

// awt
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


// swing
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import board.BoardView;

/**
 * Class that controls the pieces using KeyListener
 * 
 * @author Sophey
 *
 */
public class PieceController extends JPanel implements KeyListener {

	private TetrisGame game;
	static int DEFAULT_DROP_RATE = 1300; // default timer time
	private int dropRate;
	private Timer timer;
	private JLabel tetrisLabel; // holds the number of lines cleared
	private JLabel linesLabel; // holds the number of tetrises cleared
	private JLabel scoreLabel; // holds the score
	private JLabel levelLabel; // holds the level
	private BoardView view;

	/**
	 * Constructor for PieceController, passes in a dropRate for the time
	 * between each drop
	 * 
	 * @param dropRate
	 */
	public PieceController(int dropRate) {
		super();
		this.dropRate = dropRate;
		game = new TetrisGame(); // create a tetris game
		createView();
		createScore();
		add(view);
		setFocusable(true);
		addKeyListener(this);
		setupTimer();
	}

	/**
	 * Default constructor, uses the DEFAULT_DROP_RATE as the timer time
	 */
	public PieceController() {
		this(DEFAULT_DROP_RATE);
	}

	/**
	 * Refreshes the score for the labels
	 */
	private void refreshScore() {
		int numTetris = game.getBoard().getNumTetrises();
		int numLine = game.getBoard().getNumLines();
		int score = numTetris * 5 + numLine;
		int level = score / 10 + 1;
		tetrisLabel.setText("Tetrises: " + numTetris + "\t\t\t");
		linesLabel.setText("Lines: " + numLine);
		scoreLabel.setText("Score: " + score);
		levelLabel.setText("Level: " + level);
		
		// set dropRate lower per 10 points and displays level
		dropRate = DEFAULT_DROP_RATE - (level - 1) * 100;
	}

	/**
	 * Creates the scoreboard
	 */
	private void createScore() {
		// sets up a separate JPanel to hold the 2 score labels
		JPanel labels = new JPanel(new GridLayout(2, 2));

		// initiates the labels
		tetrisLabel = new JLabel();
		linesLabel = new JLabel();
		scoreLabel = new JLabel();
		levelLabel = new JLabel();
		
		// adds the labels to the panel
		labels.add(tetrisLabel);
		labels.add(linesLabel);
		labels.add(scoreLabel);
		labels.add(levelLabel);
		
		// sets text on labels
		refreshScore();

		// adds the labels panel to the main panel
		add(labels);
	}

	/**
	 * Initiates the view
	 */
	private void createView() {
		view = new BoardView(game.getBoard());
	}

	/*
	 * Repaints the display and refreshes the scoreboard
	 */
	public void refreshDisplay() {
		view.repaint();
		refreshScore();
	}

	/**
	 * Sets the timer
	 */
	private void setupTimer() {
		// uses the dropRate and drops every dropRate
		timer = new Timer(dropRate, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.attemptMove(TetrisGame.DOWN);
				refreshDisplay();
			}
		});
		timer.start();
	}

	/**
	 * When a key is pressed, a move is tried
	 */
	public void keyPressed(KeyEvent e) {
		moveEntered(e.getKeyCode());
	}

	/**
	 * attempts the move according to what key is pressed righ arrow: right left
	 * arrow: left down arrow: down up arrow: cw z: cw x: ccw
	 * 
	 * @param move
	 */
	private void moveEntered(int move) {
		switch (move) {
		case KeyEvent.VK_LEFT:
			game.attemptMove(TetrisGame.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			game.attemptMove(TetrisGame.RIGHT);
			break;
		case KeyEvent.VK_DOWN:
			game.attemptMove(TetrisGame.DOWN);
			break;
		case KeyEvent.VK_UP:
			game.attemptMove(TetrisGame.CW);
			break;
		case KeyEvent.VK_Z:
			game.attemptMove(TetrisGame.CW);
			break;
		case KeyEvent.VK_X:
			game.attemptMove(TetrisGame.CCW);
			break;
		}
		refreshDisplay();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
