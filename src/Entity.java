import java.awt.*;

public class Entity {
    private int height, width, x, y;

    private Rectangle rectangle;

    public Entity(int height, int width, int x, int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

        rectangle = new Rectangle(x, y, width, height);
    }
}
