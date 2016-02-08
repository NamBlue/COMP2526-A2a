package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Herbivore extends JPanel implements Inhabitants {
    private Cell cell;
    private int hunger;
    
    public Herbivore(Cell location) {
        cell = location;
        hunger = 0;
    }
    public void init() {
        cell.add(this);
    }
    
    public void eat() {
        hunger = 0;
    }
    
    public int hungry() {
        return hunger;
    }
    
    public void paintComponent(Graphics draw) {
        draw.setColor(Color.yellow);
        draw.fillRect(0, 0, getWidth() -1 ,getHeight() -1);
    }
}
