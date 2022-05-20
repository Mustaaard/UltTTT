//SubBoard class===================================================


package ultttt;
import java.awt.*;
import static ultttt.Player.players;

public class SubBoard {
    private final static int NUM_ROWS = 3;
    private final static int NUM_COLUMNS = 3;      
    private Piece subboard[][] = new Piece[NUM_ROWS][NUM_COLUMNS];
    public boolean won = false;
    public String winner;
    private static int ydelta = Window.getHeight2()/9;
    private static int xdelta = Window.getWidth2()/9;
    
    public boolean on = true;
    public int TopleftY = 0;
    public int TopleftX = 0;
    public int NextY;
    public int NextX = -1;
    
    public static int MouseXPos;
    public static int MouseYPos;
    
    SubBoard(int x,int y) {
        setLeft(x,y);
    }
    
    public void setLeft(int x,int y) {
        TopleftX = x;
        TopleftY = y;
    }
    
    public void Reset() {
//clear the Subboard.
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                subboard[zrow][zcol] = null;
        won = false;
        on =true;
    }
    
    public void setMousePos(int x, int y) {
        MouseXPos = x;
        MouseYPos = y;
    }
    
    public void AddPiece(int x, int y) {
        x -= Window.getX(0);
        y -= Window.getY(0);
        
        if(x > TopleftX*Window.getWidth2()/3 && x < Window.getWidth2()/3+TopleftX*Window.getWidth2()/3 && 
           y > TopleftY*Window.getHeight2()/3 && y < Window.getHeight2()/3+TopleftY*Window.getHeight2()/3) {
            x = (x-TopleftX*Window.getWidth2()/3)/xdelta;
            y = (y-TopleftY*Window.getHeight2()/3)/ydelta;
            //System.out.println(x + " " + y);
            if(x < 0)
                x = 0;
            else if(x > NUM_ROWS-1)
                x = NUM_ROWS-1;
            if(y < 0)
                y = 0;
            else if(y > NUM_ROWS-1)
                y = NUM_ROWS-1;
            //"falling" piece code
            
            
            if(subboard[y][x] == null) {
                NextY = y;
                NextX = x;
                subboard[y][x] = new Piece(Player.getCurrentPlayer().getColor(),Player.getCurrentPlayer().getLetter());
                if(checkWin()) {
                    won = true;
                    if(Player.getCurrentPlayer().getColor() == Color.RED) {
                        winner = "Red";
                    }
                    else {
                        winner = "Blue";
                    }
                }
                    
                Player.switchCurrentPlayer();
            }
        }
    }
    
    public boolean checkWin() {
        
        if(HorizontalCheckWin())
            return true;
        if(VerticalCheckWin())
            return true;
        if(DiagonalCheckWin())
            return true;
        return false;
    }
    
    public boolean HorizontalCheckWin() {
        int numConsecutive = 0;
        Color colorMatch = null;

//Check for horizontal win.        
        for (int row=0;row<NUM_ROWS;row++)
        {
            for (int col=0;col<NUM_COLUMNS;col++)
            {
                if(col == 0) {
                    if(subboard[row][col] != null)
                        colorMatch = subboard[row][col].getColor();
                }
                if (subboard[row][col] != null)     //same color as previous
                {   
                    if(subboard[row][col].getColor() == colorMatch) {
                        if(colorMatch == null)
                            colorMatch = subboard[row][col].getColor();
                        numConsecutive++;
                        if(numConsecutive == 3) {
                            for(int i = 0;i < 3;i++) {
                                subboard[row][col-i].setColor(Color.green);
                            }
                            return true;
                        }
                    }
                }        
            }            
            colorMatch = null;           //move to the next row.
            numConsecutive = 0; 
        }
        return false;
    }
    
    public boolean VerticalCheckWin() {
        int numConsecutive = 0;
        Color colorMatch = null;

        //Check for vertical win.        
        for (int col=0;col<NUM_ROWS;col++)
        {
            for (int row=0;row<NUM_COLUMNS;row++)
            {
                if(row == 0) {
                    if(subboard[row][col] != null)
                        colorMatch = subboard[row][col].getColor();
                }
                if (subboard[row][col] != null)     //same color as previous
                {   
                    if(subboard[row][col].getColor() == colorMatch) {
                        if(colorMatch == null)
                            colorMatch = subboard[row][col].getColor();
                        numConsecutive++;
                        if(numConsecutive == 3) {
                            for(int i = 0;i < 3;i++) {
                                subboard[row-i][col].setColor(Color.green);
                            }
                            return true;
                        }
                    }
                }
            }            
            colorMatch = null;           //move to the next row.
            numConsecutive = 0; 
        }
        return false;
    }
    
    public boolean DiagonalCheckWin() {
        int numConsecutive = 0;
        Color colorMatch = null;

        //Check for diagonal win.        
        for (int i=0;i<NUM_COLUMNS;i++)
        {
            if(subboard[0][0] != null) {
                colorMatch = subboard[0][0].getColor();
                if(subboard[i][i] != null) {
                    if(subboard[i][i].getColor() == colorMatch) {
                        numConsecutive++;
                    }
                }
                if(numConsecutive == 3) {
                    for(int o = 0;o < 3;o++) {
                        subboard[i-o][i-o].setColor(Color.green);
                    }
                    return true;
                }
            }
        }
        
        numConsecutive = 0;
        colorMatch = null;
        
        for (int i=0;i<NUM_COLUMNS;i++)
        {
            if(subboard[NUM_ROWS-1][0] != null) {
                colorMatch = subboard[NUM_ROWS-1][0].getColor();
                if(subboard[NUM_ROWS-1-i][i] != null) {
                    if(subboard[NUM_ROWS-1-i][i].getColor() == colorMatch) {
                        numConsecutive++;
                    }
                }
                if(numConsecutive == 3) {
                    for(int o = 0;o < 3;o++) {
                        subboard[NUM_ROWS-1-i+o][i-o].setColor(Color.green);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public void Draw(Graphics2D g) {
//draw grid
        if(MouseXPos > 0 && MouseXPos < Window.getWidth2() && MouseYPos > 0 && MouseYPos < Window.getHeight2()) {
            HighLight.getSection(MouseXPos,MouseYPos);
            HighLight.draw(g);
        }
        
        //code that draws the pieces
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                if (subboard[zrow][zcol] != null)
                    subboard[zrow][zcol].draw(g, Window.getY((zrow+1)*ydelta)+TopleftY*ydelta*3,
                                            Window.getX(zcol*xdelta)+5+TopleftX*xdelta*3,xdelta, ydelta);
            }
        }

        if (players[0]==Player.getCurrentPlayer())
        {
        g.setColor(Color.red);
        g.setFont (new Font ("Arial",Font.PLAIN, 25));
        g.drawString("X's Turn",Window.getWidth2()-60,60);
        }
        else if (players[1]==Player.getCurrentPlayer())
        {
        g.setColor(Color.blue);
        g.setFont (new Font ("Arial",Font.PLAIN, 25));
        g.drawString("O's Turn",Window.getX(0),60);
        }
        
        /*if(won) {
            g.setColor(Color.black);
            g.setFont (new Font ("Arial",Font.PLAIN, 50));
            g.drawString(winner + " Wins", 200, 350);
        }*/
        
    }
}