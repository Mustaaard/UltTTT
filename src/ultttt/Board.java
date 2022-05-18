//Board class===================================================


package ultttt;
import java.awt.*;
import static ultttt.Player.players;

public class Board {
    private final static int NUM_ROWS = 3;
    private final static int NUM_COLUMNS = 3;
    
    public static SubBoard[][] box = {{new SubBoard(0,0),new SubBoard(1,0),new SubBoard(2,0)},
                                      {new SubBoard(0,1),new SubBoard(1,1),new SubBoard(2,1)},
                                      {new SubBoard(0,2),new SubBoard(1,2),new SubBoard(2,2)}};
    
    public static boolean won = false;
    private static String winner = "";
    private static int ydelta = Window.getHeight2()/9;
    private static int xdelta = Window.getWidth2()/9;
    
    public static int MouseXPos;
    public static int MouseYPos;
    
    public static void Reset() {
        for(int i = 0;i < 3;i++)
            for(int o = 0;o < 3;o++)
                box[i][o].Reset();
        won = false;
    }
    
    public static void AddPiece(int indexX,int indexY,int x, int y) {
        box[indexY][indexX].AddPiece(x,y);
        if(checkWin())
            won = true;
    }
    
    public static boolean checkWin()
    {
        if(HorizontalCheckWin())
            return true;
        return false;
    }
    
    
    public static boolean HorizontalCheckWin() {
        for(int i = 0;i < 3;i++) {
            if(box[i][0].won == true && box[i][1].won == true && box[i][2].won == true)
                return true;
        }
        return false;
    }
    
    public static void setMousePos(int x, int y) {
        MouseXPos = x;
        MouseYPos = y;
    }
    
    public static void Draw(Graphics2D g) {
//draw grid
        if(MouseXPos > 0 && MouseXPos < Window.getWidth2() && MouseYPos > 0 && MouseYPos < Window.getHeight2()) {
            HighLight.getSection(MouseXPos,MouseYPos);
            HighLight.draw(g);
        }
        
        for (int zi = 0;zi<NUM_ROWS*3;zi++)
        {
            g.setColor(Color.black);
            if(zi%3 == 0)
                g.setColor(Color.red);
            g.drawLine(Window.getX(0),Window.getY(zi*ydelta),
                    Window.getX(Window.getWidth2()),Window.getY(zi*ydelta));
        }
        
        for (int zi = 1;zi<NUM_COLUMNS*3;zi++)
        {
            g.setColor(Color.black);
            if(zi%3 == 0)
                g.setColor(Color.red);
            g.drawLine(Window.getX(zi*xdelta),Window.getY(0),
                    Window.getX(zi*xdelta),Window.getY(Window.getHeight2()));
        }
        
        if(won) {
            g.setColor(Color.black);
            g.setFont (new Font ("Arial",Font.PLAIN, 50));
            g.drawString(winner + " Wins", 200, 350);
        }
        
    }
}