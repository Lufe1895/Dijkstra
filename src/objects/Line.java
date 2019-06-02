/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 *
 * @author luisl
 */
public class Line implements Serializable{
    private Circle start;
    private Circle end;
    
    private Integer name;
    
    private Color color;
    
    public Line(Circle start, Circle end, Integer name) {
        this.start=start;
        this.end = end;
        this.name = name;
        this.color = Color.BLACK;
    }
    
    public Line(Circle start, Circle end) {
        this.start=start;
        this.end = end;
        this.name = null;
        this.color = Color.BLACK;
    }
    
    public Line(){
        this.start = null;
        this.end = null;
        this.name = null;
        this.color = Color.RED;
    }
    
    public void draw(Graphics g){
        Graphics2D line = (Graphics2D) g;
        line.setColor(color);
        line.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        line.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
        
        float px = (float)(start.getX()+(end.getX()-start.getX())*0.75);
        float py = (float)(start.getY()+(end.getY()-start.getY())*0.75 + 15);
        if(name != null){
            line.drawString(String.valueOf(name), px, py);
        }
    }
    
    public Boolean isOver(int x, int y){
        float px = (float)(start.getX()+(end.getX()-start.getX())*0.75);
        float py = (float)(start.getY()+(end.getY()-start.getY())*0.75 + 15);
        if((x >=px && x<= px+10) && (y <=py && y>= py-10))
            return true;
        else
            return false;
    }

    public Circle getStart() {
        return start;
    }

    public void setStart(Circle start) {
        this.start = start;
    }

    public Circle getEnd() {
        return end;
    }

    public void setEnd(Circle end) {
        this.end = end;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }    
    
}
