import java.awt.*;

public class Game {

    private Display display;
    private Rectangle rectangle;
    private int deltaX = 3;
    private int deltaY = 1;
    public Game(int width, int height){
        display = new Display(width, height);
        rectangle = new Rectangle(0, 0, 50, 50);
    }

    public void update(){
        Insets insets = display.getInsets();
        if(rectangle.getX() > display.getWidth() - rectangle.getWidth() - insets.left - insets.right || rectangle.getX() < 0){
            deltaX *= -1;
        }
        if(rectangle.getY() > display.getHeight() - rectangle.getHeight() - insets.top - insets.bottom || rectangle.getY() < 0){
            deltaY *= -1;
        }
        rectangle.setLocation((int) rectangle.getX() + deltaX, (int) rectangle.getY() + deltaY);
    }

    public void render(){
        display.render(this);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
