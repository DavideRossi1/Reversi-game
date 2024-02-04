package desktop.utilities;

import board.Board;
import board.ColoredPawn;
import board.ValidMove;
import board.coords.BoardTile;
import desktop.gui.main.GuiManager;
import desktop.gui.main.components.CurrentPlayerPanel;
import desktop.gui.main.components.CurrentScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardDesktop extends Board {
    private static final int PAWN_ICON_SIZE = 64;
    private static final Image blackPawn = GuiManager.blackPawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH);
    private static final Image whitePawn = GuiManager.whitePawn.getImage().getScaledInstance(PAWN_ICON_SIZE, PAWN_ICON_SIZE, Image.SCALE_SMOOTH);
    private static final Image emptyPawn = new ImageIcon(new byte[0]).getImage();
    private final JGradientButton[][] buttonGrid;

    public BoardDesktop() {
        super();
        buttonGrid = new JGradientButton[BOARD_SIZE][BOARD_SIZE];
        initButtonGrid();
    }

    private void initButtonGrid(){
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttonGrid[i][j] = new JGradientButton(i,j);
                updateButtonIcon(i,j);
            }
    }

    private void updateButtonIcon(int i, int j) {
        Image img = switch (getPositionColor(i,j)) {
            case BLACK -> blackPawn;
            case WHITE -> whitePawn;
            case EMPTY -> emptyPawn;
        };
        buttonGrid[i][j].setIcon(new ImageIcon(img));
    }

    void enableSuggestions(ArrayList<ValidMove> validMoves) {
        for (ValidMove validMove : validMoves)
            setSuggestionAtTile(validMove.position(),true);
    }

    void disableSuggestions() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((boolean)buttonGrid[i][j].getClientProperty("toSuggest"))
                    setSuggestionAtTile(new BoardTile(i, j),false);
            }
    }

    private void setSuggestionAtTile(BoardTile position, boolean toSuggest) {
        int row = position.x();
        int col = position.y();
        buttonGrid[row][col].setToSuggestProperty(toSuggest);
        buttonGrid[row][col].resetBackground();
    }

    protected void addListenerToButton(BoardTile position, ActionListener listener){
        buttonGrid[position.x()][position.y()].addActionListener(listener);
    }

    protected void updateGUIBoard(ValidMove move) {
        applyMoveToBoard(move);
        updateButtonGrid();
        CurrentPlayerPanel.updateCurrentPlayerLiveLabel();
        CurrentScorePanel.updateLiveScoreLabel(computeScoreForPlayer(ColoredPawn.BLACK),
                                            computeScoreForPlayer(ColoredPawn.WHITE));
    }

    protected void updateButtonGrid() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                updateButtonIcon(i,j);
    }

    public void setEnabled(boolean enabled) {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                getButton(i, j).setEnabled(enabled);
    }

    public static Image getBlackPawnImage() {
        return blackPawn;
    }
    public static Image getWhitePawnImage() {
        return whitePawn;
    }

    public JGradientButton getButton(int row, int col) {
        return buttonGrid[row][col];
    }
}