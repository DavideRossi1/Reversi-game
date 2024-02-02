package mechanics;

import board.Board;
import board.ValidMove;
import org.junit.jupiter.api.Test;
import player.computer.RandomPlayer;
import player.human.Human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static positions.GamePositions.impossibleToMovePosition;

class GameTest {

    @Test
    void bothPlayersCantMove() {
        Board board = impossibleToMovePosition();
        Game game = new Game(board, new RandomPlayer(), new RandomPlayer());
        game.play();
        assertTrue(game.isGameOver());
    }

    @Test
    void undoToInitialPosition() {
        Board board = new Board();
        Game game = new Game(board, new Human(), new Human());
        GameController gameController = game.getGameController();
        gameController.computeValidMoves();
        ValidMove move = gameController.getValidMoves().getFirst();
        board.applyMoveToBoard(move);
        game.previousSteps.add(board.copy());
        game.undoLastMove();
        assertEquals(new Board(), gameController.getBoard());
    }

    @Test
    void undoTwoMovesIfHumanVsBot() {
        Board board = new Board();
        Game game = new Game(board, new Human(), new RandomPlayer());
        GameController gameController = game.getGameController();
        gameController.computeValidMoves();
        ValidMove move1 = gameController.getValidMoves().getFirst();
        board.applyMoveToBoard(move1);
        game.previousSteps.add(board.copy());

        gameController.computeValidMoves();
        ValidMove move2 = gameController.getValidMoves().getFirst();
        board.applyMoveToBoard(move2);
        game.previousSteps.add(board.copy());

        game.undoLastMove();

        assertEquals(new Board(), gameController.getBoard());
    }
}