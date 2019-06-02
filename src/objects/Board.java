/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import gui.PrincipalFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Vector;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author luisl
 */
public class Board extends JPanel implements MouseListener,MouseMotionListener,ActionListener{
    
    private ArrayList<Circle> circles;
    private ArrayList<Line> lines;
    
    private Point point;
    private Circle aux1;
    private Circle aux2;
    
    private Circle aux;
    
    public Integer result;
    
    private Matrix matrix;
    
    private Integer circleIndex;
    private Integer index2;
    private Integer name = 0;
    
    private JPopupMenu ppmColors;
    
    private Dijkstra dijkstra;
    
    LineThread lt;
    
    private JMenuItem blue;
    private JMenuItem black;
    private JMenuItem yellow;
    private JMenuItem orange;
    private JMenuItem red;
    private JMenuItem gray;
    private JMenuItem pink;
    private JMenuItem green;
    private JMenuItem oColor;
    
    private Line line;

    public Board() {
        this.circles = new ArrayList<>();
        this.lines = new ArrayList<>();
        line = new Line();
        matrix = new Matrix();
        
        setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        ppmColors = new JPopupMenu();
        
        blue = new JMenuItem("Blue");
        black = new JMenuItem("Black");
        yellow = new JMenuItem("Yellow");
        orange = new JMenuItem("Orange");
        red = new JMenuItem("Red");
        gray = new JMenuItem("Gray");
        pink = new JMenuItem("Pink");
        green = new JMenuItem("Green");
        oColor = new JMenuItem("Other Color");
        
        ppmColors.add(blue);
        ppmColors.add(black);
        ppmColors.add(yellow);
        ppmColors.add(orange);
        ppmColors.add(red);
        ppmColors.add(gray);
        ppmColors.add(pink);
        ppmColors.add(green);
        ppmColors.add(oColor);
        
        blue.addActionListener(this);
        black.addActionListener(this);
        yellow.addActionListener(this);
        orange.addActionListener(this);
        red.addActionListener(this);
        gray.addActionListener(this);
        pink.addActionListener(this);
        green.addActionListener(this);
        oColor.addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Circle circle : circles) {
            circle.paint(g);
        }  
        for (Line line : lines) {
            line.draw(g);
        }
        if(aux1 != null){
            line.draw(g);
        }
    }
    
    //--------------------------------------------------------------------------Clicked

    @Override
    public void mouseClicked(MouseEvent e) {
        setPoint(e.getPoint());
        if(e.getButton() == 3 && isSelected()){
                ppmColors.show(this,e.getX(),e.getY()); 
            }
        if(PrincipalFrame.bCircle){
            if(e.getButton() == 1){
                this.getCircles().add(new Circle(e.getX(), e.getY(), giveName()));
                repaint();
            }
        }else if(PrincipalFrame.bErase){
            if(e.getButton() == 1){
                int count=0;
                if(circles.size()>0){
                    try{
                        for (Circle circle : circles) {
                            if(new Rectangle(circle.getX()-Circle.RADIO/2,circle.getY()-Circle.RADIO/2,Circle.RADIO*2,Circle.RADIO*2).contains(e.getPoint())){
                                while(hasLines(count));
                                for(int i=0;i<this.name;i++){
                                    this.matrix.setMatrixAdy(circle.getName()-1, i, 0);
                                    this.matrix.setMatrixVal(circle.getName()-1, i,0);
                                    this.matrix.setMatrixAdy(i,circle.getName()-1, 0);
                                    this.matrix.setMatrixVal(i,circle.getName()-1,0);
                                }
                                circles.remove(count);
                                repaint();
                            }
                            count++;
                        }
                    }catch(ConcurrentModificationException z){
                    
                    }
                }
            }
        }
    }
    
    //--------------------------------------------------------------------------Pressed

    @Override
    public void mousePressed(MouseEvent e) {
        if(!PrincipalFrame.bLine){
            int index=0;
            for (Circle circle : circles) {
                if(new Rectangle(circle.getX()-Circle.RADIO,circle.getY()-Circle.RADIO,Circle.RADIO+50,Circle.RADIO+50).contains(e.getPoint())){
                    aux=circle;
                    index2=index;
                    break;
                }
                index++;
            }
        }else{
            if(e.getButton() == 1){
                for(Circle circle:circles){
                    if(new Rectangle(circle.getX()-Circle.RADIO/2,circle.getY()-Circle.RADIO/2,Circle.RADIO*2,Circle.RADIO*2).contains(e.getPoint())){
                        try{
                            if(aux1 == null){
                                aux1=circle;
                                line.setStart(aux1);
                            }else if(aux1 != circle){
                                lt.start();
                                repaint();
                                aux2 = circle;
                                if(!exists(aux1,aux2)){
                                    String name = JOptionPane.showInputDialog("Give me a weight: ");
                                    this.lines.add(new Line(aux1,aux2,Integer.parseInt(name)));
                                    repaint();
                                    aux1=null;
                                    aux2=null;
                                }else{
                                    aux1=null;
                                    aux2=null;
                                }
                            }
                        }catch(NumberFormatException ex){
                            aux1=null;
                            aux2=null;
                        }
                    }
                }
            }
        }
    }
    
    //--------------------------------------------------------------------------Released

    @Override
    public void mouseReleased(MouseEvent e) {
        aux=null;
        circleIndex=-1;
        index2=-1;
        if(PrincipalFrame.bLine){
            if(e.getButton() == 1){
                for(Circle circle:circles){
                    if(new Rectangle(circle.getX()-Circle.RADIO/2,circle.getY()-Circle.RADIO/2,Circle.RADIO*2,Circle.RADIO*2).contains(e.getPoint())){
                        try{
                            if(aux1 == null){
                                aux1=circle;
                                line.setStart(aux1);
                            }else if(aux1 != circle){
                                repaint();
                                aux2 = circle;
                                if(!exists(aux1,aux2)){
                                    String name = JOptionPane.showInputDialog("Give me a weight: ");
                                    this.lines.add(new Line(aux1,aux2,Integer.parseInt(name)));
                                    repaint();
                                    matrix.setMatrixAdy(aux1.getName()-1, aux2.getName()-1, 1);
                                    matrix.setMatrixAdy(aux2.getName()-1, aux1.getName()-1, 1);
                                    matrix.setMatrixVal(aux1.getName()-1, aux2.getName()-1, Integer.parseInt(name));
                                    matrix.setMatrixVal(aux2.getName()-1, aux1.getName()-1, Integer.parseInt(name));
                                    System.out.println("\n\n");
                                }
                            }
                        }catch(NumberFormatException ex){
                        }
                    }
                }
            }
        }
        aux1=null;
        aux2=null;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

    public void setCircles(ArrayList<Circle> circles) {
        this.circles = circles;
    }
    
    public boolean exists(Circle c1, Circle c2){
        for (Line line : lines) {
            if((line.getEnd() == c1 || line.getEnd() == c2) && (line.getStart() == c1 || line.getStart() == c2)){
                return true;
            }
        }
        return false;
    }
    
    //--------------------------------------------------------------------------Dragged

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!PrincipalFrame.bLine){
            if(aux!=null){
                circles.set(index2, new Circle(e.getX(),e.getY(),aux.getName()));
                circles.get(index2).setColor(aux.getColor());
            
                int counter = 0;
                for (Line line : lines) {
                
                    if(line.getStart().getName() == aux.getName()){
                        this.lines.set(counter, new Line(circles.get(index2),line.getEnd(),line.getName()));
                    } else if(line.getEnd().getName() == aux.getName()){
                        this.lines.set(counter, new Line(line.getStart(),circles.get(index2),line.getName()));
                    }
                    counter++;
                }
            }
            repaint();
        }else if(aux1 != null){
            lt = new LineThread(e,this);
            lt.start();
        }
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Boolean isSelected(){
        circleIndex=0;
        for (Circle circle : circles) {
            if(new Rectangle(circle.getX()-Circle.RADIO ,circle.getY()-Circle.RADIO ,Circle.RADIO+50,Circle.RADIO+50).contains(getPoint())){
                return true;
            }
            circleIndex++;
        }
        return false;
    }
    
    public boolean hasLines(int circleIndex){
        int count=0;
        for (Line line : lines) {
            if(new Rectangle(circles.get(circleIndex).getX()-Circle.RADIO ,circles.get(circleIndex).getY()-Circle.RADIO ,Circle.RADIO+50,Circle.RADIO+50).contains(line.getStart().getX(),line.getStart().getY())
                    || new Rectangle(circles.get(circleIndex).getX()-Circle.RADIO ,circles.get(circleIndex).getY()-Circle.RADIO ,Circle.RADIO+50,Circle.RADIO+50).contains(line.getEnd().getX(),line.getEnd().getY())){
                lines.remove(count);
                return true;
            }
            count++;
        }
        return false;
    }
    
    public Integer giveName(){
        name++;
        return name;
    }

    public Circle getAux1() {
        return aux1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Blue":
                this.circles.get(circleIndex).setColor(Color.BLUE);
                this.repaint();
                break;
                
            case "Black":
                this.circles.get(circleIndex).setColor(Color.BLACK);
                this.repaint();
                break;
                
            case "Yellow":
                this.circles.get(circleIndex).setColor(Color.YELLOW);
                this.repaint();
                break;
                
            case "Orange":
                this.circles.get(circleIndex).setColor(Color.ORANGE);
                this.repaint();
                break;
                
            case "Red":
                this.circles.get(circleIndex).setColor(Color.RED);
                this.repaint();
                break;
                
            case "Gray":
                this.circles.get(circleIndex).setColor(Color.GRAY);
                this.repaint();
                break;
                
            case "Pink":
                this.circles.get(circleIndex).setColor(Color.PINK);
                this.repaint();
                break;
                
            case "Green":
                this.circles.get(circleIndex).setColor(Color.GREEN);
                this.repaint();
                break;
            case "Other Color":
                Color b = JColorChooser.showDialog(null,"Hola",Color.BLACK);
                this.circles.get(circleIndex).setColor(b);
                this.repaint();
                break;
        }
    }
    
    public void save(File a) throws FileNotFoundException, IOException{
        Vector vector = new Vector();
        vector.add(circles);
        vector.add(lines);
        try{
            FileOutputStream out = new FileOutputStream(a);
            ObjectOutputStream ob = new ObjectOutputStream(out);
            ob.writeObject(vector);
            ob.close();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "File not found.");
        }
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }
    
    public void open(File f) throws IOException, ClassNotFoundException{
        Vector vector = new Vector();
        ArrayList<Circle> auxCircles = new ArrayList<>();
        ArrayList<Line> auxLines = new ArrayList<>();
        try{
            FileInputStream in = new FileInputStream(f);
            ObjectInputStream ob = new ObjectInputStream(in);
            vector = (Vector) ob.readObject();
            for (int i = 0; i < vector.size(); i++) {
                switch(i){
                    case 0:
                        auxCircles=(ArrayList<Circle>)vector.get(i);
                        break;
                    case 1:
                        auxLines = (ArrayList<Line>)vector.get(i);
                        break;
                }
                this.setCircles(auxCircles);
                this.setLines(auxLines);
                this.repaint();
            }
        }catch(FileNotFoundException a){
            JOptionPane.showMessageDialog(null, "File not found.");
        }catch(IOException a){
            
        }catch(ClassNotFoundException a){
            
        }
    }
    
    public void solve(){
        String ini=JOptionPane.showInputDialog("Input start node.");
        result=0;
        String fin=JOptionPane.showInputDialog("Input finish node.");
        dijkstra = new Dijkstra(matrix,circles.size(),Integer.valueOf(ini)-1,Integer.valueOf(fin)-1,this);
        dijkstra.dijkstra();
        NodoOperacion op = new NodoOperacion();
        op=dijkstra.getAuxi();
        while(op.getPredecesor() != null){
            circles.get(op.getPredecesor().getNombre()).setColor(Color.GREEN);
            circles.get(op.getPredecesor().getNombre()).setPath(true);
            op=op.getPredecesor();
        }
        circles.get(Integer.valueOf(fin)-1).setColor(Color.GREEN);
        circles.get(Integer.valueOf(fin)-1).setPath(true);
        for (Line line1 : lines) {
            if(line1.getEnd().getPath()== true && line1.getStart().getPath()== true){
                line1.setColor(Color.green);
                result+=line1.getName();
            }
        }
        repaint();
        dijkstra = null;
    }
    
    public void clean(){
        circles.clear();
        lines.clear();
        name = 0;
        this.repaint();
    }
    
}
