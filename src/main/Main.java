/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.prism.paint.Color;
import gui.PrincipalFrame;
import javax.swing.JColorChooser;
import javax.swing.SwingUtilities;

/**
 *
 * @author luisl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrincipalFrame();
                
            }
        });
    }
    
}
