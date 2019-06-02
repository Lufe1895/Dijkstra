/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 *
 * @author luisl
 */
public class Matrix {
    private int[][] matrixAdy;
    private int[][] matrixVal;
    
    public Matrix(){
        matrixAdy = new int[40][40];
        matrixVal = new int[40][40];
    }
    
    public void dijkstra(int start, int end){
        
    }

    public int getMatrixAdy(int i, int j) {
        return matrixAdy[i][j];
    }

    public void setMatrixAdy(int i, int j, int val) {
        this.matrixAdy[i][j] = val;
    }

    public int getMatrixVal(int i, int j) {
        return matrixVal[i][j];
    }

    public void setMatrixVal(int i, int j, int val) {
        this.matrixVal[i][j]= val;
    }
    
    
}
