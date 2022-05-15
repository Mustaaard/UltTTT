//Board class===================================================


package ultttt;
import java.awt.*;
import static ultttt.Player.players;

public class Board {
    private final static int NUM_ROWS = 9;
    private final static int NUM_COLUMNS = 9;      
    private static Piece subboard[][] = new Piece[NUM_ROWS][NUM_COLUMNS];
    public static boolean won = false;
    private static String winner = "";
    private static int ydelta = Window.getHeight2()/9;
    private static int xdelta = Window.getWidth2()/9;
    
    public static int MouseXPos;
    public static int MouseYPos;
    
    
    public static void Draw(Graphics2D g) {
//draw grid
        
        for (int zi = 0;zi<NUM_ROWS;zi++)
        {
            g.setColor(Color.black);
            if(zi%3 == 0)
                g.setColor(Color.red);
            g.drawLine(Window.getX(0),Window.getY(zi*ydelta),
                    Window.getX(Window.getWidth2()),Window.getY(zi*ydelta));
        }
        
        for (int zi = 1;zi<NUM_COLUMNS;zi++)
        {
            g.setColor(Color.black);
            if(zi%3 == 0)
                g.setColor(Color.red);
            g.drawLine(Window.getX(zi*xdelta),Window.getY(0),
                    Window.getX(zi*xdelta),Window.getY(Window.getHeight2()));
        }
    }
}