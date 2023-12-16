import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Game {

    private Display display;
    private Rectangle rectangle;

    private Rectangle2D.Double entity1;
    private Rectangle2D.Double entity2;

    private double gravitationalForce = 0.5d;


    private double deltaX = 2;
    private int deltaY = 1;
    public Game(int width, int height){
        display = new Display(width, height);

        //make two entities, later on make an entity class
        entity1 = new Rectangle2D.Double(30, 60, 20, 20);
        entity2 = new Rectangle2D.Double(display.getWidth()/2, display.getHeight()/2, 5, 5);
    }

    public void update() {
        double dx = entity2.getX() - entity1.getX();
        double dy = entity2.getY() - entity1.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Gravitational constant
        double G = 1000;

        // Apply gravitational force
        double gravitationalForce = G * ((entity1.getWidth() * entity2.getWidth()) / (distance * distance));

        double moveX = (dx / distance) * gravitationalForce;
        double moveY = (dy / distance) * gravitationalForce;

        // Move entity1 towards entity2
        entity1 = new Rectangle2D.Double(entity1.getX() + moveX, entity1.getY() + moveY, 5, 5);

        // Move entity2 towards entity1 (uncomment the line below if needed)
        // entity2 = new Rectangle2D.Double(entity2.getX() - moveX, entity2.getY() - moveY, 5, 5);
    }


    public void render(){
        display.render(this);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }


    public Rectangle2D.Double getEntity1() {
        return entity1;
    }

    public Rectangle2D.Double getEntity2() {
        return entity2;
    }
}
