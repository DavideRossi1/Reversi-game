package player.computer;

import board.Board;
import board.ValidMove;
import mechanics.ValidMovesChecker;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class SmartPlayer implements Player {

    ArrayList<Integer> futureScores;

    public SmartPlayer() {
        futureScores = new ArrayList<>();
    }

    @Override
    public ValidMove askForAMove(ValidMovesChecker validMovesChecker)  {
        futureScores.clear();
        for (int i = 0; i < validMovesChecker.getValidMoves().size(); i++) {
            Board board = validMovesChecker.getBoard().copy();
            board.applyMoveToBoard(validMovesChecker.getValidMoves().get(i));
            futureScores.add(board.computeScoreForPlayer(board.getCurrentOpponent()));
        }
        int maxScore = Collections.max(futureScores);
        return validMovesChecker.getValidMoves().get(futureScores.indexOf(maxScore));
    }

    public void close()  { }

}
