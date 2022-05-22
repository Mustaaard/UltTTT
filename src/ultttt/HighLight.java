package ultttt;
import java.awt.Color;
import java.awt.Graphics2D;

public class HighLight {
    private static int ydelta = Window.getHeight2()/3;
    private static int xdelta = Window.getWidth2()/3;
    
    private static int sectionX;
    private static int sectionY;
    
    public static void getSection(int MouseXPos,int MouseYPos) {
        int[][] sections = {{0,1,2},
                            {3,4,5},
                            {6,7,8}};
        int section;
        
        int x = MouseXPos/(Window.getWidth2()/3);
        int y = MouseYPos/(Window.getHeight2()/3);
        section = sections[y][x];
        
        sectionX = sections[y][x]-(sections[y][x]/3)*3;
        sectionY = sections[y][x]/3;
    }
    
    public static void setSection(int x,int y) {
        sectionX = x;
        sectionY = y;
    }
    
    public static void draw(Graphics2D g, boolean wholeBoard) {
        g.setColor(Color.white);
        if (!wholeBoard)
            g.fillRect(Window.getX(0+xdelta*sectionX),Window.getY(0+ydelta*sectionY),xdelta,ydelta);
        else
            g.fillRect(23, 70, Window.getWidth2(),Window.getHeight2());
    }
}
