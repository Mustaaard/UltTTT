//Player class===================================================




package ultttt;
import java.awt.*;

public class Player {
    private static final int numPlayers = 2;
    public static Player players[] = new Player[numPlayers];
    private static Player currentPlayer = null;
    private int score = 0;
    private Color color = null;
    private static boolean firstTime = true;
    
            
    public static void Reset() {
        if(firstTime) {
            players[0] = new Player(Color.red);
            players[1] = new Player(Color.yellow);
            firstTime = false;
        }
        currentPlayer = players[0];
    }
    
    public static Player getCurrentPlayer() {
        return(currentPlayer);
    }
    
    public static void switchCurrentPlayer() {
        if(currentPlayer == players[0]) {
            currentPlayer = players[1];
        }
        else {
            currentPlayer = players[0];
        }
    }
    
    Player(Color _color) {
        color = _color;
    }
    
    public Color getColor() {
        return(color);
    }
    
    public int getScore() {
        return score;
    }
    
    public void addToScore(int points) {
        score += points;
    }
}