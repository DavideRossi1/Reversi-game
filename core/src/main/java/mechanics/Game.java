package mechanics;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import player.Player;
import player.human.Human;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Game {
    final Board board;
    final Player whitePlayer;
    final Player blackPlayer;
    int skippedTurns;
    final ValidMovesChecker validMovesChecker;
    private boolean gameOver;

    final ArrayList<Board> previousSteps;

    public Game(Board board, Player blackPlayer, Player whitePlayer) {
        this.board = board;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        skippedTurns = 0;
        previousSteps = new ArrayList<>(0);
        previousSteps.add(this.board.copy());
        validMovesChecker = new ValidMovesChecker(this.board);
        gameOver = false;
    }

    public void play() {
        while (!board.isFull() && (skippedTurns < 2)) {
            System.out.println(board);
            System.out.println("Current player: " + validMovesChecker.getCurrentPlayerColor());
            validMovesChecker.computeValidMoves();
            if (validMovesChecker.numberOfValidMoves() == 0) {
                skippedTurns++;
                if (skippedTurns == 1) System.out.println("No valid moves for the current player. Changing turn.");
                else System.out.println("No valid moves for both players. Game over.");
            } else {
                skippedTurns = 0;
                ValidMove chosenMove = selectAValidMoveOrUndo();
                if (chosenMove == null) continue;
                board.applyMoveToBoard(chosenMove);
                previousSteps.add(board.copy());
            }
            validMovesChecker.swapTurn();
        }
        GameOver();  // currently not used
        System.out.println(board);
        printFinalScores();
    }

    private ValidMove selectAValidMoveOrUndo() {
        Player currentPlayer = validMovesChecker.isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            return currentPlayer.askForAMove(validMovesChecker);
        } catch (QuitGameException e) {
            System.out.println(e.getMessage());
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        } catch (UndoException e) {
            undoLastMove();
        } catch (RuntimeException e) {
            System.out.println("\nSomething went wrong. Closing the game.\n");
            blackPlayer.close();
            whitePlayer.close();
            System.exit(0);
        }
        return null;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void GameOver() {
        gameOver = true;
    }

    public Board getBoard() {
        return board.copy();
    }

    public void undoLastMove() {
        int numberOfHumanPlayers = (whitePlayer.getClass().equals(Human.class) ? 1 : 0) +
                (blackPlayer.getClass().equals(Human.class) ? 1 : 0);
        int numberOfStepsBack = (numberOfHumanPlayers == 1) ? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack) {
            System.out.println("Undoing last move.");
            IntStream.range(0, numberOfStepsBack).forEachOrdered(i -> previousSteps.remove(previousSteps.size() - 1));
            board.importBoardFrom(previousSteps.get(previousSteps.size() - 1));
            validMovesChecker.swapTurn();
        } else
            System.out.println("Cannot undo anymore.");
    }

    private void printFinalScores() {
        int whiteScore = board.computeScoreForPlayer(ColoredPawn.WHITE);
        int blackScore = board.computeScoreForPlayer(ColoredPawn.BLACK);
        System.out.println("FINAL SCORE: " + ColoredPawn.WHITE + ": " + whiteScore + ", " + ColoredPawn.BLACK + ": " + blackScore);
        System.out.println((whiteScore > blackScore) ? "White wins!" : (whiteScore < blackScore) ? "Black wins!" : "Draw!");
    }
}
