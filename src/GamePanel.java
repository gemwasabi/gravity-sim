import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private Game game;
    private MouseInputs mouseInputs;
    private JButton playPauseButton;

    private boolean isPaused = false;

    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);

        setPanelSize();
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        playPauseButton = new JButton("Pause");
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePause();
            }
        });
        add(playPauseButton);
    }

    private void setPanelSize() {
        Dimension d = new Dimension(1280, 800);
        setPreferredSize(d);
    }

    private void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            playPauseButton.setText("Play");
        } else {
            playPauseButton.setText("Pause");
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isPaused) {
            game.renderTrajectories(g);
        } else {
            game.render(g);
        }
    }

    public Game getGame() {
        return game;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
