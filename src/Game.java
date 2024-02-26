import java.awt.*;
import java.util.ArrayList;

public class Game implements Runnable {

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private ArrayList<Planet> planets;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public Game() {
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        planets = new ArrayList<>();
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                if(!gamePanel.isPaused()) {
                    update();
                }
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public void update() {
        for (Planet planet : planets) {
            planet.update(planets, gamePanel.isPaused());
        }
    }

    public void render(Graphics g) {
        for (Planet planet : planets) {
            planet.render(g);
        }
    }

    public void renderTrajectories(Graphics g) {
        for (Planet planet : planets) {
            ArrayList<Point> trajectoryPoints = planet.getTrajectoryPoints();
            if (trajectoryPoints.size() > 1) {
                Point prevPoint = trajectoryPoints.get(0);
                for (int i = 1; i < trajectoryPoints.size(); i++) {
                    Point currentPoint = trajectoryPoints.get(i);
                    g.drawLine(prevPoint.x, prevPoint.y, currentPoint.x, currentPoint.y);
                    prevPoint = currentPoint;
                }
            }
        }
    }
}
