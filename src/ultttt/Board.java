//Board class===================================================


package ultttt;
import java.awt.*;

public class Board {
    private final static int NUM_ROWS = 9;
    private final static int NUM_COLUMNS = 9;      
    private static Piece board[][] = new Piece[NUM_ROWS][NUM_COLUMNS];
    public static boolean won = false;
    private static String winner = "";
    private static int ydelta = Window.getHeight2()/NUM_ROWS;
    private static int xdelta = Window.getWidth2()/NUM_COLUMNS;
    
    public static int MouseXPos;
    public static int MouseYPos;
    
    public static void Reset() {
//clear the board.
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;
        won = false;
    }
    
    public static void setMousePos(int x, int y) {
        MouseXPos = x;
        MouseYPos = y;
    }
    
    public static void AddPiece(int x, int y) {
        
        x -= Window.getX(0);
        y -= Window.getY(0);
        
        
        if(x > 0 && x < Window.getWidth2() && y > 0 && y < Window.getHeight2()){
            x = x/xdelta;
            y = y/ydelta;
            //System.out.println(x + " " + y);
            
            
            //"falling" piece code
            
            
            if(board[y][x] == null) {
                board[y][x] = new Piece(Player.getCurrentPlayer().getColor(),Player.getCurrentPlayer().getLetter());
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
    
    public static boolean checkWin() {
        
        if(HorizontalCheckWin())
            return true;
        if(VerticalCheckWin())
            return true;
        if(DiagonalCheckWin())
            return true;
        return false;
    }
    
    public static boolean HorizontalCheckWin() {
        int numConsecutive = 0;
        Color colorMatch = null;

        //Check for horizontal win.        
        for (int row=0;row<NUM_ROWS;row++)
        {
            for (int col=0;col<NUM_COLUMNS;col++)
            {

                if (board[row][col] == null)      //empty location
                {        
                    numConsecutive = 0;
                }
                else if (board[row][col].getColor() == colorMatch || colorMatch == null)     //same color as previous
                {   
                    colorMatch = board[row][col].getColor();
                    numConsecutive++;
                    if(numConsecutive == 3) {
                        for(int i = 0;i < 3;i++) {
                            board[row][col-i].setColor(Color.GREEN);
                        }
                        return true;
                    }
                }
                else if (board[row][col].getColor() != colorMatch)    //different color as previous
                {                                   
                    colorMatch = board[row][col].getColor();
                    numConsecutive = 1;
                }        
            }            
            colorMatch = null;           //move to the next row.
            numConsecutive = 0; 
        } 
        return false;
    }
    
    public static boolean VerticalCheckWin() {
        int numConsecutive = 0;
        Color colorMatch = null;

        //Check for vertical win.        
        for (int col=0;col<NUM_COLUMNS;col++)
        {
            for (int row=0;row<NUM_ROWS;row++)
            {

                if (board[row][col] == null)      //empty location
                {        
                    numConsecutive = 0;
                }
                else if (board[row][col].getColor() == colorMatch || colorMatch == null)     //same color as previous
                {   
                    colorMatch = board[row][col].getColor();
                    numConsecutive++;
                    if(numConsecutive == 3) {
                        for(int i = 0;i < 3;i++) {
                            board[row-i][col].setColor(Color.GREEN);
                        }
                        return true;
                    }
                }
                else if (board[row][col].getColor() != colorMatch)    //different color as previous
                {                                   
                    colorMatch = board[row][col].getColor();
                    numConsecutive = 1;
                }        
            }            
            colorMatch = null;           //move to the next row.
            numConsecutive = 0; 
        } 
        return false;
    }
    
    public static boolean DiagonalCheckWin() {
        int numConsecutive = 0;
        Color colorMatch = null;

        //Check for diagonal win.        
        for (int row=3;row<NUM_ROWS;row++)
        {
            int currentRow = row;
            for (int col=0;col<row+1;col++)
            {
                if (board[col][currentRow] == null)      //empty location
                {        
                    numConsecutive = 0;
                }
                else if (board[col][currentRow].getColor() == colorMatch || colorMatch == null)     //same color as previous
                {   
                    colorMatch = board[col][currentRow].getColor();
                    numConsecutive++;
                    if(numConsecutive == 3) {
                        for(int i = 0;i<3;i++) {
                            board[col-i][currentRow+i].setColor(Color.GREEN);
                        }
                        return true;
                    }
                }
                else if (board[col][currentRow].getColor() != colorMatch)    //different color as previous
                {                                   
                    colorMatch = board[col][currentRow].getColor();
                    numConsecutive = 1;
                }
                currentRow--;
            }            
            colorMatch = null;           //move to the next row.
            numConsecutive = 0;
        }
        
        for(int col=0;col < NUM_COLUMNS-3;col++) {
            int currentCol = col;
            for(int row=NUM_ROWS-1;row > col-1;row--) {
                if (board[currentCol][row] == null)      //empty location
                {        
                    numConsecutive = 0;
                }
                else if (board[currentCol][row].getColor() == colorMatch || colorMatch == null)     //same color as previous
                {   
                    colorMatch = board[currentCol][row].getColor();
                    numConsecutive++;
                    if(numConsecutive == 3) {
                        for(int i = 0;i<3;i++) {
                            board[currentCol-i][row+i].setColor(Color.GREEN);
                        }
                        return true;
                    }
                }
                else if (board[currentCol][row].getColor() != colorMatch)    //different color as previous
                {                                   
                    colorMatch = board[currentCol][row].getColor();
                    numConsecutive = 1;
                }
                currentCol++;
            }
            colorMatch = null;           //move to the next col.
            numConsecutive = 0;
        }
        
        for(int col = NUM_COLUMNS-1;col > 2;col--) {
            int currentCol = col;
            for(int row = NUM_ROWS-1;row > NUM_ROWS-2-col;row--) {
                if (board[currentCol][row] == null)      //empty location
                {        
                    numConsecutive = 0;
                }
                else if (board[currentCol][row].getColor() == colorMatch || colorMatch == null)     //same color as previous
                {   
                    colorMatch = board[currentCol][row].getColor();
                    numConsecutive++;
                    if(numConsecutive == 3) {
                        for(int i = 0;i<3;i++) {
                            board[currentCol+i][row+i].setColor(Color.GREEN);
                        }
                        return true;
                    }
                }
                else if (board[currentCol][row].getColor() != colorMatch)    //different color as previous
                {                                   
                    colorMatch = board[currentCol][row].getColor();
                    numConsecutive = 1;
                }
                currentCol--;
            }
            colorMatch = null;           //move to the next col.
            numConsecutive = 0;
        }
        
        for (int row=3;row<NUM_ROWS;row++)
        {
            int currentRow = row;
            for (int col=NUM_COLUMNS-1;col>NUM_COLUMNS-2-row;col--)
            {
                if (board[col][currentRow] == null)      //empty location
                {        
                    numConsecutive = 0;
                }
                else if (board[col][currentRow].getColor() == colorMatch || colorMatch == null)     //same color as previous
                {   
                    colorMatch = board[col][currentRow].getColor();
                    numConsecutive++;
                    if(numConsecutive == 3) {
                        for(int i = 0;i<3;i++) {
                            board[col+i][currentRow+i].setColor(Color.GREEN);
                        }
                        return true;
                    }
                }
                else if (board[col][currentRow].getColor() != colorMatch)    //different color as previous
                {                                   
                    colorMatch = board[col][currentRow].getColor();
                    numConsecutive = 1;
                }
                currentRow--;
            }            
            colorMatch = null;           //move to the next row.
            numConsecutive = 0;
        }
        
        return false;
    }
    
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
        
        //code that draws the pieces
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                if (board[zrow][zcol] != null)
                    board[zrow][zcol].draw(g, zrow, zcol,xdelta, ydelta);
            }
        }

        g.setColor(Color.red);
        g.setFont (new Font ("Arial",Font.PLAIN, 25));
        g.drawString(""+Player.players[0].getScore(),Window.getWidth2(),60);
        g.setColor(Color.yellow);
        g.drawString(""+Player.players[1].getScore(),Window.getX(0),60);
        
        if(won) {
            g.setColor(Color.black);
            g.setFont (new Font ("Arial",Font.PLAIN, 50));
            g.drawString(winner + " Wins", 200, 350);
        }
        
    }
}