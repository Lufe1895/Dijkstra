/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.event.MouseEvent;

/**
 *
 * @author luisl
 */
public class LineThread extends Thread{
    private MouseEvent e;
    private Board board;
    
    public LineThread(MouseEvent e, Board board){
        this.e=e;
        this.board = board;
    }

    @Override
    public void run() {
        board.getLine().setEnd(new Circle(e.getPoint().x,e.getPoint().y));
        board.getLine().draw(board.getGraphics());
        board.repaint();
    }
    
    
}
