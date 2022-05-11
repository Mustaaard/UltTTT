//Piece class===================================================

package ultttt;
import java.awt.*;



public class Piece {
    private Color color;
    private int value;
    Piece(Color _color)
    {
        value = (int)(Math.random()*4+1);
        color = _color;
    }
    
    public int getValue() {
        return value;
    }
    
    public Color getColor()
    {
        return (color);
    }
    
    public void setColor(Color _color) {
        color = _color;
    }

    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        g.setColor(color);
        g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);
        g.setColor(Color.black);
        g.setFont (new Font ("Arial",Font.PLAIN, 50));
        g.drawString(""+value, Window.getX(column*xdelta)+22, Window.getY(row*ydelta)+50);
    }
    
}