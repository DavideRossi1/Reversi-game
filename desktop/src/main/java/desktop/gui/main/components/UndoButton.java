package desktop.gui.main.components;

import desktop.gui.main.GuiManager;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UndoButton {
    private static final Font undoButtonFont = new Font("Arial", Font.PLAIN, 20);
    private static final Color undoButtonColor = GuiManager.white;
    private static final int UNDO_ICON_SIZE = 25;
    private final JButton undoButton;


    public UndoButton(GameDesktop gameDesktop) {
        ImageIcon undoIcon = new ImageIcon(Objects.requireNonNull(UndoButton.class.getResource("/undo.png")));
        undoIcon.setImage(undoIcon.getImage().getScaledInstance(UNDO_ICON_SIZE, UNDO_ICON_SIZE, Image.SCALE_SMOOTH));
        undoButton = new JButton("Undo");
        undoButton.setIcon(undoIcon);
        undoButton.setFont(undoButtonFont);
        undoButton.setForeground(undoButtonColor);
        undoButton.addActionListener(e->gameDesktop.undoLastMove());
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public void setEnabled(boolean enabled) {
        undoButton.setEnabled(enabled);
    }
}