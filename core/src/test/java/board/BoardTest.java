package board;

import board.coords.BoardTile;
import board.coords.Direction;
import mechanics.GameController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static positions.GamePositions.*;

class BoardTest {

    @ParameterizedTest
    @MethodSource("positions.BoardTilePositions#emptyBoardTilesOnStart")
    void emptyBoardTilesOnStart(String tileName) {
        Board board = new Board();
        BoardTile tile = new BoardTile(tileName);
        ColoredPawn coloredPawnTile = board.getPositionColor(tile);
        assertEquals(ColoredPawn.EMPTY, coloredPawnTile);
    }

    @ParameterizedTest
    @MethodSource("positions.BoardTilePositions#nonEmptyBoardTilesOnStart")
    void checkStartingPosition(ColoredPawn expectedColor, int x, int y) {
        Board board = new Board();
        ColoredPawn coloredPawnTile = board.getPositionColor(x, y);
        assertEquals(expectedColor, coloredPawnTile);
    }


    @Test
    void swapAlongAllDirections() {
        Board board = thereAreTwoFlippableLinesInOneMove();
        GameController gameController = new GameController(board);
        gameController.swapTurn();
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(new Direction(-1, 0));
        directions.add(new Direction(0, 1));

        ValidMove move = new ValidMove(new BoardTile("c5"), directions, gameController.getCurrentPlayerColor());
        board.applyMoveToBoard(move);
        Board expectedBoard = twoLinesFlippedOneMove();
        assertEquals(board, expectedBoard);
    }

    @ParameterizedTest
    @MethodSource("positions.GamePositions#someScores")
    void scoreIsCorrect(Board board, int blackScore, int whiteScore) {
        assertEquals(blackScore, board.computeScoreForPlayer(ColoredPawn.BLACK));
        assertEquals(whiteScore, board.computeScoreForPlayer(ColoredPawn.WHITE));
    }

    @Test
    void copyFunctionsWorks() {
        Board board = impossibleToMovePosition();
        Board copiedBoard = board.copy();
        assertEquals(board, copiedBoard);
    }

}