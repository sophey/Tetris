package board;

// BoardView.java
// Sophey Dong

// awt
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

// swing
import javax.swing.JComponent;

/**
 * Sets the view for the board
 * 
 * @author Sophey
 *
 */
public class BoardView extends JComponent {

    private Board board;

    /**
     * Constructor for the class that takes in a board as a parameter
     * 
     * @param b
     */
    public BoardView(Board b) {
	super();
	board = b;
	setPreferredSize(new Dimension(350, 750));
	setVisible(true);
    }

    /**
     * Computes the block size for the grid depending on the size of the
     * JComponent
     * 
     * @return width of block
     */
    private int computeBlockSize() {
	// gets the max block height and width
	int x = getWidth() / board.getNumCols();
	int y = getHeight() / board.getNumRows();

	// returns the minimum square side length
	return Math.min(x, y);
    }

    /**
     * Overrides default paint method to paint the board
     */
    public void paint(Graphics g) {
	int blockSize = computeBlockSize();
	paintBoardOutline(g, blockSize); // paints the board outline
	boolean[][] b = board.getBoard();

	// paints the blocks depending on the true values in the board
	for (int i = 0; i < board.getNumRows(); i++)
	    for (int j = 0; j < board.getNumCols(); j++)
		if (b[i][j])
		    paintBlock(g, j, i, blockSize);
    }

    /**
     * Paints each block if filled, takes in which row and column the block is
     * in and the blockSize
     * 
     * @param g
     * @param row
     * @param col
     * @param blockSize
     */
    private void paintBlock(Graphics g, int row, int col, int blockSize) {
	// changes the row and col values to the x and y values
	row *= blockSize;
	col *= blockSize;

	// filled in orange block
	g.setColor(Color.ORANGE);
	g.fillRect(row, col, blockSize, blockSize);

	// set the border of the block to black
	g.setColor(Color.BLACK);
	g.drawRect(row, col, blockSize, blockSize);

    }

    /**
     * Paints the grid, takes in the blockSize
     * 
     * @param g
     * @param blockSize
     */
    private void paintBoardOutline(Graphics g, int blockSize) {
	// starts out at 0 values for row and column
	int rowIncrement = 0;
	int colIncrement = 0;

	// where the lines stop
	int width = blockSize * board.getNumCols();
	int height = blockSize * board.getNumRows();

	// sets the line colors to black
	g.setColor(Color.BLACK);

	// draws the grids
	for (int i = 0; i < board.getNumRows(); i++) {
	    g.drawLine(0, rowIncrement, width, rowIncrement);
	    rowIncrement += blockSize;
	}
	for (int j = 0; j <= board.getNumCols(); j++) {
	    g.drawLine(colIncrement, 0, colIncrement, height);
	    colIncrement += blockSize;
	}
    }
}
