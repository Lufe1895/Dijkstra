/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 *
 * @author luisl
 */
public class Circle implements Serializable{
    public static final Integer RADIO = 60;
    
    private Integer x;
    private Integer y;
    private Boolean path;
    
    private Color color;
    
    private Integer name;

    public Circle(Integer x, Integer y, int name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.path = false;
        
        color = Color.BLACK;
    }
    
        public Circle(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.name = null;
        
        color = Color.BLACK;
    }
    
    public void paint(Graphics g){
        g.setFont(new Font("Arial",0,15));
        Graphics2D circle = (Graphics2D)g;
        circle.setColor(getColor());
        circle.fillOval(x-RADIO,y-RADIO,RADIO*2,RADIO*2);
        circle.drawOval(x-RADIO,y-RADIO,RADIO*2,RADIO*2);
        
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(name), x-RADIO+10, y-RADIO+10);
    }

    public Integer getName() {
        return name;
    }

    public Boolean getPath() {
        return path;
    }

    public void setPath(Boolean path) {
        this.path = path;
    }

    public void setName(Integer name) {
        this.name = name;
    }
    
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
