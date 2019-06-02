/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author luisl
 */
public class Dijkstra {
   private Matrix matriz;
   private int subTope;
   private NodoOperacion auxi=null;
   private int auxAumulado; // es un acumulado auxiliar
   private int costo;
   private NodoOperacion[] arregloNodos; 
   private int tamano;
   private int inicio;     
   private int nodoFin;
   private JPanel pnlPadre;
   private Boolean noSepudo=false;
    public Dijkstra(Matrix matriz, int tamano,int inicio, int nodoFin,JPanel parent){
        
        this.matriz = matriz;        
        this.tamano = tamano;
        this.arregloNodos= new NodoOperacion[tamano]; 
        this.inicio = inicio;
        this.nodoFin = nodoFin;
        this.pnlPadre=parent;
    }

    public int getAcumulado(){
        return arregloNodos[nodoFin].getAcumulado(); 
    }
        
    public void dijkstra(){ 
        for (int i = 0; i < tamano; i++){  // creamos el vector arregloNodos del tamaño de tamano el cual tiene el numero de arregloNodos pintados 
                    arregloNodos[i]= new NodoOperacion(); 
        }
        if(inicio != nodoFin){
            arregloNodos[inicio].setVisitado(true);        
            arregloNodos[inicio].setNombre(inicio);       
            
            do{            
              costo=0;
              auxAumulado = 2000000000; // lo igualamos a esta cifra ya q el acomulado de los nodos, supuestamente  nunca sera mayor 
              arregloNodos[inicio].setEtiqueta(true); 
              for (int j = 0; j < tamano; j++) {
                  //System.out.println("["+j+"]"+"Relacion: "+matriz.getMatrixAdy(j, inicio));
                  
                  if( matriz.getMatrixAdy(j, inicio)==1){
                        //importante 
                        costo= arregloNodos[inicio].getAcumulado()+ matriz.getMatrixVal(j, inicio);// se suman las cantidades de
//                        System.out.println("["+j+"]"+"SubAcumulado: "+costo);
//                        System.out.println("["+j+"]"+"Visitado: "+arregloNodos[j].isVisitado());
//                        System.out.println("["+j+"]"+"Acumulado: "+arregloNodos[j].getAcumulado());
//                        System.out.println("["+j+"]"+"Visitado: "+arregloNodos[j].isEtiqueta());
                        //los enlaces 
                        if(costo <= arregloNodos[j].getAcumulado() && arregloNodos[j].isVisitado()==true && arregloNodos[j].isEtiqueta()== false){
                            //System.out.println("Cambio de datos...");
                            arregloNodos[j].setAcumulado(costo);
                            arregloNodos[j].setVisitado(true);
                            arregloNodos[j].setNombre(j);//hace el cambio al siguiente arregloNodos del 0 -> 1
                            arregloNodos[j].setPredecesor(arregloNodos[inicio]); //el arregloNodos en la posicion j tiene su predecesor en el anterior inicio como listas
                        }
                        // si no se ha visitado para nada
                        else if( arregloNodos[j].isVisitado()==false){
                            arregloNodos[j].setAcumulado(costo);
                            arregloNodos[j].setVisitado(true);
                            arregloNodos[j].setNombre(j);
                            arregloNodos[j].setPredecesor(arregloNodos[inicio]); 
                       }
                 }
              }
                //System.out.println("cambio de inicio...");
              for (int i = 0; i <tamano; i++) { // buscamos cual de los nodos visitado tiene el acomulado menor par escogerlo como inicio 
                if(arregloNodos[i].isVisitado()== true && arregloNodos[i].isEtiqueta()== false){
                   if(arregloNodos[i].getAcumulado()<=auxAumulado){// esta solo es variable de acumulacion
                       inicio= arregloNodos[i].getNombre();
                       auxAumulado= arregloNodos[i].getAcumulado();
                   }
                }               
             }
            subTope++;                
          }while(subTope<tamano+1);   
            //comenzamos en el fin y vamos ir viendo el camino de sus predecesores
          auxi= arregloNodos[nodoFin]; 
           if(auxi.getPredecesor() == null ){
               noSepudo=true;
             JOptionPane.showMessageDialog(null,"No se Pudo LLegar Al Nodo "+nodoFin); 
           
           }     
       }else{
//            System.out.println(inicio+":"+nodoFin);
//            System.out.println("Es el mismo nodo");
        }
    }

    public NodoOperacion getAuxi() {
        return auxi;
    }

    public void setNoSepudo(Boolean noSepudo) {
        this.noSepudo = noSepudo;
    }
    
    public Boolean getNoSepudo() {
        return noSepudo;
    }
    
}