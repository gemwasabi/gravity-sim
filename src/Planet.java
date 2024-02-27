import java.awt.*;
import java.util.ArrayList;

public class Planet {

    private int height, width;
    private double x, y;

    private double deltaX = 0.5, deltaY = 0.5;

    private double mass;
    private double velocityX, velocityY;

    private ArrayList<Point> trajectoryPoints;

    public Planet(double mass, int x, int y, double velocityX, double velocityY) {
        height = 20;
        width = 20;
        this.x = x;
        this.y = y;

        velocityX = 0;
        this.velocityY = velocityY;

        this.mass = mass;

        trajectoryPoints = new ArrayList<>();
    }

    public void render(Graphics g) {
        g.fillOval((int) x - width / 2, (int) y - height / 2, height, width);
    }

    public void update(ArrayList<Planet> planets, boolean isPaused) {
        if (!isPaused) {
            for (Planet otherPlanet : planets) {
                if (this != otherPlanet) {
                    moveTowards(otherPlanet);
                } else {
                    this.y += this.velocityY;
                }
            }
        }

        trajectoryPoints.add(new Point((int) x, (int) y));
    }

    private void moveTowards(Planet target) {
        double deltaX = target.x - this.x;
        double deltaY = target.y - this.y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);


        double G = 9.81;
        double minDistance = 10.0;

        double force = (G * this.mass * target.mass) / Math.max(distance * distance, minDistance * minDistance);

        double speed = 0.5;  // Adjust this value for the desired speed
        double moveX = (deltaX / distance) * force * speed / this.mass + this.velocityX;
        double moveY = (deltaY / distance) * force * speed / this.mass + this.velocityY;

        this.x += moveX;
        this.y += moveY;
    }

    public ArrayList<Point> getTrajectoryPoints() {
        return trajectoryPoints;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMass() {
        return mass;
    }
}
