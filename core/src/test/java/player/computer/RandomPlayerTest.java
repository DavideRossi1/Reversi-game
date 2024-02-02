package player.computer;

import board.Board;
import board.ValidMove;
import mechanics.GameController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomPlayerTest {

    @ParameterizedTest
    @MethodSource("positions.GamePositions#getAllPositions")
    void ReturnedMoveIsValid(Board currentPosition) {
        GameController checker = new GameController(currentPosition);
        checker.computeValidMoves();
        ArrayList<ValidMove> expected = checker.getValidMoves();
        RandomPlayer player = new RandomPlayer();
        ValidMove obtained = player.askForAMove(checker);
        assertTrue(expected.contains(obtained));
    }

    @ParameterizedTest
    @MethodSource("positions.GamePositions#getAllPositions")
    void ReturnedMoveIsValidWithWhite(Board currentPosition) {
        GameController checker = new GameController(currentPosition);
        checker.swapTurn();
        checker.computeValidMoves();
        ArrayList<ValidMove> expected = checker.getValidMoves();
        RandomPlayer player = new RandomPlayer();
        ValidMove obtained = player.askForAMove(checker);
        assertTrue(expected.contains(obtained));
    }
}