//Connect4 class===================================================


package ultttt;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

public class UltTTT extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    boolean winfirst=true;
     boolean drawfirst=true;
    Image image;
    Graphics2D g;
    public static boolean start;
    public static boolean blackout;
    public static boolean extraTurns;
    sound bfgSound = null;
    sound NumberOneVictoryRoyale = null;
    sound boomSound = null;
    Image tictac;
    
    public static void main(String[] args) {
        UltTTT frame = new UltTTT();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public UltTTT() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.BUTTON1 == e.getButton() ) {
                    if(!Board.won) {
                        for(int i = 0;i < 3;i++) {
                            for(int o = 0;o < 3;o++) {
                                boomSound = new sound("Boom.wav");
                                    Board.AddPiece(i,o,e.getX(),e.getY());
                            }
                        }
                    }
                }

                if (e.BUTTON3 == e.getButton()) {

                }
                repaint();
            }
        });
            

    addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {
            
            repaint();
        }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseMoved(MouseEvent e) {
            Board.setMousePos(e.getX()-Window.getX(0),e.getY()-Window.getY(0));
            repaint();
        }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_1 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("Last.wav");
                } else if (e.VK_2 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("ff.wav");
                } else if (e.VK_3 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("chill.wav");
                } else if (e.VK_4 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("RipandTear.wav");
                }else if (e.VK_5 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("mj.wav");
                    } 
                    else if (e.VK_6 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("bdc.wav");
                    } 
                    else if (e.VK_7 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("WalnutBowling.wav");
                    }
                    else if (e.VK_8 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("LinkinPark.wav");
                    }
                    else if (e.VK_9 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("jojo.wav");
                    }
                    else if (e.VK_0 == e.getKeyCode()) {
                    bfgSound.stopPlaying=true;
                    bfgSound = new sound("vietnam.wav");
                    }
                    else if (e.VK_SPACE == e.getKeyCode()) {
                    if (!start) {
                        start = true;
                    }
                } else if (e.VK_B == e.getKeyCode()) {
                    if (!blackout) {
                        blackout = true;
                    } else if (blackout && !start) {
                        blackout = false;
                    }
                } else if (e.VK_E == e.getKeyCode()) {
                    if (!extraTurns) {
                        extraTurns = true;
                    } else if (extraTurns && !start) {
                        extraTurns = false;
                    }
                } else if (e.VK_ESCAPE == e.getKeyCode()) {
                    bfgSound.stopPlaying = !bfgSound.stopPlaying;
                    reset();
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        
        g.setColor(Color.blue);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        if (!blackout) {
            g.setColor(Color.white);
        } 
        else {
            g.setColor(Color.black); 
        }
            
            g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        
        if (start) {
            Board.Draw(g);
        } else {
            if (!blackout) 
                g.setColor(Color.black);
             else
                g.setColor(Color.white);            g.setFont(new Font("Impact", Font.PLAIN, 25));
            g.drawString("How To Play Ultamate Tic Tac Toe:", (Window.getWidth2() / 2) - 150, (Window.getHeight2() / 2) - 150);
            g.setFont(new Font("Impact", Font.PLAIN, 20));
            g.drawString("* Each turn, you mark one of the small squares.", 30, (Window.getHeight2() / 2) - 120);
            g.drawString("* When you get three in a row on a small board, you’ve won that board", 30, (Window.getHeight2() / 2) - 100);
            g.drawString("* To win the game, you need to win three small boards in a row.", 30, (Window.getHeight2() / 2) - 80);
            g.drawString("* You don’t get to pick which of the nine boards to play on.", 30, (Window.getHeight2() / 2) - 60);
            g.drawString("That’s determined by your opponent’s previous move.", 30, (Window.getHeight2() / 2) - 40);
            g.drawString("Whichever square they pick, is the board you must play in next.", 30, (Window.getHeight2() / 2) - 20);
            g.drawString("* If your opponent sends you to a board that's already been won or is full,", 30, (Window.getHeight2() / 2) - 0);
            g.drawString("you may play anywhere that is not full or hasn't been won.", 30, (Window.getHeight2() / 2) + 20);
            g.drawString("*You may change the music with the number keys.", 30, (Window.getHeight2() / 2) + 40);
            if (blackout)
                g.setColor(Color.green);
            g.drawString("PRESS B TO ACTIVATE BLACKOUT MODE", (Window.getWidth2() / 2) - 145, (Window.getHeight2() / 2) + 70);
            if (!blackout) 
                g.setColor(Color.black);
            else
                g.setColor(Color.white);            
            if (extraTurns) 
                g.setColor(Color.green);
            g.drawString("PRESS E TO ACTIVATE EXTRA TURN MODE", (Window.getWidth2() / 2) - 145, (Window.getHeight2() / 2) + 90);
            if (!blackout) 
                g.setColor(Color.black);
             else 
                g.setColor(Color.white);
            g.drawString("PRESS SPACE TO PLAY", (Window.getWidth2() / 2) - 145, (Window.getHeight2() / 2) + 110);
            g.drawString("PRESS ESCAPE TO RESTART", (Window.getWidth2() / 2) - 145, (Window.getHeight2() / 2) + 130);
            g.drawImage(tictac, (Window.getWidth2() / 2) - 105, Window.getHeight2()-110, Window.getWidth2()/3, Window.getHeight2()/3, this);
        }
        
        for(int i = 0;i < 3;i++)
            for(int o = 0;o < 3;o++)
                Board.box[i][o].Draw(g);
        
        if(Board.won) {
            if (winfirst) {
            winfirst = false;
            bfgSound.stopPlaying = !bfgSound.stopPlaying;
            NumberOneVictoryRoyale = new sound("win.wav");
            }
            g.setColor(Color.red);
            g.setFont (new Font ("Impact",Font.PLAIN, 150));
            g.drawString(Board.winner + " Wins", 100, 350);
        }
        else if(Board.draw) {
            if (drawfirst) {
            drawfirst = false;
            bfgSound.stopPlaying = !bfgSound.stopPlaying;
            NumberOneVictoryRoyale = new sound("Better Call Saul.wav");
            }
            g.setColor(Color.red);
            g.setFont (new Font ("Arial",Font.PLAIN, 150));
            g.drawString("Draw", 100, 350);
        }
        
        gOld.drawImage(image, 0, 0, null);
    }

