package desktop.gui.other.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DifficultyPanel {

    private final JPanel difficultyPanel;
    private JRadioButton easyButton;
    private JRadioButton hardButton;

    public DifficultyPanel(Font labelsFont, Font radioButtonsFont) {
        difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
        difficultyPanel.setBorder(BorderFactory.createTitledBorder("Difficulty"));
        difficultyPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        difficultyPanel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        JLabel difficultyLabel = getDifficultyLabel(labelsFont);
        difficultyPanel.add(difficultyLabel);

        ButtonGroup difficultyGroup = new ButtonGroup();
        easyButton = getButton("Easy",radioButtonsFont);
        hardButton = getButton("Hard",radioButtonsFont);

        hardButton.setSelected(true);
        difficultyGroup.add(easyButton);
        difficultyGroup.add(hardButton);

        difficultyPanel.add(easyButton);
        difficultyPanel.add(hardButton);
    }

    public JPanel getDifficultyPanel() {
        return difficultyPanel;
    }

    private JRadioButton getButton(String buttonName, Font radioButtonsFont) {
        JRadioButton button = new JRadioButton(buttonName);
        button.setFont(radioButtonsFont);
        button.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        button.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return button;
    }

    private JLabel getDifficultyLabel(Font labelsFont) {
        JLabel difficultyLabel = new JLabel("Select a difficulty:");
        difficultyLabel.setFont(labelsFont);
        difficultyLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        difficultyLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        return difficultyLabel;
    }

    public void setActionListenerToEasyButton(ActionListener actionListener){
        easyButton.addActionListener(actionListener);
    }

    public void setActionListenerToHardButton(ActionListener actionListener){
        hardButton.addActionListener(actionListener);
    }

}
