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

    public void draw(Graphics2D g, int row, int column, int xdelta, int ydelta, boolean big, boolean notAgain, String winner) {
        if (!big) {
            g.setFont(new Font("Arial", Font.PLAIN, 80));
            g.setColor(Color.black);
            g.drawString(hahayoudontknowwhatthisvariabledoes, column, row);
        } else if (big && notAgain) {
            g.setFont(new Font("Arial", Font.PLAIN, 250));
            g.setColor(Color.black);
            if (winner.equals("X")) {
                g.drawString("X", column + 7, row);
            } else {
                g.drawString("O", column + 7, row);
            }
        }
    }
    
}