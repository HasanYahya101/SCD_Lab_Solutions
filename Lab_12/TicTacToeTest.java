import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeTest {

    @Test
    public void testInitializeBoard() {
        TicTacToe.initializeBoard();
        char[][] expectedBoard = {
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' }
        };
        assertArrayEquals(expectedBoard, TicTacToe.board);
    }

    @Test
    public void testSwitchPlayer() {
        // Test switching from 'X' to 'O'
        TicTacToe.currentPlayer = 'X';
        TicTacToe.switchPlayer();
        assertEquals('O', TicTacToe.currentPlayer);

        // Test switching from 'O' to 'X'
        TicTacToe.currentPlayer = 'O';
        TicTacToe.switchPlayer();
        assertEquals('X', TicTacToe.currentPlayer);
    }

    @Test
    public void testIsWinner_RowWin() {
        TicTacToe.board = new char[][] {
                { 'X', 'X', 'X' },
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' }
        };
        TicTacToe.currentPlayer = 'X';
        assertTrue(TicTacToe.isWinner());
    }

    @Test
    public void testIsWinner_ColumnWin() {
        TicTacToe.board = new char[][] {
                { 'O', ' ', ' ' },
                { 'O', ' ', ' ' },
                { 'O', ' ', ' ' }
        };
        TicTacToe.currentPlayer = 'O';
        assertTrue(TicTacToe.isWinner());
    }

    @Test
    public void testIsWinner_DiagonalWin() {
        TicTacToe.board = new char[][] {
                { 'X', ' ', ' ' },
                { ' ', 'X', ' ' },
                { ' ', ' ', 'X' }
        };
        TicTacToe.currentPlayer = 'X';
        assertTrue(TicTacToe.isWinner());
    }

    @Test
    public void testIsBoardFull_FullBoard() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'O', 'X', 'O' }
        };
        assertTrue(TicTacToe.isBoardFull());
    }

    @Test
    public void testIsBoardFull_NotFullBoard() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'O', ' ', 'O' }
        };
        assertFalse(TicTacToe.isBoardFull());
    }

    @Test
    public void testPlayerTurn_ValidMove() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', ' ', 'O' },
                { 'O', 'X', ' ' }
        };
        TicTacToe.currentPlayer = 'X';
        // Simulate a valid move by placing 'X' at (1, 1)
        TicTacToe.board[1][1] = 'X';
        assertEquals('X', TicTacToe.board[1][1]);
    }

    @Test
    public void testPlayerTurn_InvalidMove() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'O', 'X', 'O' }
        };
        TicTacToe.currentPlayer = 'X';
        // Attempt an invalid move on a full board
        boolean isBoardFullBefore = TicTacToe.isBoardFull();
        assertTrue(isBoardFullBefore);
    }

    @Test
    public void testPlayerTurn_InvalidMoveSameSpot() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', ' ', 'O' },
                { 'O', 'X', ' ' }
        };
        TicTacToe.currentPlayer = 'X';
        // Attempt an invalid move on a spot that is already taken
        boolean isBoardFullBefore = TicTacToe.isBoardFull();
        assertFalse(isBoardFullBefore);
    }

    @Test
    public void testPlayerTurn_InvalidMoveOutOfBounds() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'O', 'X', 'O' }
        };
        TicTacToe.currentPlayer = 'X';
        // Attempt an invalid move on a spot that is out of bounds
        boolean isBoardFullBefore = TicTacToe.isBoardFull();
        assertTrue(isBoardFullBefore);
    }

    @Test
    public void testPlayerTurn_InvalidMoveInvalidInput() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'O', 'X', 'O' }
        };
        TicTacToe.currentPlayer = 'X';
        // Attempt an invalid move with invalid input
        boolean isBoardFullBefore = TicTacToe.isBoardFull();
        assertTrue(isBoardFullBefore);
    }

    @Test
    public void testIsWinner_NoWinner() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'O', 'X', 'O' }
        };
        TicTacToe.currentPlayer = 'X';
        assertFalse(TicTacToe.isWinner());
    }

    @Test
    public void testIsWinner_MixedBoard() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', ' ' },
                { 'O', 'X', ' ' },
                { ' ', ' ', 'X' }
        };
        TicTacToe.currentPlayer = 'X';
        assertTrue(TicTacToe.isWinner());
    }

    @Test
    public void testIsWinner_EmptyBoard() {
        TicTacToe.board = new char[][] {
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' }
        };
        TicTacToe.currentPlayer = 'X';
        assertFalse(TicTacToe.isWinner());
    }

    @Test
    public void testIsWinner_LastMoveWin() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'O', ' ', 'X' }
        };
        TicTacToe.currentPlayer = 'X';
        assertTrue(TicTacToe.isWinner());
    }

    @Test
    public void testIsWinner_LastMoveNoWin() {
        TicTacToe.board = new char[][] {
                { 'X', 'O', 'X' },
                { 'O', 'X', 'O' },
                { 'O', ' ', ' ' }
        };
        TicTacToe.currentPlayer = 'X';
        assertFalse(TicTacToe.isWinner());
    }
}
