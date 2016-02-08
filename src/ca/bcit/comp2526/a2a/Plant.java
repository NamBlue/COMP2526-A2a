package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Plant extends JPanel implements Inhabitants{
        private Cell cell;
        
    public Plant (Cell location) {
        cell = location;
    }
    public void init() {
        cell.add(this);
    }
    public void paintComponent(Graphics draw) {
        draw.setColor(Color.green);
        draw.fillRect(0, 0, getWidth() -1 ,getHeight() -1);
    }
}
