package desktop.gui.other.components;

import javax.swing.*;
import java.awt.*;

public class Button {

    JButton startButton;

    public Button(Font startButtonFont, String buttonText) {
        buildStartButton(startButtonFont, buttonText);
    }

    public JButton getButton() {
        return startButton;
    }

    private void buildStartButton(Font startButtonFont, String buttonText) {
        startButton = new JButton(buttonText);
        startButton.setFont(startButtonFont);
        startButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        startButton.setAlignmentY(JLabel.TOP_ALIGNMENT);

        startButton.setEnabled(true);
        startButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, startButton.getMinimumSize().height));
    }
}
