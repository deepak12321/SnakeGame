import java.util.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
//import java.util.Random;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.Graphics;

public class GamePanel extends JPanel implements ActionListener
{
    static final int screen_width=600;
    static final int screen_hight = 600;
    static final int unit_size = 25;
    static final int game_units = (screen_width*screen_hight)/unit_size;
    static final int DELAY = 75;
    final int x [] = new int [game_units];//To store x Coordinate 
    final int y [] = new int [game_units];//To store y coordinate
    int body_parts =6;
    int applesEaten =0;
    int applesX;
    int applesY;
    char directions ='R';//R=right
    boolean running = false;
    Timer timer;
    Random random;
    
    

    GamePanel()
    {
        random = new Random();
        this.setPreferredSize(new Dimension(screen_width,screen_hight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame()
    {
        newApple();
        running =true;
        timer = new Timer(DELAY,this);//This is used because we are using action listener
        timer.start();
        
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        if(running)
        {
//            for (int i = 0; i < (screen_hight/unit_size); i++) 
//            {
//                g.drawLine(i*unit_size, 0, i*unit_size, screen_hight);
//                g.drawLine(0, i*unit_size,screen_width , i*unit_size);
//            }
            g.setColor(Color.RED);
            g.fillOval(applesX, applesY, unit_size, unit_size);
            for (int i = 0; i < body_parts; i++)
            {
                if(i==0)
                {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], unit_size, unit_size);
                }
                else
                {
                    g.setColor(new Color(45,180,0));
//                  g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], unit_size, unit_size);
                }
            }
            g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,30));
        
        FontMetrics metrics = getFontMetrics(g.getFont());

        g.drawString("Score: "+applesEaten, (screen_width - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        }
        else
        {
            gameOver(g);
        }
    }
    public void newApple()
    {
        applesX= random.nextInt((int)(screen_width/unit_size))*unit_size;
        applesY= random.nextInt((int)(screen_hight/unit_size))*unit_size;
    }
    public void move()
    {
        for (int i = body_parts; i >0; i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch(directions)
        {
            case'U':
                y[0]=y[0]- unit_size;
                break;
            case'D':
                y[0]=y[0]+ unit_size;
                break;
            case'L':
                x[0]=x[0]- unit_size;
                break;
            case'R':
                x[0]=x[0]+ unit_size;
                break;
        }
        
    }
    public void checkApple()
    {
        if((x[0]==applesX) &&(y[0]==applesY))
        {
            body_parts++;
            applesEaten++;
            newApple();
        }
        
    }
    public void checkCollision()
    {
        //check head collision with the body
        for (int i = body_parts; i >0; i--) 
        {
            if((x[0]==x[i]) && (y[0]==y[i]))
            {
                running=false;
            }
            
        }
        //check if head touches left border
        if(x[0]<0)
        {
            running =false;
        }
        //check if head touches right border
        if(x[0]>screen_width)
        {
            running =false;
        }
        //check if head touches top border
        if(y[0]<0)
        {
            running =false;
        }
        //check if head touches bottom border
        if(y[0]>screen_hight)
        {
            running =false;
        }
        if(!running)
        {
            timer.stop();
        }
 
    }
    public void gameOver(Graphics g)
    {
        //gameovertext
//        g.setColor(Color.red);
//        g.setFont(new Font("Ink Free",Font.BOLD,75));
//        FontMetrics metrics = getFontMetrics(g.getFont());
        g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (screen_width - metrics.stringWidth("Game Over"))/2, screen_width/2);
        g.drawString("Game Over",(screen_width *metrics.stringWidth(" Game Over"))/2,screen_hight/2);
        
        
         g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (screen_width - metrics1.stringWidth("Game Over"))/2, screen_width/2);
        g.drawString("Game Over",(screen_width *metrics1.stringWidth(" Game Over"))/2,screen_hight/2);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running)
        {
            move();
            checkApple();
            checkCollision();
        }
        repaint();
        
    }

  
    
    public class MyKeyAdapter extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    if(directions!='R')
                    {
                        directions = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(directions!='L')
                    {
                        directions = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(directions!='D')
                    {
                        directions = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(directions!='U')
                    {
                        directions = 'D';
                    }
                    break;
                   
            }
            
        }
        
    }
}
