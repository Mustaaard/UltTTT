//Piece class===================================================

package ultttt;
import java.awt.*;



public class Piece {
    private Color color;
    private String hahayoudontknowwhatthisvariabledoes;
    Piece(Color _color,String _letter)
    {
        color = _color;
        hahayoudontknowwhatthisvariabledoes = _letter;
    }
    public Color getColor()
    {
        return (color);
    }
    
    public void setColor(Color _color) {
        color = _color;
    }

    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        
        g.setFont (new Font ("Arial",Font.PLAIN, 92));
        g.setColor(color);
        g.drawString(hahayoudontknowwhatthisvariabledoes,Window.getX(column*xdelta), Window.getY((row)*ydelta));
        g.setColor(Color.black);
    }
    
}