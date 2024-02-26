import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            Game game = gamePanel.getGame();

            String massString = JOptionPane.showInputDialog("Enter the mass of the planet:");
            String velocityYString = JOptionPane.showInputDialog("Enter the Y Velocity of the planet:");
            try {
                double mass = Double.parseDouble(massString);
                double velocityY = Double.parseDouble(velocityYString);

                Planet planet = new Planet(mass, e.getX(), e.getY(), 0, velocityY);
                game.addPlanet(planet);

                gamePanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gamePanel, "Invalid input for mass. Please enter a valid number.");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
