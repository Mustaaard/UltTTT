package ultttt;
import java.awt.Graphics2D;

public class HighLight {
    private static int ydelta = Window.getHeight2()/9;
    private static int xdelta = Window.getWidth2()/9;
    
    public static int getSection(int MousePosX,int MousePosY) {
        int[][] sections = {{0,1,2},
                            {3,4,5},
                            {6,7,8}};
        int section;
        
        int x = MousePosX/Window.getWidth2()/3;
        int y = MousePosX/Window.getHeight2()/3;
        section = sections[y][x];
        
        return section;
    }
    
    public static void draw(Graphics2D g,int MousePosX,int MousePosY) {
        
    }
}
