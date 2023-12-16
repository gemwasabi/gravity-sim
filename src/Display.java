import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas; //set canvas that we will later paint on

    public Display(int width, int height){
        setTitle("My 2D game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);


        add(canvas);
        pack(); //not sure rn

        canvas.createBufferStrategy(3);

        setLocationRelativeTo(null); //window will pop up centered
        setVisible(true);
    }

    public void render(Game game){
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Rectangle2D entity1 = game.getEntity1();
        graphics.setColor(Color.BLUE);
        graphics.fillRect((int) entity1.getX(), (int) entity1.getY(), (int) entity1.getWidth(), (int) entity1.getHeight());

        Rectangle2D entity2 = game.getEntity2();
        graphics.setColor(Color.WHITE);
        graphics.fillRect((int) entity2.getX(), (int) entity2.getY(), (int) entity2.getWidth(), (int) entity2.getHeight());

        graphics.dispose();
        bufferStrategy.show();
    }
}
