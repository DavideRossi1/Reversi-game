package desktop.gui.other.welcome;

import desktop.gui.other.Button;
import desktop.utilities.BoardDesktop;
import desktop.utilities.GameDesktop;

import javax.swing.*;
import java.awt.*;

/**
 * The welcome frame of the game. Set up game settings and starts the game.
 */
public class WelcomeFrame {
    /**
     * The width of the welcome frame
     */
    protected static final int WIDTH = 500;
    /**
     * The height of the welcome frame
     */
    protected static final int HEIGHT = 200;
    /**
     * The font of the start button
     */
    protected static final Font StartButtonFont = new Font("Arial", Font.BOLD, 20);
    private static JFrame frame;
    private WelcomePanel welcomePanel;
    private JButton startButton;

    /**
     * Creates a new WelcomeFrame, a frame that allows to decide game settings.
     */
    public WelcomeFrame() {
        frame = new JFrame("Welcome to Reversi");
        frame.setSize(WIDTH, HEIGHT);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel generalPanel = buildGeneralPanel();
        frame.add(generalPanel);
        frame.setVisible(true);
    }

    private JPanel buildGeneralPanel() {
        welcomePanel = new WelcomePanel();
        startButton = new Button(StartButtonFont, "Start").getButton();
        setActionListenerToStartButton();
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());
        generalPanel.add(welcomePanel.getIthPanel(0), BorderLayout.WEST);
        generalPanel.add(welcomePanel.getIthPanel(1), BorderLayout.CENTER);
        generalPanel.add(welcomePanel.getIthPanel(2), BorderLayout.EAST);
        generalPanel.add(startButton, BorderLayout.SOUTH);
        return generalPanel;
    }

    private void setActionListenerToStartButton() {
        startButton.addActionListener(e -> {
            frame.dispose();
            GameSettings.Players result = welcomePanel.getPlayers();
            GameDesktop gameDesktop = new GameDesktop(new BoardDesktop(), result.blackPlayer(), result.whitePlayer());
            gameDesktop.play();
        });
    }

    /**
     * Sets the welcome frame visible in order to start the game.
     */
    public void setWelcomeFrameVisible() {
        frame.setVisible(true);
    }

    /**
     * Returns the welcome frame.
     * @return the welcome frame
     */
    public static JFrame getWelcomeFrame() {
        return frame;
    }

    /**
     * Returns the start button.
     * @return the start button
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Presses the ith button on the jth panel (indexes start from 0).
     * @param i the ith button
     * @param j the jth panel (panels are game mode, difficulty, first player)
     */
    public void pressIthButtonOnJthPanel(int i, int j) {
        welcomePanel.returnIthButtonOnJthPanel(i, j).doClick();
    }

    /**
     * Returns the ith panel of the welcome panel.
     * @param i the index of the desired panel
     * @return the panel
     */
    public JPanel getIthPanel(int i){
        return welcomePanel.getIthPanel(i);
    }

    /**
     * Checks if the button pressed in the desired panel is the default one.
     * @param i the index of the desired panel
     * @return true if the button pressed is the default one, false otherwise
     */
    public boolean isDefaultOnIthPanel(int i){
        return welcomePanel.isDefaultOnIthPanel(i);
    }
}
