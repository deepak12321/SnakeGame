/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class GameFrame extends JFrame 
{
    GameFrame()
    {
        this .add(new GamePanel());//instanting the GamePanel
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//Frame will appear in the middel of the screen
    }
    
}
