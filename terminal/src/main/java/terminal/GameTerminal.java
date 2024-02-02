package terminal;

import board.Board;
import board.ValidMove;
import mechanics.Game;
import player.Player;
import player.human.QuitGameException;
import player.human.UndoException;

import java.util.Optional;

public class GameTerminal extends Game {
    private final GameControllerTerminal gameController;
    public GameTerminal(Board board, Player blackPlayer, Player whitePlayer) {
        super(board, blackPlayer, whitePlayer);
        this.gameController = new GameControllerTerminal(board);
    }

    public void play() {
        int skippedTurns = 0;
        while (!gameController.isBoardFull() && (skippedTurns < 2)) {
            System.out.println(gameController.getBoard());
            System.out.println("Current player: " + gameController.getCurrentPlayerColor());
            gameController.computeValidMoves();
            if (gameController.thereAreNoValidMoves()) {
                skippedTurns++;
                if (skippedTurns == 1) System.out.println("No valid moves for the current player. Changing turn.");
                else System.out.println("No valid moves for both players. Game over.");
            } else {
                skippedTurns = 0;
                Optional<ValidMove> chosenMove = selectAValidMoveOrUndo();
                if (chosenMove.isEmpty()) continue;
                gameController.applyMoveToBoard(chosenMove.get());
                previousSteps.add(gameController.getBoard());
            }
            gameController.swapTurn();
        }
        gameController.printFinalScores();
        GameOver();
        blackPlayer.close();
        whitePlayer.close();
    }

    private Optional<ValidMove> selectAValidMoveOrUndo() {
        Player currentPlayer = gameController.isBlackToMove() ? blackPlayer : whitePlayer;
        try {
            if (isHumanPlayer(currentPlayer))
                System.out.print("Enter your move: ");
            return Optional.of(currentPlayer.askForAMove(gameController));
        } catch (QuitGameException e) {
            System.out.println(e.getMessage());
            exit();
        } catch (UndoException e) {
            undoLastMove();
        } catch (RuntimeException e) {
            System.out.println("\nSomething went wrong. Closing the game.\n");
            exit();
        }
        return Optional.empty();
    }

    @Override
    protected void undoLastMove() {
        int numberOfStepsBack = thereIsAComputerPlayer()? 2 : 1;
        if (previousSteps.size() > numberOfStepsBack) {
            System.out.println("Undoing last move.");
            gameController.undo(numberOfStepsBack,previousSteps);
        } else
            System.out.println("Cannot undo anymore.");
    }
}