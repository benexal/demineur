/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

/**
 *
 * @author ASUS
 */
public class Demineur implements Runnable{

    Interface face = new Interface();
    public static void main(String[] args) {
        new Thread(new Demineur()).start();
    }
    @Override
    public void run(){
       while(true){
           face.repaint();
       }
   }
  }
