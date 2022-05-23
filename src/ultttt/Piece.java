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
        if (big && notAgain) {
            g.setColor(Color.green);
            if (winner.equals("X")) {
                g.setFont(new Font("Papyrus", Font.PLAIN, 250));
                g.drawString("X", column + 7, row);
            } else {
                g.setFont(new Font("Ink Free", Font.PLAIN, 241));
                g.drawString("O", column + 7, row - 3);
            }
        } else if (!big) {
            if (hahayoudontknowwhatthisvariabledoes.equals("X")){
                g.setFont(new Font("Papyrus", Font.PLAIN, 58));
                g.setColor(Color.black);
                g.drawString(hahayoudontknowwhatthisvariabledoes, column+4, row - 10);
            }
            else{
                g.setFont(new Font("Ink Free", Font.PLAIN, 78));
                g.setColor(Color.black);
                g.drawString(hahayoudontknowwhatthisvariabledoes, column, row - 1);
            }
        }
    }
    
}