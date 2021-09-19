
package demineur;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class Interface extends JFrame{
    public int espace = 5;
    public int x = -100;
    public int y = -100;
    public int proche = 0;
    
    Random rand = new Random();
    
    int [][] mines = new int[16][9];
    int [][] voisins = new int[16][9];
    boolean[][] devoilee = new boolean[16][9];
    boolean[][] minee = new boolean[16][9];
    
    public Interface(){
        this.setTitle("Jeu Démineur");
        this.setSize(1286,829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        
        
        for(int i = 0; i < 16; i++){
                for(int j = 0; j < 9; j++){
                      if(rand.nextInt(100) < 20){
                       mines[i][j] = 1; 
                    } else{
                          mines[i][j] = 0;
                      }
                    devoilee[i][j] = false;
                }
            } 
        
        for(int i = 0; i < 16; i++){
                for(int j = 0; j < 9; j++){
                    proche=0;
                    for(int m = 0; m < 16; m++){
                        for(int n = 0; n < 9; n++){
                            if(!(m == i && n == j)){
                                if(isN(i,j,m,n)==true){
                                     proche++;
                                }
                                    
                            }
                            
                        }       
                    }
                    voisins[i][j] = proche;
                }
            } 
        
        
        
        /*afficher le contenu de la grille*/
        Grille contenant = new Grille();
        this.setContentPane(contenant);
        
        Deplacement deplacer = new Deplacement();
        this.addMouseMotionListener(deplacer);
        
        Click click = new Click();
        this.addMouseListener(click);
        
    }
    
    /* creation de la grille*/
    public class Grille extends JPanel{
        @Override
        public void paintComponent(Graphics graphe){
            graphe.setColor(Color .DARK_GRAY);
            graphe.fillRect(0,0,1280,800); 
            for(int i = 0; i < 16; i++){
                for(int j = 0; j < 9; j++){
                    graphe.setColor(Color.MAGENTA);   /*la couleur des case*/
                    if(mines[i][j] == 1){
                        graphe.setColor(Color.yellow);
                    }
                    if(devoilee[i][j] == true){
                        graphe.setColor(Color.white);
                        if(mines[i][j] == 1){
                            graphe.setColor(Color.red);
                        }
                    }
                    if(x >= espace+i*80 && x< espace+i*80+80-2*espace && y >= espace+j*80+80+26 && y < espace+j*80+80+26+80-2*espace){
                        graphe.setColor(Color.LIGHT_GRAY);
                    }
                        
                    graphe.fillRect(espace+i*80, espace+j*80+80, 80-2*espace, 80-2*espace); 
                    if(devoilee[i][j] == true){
                        if(mines[i][j] == 0){
                            graphe.setColor(Color.BLACK);
                            graphe.setFont(new Font("Tahoma", Font.BOLD, 40));
                            graphe.drawString(Integer.toString(voisins[i][j]),i*80+27,j*80+80+55);
                        }else{
                             graphe.fillRect(i*80+40, j*80+80, 20, 40);
                             graphe.fillRect(i*80+40, j*80+80, 40, 20);
                             graphe.fillRect(i*80+40, j*80+80, 30, 30);
                             
                        }
                      
                        
                    }
                }
            } 
        }
    }
    
    public class Deplacement implements MouseMotionListener{ 

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            /*
            System.out.println("la souris est déplacé !");
            System.out.println("X: " + x + ", Y: " + y);
            */
        }
        
    }
    
    public class Click implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if (caseX()!= -1 && caseY() != -1){
                devoilee[caseX()][caseY()] = true;
            }
            if (caseX()!= -1 && caseY() != -1){
                System.out.println("la souris est à la case [" + caseX() + "," + caseY());
            } else {
                    System.out.println("la souris n'est dans aucune case");
                }
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }    

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    public int caseX(){
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 9; j++){
               if(x >= espace+i*80 && x< espace+i*80+80-2*espace && y >= espace+j*80+80+26 && y < espace+j*80+80+26+80-2*espace) {
                   return i;
                } 
            }
        }
        return -1;
    }
    
    public int caseY(){
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 9; j++){
               if(x >= espace+i*80 && x< espace+i*80+80-2*espace && y >= espace+j*80+80+26 && y < espace+j*80+80+26+80-2*espace) {
                   return j;
                } 
            }
        }
        return -1;
    }
    public boolean isN(int X, int Y, int cX, int cY){
        if(x-cX < 2 && x-cX >-2 && y-cY < 2 && y-cY > -2 && mines[cX][cY]==1){
            return true;
        }    
        return false;
   }
}

       

  