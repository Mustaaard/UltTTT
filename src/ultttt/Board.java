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
    public static boolean draw = false;
    public static String winner = "";
    private static int ydelta = Window.getHeight2()/9;
    private static int xdelta = Window.getWidth2()/9;
    private static int nextX;
    private static int nextY;
    
    public static boolean freewillisamyth = false;
    
    public static int MouseXPos;
    public static int MouseYPos;
    
    public static void Reset() {
        for(int i = 0;i < 3;i++)
            for(int o = 0;o < 3;o++)
                box[i][o].Reset();
        won = false;
        draw = false;
        freewillisamyth = false;
    }
    
    public static void AddPiece(int indexX,int indexY,int x, int y) {
        if(box[indexY][indexX].won == false && box[indexY][indexX].on) {
            box[indexY][indexX].AddPiece(x,y);
            if(box[indexY][indexX].NextX != -1) {
                
                freewillisamyth = true;
                
                for(int i = 0;i < 3;i++)
                    for(int o = 0;o < 3;o++)
                        box[i][o].on = false;
                
                if(box[box[indexY][indexX].NextY][box[indexY][indexX].NextX].won ||
                   box[box[indexY][indexX].NextY][box[indexY][indexX].NextX].checkDraw()) {
                    
                    freewillisamyth = false;
                    
                    for(int i = 0;i < 3;i++)
                        for(int o = 0;o < 3;o++)
                            box[i][o].on = true;
                }
                
                box[box[indexY][indexX].NextY][box[indexY][indexX].NextX].on = true;
                nextX = box[indexY][indexX].NextX;
                nextY = box[indexY][indexX].NextY;
                
                box[indexY][indexX].NextX = -1;
            }
        }
        if(checkWin())
            won = true;
        else if(checkDraw() == true)
            draw = true;
    }
    
    public static boolean checkWin()
    {
        if(HorizontalCheckWin())
            return true;
        if(VerticalCheckWin())
            return true;
        if(DiagonalCheckWin())
            return true;
        return false;
    }
    
    public static boolean checkDraw()
    {
        for(int i = 0;i < 3;i++)
            for(int o = 0;o < 3;o++)
                if(box[i][o].won == false && box[i][o].draw == false)
                    return false;
        return true;
    }
    
    public static boolean HorizontalCheckWin() {
        String colorMatch;
        for(int i = 0;i < 3;i++) {
            if(box[i][0].won == true && box[i][1].won == true && box[i][2].won == true) {
                colorMatch = box[i][0].winner;
                if(box[i][0].winner == colorMatch && box[i][1].winner == colorMatch && box[i][2].winner == colorMatch) {
                    winner = colorMatch;
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean VerticalCheckWin() {
        String colorMatch;
        for(int i = 0;i < 3;i++) {
            if(box[0][i].won == true && box[1][i].won == true && box[2][i].won == true) {
                colorMatch = box[0][i].winner;
                if(box[0][i].winner == colorMatch && box[1][i].winner == colorMatch && box[2][i].winner == colorMatch) {
                    winner = colorMatch;
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean DiagonalCheckWin() {
        String colorMatch;
        if(box[0][0].won == true && box[1][1].won == true &&  box[2][2].won == true) {
            colorMatch = box[0][0].winner;
            if(box[0][0].winner == colorMatch && box[1][1].winner == colorMatch &&  box[2][2].winner == colorMatch) {
                winner = colorMatch;
                return true;
            }
        }
        
        if(box[2][0].won == true && box[1][1].won == true &&  box[0][2].won == true) {
            colorMatch = box[2][0].winner;
            if(box[2][0].winner == colorMatch && box[1][1].winner == colorMatch &&  box[0][2].winner == colorMatch) {
                winner = colorMatch;
                return true;
            }
        }
        return false;
    }
    
    public static void setMousePos(int x, int y) {
        MouseXPos = x;
        MouseYPos = y;
    }
    
    public static void Draw(Graphics2D g) {
//draw grid
        if(freewillisamyth) {
            HighLight.setSection(nextX,nextY);
            HighLight.draw(g,false);
        }
        else if(!freewillisamyth) {
            
                //HighLight.getSection(MouseXPos,MouseYPos);
                HighLight.draw(g,true);
            
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
        
    }
}