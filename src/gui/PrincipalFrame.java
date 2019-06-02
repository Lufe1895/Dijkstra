/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import objects.Board;

/**
 *
 * @author luisl
 */
public class PrincipalFrame extends JFrame implements Serializable{
    
    private JButton btnCircle;
    private JButton btnLine;
    private final JButton btnErase;
    private final JButton btnSolve;
    private final JButton btnExit;
    private final JButton btnSave;
    private final JButton btnOpen;
    private final JButton btnClean;
    
    private final JPanel pnlNorth;
    private final JPanel pnlCenter;
    private final JPanel pnlSouth;
    private final JPanel pnlVoidLeft;
    private final JPanel pnlVoidRight;
    private final JPanel pnlImages;
    private final JPanel pnlCorrection;
    private final JPanel pnlOutput;
    private final JPanel pnlSolve;
    private final JPanel pnlAll;
    private final JPanel pnlAbove;
    
    private JLabel lblResult;
    
    private JFileChooser fChooser;
    
    public static Boolean bCircle = false;
    public static Boolean bLine = false;
    public static Boolean bErase = false;
    
    private final Board board;
    
    private final BevelBorder border;
    
    public PrincipalFrame(){
        
        super("Dijkstra");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(1200,800);
        super.setVisible(true);
        super.setExtendedState(MAXIMIZED_BOTH);
        super.setLayout(new BorderLayout());
        
        fChooser = new JFileChooser();
        lblResult=new JLabel();
        
        board = new Board();
        pnlAll = new JPanel(new BorderLayout());
        
        pnlAbove = new JPanel( new BorderLayout());
        
        super.add(pnlAll);
        
        pnlNorth = new JPanel();
        pnlCenter = new JPanel();
        pnlSouth = new JPanel();
        pnlVoidLeft = new JPanel();
        pnlVoidRight = new JPanel();
        
        border = new BevelBorder(BevelBorder.LOWERED);
        
        pnlNorth.setPreferredSize(new Dimension(0,150));        
        pnlSouth.setPreferredSize(new Dimension(0,100));
        pnlVoidLeft.setPreferredSize(new Dimension(50,0));
        pnlVoidRight.setPreferredSize(new Dimension(50,0));
        pnlCenter.setLayout(new BorderLayout());
        pnlNorth.setLayout(new GridLayout(1,2));
        pnlSouth.setLayout(new GridLayout(1,2));
        
        pnlNorth.setBackground(Color.red);
        pnlSouth.setBackground(Color.red);
        
        pnlAll.add(pnlAbove);
        
        pnlAbove.setOpaque(false);
        pnlAbove.setPreferredSize(new Dimension(1800,1050));
        
        pnlAbove.add(pnlVoidLeft,BorderLayout.WEST);
        pnlAbove.add(pnlVoidRight,BorderLayout.EAST);
        pnlAbove.add(pnlCenter,BorderLayout.CENTER);
        pnlCenter.add(pnlNorth,BorderLayout.NORTH);
        pnlCenter.add(pnlSouth,BorderLayout.SOUTH);
        
        board.setBorder(border);
                
        pnlCenter.add(board,BorderLayout.CENTER);
        
        pnlImages = new JPanel(new FlowLayout((int) LEFT_ALIGNMENT,10,50));
        pnlCorrection = new JPanel(new FlowLayout((int) RIGHT_ALIGNMENT,15,50));
        pnlSolve = new JPanel(new FlowLayout(FlowLayout.RIGHT,40,5));
        pnlOutput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlNorth.add(pnlImages);
        pnlNorth.add(pnlCorrection);
        pnlSouth.add(pnlOutput);
        pnlSouth.add(pnlSolve);
        
        pnlNorth.setOpaque(false);
        pnlCenter.setOpaque(false);
        pnlSouth.setOpaque(false);
        pnlVoidLeft.setOpaque(false);
        pnlVoidRight.setOpaque(false);
        pnlImages.setOpaque(false);
        pnlCorrection.setOpaque(false);
        pnlOutput.setOpaque(false);
        pnlSolve.setOpaque(false);
        //----------------------------------------------------------------------Up to here, modification and instanciation of all panels
        
        ImageIcon circle = new ImageIcon(getClass().getResource("../Images/circle.png"));
        ImageIcon circle2 = new ImageIcon(circle.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        ImageIcon line = new ImageIcon(getClass().getResource("../Images/arrow.png"));
        ImageIcon line2 = new ImageIcon(line.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        ImageIcon erase = new ImageIcon(getClass().getResource("../Images/erase.png"));
        ImageIcon erase2 = new ImageIcon(erase.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        ImageIcon solve = new ImageIcon(getClass().getResource("../Images/solve.png"));
        ImageIcon solve2 = new ImageIcon(solve.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        ImageIcon exit = new ImageIcon(getClass().getResource("../Images/exit.png"));
        ImageIcon exit2 = new ImageIcon(exit.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        ImageIcon open = new ImageIcon(getClass().getResource("../Images/abrir.png"));
        ImageIcon open2 = new ImageIcon(open.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        ImageIcon save = new ImageIcon(getClass().getResource("../Images/save.png"));
        ImageIcon save2 = new ImageIcon(save.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        ImageIcon clean = new ImageIcon(getClass().getResource("../Images/clean.png"));
        ImageIcon clean2 = new ImageIcon(clean.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        
        btnCircle = new JButton(circle2);
        btnLine = new JButton(line2);
        btnErase = new JButton(erase2);
        btnExit = new JButton(exit2);
        btnErase.setPreferredSize(new Dimension(90,90));
        btnCircle.setPreferredSize(new Dimension(90,90));
        btnLine.setPreferredSize(new Dimension(90,90));
        btnSolve = new JButton(solve2);
        btnSolve.setPreferredSize(new Dimension(90,90));
        btnExit.setPreferredSize(new Dimension(90,90));
        btnSolve.setBackground(Color.GREEN);
        btnErase.setBackground(Color.red);
        btnClean = new JButton(clean2);
        btnClean.setPreferredSize(new Dimension(90,90));
        btnClean.setBackground(Color.red);
        
        btnSave = new JButton(save2);
        btnSave.setBackground(Color.LIGHT_GRAY);
        btnOpen = new JButton(open2);
        btnOpen.setBackground(Color.LIGHT_GRAY);
        btnSave.setPreferredSize(new Dimension(90,90));
        btnOpen.setPreferredSize(new Dimension(90,90));
        
        btnCircle.setBackground(Color.LIGHT_GRAY);
        btnLine.setBackground(Color.LIGHT_GRAY);
        
        btnLine.setCursor(new Cursor(HAND_CURSOR));
        btnCircle.setCursor(new Cursor(HAND_CURSOR));
        btnErase.setCursor(new Cursor(HAND_CURSOR));
        btnSolve.setCursor(new Cursor(HAND_CURSOR));
        btnExit.setCursor(new Cursor(HAND_CURSOR));
        
        pnlImages.add(btnCircle);
        pnlImages.add(btnLine);
        pnlImages.add(btnErase);
        pnlCorrection.add(btnOpen);
        pnlCorrection.add(btnSave);
        pnlSolve.add(lblResult);
        pnlSolve.add(btnSolve);
        pnlSolve.add(btnClean);
        pnlSolve.add(btnExit);        
        //----------------------------------------------------------------------Definición de las funciones de los botones
        
        btnExit.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        btnCircle.addActionListener((ActionEvent e) -> {
            if(!bCircle){
                btnCircle.setBackground(Color.DARK_GRAY);
                bCircle=true;
                board.setCursor(new Cursor(CROSSHAIR_CURSOR));
            }else{
                btnCircle.setBackground(Color.LIGHT_GRAY);
                bCircle=false;
            }
            if(bLine || bErase){
                btnLine.setBackground(Color.LIGHT_GRAY);
                bLine=false;
                btnErase.setBackground(Color.red);
                bErase = false;
            }
            if(!bCircle && !bLine && !bErase){
                board.setCursor(new Cursor(DEFAULT_CURSOR));
            }
        });
        
        btnLine.addActionListener((ActionEvent e) -> {
            if(!bLine){
                btnLine.setBackground(Color.DARK_GRAY);
                bLine=true;
                board.setCursor(new Cursor(CROSSHAIR_CURSOR));
            }else{
                btnLine.setBackground(Color.LIGHT_GRAY);
                bLine=false;
            }
            if(bCircle || bErase){
                btnCircle.setBackground(Color.LIGHT_GRAY);
                bCircle=false;
                btnErase.setBackground(Color.red);
                bErase = false;
            }
            if(!bCircle && !bLine && !bErase){
                board.setCursor(new Cursor(DEFAULT_CURSOR));
            }
        });
        
        btnErase.addActionListener((ActionEvent e) ->{
            if(!bErase){
                btnErase.setBackground(new Color(120,0,0));
                bErase = true;
                board.setCursor(new Cursor(CROSSHAIR_CURSOR));
            }else{
                btnErase.setBackground(Color.red);
                bErase = false;
            }
            if(bCircle || bLine){
                btnCircle.setBackground(Color.LIGHT_GRAY);
                bCircle=false;
                btnLine.setBackground(Color.LIGHT_GRAY);
                bLine=false;
            }
            if(!bCircle && !bLine && !bErase){
                board.setCursor(new Cursor(DEFAULT_CURSOR));
            }
        });
        
        btnSave.addActionListener( (ActionEvent e) ->{
            fChooser.showSaveDialog(this);
            try {
                if(fChooser.getSelectedFile() != null){
                    board.save(fChooser.getSelectedFile());
                }
            } catch (IOException ex) {
                Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnOpen.addActionListener((ActionEvent e) ->{
            fChooser.showOpenDialog(this);
            if(fChooser.getSelectedFile() != null){
                try {
                    board.open(fChooser.getSelectedFile());
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnSolve.addActionListener((ActionEvent e) ->{
            board.solve();
            lblResult.setText("Result: "+String.valueOf(board.result));
            lblResult.setFont(new Font("Arial",0,30));
        });
        
        btnClean.addActionListener((ActionEvent e) ->{
            board.clean();
            lblResult.setText("");
        });
    }

    public Boolean getbCircle() {
        return bCircle;
    }

    public void setbCircle(Boolean bCircle) {
        this.bCircle = bCircle;
    }

    public Boolean getbLine() {
        return bLine;
    }

    public void setbLine(Boolean bLine) {
        this.bLine = bLine;
    }
    
}
