/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.en.javafx;

import javafx.scene.Node;

/**
 *
 * @author Aabidi Hassan
 */
public class Entete {
    protected Node corps;
    protected boolean alive = true;

    public Node getCorps() {
        return corps;
    }

    public void setCorps(Node corps) {
        this.corps = corps;
    }
    public void position(Zone z){
        this.corps.setTranslateX(position2(z.getX()+50, z.getX1()-50));
        this.corps.setTranslateY(position2(z.getY()+50, z.getY1()-50));
    }
    
    public double position2(double min, double max){
        double y;
        while(true){
            y=Math.random()*(max-min);
            if(y<max & y>min){
                break;
            }
        }
        return y;
    }
    
    public boolean boom(Entete e){
        return this.corps.getBoundsInParent().intersects(e.getCorps().getBoundsInParent());
    }
    public void replace(int i, int ii){
        this.corps.setTranslateX(this.corps.getTranslateX()+i);
        this.corps.setTranslateY(this.corps.getTranslateY()+ii);
    }
    public boolean ifalive(){
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}
