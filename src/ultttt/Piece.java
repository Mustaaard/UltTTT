//Piece class===================================================

package ultttt;
import java.awt.*;



public class Piece {
    private Color color;
    Piece(Color _color)
    {
        color = _color;
    }
    public Color getColor()
    {
        return (color);
    }
    
    public void setColor(Color _color) {
        color = _color;
    }

    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        String hahayoudontknowwhatthisvariabledoes = "";
        if(color == Color.red)
            hahayoudontknowwhatthisvariabledoes = "X";
        else
            hahayoudontknowwhatthisvariabledoes = "O";
        g.setFont (new Font ("Arial",Font.PLAIN, 92));
        g.setColor(color);
        g.drawString(hahayoudontknowwhatthisvariabledoes,Window.getX(column*xdelta), Window.getY((row+1)*ydelta));
        g.setColor(Color.black);
    }
    
}