////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .1;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
    
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        Board.Reset();
        Player.Reset();
        if(NumberOneVictoryRoyale != null)
            NumberOneVictoryRoyale.stopPlaying = !NumberOneVictoryRoyale.stopPlaying;
        NumberOneVictoryRoyale = null;
        bfgSound = new sound("ChugJug.wav");
        start = false;
        blackout = false;
        extraTurns = false;
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
                tictac = Toolkit.getDefaultToolkit().getImage("./ticity tacity.jpg");
            }

            reset();
            

        }
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
    
    class sound implements Runnable {
    Thread myThread;
    File soundFile;
    public boolean donePlaying = false;
    public boolean stopPlaying = false;
    public boolean pausePlaying = false;
    sound(String _name)
    {
        soundFile = new File(_name);
        myThread = new Thread(this);
        myThread.start();
    }
    public void run()
    {
        try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat format = ais.getFormat();
    //    System.out.println("Format: " + format);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info);
        source.open(format);
        source.start();
        int read = 0;
        byte[] audioData = new byte[16384];
        while (!stopPlaying && read > -1){
            
            if (pausePlaying)
                read = 0;
            else
                read = ais.read(audioData,0,audioData.length);
           
                
                
            if (read >= 0) {
                source.write(audioData,0,read);
            }

           
            
        }
        donePlaying = true;

        source.drain();
        source.close();
        }
        catch (Exception exc) {
            System.out.println("error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
    }
}